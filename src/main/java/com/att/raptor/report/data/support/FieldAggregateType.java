/**
 * Raptor Reporting service
 * A simple reporting service that enable users to design and generate web-based reports.
 * Built on top of the JasperReports -- an open source reporting library
 * 2016 © ATT Service Assurance  -- Raptor POC team
 */
package com.att.raptor.report.data.support;

/**
 * DataFieldType Aggregate
 *
 * @author ebrimatunkara
 */
public enum FieldAggregateType {
    SUM("sum"),
    AVG("avg"),
    COUNT("count");
    
    private final String name;

    FieldAggregateType(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return name;
    }
}
