package be.gerard.general.service;

import be.gerard.general.interface_v1.ApplicationService;
import be.gerard.general.interface_v1.model.Application;
import be.gerard.general.service.dao.ApplicationDetailDAO;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * ApplicationServiceImpl
 *
 * @author bartgerard
 * @version 0.0.1
 */
@Service
public class ApplicationServiceImpl extends BaseServiceImpl implements ApplicationService {
    
    @Autowired
    private ApplicationDetailDAO applicationDetailDAO;

    @Override
    public Application find(Long id) {
        return null;
    }

    @Override
    public Application saveOrUpdate(Application to) {
        return null;
    }

    @Override
    public void remove(Application to) {
    }

    @Override
    public List<Application> findAll() {
        return null;
    }

}
