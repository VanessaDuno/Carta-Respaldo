/**
 * 
 */
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

import ssmc.CartaRespaldo.modelo.maestros.Responsable;

/**
 * ResponsablesSolicitud
 * 
 * @author Vanessa Maria Duno
 * @version 1.0
 */
@Entity
@Table(name = "responsable_solicitud")
public class ResponsableSolicitud implements Serializable {

	/**
	 * 
	 */

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_responsables_establecimientos")
	private int id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_responsable")
	private Responsable responsable;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_solicitud_traslado")
	private SolicitudTraslado solicitudTraslado;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Responsable getResponsable() {
		return responsable;
	}

	public void setResponsable(Responsable responsable) {
		this.responsable = responsable;
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
		builder.append("ResponsablesSolicitud [id=");
		builder.append(id);
		builder.append(", responsable=");
		builder.append(responsable);
		builder.append(", solicitudTraslado=");
		builder.append(solicitudTraslado);
		builder.append("]");
		return builder.toString();
	}
	
	

}
