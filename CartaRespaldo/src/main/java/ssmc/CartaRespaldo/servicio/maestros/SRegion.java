package ssmc.CartaRespaldo.servicio.maestros;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ssmc.CartaRespaldo.interfacedao.maestros.IRegionDAO;
import ssmc.CartaRespaldo.modelo.maestros.Region;

/**
 * SRegion
 * @author Vanessa Duno
 * @version 1.0
 *
 */

@Service("SRegion")
public class SRegion {

	@Autowired
	private IRegionDAO iRegionDAO;

	/**
	 * buscarTodos: Servicio que busca en todos los hospitales
	 * almacenados
	 * 
	 * @param No recibe ningun parametro
	 * @return Retorna una lista de Hospitales 
	 * @throws No dispara ninguna excepción.
	 * 
	 */
	public List<Region> buscarTodos() {
		return iRegionDAO.findAll();
	}
	
	public Region buscarId (int id){
		return iRegionDAO.findByCodigo(id); 
	}
	
}