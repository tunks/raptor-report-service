/**
 * Raptor Reporting service
 * A simple reporting service that enable users to design and generate web-based reports.
 * Built on top of the JasperReports - an open source reporting library 
 * 2016 © ATT Service Assurance  - Raptor POC team 
 *
 */
package com.att.raptor.report.data.domain;

import java.io.Serializable;

/**
 * DataSource Type
 * @author ebrimatunkara
 */
public enum DataSourceType implements Serializable{
     SQL,
     JSON,
     HTTP
}
