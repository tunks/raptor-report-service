/**
 * Raptor Reporting service
 * A simple reporting service that enable users to design and generate web-based reports.
 * Built on top of the JasperReports - an open source reporting library
 * 2016 © ATT Service Assurance  - Raptor POC team
 *
 */
package com.att.raptor.report.engine.support;

import com.att.raptor.report.data.domain.ReportField;
import com.att.raptor.report.data.domain.ReportTemplate;
import java.util.Set;

/**
 * Template Handler
 * @author ebrimatunkara
 * @param <K>
 * @param <V>
 */
public interface TemplateHandler<K,V> {
    /** Generate template design and save report template design
     * @param template
     * @param fieldset **/
    public void  generate(ReportTemplate template, Set<ReportField> fieldset);
    /**
     * load report template design
     * @param key
     * @return 
     **/
    public  V  load(K key); 
}
