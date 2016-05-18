/**
 * Raptor Reporting service
 * A simple reporting service that enable users to design and generate web-based reports.
 * Built on top of the JasperReports - an open source reporting library
 * 2016 © ATT Service Assurance  -- Raptor POC team
 */
package com.att.raptor.report.data.domain;

import com.att.raptor.report.data.support.DataFieldType;
import com.att.raptor.report.data.support.FieldAggregateType;
import java.util.HashSet;
import java.util.Set;
import org.springframework.data.mongodb.core.mapping.DBRef;

/**
 * ReportFieldComponent -- that is contained in the ReportComponentGroup
 * @author ebrimatunkara
 */
public class ReportField extends DataField{

    /**
     * Field label
     */
    private String label;
    
    /**
     * Report Field Aggregate type
     **/
    private FieldAggregateType aggregateType;

    private Set<ReportArgumentField>  fieldArguments;
    
    /**
     * TODO - report field style 
     **/
    
    public ReportField() {
      initialize();
    }

    public ReportField(String name) {
        super(name);
         initialize();
    }

    public ReportField(String id, String name) {
        super(id, name);
        initialize();
    }

    public ReportField(String id, String name, DataFieldType fieldType) {
        super(id, name, fieldType);
    }

    public ReportField( String name, DataFieldType fieldType, FieldAggregateType aggregateType) {
        super(name, fieldType);
        this.aggregateType = aggregateType;
        initialize();
    }
    
    public ReportField(String name, DataFieldType fieldType, String description, String label) {
        super(name, fieldType);
        this.label = label;
        initialize();
    }
    
    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    } 

    public FieldAggregateType getAggregateType() {
        return aggregateType;
    }

    public void setAggregateType(FieldAggregateType aggregateType) {
        this.aggregateType = aggregateType;
    }

    public Set<ReportArgumentField> getFieldArguments() {
        return fieldArguments;
    }

    public void setFieldArguments(Set<ReportArgumentField> fieldArguments) {
        this.fieldArguments = fieldArguments;
    }
    
    private void initialize(){
       this.fieldArguments = new HashSet();
    }
}
