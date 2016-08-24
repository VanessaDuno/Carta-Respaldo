package ssmc.CartaRespaldo.modelo.transacciones;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Arrays;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.zkoss.image.AImage;
import org.zkoss.util.media.Media;

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
	
	private byte[] archivoCuenta;
	
	@Column (name = "validada")
	private boolean validada; 
	
	@Column(name= "activo")
	private boolean activo; 
	
	@Column(name= "motivo_anulacion")
	private String motivoAnulacion; 
	
	private byte[] epicrisisInforme;
	
	@Column(name = "id_sigfe")
	private String idSigfe; 
	
	@Column (name = "motivo_cierre_clinico")
	private String motivoCierreClinico; 

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
		builder.append(", archivoCuenta=");
		builder.append(Arrays.toString(archivoCuenta));
		builder.append(", validada=");
		builder.append(validada);
		builder.append(", activo=");
		builder.append(activo);
		builder.append(", motivoAnulacion=");
		builder.append(motivoAnulacion);
		builder.append(", epicrisisInforme=");
		builder.append(Arrays.toString(epicrisisInforme));
		builder.append(", idSigfe=");
		builder.append(idSigfe);
		builder.append(", motivoCierreClinico=");
		builder.append(motivoCierreClinico);
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

	public byte[] getArchivoCuenta() {
		return archivoCuenta;
	}

	public void setArchivoCuenta(byte[] archivoCuenta) {
		this.archivoCuenta = archivoCuenta;
	}

	public String getMotivoAnulacion() {
		return motivoAnulacion;
	}

	public void setMotivoAnulacion(String motivoAnulacion) {
		this.motivoAnulacion = motivoAnulacion;
	}

	public byte[] getEpicrisisInforme() {
		return epicrisisInforme;
	}

	public void setEpicrisisInforme(byte[] epicrisisInforme) {
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

}
