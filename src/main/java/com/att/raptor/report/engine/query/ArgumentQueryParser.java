/**
 * Raptor Reporting service
 * A simple reporing service that enable users to design and generate web-based reports.
 * Built on top of the JasperReports - an open source reporting library
 * 2016 © ATT Service Assurance  - Raptor POC team
 *
 */
package com.att.raptor.report.engine.query;

import com.att.raptor.report.data.domain.ReportArgumentField;
import com.att.raptor.report.data.domain.ReportComponent;
import com.att.raptor.report.data.domain.ReportField;
import com.att.raptor.report.data.support.FieldOperator;
import com.att.raptor.report.data.support.FieldValueType;
import java.util.Iterator;
import java.util.Set;
import java.util.UUID;

/** 
 * ArgumentQueryParser 
 * @author ebrimatunkara
 */
public class ArgumentQueryParser extends  JdbcQueryParser<ReportComponent>{
    private final String SPACE = " ";
    private final String CONDITION_AND = "AND";
    private String condition = "";
    
    public ArgumentQueryParser() {
        super();
    }
    public ArgumentQueryParser(QueryParser nextParser) {
        super(nextParser);
    }

    @Override
    public String parse(ReportComponent component) {
        //when component has argument field
        if (component.hasArgumentFields()) {
            //create query sets for fields and model table
            createQuerySets(component.getFieldComponents());
            //parse the query string
            String query =  this.parseQuery().concat(SPACE);
            return query + createArgumentQuery(component.getFieldComponents());
        }

        //continue to next parse with query argument fields
        if (getNextParser() != null) {
            return getNextParser().parse(component);
        }

        throw new QueryParserException("No query components or fields provided");
    }
    /**
     * Create conditional query
     **/
    private String createArgumentQuery(Set<ReportField> fields) {
        StringBuilder builder = new StringBuilder();
        builder.append("WHERE");
        for (ReportField field : fields) {
            if (field.getFieldArguments().size() > 0) {
                parseFieldArguments(builder, field.getName(), field.getFieldArguments());
            }
        }
       
        return builder.toString();
    }

    /**
     * Parse field arguments
     **/
    private void parseFieldArguments(StringBuilder builder, String fieldName, Set<ReportArgumentField> fieldArgs) {
        Iterator<ReportArgumentField> itr = fieldArgs.iterator();
        String query;
        //UUID.fromString(Integer.MAX_VALUE);
        while (itr.hasNext()) {
            builder.append(SPACE);
            builder.append(condition);
            builder.append(SPACE);
            query = parseFieldArgument(fieldName, itr.next());
            builder.append(query);
            condition = CONDITION_AND;
        }
    }

    private String parseFieldArgument(String fieldName, ReportArgumentField fieldArg) {
        FieldOperator operator = fieldArg.getOperator();
        FieldValueType valueType = fieldArg.getArgValue().getValueType();
        //determine if the value type
        if (!valueType.equals(FieldValueType.CUSTOM_TYPE)) {
            throw new QueryParserException("Only custom value type is suppported!");
        }
        //custom value 
        String customValue = fieldArg.getArgValue().getCustomValue();
        //query string
        String query = fieldName + SPACE + operator.getOperator();
        //TODO if operator is LIKE
        if(fieldArg.getOperator().equals(FieldOperator.LIKE)){
           return query.concat(SPACE + "'%" + customValue + "%'");
        }
        
        if(fieldArg.getFieldType() != null){
            switch (fieldArg.getFieldType()) {
                case TEXT:
                case CHAR:
                case STRING:
                    return query.concat(SPACE + "'" + customValue + "'");
                default:
                    return query.concat(SPACE + customValue);
              }
        }
        return query.concat(SPACE + customValue);
    }
}
