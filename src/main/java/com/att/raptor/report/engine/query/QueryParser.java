/**
 * Raptor Reporting service
 * A simple reporing service that enable users to design and generate web-based reports.
 * Built on top of the JasperReports - an open source reporting library
 * 2016 © ATT Service Assurance  - Raptor POC team
 *
 */
package com.att.raptor.report.engine.query;

/**
 * QueryParser abstract class
 *
 * @author ebrimatunkara
 * @param <T>
 */
public abstract class QueryParser<T> {
    private QueryParser nextParser;

    public QueryParser() {
    }
    
    public QueryParser(QueryParser nextParser) {
        this.nextParser = nextParser;
    }

    public QueryParser getNextParser() {
        return nextParser;
    }

    public void setNextParser(QueryParser nextParser) {
        this.nextParser = nextParser;
    }

    public abstract String parse(T object);
}
