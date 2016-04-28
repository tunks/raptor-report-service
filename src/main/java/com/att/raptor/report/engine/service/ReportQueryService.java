/**
 * Raptor Reporting service
 * A simple reporing service that enable users to design and generate web-based reports.
 * Built on top of the JasperReports - an open source reporting library
 * 2016 © ATT Service Assurance  - Raptor POC team
 *
 */
package com.att.raptor.report.engine.service;

import com.att.raptor.report.engine.dao.JdbcDataDao;
import com.att.raptor.report.engine.query.QueryHandler;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author ebrimatunkara
 */
@Service("reportQueryService")
public class ReportQueryService implements IService<List>{
    @Autowired
    private JdbcDataDao jdbcDatadao;
    
    @Override
    public List query(QueryHandler handler) {
        return jdbcDatadao.getResults(handler);
    }  
}
