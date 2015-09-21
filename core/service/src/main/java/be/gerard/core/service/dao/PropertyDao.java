package be.gerard.core.service.dao;

import be.gerard.core.service.model.PropertyRecord;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.Repository;

/**
 * ApplicationDao
 *
 * @author bartgerard
 * @version 0.0.1
 */
public interface PropertyDao extends Repository<PropertyRecord, Long>, JpaSpecificationExecutor<PropertyRecord> {

}
