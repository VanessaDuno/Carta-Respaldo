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
 * Establecimiento
 * @author Vanessa Duno
 * @version 1.0
 *
 */

@Entity
@Table(name= "establecimiento")
public class Establecimiento implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name= "id_establecimiento")
	private int id;
	
	@Column(name = "nombre")
	private String nombre;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="id")
	private Region region;
	
	@Column (name ="rut")
	private String rut; 
	
	@Column (name = "isDestino")
	private boolean isDestino;
	
	@Column(name= "telefono")
	private String telefono;
	
	@Column (name = "direccion")
	private String direccion;

	@Column (name = "cantidad_firmantes")
	private int cantidadFirmantes; 
	
	public Establecimiento() {
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

	public Region getRegion() {
		return region;
	}

	public void setRegion(Region region) {
		this.region = region;
	}

	public String getRut() {
		return rut;
	}

	public void setRut(String rut) {
		this.rut = rut;
	}

	public boolean isDestino() {
		return isDestino;
	}

	public void setDestino(boolean isDestino) {
		this.isDestino = isDestino;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Establecimiento [id=");
		builder.append(id);
		builder.append(", nombre=");
		builder.append(nombre);
		builder.append(", region=");
		builder.append(region);
		builder.append(", rut=");
		builder.append(rut);
		builder.append(", isDestino=");
		builder.append(isDestino);
		builder.append(", telefono=");
		builder.append(telefono);
		builder.append(", direccion=");
		builder.append(direccion);
		builder.append(", cantidadFirmantes=");
		builder.append(cantidadFirmantes);
		builder.append("]");
		return builder.toString();
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public int getCantidadFirmantes() {
		return cantidadFirmantes;
	}

	public void setCantidadFirmantes(int cantidadFirmantes) {
		this.cantidadFirmantes = cantidadFirmantes;
	}
}
