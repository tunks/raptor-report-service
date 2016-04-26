/**
 * Raptor Reporting service
 * A simple reporting service that enable users to design and generate web-based reports.
 * Built on top of the JasperReports - an open source reporting library
 * 2016 © ATT Service Assurance  - Raptor POC team
 *
 */
package com.att.raptor.report.web.controller;

import com.att.raptor.report.data.domain.ReportFolder;
import com.att.raptor.report.data.service.ReportFolderService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.projection.ProjectionFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * ReportFolderController -- implementation of ReportFolder Controller
 *
 * @author ebrimatunkara
 */
@RestController
@RequestMapping("/att/reports/repositories")
public class ReportFolderController implements ControllerBase<ReportFolder> {
    @Autowired
    private ReportFolderService reportFolderService;
    
    @Autowired
    private ProjectionFactory projectionFactory;
     
    @Override
    public ReportFolder find(String id) {
        return reportFolderService.find(id);
    }

    @RequestMapping(method = RequestMethod.GET)
    public List<ReportFolder> findAll(@RequestParam(value="parentId", required=false) String parentId) {
        return reportFolderService.findAll();
    }

    @Override
    public void deleteAll() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @RequestMapping(method = RequestMethod.DELETE)
    @Override
    public void delete(String id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @RequestMapping(method = RequestMethod.POST)
    @Override
    public ReportFolder create(ReportFolder object) {
        return reportFolderService.create(object);
    }

    @RequestMapping(method = RequestMethod.PUT)
    @Override
    public ReportFolder update(ReportFolder object) {
        return reportFolderService.update(object);
    }
}