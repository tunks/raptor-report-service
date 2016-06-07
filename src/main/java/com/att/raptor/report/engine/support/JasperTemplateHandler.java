/**
 * Raptor Reporting service
 * A simple reporting service that enable users to design and generate web-based reports.
 * Built on top of the JasperReports - an open source reporting library
 * 2016 © ATT Service Assurance  - Raptor POC team
 *
 */
package com.att.raptor.report.engine.support;

import ar.com.fdvs.dj.core.DynamicJasperHelper;
import com.att.raptor.report.data.domain.ReportField;
import com.att.raptor.report.data.domain.ReportTemplate;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisOperations;

/**
 * Jasper Template Handler
 * @author ebrimatunkara
 */
public class JasperTemplateHandler implements TemplateHandler<String,String> {
    @Autowired
    private RedisOperations<String,String> redisTemplate;
    private String templateKey;
    private String directory;
    
    @Override
    public String load(String key) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void generate(ReportTemplate template, Set<ReportField> fieldset) {
             JasperReportBuilder  builder = new  JasperReportBuilder(directory);
             builder.generate(template, fieldset);
    }
}
