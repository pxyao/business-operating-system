
package per.crm.service;

import java.util.List;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.2.9-b130926.1035
 * Generated source version: 2.2
 * 
 */
@WebService(name = "ICustomerService", targetNamespace = "http://service.crm.per/")
@XmlSeeAlso({
})
public interface ICustomerService {


    /**
     * 
     * @return
     *     returns java.util.List<per.crm.Customer>
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "findListNotAssociation", targetNamespace = "http://service.crm.per/", className = "per.crm.FindListNotAssociation")
    @ResponseWrapper(localName = "findListNotAssociationResponse", targetNamespace = "http://service.crm.per/", className = "per.crm.FindListNotAssociationResponse")
    public List<Customer> findListNotAssociation();

    /**
     * 
     * @param arg1
     * @param arg0
     */
    @WebMethod
    @RequestWrapper(localName = "assigncustomerstodecidedzone", targetNamespace = "http://service.crm.per/", className = "per.crm.Assigncustomerstodecidedzone")
    @ResponseWrapper(localName = "assigncustomerstodecidedzoneResponse", targetNamespace = "http://service.crm.per/", className = "per.crm.AssigncustomerstodecidedzoneResponse")
    public void assigncustomerstodecidedzone(
        @WebParam(name = "arg0", targetNamespace = "")
        String arg0,
        @WebParam(name = "arg1", targetNamespace = "")
        List<Integer> arg1);

    /**
     * 
     * @return
     *     returns java.util.List<per.crm.Customer>
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "findAll", targetNamespace = "http://service.crm.per/", className = "per.crm.FindAll")
    @ResponseWrapper(localName = "findAllResponse", targetNamespace = "http://service.crm.per/", className = "per.crm.FindAllResponse")
    public List<Customer> findAll();

    /**
     * 
     * @param arg0
     * @return
     *     returns java.lang.String
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "findDecidedzoneIdByAddress", targetNamespace = "http://service.crm.per/", className = "per.crm.FindDecidedzoneIdByAddress")
    @ResponseWrapper(localName = "findDecidedzoneIdByAddressResponse", targetNamespace = "http://service.crm.per/", className = "per.crm.FindDecidedzoneIdByAddressResponse")
    public String findDecidedzoneIdByAddress(
        @WebParam(name = "arg0", targetNamespace = "")
        String arg0);

    /**
     * 
     * @param arg0
     * @return
     *     returns java.util.List<per.crm.Customer>
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "findListHasAssociation", targetNamespace = "http://service.crm.per/", className = "per.crm.FindListHasAssociation")
    @ResponseWrapper(localName = "findListHasAssociationResponse", targetNamespace = "http://service.crm.per/", className = "per.crm.FindListHasAssociationResponse")
    public List<Customer> findListHasAssociation(
        @WebParam(name = "arg0", targetNamespace = "")
        String arg0);

    /**
     * 
     * @param arg0
     * @return
     *     returns per.crm.Customer
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "findCustomerByTelphone", targetNamespace = "http://service.crm.per/", className = "per.crm.FindCustomerByTelphone")
    @ResponseWrapper(localName = "findCustomerByTelphoneResponse", targetNamespace = "http://service.crm.per/", className = "per.crm.FindCustomerByTelphoneResponse")
    public Customer findCustomerByTelphone(
        @WebParam(name = "arg0", targetNamespace = "")
        String arg0);

}