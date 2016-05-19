package ssmc.CartaRespaldo.controlador.seguridad;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * LogActividad
 * @author Vanessa Duno
 * @version 1.0
 *
 */

@Entity
@Table(name = "log_actividad")
public class LogActividad implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_log")
	private int id;
	
	@Column(name = "fecha")
	private Timestamp fecha;
	
	@Column (name = "login_usuario")
	private String loginUsuario;
	
	@Column(name= "actividad")
	private String actividad;
	
	@Column(name = "descripcion")
	private String descripcion;

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

	public String getLoginUsuario() {
		return loginUsuario;
	}

	public void setLoginUsuario(String loginUsuario) {
		this.loginUsuario = loginUsuario;
	}

	public String getActividad() {
		return actividad;
	}

	public void setActividad(String actividad) {
		this.actividad = actividad;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Log [id=");
		builder.append(id);
		builder.append(", fecha=");
		builder.append(fecha);
		builder.append(", loginUsuario=");
		builder.append(loginUsuario);
		builder.append(", actividad=");
		builder.append(actividad);
		builder.append(", descripcion=");
		builder.append(descripcion);
		builder.append("]");
		return builder.toString();
	} 

}
