/**
 * Raptor Reporting service
 * A simple reporing service that enable users to design and generate web-based reports.
 * Built on top of the JasperReports - an open source reporting library
 * 2016 © ATT Service Assurance  - Raptor POC team
 *
 */
package com.att.raptor.report.data.service;

import com.att.raptor.report.data.domain.DataSource;
import com.att.raptor.report.data.domain.DataSourceModel;
import com.att.raptor.report.data.domain.DataSourceProperty;
import com.att.raptor.report.data.repositories.DataSourceRepository;
import com.att.raptor.report.engine.dao.JdbcDataDao;
import com.att.raptor.report.engine.query.callback.DbTableCallback;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import javax.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author ebrimatunkara
 */
@Service("datasourceService")
public class DataSourceService implements CrudBaseService<DataSource, String> {

    @Resource
    private DataSourceRepository dataSourceRepository;
    /**
     * TODO Database access
     */
    @Autowired
    private JdbcDataDao jdbcDatadao;

    /**
     * TODO --- Code refactoring
     *
     * @return 
     *
     */
    @Override
    public List<DataSource> findAll() {
        // return dataSourceRepository.findAll();
        List<DataSource> sources = new ArrayList();
        sources.add(mapDataSourceSchema());
        return sources;
    }

    @Override
    public DataSource find(String id) {
        return dataSourceRepository.findOne(id);
    }

    @Override
    public DataSource create(DataSource t) {
        return dataSourceRepository.save(t);
    }

    @Override
    public DataSource update(DataSource t) {
        return dataSourceRepository.update(t);
    }

    @Override
    public void delete(String id) {
        dataSourceRepository.delete(id);
    }

    /**
     * *
     * TODO Map JDBC local database schema into Data source repository collection
     *
     * @return
     */
    public DataSource mapDataSourceSchema() {
        DataSourceProperty property = new DataSourceProperty();
        property.setServerAddress("localhost");
        DataSource source = new DataSource("localhost", property);
        Set<DataSourceModel> models = jdbcDatadao.getModels(new DbTableCallback());
        source.setModels(models);
        return source;
    }

    @Override
    public List<DataSource> create(Collection<DataSource> t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
