/**
 * Raptor Reporting service
 * A simple reporing service that enable users to design and generate web-based reports.
 * Built on top of the JasperReports - an open source reporting library
 * 2016 © ATT Service Assurance  - Raptor POC team
 *
 */
package com.att.raptor.report.engine.support;

import ar.com.fdvs.dj.core.DynamicJasperHelper;
import ar.com.fdvs.dj.core.layout.ClassicLayoutManager;
import ar.com.fdvs.dj.domain.DynamicReport;
import ar.com.fdvs.dj.domain.builders.ColumnBuilder;
import ar.com.fdvs.dj.domain.builders.ColumnBuilderException;
import ar.com.fdvs.dj.domain.builders.FastReportBuilder;
import ar.com.fdvs.dj.domain.entities.columns.AbstractColumn;
import com.att.raptor.report.data.domain.ReportComponent;
import com.att.raptor.report.data.domain.ReportField;
import com.att.raptor.report.data.domain.ReportTemplate;
import com.att.raptor.report.data.support.DataFieldType;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

/**
 * @author ebrimatunkara
 * JasperReportBuilder implementation -- implements ReportBuilder
 */
public class JasperReportBuilder implements ReportBuilder<JasperPrint> {
    private final FastReportBuilder builder;
    private JasperReport jreport;
    private Map params = new HashMap();

    public JasperReportBuilder() {
        builder = new FastReportBuilder();
    }

    @Override
    public JasperPrint build(ReportTemplate template, List data) {
        try {
            DynamicReport report = prepare(template);
            jreport = DynamicJasperHelper.generateJasperReport(report, new ClassicLayoutManager(), params);
            return JasperFillManager.fillReport(jreport, params, prepareDataSource(data));
        } catch (JRException | ColumnBuilderException ex) {
            Logger.getLogger(JasperReportBuilder.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    private JRDataSource prepareDataSource(List data) {
        return new JRBeanCollectionDataSource(data);
    }

    public Map getParams() {
        return params;
    }

    public void setParams(Map params) {
        this.params = params;
    }

    public DynamicReport prepare(ReportTemplate template) {
        DynamicReport report = builder.setReportName(template.getName())
                //.setIgnorePagination(true)
                .setMargins(10, 10, 10, 10)
                .setUseFullPageWidth(true)
                .setSubtitle("This report was generated at " + new Date())
                .setIgnorePagination(false)
                .build();
        ReportColumns(report, template.getComponents());
        return report;
    }

    public void ReportColumns(DynamicReport report, Set<ReportComponent> components) {
        if (components == null) {
            return;
        }

        List<AbstractColumn> columns;
        for (ReportComponent component : components) {
            columns = createColumns(component.getFieldComponents());
            report.setColumns(columns);
        }
    }

    protected List<AbstractColumn> createColumns(Set<ReportField> fields) {
        List<AbstractColumn> columns = new ArrayList();
        for (ReportField field : fields) {
            columns.add(createColumn(field));
        }
        return columns;
    }

    private AbstractColumn createColumn(ReportField field) {
        String name = field.getName();
        DataFieldType dfieldType = field.getFieldType();
        String fType;
        if (dfieldType == null) {
            fType = String.class.getName();
        } else {
            fType = dfieldType.getType();
        }
        ColumnBuilder cBuilder = ColumnBuilder.getNew();
        cBuilder.setColumnProperty(field.getName(), fType);
        cBuilder.setTitle(columnTitle(name));
        return cBuilder.build();
    }

    private String columnTitle(String fieldName) {
        return fieldName;
    }

}
