package be.gerard.ubrew.service.model.product;

import be.gerard.common.db.model.BaseRecord;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * BeerTypeRecord
 *
 * @author Bart Gerard
 */
@Entity
@SequenceGenerator(name = BaseRecord.SEQUENCE_GENERATOR, sequenceName = "s_beer_type", allocationSize = BaseRecord.SEQUENCE_ALLOCATION_SIZE)
@Table(name = "ubrew_product_beer_type")
public class BeerTypeRecord extends BaseRecord {

    @Column(name = "labelKey", nullable = false)
    private String labelKey;

    @Column(name = "description", nullable = true)
    private String description;

    @Column(name = "alcohol_min", nullable = false, length = 3)
    private Integer alcoholMin;

    @Column(name = "alcohol_max", nullable = false, length = 3)
    private Integer alcoholMax;

    @Column(name = "color_min", nullable = false, length = 3)
    private Integer colorMin;

    @Column(name = "color_max", nullable = false, length = 3)
    private Integer colorMax;

    @Column(name = "bitter_min", nullable = false, length = 3)
    private Integer bitterMin;

    @Column(name = "bitter_max", nullable = false, length = 3)
    private Integer bitterMax;

    public BeerTypeRecord() {
    }

    public String getLabelKey() {
        return labelKey;
    }

    public void setLabelKey(String labelKey) {
        this.labelKey = labelKey;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getAlcoholMin() {
        return alcoholMin;
    }

    public void setAlcoholMin(Integer alcoholMin) {
        this.alcoholMin = alcoholMin;
    }

    public Integer getAlcoholMax() {
        return alcoholMax;
    }

    public void setAlcoholMax(Integer alcoholMax) {
        this.alcoholMax = alcoholMax;
    }

    public Integer getColorMin() {
        return colorMin;
    }

    public void setColorMin(Integer colorMin) {
        this.colorMin = colorMin;
    }

    public Integer getColorMax() {
        return colorMax;
    }

    public void setColorMax(Integer colorMax) {
        this.colorMax = colorMax;
    }

    public Integer getBitterMin() {
        return bitterMin;
    }

    public void setBitterMin(Integer bitterMin) {
        this.bitterMin = bitterMin;
    }

    public Integer getBitterMax() {
        return bitterMax;
    }

    public void setBitterMax(Integer bitterMax) {
        this.bitterMax = bitterMax;
    }

    @Override
    public String toString() {
        return "BeerType{" + "labelKey=" + labelKey + ", description=" + description + ", alcoholMin=" + alcoholMin + ", alcoholMax=" + alcoholMax + ", colorMin=" + colorMin + ", colorMax=" + colorMax + ", bitterMin=" + bitterMin + ", bitterMax=" + bitterMax + '}';
    }

}
