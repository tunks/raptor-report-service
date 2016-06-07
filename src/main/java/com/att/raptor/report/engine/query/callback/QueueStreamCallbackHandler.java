package com.att.raptor.report.engine.query.callback;


import com.att.raptor.report.engine.query.QueryUtils;
import com.att.raptor.report.engine.support.ProducerStream;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.atomic.AtomicLong;
import org.springframework.jdbc.core.RowCallbackHandler;

public class QueueStreamCallbackHandler  implements RowCallbackHandler {
    private final ProducerStream queueStream;
    private final Set<String> fieldSet;
    private final AtomicLong  count = new AtomicLong();
    private int batchSize = 3000; //default batch size is 3000
    private List<Map<String,Object>> batchList = new ArrayList( batchSize);
    public QueueStreamCallbackHandler(ProducerStream queueStream, Set<String> fieldSet) {
        this.queueStream = queueStream;
        this.fieldSet = fieldSet;
    }
    
    public QueueStreamCallbackHandler(ProducerStream queueStream, Set<String> fieldSet , int batchSize) {
        this.queueStream = queueStream;
        this.fieldSet = fieldSet;
        this.batchSize = batchSize;
    }  

    @Override
    public void processRow(ResultSet rs) throws SQLException {
          //StopWatch watch = new StopWatch();
          //watch.start();
          Map<String,Object>  data  = QueryUtils.extractFromRs(rs, fieldSet);
          count.incrementAndGet();
          //watch.stop();
          batchList.add(data);
          if(count.intValue()%batchSize == 0){
             queueStream.push(batchList);
             //System.out.println("Process batch row: "+data.values());
             batchList = new ArrayList(batchSize);
          }
    
           //queueStream.push(data);
             
         
    }
}