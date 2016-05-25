/**
 * Raptor Reporting service
 * A simple reporting service that enable users to design and generate web-based reports.
 * Built on top of the JasperReports - an open source reporting library
 * 2016 © ATT Service Assurance  - Raptor POC team
 *
 */
package com.att.raptor.report.engine.query;

import com.att.raptor.report.data.domain.ReportComponent;
import com.att.raptor.report.data.domain.ReportField;
import com.att.raptor.report.data.domain.ReportTemplate;
import com.att.raptor.report.engine.support.FilterPredicate;
import com.att.raptor.report.engine.support.ReportFieldPredicate;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

/**
 * JDBCQuery Handler class - this handler class prepares and creates the jdbc query arguments,statements
 * @author ebrimatunkara
 */
public class JdbcQueryHandler extends QueryHandler<PreparedStatement, List<String>> {
    private Set<String> modelSet;
    private Set<String> fieldSets;
    /*
     * QueryParser 
     */
    private QueryParser queryParser;

    public JdbcQueryHandler(ReportTemplate template, QueryParser parser) {
        super(template);
        queryParser = parser;
        modelSet = new HashSet();
        fieldSets = createFieldSets(template.getComponents());
    }

    @Override
    public PreparedStatement getQuery() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<String> parseQuery() {
        List<String> queries = new ArrayList();
        Iterator<ReportComponent> itr = getTemplate().getComponents().iterator();
        while(itr.hasNext()){
           queries.add(queryParser.parse(itr.next()));
        }
        return queries;
    }

    @Override
    public String getQueryString() {
       return  parseQuery().get(0);//SimpleQuery.createQuery(fieldSets, modelSet);
    }

    @Override
    public Set getFieldSet() {
          if(fieldSets == null || fieldSets.isEmpty())
             fieldSets =createFieldSets(this.getTemplate().getComponents());
        return fieldSets;
    }

    private Set<String> createFieldSets(  Set<ReportComponent> components ) {
         Set<String> fieldSet = new HashSet();
        for (ReportComponent component : components) {
             fieldSet.addAll(createFieldSet(component.getFieldComponents()));
        }
        return fieldSet;
    }

    private Set<String> createFieldSet(Set<ReportField> fields) {
        Set<String> fieldSet = new HashSet();
        FilterPredicate predicate = new ReportFieldPredicate();  
        for (ReportField field : fields) {
             //add only visible fields to fieldset
             if(predicate.filter(field)){
                fieldSet.add(field.getName());
             }
             //add table into the model set
             modelSet.add(field.getModelName());
        }
        return fieldSet;
    }
    
}
