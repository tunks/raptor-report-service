/**
 * Raptor Reporting service
 * A simple reporting service that enable users to design and generate web-based reports.
 * Built on top of the JasperReports - an open source reporting library
 * 2016 © ATT Service Assurance  - Raptor POC team
 */
package com.att.raptor.report.engine.query;

import com.att.raptor.report.data.domain.ReportField;
import com.att.raptor.report.engine.query.QueryUtils.QuerySet;
import com.att.raptor.report.engine.query.callback.DbQueryCallback;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import org.springframework.jdbc.core.PreparedStatementCallback;

/**
 *
 * @author ebrimatunkara
 */
public class JdbcQueryFactory  implements QueryFactory<PreparedStatementCallback,Set<String>>{
    @Override
    public PreparedStatementCallback createQueryCallback(Set<String> fieldSet) {
      return new DbQueryCallback(fieldSet);
    }
    
    public static PreparedStatementCallback createNewQueryCallback(QuerySet<ReportField> querySet){
      Set<String> fieldSet = createResultFieldSet(querySet);
      return  new JdbcQueryFactory().createQueryCallback(fieldSet);
    }
    
    public static Set<String> createResultFieldSet(QuerySet<ReportField> querySet){
         Set<String> fieldSet = new HashSet();
      Iterator<ReportField> itr = querySet.getFieldSet().iterator();
      while(itr.hasNext()){
        fieldSet.add(itr.next().getName());
      }
      return fieldSet;
    }
    
}
