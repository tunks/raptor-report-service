/**
 * Raptor Reporting service
 * A simple reporing service that enable users to design and generate web-based reports.
 * Built on top of the JasperReports - an open source reporting library
 * 2016 © ATT Service Assurance  - Raptor POC team
 */
package com.att.raptor.report.data.repositories;

import com.att.raptor.report.data.domain.DataSource;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Report Data Source Repository
 * @author ebrimatunkara
 */
public interface DataSourceRepository extends MongoRepository<DataSource,String>, 
                                              DataRepositoryCustom<DataSource,String> {
    
     /**
      * Get list of data sources by name
      * @param name
      * @return 
      */
     List<DataSource> findByName(String name);
     /**
      * Get list of data sources by name in pages
     * @param name
     * @param page
     * @return 
      **/
     Page<DataSource> findByName(String name, Pageable page);
}
