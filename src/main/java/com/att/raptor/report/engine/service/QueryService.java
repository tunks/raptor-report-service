/**
 * Raptor Reporting service
 * A simple reporing service that enable users to design and generate web-based reports.
 * Built on top of the JasperReports - an open source reporting library
 * 2016 © ATT Service Assurance  - Raptor POC team
 *
 */
package com.att.raptor.report.engine.service;

import com.att.raptor.report.data.domain.ReportTemplate;
import com.att.raptor.report.engine.query.QueryHandler;

/**
 * Query Service interface
 * @author ebrimatunkara
 * @param <T>
 * @param <V>
 */
public interface QueryService<T,V> {
      public T query(V object);
      public T query(QueryHandler handler, ReportTemplate template);
}
