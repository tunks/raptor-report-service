/**
 * Raptor Reporting service
 * A simple reporing service that enable users to design and generate web-based reports.
 * Built on top of the JasperReports - an open source reporting library
 * 2016 © ATT Service Assurance  - Raptor POC team
 *
 */
package com.att.raptor.report.data.domain;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * @author ebrimatunkara ReportTemplate -- extends Template model Contains the
 * report template meta-information
 */
@Document
public class ReportTemplate extends Template implements Serializable {
    private static final long serialVersionUID = 1L;
     /**
      * Report template layout
      **/
    private TemplateLayout layout;
    /*
     Folder parent id
    */
    private String parentId;
    /**
     * Report component Groups
     */
    @DBRef
    private Set<ReportComponent> components;

    public ReportTemplate() {
        super();
        initialize();
    }

    public ReportTemplate(String name) {
        super(name);
        initialize();
    }

    public ReportTemplate(String name, String parentId) {
        super(name);
        this.parentId = parentId;
        initialize();
    }

    
    public TemplateLayout getLayout() {
        return layout;
    }

    public void setLayout(TemplateLayout layout) {
        this.layout = layout;
    }

    public Set<ReportComponent> getComponents() {
        return components;
    }

    public void setComponents(Set<ReportComponent> components) {
        this.components = components;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public void addComponent(ReportComponent component) {
        components.add(component);
    }
    
    private void initialize(){
       components = new HashSet();
    }
}
