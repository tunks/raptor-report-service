/**
 * Raptor Reporting service
 * A simple reporing service that enable users to design and generate web-based reports.
 * Built on top of the JasperReports - an open source reporting library
 * 2016 © ATT Service Assurance  - Raptor POC team
 *
 */
package com.att.raptor.report.engine.support;

/**
 *
 * @author ebrimatunkara
 */
public enum ReportFormat {
    XLSX("xlsx"),
    PDF("pdf"),
    HTML("html");

    private String format;

    ReportFormat(String format) {
        this.format = format;
    }

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    public static ReportFormat formatType(String format) {
        //return xlsx if format type is xls
        if (format.equals("xls")) {
            return ReportFormat.XLSX;
        }

        //ReportFormat ReportFormat.values();
        for (ReportFormat f : ReportFormat.values()) {
            if (f.getFormat().equals(format)) {
                return f;
            }
        }
        return null;
    }
}
