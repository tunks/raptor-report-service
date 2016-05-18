/**
 * Raptor Reporting service
 * A simple reporing service that enable users to design and generate web-based reports.
 * Built on top of the JasperReports - an open source reporting library
 * 2016 © ATT Service Assurance  - Raptor POC team
 *
 */
package com.att.raptor.report.data.service;

import com.att.raptor.report.data.domain.ReportFolder;
import com.att.raptor.report.data.repositories.ReportFolderRepository;
import java.util.Collection;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

/**
 * ReportFolderService -- implementation of the report service
 * @author ebrimatunkara
 */
@Service("reportFolderService")
public class ReportFolderService implements CrudBaseService<ReportFolder,String>{
    @Resource
    private ReportFolderRepository  reportFolderRepository;

    @Override
    public List<ReportFolder> findAll() {
        return reportFolderRepository.findAll();
    }

    public Page<ReportFolder> findAll(Pageable page) {
        return reportFolderRepository.findAll(page);
    }
    
    @Override
    public ReportFolder find(String id) {
           return reportFolderRepository.findOne(id);
    }

    @Override
    public ReportFolder create(ReportFolder object) {
          return reportFolderRepository.save(object);
    }

    @Override
    public ReportFolder update(ReportFolder object) {
       return reportFolderRepository.update(object);
    }

    public List< ReportFolder> findAllRoots() {
        return reportFolderRepository.findByParentIdNull();
    }
    
    public Page< ReportFolder> findAllRoots(Pageable page) {
         return reportFolderRepository.findByParentIdNull(page);
    }

    @Override
    public void delete(String id) {
        reportFolderRepository.delete(id);
    }

    @Override
    public List<ReportFolder> create(Collection<ReportFolder> t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
