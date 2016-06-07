/**
 * Raptor Reporting service
 * A simple reporing service that enable users to design and generate web-based reports.
 * Built on top of the JasperReports - an open source reporting library
 * 2016 © ATT Service Assurance  - Raptor POC team
 *
 */
package com.att.raptor.report.engine.support.generators;

import java.util.List;
import net.sf.jasperreports.engine.JasperPrint;

/**
 * JasperGenerator abstract implementation of the IGenerator interface
 *
 * @author ebrimatunkara
 * @param <T>
 */
public abstract class JasperGenerator<T> implements IGenerator<T> {
    private List<JasperPrint> jsPrint;

    public JasperGenerator(List<JasperPrint> jsPrint) {
        this.jsPrint = jsPrint;
    }

    public List<JasperPrint> getJsPrint() {
        return jsPrint;
    }

    public void setJsPrint(List<JasperPrint> jsPrint) {
        this.jsPrint = jsPrint;
    }

}
