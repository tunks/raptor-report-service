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

/**
 * DataSource property
 * @author ebrimatunkara
 */
public class DataSourceProperty implements Serializable{
     /**
      * Data source host information and credentials
      *  -- username
      *  -- password
      *  -- server
      *   
      **/
    private String username;
    private String password;
    private String serverAddress;
    private String serverPort;
    private String url;
    private DataSourceType sourceType;
    
    public DataSourceProperty() {
   
    }

    public DataSourceProperty(String username, String password, String url, DataSourceType sourceType) {
        this.username = username;
        this.password = password;
        this.url = url;
        this.sourceType = sourceType;
    }

    public DataSourceProperty(String username, String password, String serverAddress, String serverPort, DataSourceType sourceType) {
        this.username = username;
        this.password = password;
        this.serverAddress = serverAddress;
        this.serverPort = serverPort;
        this.sourceType = sourceType;
    }
   
    public DataSourceProperty(String username, String password, String serverAddress, String serverPort, String url, DataSourceType sourceType) {
        this.username = username;
        this.password = password;
        this.serverAddress = serverAddress;
        this.serverPort = serverPort;
        this.url = url;
        this.sourceType = sourceType;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getServerAddress() {
        return serverAddress;
    }

    public void setServerAddress(String serverAddress) {
        this.serverAddress = serverAddress;
    }

    public String getServerPort() {
        return serverPort;
    }

    public void setServerPort(String serverPort) {
        this.serverPort = serverPort;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public DataSourceType getSourceType() {
        return sourceType;
    }

    public void setSourceType(DataSourceType sourceType) {
        this.sourceType = sourceType;
    }    

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 29 * hash + Objects.hashCode(this.username);
        hash = 29 * hash + Objects.hashCode(this.password);
        hash = 29 * hash + Objects.hashCode(this.serverAddress);
        hash = 29 * hash + Objects.hashCode(this.serverPort);
        hash = 29 * hash + Objects.hashCode(this.url);
        hash = 29 * hash + Objects.hashCode(this.sourceType);
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
        final DataSourceProperty other = (DataSourceProperty) obj;
        if (!Objects.equals(this.username, other.username)) {
            return false;
        }
        if (!Objects.equals(this.password, other.password)) {
            return false;
        }
        if (!Objects.equals(this.serverAddress, other.serverAddress)) {
            return false;
        }
        if (!Objects.equals(this.serverPort, other.serverPort)) {
            return false;
        }
        if (!Objects.equals(this.url, other.url)) {
            return false;
        }
        return this.sourceType == other.sourceType;
    }
    
    
    /**
     * Build and provide class instance
     * @return 
     
    public static DataSourceProperty build(){
         property = new DataSourceProperty();
         return property;
    }
    
    public static void setUserName(String userame){
       property.setUsername(userame);
    }
    */
}
