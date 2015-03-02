package be.gerard.core.service.dao;

import be.gerard.core.service.model.PropertyRecord;
import org.springframework.data.repository.Repository;

import java.util.List;

/**
 * ApplicationDao
 *
 * @author bartgerard
 * @version 0.0.1
 */
public interface PropertyDao extends Repository<PropertyRecord, Long> {

    List<PropertyRecord> findAll();

}
