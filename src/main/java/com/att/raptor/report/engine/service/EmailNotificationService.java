/**
 * Raptor Reporting service
 * A simple reporing service that enable users to design and generate web-based reports.
 * Built on top of the JasperReports - an open source reporting library
 * 2016 © ATT Service Assurance  - Raptor POC team
 *
 */
package com.att.raptor.report.engine.service;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

/**
 *
 * @author ebrimatunkara
 */
@Service("mailService")
public class EmailNotificationService implements NotificationService<SimpleMailMessage> {
    @Autowired
    private JavaMailSender mailSender;

    @Override
    public void send(SimpleMailMessage message) {
        try {
            mailSender.send(createMimeMessage(message));
        } catch (MessagingException ex) {
            Logger.getLogger(EmailNotificationService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    /* create mime message */
    private MimeMessage createMimeMessage(SimpleMailMessage message) throws MessagingException {
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
        helper.setFrom(message.getFrom());
        helper.setTo(message.getTo());
        helper.setSubject(message.getSubject());
        helper.setText(message.getText(),true);
       
        //FileSystemResource file = new FileSystemResource("C:\\log.txt");
        //helper.addAttachment(file.getFilename(), file);
        return mimeMessage;
    }
}
