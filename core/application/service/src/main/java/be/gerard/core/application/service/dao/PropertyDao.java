package be.gerard.core.application.service.dao;

import be.gerard.core.application.service.model.PropertyRecord;
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
