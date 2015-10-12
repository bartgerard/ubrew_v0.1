package be.gerard.game.service.model;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Expansion
 *
 * @author bartgerard
 * @version v0.0.1
 */
@Entity
@Table(name = "bgc_game_expansion")
@DiscriminatorValue("expansion")
public class Expansion extends Game {
}
