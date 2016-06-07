/**
 * Raptor Reporting service
 * A simple reporting service that enable users to design and generate web-based reports.
 * Built on top of the JasperReports - an open source reporting library
 * 2016 © ATT Service Assurance  - Raptor POC team
 *
 */
package com.att.raptor.report.engine.support.generators;

import com.att.raptor.report.data.domain.ReportOutputFile;
import com.att.raptor.report.engine.support.ReportFormat;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.export.JRXlsExporter;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;
import net.sf.jasperreports.export.SimpleXlsxReportConfiguration;
import org.apache.commons.lang.RandomStringUtils;

/**
 * XLSX File Generator -- implementation
 * @author ebrimatunkara
 */
public class XlsxFileGenerator extends JasperGenerator<ReportOutputFile> {
    private final String directory;
    public XlsxFileGenerator(List<JasperPrint> jsPrint, String directory) {
        super(jsPrint);
        this.directory = directory;
    }

    @Override
    public ReportOutputFile generate() {
        try {
            JRXlsExporter exporter = new JRXlsExporter();
            String filename = RandomStringUtils.randomAlphabetic(12);
            File file = File.createTempFile(filename,".html",new File(directory));
            exporter.setExporterInput(SimpleExporterInput.getInstance(this.getJsPrint()));
            exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(file));
            SimpleXlsxReportConfiguration configuration = new SimpleXlsxReportConfiguration();
            configuration.setDetectCellType(true);
            configuration.setCollapseRowSpan(false);
            exporter.setConfiguration(configuration);
            exporter.exportReport();
            return new ReportOutputFile(file.getAbsolutePath(),ReportFormat.XLSX);
        } catch (JRException ex) {
            Logger.getLogger(XlsxGenerator.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(XlsxFileGenerator.class.getName()).log(Level.SEVERE, null, ex);
        }
        throw new GeneratorException("Xlsx Report Generator exception"); 
    }
}
