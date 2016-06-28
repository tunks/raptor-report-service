package com.att.raptor.report.web.controller;

/**
 * Raptor Reporting service A simple reporting service that enable users to
 * design and generate web-based reports. Built on top of the JasperReports - an
 * open source reporting library 2016 © ATT Service Assurance - Raptor POC team
 */
import com.att.raptor.report.data.domain.ReportOutputFile;
import com.att.raptor.report.data.domain.ReportTemplate;
import com.att.raptor.report.data.service.ReportTemplateService;
import com.att.raptor.report.engine.query.JdbcQueryHandler;
import com.att.raptor.report.engine.query.QueryHandler;
import com.att.raptor.report.engine.query.QueryParser;
import com.att.raptor.report.engine.service.JasperReportService;
import com.att.raptor.report.engine.support.JasperReportFactory;
import com.att.raptor.report.engine.support.ReportUtils;
import com.att.raptor.report.engine.support.ReportFormat;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.concurrent.ExecutionException;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.request.async.DeferredResult;

/**
 * ReportView controller
 */
@Controller
@RequestMapping("/att/reports/view")
public class ReportViewController {

    /*
     * jasper report service
     */
    @Autowired
    private JasperReportService jasperReportService;
    /*
     Report template service
     */
    @Autowired
    private ReportTemplateService reportTemplateService;

    /**
     * HTTP request method to generate report
     *
     * @param templateId
     * @param type
     * @param response
     * @throws java.io.IOException
     * @throws java.lang.InterruptedException
     * @throws java.util.concurrent.ExecutionException
     *
     */

    @RequestMapping(method = RequestMethod.GET)
    public void generateReport(@RequestParam(value = "templateId", required = true) String templateId,
            @RequestParam(value = "type", required = false, defaultValue = "html") String type, HttpServletResponse response) throws IOException, InterruptedException, ExecutionException {
        DeferredResult<ResponseEntity<?>> deferredResult = new DeferredResult<>();
        
        // response.g
        ReportFormat format = ReportFormat.formatType(type);
        MediaType mediaType = ReportUtils.requestMediaType(format);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(mediaType);
        ReportTemplate template = reportTemplateService.find(templateId);
        if (template != null) {
            String filename = templateId + "." + format.getFormat();
            QueryParser parser = JasperReportFactory.createQueryParser();
            QueryHandler handler = new JdbcQueryHandler(parser);
            
            ReportOutputFile outputFile = jasperReportService.generate(handler, template, format);
            response.setContentType(mediaType.toString());
            response.setHeader("Content-Disposition", String.format("attachment; filename=\"" + filename + "\""));     
            try (InputStream inputStream = new FileInputStream(outputFile.getPath())) {
                FileCopyUtils.copy(inputStream, response.getOutputStream());
            }
            //deferredResult.

        } else {
            response.setStatus(HttpStatus.NOT_FOUND.value());
        }
    }
}
