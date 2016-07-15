package ws.cl.gov.fonasa.certificadorprevisional;

public class CertificadorPrevisionalSoapProxy implements ws.cl.gov.fonasa.certificadorprevisional.CertificadorPrevisionalSoap {
  private String _endpoint = null;
  private ws.cl.gov.fonasa.certificadorprevisional.CertificadorPrevisionalSoap certificadorPrevisionalSoap = null;
  
  public CertificadorPrevisionalSoapProxy() {
    _initCertificadorPrevisionalSoapProxy();
  }
  
  public CertificadorPrevisionalSoapProxy(String endpoint) {
    _endpoint = endpoint;
    _initCertificadorPrevisionalSoapProxy();
  }
  
  private void _initCertificadorPrevisionalSoapProxy() {
    try {
      certificadorPrevisionalSoap = (new ws.cl.gov.fonasa.certificadorprevisional.SONDACertificadoPrevisionalLocator()).getCertificadorPrevisionalSoap();
      if (certificadorPrevisionalSoap != null) {
        if (_endpoint != null)
          ((javax.xml.rpc.Stub)certificadorPrevisionalSoap)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
        else
          _endpoint = (String)((javax.xml.rpc.Stub)certificadorPrevisionalSoap)._getProperty("javax.xml.rpc.service.endpoint.address");
      }
      
    }
    catch (javax.xml.rpc.ServiceException serviceException) {}
  }
  
  public String getEndpoint() {
    return _endpoint;
  }
  
  public void setEndpoint(String endpoint) {
    _endpoint = endpoint;
    if (certificadorPrevisionalSoap != null)
      ((javax.xml.rpc.Stub)certificadorPrevisionalSoap)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
    
  }
  
  public ws.cl.gov.fonasa.certificadorprevisional.CertificadorPrevisionalSoap getCertificadorPrevisionalSoap() {
    if (certificadorPrevisionalSoap == null)
      _initCertificadorPrevisionalSoapProxy();
    return certificadorPrevisionalSoap;
  }
  
  public ws.cl.gov.fonasa.certificadorprevisional.ReplyCertificadorPrevisionalTO getCertificadoPrevisional(ws.cl.gov.fonasa.certificadorprevisional.QueryCertificadorPrevisionalTO query) throws java.rmi.RemoteException{
    if (certificadorPrevisionalSoap == null)
      _initCertificadorPrevisionalSoapProxy();
    return certificadorPrevisionalSoap.getCertificadoPrevisional(query);
  }
  
  
}