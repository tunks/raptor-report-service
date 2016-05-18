/**
 * Raptor Reporting service
 * A simple reporing service that enable users to design and generate web-based reports.
 * Built on top of the JasperReports - an open source reporting library
 * 2016 © ATT Service Assurance  - Raptor POC team
 *
 */
package com.att.raptor.report.engine.service;

import com.att.raptor.report.data.domain.ReportTemplate;
import com.att.raptor.report.data.service.ReportTemplateService;
import com.att.raptor.report.engine.query.JdbcQueryHandler;
import com.att.raptor.report.engine.query.QueryHandler;
import com.att.raptor.report.starter.ReportServiceApplication;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 *
 * @author ebrimatunkara
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(locations = {"classpath:application-context.xml"}, 
                                            classes=ReportServiceApplication.class)

public class JasperReportServiceTest {
    @Autowired
    private JasperReportService jasperReportService;
    
    @Autowired
    private ReportTemplateService reportTemplateService;

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
        String templateId = "57224cc477c81fca3b334513";
        ReportTemplate template = reportTemplateService.find(templateId);
        handler = new JdbcQueryHandler(template);
    }

    @After
    public void tearDown() {
       
    }

    /**
     * Test of generate method, of class JasperReportService.
     */
    //@Test
    public void testProcess() {
//        System.out.println("generate");
//        String type = "HTTP";
//        Object result = jasperReportService.generate(type, handler);
//        //System.out.println(result);
//        assertNotNull(result);
    }
    
    @Test
    public void proccessAction(){
        jasperReportService.process(handler);
    }
}
//
