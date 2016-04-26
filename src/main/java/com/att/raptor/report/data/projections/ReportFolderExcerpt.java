/**
 * Raptor Reporting service
 * A simple reporing service that enable users to design and generate web-based reports.
 * Built on top of the JasperReports - an open source reporting library
 * 2016 © ATT Service Assurance  - Raptor POC team
 *
 */
package com.att.raptor.report.data.projections;

import com.att.raptor.report.data.domain.ReportFolder;
import com.att.raptor.report.data.domain.ReportTemplate;
import java.util.List;

/**
 * Reports folder projections
 * @author ebrimatunkara
 */
public interface ReportFolderExcerpt {
     public String getId();
     public String getParentId();
     public String getName();
     public List<ReportFolder> getSubFolders();
     public List<ReportTemplate> getReportTemplates();
}
