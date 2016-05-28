/**
 * Raptor Reporting service
 * A simple reporting service that enable users to design and generate web-based reports.
 * Built on top of the JasperReports - an open source reporting library
 * 2016 © ATT Service Assurance  - Raptor POC team
 *
 */
package com.att.raptor.report.engine.query.callback;

import com.att.raptor.report.engine.query.QueryUtils;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.Set;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.PreparedStatementCallback;

/**
 *  Database results query results callback
 * @author ebrimatunkara
 */
public class DbQueryCallback implements PreparedStatementCallback<List> {
         Set<String> fieldSet;

        public DbQueryCallback(Set<String> fieldSet) {
            this.fieldSet = fieldSet;
        }
        /**
         * Extract the result list
         * return List
         * @param ps
         * @return 
         * @throws java.sql.SQLException
         **/
        @Override
        public List doInPreparedStatement(PreparedStatement ps) throws SQLException, DataAccessException {           
            return QueryUtils.extractListFromRs(ps.executeQuery(),fieldSet);
        }
    }