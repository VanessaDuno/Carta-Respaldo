/**
 * 
 */
package ssmc.CartaRespaldo.enums;

/**
 * EnumEstadoSolicitud
 * 
 * @author Vanessa Maria Duno
 * @version 1.0
 */
public enum EnumEstadoSolicitud {

	CREADA("Creada", 1), TRASLADO("Traslado", 2), ANULADA(
			"Anulada", 3), CIERRECLINICO("Cierre Clinico", 4), CIERREADMINISTRATIVO(
			"Cierre Administrativo", 675);

	private final String estado;
	private final int id;

	private EnumEstadoSolicitud(String estado, int id) {
		this.estado = estado;
		this.id = id;
	}

	public String getEstado() {
		return estado;
	}

	public int getId() {
		return id;
	}

}
