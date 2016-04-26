/**
 * Raptor Reporting service
 * A simple reporing service that enable users to design and generate web-based reports.
 * Built on top of the JasperReports - an open source reporting library
 * 2016 © ATT Service Assurance  - Raptor POC team
 *
 */
package com.att.raptor.report.data.service;

import com.att.raptor.report.data.domain.DataSource;
import com.att.raptor.report.data.repositories.DataSourceRepository;
import java.util.List;
import javax.annotation.Resource;

/**
 *
 * @author ebrimatunkara
 */
public class DataSourceService implements CrudBaseService<DataSource,String>{
    @Resource
    DataSourceRepository dataSourceRepository;
    
    @Override
    public List<DataSource> findAll() {
       return dataSourceRepository.findAll();
    }

    @Override
    public DataSource find(String id) {
       return dataSourceRepository.findOne(id);
    }

    @Override
    public DataSource create(DataSource t) {
      return dataSourceRepository.save(t);
    }

    @Override
    public DataSource update(DataSource t) {
        return dataSourceRepository.update(t);
    }
    
}
