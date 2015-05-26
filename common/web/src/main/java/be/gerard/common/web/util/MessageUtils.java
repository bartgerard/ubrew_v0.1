package be.gerard.common.web.util;

import be.gerard.core.interface_v1.util.MessageResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.faces.context.FacesContext;
import java.util.AbstractMap;
import java.util.Locale;
import java.util.Set;

/**
 * MessageUtil
 *
 * @author bartgerard
 * @version v0.0.1
 */
@Component("i18n")
public class MessageUtils extends AbstractMap<String, String> {

    @Autowired
    private MessageResolver messageResolver;

    private String getLanguage() {
        Locale locale = FacesContext.getCurrentInstance().getViewRoot().getLocale();

        if (locale == null) {
            locale = FacesContext.getCurrentInstance().getExternalContext().getRequestLocale();
        }

        return locale != null ? locale.getLanguage(): null;
    }

    @Override
    public String get(Object key) {
        return get(String.valueOf(key));
    }

    public String get(final String key) {
        return messageResolver.get(getLanguage(), key);
    }

    @Override
    public Set<Entry<String, String>> entrySet() {
        throw new UnsupportedOperationException("Not supported.");
    }

}
