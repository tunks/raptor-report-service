/**
 * Raptor Reporting service
 * A simple reporting service that enable users to design and generate web-based reports.
 * Built on top of the JasperReports - an open source reporting library
 * 2016 © ATT Service Assurance  - Raptor POC team
 *
 */
package com.att.raptor.report.web.controller;

import org.springframework.web.bind.annotation.PathVariable;

/**
 * ControllerBase abstract class 
 * @author ebrimatunkara
 * @param <T>
 */
public interface ControllerBase<T> {
   
    /**
     * Request method to find and return a resource by its id
     * @param id
     * @return 
     **/
    public T find(@PathVariable String id);
    /**
     * Request method to delete all resources
     **/

    public void deleteAll();
    /**
     * Request method to delete a resource by its id
     * @param id
     **/
    public void delete(@PathVariable String id);
    /**
     * Request method to create resource  
     * @param object
     * @return 
     **/
    public T create(T object);
    /**
     * Request method to update resource
     * @param object
     * @return 
     **/
    public T update(T object);
}
