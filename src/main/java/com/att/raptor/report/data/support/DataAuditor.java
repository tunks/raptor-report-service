/**
 * Raptor Reporting service
 * A simple reporing service that enable users to design and generate web-based reports.
 * Built on top of the JasperReports - an open source reporting library 
 * 2016 © ATT Service Assurance  - Raptor POC team 
 *
 */
package com.att.raptor.report.data.support;

import org.springframework.data.domain.AuditorAware;

/**
 * @author ebrimatunkara
 * Data Auditor implementation
 */
public class DataAuditor  implements AuditorAware<String>{
	/* (non-Javadoc)
	 * @see org.springframework.data.domain.AuditorAware#getCurrentAuditor()
	 */
	@Override
	public String getCurrentAuditor() {
		/*TODO
		 * user auditor user name 
		 **/
		return "system";
	}

}

