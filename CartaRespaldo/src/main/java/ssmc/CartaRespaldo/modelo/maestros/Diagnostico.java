package ssmc.CartaRespaldo.modelo.maestros;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Diagnostico
 * 
 * @author Vanessa Duno
 * @version 1.0
 * 
 */

@Entity
@Table(name = "diagnostico")
public class Diagnostico implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_diagnostico")
	private long id;

	@Column(name = "nombre")
	private String nombre;

	@Column(name = "codigo_cie")
	private String codigoCie;

	@Column(name = "padre")
	private long padre;

	@Column(name = "hoja")
	private boolean hoja;

	public Diagnostico() {
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getCodigoCie() {
		return codigoCie;
	}

	public void setCodigoCie(String codigoCie) {
		this.codigoCie = codigoCie;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Diagnostico [id=");
		builder.append(id);
		builder.append(", nombre=");
		builder.append(nombre);
		builder.append(", codigoCie=");
		builder.append(codigoCie);
		builder.append(", padre=");
		builder.append(padre);
		builder.append(", hoja=");
		builder.append(hoja);
		builder.append("]");
		return builder.toString();
	}

	public long getPadre() {
		return padre;
	}

	public void setPadre(long padre) {
		this.padre = padre;
	}

	public boolean isHoja() {
		return hoja;
	}

	public void setHoja(boolean hoja) {
		this.hoja = hoja;
	}
}
