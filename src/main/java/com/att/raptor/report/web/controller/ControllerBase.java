/*
 * Project name: Archive Rest service
 * A simple Rest service to archive data in MongoDB and index data in Apache Solr for  dynamic search queries 
 * 2016 © ATT Service Assurance  - Raptor POC team 
 *
 */
package com.att.raptor.report.web.controller;

import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

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
     **/
    public T create(T object);
    /**
     * Request method to update resource
     * @param object
     **/
    public T update(T object);
}
