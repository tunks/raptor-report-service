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
import com.att.raptor.report.engine.query.JdbcQueryFactory;
import com.att.raptor.report.engine.query.QueryHandler;
import com.att.raptor.report.engine.query.QueryUtils.QuerySet;
import com.att.raptor.report.engine.query.callback.QueueStreamCallbackHandler;
import com.att.raptor.report.engine.query.callback.StreamingResultSetExtractor;
import com.att.raptor.report.engine.support.ProducerStream;
import java.util.List;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.PreparedStatementCallback;
import org.springframework.stereotype.Service;

/**
 * ReportQueryService 
 * @author ebrimatunkara
 */
@Service("reportQueryService")
public class ReportQueryService implements QueryService<List,QuerySet>{
    @Autowired
    private JdbcDataDao jdbcDatadao;
    
    @Override
    public List query(QuerySet querySet) {
        PreparedStatementCallback callback = JdbcQueryFactory.createNewQueryCallback(querySet);
        return jdbcDatadao.getResults(querySet.getQueryString(), callback);
    }  

    @Override
    public void query(QuerySet querySet, ProducerStream stream) {     
        Set<String> fieldSet = JdbcQueryFactory.createResultFieldSet(querySet);
        jdbcDatadao.query(querySet.getQueryString(), new QueueStreamCallbackHandler(stream,fieldSet));
    }
}
