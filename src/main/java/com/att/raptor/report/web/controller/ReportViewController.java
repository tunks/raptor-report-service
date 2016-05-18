package com.att.raptor.report.web.controller;

/**
 * Raptor Reporting service A simple reporting service that enable users to
 * design and generate web-based reports. Built on top of the JasperReports - an
 * open source reporting library 2016 © ATT Service Assurance - Raptor POC team
 */
import com.att.raptor.report.data.domain.ReportTemplate;
import com.att.raptor.report.data.service.ReportTemplateService;
import com.att.raptor.report.engine.query.JdbcQueryHandler;
import com.att.raptor.report.engine.query.QueryHandler;
import com.att.raptor.report.engine.service.JasperReportService;
import com.att.raptor.report.engine.support.ReportUtils;
import com.att.raptor.report.engine.support.ReportFormat;
import java.io.ByteArrayOutputStream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * ReportView controller
 */
@Controller
@RequestMapping("/att/reports/view")
public class ReportViewController {
    @Autowired
    private JasperReportService jasperReportService;

    @Autowired
    private ReportTemplateService reportTemplateService;

    /**
     * HTTP request method to generate report
     * @param templateId
     * @param type
     * @return 
     **/
    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<?> generateReport(@RequestParam(value = "templateId", required = true) String templateId,
              @RequestParam(value = "type", required = false, defaultValue = "html") String type) {
        
        ReportFormat format = ReportFormat.formatType(type);
        MediaType mediaType = ReportUtils.requestMediaType(format);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(mediaType);

        ReportTemplate template = reportTemplateService.find(templateId);
        if (template != null) {
            QueryHandler handler = new JdbcQueryHandler(template);
            ByteArrayOutputStream out = jasperReportService.process(handler,format);
            //add extra header options for downloadabale media types
            ReportUtils.addHeaderOptions(httpHeaders, templateId, format);
            return new ResponseEntity(out.toByteArray(), httpHeaders, HttpStatus.OK);
        }
        return new ResponseEntity("No such report available", httpHeaders, HttpStatus.NOT_FOUND);
    }
}
