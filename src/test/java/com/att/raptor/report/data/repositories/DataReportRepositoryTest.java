/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.att.raptor.report.data.repositories;

import com.att.raptor.report.data.domain.DataReport;
import com.att.raptor.report.data.domain.ReportTemplate;
import javax.annotation.Resource;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.runner.RunWith;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 *
 * @author ebrimatunkara
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:application-context.xml"})
//@SpringApplicationConfiguration(ReportServiceApplication.class)

public class DataReportRepositoryTest {
    @Resource
    DataReportRepository dataReportRepository;
   
    @Resource
    ReportTemplateRepository reportTemplateRepository;
      
    public DataReportRepositoryTest() {
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
    * Save DataReport without report template
    * 
    */
    @Test
    public void testSave(){
      DataReport dr = new DataReport("Daily tickets created");
      DataReport result = dataReportRepository.save(dr);
      assertEquals(dr.getName(), result.getName());
    }
    
    /**
     * Save DataReport with template
     **/
    @Test
    public void testSaveWithTemplate(){
      ReportTemplate  template = new ReportTemplate();
      ReportTemplate templateResult = reportTemplateRepository.save(template);
      DataReport dr = new DataReport("Daily tickets closed",templateResult);
      DataReport result = dataReportRepository.save(dr);
      assertEquals(dr.getName(), result.getName());
    }
    
}
