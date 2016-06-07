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
import java.util.Map;
import net.sf.jasperreports.engine.JRDataSource;

/** 
 * Report Builder interface
 * @author ebrimatunkara
 * @param <T>
 * @param <V>
 */
public interface ReportBuilder <T,V>{
    public T build(ReportTemplate template, List data);
    public T build(ReportTemplate template, List<Map<?,?>>  data, V fieldset);
    public T build(ReportTemplate template,  JRDataSource datasource, V fieldset);
    public void generate(ReportTemplate template,V fieldset);
}
