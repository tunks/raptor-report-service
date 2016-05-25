/**
 * Raptor Reporting service
 * A simple reporing service that enable users to design and generate web-based reports.
 * Built on top of the JasperReports - an open source reporting library
 * 2016 © ATT Service Assurance  - Raptor POC team
 *
 */
package com.att.raptor.report.engine.support;

import com.att.raptor.report.data.domain.ReportField;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
/**
 * ReportFieldFunctor implementation 
 * @author ebrimatunkara
 */
public class ReportFieldFunctor implements Functor<Set<ReportField>> {
    private final FilterPredicate predicate;

    public ReportFieldFunctor(FilterPredicate predicate) {
        this.predicate = predicate;
    }

    @Override
    public Set<ReportField> process(Set<ReportField> object) {
        ReportField field;
        Set<ReportField> fields = new HashSet();
        Iterator<ReportField> itr = object.iterator();

        while (itr.hasNext()) {
            field = itr.next();
            if (predicate.filter(field)) {
                fields.add(field);
            }
        }
        return fields;
    }
}
