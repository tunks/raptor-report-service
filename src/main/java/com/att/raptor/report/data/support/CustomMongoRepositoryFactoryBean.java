/**
 * Raptor Reporting service
 * A simple reporing service that enable users to design and generate web-based reports.
 * Built on top of the JasperReports - an open source reporting library 
 * 2016 © ATT Service Assurance  - Raptor POC team 
 *
 */
package com.att.raptor.report.data.support;

import java.io.Serializable;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.repository.query.MongoEntityInformation;
import org.springframework.data.mongodb.repository.support.MongoRepositoryFactory;
import org.springframework.data.mongodb.repository.support.MongoRepositoryFactoryBean;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.core.RepositoryInformation;
import org.springframework.data.repository.core.RepositoryMetadata;
import org.springframework.data.repository.core.support.RepositoryFactorySupport;

import com.att.raptor.report.data.repositories.DataRepositoryImpl;

/**
 * CustomMongoRepositoryFactoryBean -- instantiates the DataRepositoryImpl class
 * @author ebrimatunkara
 * @param <T>
 * @param <S>
 * @param <ID>
 */
@NoRepositoryBean
public class CustomMongoRepositoryFactoryBean<T extends Repository<S, ID>, S, ID extends Serializable> extends MongoRepositoryFactoryBean<T,S,ID>{

    @Override
    protected RepositoryFactorySupport getFactoryInstance(MongoOperations operations) {
        return  new CustomMongoRepositoryFactory(operations);
    }
   
    private static class CustomMongoRepositoryFactory<T,ID extends Serializable> extends MongoRepositoryFactory{
        private final MongoOperations mongoOperations;
        
        public CustomMongoRepositoryFactory(MongoOperations mongoOperations) {
            super(mongoOperations);
            this.mongoOperations = mongoOperations;
        }

        @Override
        protected Object getTargetRepository(RepositoryInformation information) {
            MongoEntityInformation mongoMeta = getEntityInformation(information.getDomainType());
            return new DataRepositoryImpl<>(mongoMeta,mongoOperations);
        }

        @Override
        protected Class<?> getRepositoryBaseClass(RepositoryMetadata metadata) {
            return DataRepositoryImpl.class;
        }
    }
}
