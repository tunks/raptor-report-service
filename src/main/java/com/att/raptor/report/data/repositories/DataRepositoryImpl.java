/**
 * Raptor Reporting service
 * A simple reporing service that enable users to design and generate web-based reports.
 * Built on top of the JasperReports - an open source reporting library
 * 2016 © ATT Service Assurance  - Raptor POC team
 *
 */
package com.att.raptor.report.data.repositories;

import java.io.Serializable;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;

import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.data.mongodb.repository.query.MongoEntityInformation;
import org.springframework.data.mongodb.repository.support.SimpleMongoRepository;

import com.mongodb.BasicDBObject;
import org.springframework.data.mongodb.core.FindAndModifyOptions;

/**
 * @author ebrimatunkara
 *
 */
@SuppressWarnings("rawtypes")
public class DataRepositoryImpl<T, ID extends Serializable>
        extends SimpleMongoRepository implements DataRepositoryCustom<T, ID> {

    /**
     * @param metadata
     * @param mongoOperations
     */
    private MongoOperations mongoOperations;
    private MongoEntityInformation metadata;

    @SuppressWarnings("unchecked")
    public DataRepositoryImpl(MongoEntityInformation metadata, MongoOperations mongoOperations) {
        super(metadata, mongoOperations);
        this.mongoOperations = mongoOperations;
        this.metadata = metadata;
    }

    /* (non-Javadoc)
	 * Update existing object based on the id
	 * @see com.att.raptor.report.data.repositories.DataRepositoryCustom#update(java.lang.Object)
     */
    @Override
    public T update(T object) {
        return findAndUpdate(object);
    }


    /* (non-Javadoc)
	 * Update existing list of objects
	 * @see com.att.raptor.report.data.repositories.DataRepositoryCustom#update(java.util.List)
     */
    @Override
    public List<T> updateList(List<T> objects) {
        // TODO Auto-generated method stub
        return null;
    }

    @SuppressWarnings("unchecked")
    private T findAndUpdate(T object) {
        BasicDBObject tmp = new BasicDBObject();
        mongoOperations.getConverter().write(object, tmp);
        Query query = new Query(Criteria.where("_id").is(tmp.get("_id")));
        Update update = createUpdate(object, tmp);
        FindAndModifyOptions options = FindAndModifyOptions.options().returnNew(true);
        return (T) mongoOperations.findAndModify(query, update, options, object.getClass(), this.metadata.getCollectionName());
    }

    private Update createUpdate(T object, BasicDBObject tmp) {
        Update update = new Update();
        Iterator<Entry<String, Object>> itr = tmp.entrySet().iterator();
        Entry<String, Object> entry;
        while (itr.hasNext()) {
            entry = itr.next();
            if (!entry.getKey().equals("_id")) {
                update.set(entry.getKey(), entry.getValue());
            }
        }
        return update;
    }

}
