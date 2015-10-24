package be.gerard.game.service.model;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Promo
 *
 * @author bartgerard
 * @version v0.0.1
 */
@Entity
@Table(name = "bgc_game_promo")
@DiscriminatorValue("promo")
public class PromoRecord extends GameRecord {
}
