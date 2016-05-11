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

/**
 * ServicioClinico
 * @author Vanessa Duno
 * @version 1.0
 *
 */

@Entity
@Table(name = "servicio_clinico")
public class ServicioClinico implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	@Column(name="id_servicio_clinico")
	private int id; 
	
	@Column(name= "nombre")
	private String nombre; 
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name= "id_unidad")
	private Unidad unidad; 

	public ServicioClinico() {
	}

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

	public Unidad getUnidad() {
		return unidad;
	}

	public void setUnidad(Unidad unidad) {
		this.unidad = unidad;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("ServicioClinico [id=");
		builder.append(id);
		builder.append(", nombre=");
		builder.append(nombre);
		builder.append(", unidad=");
		builder.append(unidad);
		builder.append("]");
		return builder.toString();
	}
}
