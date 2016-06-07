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
import com.att.raptor.report.engine.support.ReportFormat;
import java.util.concurrent.ExecutionException;
/**
 *
 * @author ebrimatunkara
 * @param <T>
 */
public interface ReportBaseService<T>{
    public T generate(QueryHandler handler,ReportTemplate template  );
    public T generate(QueryHandler handler, ReportTemplate template,ReportFormat format ) 
            throws InterruptedException,ExecutionException;
}
