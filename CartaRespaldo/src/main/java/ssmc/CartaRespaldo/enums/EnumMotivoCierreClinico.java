/**
 * 
 */
package ssmc.CartaRespaldo.enums;

/**
 * EnumMotivoCierreClinico
 * 
 * @author Vanessa Maria Duno
 * @version 1.0
 */
public enum EnumMotivoCierreClinico {

	RESCATE("Rescate", 1), FALLECIMIENTO("Fallecimiento", 2),  ALTACLINICA("Alta clínica", 3), DERIVACIONOTROCENTRO(
			"Derivación a otro centro", 4);

	private final String motivo;
	private final int id;

	private EnumMotivoCierreClinico(String motivo, int id) {
		this.motivo = motivo;
		this.id = id;
	}

	public String getMotivo() {
		return motivo;
	}

	public int getId() {
		return id;
	}

}
