package be.gerard.core.service.dao;

import be.gerard.core.service.model.PropertyRecord;

import java.util.List;

/**
 * ApplicationDao
 *
 * @author bartgerard
 * @version 0.0.1
 */
public interface CustomPropertyDao {

    PropertyRecord findOne(Long id);

    List<PropertyRecord> findAllForApp(final String app);

}
