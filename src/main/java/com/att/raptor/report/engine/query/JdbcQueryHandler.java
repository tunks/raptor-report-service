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
import java.sql.PreparedStatement;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * JDBCQuery Handler class - this handler class prepares and creates the jdbc query arguments,statements
 * @author ebrimatunkara
 */
public class JdbcQueryHandler extends QueryHandler<PreparedStatement, List> {
    private Set<String> modelSet;
    private Set<String> fieldSets;
    public JdbcQueryHandler() {
        modelSet = new HashSet();
    }

    public JdbcQueryHandler(ReportTemplate template) {
        super(template);
        modelSet = new HashSet();
        fieldSets = createFieldSets(template);
    }
   
    @Override
    public PreparedStatement getQuery() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List processQuery() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getQueryString() {
       return SimpleQuery.createQuery(fieldSets, modelSet);
    }

    @Override
    public Set getFieldSet() {
          if(fieldSets == null || fieldSets.isEmpty())
             fieldSets =createFieldSets(this.getTemplate());
        return fieldSets;
    }

    private Set<String> createFieldSets(ReportTemplate template) {
        Set<ReportComponent> components = template.getComponents();
        Set<String> fieldSet = new HashSet();
        for (ReportComponent component : components) {
             fieldSet.addAll(createFieldSet(component.getFieldComponents()));
        }
        return fieldSet;
    }

    private Set<String> createFieldSet(Set<ReportField> fields) {
        Set<String> fieldSet = new HashSet();
        for (ReportField field : fields) {
             //add field set
             fieldSet.add(field.getName());
             //add table into the model set
             modelSet.add(field.getModelName());
        }
        return fieldSet;
    }

    public static class SimpleQuery{
         public static String createQuery(Set<String> fieldSet, Set<String> modelSet){
                String fields = QueryUtils.collectionToString(fieldSet);
                String tables = QueryUtils.collectionToString(modelSet);
                return  "SELECT " +fields + " FROM "+ tables;
         }  
    }
}
