/**
 * Raptor Reporting service
 * A simple reporing service that enable users to design and generate web-based reports.
 * Built on top of the JasperReports - an open source reporting library
 * 2016 © ATT Service Assurance  - Raptor POC team
 *
 */
package com.att.raptor.report.engine.support;

import java.io.ByteArrayOutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.export.JRXlsExporter;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;
import net.sf.jasperreports.export.SimpleXlsxReportConfiguration;

/**
 * XLSX Generator -- implementation
 * @author ebrimatunkara
 */
public class XlsxGenerator extends JasperGenerator<ByteArrayOutputStream> {

    public XlsxGenerator(JasperPrint jsPrint) {
        super(jsPrint);
    }

    @Override
    public ByteArrayOutputStream generate() {
        try {
            JRXlsExporter exporter = new JRXlsExporter();
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            exporter.setExporterInput(new SimpleExporterInput(this.getJsPrint()));
            exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(outputStream));
            SimpleXlsxReportConfiguration configuration = new SimpleXlsxReportConfiguration();
            configuration.setDetectCellType(true);
            configuration.setCollapseRowSpan(false);
            exporter.setConfiguration(configuration);
            exporter.exportReport();
            return outputStream;
        } catch (JRException ex) {
            Logger.getLogger(XlsxGenerator.class.getName()).log(Level.SEVERE, null, ex);
        }
        throw new GeneratorException("Xlsx Report Generator exception"); 
    }
}
