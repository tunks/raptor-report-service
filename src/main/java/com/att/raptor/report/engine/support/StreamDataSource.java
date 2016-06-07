/**
 * Raptor Reporting service
 * A simple reporing service that enable users to design and generate web-based reports.
 * Built on top of the JasperReports - an open source reporting library
 * 2016 © ATT Service Assurance  - Raptor POC team
 *
 */
package com.att.raptor.report.engine.support;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRField;
import net.sf.jasperreports.engine.data.JRAbstractBeanDataSource;

/**
 *
 * @author ebrimatunkara
 * @param <T>
 */
public class StreamDataSource<T> extends JRAbstractBeanDataSource {
    private final ConsumerStream<List<T>> stream;
    private Collection<T> batchList;
    private Iterator<T> iterator;
    private T current;

    public StreamDataSource(ConsumerStream stream) {
        super(true);
        this.stream = stream;
    }

    @Override
    public boolean next() throws JRException {
        boolean hasNext = false;

        if (this.iterator != null) {
            hasNext = this.iterator.hasNext();

            if (hasNext) {
                this.current = this.iterator.next();
            } else {
                 moveFirst();
                 hasNext = iterator.hasNext();
                 if(hasNext){
                   this.current = iterator.next();
                 }
            }
        }
        else{
           batchList =  stream.poll();
          if (batchList != null) {
             this.iterator = batchList.iterator();
             hasNext= this.iterator.hasNext();
             if(hasNext){
               current = this.iterator.next();
             }
           }
        }

        return hasNext;
//       current  = stream.poll();
//       
//      
//       
//       if(current != null){
//         return true;
//       }
//       else{
//         return false;
//       }
    }

    @Override
    public Object getFieldValue(JRField jrField) throws JRException {
        return this.getFieldValue(current, jrField);
    }

    @Override
    public void moveFirst() throws JRException {
        batchList =  stream.poll();
        if (batchList != null) {
            this.iterator = batchList.iterator();
        }
          //current =  stream.poll();
    }

}
