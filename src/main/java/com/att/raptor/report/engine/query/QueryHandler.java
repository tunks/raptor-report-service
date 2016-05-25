/**
 * Raptor Reporting service
 * A simple reporing service that enable users to design and generate web-based reports.
 * Built on top of the JasperReports - an open source reporting library
 * 2016 © ATT Service Assurance  - Raptor POC team
 *
 */
package com.att.raptor.report.engine.query;

import com.att.raptor.report.data.domain.ReportTemplate;
import java.util.Set;

/**
 * QueryHandler interface
 *
 * @author ebrimatunkara
 * @param <T>
 * @param <R>
 */
public abstract class QueryHandler<T, R> {

    ReportTemplate template;

    public QueryHandler() {
    }

    public QueryHandler(ReportTemplate template) {
        this.template = template;
    }

    public ReportTemplate getTemplate() {
        return template;
    }

    public void setTemplate(ReportTemplate template) {
        this.template = template;
    }

    public abstract R parseQuery();

    public abstract T getQuery();

    public abstract String getQueryString();

    public abstract Set getFieldSet();
}
