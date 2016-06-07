/**
 * Raptor Reporting service
 * A simple reporing service that enable users to design and generate web-based reports.
 * Built on top of the JasperReports - an open source reporting library
 * 2016 © ATT Service Assurance  - Raptor POC team
 *
 */
package com.att.raptor.report.engine.support.generators;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.export.HtmlExporter;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleHtmlExporterOutput;

/**
 * Customer jasper  - HTMLGenerator implementation 
 * @author ebrimatunkara
 */
public class HtmlGenerator extends JasperGenerator<ByteArrayOutputStream>{
    public HtmlGenerator(List<JasperPrint> jsPrint) {
        super(jsPrint);
    }

    @Override
    public ByteArrayOutputStream generate() {
        try {
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            //FileInputStream in = new FileInputStream(file);
            HtmlExporter exporter = new HtmlExporter();
            exporter.setExporterInput(SimpleExporterInput.getInstance(this.getJsPrint()));
            exporter.setExporterOutput(new SimpleHtmlExporterOutput(outputStream));
            exporter.exportReport();
            return outputStream;
        } catch (JRException ex) {
            Logger.getLogger(HtmlGenerator.class.getName()).log(Level.SEVERE, null, ex);
        }
         throw new GeneratorException("HTML Report Generator exception"); 
    }
    
}
