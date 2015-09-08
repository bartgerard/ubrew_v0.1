package be.gerard.ubrew.core.webservice;

import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;

/**
 * AuthenticationWebService
 *
 * @author bartgerard
 * @version 0.0.1
 */
@WebService(portName = "AuthenticationWebServicePort", targetNamespace = "http://ubrew.be")
@SOAPBinding(style = SOAPBinding.Style.RPC, use = SOAPBinding.Use.LITERAL, parameterStyle = SOAPBinding.ParameterStyle.WRAPPED)
public interface UserWebService {

}
