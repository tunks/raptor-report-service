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
public class JasperReportFactory implements ReportBaseFactory<JasperPrint, JasperGenerator> {

    @Override
    public JasperGenerator createGenerator(ReportFormat format) {
      return createGenerator(null,format);
    }

    @Override
    public JasperGenerator createGenerator(JasperPrint object, ReportFormat format) {
        switch (format) {
            case XLSX:
                return new XlsxGenerator(object);
            case PDF:
                return new PdfGenerator(object);
            default:
                return new HtmlGenerator(object);
        }

    }
    
   public static JasperGenerator createNewGenerator(JasperPrint object, ReportFormat format) {
           return CreateFactory().createGenerator(object, format);
    }

    public static JasperReportFactory CreateFactory() {
        return new JasperReportFactory();
    }
    
}
