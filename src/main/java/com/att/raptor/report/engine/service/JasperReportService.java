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
import com.att.raptor.report.data.domain.ReportOutput;
import com.att.raptor.report.data.domain.ReportTemplate;
import com.att.raptor.report.data.service.CrudBaseService;
import com.att.raptor.report.engine.query.QueryHandler;
import com.att.raptor.report.engine.query.QueryUtils.QuerySet;
import com.att.raptor.report.engine.support.JasperReportBuilder;
import com.att.raptor.report.engine.support.JasperScheduleReportAction;
import com.att.raptor.report.engine.support.ReportBaseAction;
import com.att.raptor.report.engine.support.ReportFormat;
import com.att.raptor.report.engine.support.ReportUtils;
import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.logging.Level;
import java.util.logging.Logger;
import net.sf.jasperreports.engine.JasperPrint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * JasperReportService implementation
 * @author ebrimatunkara
 */
@Service("jasperReportService")
public class JasperReportService implements ReportBaseService<ByteArrayOutputStream> {
    @Autowired
    private ReportQueryService reportQueryService;
   
    @Autowired
    private  CrudBaseService reportOutputService;
    @Value("${report.directory}")
    private String fileDirectory;
    /*
    */
    private final ExecutorService executor = Executors.newSingleThreadExecutor(); 
    
    @Override
    public ByteArrayOutputStream generate(QueryHandler handler, ReportTemplate template, ReportFormat format) {
        List<JasperPrint> jsPrints = new ArrayList();
        try {
            jsPrints = createJasperPrints(template, handler);
        } catch (ExecutionException | InterruptedException ex) {
            Logger.getLogger(JasperReportService.class.getName()).log(Level.SEVERE, null, ex);
        }
        //JasperPrint jsPrinter = builder.build(handler.getTemplate(), result);
        return ReportUtils.generateReport(jsPrints, format, template.getId());
    }

    private List<JasperPrint> createJasperPrints(ReportTemplate template, QueryHandler handler) throws ExecutionException, InterruptedException {
        JasperReportBuilder builder = new JasperReportBuilder();
        /**
         * Parallel parse on all report template components
         * List<QuerySet> querySet = new ArrayList();
         **/
        List<QueryParserTask> tasks = new ArrayList();
        for(ReportComponent component : template.getComponents())
        {
            tasks.add(new QueryParserTask(handler,component));
        }
        /*
        * Invoke all parallel callable tasks
        */
        List<Future<QuerySet>> futures= executor.invokeAll(tasks);
        List<JasperPrint> jsPrints  = new ArrayList();
        for(Future<QuerySet> future : futures){
            QuerySet<ReportField> querySet = future.get();
            List results = reportQueryService.query(querySet);
            JasperPrint jsPrint = builder.build(template,results,querySet.getFieldSet());
            jsPrints.add(jsPrint);
        }
        return jsPrints;
    }

    @Override
    public ByteArrayOutputStream generate(QueryHandler handler, ReportTemplate template) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    /**
     * QueryParserTask  -- implementation of a parallel query parser for Report component
     **/
    private class QueryParserTask implements Callable<QuerySet>{
        private final QueryHandler<ReportComponent, QuerySet> handler;
        private final ReportComponent component;
        public QueryParserTask( QueryHandler handler, ReportComponent component) {
            this.handler = handler;
            this.component = component;
        }
        
        @Override
        public QuerySet call() throws Exception {
            return  handler.parseQuery(component);
        }
    }
}
