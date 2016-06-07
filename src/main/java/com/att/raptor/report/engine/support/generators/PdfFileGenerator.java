/**
 * Raptor Reporting service
 * A simple reporing service that enable users to design and generate web-based reports.
 * Built on top of the JasperReports - an open source reporting library
 * 2016 © ATT Service Assurance  - Raptor POC team
 *
 */
package com.att.raptor.report.engine.support.generators;

import com.att.raptor.report.data.domain.ReportOutputFile;
import com.att.raptor.report.engine.support.ReportFormat;
import com.att.raptor.report.engine.support.generators.XlsxGenerator;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;
import net.sf.jasperreports.export.SimplePdfReportConfiguration;
import org.apache.commons.lang.RandomStringUtils;

/**
 * Pdf File Generator -- implementation
 * @author ebrimatunkara
 */
public class PdfFileGenerator extends JasperGenerator<ReportOutputFile> {
    private final String directory;
    public PdfFileGenerator(List<JasperPrint> jsPrint, String directory) {
        super(jsPrint);
        this.directory = directory;
    }

    @Override
    public ReportOutputFile generate() {
        try {       
            JRPdfExporter exporter = new JRPdfExporter();
            String filename = RandomStringUtils.randomAlphabetic(12);
            File file = File.createTempFile(filename,".html",new File(directory));
            exporter.setExporterInput(SimpleExporterInput.getInstance(this.getJsPrint()));
            exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(file));
            SimplePdfReportConfiguration configuration = new SimplePdfReportConfiguration();
            configuration.setSizePageToContent(true);
            exporter.setConfiguration(configuration);
            exporter.exportReport();       
            return new ReportOutputFile(file.getAbsolutePath(),ReportFormat.PDF);
        } catch (JRException ex) {
            Logger.getLogger(XlsxGenerator.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(PdfFileGenerator.class.getName()).log(Level.SEVERE, null, ex);
        }
        throw new GeneratorException("Pdf Report File Generator exception"); 
    }
}