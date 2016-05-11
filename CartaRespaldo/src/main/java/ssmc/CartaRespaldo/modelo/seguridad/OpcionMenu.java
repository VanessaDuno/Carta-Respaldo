package ssmc.CartaRespaldo.modelo.seguridad;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * OpcionMenu
 * @author Vanessa Duno
 * @version 1.0
 *
 */

@Entity
@Table (name = "menu")
public class OpcionMenu implements Serializable {



	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column (name = "id_menu")
	private int idMenu;
	
	@Column (name = "nombre")
	private String nombre;
	
	@Column (name= "url")
	private String url;

	@Column (name = "estado")
	private int estado;
	
	@Column (name = "id_padre")
	private int idPadre;
	
	public int getIdMenu() {
		return idMenu;
	}

	public void setIdMenu(int idMenu) {
		this.idMenu = idMenu;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public int isEstado() {
		return estado;
	}

	public void setEstado(int estado) {
		this.estado = estado;
	}
	
	public int getIdPadre() {
		return idPadre;
	}

	public void setIdPadre(int idPadre) {
		this.idPadre = idPadre;
	}

	public int getEstado() {
		return estado;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("OpcionMenu [idMenu=");
		builder.append(idMenu);
		builder.append(", nombre=");
		builder.append(nombre);
		builder.append(", url=");
		builder.append(url);
		builder.append(", estado=");
		builder.append(estado);
		builder.append(", idPadre=");
		builder.append(idPadre);
		builder.append("]");
		return builder.toString();
	}
	
	
	
}
	