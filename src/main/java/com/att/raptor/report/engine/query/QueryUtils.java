/**
 * Raptor Reporting service
 * A simple reporing service that enable users to design and generate web-based reports.
 * Built on top of the JasperReports - an open source reporting library
 * 2016 © ATT Service Assurance  - Raptor POC team
 *
 */
package com.att.raptor.report.engine.query;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 *
 * @author ebrimatunkara
 */
public final class QueryUtils {
    public static Map<String,Object> extractArticleFromRs(ResultSet rs, Set fieldSet) throws SQLException {
           String name;
           Iterator<String> itr = fieldSet.iterator();
           Map<String,Object> map = new HashMap();
           while(itr.hasNext()){
               name =  itr.next();
               map.put(name,rs.getString(name));
           }
           return map;
    }
     
    public static List<?>  extractArticleListFromRs(ResultSet rs, Set fieldSet) throws SQLException {
        List list = new ArrayList();
        while(rs.next()) {                
            list.add(extractArticleFromRs(rs, fieldSet));
        }
        return list;
    }
}
