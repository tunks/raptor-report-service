/**
 * Raptor Reporting service
 * A simple reporing service that enable users to design and generate web-based reports.
 * Built on top of the JasperReports - an open source reporting library
 * 2016 © ATT Service Assurance  - Raptor POC team
 *
 */
package com.att.raptor.report.data.service;

import com.att.raptor.report.data.domain.ReportFolder;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * ReportFolder Service test
 *
 * @author ebrimatunkara
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:application-context.xml"})
public class ReportFolderServiceTest {

    @Autowired
    private ReportFolderService reportFolderService;

    private ReportFolder mainReportFolder;

    public ReportFolderServiceTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        mainReportFolder = new ReportFolder("Service Report folder 1");
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of findAll method, of class ReportFolderService.
     */
    @Test
    public void testFindAll() {
        System.out.println("findAll");
        List<ReportFolder> result = reportFolderService.findAll();
        if (result.size() > 0) {
            assertTrue(result.size() > 0);
        }
    }

//    /**
//     * Test of find method, of class ReportFolderService.
//     */
//    @Test
//    public void testFind() {
//        System.out.println("find");
//        String id = "";
//        ReportFolderService instance = new ReportFolderService();
//        ReportFolder expResult = null;
//        ReportFolder result = instance.find(id);
//        assertEquals(expResult, result);
//    }

    /**
     * Test of create method, of class ReportFolderService.
     */
    @Test
    public void testCreate() {
        System.out.println("create");
        ReportFolder result = reportFolderService.create(mainReportFolder);
        assertNotNull(result);
    }

    /**
     * Test of update method, of class ReportFolderService.
     */
    @Test
    public void testUpdate() {
        System.out.println("update");
        List<ReportFolder> folders = reportFolderService.findAllRoots();
        if (folders.size() > 0) {
            String name = "Report name change";
            mainReportFolder = folders.get(0);
            mainReportFolder.setName(name);
            ReportFolder result = reportFolderService.update(mainReportFolder);
            assertEquals(result.getName(), name);
        }
    }

}
