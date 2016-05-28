/**
 * Raptor Reporting service
 * A simple reporing service that enable users to design and generate web-based reports.
 * Built on top of the JasperReports - an open source reporting library
 * 2016 © ATT Service Assurance  - Raptor POC team
 *
 */
package com.att.raptor.report.engine.dao;

import com.att.raptor.report.data.domain.DataField;
import com.att.raptor.report.data.support.DataFieldType;
import com.att.raptor.report.data.domain.DataSourceModel;
import com.att.raptor.report.data.domain.DataSourceProperty;
import java.sql.ResultSet;
import com.att.raptor.report.engine.query.QueryUtils;
import java.sql.DatabaseMetaData;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCallback;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.jdbc.support.DatabaseMetaDataCallback;
import org.springframework.jdbc.support.JdbcUtils;
import org.springframework.jdbc.support.MetaDataAccessException;
import org.springframework.stereotype.Component;
import com.att.raptor.report.engine.query.QueryUtils.QuerySet;
import com.att.raptor.report.engine.query.callback.DbQueryCallback;
import com.att.raptor.report.engine.query.callback.DbTableCallback;

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
     * @param querySet
     * @param callback
     */
//    @Deprecated
//    @Override
//    public List getResults(String query) {
//        return getJdbcTemplate().execute(query, new DbQueryCallback(querySet.getFieldSet()));
//    }

    @Override
    public List getResults(String query, PreparedStatementCallback<List> callback) {
         return getJdbcTemplate().execute(query,callback);
    }

}
