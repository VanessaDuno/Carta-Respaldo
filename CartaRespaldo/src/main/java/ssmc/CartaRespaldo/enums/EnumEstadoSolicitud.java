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

	CREADA("Creada", 1), PORVALIDAR("Por validar", 2),  TRASLADO("Traslado", 3), ANULADA(
			"Anulada", 4), CIERRECLINICO("Cierre Clinico", 5), CIERREADMINISTRATIVO(
			"Cierre Administrativo", 6), RECEPCIONCUENTA("Recepción cuenta", 7), VISACION ("Visación", 8);

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
