/**
 * Raptor Reporting service
 * A simple reporing service that enable users to design and generate web-based reports.
 * Built on top of the JasperReports - an open source reporting library
 * 2016 © ATT Service Assurance  - Raptor POC team
 *
 */
package com.att.raptor.report.web.controller;

import com.att.raptor.report.data.domain.DataSource;
import com.att.raptor.report.data.service.DataSourceService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * Report DataSource controller 
 * @author ebrimatunkara
 */
@RestController
@RequestMapping("/att/reports/datasources")
public class DataSourceController implements ControllerBase<DataSource>  {
    @Autowired
    private DataSourceService datasourceService;
    
    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public List<DataSource> findAll(){
        return datasourceService.findAll();
    }
    
    @Override
    public DataSource find(String id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void deleteAll() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void delete(String id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public DataSource create(DataSource object) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public DataSource update(DataSource object) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
