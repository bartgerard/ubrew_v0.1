package be.gerard.common.invoker;

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
        connection.addRequestProperty(CustomHttpInvokerServiceExporter.USER_HEADER, "a"); // TODO
        connection.addRequestProperty(CustomHttpInvokerServiceExporter.APPLICATION_HEADER, "a"); // TODO
        super.prepareConnection(connection, contentLength);
    }

}
