/**
 * Raptor Reporting service
 * A simple reporing service that enable users to design and generate web-based reports.
 * Built on top of the JasperReports - an open source reporting library
 * 2016 © ATT Service Assurance  - Raptor POC team
 *
 */
package com.att.raptor.report.engine.query.callback;

import com.att.raptor.report.engine.query.QueryUtils;
import com.att.raptor.report.engine.support.ProducerStream;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.jdbc.core.support.AbstractLobStreamingResultSetExtractor;
import org.springframework.jdbc.support.lob.LobHandler;
import org.springframework.util.StopWatch;

/**
 *
 * @author ebrimatunkara
 */
public class StreamingResultSetExtractor  extends AbstractLobStreamingResultSetExtractor {
    private final LobHandler lobHandler = null;
    private final ProducerStream queueStream;
    private final Set<String> fieldSet;

    public StreamingResultSetExtractor(ProducerStream queueStream, Set<String> fieldSet) {
        this.queueStream = queueStream;
        this.fieldSet = fieldSet;
    }
   
    @Override
    protected void streamData(ResultSet rs) throws SQLException, IOException, DataAccessException {
       StopWatch w1 = new StopWatch();
       //rs.setFetchSize(1000);
       //rs.g
       w1.start();
       List<Map<String,Object>> results = QueryUtils.extractListFromRs(rs, fieldSet);
       w1.stop();
       System.out.println("ExtraList Time :"+w1.getTotalTimeMillis());
       int count = 0;
//       w1.start();
//       Iterator<Map<String,Object>> itr = results.iterator();
//       while(itr.hasNext()){
//           //queueStream.push(itr.next());
//           count++;
//       }
//       w1.stop();
       //System.out.println("After queue Time :"+w1.getTotalTimeMillis());
       System.out.println("Row size "+ results.size());
       System.out.println("-------------------------------");
    }   
}
