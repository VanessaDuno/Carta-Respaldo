package ssmc.CartaRespaldo.servicio.maestros;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ssmc.CartaRespaldo.interfacedao.maestros.IProvincia;
import ssmc.CartaRespaldo.modelo.maestros.Provincia;

/**
 * SProvincia
 * @author Vanessa Duno
 * @version 1.0
 *
 */

@Service("SProvincia")
public class SProvincia {

	@Autowired
	private IProvincia iProvincia;

	/**
	 * buscarTodos: Servicio que busca en todos las provincias
	 * almacenados
	 * 
	 * @param No recibe ningun parametro
	 * @return Retorna una lista de provincias 
	 * @throws No dispara ninguna excepción.
	 * 
	 */
	public List<Provincia> buscarTodos() {
		return iProvincia.findAll(); 
	}
	
	public List<Provincia> buscarPorRegion (int region){
		return iProvincia.findByRegionId(region); 
	}
	
}
