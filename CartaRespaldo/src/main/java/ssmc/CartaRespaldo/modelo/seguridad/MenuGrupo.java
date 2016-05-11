package ssmc.CartaRespaldo.modelo.seguridad;

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
 * MenuGrupo
 * @author Vanessa Duno
 * @version 1.0
 *
 */

@Entity
@Table (name = "menu_grupo")
public class MenuGrupo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column (name= "id_menu_grupo")
	private int id;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="id_grupo")
	private Grupo grupo;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="id_menu")
	private OpcionMenu opionMenu;


	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Grupo getGrupo() {
		return grupo;
	}

	public void setGrupo(Grupo grupo) {
		this.grupo = grupo;
	}

	public OpcionMenu getOpionMenu() {
		return opionMenu;
	}

	public void setOpionMenu(OpcionMenu opionMenu) {
		this.opionMenu = opionMenu;
	}


	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("MenuGrupo [id=");
		builder.append(id);
		builder.append(", grupo=");
		builder.append(grupo);
		builder.append(", opionMenu=");
		builder.append(opionMenu);
		builder.append("]");
		return builder.toString();
	} 
	
	
}
