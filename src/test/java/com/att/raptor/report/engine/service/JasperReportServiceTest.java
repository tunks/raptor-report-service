/**
 * Raptor Reporting service
 * A simple reporing service that enable users to design and generate web-based reports.
 * Built on top of the JasperReports - an open source reporting library
 * 2016 © ATT Service Assurance  - Raptor POC team
 *
 */
package com.att.raptor.report.engine.service;

import com.att.raptor.report.data.domain.ReportTemplate;
import com.att.raptor.report.data.repositories.ReportTemplateRepository;
import com.att.raptor.report.engine.query.JdbcQueryHandler;
import com.att.raptor.report.engine.query.QueryHandler;
import java.util.HashSet;
import java.util.Set;
import javax.annotation.Resource;
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
 *
 * @author ebrimatunkara
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:application-context.xml"})
public class JasperReportServiceTest {
    @Autowired
    private JasperReportService jasperReportService;
    @Resource
    private ReportTemplateRepository reportTemplateRepository;
    
    private QueryHandler handler;
    
    public JasperReportServiceTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
           String templateId = "572197e3d4c6e23119db1b03";
           ReportTemplate template = reportTemplateRepository.findOne(templateId);
           handler = new JdbcQueryHandler(template);
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of generate method, of class JasperReportService.
     */
    @Test
    public void testGenerate() {
        System.out.println("generate");
        String type = "HTTP";
        Object result = jasperReportService.generate(type, handler);
        //System.out.println(result);
        assertNotNull(result);

    }
    
}
