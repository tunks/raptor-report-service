/**
 * Raptor Reporting service
 * A simple reporing service that enable users to design and generate web-based reports.
 * Built on top of the JasperReports - an open source reporting library 
 * 2016 © ATT Service Assurance  - Raptor POC team 
 *
 */
package com.att.raptor.report.data.domain;

import java.util.Set;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Report DataProvider class that consists of the data sources, components for a data
 * @author ebrimatunkara
 */
@Document
public class DataProvider extends Audit{
   @Id
   private String id;

   @DBRef
   Set<DataSource> sources;

   /**
    * TODO data components
    * fields
    * functions
    **/
   public DataProvider() {
   }
   
   @Override
   public String getId() {
      return id;
   }
}

