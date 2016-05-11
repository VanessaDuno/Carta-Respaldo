package ssmc.CartaRespaldo.modelo.transacciones;

import java.io.Serializable;
import java.sql.Timestamp;

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

}
