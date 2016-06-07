/**
 * Raptor Reporting service
 * A simple reporting service that enable users to design and generate web-based reports.
 * Built on top of the JasperReports - an open source reporting library
 * 2016 © ATT Service Assurance  - Raptor POC team
 *
 */
package com.att.raptor.report.web.controller;

import com.att.raptor.report.data.domain.ReportOutput;
import com.att.raptor.report.data.domain.ReportTemplate;
import com.att.raptor.report.data.service.ReportOutputService;
import com.att.raptor.report.data.service.ReportTemplateService;
import com.att.raptor.report.engine.query.JdbcQueryHandler;
import com.att.raptor.report.engine.query.QueryParser;
import com.att.raptor.report.engine.service.JasperReportService;
import com.att.raptor.report.engine.support.JasperReportFactory;
import com.att.raptor.report.engine.support.ReportFormat;
import com.att.raptor.report.engine.support.ReportRequest;
import com.att.raptor.report.engine.support.ReportUtils;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Report Schedule Action Controller
 */
@Controller
@RequestMapping("/att/reports/action")
public class ReportActionController {
    @Autowired
    private JasperReportService jasperReportService;

    @Autowired
    private ReportTemplateService reportTemplateService;

    @Autowired
    private ReportOutputService reportOutputService;

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<?> processScheduleAction(@RequestBody ReportRequest request) {
        ReportTemplate template = reportTemplateService.find(request.getTemplateId());
        if (template != null) {
            QueryParser  parser = JasperReportFactory.createQueryParser();
            jasperReportService.generate(new JdbcQueryHandler(parser),template);
            return new ResponseEntity(HttpStatus.OK);
        }
        return new ResponseEntity<>("No such report available", HttpStatus.NOT_FOUND);
    }

    //load schedule generated reports
    @RequestMapping(value = "/{outputId}", method = RequestMethod.GET)
    public void viewScheduleReports(@PathVariable String outputId,@RequestParam(value = "type", required = false, defaultValue = "html") String type, HttpServletResponse response) 
            throws FileNotFoundException, IOException
    {
        ReportOutput output = reportOutputService.find(outputId);
        if (output != null) {
            ReportFormat format = ReportFormat.formatType(type);
            MediaType mediaType = ReportUtils.requestMediaType(format);
            String filePath = output.getFilePath(format);
            File file = new File(filePath);
            String createdon = output.getCreatedDate().toString();
            String fileName = output.getName() + "-"+ createdon + "." + format.getFormat().toLowerCase();
            response.setContentType(mediaType.toString());
            /* "Content-Disposition : inline" will show viewable types [like images/text/pdf/anything viewable by browser] right on browser 
            while others(zip e.g) will be directly downloaded [may provide save as popup, based on your browser setting.]*/
            response.setHeader("Content-Disposition", String.format("inline; filename=\"" + fileName + "\""));
            /* "Content-Disposition : attachment" will be directly download, may provide save as popup, based on your browser setting*/
            //response.setHeader("Content-Disposition", String.format("attachment; filename=\"%s\"", file.getName()));
            response.setContentLength((int) file.length());
            try (InputStream inputStream = new FileInputStream(file)) {
                FileCopyUtils.copy(inputStream, response.getOutputStream());
            }
        }
    }
}
