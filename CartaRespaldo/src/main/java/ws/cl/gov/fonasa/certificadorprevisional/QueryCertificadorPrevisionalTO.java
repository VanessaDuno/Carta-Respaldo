/**
 * QueryCertificadorPrevisionalTO.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package ws.cl.gov.fonasa.certificadorprevisional;

public class QueryCertificadorPrevisionalTO  implements java.io.Serializable {
    private ws.cl.gov.fonasa.certificadorprevisional.QueryTO queryTO;

    private int entidad;

    private int claveEntidad;

    private int rutBeneficiario;

    private java.lang.String dgvBeneficiario;

    private int canal;

    public QueryCertificadorPrevisionalTO() {
    }

    public QueryCertificadorPrevisionalTO(
           ws.cl.gov.fonasa.certificadorprevisional.QueryTO queryTO,
           int entidad,
           int claveEntidad,
           int rutBeneficiario,
           java.lang.String dgvBeneficiario,
           int canal) {
           this.queryTO = queryTO;
           this.entidad = entidad;
           this.claveEntidad = claveEntidad;
           this.rutBeneficiario = rutBeneficiario;
           this.dgvBeneficiario = dgvBeneficiario;
           this.canal = canal;
    }


    /**
     * Gets the queryTO value for this QueryCertificadorPrevisionalTO.
     * 
     * @return queryTO
     */
    public ws.cl.gov.fonasa.certificadorprevisional.QueryTO getQueryTO() {
        return queryTO;
    }


    /**
     * Sets the queryTO value for this QueryCertificadorPrevisionalTO.
     * 
     * @param queryTO
     */
    public void setQueryTO(ws.cl.gov.fonasa.certificadorprevisional.QueryTO queryTO) {
        this.queryTO = queryTO;
    }


    /**
     * Gets the entidad value for this QueryCertificadorPrevisionalTO.
     * 
     * @return entidad
     */
    public int getEntidad() {
        return entidad;
    }


    /**
     * Sets the entidad value for this QueryCertificadorPrevisionalTO.
     * 
     * @param entidad
     */
    public void setEntidad(int entidad) {
        this.entidad = entidad;
    }


    /**
     * Gets the claveEntidad value for this QueryCertificadorPrevisionalTO.
     * 
     * @return claveEntidad
     */
    public int getClaveEntidad() {
        return claveEntidad;
    }


    /**
     * Sets the claveEntidad value for this QueryCertificadorPrevisionalTO.
     * 
     * @param claveEntidad
     */
    public void setClaveEntidad(int claveEntidad) {
        this.claveEntidad = claveEntidad;
    }


    /**
     * Gets the rutBeneficiario value for this QueryCertificadorPrevisionalTO.
     * 
     * @return rutBeneficiario
     */
    public int getRutBeneficiario() {
        return rutBeneficiario;
    }


    /**
     * Sets the rutBeneficiario value for this QueryCertificadorPrevisionalTO.
     * 
     * @param rutBeneficiario
     */
    public void setRutBeneficiario(int rutBeneficiario) {
        this.rutBeneficiario = rutBeneficiario;
    }


    /**
     * Gets the dgvBeneficiario value for this QueryCertificadorPrevisionalTO.
     * 
     * @return dgvBeneficiario
     */
    public java.lang.String getDgvBeneficiario() {
        return dgvBeneficiario;
    }


    /**
     * Sets the dgvBeneficiario value for this QueryCertificadorPrevisionalTO.
     * 
     * @param dgvBeneficiario
     */
    public void setDgvBeneficiario(java.lang.String dgvBeneficiario) {
        this.dgvBeneficiario = dgvBeneficiario;
    }


    /**
     * Gets the canal value for this QueryCertificadorPrevisionalTO.
     * 
     * @return canal
     */
    public int getCanal() {
        return canal;
    }


    /**
     * Sets the canal value for this QueryCertificadorPrevisionalTO.
     * 
     * @param canal
     */
    public void setCanal(int canal) {
        this.canal = canal;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof QueryCertificadorPrevisionalTO)) return false;
        QueryCertificadorPrevisionalTO other = (QueryCertificadorPrevisionalTO) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.queryTO==null && other.getQueryTO()==null) || 
             (this.queryTO!=null &&
              this.queryTO.equals(other.getQueryTO()))) &&
            this.entidad == other.getEntidad() &&
            this.claveEntidad == other.getClaveEntidad() &&
            this.rutBeneficiario == other.getRutBeneficiario() &&
            ((this.dgvBeneficiario==null && other.getDgvBeneficiario()==null) || 
             (this.dgvBeneficiario!=null &&
              this.dgvBeneficiario.equals(other.getDgvBeneficiario()))) &&
            this.canal == other.getCanal();
        __equalsCalc = null;
        return _equals;
    }

    private boolean __hashCodeCalc = false;
    public synchronized int hashCode() {
        if (__hashCodeCalc) {
            return 0;
        }
        __hashCodeCalc = true;
        int _hashCode = 1;
        if (getQueryTO() != null) {
            _hashCode += getQueryTO().hashCode();
        }
        _hashCode += getEntidad();
        _hashCode += getClaveEntidad();
        _hashCode += getRutBeneficiario();
        if (getDgvBeneficiario() != null) {
            _hashCode += getDgvBeneficiario().hashCode();
        }
        _hashCode += getCanal();
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(QueryCertificadorPrevisionalTO.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://certificadorprevisional.fonasa.gov.cl.ws/", "QueryCertificadorPrevisionalTO"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("queryTO");
        elemField.setXmlName(new javax.xml.namespace.QName("http://certificadorprevisional.fonasa.gov.cl.ws/", "queryTO"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://certificadorprevisional.fonasa.gov.cl.ws/", "queryTO"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("entidad");
        elemField.setXmlName(new javax.xml.namespace.QName("http://certificadorprevisional.fonasa.gov.cl.ws/", "entidad"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("claveEntidad");
        elemField.setXmlName(new javax.xml.namespace.QName("http://certificadorprevisional.fonasa.gov.cl.ws/", "claveEntidad"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("rutBeneficiario");
        elemField.setXmlName(new javax.xml.namespace.QName("http://certificadorprevisional.fonasa.gov.cl.ws/", "rutBeneficiario"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("dgvBeneficiario");
        elemField.setXmlName(new javax.xml.namespace.QName("http://certificadorprevisional.fonasa.gov.cl.ws/", "dgvBeneficiario"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("canal");
        elemField.setXmlName(new javax.xml.namespace.QName("http://certificadorprevisional.fonasa.gov.cl.ws/", "canal"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
    }

    /**
     * Return type metadata object
     */
    public static org.apache.axis.description.TypeDesc getTypeDesc() {
        return typeDesc;
    }

    /**
     * Get Custom Serializer
     */
    public static org.apache.axis.encoding.Serializer getSerializer(
           java.lang.String mechType, 
           java.lang.Class _javaType,  
           javax.xml.namespace.QName _xmlType) {
        return 
          new  org.apache.axis.encoding.ser.BeanSerializer(
            _javaType, _xmlType, typeDesc);
    }

    /**
     * Get Custom Deserializer
     */
    public static org.apache.axis.encoding.Deserializer getDeserializer(
           java.lang.String mechType, 
           java.lang.Class _javaType,  
           javax.xml.namespace.QName _xmlType) {
        return 
          new  org.apache.axis.encoding.ser.BeanDeserializer(
            _javaType, _xmlType, typeDesc);
    }

}
