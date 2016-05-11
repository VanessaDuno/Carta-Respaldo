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

/** CategoriaPrestacion
 * @author Vanessa Maria Duno
 * @version 1.0
 */

@Entity
@Table (name = "categoria_prestacion")
public class CategoriaPrestacion implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	@Column (name = "id_categoria_prestacion")
	private int id;
	
	@Column(name = "codigo")
	private String codigo; 
	
	@Column(name = "nombre")
	private String nombre;
	
	@ManyToOne (fetch = FetchType.LAZY)
	@JoinColumn (name= "id_prestacion")
	private Prestacion prestacion;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("CategoriaPrestacion [id=");
		builder.append(id);
		builder.append(", codigo=");
		builder.append(codigo);
		builder.append(", nombre=");
		builder.append(nombre);
		builder.append(", prestacion=");
		builder.append(prestacion);
		builder.append("]");
		return builder.toString();
	}

	public Prestacion getPrestacion() {
		return prestacion;
	}

	public void setPrestacion(Prestacion prestacion) {
		this.prestacion = prestacion;
	} 
	
}
