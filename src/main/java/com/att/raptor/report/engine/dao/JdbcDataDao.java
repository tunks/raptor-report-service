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
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.jdbc.support.DatabaseMetaDataCallback;
import org.springframework.jdbc.support.JdbcUtils;
import org.springframework.jdbc.support.MetaDataAccessException;
import org.springframework.stereotype.Component;
import com.att.raptor.report.engine.query.QueryHandler;

/**
 * JDBCDataDao class- implementation of the BaseDao This data source access
 * layer class loads data from the given data source properties
 *
 * @author ebrimatunkara
 */
@Component("jdbcDatadao")
public class JdbcDataDao extends JdbcDaoSupport implements BaseDao<DataSourceProperty, List> {

    @Autowired
    public JdbcDataDao(JdbcTemplate jdbcTemplate) {
        super.setJdbcTemplate(jdbcTemplate);
    }

    /**
     * Load the data models of the dao data source
     *
     */
    @Override
    public Set getModels() {
        try {
            GetTableNames tableNames = new GetTableNames();
            Object result = JdbcUtils.extractDatabaseMetaData(this.getDataSource(), tableNames);
            return (Set) result;
        } catch (MetaDataAccessException ex) {
            Logger.getLogger(JdbcDataDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Set getModels(DataSourceProperty t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * Data
     *
     * @param queryHandler
     */
    @Override
    public List getResults(QueryHandler queryHandler) {
        return getJdbcTemplate().execute(queryHandler.getQueryString(), new DataListPreparedStatement(queryHandler));
    }

    @Override
    public List getResults(DataSourceProperty t, QueryHandler query) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private class GetTableNames implements DatabaseMetaDataCallback {

        @Override
        public Object processMetaData(DatabaseMetaData dbmd) throws SQLException {
            String catalog = dbmd.getConnection().getCatalog();
            ResultSet rs = dbmd.getTables(catalog, null, "%", null);
            String tableName;
            Set<DataSourceModel> models = new HashSet();
            while (rs.next()) {
                tableName = rs.getString(3);
                Set<DataField> fields = getColumns(dbmd.getColumns(catalog, tableName, tableName, null));
                models.add(new DataSourceModel(null, tableName, fields));
            }
            return models;
        }

        private Set getColumns(ResultSet rs) throws SQLException {
            int typeIndex;
            String columnName;
            DataFieldType dataType;
            Set<DataField> fields = new HashSet();
            while (rs.next()) {
                columnName = rs.getString(4);
                typeIndex = Integer.parseInt(rs.getString(5));
                dataType = DataFieldType.getDataFieldType(typeIndex);
                fields.add(new DataField(null, columnName, dataType));
            }
            return fields;
        }
    }

    public class DataListPreparedStatement implements PreparedStatementCallback<List> {
        QueryHandler queryHandler;

        public DataListPreparedStatement() {
        }

        public DataListPreparedStatement(QueryHandler queryHandler) {
            this.queryHandler = queryHandler;
        }
        
        @Override
        public List doInPreparedStatement(PreparedStatement ps) throws SQLException, DataAccessException {           
            return QueryUtils.extractArticleListFromRs(ps.executeQuery(),queryHandler.getFieldSet());
        }
    }
}
