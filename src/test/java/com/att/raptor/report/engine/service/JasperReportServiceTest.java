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
import com.att.raptor.report.engine.query.QueryParser;
import com.att.raptor.report.engine.support.JasperReportFactory;
import com.att.raptor.report.engine.support.ReportFormat;
import com.att.raptor.report.starter.ReportServiceApplication;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.assertNotNull;
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

    private  ReportFormat format;
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
        String templateId = "5722328fd4c69551a9dcbb0f";// 5722328fd4c69551a9dcbb0f
        ReportTemplate template = reportTemplateService.find(templateId);
        QueryParser  parser = JasperReportFactory.createQueryParser();
        handler = new JdbcQueryHandler(template,parser);
       format = ReportFormat.formatType("html");
    }

    @After
    public void tearDown() {
       
    }

    /**
     * Test of generate method, of class JasperReportService.
     */
   // @Test
    public void testProcess() {
        System.out.println("generate");
        Object result = jasperReportService.process(handler,format);
        System.out.println(result);
        assertNotNull(result);
    }
    
    @Test
    public void proccessAction(){
       // jasperReportService.process(handler);
    }
}
//
