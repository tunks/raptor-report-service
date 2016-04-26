/**
 * Raptor Reporting service
 * A simple reporing service that enable users to design and generate web-based reports.
 * Built on top of the JasperReports - an open source reporting library
 * 2016 © ATT Service Assurance  - Raptor POC team
 *
 */
package com.att.raptor.report.data.support;

/**
 * FieldArgumentType
 * @author ebrimatunkara
 */
public enum FieldArgumentType {
      EQUALS,
      LESS_THAN,
      LESS_THAN_EQUALS,
      GREATER_THAN,
      GREATER_THAN_EQUALS,
      RANGE,
      LIKE;
      
}
