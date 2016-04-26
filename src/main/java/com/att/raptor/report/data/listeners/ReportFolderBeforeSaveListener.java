/**
 * Raptor Reporting service
 * A simple reporing service that enable users to design and generate web-based reports.
 * Built on top of the JasperReports - an open source reporting library
 * 2016 © ATT Service Assurance  - Raptor POC team
 *
 */
package com.att.raptor.report.data.listeners;

import com.att.raptor.report.data.domain.ReportFolder;
import org.springframework.data.mongodb.core.mapping.event.AbstractMongoEventListener;
import org.springframework.data.mongodb.core.mapping.event.BeforeSaveEvent;
import org.springframework.stereotype.Component;

/**
 * ReportFolderBeforeSaveListener actions  -- to set the parent id of sub-folders and templates
 * @author ebrimatunkara
 */
@Component
public class ReportFolderBeforeSaveListener extends AbstractMongoEventListener<ReportFolder>{

    @Override
    public void onBeforeSave(BeforeSaveEvent<ReportFolder> event) {
       super.onBeforeSave(event); //To change body of generated methods, choose Tools | Templates.
    }
}


