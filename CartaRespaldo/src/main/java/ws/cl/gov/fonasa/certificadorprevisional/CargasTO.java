/**
 * CargasTO.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package ws.cl.gov.fonasa.certificadorprevisional;

public class CargasTO  implements java.io.Serializable {
    private int rutcarga;

    private java.lang.String dgvcarga;

    private java.lang.String apell1;

    private java.lang.String apell2;

    private java.lang.String nombres;

    private java.lang.String parentesco;

    private java.lang.String fecnac;

    private java.lang.String genero;

    private java.lang.String generoDes;

    public CargasTO() {
    }

    public CargasTO(
           int rutcarga,
           java.lang.String dgvcarga,
           java.lang.String apell1,
           java.lang.String apell2,
           java.lang.String nombres,
           java.lang.String parentesco,
           java.lang.String fecnac,
           java.lang.String genero,
           java.lang.String generoDes) {
           this.rutcarga = rutcarga;
           this.dgvcarga = dgvcarga;
           this.apell1 = apell1;
           this.apell2 = apell2;
           this.nombres = nombres;
           this.parentesco = parentesco;
           this.fecnac = fecnac;
           this.genero = genero;
           this.generoDes = generoDes;
    }


    /**
     * Gets the rutcarga value for this CargasTO.
     * 
     * @return rutcarga
     */
    public int getRutcarga() {
        return rutcarga;
    }


    /**
     * Sets the rutcarga value for this CargasTO.
     * 
     * @param rutcarga
     */
    public void setRutcarga(int rutcarga) {
        this.rutcarga = rutcarga;
    }


    /**
     * Gets the dgvcarga value for this CargasTO.
     * 
     * @return dgvcarga
     */
    public java.lang.String getDgvcarga() {
        return dgvcarga;
    }


    /**
     * Sets the dgvcarga value for this CargasTO.
     * 
     * @param dgvcarga
     */
    public void setDgvcarga(java.lang.String dgvcarga) {
        this.dgvcarga = dgvcarga;
    }


    /**
     * Gets the apell1 value for this CargasTO.
     * 
     * @return apell1
     */
    public java.lang.String getApell1() {
        return apell1;
    }


    /**
     * Sets the apell1 value for this CargasTO.
     * 
     * @param apell1
     */
    public void setApell1(java.lang.String apell1) {
        this.apell1 = apell1;
    }


    /**
     * Gets the apell2 value for this CargasTO.
     * 
     * @return apell2
     */
    public java.lang.String getApell2() {
        return apell2;
    }


    /**
     * Sets the apell2 value for this CargasTO.
     * 
     * @param apell2
     */
    public void setApell2(java.lang.String apell2) {
        this.apell2 = apell2;
    }


    /**
     * Gets the nombres value for this CargasTO.
     * 
     * @return nombres
     */
    public java.lang.String getNombres() {
        return nombres;
    }


    /**
     * Sets the nombres value for this CargasTO.
     * 
     * @param nombres
     */
    public void setNombres(java.lang.String nombres) {
        this.nombres = nombres;
    }


    /**
     * Gets the parentesco value for this CargasTO.
     * 
     * @return parentesco
     */
    public java.lang.String getParentesco() {
        return parentesco;
    }


    /**
     * Sets the parentesco value for this CargasTO.
     * 
     * @param parentesco
     */
    public void setParentesco(java.lang.String parentesco) {
        this.parentesco = parentesco;
    }


    /**
     * Gets the fecnac value for this CargasTO.
     * 
     * @return fecnac
     */
    public java.lang.String getFecnac() {
        return fecnac;
    }


    /**
     * Sets the fecnac value for this CargasTO.
     * 
     * @param fecnac
     */
    public void setFecnac(java.lang.String fecnac) {
        this.fecnac = fecnac;
    }


    /**
     * Gets the genero value for this CargasTO.
     * 
     * @return genero
     */
    public java.lang.String getGenero() {
        return genero;
    }


    /**
     * Sets the genero value for this CargasTO.
     * 
     * @param genero
     */
    public void setGenero(java.lang.String genero) {
        this.genero = genero;
    }


    /**
     * Gets the generoDes value for this CargasTO.
     * 
     * @return generoDes
     */
    public java.lang.String getGeneroDes() {
        return generoDes;
    }


    /**
     * Sets the generoDes value for this CargasTO.
     * 
     * @param generoDes
     */
    public void setGeneroDes(java.lang.String generoDes) {
        this.generoDes = generoDes;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof CargasTO)) return false;
        CargasTO other = (CargasTO) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            this.rutcarga == other.getRutcarga() &&
            ((this.dgvcarga==null && other.getDgvcarga()==null) || 
             (this.dgvcarga!=null &&
              this.dgvcarga.equals(other.getDgvcarga()))) &&
            ((this.apell1==null && other.getApell1()==null) || 
             (this.apell1!=null &&
              this.apell1.equals(other.getApell1()))) &&
            ((this.apell2==null && other.getApell2()==null) || 
             (this.apell2!=null &&
              this.apell2.equals(other.getApell2()))) &&
            ((this.nombres==null && other.getNombres()==null) || 
             (this.nombres!=null &&
              this.nombres.equals(other.getNombres()))) &&
            ((this.parentesco==null && other.getParentesco()==null) || 
             (this.parentesco!=null &&
              this.parentesco.equals(other.getParentesco()))) &&
            ((this.fecnac==null && other.getFecnac()==null) || 
             (this.fecnac!=null &&
              this.fecnac.equals(other.getFecnac()))) &&
            ((this.genero==null && other.getGenero()==null) || 
             (this.genero!=null &&
              this.genero.equals(other.getGenero()))) &&
            ((this.generoDes==null && other.getGeneroDes()==null) || 
             (this.generoDes!=null &&
              this.generoDes.equals(other.getGeneroDes())));
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
        _hashCode += getRutcarga();
        if (getDgvcarga() != null) {
            _hashCode += getDgvcarga().hashCode();
        }
        if (getApell1() != null) {
            _hashCode += getApell1().hashCode();
        }
        if (getApell2() != null) {
            _hashCode += getApell2().hashCode();
        }
        if (getNombres() != null) {
            _hashCode += getNombres().hashCode();
        }
        if (getParentesco() != null) {
            _hashCode += getParentesco().hashCode();
        }
        if (getFecnac() != null) {
            _hashCode += getFecnac().hashCode();
        }
        if (getGenero() != null) {
            _hashCode += getGenero().hashCode();
        }
        if (getGeneroDes() != null) {
            _hashCode += getGeneroDes().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(CargasTO.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://certificadorprevisional.fonasa.gov.cl.ws/", "CargasTO"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("rutcarga");
        elemField.setXmlName(new javax.xml.namespace.QName("http://certificadorprevisional.fonasa.gov.cl.ws/", "rutcarga"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("dgvcarga");
        elemField.setXmlName(new javax.xml.namespace.QName("http://certificadorprevisional.fonasa.gov.cl.ws/", "dgvcarga"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("apell1");
        elemField.setXmlName(new javax.xml.namespace.QName("http://certificadorprevisional.fonasa.gov.cl.ws/", "apell1"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("apell2");
        elemField.setXmlName(new javax.xml.namespace.QName("http://certificadorprevisional.fonasa.gov.cl.ws/", "apell2"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("nombres");
        elemField.setXmlName(new javax.xml.namespace.QName("http://certificadorprevisional.fonasa.gov.cl.ws/", "nombres"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("parentesco");
        elemField.setXmlName(new javax.xml.namespace.QName("http://certificadorprevisional.fonasa.gov.cl.ws/", "parentesco"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("fecnac");
        elemField.setXmlName(new javax.xml.namespace.QName("http://certificadorprevisional.fonasa.gov.cl.ws/", "fecnac"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("genero");
        elemField.setXmlName(new javax.xml.namespace.QName("http://certificadorprevisional.fonasa.gov.cl.ws/", "genero"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("generoDes");
        elemField.setXmlName(new javax.xml.namespace.QName("http://certificadorprevisional.fonasa.gov.cl.ws/", "generoDes"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
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
