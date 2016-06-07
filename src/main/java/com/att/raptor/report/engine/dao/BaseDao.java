/**
 * Raptor Reporting service
 * A simple reporing service that enable users to design and generate web-based reports.
 * Built on top of the JasperReports - an open source reporting library
 * 2016 © ATT Service Assurance  - Raptor POC team
 *
 */
package com.att.raptor.report.engine.dao;

import java.util.Set;
import com.att.raptor.report.engine.query.QueryHandler;
import java.util.List;
import org.springframework.jdbc.core.PreparedStatementCallback;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.jdbc.support.DatabaseMetaDataCallback;

/**
 * Report Engine Data Access Layer interface
 * @author ebrimatunkara
 * @param <T>
 * @param <V>
 */
public interface BaseDao<T,V>{
   /**
    * Get results 
     * @param callback
    * @return 
    */
   public Set getModels(DatabaseMetaDataCallback callback);
   
   /**
    * Get results with query callback
    * @param query
    * @param callback
    * @return 
    */
   public V getResults(T query, PreparedStatementCallback<List> callback);
   
   public void query(T query, ResultSetExtractor callback);
   public void query(T query, RowCallbackHandler callback);
}
