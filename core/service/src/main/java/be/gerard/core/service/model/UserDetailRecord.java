package be.gerard.core.service.model;

import be.gerard.common.db.converter.LocalDatePersistenceConverter;
import be.gerard.common.db.model.BaseRecord;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * UserDetailRecord
 *
 * @author bartgerard
 * @version v0.0.1
 */
@Entity
@SequenceGenerator(name = BaseRecord.SEQUENCE_GENERATOR, sequenceName = "s_user_detail", allocationSize = BaseRecord.SEQUENCE_ALLOCATION_SIZE)
@Table(name = "core_user_detail")
public class UserDetailRecord extends BaseRecord {

}
