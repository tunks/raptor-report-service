/**
 * Raptor Reporting service
 * A simple reporing service that enable users to design and generate web-based reports.
 * Built on top of the JasperReports - an open source reporting library
 * 2016 © ATT Service Assurance  - Raptor POC team
 *
 */
package com.att.raptor.report.engine.service;

import com.att.raptor.report.data.domain.ReportTemplate;
import com.att.raptor.report.engine.dao.JdbcDataDao;
import com.att.raptor.report.engine.query.QueryHandler;
import com.att.raptor.report.engine.query.QueryUtils.QuerySet;
import com.att.raptor.report.engine.query.callback.DbQueryCallback;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.PreparedStatementCallback;
import org.springframework.stereotype.Service;

/**
 *
 * @author ebrimatunkara
 */
@Service("reportQueryService")
public class ReportQueryService implements QueryService<List,QuerySet>{
    @Autowired
    private JdbcDataDao jdbcDatadao;
    
    @Override
    public List query(QuerySet querySet) {
        PreparedStatementCallback callback = new DbQueryCallback(querySet.getFieldSet());
        return jdbcDatadao.getResults(querySet.getQueryString(), callback);
    }  

    @Override
    public List query(QueryHandler handler, ReportTemplate template) {
       return null;
    }
}
