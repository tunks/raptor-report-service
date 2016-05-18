/**
 * Raptor Reporting service
 * A simple reporting service that enable users to design and generate web-based reports.
 * Built on top of the JasperReports - an open source reporting library
 * 2016 © ATT Service Assurance  - Raptor POC team
 *
 */
package com.att.raptor.report.web.controller;

import com.att.raptor.report.data.domain.ReportComponent;
import com.att.raptor.report.data.service.ReportComponentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * ReportComponent controller
 * @author ebrimatunkara
 */
@RestController
@RequestMapping("/att/reports/templates/components")
public class ReportComponentController implements ControllerBase<ReportComponent>{
    @Autowired
    private ReportComponentService reportComponentService;
    
    @RequestMapping(value="/{id}", method= RequestMethod.GET)
    @Override
    public ReportComponent find(@PathVariable String id) {
       return reportComponentService.find(id);
    }

    @Override
    public void deleteAll() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @RequestMapping(value="/{id}", method= RequestMethod.DELETE)
    @Override
    public void delete(String id) {
       reportComponentService.delete(id);
    }

    @RequestMapping(method= RequestMethod.POST)
    @Override
    public ReportComponent create(ReportComponent object) {
        return reportComponentService.create(object);
    }
    
    @RequestMapping(method= RequestMethod.PUT)
    @Override
    public ReportComponent update(ReportComponent object) {
       return reportComponentService.update(object);
    }
    
}
