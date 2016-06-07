/**
 * Raptor Reporting service
 * A simple reporing service that enable users to design and generate web-based reports.
 * Built on top of the JasperReports - an open source reporting library
 * 2016 © ATT Service Assurance  - Raptor POC team
 *
 */
package com.att.raptor.report.data.support;

import java.sql.Types;

/**
 * DataFieldType enum -- Sql data type mapping to Java data types
 * @author ebrimatunkara
 */
public enum DataFieldType {
    BIGINT(Types.BIGINT, "java.lang.Long"),
    BIT(Types.BIT, "java.lang.Boolean"),
    CHAR(Types.CHAR,"java.lang.String"),
    DATE(Types.DATE, "java.util.Date"),
    DECIMAL(Types.DECIMAL, "java.lang.Double"),
    FLOAT(Types.REAL, "java.lang.Float"),
    INTEGER(Types.INTEGER, "java.lang.Integer"),
    SMALLINT(Types.SMALLINT, "java.lang.Integer"),
    STRING(Types.VARCHAR, "java.lang.String"),
    TEXT(Types.LONGVARCHAR, "java.lang.String"),
    TIME(Types.TIME,"java.sql.Time"),
    TIMESTAMP(Types.TIMESTAMP,"java.sql.Timestamp"),
    TINYINT(Types.TINYINT,"java.lang.Integer");
   
    private final int index;
    private final String type;

    DataFieldType(int index, String type) {
        this.index = index;
        this.type = type;
    }

    public int getIndex() {
        return index;
    }

    public String getType() {
        return type;
    }

    public static DataFieldType getDataFieldType(int index) {
        for (DataFieldType dType : DataFieldType.values()) {
            if (index == dType.getIndex()) {
                return dType;
            }
        }
        return null;
    }

    @Override
    public String toString() {
        return "DataFieldType{" + "index=" + index + ", type=" + type + '}';
    }
}
