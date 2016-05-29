/**
 * Raptor Reporting service
 * A simple reporing service that enable users to design and generate web-based reports.
 * Built on top of the JasperReports - an open source reporting library
 * 2016 © ATT Service Assurance  - Raptor POC team
 *
 */
package com.att.raptor.report.engine.query;

import com.att.raptor.report.data.domain.DataField;
import com.att.raptor.report.data.domain.ReportArgumentField;
import com.att.raptor.report.data.domain.ReportComponent;
import com.att.raptor.report.data.domain.ReportField;
import com.att.raptor.report.data.support.DataFieldType;
import com.att.raptor.report.data.support.FieldOperator;
import com.att.raptor.report.data.support.FieldValueType;
import com.att.raptor.report.engine.query.QueryUtils.QuerySet;
import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Table;
import com.healthmarketscience.sqlbuilder.BinaryCondition;
import com.healthmarketscience.sqlbuilder.Condition;
import com.healthmarketscience.sqlbuilder.SelectQuery;
import com.healthmarketscience.sqlbuilder.dbspec.basic.DbColumn;
import com.healthmarketscience.sqlbuilder.dbspec.basic.DbSchema;
import com.healthmarketscience.sqlbuilder.dbspec.basic.DbSpec;
import com.healthmarketscience.sqlbuilder.dbspec.basic.DbTable;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ebrimatunkara
 */
public class JdbcQueryParser implements QueryParser<QuerySet,ReportComponent> {
    private DbSchema schema;
    public JdbcQueryParser() {
        initialize();
    }

    /**
     * Retrieve the fields and table model table | fields | joins |
     * ------------------------------------------- T1 | Set<TF1> |
     * Set<T2, Field> | T2 | Set<TF2> | Set<T1,F1>
     *
     *
     * select a.ID, b.brand, 
     * ( select count(c.ID) from cars c where
     * a.brand=c.brand ) as countCars from cars a join brands b on a.brand=b.ID
     * ------------------------------------ 
     * select a.ID, b.brand 
     * from cars a
     * join brands b 
     *      on a.brand=b.ID where a.brand in (1,2,6)
     *
     *
     * @param fields
     * @return 
     */
    private  Table<String,String,ReportField>  createFieldTable(Set<ReportField> fields) {
        Table<String,String,ReportField> fieldTable =  HashBasedTable.create();
        for(ReportField field : fields) {
            String tableName = field.getModelName();
            fieldTable.put(tableName, field.getName(),field);
        }
        return fieldTable;
    }

    /**
     * Parse fields and model table to Sql query string
     *
     * @return
     */
    
    public String parseQuery() {
       //createQueryTable()
        return "";
    }

    private void initialize() {
       DbSpec spec = new DbSpec();
       schema = spec.addDefaultSchema();
    }

    private QuerySet buildQuery(  Table<String,String,ReportField> tableMap ) {
        Iterator<Table.Cell<String,String,ReportField>> itr = tableMap.cellSet().iterator();      
        SelectQuery query = new SelectQuery();
        // add table with basic customer info
        DbTable table;
        Table.Cell<String,String,ReportField> cell;
        //field set for visible fields
        QuerySet<ReportField>  querySet = new QuerySet();
        ReportField field;
        while(itr.hasNext()){
            cell = itr.next();
            table = findSchemaTable(cell.getRowKey());
            DbColumn column = findSchemaTableColumn(table, cell.getColumnKey());
            //include only visible(true) fields in column selection 
            field = cell.getValue();
            if(cell.getValue().isVisible()){
              query.addColumns(column);
              //add field name to the fieldset  in the queryset instance
              querySet.addField(field);
            }
            //add the field query condition 
            createFieldQuery(column,cell.getValue().getFieldArguments() , query); 
        }
         querySet.setQueryString(query.validate().toString());
         return querySet;
    }

    private DbColumn findSchemaTableColumn(DbTable table, String columKey) {
        DbColumn  column = table.findColumn(columKey);
        if(column == null){
            column = table.addColumn(columKey);
        }
        return column;
    }

    private DbTable findSchemaTable(String tableName) {
        DbTable table;
        table = schema.findTable(tableName);
        if(table == null){
            table = schema.addTable(tableName);
        }
        return table;
    }
    /**
     * Create field arguments query conditions
     **/
    private void createFieldQuery( DbColumn column, Set<ReportArgumentField> fieldArguments , SelectQuery query) {
         ReportArgumentField fieldArg ;
         Condition condition;
         Iterator<ReportArgumentField> itr = fieldArguments.iterator();
         FieldValueType valueType;
         FieldOperator operator;
         while(itr.hasNext()){
            fieldArg = itr.next();
            valueType = fieldArg.getArgValue().getValueType();
            operator = fieldArg.getOperator();
            
            if(operator.equals(FieldOperator.JOIN)){
               addJoinQuery(query, column, fieldArg.getArgValue().getDataField());
             }
            else{
               //create condition
                if(valueType.equals(FieldValueType.CUSTOM_TYPE)){
                    DataFieldType fieldType = fieldArg.getFieldType();
                    Object value = customValueObject(fieldType,fieldArg.getArgValue().getCustomValue());
                    condition = createConditionQuery(column,fieldArg.getOperator(),value);
                }
                else{
                   DataField dField = fieldArg.getArgValue().getDataField();
                   DbTable table = findSchemaTable(dField.getModelName());
                   DbColumn coln = findSchemaTableColumn(table, dField.getName());
                   condition = createConditionQuery(column,fieldArg.getOperator(),coln);
                }

                query.addCondition(condition);
            }

         }
    }
    
    private Condition createConditionQuery(DbColumn column, FieldOperator operator, Object value){
                  switch(operator){
                     case EQUALS:
                          return BinaryCondition.equalTo(column, value);
                     case LESS_THAN:
                          return BinaryCondition.lessThan(column, value, true);
                     case GREATER_THAN:
                          return BinaryCondition.greaterThan(column, value, true);
                     case NOT_EQUALS:
                         return  BinaryCondition.notEqualTo(column, value);
                    case LIKE:
                         return  BinaryCondition.like(column, value);          
                     default:
                          return BinaryCondition.EMPTY;
                   
           }
    
    }
    //add join query on the query
    private void addJoinQuery(SelectQuery query, DbColumn fromColumn, DataField dField) {
                DbTable fromTable = fromColumn.getTable();
                DbTable toTable = findSchemaTable(dField.getModelName());
                DbColumn toColumn = findSchemaTableColumn( toTable, dField.getName());
                query.addJoin(SelectQuery.JoinType.INNER, fromTable, toTable, fromColumn, toColumn);
    }

    private Object customValueObject( DataFieldType fieldType, String value){
        try {
            if(fieldType.getType().equals(String.class.getCanonicalName())){
               return value;
            }
            Class clazz = Class.forName(fieldType.getType());
            Method method = clazz.getDeclaredMethod("valueOf", String.class);
            return method.invoke(clazz, value);
        } catch (ClassNotFoundException | 
                 NoSuchMethodException | 
                 SecurityException | 
                 IllegalAccessException |
                 IllegalArgumentException | 
                 InvocationTargetException ex) {
            Logger.getLogger(JdbcQueryParser.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    @Override
    public QuerySet parse(ReportComponent component) {
           Table<String,String,ReportField> table = createFieldTable(component.getFieldComponents());
           return buildQuery(table);
    }
    
}
