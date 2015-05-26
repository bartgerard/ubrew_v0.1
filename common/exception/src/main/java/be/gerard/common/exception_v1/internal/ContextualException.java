package be.gerard.common.exception_v1.internal;

import java.io.PrintStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.net.Inet4Address;
import java.net.UnknownHostException;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 *
 * @author bartgerard
 */
public class ContextualException extends RuntimeException {

    public static final String KEY_DELIMITER = "_";

    private long id;

    private String hostName;

    private String userName;

    private String domainName;

    private final Map<String, String> info = new LinkedHashMap<>();

    public ContextualException(final String message) {
        this(message, null, null);
    }

    public ContextualException(final String message, final Map<String, String> info) {
        this(message, null, info);
    }

    public ContextualException(final String message, final Throwable cause, final Map<String, String> info) {
        super(message, cause);

        this.id = cause instanceof ContextualException ? ((ContextualException) cause).getId() : System.currentTimeMillis();
        this.hostName = getHost();
        this.userName = getUser();
        this.domainName = getDomain();

        if (info != null) {
            this.info.putAll(info);
        }
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

        if (info != null && !info.isEmpty()) {
            for (Map.Entry<String, String> entry : info.entrySet()) {
                printWriter.println(String.format("\t %s, arguments = %s", entry.getKey(), entry.getValue()));
            }
        }

        Throwable cause = getCause();
        
        // Output the runtime stack of any primary exception.
        printWriter.println("\n\tStackTrace on " + cause.getClass().getName());
        for (StackTraceElement stackTraceElement : cause.getStackTrace()) {
            printWriter.println("\t\t" + stackTraceElement);
        }
        
        printWriter.println();
        printWriter.println(String.format("- END of EXCEPTION: #%d -", this.getId()));
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
