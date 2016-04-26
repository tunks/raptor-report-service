/**
 * Raptor Reporting service
 * A simple reporing service that enable users to design and generate web-based reports.
 * Built on top of the JasperReports - an open source reporting library
 * 2016 © ATT Service Assurance  - Raptor POC team
 *
 */
package com.att.raptor.report.data.repositories;

import com.att.raptor.report.data.domain.DataComponent;
import com.att.raptor.report.data.domain.ReportComponent;
import com.att.raptor.report.data.domain.ReportField;
import com.att.raptor.report.data.support.DataFieldType;
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
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 *
 * @author ebrimatunkara
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:application-context.xml"})
public class ReportComponentRepositoryTest {
    @Resource
    private ReportComponentRepository  reportComponentRepository;
     
    private ReportComponent reportComponent ;

    public ReportComponentRepositoryTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        reportComponent = new ReportComponent();
        Set<DataComponent> component = new HashSet();
        ReportField  rfield1 = new ReportField(null,"field1",DataFieldType.STRING); 
        component.add(rfield1);
        reportComponent.setReportComponents(component);
    }
    
    @After
    public void tearDown() {
    }

    @Test
    public void testSave() { 
       ReportComponent result = reportComponentRepository.save(reportComponent);
       assertEquals(reportComponent,result);
    }
    
}
