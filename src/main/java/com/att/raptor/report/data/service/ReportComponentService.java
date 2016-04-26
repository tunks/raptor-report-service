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

/**
 * Report Component Service
 * @author ebrimatunkara
 */
public class ReportComponentService implements CrudBaseService<ReportComponent,String> {
    @Resource
    ReportComponentRepository reportComponentRepository;
    
    @Override
    public List<ReportComponent> findAll() {
        return reportComponentRepository.findAll();
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
    
}
