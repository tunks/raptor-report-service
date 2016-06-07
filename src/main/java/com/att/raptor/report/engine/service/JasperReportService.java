/**
 * Raptor Reporting service
 * A simple reporing service that enable users to design and generate web-based reports.
 * Built on top of the JasperReports - an open source reporting library
 * 2016 © ATT Service Assurance  - Raptor POC team
 *
 */
package com.att.raptor.report.engine.service;

import com.att.raptor.report.data.domain.ReportComponent;
import com.att.raptor.report.data.domain.ReportField;
import com.att.raptor.report.data.domain.ReportOutputFile;
import com.att.raptor.report.data.domain.ReportTemplate;
import com.att.raptor.report.data.service.CrudBaseService;
import com.att.raptor.report.engine.query.QueryHandler;
import com.att.raptor.report.engine.query.QueryUtils.QuerySet;
import com.att.raptor.report.engine.support.JasperReportBuilder;
import com.att.raptor.report.engine.support.ProducerStream;
import com.att.raptor.report.engine.support.ReportFormat;
import com.att.raptor.report.engine.support.ReportUtils;
import com.att.raptor.report.engine.support.ResultSetDataStream;
import com.att.raptor.report.engine.support.StreamDataSource;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Deque;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.logging.Level;
import java.util.logging.Logger;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRParameter;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.fill.JRSwapFileVirtualizer;
import net.sf.jasperreports.engine.util.JRSwapFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.support.collections.DefaultRedisList;
import org.springframework.stereotype.Service;
import org.springframework.util.StopWatch;

/**
 * JasperReportService implementation
 *
 * @author ebrimatunkara
 */
@Service("jasperReportService")
public class JasperReportService implements ReportBaseService<ReportOutputFile> {

    @Autowired
    private ReportQueryService reportQueryService;

    @Autowired
    private CrudBaseService reportOutputService;

    @Autowired
    private RedisTemplate<String,Map<String,Object>> redisTemplate;

    @Value("${report.directory}")
    private String fileDirectory;
    /*
     */
    private final ExecutorService executor = Executors.newFixedThreadPool(50);

    @Override
    public ReportOutputFile generate(QueryHandler handler, ReportTemplate template, ReportFormat format) throws InterruptedException, ExecutionException {
        HashMap params = new HashMap();
        JRSwapFile file = new JRSwapFile(fileDirectory+ "/tmp", 1048, 1024);
        JRSwapFileVirtualizer virtualizer = new JRSwapFileVirtualizer(100, file, false);
        params.put(JRParameter.REPORT_VIRTUALIZER, virtualizer);
        //redisTemplate.execute(session)
        Deque<List<Map<String, Object>>> queue = new DefaultRedisList(redisTemplate.boundListOps("queue:101")); 
        ResultSetDataStream pStream = new ResultSetDataStream(queue);
        StreamDataSource<Map<String,Object>> dSource = new StreamDataSource(pStream);
        List<JasperPrint> jsPrints = new ArrayList();
        try {
            jsPrints = createJasperPrints(template, handler,dSource,pStream,params);
        } catch (ExecutionException | InterruptedException ex) {
            Logger.getLogger(JasperReportService.class.getName()).log(Level.SEVERE, null, ex);
        }
        //            reportQueryService.query(querySet, stream);
         
        Future<ReportOutputFile> output = executor.submit( new GenerateReportFile(jsPrints, format, template.getId(),fileDirectory));
        //JasperPrint jsPrinter = builder.build(handler.getTemplate(), result);
        //return ReportUtils.generateReport(jsPrints, format, template.getId());
        return output.get();
    }

    private List<JasperPrint> createJasperPrints(ReportTemplate template, QueryHandler handler, JRDataSource dataSource, ProducerStream stream , Map params) throws ExecutionException, InterruptedException {
        JasperReportBuilder builder = new JasperReportBuilder(fileDirectory);
        builder.addParams(params);
        /**
         * Parallel parse on all report template components List<QuerySet>
         * querySet = new ArrayList();
         *
         */
        List<QueryParserTask> tasks = new ArrayList();
        for (ReportComponent component : template.getComponents()) {
            tasks.add(new QueryParserTask(handler, component));
        }
        /*
        * Invoke all parallel callable tasks
         */
        List<Future<QuerySet>> futures = executor.invokeAll(tasks);
        List<JasperPrint> jsPrints = new ArrayList();
        for (Future<QuerySet> future : futures) {
            QuerySet<ReportField> querySet = future.get();
            // List results = reportQueryService.query(querySet);
            //JasperPrint jsPrint = builder.build(template, results, querySet.getFieldSet());
             //execute query 
            executor.execute(new QueryAction( reportQueryService,querySet, stream));
            JasperPrint jsPrint = builder.build(template, dataSource, querySet.getFieldSet());
            jsPrints.add(jsPrint);
        }
        return jsPrints;
    }

    @Override
    public ReportOutputFile generate(QueryHandler handler, ReportTemplate template) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * QueryParserTask -- implementation of a parallel query parser for Report
     * component
     *
     */
    private class QueryParserTask implements Callable<QuerySet> {

        private final QueryHandler<ReportComponent, QuerySet> handler;
        private final ReportComponent component;

        public QueryParserTask(QueryHandler handler, ReportComponent component) {
            this.handler = handler;
            this.component = component;
        }

        @Override
        public QuerySet call() throws Exception {
            return handler.parseQuery(component);
        }
    }
    
    //Query execution action thread
    private class QueryAction implements Runnable{ 
        private final QueryService service;
        private final QuerySet querySet;
        private final ProducerStream stream;

        public QueryAction(QueryService service, QuerySet querySet, ProducerStream stream) {
            this.service = service;
            this.querySet = querySet;
            this.stream = stream;
        }
        
        @Override
        public void run() {
          StopWatch watch = new StopWatch();
          watch.start();
          service.query(querySet, stream);
          watch.stop();
          System.out.println("Query action time measurement: "+watch.getLastTaskTimeMillis());
        
        }
      }
    /**
     * generate report 
     */
    private class GenerateReportFile implements Callable<ReportOutputFile>{
        private final List<JasperPrint> jsPrints;
        private final ReportFormat format;
        private final String id;
        private final String directory;

        public GenerateReportFile(List<JasperPrint> jsPrints, ReportFormat format, String id, String directory) {
            this.jsPrints = jsPrints;
            this.format = format;
            this.id = id;
            this.directory = directory;
        }
           
        @Override
        public ReportOutputFile call() throws Exception {
             StopWatch w = new StopWatch();
             w.start();
             ReportOutputFile file  = ReportUtils.generateReport(jsPrints, format, id, directory);
             w.stop();
             System.out.println("Generate action report time "+w.getLastTaskTimeMillis());
             return file;
        }
    } 
    
    /**
     * generate report 
     */
    private class GenerateReport implements Callable<OutputStream>{
        private final List<JasperPrint> jsPrints;
        private final ReportFormat format;
        private final String id;
        private final String directory;

        public GenerateReport(List<JasperPrint> jsPrints, ReportFormat format, String id, String directory) {
            this.jsPrints = jsPrints;
            this.format = format;
            this.id = id;
            this.directory = directory;
        }
           
        @Override
        public OutputStream call() throws Exception {
             StopWatch w = new StopWatch();
             w.start();
             OutputStream output = ReportUtils.generateReport(jsPrints, format, id);
             w.stop();
             System.out.println("Generate action report time "+w.getLastTaskTimeMillis());
             return output;
        }
    
    }
}
