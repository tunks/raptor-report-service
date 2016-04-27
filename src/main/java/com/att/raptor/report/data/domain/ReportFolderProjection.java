/**
 * Raptor Reporting service
 * A simple reporing service that enable users to design and generate web-based reports.
 * Built on top of the JasperReports - an open source reporting library
 * 2016 © ATT Service Assurance  - Raptor POC team
 *
 */
package com.att.raptor.report.data.domain;

import java.util.List;
import org.springframework.data.rest.core.config.Projection;

/**
 * Reports folder projections
 * @author ebrimatunkara
 */
@Projection(name = "inlineAddress", types = { ReportFolder.class }) 
public interface ReportFolderProjection {
     public String getId();
     public String getParentId();
     public String getName();
     public List<ReportFolder> getSubFolders();
     public List<ReportTemplate> getReportTemplates();
}
