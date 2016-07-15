/**
 * AfiliadoTO.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package ws.cl.gov.fonasa.certificadorprevisional;

public class AfiliadoTO  implements java.io.Serializable {
    private int rutafili;

    private java.lang.String dgvafili;

    private java.lang.String nombres;

    private java.lang.String apell1;

    private java.lang.String apell2;

    private java.lang.String fecnac;

    private java.lang.String tramo;

    private java.lang.String genero;

    private java.lang.String generoDes;

    private java.lang.String cdgEstado;

    private java.lang.String desEstado;

    public AfiliadoTO() {
    }

    public AfiliadoTO(
           int rutafili,
           java.lang.String dgvafili,
           java.lang.String nombres,
           java.lang.String apell1,
           java.lang.String apell2,
           java.lang.String fecnac,
           java.lang.String tramo,
           java.lang.String genero,
           java.lang.String generoDes,
           java.lang.String cdgEstado,
           java.lang.String desEstado) {
           this.rutafili = rutafili;
           this.dgvafili = dgvafili;
           this.nombres = nombres;
           this.apell1 = apell1;
           this.apell2 = apell2;
           this.fecnac = fecnac;
           this.tramo = tramo;
           this.genero = genero;
           this.generoDes = generoDes;
           this.cdgEstado = cdgEstado;
           this.desEstado = desEstado;
    }


    /**
     * Gets the rutafili value for this AfiliadoTO.
     * 
     * @return rutafili
     */
    public int getRutafili() {
        return rutafili;
    }


    /**
     * Sets the rutafili value for this AfiliadoTO.
     * 
     * @param rutafili
     */
    public void setRutafili(int rutafili) {
        this.rutafili = rutafili;
    }


    /**
     * Gets the dgvafili value for this AfiliadoTO.
     * 
     * @return dgvafili
     */
    public java.lang.String getDgvafili() {
        return dgvafili;
    }


    /**
     * Sets the dgvafili value for this AfiliadoTO.
     * 
     * @param dgvafili
     */
    public void setDgvafili(java.lang.String dgvafili) {
        this.dgvafili = dgvafili;
    }


    /**
     * Gets the nombres value for this AfiliadoTO.
     * 
     * @return nombres
     */
    public java.lang.String getNombres() {
        return nombres;
    }


    /**
     * Sets the nombres value for this AfiliadoTO.
     * 
     * @param nombres
     */
    public void setNombres(java.lang.String nombres) {
        this.nombres = nombres;
    }


    /**
     * Gets the apell1 value for this AfiliadoTO.
     * 
     * @return apell1
     */
    public java.lang.String getApell1() {
        return apell1;
    }


    /**
     * Sets the apell1 value for this AfiliadoTO.
     * 
     * @param apell1
     */
    public void setApell1(java.lang.String apell1) {
        this.apell1 = apell1;
    }


    /**
     * Gets the apell2 value for this AfiliadoTO.
     * 
     * @return apell2
     */
    public java.lang.String getApell2() {
        return apell2;
    }


    /**
     * Sets the apell2 value for this AfiliadoTO.
     * 
     * @param apell2
     */
    public void setApell2(java.lang.String apell2) {
        this.apell2 = apell2;
    }


    /**
     * Gets the fecnac value for this AfiliadoTO.
     * 
     * @return fecnac
     */
    public java.lang.String getFecnac() {
        return fecnac;
    }


    /**
     * Sets the fecnac value for this AfiliadoTO.
     * 
     * @param fecnac
     */
    public void setFecnac(java.lang.String fecnac) {
        this.fecnac = fecnac;
    }


    /**
     * Gets the tramo value for this AfiliadoTO.
     * 
     * @return tramo
     */
    public java.lang.String getTramo() {
        return tramo;
    }


    /**
     * Sets the tramo value for this AfiliadoTO.
     * 
     * @param tramo
     */
    public void setTramo(java.lang.String tramo) {
        this.tramo = tramo;
    }


    /**
     * Gets the genero value for this AfiliadoTO.
     * 
     * @return genero
     */
    public java.lang.String getGenero() {
        return genero;
    }


    /**
     * Sets the genero value for this AfiliadoTO.
     * 
     * @param genero
     */
    public void setGenero(java.lang.String genero) {
        this.genero = genero;
    }


    /**
     * Gets the generoDes value for this AfiliadoTO.
     * 
     * @return generoDes
     */
    public java.lang.String getGeneroDes() {
        return generoDes;
    }


    /**
     * Sets the generoDes value for this AfiliadoTO.
     * 
     * @param generoDes
     */
    public void setGeneroDes(java.lang.String generoDes) {
        this.generoDes = generoDes;
    }


    /**
     * Gets the cdgEstado value for this AfiliadoTO.
     * 
     * @return cdgEstado
     */
    public java.lang.String getCdgEstado() {
        return cdgEstado;
    }


    /**
     * Sets the cdgEstado value for this AfiliadoTO.
     * 
     * @param cdgEstado
     */
    public void setCdgEstado(java.lang.String cdgEstado) {
        this.cdgEstado = cdgEstado;
    }


    /**
     * Gets the desEstado value for this AfiliadoTO.
     * 
     * @return desEstado
     */
    public java.lang.String getDesEstado() {
        return desEstado;
    }


    /**
     * Sets the desEstado value for this AfiliadoTO.
     * 
     * @param desEstado
     */
    public void setDesEstado(java.lang.String desEstado) {
        this.desEstado = desEstado;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof AfiliadoTO)) return false;
        AfiliadoTO other = (AfiliadoTO) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            this.rutafili == other.getRutafili() &&
            ((this.dgvafili==null && other.getDgvafili()==null) || 
             (this.dgvafili!=null &&
              this.dgvafili.equals(other.getDgvafili()))) &&
            ((this.nombres==null && other.getNombres()==null) || 
             (this.nombres!=null &&
              this.nombres.equals(other.getNombres()))) &&
            ((this.apell1==null && other.getApell1()==null) || 
             (this.apell1!=null &&
              this.apell1.equals(other.getApell1()))) &&
            ((this.apell2==null && other.getApell2()==null) || 
             (this.apell2!=null &&
              this.apell2.equals(other.getApell2()))) &&
            ((this.fecnac==null && other.getFecnac()==null) || 
             (this.fecnac!=null &&
              this.fecnac.equals(other.getFecnac()))) &&
            ((this.tramo==null && other.getTramo()==null) || 
             (this.tramo!=null &&
              this.tramo.equals(other.getTramo()))) &&
            ((this.genero==null && other.getGenero()==null) || 
             (this.genero!=null &&
              this.genero.equals(other.getGenero()))) &&
            ((this.generoDes==null && other.getGeneroDes()==null) || 
             (this.generoDes!=null &&
              this.generoDes.equals(other.getGeneroDes()))) &&
            ((this.cdgEstado==null && other.getCdgEstado()==null) || 
             (this.cdgEstado!=null &&
              this.cdgEstado.equals(other.getCdgEstado()))) &&
            ((this.desEstado==null && other.getDesEstado()==null) || 
             (this.desEstado!=null &&
              this.desEstado.equals(other.getDesEstado())));
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
        _hashCode += getRutafili();
        if (getDgvafili() != null) {
            _hashCode += getDgvafili().hashCode();
        }
        if (getNombres() != null) {
            _hashCode += getNombres().hashCode();
        }
        if (getApell1() != null) {
            _hashCode += getApell1().hashCode();
        }
        if (getApell2() != null) {
            _hashCode += getApell2().hashCode();
        }
        if (getFecnac() != null) {
            _hashCode += getFecnac().hashCode();
        }
        if (getTramo() != null) {
            _hashCode += getTramo().hashCode();
        }
        if (getGenero() != null) {
            _hashCode += getGenero().hashCode();
        }
        if (getGeneroDes() != null) {
            _hashCode += getGeneroDes().hashCode();
        }
        if (getCdgEstado() != null) {
            _hashCode += getCdgEstado().hashCode();
        }
        if (getDesEstado() != null) {
            _hashCode += getDesEstado().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(AfiliadoTO.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://certificadorprevisional.fonasa.gov.cl.ws/", "AfiliadoTO"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("rutafili");
        elemField.setXmlName(new javax.xml.namespace.QName("http://certificadorprevisional.fonasa.gov.cl.ws/", "rutafili"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("dgvafili");
        elemField.setXmlName(new javax.xml.namespace.QName("http://certificadorprevisional.fonasa.gov.cl.ws/", "dgvafili"));
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
        elemField.setFieldName("fecnac");
        elemField.setXmlName(new javax.xml.namespace.QName("http://certificadorprevisional.fonasa.gov.cl.ws/", "fecnac"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("tramo");
        elemField.setXmlName(new javax.xml.namespace.QName("http://certificadorprevisional.fonasa.gov.cl.ws/", "tramo"));
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
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("cdgEstado");
        elemField.setXmlName(new javax.xml.namespace.QName("http://certificadorprevisional.fonasa.gov.cl.ws/", "cdgEstado"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("desEstado");
        elemField.setXmlName(new javax.xml.namespace.QName("http://certificadorprevisional.fonasa.gov.cl.ws/", "desEstado"));
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
