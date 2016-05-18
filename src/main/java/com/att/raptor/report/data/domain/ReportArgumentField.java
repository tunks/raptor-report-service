/**
 * Raptor Reporting service
 * A simple reporting service that enable users to design and generate web-based reports.
 * Built on top of the JasperReports - an open source reporting library
 * 2016 © ATT Service Assurance  - Raptor POC team
 */
package com.att.raptor.report.data.domain;

import com.att.raptor.report.data.support.DataFieldType;
import com.att.raptor.report.data.support.FieldOperator;

/**
 * ReportArgument Field
 * @author ebrimatunkara 
 */
public class ReportArgumentField extends DataField {
    /**
     * ReportFieldArugment type
     */
    private FieldOperator operator;
    /*
     * Report Field argument
     */
    private ReportArgumentValue argValue;

    public ReportArgumentField() {
        super();
    }

    public ReportArgumentField(FieldOperator argumentOperator, String name, DataFieldType fieldType) {
        super(name, fieldType);
        this.operator = argumentOperator;
    }

    public FieldOperator getOperator() {
        return operator;
    }

    public void setOperator(FieldOperator operator) {
        this.operator = operator;
    }

    public ReportArgumentField(FieldOperator operator, ReportArgumentValue argValue) {
        this.operator = operator;
        this.argValue = argValue;
    }

    public ReportArgumentValue getArgValue() {
        return argValue;
    }

    public void setArgValue(ReportArgumentValue argValue) {
        this.argValue = argValue;
    }
    
}
