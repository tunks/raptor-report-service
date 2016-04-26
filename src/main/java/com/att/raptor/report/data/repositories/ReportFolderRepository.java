/**
 * Raptor Reporting service
 * A simple reporing service that enable users to design and generate web-based reports.
 * Built on top of the JasperReports - an open source reporting library
 * 2016 © ATT Service Assurance  - Raptor POC team
 *
 */
package com.att.raptor.report.data.repositories;

import com.att.raptor.report.data.domain.ReportFolder;
import java.util.List;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * ReportFolderRepository - data access layer interface
 * @author ebrimatunkara
 */
public interface ReportFolderRepository extends MongoRepository<ReportFolder,String>, 
                                                DataRepositoryCustom<ReportFolder, String> {
    List<ReportFolder> findByParentId(String parentId);
    List<ReportFolder> findByParentIdNotNull();
    List<ReportFolder> findByParentIdNull();
}
