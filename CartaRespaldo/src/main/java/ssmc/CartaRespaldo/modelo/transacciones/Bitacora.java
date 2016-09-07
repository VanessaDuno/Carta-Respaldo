package ssmc.CartaRespaldo.modelo.transacciones;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Arrays;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import ssmc.CartaRespaldo.modelo.maestros.Motivo;
import ssmc.CartaRespaldo.modelo.seguridad.Usuario;

/**
 * Bitacora
 * @author Vanessa Duno
 * @version 1.0
 *
 */

@Entity
@Table(name = "bitacora")
public class Bitacora implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_bitacora")
	private int id;

	@Column(name = "fecha")
	private Timestamp fecha;

	@Column(name = "estatus")
	private String estatus;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_solicitud_traslado")
	private SolicitudTraslado traslado;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_motivo")
	private Motivo motivo;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_usuario")
	private Usuario usuario;
	
	@Column (name="observacion_cierre_clinico")
	private String observacionCierreClinico;
	
	@Column (name= "cuenta")
	private String cuenta; 
	
	@Column (name= "epicrisis")
	private String epicrisis; 
	
	private byte[] carta;
	
	private String resolucion;
	
	@Column (name = "validada")
	private boolean validada; 
	
	@Column(name= "activo")
	private boolean activo; 
	
	@Column(name= "motivo_anulacion")
	private String motivoAnulacion; 
	
	private String epicrisisInforme;
	
	@Column(name = "id_sigfe")
	private String idSigfe; 
	
	@Column (name = "motivo_cierre_clinico")
	private String motivoCierreClinico; 

	@Column (name = "numero_factura")
	private String numeroFactura;
	
	@Column (name = "fecha_recepcion")
	private Timestamp fechaRecepcion;
	
	@Column(name = "monto_cobrado")
	private double montoCobrado; 
	
	@Column(name = "fecha_ingreso_cuenta")
	private Timestamp fechaIngresoCuenta;
	
	@Column (name= "observacion_visacion")
	private String observacionVisacion;
	
	@Column (name= "monto_descuento")
	private double montoDescuento;
	
	@Column(name = "total_dias_cama_uci")
	private int totalDiasCamaUci;
	
	@Column(name = "total_dias_cama_uti")
	private int totalDiasCamaUti;
	
	@Column(name = "total_dias_cama_basica")
	private int totalDiasCamaBasica;
	
	@Column (name= "monto_pago")
	private double montoPago;
	
	@Column(name = "prestaciones_adicionales")
	private double prestacionesAdicionales;	
	
	@Column(name = "numero_orden_compra")
	private String ordenCompra; 
	
	@Column(name = "fecha_ingreso_paciente")
	private Timestamp fechaIngresoPaciente;
	
	@Column(name = "fecha_egreso_paciente")
	private Timestamp fechaEgresoPaciente;
	
	@Column(name="tipo_cuenta")
	private String tipoCuenta; 
	
	@Column(name="ruta_acta_valorizacion")
	private String rutaActaValorizacion;
	
	@Column(name ="ruta_acta_auditoria")
	private String rutaActaAuditoria;
	
	@Column(name = "ruta_memorandum")
	private String rutaMemorandum; 
	
	
	public Bitacora() {
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Timestamp getFecha() {
		return fecha;
	}

	public void setFecha(Timestamp fecha) {
		this.fecha = fecha;
	}

	public SolicitudTraslado getTraslado() {
		return traslado;
	}

	public void setTraslado(SolicitudTraslado traslado) {
		this.traslado = traslado;
	}

	public Motivo getMotivo() {
		return motivo;
	}

	public void setMotivo(Motivo motivo) {
		this.motivo = motivo;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Bitacora [id=");
		builder.append(id);
		builder.append(", fecha=");
		builder.append(fecha);
		builder.append(", estatus=");
		builder.append(estatus);
		builder.append(", traslado=");
		builder.append(traslado);
		builder.append(", motivo=");
		builder.append(motivo);
		builder.append(", usuario=");
		builder.append(usuario);
		builder.append(", observacionCierreClinico=");
		builder.append(observacionCierreClinico);
		builder.append(", cuenta=");
		builder.append(cuenta);
		builder.append(", epicrisis=");
		builder.append(epicrisis);
		builder.append(", carta=");
		builder.append(Arrays.toString(carta));
		builder.append(", resolucion=");
		builder.append(resolucion);
		builder.append(", validada=");
		builder.append(validada);
		builder.append(", activo=");
		builder.append(activo);
		builder.append(", motivoAnulacion=");
		builder.append(motivoAnulacion);
		builder.append(", epicrisisInforme=");
		builder.append(epicrisisInforme);
		builder.append(", idSigfe=");
		builder.append(idSigfe);
		builder.append(", motivoCierreClinico=");
		builder.append(motivoCierreClinico);
		builder.append(", numeroFactura=");
		builder.append(numeroFactura);
		builder.append(", fechaRecepcion=");
		builder.append(fechaRecepcion);
		builder.append(", montoCobrado=");
		builder.append(montoCobrado);
		builder.append(", fechaIngresoCuenta=");
		builder.append(fechaIngresoCuenta);
		builder.append(", observacionVisacion=");
		builder.append(observacionVisacion);
		builder.append(", montoDescuento=");
		builder.append(montoDescuento);
		builder.append(", totalDiasCamaUci=");
		builder.append(totalDiasCamaUci);
		builder.append(", totalDiasCamaUti=");
		builder.append(totalDiasCamaUti);
		builder.append(", totalDiasCamaBasica=");
		builder.append(totalDiasCamaBasica);
		builder.append(", montoPago=");
		builder.append(montoPago);
		builder.append(", prestacionesAdicionales=");
		builder.append(prestacionesAdicionales);
		builder.append(", ordenCompra=");
		builder.append(ordenCompra);
		builder.append(", fechaIngresoPaciente=");
		builder.append(fechaIngresoPaciente);
		builder.append(", fechaEgresoPaciente=");
		builder.append(fechaEgresoPaciente);
		builder.append(", tipoCuenta=");
		builder.append(tipoCuenta);
		builder.append(", rutaActaValorizacion=");
		builder.append(rutaActaValorizacion);
		builder.append(", rutaActaAuditoria=");
		builder.append(rutaActaAuditoria);
		builder.append(", rutaMemorandum=");
		builder.append(rutaMemorandum);
		builder.append("]");
		return builder.toString();
	}

	public String getEstatus() {
		return estatus;
	}

	public void setEstatus(String estatus) {
		this.estatus = estatus;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public byte[] getCarta() {
		return carta;
	}

	public void setCarta(byte[] carta) {
		this.carta = carta;
	}

	public boolean isValidada() {
		return validada;
	}

	public void setValidada(boolean validada) {
		this.validada = validada;
	}

	public boolean isActivo() {
		return activo;
	}

	public void setActivo(boolean activo) {
		this.activo = activo;
	}

	public String getObservacionCierreClinico() {
		return observacionCierreClinico;
	}

	public void setObservacionCierreClinico(String observacionCierreClinico) {
		this.observacionCierreClinico = observacionCierreClinico;
	}

	public String getCuenta() {
		return cuenta;
	}

	public void setCuenta(String cuenta) {
		this.cuenta = cuenta;
	}

	public String getEpicrisis() {
		return epicrisis;
	}

	public void setEpicrisis(String epicrisis) {
		this.epicrisis = epicrisis;
	}

	public String getResolucion() {
		return resolucion;
	}

	public void setResolucion(String resolucion) {
		this.resolucion = resolucion;
	}

	public String getMotivoAnulacion() {
		return motivoAnulacion;
	}

	public void setMotivoAnulacion(String motivoAnulacion) {
		this.motivoAnulacion = motivoAnulacion;
	}

	public String getEpicrisisInforme() {
		return epicrisisInforme;
	}

	public void setEpicrisisInforme(String epicrisisInforme) {
		this.epicrisisInforme = epicrisisInforme;
	}

	public String getIdSigfe() {
		return idSigfe;
	}

	public void setIdSigfe(String idSigfe) {
		this.idSigfe = idSigfe;
	}

	public String getMotivoCierreClinico() {
		return motivoCierreClinico;
	}

	public void setMotivoCierreClinico(String motivoCierreClinico) {
		this.motivoCierreClinico = motivoCierreClinico;
	}

	public String getNumeroFactura() {
		return numeroFactura;
	}

	public void setNumeroFactura(String numeroFactura) {
		this.numeroFactura = numeroFactura;
	}

	public Timestamp getFechaRecepcion() {
		return fechaRecepcion;
	}

	public void setFechaRecepcion(Timestamp fechaRecepcion) {
		this.fechaRecepcion = fechaRecepcion;
	}

	public double getMontoCobrado() {
		return montoCobrado;
	}

	public void setMontoCobrado(double montoCobrado) {
		this.montoCobrado = montoCobrado;
	}

	public Timestamp getFechaIngresoCuenta() {
		return fechaIngresoCuenta;
	}

	public void setFechaIngresoCuenta(Timestamp fechaIngresoCuenta) {
		this.fechaIngresoCuenta = fechaIngresoCuenta;
	}

	public String getObservacionVisacion() {
		return observacionVisacion;
	}

	public void setObservacionVisacion(String observacionVisacion) {
		this.observacionVisacion = observacionVisacion;
	}

	public double getMontoDescuento() {
		return montoDescuento;
	}

	public void setMontoDescuento(double montoDescuento) {
		this.montoDescuento = montoDescuento;
	}

	public double getMontoPago() {
		return montoPago;
	}

	public void setMontoPago(double montoPago) {
		this.montoPago = montoPago;
	}

	public double getPrestacionesAdicionales() {
		return prestacionesAdicionales;
	}

	public void setPrestacionesAdicionales(double prestacionesAdicionales) {
		this.prestacionesAdicionales = prestacionesAdicionales;
	}

	public String getOrdenCompra() {
		return ordenCompra;
	}

	public void setOrdenCompra(String ordenCompra) {
		this.ordenCompra = ordenCompra;
	}

	public int getTotalDiasCamaUci() {
		return totalDiasCamaUci;
	}

	public void setTotalDiasCamaUci(int totalDiasCamaUci) {
		this.totalDiasCamaUci = totalDiasCamaUci;
	}

	public int getTotalDiasCamaUti() {
		return totalDiasCamaUti;
	}

	public void setTotalDiasCamaUti(int totalDiasCamaUti) {
		this.totalDiasCamaUti = totalDiasCamaUti;
	}

	public int getTotalDiasCamaBasica() {
		return totalDiasCamaBasica;
	}

	public void setTotalDiasCamaBasica(int totalDiasCamaBasica) {
		this.totalDiasCamaBasica = totalDiasCamaBasica;
	}

	public Timestamp getFechaIngresoPaciente() {
		return fechaIngresoPaciente;
	}

	public void setFechaIngresoPaciente(Timestamp fechaIngresoPaciente) {
		this.fechaIngresoPaciente = fechaIngresoPaciente;
	}

	public Timestamp getFechaEgresoPaciente() {
		return fechaEgresoPaciente;
	}

	public void setFechaEgresoPaciente(Timestamp fechaEgresoPaciente) {
		this.fechaEgresoPaciente = fechaEgresoPaciente;
	}

	public String getTipoCuenta() {
		return tipoCuenta;
	}

	public void setTipoCuenta(String tipoCuenta) {
		this.tipoCuenta = tipoCuenta;
	}

	public String getRutaActaValorizacion() {
		return rutaActaValorizacion;
	}

	public void setRutaActaValorizacion(String rutaActaValorizacion) {
		this.rutaActaValorizacion = rutaActaValorizacion;
	}

	public String getRutaActaAuditoria() {
		return rutaActaAuditoria;
	}

	public void setRutaActaAuditoria(String rutaActaAuditoria) {
		this.rutaActaAuditoria = rutaActaAuditoria;
	}

	public String getRutaMemorandum() {
		return rutaMemorandum;
	}

	public void setRutaMemorandum(String rutaMemorandum) {
		this.rutaMemorandum = rutaMemorandum;
	}

}
