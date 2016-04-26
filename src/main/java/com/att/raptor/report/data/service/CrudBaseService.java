/**
 * Raptor Reporting service
 * A simple reporing service that enable users to design and generate web-based reports.
 * Built on top of the JasperReports - an open source reporting library
 * 2016 © ATT Service Assurance  - Raptor POC team
 *
 */
package com.att.raptor.report.data.service;

import java.util.List;

/**
 *
 * @author ebrimatunkara
 * @param <T>
 * @param <ID>
 */
public interface CrudBaseService<T,ID> {
    public List<T> findAll();
    public T find(ID id);
    public T create(T t);
    public T update(T t);
}
