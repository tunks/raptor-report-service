/**
 * Raptor Reporting service
 * A simple reporing service that enable users to design and generate web-based reports.
 * Built on top of the JasperReports - an open source reporting library
 * 2016 © ATT Service Assurance  - Raptor POC team
 *
 */
package com.att.raptor.report.data.service;

import com.att.raptor.report.data.domain.ReportComponent;
import java.util.List;
import javax.annotation.Resource;
import com.att.raptor.report.data.repositories.ReportComponentRepository;
import com.att.raptor.report.data.support.ReportComponentType;
import java.util.Collection;
import org.springframework.stereotype.Service;

/**
 * Report Component Service
 * @author ebrimatunkara
 */
@Service("reportComponentService")
public class ReportComponentService implements CrudBaseService<ReportComponent,String> {
    @Resource
    ReportComponentRepository reportComponentRepository;
    
    @Override
    public List<ReportComponent> findAll() {
        return reportComponentRepository.findAll();
    }
    
    /***
     * find by template id
     * @param templateId
     * @return  
     */
    public List<ReportComponent> findByTemplateId(String templateId){
       return reportComponentRepository.findByTemplateId(templateId);
    }
    
    /**
     * find by template id and component type
     * @param templateId
     * @param type
     * @return 
     **/
    public List<ReportComponent> findByTemplateIdAnd(String templateId, ReportComponentType type){
      return reportComponentRepository.findByTemplateIdAndReportComponentType(templateId, type);
    }
    
    @Override
    public ReportComponent find(String id) {
        return reportComponentRepository.findOne(id);
    }

    @Override
    public ReportComponent create(ReportComponent component) {
         return reportComponentRepository.save(component);
    }

    @Override
    public ReportComponent update(ReportComponent component) {
       return reportComponentRepository.update(component);
    }

    @Override
    public void delete(String id) {
        reportComponentRepository.delete(id);
    }

    @Override
    public List<ReportComponent> create(Collection<ReportComponent> t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
