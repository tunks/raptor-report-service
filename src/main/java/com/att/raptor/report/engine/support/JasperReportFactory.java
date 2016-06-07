/**
 * Raptor Reporting service
 * A simple reporing service that enable users to design and generate web-based reports.
 * Built on top of the JasperReports - an open source reporting library
 * 2016 © ATT Service Assurance  - Raptor POC team
 *
 */
package com.att.raptor.report.engine.support;

import com.att.raptor.report.engine.support.generators.JasperGenerator;
import com.att.raptor.report.engine.support.generators.HtmlGenerator;
import com.att.raptor.report.engine.support.generators.PdfGenerator;
import com.att.raptor.report.engine.support.generators.XlsxGenerator;
import com.att.raptor.report.engine.query.JdbcQueryParser;
import com.att.raptor.report.engine.query.QueryParser;
import com.att.raptor.report.engine.support.generators.HtmlFileGenerator;
import com.att.raptor.report.engine.support.generators.PdfFileGenerator;
import com.att.raptor.report.engine.support.generators.XlsxFileGenerator;
import java.util.List;
import net.sf.jasperreports.engine.JasperPrint;

/**
 *
 * @author ebrimatunkara
 */
public class JasperReportFactory implements ReportBaseFactory<List<JasperPrint>, JasperGenerator> {
    @Override
    public JasperGenerator createGenerator(ReportFormat format) {
      return createGenerator(null,format);
    }

    @Override
    public JasperGenerator createGenerator(List<JasperPrint> jsPrints, ReportFormat format) {
        switch (format) {
            case XLSX:
                return new XlsxGenerator(jsPrints);
            case PDF:
                return new PdfGenerator(jsPrints);
            default:
                return new HtmlGenerator(jsPrints);
        }

    }

    @Override
    public JasperGenerator createGenerator(List<JasperPrint> jsPrints, ReportFormat format, String directory) {
        switch (format) {
            case XLSX:
                return new XlsxFileGenerator(jsPrints,directory);
            case PDF:
                return new PdfFileGenerator(jsPrints,directory);
            default:
                return new HtmlFileGenerator(jsPrints,directory);
        }

    }    
   public static JasperGenerator createNewGenerator(List<JasperPrint> object, ReportFormat format) {
           return CreateFactory().createGenerator(object, format);
    }

    public static JasperGenerator createNewGenerator(List<JasperPrint> object, ReportFormat format,String directory) {
           return CreateFactory().createGenerator(object, format,directory);
    }

    public static JasperReportFactory CreateFactory() {
        return new JasperReportFactory();
    }
    
    /**
     * Create Query Parser
     * @return 
     **/
    public static QueryParser createQueryParser(){
        return new JdbcQueryParser();
    }
    
}
