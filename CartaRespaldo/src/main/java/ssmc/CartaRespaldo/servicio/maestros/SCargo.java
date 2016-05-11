package ssmc.CartaRespaldo.servicio.maestros;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ssmc.CartaRespaldo.interfacedao.maestros.ICargoDAO;
import ssmc.CartaRespaldo.modelo.maestros.Cargo;

/**
 * SCargo
 * @author Vanessa Duno
 * @version 1.0
 *
 */

@Service("SCargo")
public class SCargo {

	@Autowired
	private ICargoDAO iCargoDAO;

	/**
	 * buscarTodos: Servicio que busca en todos los cargos
	 * almacenados
	 * 
	 * @param No recibe ningun parametro
	 * @return Retorna una lista de cargos 
	 * @throws No dispara ninguna excepción.
	 * 
	 */
	public List<Cargo> buscarTodos() {
		return iCargoDAO.findAll(); 
	}
	
	public Cargo buscarId (int id){
		return iCargoDAO.findOne(id); 
	}
	
	
}
