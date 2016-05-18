/**
 * Raptor Reporting service
 * A simple reporting service that enable users to design and generate web-based reports.
 * Built on top of the JasperReports - an open source reporting library
 * 2016 © ATT Service Assurance  - Raptor POC team
 */
package com.att.raptor.report.data.domain;

import com.att.raptor.report.data.support.FieldValueType;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

/**
 * Report Argument Value
 * @author ebrimatunkara
 */
@JsonInclude(Include.NON_NULL)
public class ReportArgumentValue extends DataField{
    /*
     * FieldValue type 
     */
    private FieldValueType valueType; 
    
    /*
      Argument value
     */
    private String  customValue;

    public FieldValueType getValueType() {
        return valueType;
    }

    public void setValueType(FieldValueType valueType) {
        this.valueType = valueType;
    }

    public String getCustomValue() {
        return customValue;
    }

    public void setCustomValue(String customValue) {
        this.customValue = customValue;
    }
   
}

