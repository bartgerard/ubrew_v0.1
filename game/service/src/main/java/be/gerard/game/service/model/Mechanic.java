package be.gerard.game.service.model;

import be.gerard.common.db.model.BaseRecord;
import be.gerard.common.db.model.DefinitionRecord;

import javax.persistence.Entity;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * Mechanic
 *
 * @author bartgerard
 * @version v0.0.1
 */
@Entity
@SequenceGenerator(name = BaseRecord.SEQUENCE_GENERATOR, sequenceName = "s_mechanic", allocationSize = BaseRecord.SEQUENCE_ALLOCATION_SIZE)
@Table(name = "bgc_mechanic")
public class Mechanic extends DefinitionRecord {

}
