/**
 * Raptor Reporting service
 * A simple reporing service that enable users to design and generate web-based reports.
 * Built on top of the JasperReports - an open source reporting library
 * 2016 © ATT Service Assurance  - Raptor POC team
 *
 */
package com.att.raptor.report.engine.support;

import java.util.Deque;

/**
 *
 * @author ebrimatunkara
 * @param <T>
 */
public class ResultSetDataStream<T> implements ProducerStream<T>,ConsumerStream<T> {
    private final Deque<T> queue;  
    private final boolean complete = true;
    //private final StopWatch watch = new StopWatch();

    public ResultSetDataStream(Deque<T> queue) {
        this.queue = queue;
    }
    
    @Override
    public boolean isComplete() {
         return complete;
    }

    @Override
    public void push(T object) {
       this.queue.push(object);
    }

    @Override
    public T poll() {
        return this.queue.poll();
    }

}
