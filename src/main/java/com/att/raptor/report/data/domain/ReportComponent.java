/**
 * Raptor Reporting service
 * A simple reporing service that enable users to design and generate web-based reports.
 * Built on top of the JasperReports - an open source reporting library
 * 2016 © ATT Service Assurance  - Raptor POC team
 *
 */
package com.att.raptor.report.data.domain;

import com.att.raptor.report.data.support.ReportComponentType;
import java.util.Set;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Report Component groups - stores meta information of the template layout data groups
 * @author ebrimatunkara
 */
@Document
public class ReportComponent extends DataComponent {
    private ReportComponentType  reportComponentType;
    
    private Set<DataComponent> reportComponents;
   
    public ReportComponent() {
        super();
    }

    public ReportComponent(String name) {
        super(name);
    }

    public ReportComponent(String id, String name) {
        super(id, name);
    }

    public ReportComponent( String id, String name,ReportComponentType reportComponentType) {
        super(id, name);
        this.reportComponentType = reportComponentType;
    }
   
    public Set<DataComponent> getReportComponents() {
        return reportComponents;
    }

    public void setReportComponents(Set<DataComponent> reportComponents) {
        this.reportComponents = reportComponents;
    }

    public ReportComponentType getReportComponentTypeType() {
        return reportComponentType;
    }

    public void setReportComponentType(ReportComponentType reportComponentType) {
        this.reportComponentType = reportComponentType;
    } 
}
