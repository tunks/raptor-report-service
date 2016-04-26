/**
 * Raptor Reporting service
 * A simple reporing service that enable users to design and generate web-based reports.
 * Built on top of the JasperReports - an open source reporting library
 * 2016 © ATT Service Assurance  - Raptor POC team
 *
 */
package com.att.raptor.report.data.repositories;

import com.att.raptor.report.data.domain.ReportFolder;
import com.att.raptor.report.data.domain.ReportTemplate;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Resource;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 *
 * @author ebrimatunkara
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:application-context.xml"})
public class ReportFolderRepositoryTest {
    @Resource
    private ReportFolderRepository  reportFolderRepository;
    @Resource
    private ReportTemplateRepository reportTemplateRepository;

    private ReportFolder mainReportFolder;

    public ReportFolderRepositoryTest() {
        
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        // main folder
        mainReportFolder = new ReportFolder("Report Folder 1");
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of findByParentId method, of class ReportFolderRepository.
     */
    @Test
    public void testFindByParentId() {
        System.out.println("findByParentId");
        String parentId = "";
        List<ReportFolder> expResult = null;
        List<ReportFolder> result = reportFolderRepository.findByParentId(parentId);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
    }

    
     /**
     * Test of save method, of class ReportFolderRepository.
     */
    @Test
    public void testSave() {
        System.out.println("Report Folder save");
        ReportFolder result = reportFolderRepository.save(mainReportFolder);
        assertNotNull(result);
    }
    
    private List<ReportFolder> createSubFolders(String parentId){
        List<ReportFolder> subFolder =  new ArrayList();
        subFolder.add(new ReportFolder("Sub report folder 1", parentId));
        subFolder.add(new ReportFolder("Sub report folder 2", parentId));
        subFolder.add(new ReportFolder("Sub report folder 3", parentId));
        return  subFolder;
    }
    
   private List<ReportTemplate> createTemplates(String parentId){
        List<ReportTemplate> templates =  new ArrayList();
        templates.add(new ReportTemplate("Report 1 -> template 1", parentId));
        templates.add(new ReportTemplate("Report 1 -> template 2", parentId));
        templates.add(new ReportTemplate("Report 1 -> template 3", parentId));
        return templates;
    }
    
        
    /**
     * Test of update - add sub folders to main report repository, of class ReportFolderRepository.
     */
    @Test
    public void testUpdate() {
        System.out.println("Report Folder save");
        //save sub report folders
        List<ReportFolder> folders = reportFolderRepository.findByParentIdNull();
        if(folders.size() > 0){
          mainReportFolder = folders.get(0);
          List<ReportFolder> subFolder = createSubFolders(mainReportFolder.getId());
          List< ReportFolder> subFolderResults = reportFolderRepository.save(subFolder);
          mainReportFolder.setSubFolders(subFolderResults);
          ReportFolder result = reportFolderRepository.update(mainReportFolder);
          assertNotNull(result);
          assertTrue(result.getSubFolders().size()> 0);
        }
    }
    
      /**
     * Test of update-- add templates to  report folder repository, of class ReportFolderRepository.
     */
    @Test
    public void testUpdateWithTemplate() {
        System.out.println("Report Folder save");
        //save sub report folders
        List<ReportFolder> folders = reportFolderRepository.findByParentIdNull();
        if(folders.size() > 0){
          mainReportFolder = folders.get(0);
          List<ReportTemplate> templates =createTemplates(mainReportFolder.getId());
          List< ReportTemplate> templateResults = reportTemplateRepository.save(templates);
          mainReportFolder.setReportTemplates(templateResults);
          ReportFolder result = reportFolderRepository.update(mainReportFolder);
          assertNotNull(result);
          assertTrue(result.getReportTemplates().size()> 0);
        }
    }
    
     /**
     * Test of update method - add folders to a single  sub report folder , of class ReportFolderRepository.
     */
    @Test
    public void testUpdateSubFolder() {
        System.out.println("Report Folder save");
        //save sub report folders
        List<ReportFolder> folders = reportFolderRepository.findByParentIdNotNull();
        if(folders.size() > 0){
          mainReportFolder = folders.get(0);
          List<ReportFolder> subFolders =createSubFolders(mainReportFolder.getId());
          List< ReportFolder> subFolderResults = reportFolderRepository.save(subFolders);
          mainReportFolder.setSubFolders(subFolderResults);
          ReportFolder result = reportFolderRepository.update(mainReportFolder);
          assertNotNull(result);
          assertTrue(result.getSubFolders().size()> 0);
        }
    }  
    
     /**
     * Test of update method - add templates to a single  sub report folder , of class ReportFolderRepository.
     */
    @Test
    public void testUpdateSubFolderWithTemplate() {
        System.out.println("Report Folder save");
        //save sub report folders
        List<ReportFolder> folders = reportFolderRepository.findByParentIdNotNull();
        if(folders.size() > 0){
          mainReportFolder = folders.get(0);
          List<ReportTemplate> templates =createTemplates(mainReportFolder.getId());
          List< ReportTemplate> templateResults = reportTemplateRepository.save(templates);
          mainReportFolder.setReportTemplates(templateResults);
          ReportFolder result = reportFolderRepository.update(mainReportFolder);
          assertNotNull(result);
          assertTrue(result.getReportTemplates().size()> 0);
        }
    }  
}
