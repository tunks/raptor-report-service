/**
 * Raptor Reporting service
 * A simple reporing service that enable users to design and generate web-based reports.
 * Built on top of the JasperReports - an open source reporting library
 * 2016 © ATT Service Assurance  - Raptor POC team
 *
 */
package com.att.raptor.report.web.controller;

import com.att.raptor.report.data.support.DataFieldType;
import com.att.raptor.report.data.support.FieldOperator;
import com.att.raptor.report.data.support.FieldValueType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Misc controller to provide data operators
 * @author ebrimatunkara
 */
@RestController
@RequestMapping("/att/reports/support")
public class DataSupportController {
    /*
      gets all data operators
     */
    @RequestMapping(value="/operators",method=RequestMethod.GET)
    public FieldOperator[] getAllOperators(){
         return FieldOperator.values();
    }         
    
    /*
      value types
     */
    @RequestMapping(value="/valuetypes",method=RequestMethod.GET)
    public FieldValueType[] getAllValueTypes(){
         return FieldValueType.values();
    }
    
     /*
      value field data types
     */
    @RequestMapping(value="/datatypes",method=RequestMethod.GET)
    public DataFieldType[] getAllDataTypes(){
         return DataFieldType.values();
    }
}
