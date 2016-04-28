/**
 * Raptor Reporting service
 * A simple reporing service that enable users to design and generate web-based reports.
 * Built on top of the JasperReports - an open source reporting library
 * 2016 © ATT Service Assurance  - Raptor POC team
 *
 */
package com.att.raptor.report.web.controller;

import com.att.raptor.report.data.domain.ReportComponent;
import com.att.raptor.report.data.domain.ReportFolder;
import com.att.raptor.report.data.domain.ReportTemplate;
import com.att.raptor.report.data.service.ReportComponentService;
import com.att.raptor.report.data.service.ReportFolderService;
import com.att.raptor.report.data.service.ReportTemplateService;
import com.att.raptor.report.data.support.ReportComponentType;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import static org.junit.Assert.*;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * ReportTemplateController -- implementation of ReportTemplate Controller
 *
 * @author ebrimatunkara
 */
@RestController
@RequestMapping("/att/reports/templates")
public class ReportTemplateController implements ControllerBase<ReportTemplate>{
    @Autowired
    private ReportComponentService reportComponentService;
    @Autowired
    private ReportTemplateService reportTemplateService;
    @Autowired
    private ReportFolderService reportFolderService;
        
    @RequestMapping(method = RequestMethod.GET)
    @Override
    public ReportTemplate find(String id) {
       return reportTemplateService.find(id);
    }

    @Override
    public void deleteAll() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @RequestMapping(method = RequestMethod.DELETE)
    @Override
    public void delete(String id) {
       reportTemplateService.delete(id);
    }

    @RequestMapping(method = RequestMethod.POST)
    @ResponseBody
    @Override
    public ReportTemplate create( @RequestBody ReportTemplate object) {      
        
        String parentId = object.getParentId();
        ReportFolder folder = reportFolderService.find(parentId);              
        assertNotNull(folder);
        
        ReportTemplate template = reportTemplateService.create(object);
        assertNotNull(template);
        
        folder.addReportTemplate(template);
        reportFolderService.update(folder);
        
        return template;
    }

    @RequestMapping(method = RequestMethod.PUT)
    @ResponseBody
    @Override
    public ReportTemplate update(@RequestBody ReportTemplate object) {
      return  reportTemplateService.update(object);
    }   
    /***
     * find template components by template id and type
     * @param templateId
     * @param type
     * @return 
     */
    @ResponseBody
    @RequestMapping(value="/{templateId}/components", method=RequestMethod.GET)
    public List<ReportComponent> findByTemplateAndType( @PathVariable String templateId, @RequestParam(value="type" , required=false) ReportComponentType type){
        if(type != null) 
           return reportComponentService.findByTemplateIdAnd(templateId, type);
        else
          return  reportComponentService.findByTemplateId(templateId);
    }
    
    /**
     * create and save template component 
     * @param templateId
     * @param component
     * @return 
     */
    @ResponseBody
    @RequestMapping(value="/{templateId}/components", method=RequestMethod.POST)
    public ReportComponent createComponent( @PathVariable String templateId, @RequestBody ReportComponent component){
           //fetch template by the template id give
           ReportTemplate template = reportTemplateService.find(templateId);
           assertNotNull(template);
           
           //set the component templateId
           assertNotNull(component);
           component.setTemplateId(templateId);
           ReportComponent result = reportComponentService.create(component);
           
           //add result to template
           template.addComponent(result);
           //update template
           reportTemplateService.update(template);
           return result;
    }
    
    /**
     * update template component 
     * @param templateId
     * @param component
     * @return 
     */
    @ResponseBody
    @RequestMapping(value="/{templateId}/components", method=RequestMethod.PUT)
    public ReportComponent updateComponent( @PathVariable String templateId, @RequestBody ReportComponent component){
       return reportComponentService.update(component);
    }
}
