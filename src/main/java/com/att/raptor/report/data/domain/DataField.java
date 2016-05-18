/**
 * Raptor Reporting service
 * A simple reporing service that enable users to design and generate web-based reports.
 * Built on top of the JasperReports - an open source reporting library 
 * 2016 © ATT Service Assurance  - Raptor POC team 
 *
 */
package com.att.raptor.report.data.domain;

import com.att.raptor.report.data.support.DataFieldType;
import java.util.Objects;

/**
 * DataField Component extends DataComponent
 * @author ebrimatunkara
 */
public class DataField extends DataComponent{
    private DataFieldType fieldType;
        /**
     * Field Model name
     */
    private String modelName;
    
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
        int hash = 5;
        hash = 67 * hash + ((this.modelName != null)? Objects.hashCode(this.modelName) : 0);
        hash = 67 * hash + ((this.getName() != null)? Objects.hashCode(this.getName()) : 0);
        return hash;
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
        
        if(this.modelName == null || other.modelName == null){
           return false;
        }
        
       if(this.getName() == null || other.getName() == null){
           return false;
        }
       
        if (!Objects.equals(this.modelName, other.modelName)) {
            return false;
        }
        return this.getName().equals(other.getName());
    }



    public String getModelName() {
        return modelName;
    }

    public void setModelName(String modelName) {
        this.modelName = modelName;
    }

    
    @Override
    public String toString() {
        return "DataField{" + "fieldType=" + fieldType + ", "+super.toString()+"}" ;
    }    
}
