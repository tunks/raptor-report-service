/**
 * Raptor Reporting service
 * A simple reporing service that enable users to design and generate web-based reports.
 * Built on top of the JasperReports - an open source reporting library
 * 2016 © ATT Service Assurance  - Raptor POC team
 *
 */
package com.att.raptor.report.engine.query.callback;

import com.att.raptor.report.data.domain.DataField;
import com.att.raptor.report.data.domain.DataSourceModel;
import com.att.raptor.report.data.support.DataFieldType;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;
import org.springframework.jdbc.support.DatabaseMetaDataCallback;

/**
 * Database tables callback
 * @author ebrimatunkara
 */
public class DbTableCallback implements DatabaseMetaDataCallback {

        @Override
        public Object processMetaData(DatabaseMetaData dbmd) throws SQLException {
            String catalog = dbmd.getConnection().getCatalog();
            ResultSet rs = dbmd.getTables(catalog, null, "%", null);
            String tableName;
            Set<DataSourceModel> models = new HashSet();
            while (rs.next()) {
                tableName = rs.getString(3);
                Set<DataField> fields = getColumns(dbmd.getColumns(catalog, tableName, tableName, null));
                models.add(new DataSourceModel(null, tableName, fields));
            }
            return models;
        }

        private Set getColumns(ResultSet rs) throws SQLException {
            int typeIndex;
            String columnName;
            DataFieldType dataType;
            Set<DataField> fields = new HashSet();
            while (rs.next()) {
                columnName = rs.getString(4);
                typeIndex = Integer.parseInt(rs.getString(5));
                dataType = DataFieldType.getDataFieldType(typeIndex);
                fields.add(new DataField(null, columnName, dataType));
            }
            return fields;
        }
    }