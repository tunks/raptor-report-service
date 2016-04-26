/**
 * Raptor Reporting service
 * A simple reporing service that enable users to design and generate web-based reports.
 * Built on top of the JasperReports - an open source reporting library 
 * 2016 © ATT Service Assurance  - Raptor POC team 
 *
 */
package com.att.raptor.report.data.domain;

import org.springframework.data.annotation.Id;

/**
 * Template Class model
 * 
 * @author ebrimatunkara
 */

public  class Template extends Audit {
	/**
	 * 
	 */
	private static final long serialVersionUID = -1586445912780916256L;
	@Id
	private String id;
	private String name;
   
	public Template() {
		super();
	}

	public Template(String name) {
		this.name = name;
	}

	@Override
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
