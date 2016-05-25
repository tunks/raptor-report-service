/**
 * Raptor Reporting service
 * A simple reporing service that enable users to design and generate web-based reports.
 * Built on top of the JasperReports - an open source reporting library
 * 2016 © ATT Service Assurance  - Raptor POC team
 *
 */
package com.att.raptor.report.engine.service;

import com.att.raptor.report.data.domain.ReportOutput;
import com.att.raptor.report.data.domain.ReportTemplate;
import com.att.raptor.report.data.domain.Template;
import com.att.raptor.report.data.service.CrudBaseService;
import com.att.raptor.report.engine.query.QueryHandler;
import com.att.raptor.report.engine.support.JasperReportBuilder;
import com.att.raptor.report.engine.support.JasperScheduleReportAction;
import com.att.raptor.report.engine.support.ReportBaseAction;
import com.att.raptor.report.engine.support.ReportFormat;
import com.att.raptor.report.engine.support.ReportUtils;
import java.io.ByteArrayOutputStream;
import java.util.List;
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
    
    @Override
    public ByteArrayOutputStream process(QueryHandler handler, ReportFormat format) {
        JasperReportBuilder builder = new JasperReportBuilder();
        List result = reportQueryService.query(handler);
        Template template = handler.getTemplate();
        JasperPrint jsPrinter = builder.build(handler.getTemplate(), result);
        return ReportUtils.generateReport(jsPrinter, format, template.getId());
    }

    @Override
    public void process(QueryHandler handler) {
        JasperReportBuilder builder = new JasperReportBuilder();
        ReportTemplate template = handler.getTemplate();
        List result = reportQueryService.query(handler);
        JasperPrint jsPrinter = builder.build(template, result);
        ReportBaseAction<ReportOutput> action =  new JasperScheduleReportAction (jsPrinter,fileDirectory,template.getName());
        ReportOutput output = action.process(template.getId());
        reportOutputService.create(output);
        action.shutdown();
    }    
}
