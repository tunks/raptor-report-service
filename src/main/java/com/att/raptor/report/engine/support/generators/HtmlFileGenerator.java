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
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.export.HtmlExporter;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleHtmlExporterOutput;
import org.apache.commons.lang.RandomStringUtils;
import org.springframework.util.StopWatch;

/**
 * Customer jasper  - HTML File Generator implementation 
 * @author ebrimatunkara
 */
public class HtmlFileGenerator extends JasperGenerator<ReportOutputFile>{
    private final String directory;
    public HtmlFileGenerator(List<JasperPrint> jsPrint, String directory) {
        super(jsPrint);
        this.directory = directory; 
    }

    @Override
    public ReportOutputFile generate() {
        try {
            StopWatch w = new StopWatch();
            w.start();
            String filename = RandomStringUtils.randomAlphabetic(12);
            File file = File.createTempFile(filename,".html",new File(directory));
            HtmlExporter exporter = new HtmlExporter();
            exporter.setExporterInput(SimpleExporterInput.getInstance(this.getJsPrint()));
            exporter.setExporterOutput(new SimpleHtmlExporterOutput(file));
            exporter.exportReport();
            w.stop();
            System.out.println("HTML file export "+w.getLastTaskTimeMillis());
            return new  ReportOutputFile(file.getAbsolutePath(), ReportFormat.HTML);
        } catch (JRException ex) {
            Logger.getLogger(HtmlGenerator.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(HtmlFileGenerator.class.getName()).log(Level.SEVERE, null, ex);
        }
         throw new GeneratorException("HTML File Generator exception"); 
    }
    
}
