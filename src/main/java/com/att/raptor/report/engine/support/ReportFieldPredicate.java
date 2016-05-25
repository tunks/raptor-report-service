/**
 * Raptor Reporting service
 * A simple reporting service that enable users to design and generate web-based reports.
 * Built on top of the JasperReports - an open source reporting library
 * 2016 © ATT Service Assurance  - Raptor POC team
 *
 */
package com.att.raptor.report.engine.support;

import com.att.raptor.report.data.domain.ReportField;

/**
 * ReportFieldPredicate field concrete implementation
 * @author ebrimatunkara
 */
public class ReportFieldPredicate implements FilterPredicate<ReportField>{
    //filter report fields that are invisible
    @Override
    public boolean filter(ReportField object) {
          return object.isVisible();
    }  
}
