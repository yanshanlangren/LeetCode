package elvis.webservice;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.xml.bind.annotation.XmlSeeAlso;

@WebService(name = "iVendorWsService", targetNamespace = "http://www.aurora-framework.org/schema")
@SOAPBinding(parameterStyle = SOAPBinding.ParameterStyle.BARE)
@XmlSeeAlso({Request.class})
public interface WebserviceInterface {
    @WebMethod(action = "execute")
    @WebResult(name = "vendorResponse", targetNamespace = "http://www.aurora-framework.org/schema", partName = "vendorResponse_part")
    Response execute(@WebParam(name = "vendorRequest", targetNamespace = "http://www.aurora-framework.org/schema", partName = "vendorRequest_part")
                             Request request);
}
