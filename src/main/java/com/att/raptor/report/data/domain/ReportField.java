/**
 * Raptor Reporting service
 * A simple reporting service that enable users to design and generate web-based reports.
 * Built on top of the JasperReports - an open source reporting library
 * 2016 © ATT Service Assurance  -- Raptor POC team
 */
package com.att.raptor.report.data.domain;

import com.att.raptor.report.data.support.DataFieldType;
import com.att.raptor.report.data.support.FieldAggregateType;
import org.springframework.data.mongodb.core.mapping.DBRef;

/**
 * ReportFieldComponent -- that is contained in the ReportComponentGroup
 * @author ebrimatunkara
 */
public class ReportField extends DataField{
    /**
     * Field description
     **/
    private String description;
    /**
     * Field Model name
     */
    private String modelName;
    /**
     * Field label
     */
    private String label;
    
    /**
     * Report Field Aggregate type
     **/
    private FieldAggregateType aggregateType;
    /**
     * Report field argument - stores the argument for a report field
     */
    @DBRef
    private ReportArgumentField  fieldArgument;
    
    /**
     * TODO - report field style 
     **/
    
    public ReportField() {
    }

    public ReportField(String name) {
        super(name);
    }

    public ReportField(String id, String name) {
        super(id, name);
    }

    public ReportField(String id, String name, DataFieldType fieldType) {
        super(id, name, fieldType);
    }

    public ReportField( String name, DataFieldType fieldType, FieldAggregateType aggregateType) {
        super(name, fieldType);
        this.aggregateType = aggregateType;
    }
    
    public ReportField(String name, DataFieldType fieldType, String description, String label) {
        super(name, fieldType);
        this.description = description;
        this.label = label;
    }
    
    public ReportField(String name, DataFieldType fieldType,String description, String label, FieldAggregateType aggregateType) {
        super(name, fieldType);
        this.description = description;
        this.label = label;
        this.aggregateType = aggregateType;
    }
   
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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

    public ReportArgumentField getFieldArgument() {
        return fieldArgument;
    }

    public void setFieldArgument(ReportArgumentField fieldArgument) {
        this.fieldArgument = fieldArgument;
    }

    public String getModelName() {
        return modelName;
    }

    public void setModelName(String modelName) {
        this.modelName = modelName;
    }
}
