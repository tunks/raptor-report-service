/**
 * Raptor Reporting service
 * A simple reporing service that enable users to design and generate web-based reports.
 * Built on top of the JasperReports - an open source reporting library 
 * 2016 © ATT Service Assurance  - Raptor POC team 
 *
 */
package com.att.raptor.report.data.domain;

import java.util.Set;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * DataSource extends DataComponent
 * @author ebrimatunkara
 *
 */
@Document
public class DataSource extends DataComponent{
     /**
      * DataSource property
      **/
    private DataSourceProperty property;
    /**
     * Data source data models
     **/
    private Set<DataSourceModel> models;

    public DataSource() {
        super();
    }

    public DataSource(String name) {
        super(name);
    }

    public DataSource(DataSourceProperty property) {
        this.property = property;
    }

    public DataSource(String name, DataSourceProperty property) {
        super(name);
        this.property = property;
    }
    
    
    public DataSourceProperty getProperty() {
        return property;
    }

    public void setProperty(DataSourceProperty property) {
        this.property = property;
    }

    public Set<DataSourceModel> getModels() {
        return models;
    }

    public void setModels(Set<DataSourceModel> models) {
        this.models = models;
    }
   
}

