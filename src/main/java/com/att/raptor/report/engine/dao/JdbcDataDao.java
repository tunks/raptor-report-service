/**
 * Raptor Reporting service
 * A simple reporting service that enable users to design and generate web-based reports.
 * Built on top of the JasperReports - an open source reporting library
 * 2016 © ATT Service Assurance  - Raptor POC team
 *
 */
package com.att.raptor.report.engine.dao;

import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCallback;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.jdbc.support.DatabaseMetaDataCallback;
import org.springframework.jdbc.support.JdbcUtils;
import org.springframework.jdbc.support.MetaDataAccessException;
import org.springframework.stereotype.Component;
import com.att.raptor.report.engine.query.callback.DbTableCallback;
import com.att.raptor.report.engine.query.callback.StreamingStatementCreator;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowCallbackHandler;

/**
 * JDBCDataDao class- implementation of the BaseDao This data source access
 * layer class loads data from the given data source properties
 *
 * @author ebrimatunkara
 */
@Component("jdbcDatadao")
public class JdbcDataDao extends JdbcDaoSupport implements BaseDao<String, List> {

    @Autowired
    public JdbcDataDao(JdbcTemplate jdbcTemplate) {
        super.setJdbcTemplate(jdbcTemplate);
    }

    /**
     * Load the data models of the dao data source
     *
     */
    @Override
    public Set getModels(DatabaseMetaDataCallback callback) {
        try {
            DbTableCallback tableCallback = new DbTableCallback();
            Object result = JdbcUtils.extractDatabaseMetaData(this.getDataSource(), tableCallback);
            return (Set) result;
        } catch (MetaDataAccessException ex) {
            Logger.getLogger(JdbcDataDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * Data
     *
     * @param callback
     */
    @Override
    public List getResults(String query, PreparedStatementCallback<List> callback) {
         return getJdbcTemplate().execute(query,callback);
    }

    @Override
    public void query(String query, RowCallbackHandler callback) {
          getJdbcTemplate().query(new StreamingStatementCreator(query), callback);
    }

    @Override
    public void query(String query, ResultSetExtractor callback) {
         // getJdbcTemplate().setFetchSize(0);
          getJdbcTemplate().query(new StreamingStatementCreator(query), callback);
    }

}
