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

/**
 *
 * @author ebrimatunkara
 */
public class ReportFolderServiceTest {
    
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
        ReportFolderService instance = new ReportFolderService();
        List<ReportFolder> expResult = null;
        List<ReportFolder> result = instance.findAll();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of find method, of class ReportFolderService.
     */
    @Test
    public void testFind() {
        System.out.println("find");
        String id = "";
        ReportFolderService instance = new ReportFolderService();
        ReportFolder expResult = null;
        ReportFolder result = instance.find(id);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of create method, of class ReportFolderService.
     */
    @Test
    public void testCreate() {
        System.out.println("create");
        ReportFolder object = null;
        ReportFolderService instance = new ReportFolderService();
        ReportFolder expResult = null;
        ReportFolder result = instance.create(object);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of update method, of class ReportFolderService.
     */
    @Test
    public void testUpdate() {
        System.out.println("update");
        ReportFolder object = null;
        ReportFolderService instance = new ReportFolderService();
        ReportFolder expResult = null;
        ReportFolder result = instance.update(object);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
