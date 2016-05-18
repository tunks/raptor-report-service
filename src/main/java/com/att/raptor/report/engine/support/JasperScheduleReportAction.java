/**
 * Raptor Reporting service
 * A simple reporing service that enable users to design and generate web-based reports.
 * Built on top of the JasperReports - an open source reporting library
 * 2016 © ATT Service Assurance  - Raptor POC team
 *
 */
package com.att.raptor.report.engine.support;

import com.att.raptor.report.data.domain.ReportOutput;
import com.att.raptor.report.data.domain.ReportOutputFile;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.logging.Level;
import java.util.logging.Logger;
import net.sf.jasperreports.engine.JasperPrint;

/**
 * JasperScheduleReportAction implementation of the ReportBaseAction
 *
 * @author ebrimatunkara
 */
public class JasperScheduleReportAction implements ReportBaseAction<ReportOutput> {
    private final ExecutorService executor = Executors.newSingleThreadExecutor();
    private final JasperPrint jsPrinter;
    private final String fileDirectory;
    private final String reportName;
    
    public JasperScheduleReportAction(JasperPrint jsPrinter, String fileDirectory,String reportName) {
        this.jsPrinter = jsPrinter;
        this.fileDirectory = fileDirectory;
        this.reportName = reportName;
    }

    @Override
    public ReportOutput process(String templateId) {
           ReportOutput output = new ReportOutput(templateId, new HashSet(), reportName);
        try {
            List<ScheduleTask> tasks = new ArrayList();
            for(ReportFormat format : ReportFormat.values()){
                tasks.add(new ScheduleTask(templateId, format,  jsPrinter,fileDirectory));
            }
            
            List<Future<ReportOutputFile>> futures = executor.invokeAll(tasks);
            for(Future<ReportOutputFile> future : futures){
               output.addFile(future.get());
           }
            
        } catch (InterruptedException | ExecutionException ex) {
            Logger.getLogger(JasperScheduleReportAction.class.getName()).log(Level.SEVERE, null, ex);
        }
        return output;
    }

    @Override
    public void shutdown() {
            executor.shutdown();
    }
   
    private class ScheduleTask implements Callable<ReportOutputFile>{
        private final ReportFormat format;
        private final String templateId;
        private final JasperPrint jsPrinter;
        private String directory;
        
        public ScheduleTask(String templateId, ReportFormat format,JasperPrint jsPrinter) {
            this.templateId = templateId;
            this.format = format;
            this.jsPrinter = jsPrinter;
        }

        public ScheduleTask(String templateId, ReportFormat format,  JasperPrint jsPrinter, String directory) {
            this(templateId,format,jsPrinter);
            this.directory = directory;
        }
        
        
        @Override
        public ReportOutputFile call() throws Exception {
              return  ReportUtils.generateReportFile(jsPrinter, format, templateId,directory);
        }

    }

}
