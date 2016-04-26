/**
 * Raptor Reporting service
 * A simple reporing service that enable users to design and generate web-based reports.
 * Built on top of the JasperReports - an open source reporting library
 * 2016 © ATT Service Assurance  - Raptor POC team
 *
 */
package com.att.raptor.report.engine.query;

import java.util.Set;

/**
 * DataQueryHandler interface
 * @author ebrimatunkara
 * @param <T>
 * @param <R>
 */
public interface DataQueryHandler<T,R> {
      public R processQuery();
      public T getQuery();
      public String getQueryString();
      public Set getFieldSet();
}
