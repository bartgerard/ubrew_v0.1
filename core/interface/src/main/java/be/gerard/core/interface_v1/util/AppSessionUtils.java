package be.gerard.core.interface_v1.util;

import be.gerard.core.interface_v1.session.AppSession;

/**
 * UserUtils
 *
 * @author bartgerard
 * @version 0.0.1
 */
public class AppSessionUtils {

    private static final ThreadLocal<AppSession> appSessionThreadLocal = new ThreadLocal<>();

    private AppSessionUtils() {
    }

    public static AppSession getAppSession() {
        return appSessionThreadLocal.get();
    }

    public static void setAppSession(AppSession appSession) {
        appSessionThreadLocal.set(appSession);
    }

    public static void remove() {
        appSessionThreadLocal.remove();
    }

}
