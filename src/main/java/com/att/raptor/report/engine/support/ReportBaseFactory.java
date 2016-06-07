/**
 * Raptor Reporting service
 * A simple reporing service that enable users to design and generate web-based reports.
 * Built on top of the JasperReports - an open source reporting library
 * 2016 © ATT Service Assurance  - Raptor POC team
 *
 */
package com.att.raptor.report.engine.support;

import com.att.raptor.report.engine.support.generators.IGenerator;

/**
 *
 * @author ebrimatunkara
 * @param <P>
 * @param <T>
 */
public interface  ReportBaseFactory<P,T extends IGenerator> {
    public T createGenerator(ReportFormat format);
    public T createGenerator(P object, ReportFormat format);    
    public T createGenerator(P object, ReportFormat format, String directory); 
}
