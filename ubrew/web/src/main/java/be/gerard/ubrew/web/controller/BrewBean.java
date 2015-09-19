package be.gerard.ubrew.web.controller;

import java.io.Serializable;
import javax.inject.Named;
import org.springframework.context.annotation.Scope;

//@ViewScoped
@Scope("session")
@Named
public class BrewBean implements Serializable {

    private static final long serialVersionUID = 1L;

    public enum BREW_STYLE {
        Unique, Classic;
        
        public boolean isUnique() {
            return this == Unique;
        }
        
        public boolean isClassic() {
            return this == Classic;
        }
        
    }

    private BREW_STYLE brewStyle = BREW_STYLE.Unique;
    
    private String name;
    
    private int percentage = 0;

    public BREW_STYLE getBrewStyle() {
        return brewStyle;
    }

    public void setBrewStyle(BREW_STYLE brewStyle) {
        this.brewStyle = brewStyle;
    }
    
    public void setUnique() {
        setBrewStyle(BREW_STYLE.Unique);
    }
    
    public void setClassic() {
        setBrewStyle(BREW_STYLE.Classic);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPercentage() {
        return percentage;
    }

    public void setPercentage(int percentage) {
        this.percentage = percentage;
    }
    
    

}
