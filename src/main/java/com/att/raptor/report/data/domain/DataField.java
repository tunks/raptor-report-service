/**
 * Raptor Reporting service
 * A simple reporing service that enable users to design and generate web-based reports.
 * Built on top of the JasperReports - an open source reporting library 
 * 2016 © ATT Service Assurance  - Raptor POC team 
 *
 */
package com.att.raptor.report.data.domain;

import com.att.raptor.report.data.support.DataFieldType;

/**
 * DataField Component extends DataComponent
 * @author ebrimatunkara
 */
public class DataField extends DataComponent{
    private DataFieldType fieldType;
    
    public DataField(){
    }

    public DataField(String name) {
        super(name);
    }

    public DataField(String id, String name) {
        super(id, name);
    }

    public DataField(String name, DataFieldType fieldType) {
        super(name);
        this.fieldType = fieldType;
    }
    
    public DataField(String id, String name, DataFieldType fieldType) {
        super(id, name);
        this.fieldType = fieldType;
    }

    public DataFieldType getFieldType() {
        return fieldType;
    }

    public void setFieldType(DataFieldType fieldType) {
        this.fieldType = fieldType;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        return hash + super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final DataField other = (DataField) obj;
        return this.fieldType == other.fieldType && (this.getName() == null ? other.getName() == null : this.getName().equals(other.getName()));
    }

    @Override
    public String toString() {
        return "DataField{" + "fieldType=" + fieldType + ", "+super.toString()+"}" ;
    }
    
    
}
