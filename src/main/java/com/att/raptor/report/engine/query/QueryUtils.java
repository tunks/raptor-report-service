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
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.apache.commons.lang3.RandomStringUtils;

/**
 * Query Utility class
 *
 * @author ebrimatunkara
 */
public final class QueryUtils {
    public static Map<String, Object> extractFromRs(ResultSet rs, Set fieldSet) throws SQLException {
        String name;
        Iterator<String> itr = fieldSet.iterator();
        Map<String, Object> map = new HashMap();
        while (itr.hasNext()) {
            name = itr.next();
            map.put(name, rs.getString(name));
        }
        return map;
    }

    public static List<?> extractListFromRs(ResultSet rs, Set fieldSet) throws SQLException {
        List list = new ArrayList();
        while (rs.next()) {
            list.add(extractFromRs(rs, fieldSet));
        }
        return list;
    }

    public static String collectionToString(Collection<String> collection) {
        if (collection == null) {
            return "";
        }

        StringBuilder builder = new StringBuilder();
        Iterator<String> itr = collection.iterator();
        String seperator = "";
        while (itr.hasNext()) {
            builder.append(seperator);
            builder.append(itr.next());
            seperator = ",";
        }
        return builder.toString();
    }

    public static String collectionToString(Collection<String> collection, int limit) {
        if (collection == null) {
            return "";
        }

        StringBuilder builder = new StringBuilder();
        Iterator<String> itr = collection.iterator();
        String seperator = "";
        int count = 0;
        while (itr.hasNext() && count < limit) {
            builder.append(seperator);
            builder.append(itr.next());
            seperator = ",";
            count++;
        }
        return builder.toString();
    }

    public static String random(int count, String text) {
        return RandomStringUtils.random(count, text);
    }

    /**
     * Create and return table alisa
     *
     * @param collection
     * @param count
     * @return 
       *
     */
    public static Map<String, String> createTableAlias(Collection<String> collection, int count) {
        Map<String, String> tableMap = new HashMap();
        Iterator<String> itr = collection.iterator();
        String table;
        while (itr.hasNext()) {
            table = itr.next();
            createTableAlias(tableMap, count, table);
        }
        return tableMap;
    }

    public static void createTableAlias(Map<String, String> tableMap, int count, String table) {
        String value = random(count, table);
        if (!tableMap.containsValue(value)) {
            tableMap.put(table, value);
        } else {
            createTableAlias(tableMap, count, table);
        }
    }

    /**
     * Query Set class 
     * @param <T>
     */
    public static class QuerySet<T> {
        private Set<T> fieldSet;
        private String queryString;

         public QuerySet() {
             this.fieldSet  =  new HashSet();
        }

        public QuerySet(Set<T> fieldSet, String query) {
             this.fieldSet  = fieldSet;
             this.queryString = query;
        }

        public Set<T> getFieldSet() {
            return fieldSet;
        }

        public void setFieldSet(Set<T> fieldSet) {
            this.fieldSet = fieldSet;
        }

        public String getQueryString() {
            return queryString;
        }

        public void setQueryString(String queryString) {
            this.queryString = queryString;
        }
        
        public void addField(T field){
           fieldSet.add(field);
        }
        
        public void removeField(T field){
           fieldSet.remove(field);
        }
    }
}
