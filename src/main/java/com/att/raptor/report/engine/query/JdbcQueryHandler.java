/**
 * Raptor Reporting service
 * A simple reporting service that enable users to design and generate web-based reports.
 * Built on top of the JasperReports - an open source reporting library
 * 2016 © ATT Service Assurance  - Raptor POC team
 */
package com.att.raptor.report.engine.query;

import com.att.raptor.report.data.domain.ReportComponent;
import com.att.raptor.report.data.domain.ReportField;
import com.att.raptor.report.engine.query.QueryUtils.QuerySet;
import com.att.raptor.report.engine.support.FilterPredicate;
import com.att.raptor.report.engine.support.ReportFieldPredicate;
import java.util.HashSet;
import java.util.Set;

/**
 * JDBCQuery Handler class - this handler class prepares and creates the jdbc
 * query arguments,statements
 *
 * @author ebrimatunkara
 */
public class JdbcQueryHandler extends QueryHandler<ReportComponent, QuerySet> {   
    /*
     * QueryParser  -- for parsing the template fields into sql query statement
     */
    private final QueryParser<QuerySet,ReportComponent> queryParser;

    public JdbcQueryHandler( QueryParser parser) {
        queryParser = parser;
    }

    private Set<String> createFieldSet(Set<ReportField> fields) {
        Set<String> fieldSet = new HashSet();
        FilterPredicate predicate = new ReportFieldPredicate();
        for (ReportField field : fields) {
            //add only visible fields to fieldset
            if (predicate.filter(field)) {
                fieldSet.add(field.getName());
            }
            //add table into the model set
           // modelSet.add(field.getModelName());
        }
        return fieldSet;
    }

   /**
    *  Process process query
     * @param component
     * @return 
    **/
    @Override
    public QuerySet  parseQuery(ReportComponent component) {
      //  Set<String> fieldSet = createFieldSet(component.getFieldComponents());
        return queryParser.parse(component);
        ///return  new QuerySet(fieldSet,query);
    }
}
