/**
 * Raptor Reporting service
 * A simple reporing service that enable users to design and generate web-based reports.
 * Built on top of the JasperReports - an open source reporting library
 * 2016 © ATT Service Assurance  - Raptor POC team
 *
 */
package com.att.raptor.report.engine.service;

import ar.com.fdvs.dj.core.DynamicJasperHelper;
import ar.com.fdvs.dj.core.layout.ClassicLayoutManager;
import ar.com.fdvs.dj.domain.DynamicReport;
import ar.com.fdvs.dj.domain.builders.ColumnBuilderException;
import ar.com.fdvs.dj.domain.builders.FastReportBuilder;
import com.att.raptor.report.data.domain.ReportTemplate;
import com.att.raptor.report.engine.query.QueryHandler;
import com.att.raptor.report.engine.support.IGenerator;
import com.att.raptor.report.engine.support.JasperReportBuilder;
import com.att.raptor.report.engine.support.JasperReportFactory;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * ReportGenerator implementation
 *
 * @author ebrimatunkara
 */
@Service("jasperReportService")
public class JasperReportService implements ReportService {
    @Autowired
    private ReportQueryService reportQueryService;
    private final JasperReportFactory factory = JasperReportFactory.CreateFactory();

    @Override
    public Object generate(String type, QueryHandler handler) {
        try {
            ReportBuilder builder = new ReportBuilder();
            List result = reportQueryService.query(handler); //TestRepositoryProducts.getDummyCollection();//
            JasperPrint jsPrinter =  builder.build(handler.getTemplate(), result);
            IGenerator generator = factory.createGenerator(jsPrinter, type);
            return generator.generate();
        } catch (JRException ex) {
            Logger.getLogger(JasperReportService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    private class ReportBuilder {
        JasperReportBuilder  builder;
       // FastReportBuilder builder;
        private JasperReport jreport;
        private Map params = new HashMap();

        public ReportBuilder() {
            builder = new JasperReportBuilder();
        }

        public JasperPrint build(ReportTemplate template, List data) throws JRException {
            try {
                DynamicReport report =  builder.prepare(template);
                jreport = DynamicJasperHelper.generateJasperReport(report, new ClassicLayoutManager(), params);
                return JasperFillManager.fillReport(jreport, params, prepareDataSource(data));
            } catch (ColumnBuilderException ex) {
                Logger.getLogger(JasperReportService.class.getName()).log(Level.SEVERE, null, ex);
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

    }
}
