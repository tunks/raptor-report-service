    /**
     * Raptor Reporting service
     * A simple reporing service that enable users to design and generate web-based reports.
     * Built on top of the JasperReports - an open source reporting library
     * 2016 © ATT Service Assurance  - Raptor POC team
     *
     */
    package com.att.raptor.report.data.repositories;

    import com.att.raptor.report.data.domain.ReportComponent;
    import com.att.raptor.report.data.support.ReportComponentType;
    import java.util.List;
    import org.springframework.data.mongodb.repository.MongoRepository;

    /**
     *
     * @author ebrimatunkara
     */
    public interface ReportComponentRepository extends MongoRepository<ReportComponent,String>, 
            DataRepositoryCustom<ReportComponent, String>{   
         /**
         * Find ReportComponents by templateId
         * @param id
         * @return 
         */
          public List<ReportComponent> findByTemplateId(String id);
         /**
          * Find ReportComponents by templateId and report component type
          * @param templateId
          * @param type
          * @return 
          */
          public List<ReportComponent> findByTemplateIdAndReportComponentType( String templateId, ReportComponentType type);
    }
