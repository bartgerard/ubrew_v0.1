package be.gerard.core.interface_v1.util;

import be.gerard.core.interface_v1.session.UserSession;

/**
 * UserUtils
 *
 * @author bartgerard
 * @version 0.0.1
 */
public class UserSessionUtils {

    private static final ThreadLocal<UserSession> userSessionThreadLocal = new ThreadLocal<>();

    private UserSessionUtils() {
    }

    public static UserSession getUserSession() {
        return userSessionThreadLocal.get();
    }
    
    public static void setUserSession(UserSession userSession) {
        userSessionThreadLocal.set(userSession);
    }
    
    public static void remove() {
        userSessionThreadLocal.remove();
    }

}
