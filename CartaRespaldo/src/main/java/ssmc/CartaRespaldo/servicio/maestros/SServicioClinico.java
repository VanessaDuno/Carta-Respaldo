package ssmc.CartaRespaldo.servicio.maestros;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ssmc.CartaRespaldo.interfacedao.maestros.IServicioClinicoDAO;
import ssmc.CartaRespaldo.modelo.maestros.ServicioClinico;

/**
 * SServicioClinico
 * @author Vanessa Duno
 * @version 1.0
 *
 */

@Service("SServicioClinico")
public class SServicioClinico {

	@Autowired
	private IServicioClinicoDAO iServicioClinicoDAO;

	/**
	 * buscarTodos: Servicio que busca los servicios clinicos
	 * de una unidad
	 * 
	 * @param Recibe int idUnidad
	 * @return Retorna una lista de ServicioClinico 
	 * @throws No dispara ninguna excepción.
	 * 
	 */
	public List<ServicioClinico> buscarPorUnidad(int idUnidad) {
		return iServicioClinicoDAO.findByUnidadId(idUnidad);  
	}

	
	
}
