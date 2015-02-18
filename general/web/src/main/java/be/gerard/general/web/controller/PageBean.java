package be.gerard.general.web.controller;

import javax.inject.Named;

import java.io.Serializable;
import org.springframework.context.annotation.Scope;

@Scope("session")
@Named
public class PageBean implements Serializable {

    private static final long serialVersionUID = 1L;

    public enum PAGE {

        USER("/admin/user/List.xhtml"),
        TRANSLATION("/admin/translation/List.xhtml");

        private PAGE(String path) {
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
