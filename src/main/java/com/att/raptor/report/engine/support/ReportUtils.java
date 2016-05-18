/**
 * Raptor Reporting service
 * A simple reporing service that enable users to design and generate web-based reports.
 * Built on top of the JasperReports - an open source reporting library
 * 2016 © ATT Service Assurance  - Raptor POC team
 *
 */
package com.att.raptor.report.engine.support;

import com.att.raptor.report.data.domain.ReportOutput;
import com.att.raptor.report.data.domain.ReportOutputFile;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import net.sf.jasperreports.engine.JasperPrint;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

/**
 * Http controller static helper functions
 *
 * @author ebrimatunkara
 */
public class ReportUtils {

    public static MediaType requestMediaType(ReportFormat format) {
        switch (format) {
            case PDF:
                return MediaType.parseMediaType("application/pdf");
            case XLSX:
                return MediaType.APPLICATION_OCTET_STREAM;
            default:
                return MediaType.TEXT_HTML;
        }
    }

    public static void addHeaderOptions(HttpHeaders httpHeader, String templateId, ReportFormat format) {
        switch (format) {
            case PDF:
                httpHeader.setContentDispositionFormData("attachment", templateId + ".pdf");
                break;
            case XLSX:
                httpHeader.setContentDispositionFormData("attachment", templateId + ".xlsx");
        }

    }

    public static  ByteArrayOutputStream   generateReport(JasperPrint jsPrinter, ReportFormat format, String templateId) {
        IGenerator<ByteArrayOutputStream> generator = JasperReportFactory.createNewGenerator(jsPrinter, format);
        ByteArrayOutputStream out = generator.generate();
        return out;
    }
    
    public static ReportOutputFile generateReportFile(JasperPrint jsPrinter, ReportFormat format, String templateId,String directory) throws FileNotFoundException, IOException {
        ByteArrayOutputStream out =  generateReport(jsPrinter,format,templateId) ;
        String filename = templateId;
        String fileType = format.getFormat().toLowerCase();
        File file = File.createTempFile(filename,".".concat(fileType),new File(directory));
        String filePath = file.getAbsolutePath();
        try (FileOutputStream fileOuputStream = new FileOutputStream(file)) {
            fileOuputStream.write(out.toByteArray());
        }
        return new ReportOutputFile(filePath, format);
    }
    
     public static ReportOutputFile generateReportFile(JasperPrint jsPrinter, ReportFormat format, String templateId) throws FileNotFoundException, IOException {
        return  generateReportFile(jsPrinter,format,templateId,null);
    }
}
