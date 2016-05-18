/**
 * Raptor Reporting service
 * A simple reporing service that enable users to design and generate web-based reports.
 * Built on top of the JasperReports - an open source reporting library
 * 2016 © ATT Service Assurance  - Raptor POC team
 *
 */
package com.att.raptor.report.data.service;

import com.att.raptor.report.data.domain.ReportOutput;
import java.util.Collection;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import com.att.raptor.report.data.repositories.ReportOutputRepository;

/**
 * ReportOutputService
 * @author ebrimatunkara
 */
@Service("reportOutputService")
public class ReportOutputService implements CrudBaseService<ReportOutput,String>{
    @Resource
    private ReportOutputRepository reportOutputRepository;
    
    @Override
    public List<ReportOutput> findAll() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ReportOutput find(String id) {
        return reportOutputRepository.findOne(id);
    }

    @Override
    public ReportOutput create(ReportOutput document) {
        return reportOutputRepository.save(document);
    }

    @Override
    public ReportOutput update(ReportOutput t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void delete(String id) {
          reportOutputRepository.delete(id);
    }

    @Override
    public List<ReportOutput> create(Collection<ReportOutput> documents) {
          return reportOutputRepository.save(documents);
     }
    
}
