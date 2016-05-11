package ssmc.CartaRespaldo.modelo.transacciones;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import ssmc.CartaRespaldo.modelo.maestros.DetallePrestacion;
import ssmc.CartaRespaldo.modelo.maestros.Motivo;

/**
 * PrestacionSolicitud
 * @author Vanessa Duno
 * @version 1.0
 *
 */

@Entity
@Table(name="prestacion_solicitud")
public class PrestacionSolicitud implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	@Column(name="id_prestacion_solicitud")
	private int id;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="id_solicitud_traslado")
	private SolicitudTraslado solicitudTraslado;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="id_detalle_prestacion")
	private DetallePrestacion prestacion; 
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="id_motivo")
	private Motivo motivo; 
	
	@Column(name= "tipo_cama")
	private String tipoCama; 

	public PrestacionSolicitud() {
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public SolicitudTraslado getSolicitudTraslado() {
		return solicitudTraslado;
	}

	public void setSolicitudTraslado(SolicitudTraslado solicitudTraslado) {
		this.solicitudTraslado = solicitudTraslado;
	}


	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("PrestacionSolicitud [id=");
		builder.append(id);
		builder.append(", solicitudTraslado=");
		builder.append(solicitudTraslado);
		builder.append(", prestacion=");
		builder.append(prestacion);
		builder.append(", motivo=");
		builder.append(motivo);
		builder.append(", tipoCama=");
		builder.append(tipoCama);
		builder.append("]");
		return builder.toString();
	}

	public DetallePrestacion getPrestacion() {
		return prestacion;
	}

	public void setPrestacion(DetallePrestacion prestacion) {
		this.prestacion = prestacion;
	}

	public Motivo getMotivo() {
		return motivo;
	}

	public void setMotivo(Motivo motivo) {
		this.motivo = motivo;
	}

	public String getTipoCama() {
		return tipoCama;
	}

	public void setTipoCama(String tipoCama) {
		this.tipoCama = tipoCama;
	}
}
