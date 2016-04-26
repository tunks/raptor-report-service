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
import java.util.List;
import javax.annotation.Resource;

/**
 * ReportFolderService -- implementation of the report service
 * @author ebrimatunkara
 */
public class ReportFolderService implements CrudBaseService<ReportFolder,String>{
    @Resource
    private ReportFolderRepository  reportFolderRepository;

    @Override
    public List<ReportFolder> findAll() {
        return reportFolderRepository.findAll();
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
}
