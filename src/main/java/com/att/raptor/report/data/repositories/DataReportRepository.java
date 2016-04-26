/**
 * Raptor Reporting service
 * A simple reporing service that enable users to design and generate web-based reports.
 * Built on top of the JasperReports - an open source reporting library 
 * 2016 © ATT Service Assurance  - Raptor POC team 
 *
 */
package com.att.raptor.report.data.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.att.raptor.report.data.domain.DataReport;

/**
 * @author ebrimatunkara
 * DataReport Report
 */
public interface DataReportRepository extends MongoRepository<DataReport,String>,DataRepositoryCustom<DataReport,String>{
        
}

