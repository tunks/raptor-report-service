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
 *
 * @author ebrimatunkara
 */
public class JasperReportFactory implements ReportBaseFactory<JasperPrint, JasperGenerator>{

    @Override
    public JasperGenerator createGenerator(String type) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public JasperGenerator createGenerator(JasperPrint object, String type) {
         switch(type){
             case "xls":
             case "xlsx":
                  return new XlsxGenerator(object);
             case "pdf":
                  return new PdfGenerator(object);
             default:
                  return new HtmlGenerator(object);
         }
         
    }
    
    public static JasperReportFactory CreateFactory(){
         return new JasperReportFactory();
    }

}
