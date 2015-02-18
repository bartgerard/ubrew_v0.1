package be.gerard.ubrew.core.web.controller;

import javax.inject.Named;

import java.io.Serializable;
import org.springframework.context.annotation.Scope;

@Scope("session")
@Named
public class PageBean implements Serializable {

    private static final long serialVersionUID = 1L;

    public enum PAGE implements Serializable {

        HOME("/public/home.xhtml"),
        NEWS("/public/news.xhtml"),
        CLICK_AND_BREW("/private/brew.xhtml"),
        ABOUT("/public/about.xhtml"),
        REGISTER("/public/register.xhtml"),
        PROFILE("/private/profile.xhtml"),
        MY_BREW("/private/my_brew.xhtml");

        private PAGE(String path) {
            this.path = path;
        }

        private final String path;

        public String getPath() {
            return path;
        }

        public boolean isHome() {
            return this == PAGE.HOME;
        }
        
        public boolean isNews() {
            return this == PAGE.NEWS;
        }
        
        public boolean isClickAndBrew() {
            return this == PAGE.CLICK_AND_BREW;
        }
        
        public boolean isAbout() {
            return this == PAGE.ABOUT;
        }
        
        public boolean isProfile() {
            return this == PAGE.PROFILE;
        }
        
        public boolean isMyBrew() {
            return this == PAGE.MY_BREW;
        }

    }

    private PAGE page = PAGE.HOME;

    public PAGE getPage() {
        return page;
    }

    void setPage(PAGE page) {
        this.page = page;
    }

    public void switchPage(String key) {
        setPage(PAGE.valueOf(key));
    }

}
