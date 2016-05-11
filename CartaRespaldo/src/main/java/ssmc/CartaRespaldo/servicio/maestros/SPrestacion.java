package ssmc.CartaRespaldo.servicio.maestros;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ssmc.CartaRespaldo.interfacedao.maestros.IPrestacionDAO;
import ssmc.CartaRespaldo.modelo.maestros.Prestacion;

/**
 * SPrestacion
 * 
 * @author Vanessa Duno
 * @version 1.0
 * 
 */

@Service("SPrestacion")
public class SPrestacion {

	@Autowired
	private IPrestacionDAO iPrestacionDAO;

	/**
	 * buscarPrestacionesEstableciento: Servicio que busca los tipos de
	 * prestaciones  almacenados BD
	 * 
	 * @param Recibe
	 *           int tipoPrestacion
	 * @return Retorna una lista de PrestacionEstablecimiento
	 * @throws No
	 *             dispara ninguna excepción.
	 * 
	 */
	public List<Prestacion> buscarPrestaciones(int tipoPrestacion) {
		return iPrestacionDAO
				.findByTipo(tipoPrestacion);
	}

}
