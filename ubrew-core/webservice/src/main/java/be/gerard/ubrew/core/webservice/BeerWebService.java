package be.gerard.ubrew.core.webservice;

import be.gerard.ubrew.core.interface_v1.model.product.BeerType;
import java.util.List;
import javax.jws.WebMethod;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;

/**
 * BeerWebService
 *
 * @author bartgerard
 * @version 0.0.1
 */
@WebService(portName = "BeerWebServicePort", targetNamespace = "http://ubrew.be")
@SOAPBinding(style = SOAPBinding.Style.RPC, use = SOAPBinding.Use.LITERAL, parameterStyle = SOAPBinding.ParameterStyle.WRAPPED)
public interface BeerWebService {
    
    @WebMethod(operationName = "getVersion")
    @WebResult(name = "version")
    String getVersion();
    
    @WebMethod(operationName = "saveOrUpdateBeerType")
    @WebResult(name = "beerType")
    BeerType saveOrUpdateBeerType(BeerType beerType);

    @WebMethod(operationName = "findAllBeerTypes")
    @WebResult(name = "beerTypes")
    List<BeerType> findAllBeerTypes();

}
