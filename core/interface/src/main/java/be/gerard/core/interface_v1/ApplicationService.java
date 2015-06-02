package be.gerard.core.interface_v1;

import be.gerard.core.interface_v1.model.Application;

import java.util.Collection;

/**
 * ApplicationService
 *
 * @author bartgerard
 * @version 0.0.1
 * @since 2015-01-10
 */
public interface ApplicationService {

    Application save(Application application);

    void instantiate(String appKey, String reference, String password, Collection<String> ips, Collection<String> macs);

    void delete(String appKey, String reference);

}
