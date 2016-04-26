/**
 * Raptor Reporting service
 * A simple reporing service that enable users to design and generate web-based reports.
 * Built on top of the JasperReports - an open source reporting library
 * 2016 © ATT Service Assurance  - Raptor POC team
 *
 */
package com.att.raptor.report.data.repositories;

import com.att.raptor.report.data.domain.DataField;
import com.att.raptor.report.data.domain.DataSource;
import com.att.raptor.report.data.domain.DataSourceModel;
import com.att.raptor.report.data.domain.DataSourceProperty;
import com.att.raptor.report.data.domain.DataSourceType;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.annotation.Resource;
import org.joda.time.DateTime;
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
 * Test DataSouce Repository
 * @author ebrimatunkara
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:application-context.xml"})
public class DataSourceRepositoryTest {
    @Resource
    DataSourceRepository dataSourceRepository;  
    
    private DataSource dataSource;
    private DataSourceProperty sourceProperty;   
    private DataSourceType sourceType;
    private Set<DataSourceModel> sourceModels;
    private String sourceName ;
    private String username;
    private String password;
    private String url;
    
    public DataSourceRepositoryTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        sourceModels = new HashSet();
        sourceName = "VTM-MariaDB";
        username = "root";
        password = "admin";
        url = "http://localhost:8080/data";
        sourceType = DataSourceType.HTTP;
        sourceProperty = new DataSourceProperty(username,password,url,sourceType);
        dataSource =  new DataSource(sourceName,sourceProperty);
        //add data models
        sourceModels.add(new DataSourceModel("1xxx1","Model1"));
        sourceModels.add(new DataSourceModel("1xxx2","Model2"));
        sourceModels.add(new DataSourceModel("1xxx3","Model3"));
        //add fields to data model
        Set<DataField> fields = new HashSet();
        fields.add(new DataField("1xxff1","ticketNumber"));
        fields.add(new DataField("1xxff2","ticketStatus"));
        fields.add(new DataField("1xxff3","ticketAutoState"));
        DataSourceModel model4 = new DataSourceModel("1xxx4","Model4");
        model4.setFields(fields);
        sourceModels.add(model4);
    }
    
    @After
    public void tearDown() {
    }
    /***
     * Save new DataSource object
     **/
    @Test
    public void testSave() {
       DataSource result = dataSourceRepository.save(dataSource);
       assertNotNull(result);
    }

    /***
     * Update existing DataSource object
     **/
    @Test
    public void testUpdate() {
       List<DataSource> sources = dataSourceRepository.findAll();
       if(sources.size() > 0){
         DataSource ds = sources.get(0);
         String name = "ds_" +DateTime.now();
         ds.setName(name);
         DataSource result = dataSourceRepository.update(ds);
         System.out.println("Version "+ds.getVersion());
         assertEquals(result.getName(),name);
       }
    }

     /***
      * List of data sources by name
      **/
    @Test
    public void testFindByName() {
        List<DataSource> sources = dataSourceRepository.findByName(sourceName);
        assertTrue(sources.size() > 0);
        assertEquals(sources.get(0).getProperty(), sourceProperty);
    } 
    
     /***
     * Update existing DataSource object
     **/
    @Test
    public void testUpdateWithDataModels() {
       List<DataSource> sources = dataSourceRepository.findAll();
       if(sources.size() > 0){
         DataSource ds = sources.get(0);
         ds.setModels(sourceModels);
         DataSource result = dataSourceRepository.update(ds);
         assertEquals(sourceModels.size(),result.getModels().size());
       }
    }
}
