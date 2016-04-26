/**
 * Raptor Reporting service
 * A simple reporing service that enable users to design and generate web-based reports.
 * Built on top of the JasperReports - an open source reporting library
 * 2016 © ATT Service Assurance  - Raptor POC team
 *
 */
package com.att.raptor.report.engine.dao;

import java.util.List;
import java.util.Set;
import com.att.raptor.report.engine.query.DataQueryHandler;

/**
 * Report Engine Data Access Layer interface
 * @author ebrimatunkara
 * @param <T>
 * @param <R>
 */
public interface BaseDao<T,R>{
   /**
    * Get results 
    * @return 
    */
   public Set getModels();
   /**
    * Get results of the data source schema
     * @param t
     * @return 
    **/
   public Set getModels(T t);
   
   /**
    * Get results with query 
    * @param t
    * @param query
    * @return 
    */
   public R getResults(DataQueryHandler query);
   
   /**
    * Get results with query 
    * @param t
    * @param query
    * @return 
    */
   public R getResults(T t,  DataQueryHandler query);
}
