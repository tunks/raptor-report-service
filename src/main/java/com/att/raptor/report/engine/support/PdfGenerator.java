/**
 * Raptor Reporting service
 * A simple reporing service that enable users to design and generate web-based reports.
 * Built on top of the JasperReports - an open source reporting library
 * 2016 © ATT Service Assurance  - Raptor POC team
 *
 */
package com.att.raptor.report.engine.support;

import java.io.ByteArrayOutputStream;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;
import net.sf.jasperreports.export.SimplePdfReportConfiguration;

/**
 * Pdf Generator -- implementation
 * @author ebrimatunkara
 */
public class PdfGenerator extends JasperGenerator {

    public PdfGenerator(List<JasperPrint> jsPrint) {
        super(jsPrint);
    }

    @Override
    public Object generate() {
        try {
            
            JRPdfExporter exporter = new JRPdfExporter();
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            exporter.setExporterInput(SimpleExporterInput.getInstance(this.getJsPrint()));
            exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(outputStream));
            SimplePdfReportConfiguration configuration = new SimplePdfReportConfiguration();
            configuration.setSizePageToContent(true);
            exporter.setConfiguration(configuration);
            exporter.exportReport();
            return outputStream;
        } catch (JRException ex) {
            Logger.getLogger(XlsxGenerator.class.getName()).log(Level.SEVERE, null, ex);
        }
        throw new GeneratorException("Pdf Report Generator exception"); 
    }
}