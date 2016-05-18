/**
 * Raptor Reporting service
 * A simple reporting service that enable users to design and generate web-based reports.
 * Built on top of the JasperReports - an open source reporting library
 * 2016 © ATT Service Assurance  - Raptor POC team
 *
 */
package com.att.raptor.report.data.listeners;

import com.att.raptor.report.data.domain.ReportOutput;
import com.att.raptor.report.engine.support.IMessage;
import com.att.raptor.report.engine.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.mapping.event.AbstractMongoEventListener;
import org.springframework.data.mongodb.core.mapping.event.AfterSaveEvent;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Component;


/**
 *
 * @author ebrimatunkara
 */
@Component
public class ReportDocumentAfterSaveLister extends AbstractMongoEventListener<ReportOutput>{
    @Autowired
    private NotificationService mailService;
    
    @Autowired
    IMessage<ReportOutput, SimpleMailMessage> reportMailMesage;
     
    @Override
    public void onAfterSave(AfterSaveEvent<ReportOutput> event) {
        super.onAfterSave(event); 
        //send message
        SimpleMailMessage message = reportMailMesage.createMessage(event.getSource());
        mailService.send(message);
    }
    
}
