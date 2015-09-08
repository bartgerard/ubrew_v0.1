package be.gerard.ubrew.core.interface_v1.model.product;

import java.io.Serializable;
import java.util.Objects;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

/**
 * BeerType
 *
 * @author Bart Gerard
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "beerType")
public class BeerType implements Serializable {

    @XmlElement
    private Long id;

    @XmlElement(required = true)
    private String labelKey;

    @XmlElement
    private String description;

    // Min Percentage
    @XmlElement(required = true)
    private Integer alcoholMin;

    // Max Percentage
    @XmlElement(required = true)
    private Integer alcoholMax;

    @XmlElement(required = true)
    private Integer colorMin;

    @XmlElement(required = true)
    private Integer colorMax;

    @XmlElement(required = true)
    private Integer bitterMin;

    @XmlElement(required = true)
    private Integer bitterMax;

    public BeerType() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
    public int hashCode() {
        int hash = 7;
        hash = 73 * hash + Objects.hashCode(this.id);
        hash = 73 * hash + Objects.hashCode(this.description);
        hash = 73 * hash + Objects.hashCode(this.alcoholMin);
        hash = 73 * hash + Objects.hashCode(this.alcoholMax);
        hash = 73 * hash + Objects.hashCode(this.colorMin);
        hash = 73 * hash + Objects.hashCode(this.colorMax);
        hash = 73 * hash + Objects.hashCode(this.bitterMin);
        hash = 73 * hash + Objects.hashCode(this.bitterMax);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final BeerType other = (BeerType) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        if (!Objects.equals(this.labelKey, other.labelKey)) {
            return false;
        }
        if (!Objects.equals(this.description, other.description)) {
            return false;
        }
        if (!Objects.equals(this.alcoholMin, other.alcoholMin)) {
            return false;
        }
        if (!Objects.equals(this.alcoholMax, other.alcoholMax)) {
            return false;
        }
        if (!Objects.equals(this.colorMin, other.colorMin)) {
            return false;
        }
        if (!Objects.equals(this.colorMax, other.colorMax)) {
            return false;
        }
        if (!Objects.equals(this.bitterMin, other.bitterMin)) {
            return false;
        }
        return Objects.equals(this.bitterMax, other.bitterMax);
    }

    @Override
    public String toString() {
        return "BeerType{" + "id=" + id + ", labelKey=" + labelKey + ", description=" + description + ", alcoholMin=" + alcoholMin + ", alcoholMax=" + alcoholMax + ", colorMin=" + colorMin + ", colorMax=" + colorMax + ", bitterMin=" + bitterMin + ", bitterMax=" + bitterMax + '}';
    }

}
