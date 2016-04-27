/**
 * Raptor Reporting service
 * A simple reporing service that enable users to design and generate web-based reports.
 * Built on top of the JasperReports - an open source reporting library
 * 2016 © ATT Service Assurance  - Raptor POC team
 *
 */
package com.att.raptor.report.data.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Report Folder repository -- contains a collection of templates and sub report
 * folders
 *
 * @author ebrimatunkara
 */
@Document
public class ReportFolder extends DataComponent {

    /**
     * Parent id
     *
     */
    private String parentId;
    /**
     * Collection of report sub folders folders
     */
    @DBRef
    private List<ReportFolder> subFolders;
    /*
      Collection of report templates
     **/
    @DBRef
    private List<ReportTemplate> reportTemplates;

    public ReportFolder() {
        reportTemplates = new ArrayList();
        subFolders = new ArrayList();
    }

    public ReportFolder(String name) {
        super(name);
        initialize();
    }

    public ReportFolder(String name, String parentId) {
        super(name);
        this.parentId = parentId;
        initialize();
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public List<ReportFolder> getSubFolders() {
        return subFolders;
    }

    public void setSubFolders(List<ReportFolder> subFolders) {
        this.subFolders = subFolders;
    }

    public List<ReportTemplate> getReportTemplates() {
        return reportTemplates;
    }

    public void setReportTemplates(List<ReportTemplate> reportTemplates) {
        this.reportTemplates = reportTemplates;
    }
    
    public void addReportFolder(ReportFolder folder){
       subFolders.add(folder);
    }
    
    public void addReportTemplate(ReportTemplate template){
       reportTemplates.add(template);
    }
    
    private void initialize(){
        reportTemplates = new ArrayList();
        subFolders = new ArrayList();
    }

}
