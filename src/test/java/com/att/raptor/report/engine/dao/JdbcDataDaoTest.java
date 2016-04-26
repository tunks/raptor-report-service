/**
 * Raptor Reporting service
 * A simple reporing service that enable users to design and generate web-based reports.
 * Built on top of the JasperReports - an open source reporting library
 * 2016 © ATT Service Assurance  - Raptor POC team
 *
 */
package com.att.raptor.report.engine.dao;

import com.att.raptor.report.data.domain.DataSourceProperty;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Set;
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
import com.att.raptor.report.engine.query.DataQueryHandler;
import java.util.HashSet;

/**
 *
 * @author ebrimatunkara
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:application-context.xml"})
public class JdbcDataDaoTest {
    @Autowired
    private JdbcDataDao jdbcDatadao;
    
    public JdbcDataDaoTest() {}
    
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
     * Test of getModels method, of class JdbcDataDao.
     * @throws java.sql.SQLException
     */
    @Test
    public void testGetModels() throws SQLException {
        System.out.println("get models");
        Set result = jdbcDatadao.getModels();
        System.out.println("result ... "+result);
        assertTrue(result.size()> 0);
    }
    /**
     * Test of getResults method, of class JdbcDataDao.
     */
    @Test
    public void testGetResults_DataQuery() { 
        System.out.println("getResults");
        DataQueryHandler query = new DataQueryHandler(){
           
            @Override
            public Object processQuery() {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public Object getQuery() {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public String getQueryString() {
                return "SELECT * FROM aircraft";   
            }

            @Override
            public Set getFieldSet() {
                Set fields = new HashSet();
                fields.add("aircraft_serial");
                fields.add("aircraft_model_code");
                return fields;
            }
        };
        
        List result = jdbcDatadao.getResults(query);
        assertNotNull(result);
    }
    
}
