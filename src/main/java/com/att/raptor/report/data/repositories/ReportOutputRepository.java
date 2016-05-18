/**
 * Raptor Reporting service
 * A simple reporting service that enable users to design and generate web-based reports.
 * Built on top of the JasperReports - an open source reporting library
 * 2016 © ATT Service Assurance  - Raptor POC team
 *
 */
package com.att.raptor.report.data.repositories;

import com.att.raptor.report.data.domain.ReportOutput;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * ReportOutputRepository
 * @author ebrimatunkara
 */
public interface ReportOutputRepository extends MongoRepository<ReportOutput,String>,
                                                  DataRepositoryCustom<ReportOutput,String>{
    
}
