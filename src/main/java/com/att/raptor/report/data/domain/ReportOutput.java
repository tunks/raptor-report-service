/**
 * Raptor Reporting service
 * A simple reporing service that enable users to design and generate web-based reports.
 * Built on top of the JasperReports - an open source reporting library
 * 2016 © ATT Service Assurance  - Raptor POC team
 *
 */
package com.att.raptor.report.data.domain;

import com.att.raptor.report.engine.support.ReportFormat;
import java.util.Set;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * ReportOutput document mode -- stores the meta information of generated report out and f
 * @author ebrimatunkara
 */
@Document
public class ReportOutput extends Audit{
    @Id
    private String id;
    private String templateId;
    private Set<ReportOutputFile> files;
    private String name;

    public ReportOutput() {}

    public ReportOutput(String templateId) {
        this.templateId = templateId;
    }
    
    public ReportOutput(String templateId, Set<ReportOutputFile> files) {
        this.templateId = templateId;
        this.files = files;
    }

    public ReportOutput(String templateId, Set<ReportOutputFile> files, String name) {
        this(templateId, files);
        this.name = name;
    }
   
    
    @Override
    public String getId() {
      return id;
    }
    
    public void setId(String id){
       this.id = id;
    }

    public String getTemplateId() {
        return templateId;
    }

    public void setTemplateId(String templateId) {
        this.templateId = templateId;
    } 

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<ReportOutputFile> getFiles() {
        return files;
    }

    public void setFiles(Set<ReportOutputFile> files) {
        this.files = files;
    }
    
    public void addFile(ReportOutputFile file){
        this.files.add(file);
    }

    public String getFilePath(ReportFormat format){
           for(ReportOutputFile file: files){
              if(file.getFormat().equals(format)){
                 return file.getPath();
              }
           }
          return null;
    }
}
