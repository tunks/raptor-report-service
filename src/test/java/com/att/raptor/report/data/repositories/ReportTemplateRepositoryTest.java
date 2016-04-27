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
import static org.junit.Assert.*;

import javax.annotation.Resource;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.att.raptor.report.data.domain.ReportTemplate;
import com.att.raptor.report.data.domain.Template;
import com.att.raptor.report.data.support.DataFieldType;
import com.att.raptor.report.data.support.FieldAggregateType;
import java.util.HashSet;
import java.util.Set;

/**
 * @author ebrimatunkara
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:application-context.xml"})
public class ReportTemplateRepositoryTest {

    @Resource
    private ReportTemplateRepository reportTemplateRepository;

    @Resource
    private ReportComponentRepository reportComponentRepository;

    private Set<ReportComponent> reportComponents;
    /**
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception {
        reportComponents = new HashSet();
        ReportComponent reportComponent = new ReportComponent();
        Set<ReportField> component = new HashSet();

        ReportField rfield1 = new ReportField(null, "field1", DataFieldType.STRING);
        ReportField customField1 = new ReportField("Sum Total", DataFieldType.STRING,FieldAggregateType.SUM);

        component.add(rfield1);
        component.add(customField1);
        reportComponent.setFieldComponents(component);
        reportComponents.add(reportComponent);
    }

    /**
     * @throws java.lang.Exception
     */
    @After
    public void tearDown() throws Exception {
    }

    /**
     * Test method for
     * {@link org.springframework.data.mongodb.repository.MongoRepository#save(java.lang.Object)}.
     */
    @Test
    public void testSave() {
        //org.springframework.data.mongodb.core.MongoOperations.update
        reportComponentRepository.save(reportComponents);
        ReportTemplate template = new ReportTemplate("template xxxxx1");
        template.setComponents(reportComponents);
        Template result = reportTemplateRepository.save(template);
        System.out.println("version " + result.getVersion());
        assertEquals(template.getId(), result.getId());
    }

    /**
//     * Test method for
//     * {@link org.springframework.data.mongodb.repository.MongoRepository#update(java.lang.Object)}.
//     */
//    @Test
//    public void testUpdate() {
//        //org.springframework.data.mongodb.core.MongoOperations.update
//        ReportTemplate template = new ReportTemplate("report template xxxx1");
//        Template result = reportTemplateRepository.update(template);    
//        assertNotNull(result);
//    }
}
