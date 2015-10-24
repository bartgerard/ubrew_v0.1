package be.gerard.game.service.model;

import javax.persistence.Column;

/**
 * BoardGame
 *
 * @author bartgerard
 * @version v0.0.1
 */
public abstract class GameRecord extends ProductDefinitionRecord {

    public GameRecord() {
    }

    public GameRecord(String key, Integer bggId, String name, PriceRecord msrp) {
        super(key, name, msrp);
        this.bggId = bggId;
    }

    @Column(name = "bgg_id")
    private Integer bggId;

    public Integer getBggId() {
        return bggId;
    }

    public void setBggId(Integer bggId) {
        this.bggId = bggId;
    }

}
