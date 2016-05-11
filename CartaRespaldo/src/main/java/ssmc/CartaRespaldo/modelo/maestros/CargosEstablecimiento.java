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

/**CargosEstablecimiento
 * @author Vanessa Maria Duno
 * @version 1.0
 */
@Entity
@Table(name = "cargos_establecimiento")
public class CargosEstablecimiento implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	@Column (name= "id_cargo_establecimiento")
	private int id; 
	
	@ManyToOne (fetch = FetchType.LAZY)
	@JoinColumn(name = "id_establecimiento")
	private Establecimiento establecimiento;
	
	@ManyToOne (fetch = FetchType.LAZY)
	@JoinColumn(name = "id_cargo")
	private Cargo cargo;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Establecimiento getEstablecimiento() {
		return establecimiento;
	}

	public void setEstablecimiento(Establecimiento establecimiento) {
		this.establecimiento = establecimiento;
	}

	public Cargo getCargo() {
		return cargo;
	}

	public void setCargo(Cargo cargo) {
		this.cargo = cargo;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("CargosEstablecimiento [id=");
		builder.append(id);
		builder.append(", establecimiento=");
		builder.append(establecimiento);
		builder.append(", cargo=");
		builder.append(cargo);
		builder.append("]");
		return builder.toString();
	} 

}
