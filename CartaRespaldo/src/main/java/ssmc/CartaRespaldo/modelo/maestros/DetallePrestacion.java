/**
 * 
 */
package ssmc.CartaRespaldo.modelo.maestros;

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

/** DetallePrestacion
 * @author Vanessa Maria Duno
 * @version 1.0
 */

@Entity
@Table(name= "detalle_prestacion")
public class DetallePrestacion implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column (name = "id_detalle_prestacion")
	private int id;
	
	@Column (name= "nombre", length = 350)
	private String nombre; 
	
	@ManyToOne (fetch = FetchType.LAZY)
	@JoinColumn (name= "id_prestacion")
	private Prestacion prestacion;
	
	@Column (name= "codigo")
	private String codigo; 

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Prestacion getPrestacion() {
		return prestacion;
	}

	public void setPrestacion(Prestacion prestacion) {
		this.prestacion = prestacion;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("DetallePrestacion [id=");
		builder.append(id);
		builder.append(", nombre=");
		builder.append(nombre);
		builder.append(", prestacion=");
		builder.append(prestacion);
		builder.append(", codigo=");
		builder.append(codigo);
		builder.append("]");
		return builder.toString();
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	} 
	
	
}
