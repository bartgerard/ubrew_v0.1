package be.gerard.common.exception_v1;

import be.gerard.common.exception_v1.error.ServiceError;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.io.Serializable;
import java.io.StringWriter;
import java.net.Inet4Address;
import java.net.UnknownHostException;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author bartgerard
 */
public abstract class ServiceException extends RuntimeException implements Serializable {

    public static final String KEY_DELIMITER = "_";

    private long id;

    private String hostName;

    private String userName;

    private String domainName;

    private final Map<ServiceError, String[]> errors = new HashMap<>();

    public ServiceException(final String message, final ServiceError error, final String... arguments) {
        super(message);
        prepareContext();
        this.errors.put(error, arguments);
    }

    public ServiceException(final String message, final Map<? extends ServiceError, String[]> errors) {
        super(message);
        prepareContext();
        this.errors.putAll(errors);
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getHostName() {
        return hostName;
    }

    public void setHostName(String hostName) {
        this.hostName = hostName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getDomainName() {
        return domainName;
    }

    public void setDomainName(String domainName) {
        this.domainName = domainName;
    }

    public Map<ServiceError, String[]> getErrors() {
        return Collections.unmodifiableMap(errors);
    }
    
    public boolean contains(ServiceError error) {
        return errors.containsKey(error);
    }

    private void prepareContext() {
        this.id = System.currentTimeMillis();
        this.hostName = getHost();
        this.userName = getUser();
        this.domainName = getDomain();
    }

    private static String getHost() {
        try {
            return Inet4Address.getLocalHost().getCanonicalHostName();
        } catch (UnknownHostException e) {
            return "n/a";
        }
    }

    private static String getUser() {
        try {
            return System.getProperty("user.name");
        } catch (Exception e) {
            return "n/a";
        }
    }

    private static String getDomain() {
        try {
            return System.getenv("USERDOMAIN");
        } catch (Exception e) {
            return "n/a";
        }
    }

    @Override
    public void printStackTrace(PrintStream printStream) {
        try (PrintWriter printWriter = new PrintWriter(printStream == null ? System.err : printStream)) {
            printStackTrace(printWriter);
            printWriter.close();
        }
    }

    @Override
    public final void printStackTrace(PrintWriter printWriter) {
        if (printWriter == null) {
            printWriter = new PrintWriter(System.err);
        }

        printWriter.println(String.format("EXCEPTION: %s@%s/%s %s#%d: %s", getUserName(), getHostName(), getDomainName(), getClass().getSimpleName(), getId(), getMessage()));
        
        if (!errors.isEmpty()) {
            for (Map.Entry<? extends ServiceError, String[]> error : errors.entrySet()) {
                printWriter.println(String.format("\t %s, arguments = %s", error.getKey().getKey(), Arrays.toString(error.getValue())));
            }
        }
    }

    @Override
    public String toString() {
        StringWriter stringWriter = new StringWriter();
        try (PrintWriter printWriter = new PrintWriter(stringWriter)) {
            printStackTrace(printWriter);
            printWriter.close();
        }
        return stringWriter.toString();
    }

}
