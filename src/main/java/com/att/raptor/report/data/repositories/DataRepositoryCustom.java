/**
 * Raptor Reporting service
 * A simple reporing service that enable users to design and generate web-based reports.
 * Built on top of the JasperReports - an open source reporting library 
 * 2016 © ATT Service Assurance  - Raptor POC team 
 *
 */
package com.att.raptor.report.data.repositories;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.repository.NoRepositoryBean;

/**
 * @author ebrimatunkara
 *  Custom repository
 * @param <T>
 * @param <ID>
 */
@NoRepositoryBean
public interface DataRepositoryCustom<T, ID extends Serializable>{
    public T update(T object);
    public List<T>updateList(List<T> objects);
}

