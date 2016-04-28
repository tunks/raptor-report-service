/**
 * Raptor Reporting service
 * A simple reporing service that enable users to design and generate web-based reports.
 * Built on top of the JasperReports - an open source reporting library
 * 2016 © ATT Service Assurance  - Raptor POC team
 *
 */
package com.att.raptor.report.engine.support;

import ar.com.fdvs.dj.domain.DynamicReport;
import ar.com.fdvs.dj.domain.builders.ColumnBuilder;
import ar.com.fdvs.dj.domain.builders.FastReportBuilder;
import ar.com.fdvs.dj.domain.entities.columns.AbstractColumn;
import com.att.raptor.report.data.domain.DataField;
import com.att.raptor.report.data.domain.ReportComponent;
import com.att.raptor.report.data.domain.ReportField;
import com.att.raptor.report.data.domain.ReportTemplate;
import com.att.raptor.report.data.support.DataFieldType;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

/**
 * @author ebrimatunkara
 */
public class JasperReportBuilder {
    FastReportBuilder builder;
     
    public JasperReportBuilder() {
          builder = new FastReportBuilder();
    }

    public DynamicReport prepare(ReportTemplate template){
           DynamicReport  report  = builder.setReportName(template.getName())
                                           //.setIgnorePagination(true)
                                           .setMargins(10, 10, 10, 10)
                                           .setUseFullPageWidth(true)
                                           .setSubtitle("This report was generated at " + new Date())
                                           .setIgnorePagination(false)
                                           .build();        
           ReportColumns(report, template.getComponents());
           return report;
    }
    
    public void ReportColumns(DynamicReport report, Set<ReportComponent> components){
          if(components == null){
             return ;
          }
           
          List<AbstractColumn> columns;
          for(ReportComponent component : components){
              columns = createColumns(component.getFieldComponents());
              report.setColumns(columns);
          }
    }
    
    protected List<AbstractColumn> createColumns(Set<ReportField> fields){
          List<AbstractColumn> columns = new ArrayList();
          for(ReportField field : fields){
               columns.add(createColumn(field));               
          }
          return columns;
    }
    
   private AbstractColumn createColumn(ReportField field){
           String name = field.getName();
           DataFieldType dfieldType= field.getFieldType();
           String fType;
           if(dfieldType == null){
              fType = String.class.getName();
           }
           else{
              fType = dfieldType.getType();
           }
           ColumnBuilder cBuilder =  ColumnBuilder.getNew();
           cBuilder.setColumnProperty(field.getName(), fType);
           cBuilder.setTitle(columnTitle(name));
           return cBuilder.build();
   }
   
   private String columnTitle(String fieldName){
         return fieldName;
   }
    
}
