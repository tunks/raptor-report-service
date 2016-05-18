/**
 * Raptor Reporting service
 * A simple reporing service that enable users to design and generate web-based reports.
 * Built on top of the JasperReports - an open source reporting library
 * 2016 © ATT Service Assurance  - Raptor POC team
 */
package com.att.raptor.report.data.service;

import com.att.raptor.report.data.domain.ReportTemplate;
import com.att.raptor.report.data.repositories.ReportTemplateRepository;
import java.util.Collection;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;

/**
 * Report Template service
 * @author ebrimatunkara
 */
@Service("reportTemplateService")
public class ReportTemplateService implements CrudBaseService<ReportTemplate,String>{
    @Resource
    ReportTemplateRepository reportTemplateRepository;
    
    @Override
    public List<ReportTemplate> findAll() {
          return reportTemplateRepository.findAll();
    }

    @Override
    public ReportTemplate find(String id) {
          return reportTemplateRepository.findOne(id);
    }

    @Override
    public ReportTemplate create(ReportTemplate template) {
          return reportTemplateRepository.save(template);
    }

    @Override
    public ReportTemplate update(ReportTemplate template) {
          return reportTemplateRepository.update(template);
    }

    @Override
    public void delete(String id) {
        reportTemplateRepository.delete(id);
    }

    @Override
    public List<ReportTemplate> create(Collection<ReportTemplate> t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
