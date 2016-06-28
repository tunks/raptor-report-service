/**
 * Raptor Reporting service
 * A simple reporting service that enable users to design and generate web-based reports.
 * Built on top of the JasperReports - an open source reporting library
 * 2016 © ATT Service Assurance  - Raptor POC team
 *
 */
package com.att.raptor.report.engine.support;

import ar.com.fdvs.dj.core.DynamicJasperHelper;
import ar.com.fdvs.dj.core.layout.ClassicLayoutManager;
import ar.com.fdvs.dj.domain.DynamicReport;
import ar.com.fdvs.dj.domain.Style;
import ar.com.fdvs.dj.domain.builders.ColumnBuilder;
import ar.com.fdvs.dj.domain.builders.ColumnBuilderException;
import ar.com.fdvs.dj.domain.builders.FastReportBuilder;
import ar.com.fdvs.dj.domain.constants.Border;
import ar.com.fdvs.dj.domain.constants.Font;
import ar.com.fdvs.dj.domain.constants.HorizontalAlign;
import ar.com.fdvs.dj.domain.constants.VerticalAlign;
import ar.com.fdvs.dj.domain.entities.columns.AbstractColumn;
import com.att.raptor.report.data.domain.ReportField;
import com.att.raptor.report.data.domain.ReportTemplate;
import com.att.raptor.report.data.support.DataFieldType;
import java.awt.Color;
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
 * @author ebrimatunkara JasperReportBuilder implementation 
 * ReportBuilder
 */
public class JasperReportBuilder implements ReportBuilder<JasperPrint, Set<ReportField>> {
    private final FastReportBuilder builder;
    private JasperReport jreport;
    private Map params = new HashMap();
    private String directory;
    public JasperReportBuilder() {
        builder = new FastReportBuilder();
    }

    public JasperReportBuilder(String directory) {
        this.directory = directory;
        builder = new FastReportBuilder();
    }
    
    @Override
    public JasperPrint build(ReportTemplate template, List data) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public JasperPrint build(ReportTemplate template, List<Map<?,?>> data, Set<ReportField> fieldset) {
        try {
            //DynamicJasperHelper       
            DynamicReport report = prepare(template, fieldset);
            jreport = DynamicJasperHelper.generateJasperReport(report, new ClassicLayoutManager(), params);
             //JasperFillManacger.fillReportToStream(inputStream, outputStream, params, dataSource);
            return JasperFillManager.fillReport(jreport, params, prepareDataSource(data));
        } catch (JRException | ColumnBuilderException ex) {
            Logger.getLogger(JasperReportBuilder.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    
    @Override
    public JasperPrint build(ReportTemplate template, JRDataSource datasource, Set<ReportField> fieldset) {
       try {
            DynamicReport report = prepare(template, fieldset);
            jreport = DynamicJasperHelper.generateJasperReport(report, new ClassicLayoutManager(), params);
            return JasperFillManager.fillReport(jreport, params, datasource);
        } catch (JRException | ColumnBuilderException ex) {
            Logger.getLogger(JasperReportBuilder.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    /**
     * Generate template designer
     * @param template
     * @param fieldset
     **/
    @Override
    public void generate(ReportTemplate template, Set<ReportField> fieldset) {
        try {
            DynamicReport report = prepare(template, fieldset);
            jreport = DynamicJasperHelper.generateJasperReport(report, new ClassicLayoutManager(), params);
            String filename = directory +"/templates/"+template.getId()+ ".jrxml";
            DynamicJasperHelper.generateJRXML(jreport, DynamicJasperHelper.DEFAULT_XML_ENCODING, filename);

        } catch (JRException ex) {
            Logger.getLogger(JasperReportBuilder.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private JRDataSource prepareDataSource(List data) {
        //JRResultSetDataSource s;
        return new JRBeanCollectionDataSource(data);
    }

    public Map getParams() {
        return params;
    }

    public void setParams(Map params) {
        this.params = params;
    }
    public void addParams( String property, Object value){
        this.params.put(property, value);
    }

    public DynamicReport prepare(ReportTemplate template, Set<ReportField> fieldset) {
        DynamicReport report = builder.setReportName(template.getName())
                //.setIgnorePagination(true)
                .setMargins(10, 10, 10, 10)
                .setUseFullPageWidth(true)
                .setSubtitle("This report was generated at " + new Date())
                .setIgnorePagination(false)
                
                .build();

        //append report columns
        appendColumns(fieldset);
        return report;
    }

    private void appendColumns(Set<ReportField> fields) {
        // List<AbstractColumn> columns = new ArrayList();
        for (ReportField field : fields) {
            //  columns.add(createColumn(field));
            builder.addColumn(createColumn(field));
        }
        //report.setColumns(columns);
    }

    private AbstractColumn createColumn(ReportField field) {
        String name = field.getName();
        DataFieldType dfieldType = field.getFieldType();
        String fieldType = (dfieldType == null) ? String.class.getName() : dfieldType.getType();
        ColumnBuilder cBuilder = ColumnBuilder.getNew();
        cBuilder.setColumnProperty(name, fieldType);
        cBuilder.setTitle(columnTitle(field));
        cBuilder.setHeaderStyle(defineTitleStyle());
        return cBuilder.build();
    }

    private Style defineTitleStyle() {
        Style style = new Style();
        style.setFont(Font.ARIAL_MEDIUM_BOLD);
        style.setBorderBottom(Border.THIN());
        style.setVerticalAlign(VerticalAlign.MIDDLE);
        style.setBackgroundColor(Color.LIGHT_GRAY);
        //style.setTransparency(Transparency.OPAQUE); //to show background color
        style.setHorizontalAlign(HorizontalAlign.LEFT);
        return style;
    }

    /**
     * *
     * Define column title Use the field label if it is available else use the
     * field name
     **
     */
    private String columnTitle(ReportField field) {
        return (field.getLabel() != null && !field.getLabel().isEmpty()) ? field.getLabel() : field.getName();
    }

    public void addParams(Map params) {
         this.params.putAll(params);
    }
}
