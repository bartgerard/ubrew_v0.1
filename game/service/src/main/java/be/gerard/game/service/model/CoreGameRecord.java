package be.gerard.game.service.model;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * CoreGame
 *
 * @author bartgerard
 * @version v0.0.1
 */
@Entity
@Table(name = "bgc_game_core")
@DiscriminatorValue("core")
public class CoreGameRecord extends GameRecord {
/*
    private final Set<Expansion> expansions = new HashSet<>();

    private final Set<Promo> promos = new HashSet<>();

    public Set<Expansion> getExpansions() {
        return expansions;
    }

    public Set<Promo> getPromos() {
        return promos;
    }
*/
}
