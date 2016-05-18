/**
 * Raptor Reporting service
 * A simple reporing service that enable users to design and generate web-based reports.
 * Built on top of the JasperReports - an open source reporting library 
 * 2016 © ATT Service Assurance  - Raptor POC team 
 *
 */
package com.att.raptor.report.data.domain;

import java.io.Serializable;
import java.util.Objects;
import javax.validation.constraints.NotNull;

import org.springframework.data.annotation.Id;

/**
 * Data component - maps data elements from the data source
 * The data elements could be one or more of the following:
 *   1. fields
 *   2. data object/table
 *   3. Aggregate function
 * @author ebrimatunkara
 *
 */

public abstract class DataComponent extends Audit implements Serializable{
    @Id
    private String id;
   /**
    * Name of the data component
    **/
    private String name;
    
    /**
     * Description
     **/
    private String description;

    public DataComponent() {
    }

    public DataComponent(String name) {
        this.name = name;
    }

    public DataComponent(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public DataComponent(String id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }
    
    @Override
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public int hashCode() {
        int hash = 7;
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
        final DataComponent other = (DataComponent) obj;
        return Objects.equals(this.name, other.name);
    }

    @Override
    public String toString() {
        return "DataComponent{" + "id=" + id + ", name=" + name + '}';
    }
}

