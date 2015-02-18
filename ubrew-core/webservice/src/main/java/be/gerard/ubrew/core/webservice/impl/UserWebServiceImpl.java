package be.gerard.ubrew.core.webservice.impl;

import be.gerard.general.interface_v1.UserService;
import be.gerard.ubrew.core.webservice.UserWebService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * AuthenticationWebServiceImpl
 *
 * @author bartgerard
 * @version 0.0.1
 */
@Service
public class UserWebServiceImpl implements UserWebService {

    @Autowired
    private UserService userService;
    
}
