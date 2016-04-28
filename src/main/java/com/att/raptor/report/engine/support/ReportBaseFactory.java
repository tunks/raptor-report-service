/**
 * Raptor Reporting service
 * A simple reporing service that enable users to design and generate web-based reports.
 * Built on top of the JasperReports - an open source reporting library
 * 2016 © ATT Service Assurance  - Raptor POC team
 *
 */
package com.att.raptor.report.engine.support;

/**
 *
 * @author ebrimatunkara
 * @param <P>
 * @param <T>
 */
public interface  ReportBaseFactory<P,T extends IGenerator> {
    public T createGenerator(String type);
    public T createGenerator(P object, String type);    
}
