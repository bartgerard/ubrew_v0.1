package be.gerard.common.invoker;

import static be.gerard.common.invoker.CustomHttpInvokerServiceExporter.APPLICATION_HEADER;
import static be.gerard.common.invoker.CustomHttpInvokerServiceExporter.USER_HEADER;
import java.io.IOException;
import java.io.Serializable;
import java.net.HttpURLConnection;
import org.springframework.remoting.httpinvoker.SimpleHttpInvokerRequestExecutor;

/**
 * CustomHttpInvokerRequestExecutor
 *
 * @author bartgerard
 * @version 0.0.1
 */
public class CustomHttpInvokerRequestExecutor extends SimpleHttpInvokerRequestExecutor implements Serializable {

    @Override
    protected void prepareConnection(HttpURLConnection connection, int contentLength) throws IOException {
        connection.addRequestProperty(USER_HEADER, "a"); // TODO
        connection.addRequestProperty(APPLICATION_HEADER, "a"); // TODO
        super.prepareConnection(connection, contentLength);
    }

}
