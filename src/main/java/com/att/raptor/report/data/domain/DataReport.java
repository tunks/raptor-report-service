/**
 * Raptor Reporting service
 * A simple reporing service that enable users to design and generate web-based reports.
 * Built on top of the JasperReports - an open source reporting library
 * 2016 © ATT Service Assurance  - Raptor POC team
 *
 */
package com.att.raptor.report.data.domain;

import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Data report contains meta information about report One-to-One relationship
 * with DataProvider
 *
 * @author ebrimatunkara
 *
 */
@Document
public class DataReport extends Audit {

    private String id;
    /**
     * Data report name
	 *
     */
    private String name;
    /**
     * Data report template
	 *
     */
    @DBRef
    private ReportTemplate template;

    @DBRef
    private DataProvider dataProvider;

    public DataReport() {
    }

    public DataReport(String name) {
        super();
        this.name = name;
    }

    public DataReport(String name, ReportTemplate template) {
        super();
        this.name = name;
        this.template = template;
    }

    @Override
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ReportTemplate getTemplate() {
        return template;
    }

    public void setTemplate(ReportTemplate template) {
        this.template = template;
    }

    public DataProvider getDataProvider() {
        return dataProvider;
    }

    public void setDataProvider(DataProvider dataProvider) {
        this.dataProvider = dataProvider;
    }

}
