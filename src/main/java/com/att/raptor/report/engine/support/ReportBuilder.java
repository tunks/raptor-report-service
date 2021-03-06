/**
 * Raptor Reporting service
 * A simple reporing service that enable users to design and generate web-based reports.
 * Built on top of the JasperReports - an open source reporting library
 * 2016 © ATT Service Assurance  - Raptor POC team
 *
 */
package com.att.raptor.report.engine.support;

import com.att.raptor.report.data.domain.ReportTemplate;
import java.util.List;

/** 
 * Report Builder interface
 * @author ebrimatunkara
 * @param <T>
 * @param <V>
 */
public interface ReportBuilder <T,V>{
    public T build(ReportTemplate template, List data);
    public T build(ReportTemplate template, List data, V fieldset);
}
