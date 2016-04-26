/**
 * Raptor Reporting service
 * A simple reporing service that enable users to design and generate web-based reports.
 * Built on top of the JasperReports - an open source reporting library
 * 2016 © ATT Service Assurance  - Raptor POC team
 *
 */
package com.att.raptor.report.data.domain;

import com.att.raptor.report.data.support.DataFieldType;
import com.att.raptor.report.data.support.FieldArgumentType;

/**
 * ReportArgument Field
 * @author ebrimatunkara
 */
public class ReportArgumentField extends DataField{
    /**
     * ReportFieldArugment type
     **/
    private FieldArgumentType argumentType;
    
    public ReportArgumentField() {
      super();
    }

    public ReportArgumentField(FieldArgumentType argumentType, String name, DataFieldType fieldType) {
        super(name, fieldType);
        this.argumentType = argumentType;
    }

    public FieldArgumentType getArgumentType() {
        return argumentType;
    }

    public void setArgumentType(FieldArgumentType argumentType) {
        this.argumentType = argumentType;
    }
       
}
