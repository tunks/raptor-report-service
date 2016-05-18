/**
 * Raptor Reporting service
 * A simple reporting service that enable users to design and generate web-based reports.
 * Built on top of the JasperReports - an open source reporting library
 * 2016 © ATT Service Assurance  - Raptor POC team
 *
 */
package com.att.raptor.report.engine.support;

import com.att.raptor.report.data.domain.ReportOutput;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Component;

/**
 *  Report Custom Message - implementation  of IMessage interface
 *  This class  prepares Report mail message subject, content,send address, recipient addresses
 * @author ebrimatunkara
 */
@Component("reportMailMesage")
public class ReportMailMessage implements IMessage<ReportOutput, SimpleMailMessage>{
    @Value("${report.mail.subject}")
    private String reportSubject;
     
    @Value("${report.mail.content}")
    private String reportContent;
    
    @Value("${spring.mail.username}")
    private String mailFrom;
    
    @Value("{$server.port}")
    private String serverPort;
    
    @Value("${report.schedule.url}")
    private String reportScheduleUrl;
    
    @Autowired
    private String  reportHtmlContent;
    
    private final String pattern = "MM-dd-yyyy HH:mm:ss";
    
//    @Value("classpath:templates/report-content.html")
//    private Resource reportHtml;
      
    @Override
    public SimpleMailMessage createMessage(ReportOutput object) {
       return createMailMessage(object);
    }
    
    //TODO
    private SimpleMailMessage  createMailMessage(ReportOutput object){
            String title = object.getName() ;
            String createdOn = object.getCreatedDate().toString(pattern);
            String subject = title + "-" + object.getCreatedDate().toString();
            
            String content = insertContentFormats(object,title,reportHtmlContent);
            content = formatContent("\\$\\{createdOn\\}",createdOn, content);
            
            SimpleMailMessage message = new SimpleMailMessage();     
            message.setFrom(mailFrom);
            message.setTo("ebrimatunkara@gmail.com");
            message.setSubject(subject);
            message.setText(content);
            return message;
    }
    
    private String insertContentFormats(ReportOutput object, String title, String mailContent){
       String content;
       content = formatContent("\\$\\{title\\}",title, mailContent);
        try {
            content = embedContentFormats(object,content);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(ReportMailMessage.class.getName()).log(Level.SEVERE, null, ex);
        }
      return content;
    }
    private String formatContent(String pattern,String value, String content){
        return content.replaceAll(pattern, value);
    }
    
    private String  embedContentFormats(ReportOutput object,String content) throws FileNotFoundException{
          // EmbeddedContent em = new HtmlEmbeddedContent(object.getFilePath(ReportFormat.HTML));
           //content = em.embeded(content, ""); //"\\$\\{embeded:html\\}"
           //TODO
           content = formatContent("\\$\\{embeded:html\\}","",content);
           
           String link =  reportScheduleUrl +"/"+object.getId();
           content = formatContent("\\$\\{link:html\\}",link,content);
         
           link =  reportScheduleUrl +"/"+object.getId() +"?type=pdf";
           content = formatContent("\\$\\{link:pdf\\}",link,content);
             
           link =  reportScheduleUrl +"/"+object.getId() +"?type=xlsx";
           return  formatContent("\\$\\{link:xlsx\\}",link,content);

   }
 
    private interface EmbeddedContent{
         public String embeded(String content, String pattern) throws FileNotFoundException;
    }
    
    private class HtmlEmbeddedContent implements EmbeddedContent{
        private final String embedHTML ="${embeded:html}";
        private final File file;
        
        public HtmlEmbeddedContent(String filePath) {
            this.file = new File(filePath);
        }
  
        @Override
        public String embeded(String content, String pattern) throws FileNotFoundException {
            if(content.contains(embedHTML)){
                try {
                    StringBuilder sbuilder = new StringBuilder();
                    try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                        String line;
                        while((line = reader.readLine()) != null ){
                            sbuilder.append(line);
                        }
                    }
                    return content.replaceFirst(pattern, sbuilder.toString());
                   } catch (IOException ex) {
                    Logger.getLogger(ReportMailMessage.class.getName()).log(Level.SEVERE, null, ex); 
                }
            }
            return content;
        }
    }
}
