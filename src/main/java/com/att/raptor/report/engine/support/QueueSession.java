/**
 * Raptor Reporting service
 * A simple reporting service that enable users to design and generate web-based reports.
 * Built on top of the JasperReports - an open source reporting library
 * 2016 © ATT Service Assurance  - Raptor POC team
 *
 */
package com.att.raptor.report.engine.support;

import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.core.RedisOperations;
import org.springframework.data.redis.core.SessionCallback;

/**
 *
 * @author ebrimatunkara
 */
public class QueueSession implements SessionCallback{

    @Override
    public Object execute(RedisOperations operations) throws DataAccessException {
        operations.multi();
      
        //operations.boundListOps(this).getOperations().multi();
        //operat
        return null;
     }
    
}
