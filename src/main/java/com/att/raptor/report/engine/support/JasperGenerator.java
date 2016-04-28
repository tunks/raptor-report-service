/**
 * Raptor Reporting service
 * A simple reporing service that enable users to design and generate web-based reports.
 * Built on top of the JasperReports - an open source reporting library
 * 2016 © ATT Service Assurance  - Raptor POC team
 *
 */
package com.att.raptor.report.engine.support;

import net.sf.jasperreports.engine.JasperPrint;

/**
 * JasperGenerator abstract implementation of the IGenerator interface
 * @author ebrimatunkara
 * @param <T>
 */
public abstract class JasperGenerator<T> implements IGenerator{
     private JasperPrint jsPrint;
     
     public JasperGenerator(JasperPrint jsPrint){
         this.jsPrint = jsPrint;              
     }

     public JasperPrint getJsPrint() {
        return jsPrint;
     }

    public void setJsPrint(JasperPrint jsPrint) {
        this.jsPrint = jsPrint;
    }
}
