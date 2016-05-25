/**
 * Raptor Reporting service
 * A simple reporing service that enable users to design and generate web-based reports.
 * Built on top of the JasperReports - an open source reporting library
 * 2016 © ATT Service Assurance  - Raptor POC team
 *
 */
package com.att.raptor.report.engine.query;

import com.att.raptor.report.data.domain.ReportComponent;

/**
 * SimpleQueryParser class implementation of the QueryParser abstract class
 * @author ebrimatunkara
 */
public class SimpleQueryParser extends JdbcQueryParser<ReportComponent>{
    
    public SimpleQueryParser(){
      super();
    }
    
    public SimpleQueryParser(QueryParser nextParser) {
        super(nextParser);      
    }

    @Override
    public String parse(ReportComponent component) {
         //if component has not condition argument
         if(!component.hasArgumentFields()){
            //create query sets for fields and model table
            createQuerySets(component.getFieldComponents());
            //parse the query string
            return parseQuery();
         }
         //continue to next parse with query argument fields
         if(getNextParser()!=null){
            return getNextParser().parse(component);
         }
        throw new QueryParserException("No query components or fields provided"); 
    }

}