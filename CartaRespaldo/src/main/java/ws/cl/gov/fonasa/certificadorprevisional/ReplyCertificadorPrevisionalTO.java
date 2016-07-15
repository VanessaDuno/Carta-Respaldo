/**
 * ReplyCertificadorPrevisionalTO.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package ws.cl.gov.fonasa.certificadorprevisional;

public class ReplyCertificadorPrevisionalTO  implements java.io.Serializable {
    private ws.cl.gov.fonasa.certificadorprevisional.ReplyTO replyTO;

    private long folio;

    private java.lang.String codcybl;

    private java.lang.String coddesc;

    private int numeroCarga;

    private java.lang.String codigoprais;

    private java.lang.String descprais;

    private java.lang.String cdgIsapre;

    private java.lang.String desIsapre;

    private ws.cl.gov.fonasa.certificadorprevisional.BeneficiarioTO beneficiarioTO;

    private ws.cl.gov.fonasa.certificadorprevisional.AfiliadoTO afiliadoTO;

    private ws.cl.gov.fonasa.certificadorprevisional.CargasTO[] listCargas;

    public ReplyCertificadorPrevisionalTO() {
    }

    public ReplyCertificadorPrevisionalTO(
           ws.cl.gov.fonasa.certificadorprevisional.ReplyTO replyTO,
           long folio,
           java.lang.String codcybl,
           java.lang.String coddesc,
           int numeroCarga,
           java.lang.String codigoprais,
           java.lang.String descprais,
           java.lang.String cdgIsapre,
           java.lang.String desIsapre,
           ws.cl.gov.fonasa.certificadorprevisional.BeneficiarioTO beneficiarioTO,
           ws.cl.gov.fonasa.certificadorprevisional.AfiliadoTO afiliadoTO,
           ws.cl.gov.fonasa.certificadorprevisional.CargasTO[] listCargas) {
           this.replyTO = replyTO;
           this.folio = folio;
           this.codcybl = codcybl;
           this.coddesc = coddesc;
           this.numeroCarga = numeroCarga;
           this.codigoprais = codigoprais;
           this.descprais = descprais;
           this.cdgIsapre = cdgIsapre;
           this.desIsapre = desIsapre;
           this.beneficiarioTO = beneficiarioTO;
           this.afiliadoTO = afiliadoTO;
           this.listCargas = listCargas;
    }


    /**
     * Gets the replyTO value for this ReplyCertificadorPrevisionalTO.
     * 
     * @return replyTO
     */
    public ws.cl.gov.fonasa.certificadorprevisional.ReplyTO getReplyTO() {
        return replyTO;
    }


    /**
     * Sets the replyTO value for this ReplyCertificadorPrevisionalTO.
     * 
     * @param replyTO
     */
    public void setReplyTO(ws.cl.gov.fonasa.certificadorprevisional.ReplyTO replyTO) {
        this.replyTO = replyTO;
    }


    /**
     * Gets the folio value for this ReplyCertificadorPrevisionalTO.
     * 
     * @return folio
     */
    public long getFolio() {
        return folio;
    }


    /**
     * Sets the folio value for this ReplyCertificadorPrevisionalTO.
     * 
     * @param folio
     */
    public void setFolio(long folio) {
        this.folio = folio;
    }


    /**
     * Gets the codcybl value for this ReplyCertificadorPrevisionalTO.
     * 
     * @return codcybl
     */
    public java.lang.String getCodcybl() {
        return codcybl;
    }


    /**
     * Sets the codcybl value for this ReplyCertificadorPrevisionalTO.
     * 
     * @param codcybl
     */
    public void setCodcybl(java.lang.String codcybl) {
        this.codcybl = codcybl;
    }


    /**
     * Gets the coddesc value for this ReplyCertificadorPrevisionalTO.
     * 
     * @return coddesc
     */
    public java.lang.String getCoddesc() {
        return coddesc;
    }


    /**
     * Sets the coddesc value for this ReplyCertificadorPrevisionalTO.
     * 
     * @param coddesc
     */
    public void setCoddesc(java.lang.String coddesc) {
        this.coddesc = coddesc;
    }


    /**
     * Gets the numeroCarga value for this ReplyCertificadorPrevisionalTO.
     * 
     * @return numeroCarga
     */
    public int getNumeroCarga() {
        return numeroCarga;
    }


    /**
     * Sets the numeroCarga value for this ReplyCertificadorPrevisionalTO.
     * 
     * @param numeroCarga
     */
    public void setNumeroCarga(int numeroCarga) {
        this.numeroCarga = numeroCarga;
    }


    /**
     * Gets the codigoprais value for this ReplyCertificadorPrevisionalTO.
     * 
     * @return codigoprais
     */
    public java.lang.String getCodigoprais() {
        return codigoprais;
    }


    /**
     * Sets the codigoprais value for this ReplyCertificadorPrevisionalTO.
     * 
     * @param codigoprais
     */
    public void setCodigoprais(java.lang.String codigoprais) {
        this.codigoprais = codigoprais;
    }


    /**
     * Gets the descprais value for this ReplyCertificadorPrevisionalTO.
     * 
     * @return descprais
     */
    public java.lang.String getDescprais() {
        return descprais;
    }


    /**
     * Sets the descprais value for this ReplyCertificadorPrevisionalTO.
     * 
     * @param descprais
     */
    public void setDescprais(java.lang.String descprais) {
        this.descprais = descprais;
    }


    /**
     * Gets the cdgIsapre value for this ReplyCertificadorPrevisionalTO.
     * 
     * @return cdgIsapre
     */
    public java.lang.String getCdgIsapre() {
        return cdgIsapre;
    }


    /**
     * Sets the cdgIsapre value for this ReplyCertificadorPrevisionalTO.
     * 
     * @param cdgIsapre
     */
    public void setCdgIsapre(java.lang.String cdgIsapre) {
        this.cdgIsapre = cdgIsapre;
    }


    /**
     * Gets the desIsapre value for this ReplyCertificadorPrevisionalTO.
     * 
     * @return desIsapre
     */
    public java.lang.String getDesIsapre() {
        return desIsapre;
    }


    /**
     * Sets the desIsapre value for this ReplyCertificadorPrevisionalTO.
     * 
     * @param desIsapre
     */
    public void setDesIsapre(java.lang.String desIsapre) {
        this.desIsapre = desIsapre;
    }


    /**
     * Gets the beneficiarioTO value for this ReplyCertificadorPrevisionalTO.
     * 
     * @return beneficiarioTO
     */
    public ws.cl.gov.fonasa.certificadorprevisional.BeneficiarioTO getBeneficiarioTO() {
        return beneficiarioTO;
    }


    /**
     * Sets the beneficiarioTO value for this ReplyCertificadorPrevisionalTO.
     * 
     * @param beneficiarioTO
     */
    public void setBeneficiarioTO(ws.cl.gov.fonasa.certificadorprevisional.BeneficiarioTO beneficiarioTO) {
        this.beneficiarioTO = beneficiarioTO;
    }


    /**
     * Gets the afiliadoTO value for this ReplyCertificadorPrevisionalTO.
     * 
     * @return afiliadoTO
     */
    public ws.cl.gov.fonasa.certificadorprevisional.AfiliadoTO getAfiliadoTO() {
        return afiliadoTO;
    }


    /**
     * Sets the afiliadoTO value for this ReplyCertificadorPrevisionalTO.
     * 
     * @param afiliadoTO
     */
    public void setAfiliadoTO(ws.cl.gov.fonasa.certificadorprevisional.AfiliadoTO afiliadoTO) {
        this.afiliadoTO = afiliadoTO;
    }


    /**
     * Gets the listCargas value for this ReplyCertificadorPrevisionalTO.
     * 
     * @return listCargas
     */
    public ws.cl.gov.fonasa.certificadorprevisional.CargasTO[] getListCargas() {
        return listCargas;
    }


    /**
     * Sets the listCargas value for this ReplyCertificadorPrevisionalTO.
     * 
     * @param listCargas
     */
    public void setListCargas(ws.cl.gov.fonasa.certificadorprevisional.CargasTO[] listCargas) {
        this.listCargas = listCargas;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ReplyCertificadorPrevisionalTO)) return false;
        ReplyCertificadorPrevisionalTO other = (ReplyCertificadorPrevisionalTO) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.replyTO==null && other.getReplyTO()==null) || 
             (this.replyTO!=null &&
              this.replyTO.equals(other.getReplyTO()))) &&
            this.folio == other.getFolio() &&
            ((this.codcybl==null && other.getCodcybl()==null) || 
             (this.codcybl!=null &&
              this.codcybl.equals(other.getCodcybl()))) &&
            ((this.coddesc==null && other.getCoddesc()==null) || 
             (this.coddesc!=null &&
              this.coddesc.equals(other.getCoddesc()))) &&
            this.numeroCarga == other.getNumeroCarga() &&
            ((this.codigoprais==null && other.getCodigoprais()==null) || 
             (this.codigoprais!=null &&
              this.codigoprais.equals(other.getCodigoprais()))) &&
            ((this.descprais==null && other.getDescprais()==null) || 
             (this.descprais!=null &&
              this.descprais.equals(other.getDescprais()))) &&
            ((this.cdgIsapre==null && other.getCdgIsapre()==null) || 
             (this.cdgIsapre!=null &&
              this.cdgIsapre.equals(other.getCdgIsapre()))) &&
            ((this.desIsapre==null && other.getDesIsapre()==null) || 
             (this.desIsapre!=null &&
              this.desIsapre.equals(other.getDesIsapre()))) &&
            ((this.beneficiarioTO==null && other.getBeneficiarioTO()==null) || 
             (this.beneficiarioTO!=null &&
              this.beneficiarioTO.equals(other.getBeneficiarioTO()))) &&
            ((this.afiliadoTO==null && other.getAfiliadoTO()==null) || 
             (this.afiliadoTO!=null &&
              this.afiliadoTO.equals(other.getAfiliadoTO()))) &&
            ((this.listCargas==null && other.getListCargas()==null) || 
             (this.listCargas!=null &&
              java.util.Arrays.equals(this.listCargas, other.getListCargas())));
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
        if (getReplyTO() != null) {
            _hashCode += getReplyTO().hashCode();
        }
        _hashCode += new Long(getFolio()).hashCode();
        if (getCodcybl() != null) {
            _hashCode += getCodcybl().hashCode();
        }
        if (getCoddesc() != null) {
            _hashCode += getCoddesc().hashCode();
        }
        _hashCode += getNumeroCarga();
        if (getCodigoprais() != null) {
            _hashCode += getCodigoprais().hashCode();
        }
        if (getDescprais() != null) {
            _hashCode += getDescprais().hashCode();
        }
        if (getCdgIsapre() != null) {
            _hashCode += getCdgIsapre().hashCode();
        }
        if (getDesIsapre() != null) {
            _hashCode += getDesIsapre().hashCode();
        }
        if (getBeneficiarioTO() != null) {
            _hashCode += getBeneficiarioTO().hashCode();
        }
        if (getAfiliadoTO() != null) {
            _hashCode += getAfiliadoTO().hashCode();
        }
        if (getListCargas() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getListCargas());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getListCargas(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(ReplyCertificadorPrevisionalTO.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://certificadorprevisional.fonasa.gov.cl.ws/", "ReplyCertificadorPrevisionalTO"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("replyTO");
        elemField.setXmlName(new javax.xml.namespace.QName("http://certificadorprevisional.fonasa.gov.cl.ws/", "replyTO"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://certificadorprevisional.fonasa.gov.cl.ws/", "replyTO"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("folio");
        elemField.setXmlName(new javax.xml.namespace.QName("http://certificadorprevisional.fonasa.gov.cl.ws/", "folio"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "long"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("codcybl");
        elemField.setXmlName(new javax.xml.namespace.QName("http://certificadorprevisional.fonasa.gov.cl.ws/", "codcybl"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("coddesc");
        elemField.setXmlName(new javax.xml.namespace.QName("http://certificadorprevisional.fonasa.gov.cl.ws/", "coddesc"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("numeroCarga");
        elemField.setXmlName(new javax.xml.namespace.QName("http://certificadorprevisional.fonasa.gov.cl.ws/", "numeroCarga"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("codigoprais");
        elemField.setXmlName(new javax.xml.namespace.QName("http://certificadorprevisional.fonasa.gov.cl.ws/", "codigoprais"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("descprais");
        elemField.setXmlName(new javax.xml.namespace.QName("http://certificadorprevisional.fonasa.gov.cl.ws/", "descprais"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("cdgIsapre");
        elemField.setXmlName(new javax.xml.namespace.QName("http://certificadorprevisional.fonasa.gov.cl.ws/", "cdgIsapre"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("desIsapre");
        elemField.setXmlName(new javax.xml.namespace.QName("http://certificadorprevisional.fonasa.gov.cl.ws/", "desIsapre"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("beneficiarioTO");
        elemField.setXmlName(new javax.xml.namespace.QName("http://certificadorprevisional.fonasa.gov.cl.ws/", "beneficiarioTO"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://certificadorprevisional.fonasa.gov.cl.ws/", "BeneficiarioTO"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("afiliadoTO");
        elemField.setXmlName(new javax.xml.namespace.QName("http://certificadorprevisional.fonasa.gov.cl.ws/", "afiliadoTO"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://certificadorprevisional.fonasa.gov.cl.ws/", "AfiliadoTO"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("listCargas");
        elemField.setXmlName(new javax.xml.namespace.QName("http://certificadorprevisional.fonasa.gov.cl.ws/", "listCargas"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://certificadorprevisional.fonasa.gov.cl.ws/", "CargasTO"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        elemField.setItemQName(new javax.xml.namespace.QName("http://certificadorprevisional.fonasa.gov.cl.ws/", "item"));
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
