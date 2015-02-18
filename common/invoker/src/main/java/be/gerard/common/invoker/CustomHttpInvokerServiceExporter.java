package be.gerard.common.invoker;

import be.gerard.common.authentication.util.AppSessionUtils;
import be.gerard.common.authentication.util.UserSessionUtils;
import be.gerard.general.interface_v1.UserAuthenticationService;
import be.gerard.general.interface_v1.session.AppSession;
import be.gerard.general.interface_v1.session.UserSession;
import java.io.IOException;
import java.io.Serializable;
import java.util.UUID;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.remoting.httpinvoker.HttpInvokerServiceExporter;

/**
 * CustomHttpInvokerServiceExporter
 *
 * @author bartgerard
 * @version 0.0.1
 */
public class CustomHttpInvokerServiceExporter extends HttpInvokerServiceExporter implements Serializable {

    public static final String USER_HEADER = "be.gerard.session.user.token";

    public static final String APPLICATION_HEADER = "be.gerard.session.application.token";

    @Autowired
    private UserAuthenticationService authenticationService;

    @Override
    public void handleRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String sessionToken = request.getHeader(USER_HEADER);
        String applicationToken = request.getHeader(APPLICATION_HEADER);

        UserSession session = authenticationService.findSession(UUID.fromString(sessionToken));
        // TODO AUTHORIZATION ERROR + LOG IN FIRST!

        try {
            UserSessionUtils.setUserSession(session);
            AppSessionUtils.setAppSession(new AppSession(UUID.fromString(applicationToken)));
            super.handleRequest(request, response);
        } finally {
            UserSessionUtils.remove();
        }
    }

}
