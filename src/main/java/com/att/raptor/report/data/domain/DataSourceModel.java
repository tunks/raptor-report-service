/**
 * Raptor Reporting service
 * A simple reporing service that enable users to design and generate web-based reports.
 * Built on top of the JasperReports - an open source reporting library 
 * 2016 © ATT Service Assurance  - Raptor POC team 
 *
 */
package com.att.raptor.report.data.domain;

import java.util.Set;

/**
 * DataSourceModel - Data source schema entity model is mapped to this class object
 * @author ebrimatunkara
 */
public class DataSourceModel extends DataComponent{
    private Set<DataField> fields;

    public DataSourceModel() {
        super();
    }

    public DataSourceModel(String name) {
        super(name);
    }

    public DataSourceModel(String id, String name) {
        super(id, name);
    }

    public DataSourceModel(String id, String name, Set<DataField> fields) {
        super(id, name);
        this.fields = fields;
    }
    
    public Set<DataField> getFields() {
        return fields;
    }

    public void setFields(Set<DataField> fields) {
        this.fields = fields;
    }    

    @Override
    public String toString() {
        return "DataSourceModel{"+ super.toString()  + ", fields=" + fields + '}';
    }
    
}