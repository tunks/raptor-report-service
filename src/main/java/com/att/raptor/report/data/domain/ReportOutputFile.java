/**
 * Raptor Reporting service
 * A simple reporting service that enable users to design and generate web-based reports.
 * Built on top of the JasperReports - an open source reporting library
 * 2016 © ATT Service Assurance  - Raptor POC team
 *
 */
package com.att.raptor.report.data.domain;

import com.att.raptor.report.engine.support.ReportFormat;
import java.util.Objects;

/**
 *  Report Document File 
 * @author ebrimatunkara
 */
public class ReportOutputFile {
    private String path;
    private ReportFormat format;

    public ReportOutputFile() {
    }

    public ReportOutputFile(String path, ReportFormat format) {
        this.path = path;
        this.format = format;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + Objects.hashCode(this.path);
        hash = 97 * hash + Objects.hashCode(this.format);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final ReportOutputFile other = (ReportOutputFile) obj;
        if (!Objects.equals(this.path, other.path)) {
            return false;
        }
        if (this.format != other.format) {
            return false;
        }
        return true;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public ReportFormat getFormat() {
        return format;
    }

    public void setFormat(ReportFormat format) {
        this.format = format;
    }
    
}
