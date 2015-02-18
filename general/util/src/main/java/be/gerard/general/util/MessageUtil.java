package be.gerard.general.util;

import java.io.Serializable;
import java.util.AbstractMap;
import java.util.Locale;
import java.util.Set;
import javax.faces.context.FacesContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author bartgerard
 */
@Component("i18n")
public class MessageUtil extends AbstractMap<String, String> implements Serializable {
    
    @Autowired
    private MessageResolver messageCache;
    
    private String getLanguage() {
        Locale locale = FacesContext.getCurrentInstance().getViewRoot().getLocale();
        
        if (locale == null) {
            locale = FacesContext.getCurrentInstance().getExternalContext().getRequestLocale();
        }
        
        return locale != null ? locale.getLanguage(): null;
    }

    @Override
    public String get(final Object key) {
        return get((String) key);
    }

    public String get(final String key) {
        return messageCache.get(getLanguage(), key);
    }
    
    @Override
    public Set<Entry<String, String>> entrySet() {
        throw new UnsupportedOperationException("Not supported.");
    }
    
}
