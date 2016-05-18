/**
 * Raptor Reporting service
 * A simple reporting service that enable users to design and generate web-based reports.
 * Built on top of the JasperReports - an open source reporting library
 * 2016 © ATT Service Assurance  - Raptor POC team
 *
 */
package com.att.raptor.report.engine.support;

/**
 * Custom Message interface
 * @author ebrimatunkara
 * @param <T>
 * @param <V>
 */
public interface IMessage<T,V> {
      public V createMessage(T object);
}
