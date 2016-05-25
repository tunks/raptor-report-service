/**
 * Raptor Reporting service
 * A simple reporing service that enable users to design and generate web-based reports.
 * Built on top of the JasperReports - an open source reporting library
 * 2016 © ATT Service Assurance  - Raptor POC team
 *
 */
package com.att.raptor.report.engine.query;

import com.att.raptor.report.data.domain.ReportField;
import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author ebrimatunkara
 */
public abstract class JdbcQueryParser<T> extends QueryParser<T> {
    private Set<String> fieldSet;
    private  Set<String> modelSet;

    public JdbcQueryParser() {
         initialize();
    }

    public JdbcQueryParser(QueryParser nextParser) {
        super(nextParser);
        initialize();
    }

    /**
     * Retrieve the fields and table model
     *
     * @param fields
     *
     */
    public void createQuerySets(Set<ReportField> fields) {
        for (ReportField field : fields) {
            //add field set
            fieldSet.add(field.getName());
            //add table into the model set
            modelSet.add(field.getModelName());
        }
        //TODO -- refactoring
        //invoke exception if  model set is more than 1
        //not support now 
        if (modelSet.size() > 1) {
            throw new QueryParserException("Multiple table query joins not supported");
        }
    }
    
    /**
     * Parse fields and model table to Sql query string
     * @return 
     */
    public String parseQuery(){
         //fieldsets to string
         String fields = QueryUtils.collectionToString(fieldSet);
         //get only single table
         String table =  QueryUtils.collectionToString(modelSet,1);
         return  "SELECT " +fields + " FROM "+ table;
    }
    
    private void initialize(){
           fieldSet = new HashSet();
           modelSet = new HashSet();
    }
    
}
