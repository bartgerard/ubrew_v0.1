package be.gerard.evenizer.web.controller;

import org.springframework.context.annotation.Scope;

import javax.inject.Named;
import java.io.Serializable;

@Scope("session")
@Named
public class PageBean implements Serializable {

    private static final long serialVersionUID = 1L;

    public enum PAGE {

        USER("/admin/user/List.xhtml"),
        TRANSLATION("/admin/translation/List.xhtml"),
        NEW("/public/new.xhtml");

        PAGE(String path) {
            this.path = path;
        }

        private final String path;

        public String getPath() {
            return path;
        }

        public boolean isUser() {
            return this == PAGE.USER;
        }
        
        public boolean isTranslation() {
            return this == PAGE.TRANSLATION;
        }

    }

    private PAGE page = PAGE.USER;

    public PAGE getPage() {
        return page;
    }

    void setPage(PAGE page) {
        this.page = page;
    }

    public String switchPage(String key) {
        setPage(PAGE.valueOf(key));
        return page.getPath() + "?faces-redirect=true";
    }

}
