/**
 * Raptor Reporting service
 * A simple reporing service that enable users to design and generate web-based reports.
 * Built on top of the JasperReports - an open source reporting library
 * 2016 © ATT Service Assurance  - Raptor POC team
 *
 */
package com.att.raptor.report.data.service;

import com.att.raptor.report.data.domain.ReportTemplate;
import com.att.raptor.report.data.repositories.ReportTemplateRepository;
import java.util.List;
import javax.annotation.Resource;

/**
 * Report Template service
 * @author ebrimatunkara
 */
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
    
}
