package com.att.raptor.report.web.controller;

/**
 * Raptor Reporting service A simple reporing service that enable users to
 * design and generate web-based reports. Built on top of the JasperReports - an
 * open source reporting library 2016 © ATT Service Assurance - Raptor POC team
 *
 */
/**
 *
 * @author ebrimatunkara
 */
import com.att.raptor.report.data.domain.ReportTemplate;
import com.att.raptor.report.data.repositories.ReportTemplateRepository;
import com.att.raptor.report.engine.query.JdbcQueryHandler;
import com.att.raptor.report.engine.query.QueryHandler;
import com.att.raptor.report.engine.service.JasperReportService;
import java.io.ByteArrayOutputStream;
import javax.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * ReportView controller
 *
 */
@Controller
public class ReportViewController {
    @Autowired
    private JasperReportService jasperReportService;
    @Resource
    private ReportTemplateRepository reportTemplateRepository;

    @RequestMapping("/reports/templates/view")
    public ResponseEntity<?> generateReport(@RequestParam(value="templateId", required=true) String templateId,
                                            @RequestParam(value="type", required=false, defaultValue="html") String type) 
    {
           QueryHandler handler;
           MediaType mediaType = requestMediaType(type);
           HttpHeaders httpHeaders = new HttpHeaders();
           httpHeaders.setContentType(mediaType);
           
           ReportTemplate template = reportTemplateRepository.findOne(templateId);
           if(template != null){
              handler = new JdbcQueryHandler(template);
              ByteArrayOutputStream data = (ByteArrayOutputStream) jasperReportService.generate(type, handler);
              //add extra header options for downloadabale media types
              addHeaderOptions(httpHeaders,templateId,type);
              return new ResponseEntity<>(data.toByteArray(), httpHeaders, HttpStatus.OK);
           }
           return new ResponseEntity<>("No such report available", httpHeaders, HttpStatus.NOT_FOUND);
    }

    
    private MediaType requestMediaType(String type){
        switch (type) {
            case "pdf":
                return MediaType.parseMediaType("application/pdf");
            case "xls":
            case "xlsx":
                return MediaType.APPLICATION_OCTET_STREAM;
            default:
                return MediaType.TEXT_HTML;
        }
    }
    
    private void addHeaderOptions(HttpHeaders httpHeader, String templateId, String type){
           switch(type){
               case "pdf":
                   httpHeader.setContentDispositionFormData("attachment", templateId + ".pdf");
                   break;
               case "xls":
               case "xlsx":
                   httpHeader.setContentDispositionFormData("attachment", templateId + ".xls");               
           }
          
    }

}
