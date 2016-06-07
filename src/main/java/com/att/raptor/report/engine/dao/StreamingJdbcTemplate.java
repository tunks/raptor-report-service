/**
 * Raptor Reporting service
 * A simple reporing service that enable users to design and generate web-based reports.
 * Built on top of the JasperReports - an open source reporting library
 * 2016 © ATT Service Assurance  - Raptor POC team
 *
 */
package com.att.raptor.report.engine.dao;

import java.sql.SQLException;
import java.sql.Statement;
import javax.sql.DataSource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceUtils;

/**
 *
 * @author ebrimatunkara
 */
/**
 * A {@link JdbcTemplate} which will make it possible to mimic streaming
 * Resultset's by allowing negative fetch sizes to be set on the
 * {@link Statement}.
 *
 * @author reik.schatz
 */
public class StreamingJdbcTemplate extends JdbcTemplate {

    public StreamingJdbcTemplate() {
    }

    public StreamingJdbcTemplate(final DataSource dataSource) {
        super(dataSource);
    }

    public StreamingJdbcTemplate(final DataSource dataSource, final boolean lazyInit) {
        super(dataSource, lazyInit);
    }

    /**
     * Prepare the given JDBC Statement (or PreparedStatement or
     * CallableStatement), applying statement settings such as fetch size, max
     * rows, and query timeout. Unlike in {@link JdbcTemplate} you can also
     * specify a negative fetch size.
     *
     * @param stmt the JDBC Statement to prepare
     * @throws java.sql.SQLException if thrown by JDBC API
     * @see #setFetchSize
     * @see #setMaxRows
     * @see #setQueryTimeout
     * @see
     * org.springframework.jdbc.datasource.DataSourceUtils#applyTransactionTimeout
     */
    @Override
    protected void applyStatementSettings(final Statement stmt) throws SQLException {
        int fetchSize = getFetchSize();
        stmt.setFetchSize(fetchSize);

        int maxRows = getMaxRows();
        if (maxRows > 0) {
            stmt.setMaxRows(maxRows);
        }
        DataSourceUtils.applyTimeout(stmt, getDataSource(), getQueryTimeout());
    }
    
    @Override
    public void afterPropertiesSet(){
       this.setFetchSize(1000);
    }
    
}
