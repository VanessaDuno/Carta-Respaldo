/**
 * SONDACertificadoPrevisionalLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package ws.cl.gov.fonasa.certificadorprevisional;

public class SONDACertificadoPrevisionalLocator extends org.apache.axis.client.Service implements ws.cl.gov.fonasa.certificadorprevisional.SONDACertificadoPrevisional {

    public SONDACertificadoPrevisionalLocator() {
    }


    public SONDACertificadoPrevisionalLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public SONDACertificadoPrevisionalLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for CertificadorPrevisionalSoap
    private java.lang.String CertificadorPrevisionalSoap_address = "http://ws.fonasa.cl:8080/Certificados/Previsional";

    public java.lang.String getCertificadorPrevisionalSoapAddress() {
        return CertificadorPrevisionalSoap_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String CertificadorPrevisionalSoapWSDDServiceName = "CertificadorPrevisionalSoap";

    public java.lang.String getCertificadorPrevisionalSoapWSDDServiceName() {
        return CertificadorPrevisionalSoapWSDDServiceName;
    }

    public void setCertificadorPrevisionalSoapWSDDServiceName(java.lang.String name) {
        CertificadorPrevisionalSoapWSDDServiceName = name;
    }

    public ws.cl.gov.fonasa.certificadorprevisional.CertificadorPrevisionalSoap getCertificadorPrevisionalSoap() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(CertificadorPrevisionalSoap_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getCertificadorPrevisionalSoap(endpoint);
    }

    public ws.cl.gov.fonasa.certificadorprevisional.CertificadorPrevisionalSoap getCertificadorPrevisionalSoap(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            ws.cl.gov.fonasa.certificadorprevisional.CertificadorPrevisionalSoapSoapBindingStub _stub = new ws.cl.gov.fonasa.certificadorprevisional.CertificadorPrevisionalSoapSoapBindingStub(portAddress, this);
            _stub.setPortName(getCertificadorPrevisionalSoapWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setCertificadorPrevisionalSoapEndpointAddress(java.lang.String address) {
        CertificadorPrevisionalSoap_address = address;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (ws.cl.gov.fonasa.certificadorprevisional.CertificadorPrevisionalSoap.class.isAssignableFrom(serviceEndpointInterface)) {
                ws.cl.gov.fonasa.certificadorprevisional.CertificadorPrevisionalSoapSoapBindingStub _stub = new ws.cl.gov.fonasa.certificadorprevisional.CertificadorPrevisionalSoapSoapBindingStub(new java.net.URL(CertificadorPrevisionalSoap_address), this);
                _stub.setPortName(getCertificadorPrevisionalSoapWSDDServiceName());
                return _stub;
            }
        }
        catch (java.lang.Throwable t) {
            throw new javax.xml.rpc.ServiceException(t);
        }
        throw new javax.xml.rpc.ServiceException("There is no stub implementation for the interface:  " + (serviceEndpointInterface == null ? "null" : serviceEndpointInterface.getName()));
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(javax.xml.namespace.QName portName, Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        if (portName == null) {
            return getPort(serviceEndpointInterface);
        }
        java.lang.String inputPortName = portName.getLocalPart();
        if ("CertificadorPrevisionalSoap".equals(inputPortName)) {
            return getCertificadorPrevisionalSoap();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("http://certificadorprevisional.fonasa.gov.cl.ws/", "SONDACertificadoPrevisional");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("http://certificadorprevisional.fonasa.gov.cl.ws/", "CertificadorPrevisionalSoap"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(java.lang.String portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        
if ("CertificadorPrevisionalSoap".equals(portName)) {
            setCertificadorPrevisionalSoapEndpointAddress(address);
        }
        else 
{ // Unknown Port Name
            throw new javax.xml.rpc.ServiceException(" Cannot set Endpoint Address for Unknown Port" + portName);
        }
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(javax.xml.namespace.QName portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        setEndpointAddress(portName.getLocalPart(), address);
    }

}
