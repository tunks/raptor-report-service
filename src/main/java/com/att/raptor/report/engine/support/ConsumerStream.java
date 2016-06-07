/**
 * Raptor Reporting service
 * A simple reporing service that enable users to design and generate web-based reports.
 * Built on top of the JasperReports - an open source reporting library
 * 2016 © ATT Service Assurance  - Raptor POC team
 *
 */
package com.att.raptor.report.engine.support;

/**
 * ConsumerStream interface
 * @author ebrimatunkara
 * @param <T>
 */
public interface ConsumerStream<T> {
    public T poll();
    public boolean isComplete();
}
