package ssmc.CartaRespaldo.servicio.maestros;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ssmc.CartaRespaldo.interfacedao.maestros.IComunaDAO;
import ssmc.CartaRespaldo.modelo.maestros.Comuna;

/**
 * SComuna
 * @author Vanessa Duno
 * @version 1.0
 *
 */

@Service("SComuna")
public class SComuna {

	@Autowired
	private IComunaDAO iComunaDAO;

	/**
	 * buscarTodos: Servicio que busca en todos las comunas
	 * almacenados
	 * 
	 * @param No recibe ningun parametro
	 * @return Retorna una lista de comunas 
	 * @throws No dispara ninguna excepción.
	 * 
	 */
	public List<Comuna> buscarTodos() {
		return iComunaDAO.findAll(); 
	}
	
	public List<Comuna> buscarPorProvincia (int idProvincia){
		return iComunaDAO.findByProvinciaId(idProvincia);
	}
	
	public Comuna buscarId (int id){
		return iComunaDAO.findOne(id);
	}
	
	
}
