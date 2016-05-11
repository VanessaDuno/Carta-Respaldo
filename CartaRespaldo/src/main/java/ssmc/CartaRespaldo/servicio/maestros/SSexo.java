package ssmc.CartaRespaldo.servicio.maestros;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ssmc.CartaRespaldo.interfacedao.maestros.ISexoDAO;
import ssmc.CartaRespaldo.modelo.maestros.Sexo;

/**
 * SSexo
 * @author Vanessa Duno
 * @version 1.0
 *
 */

@Service("SSexo")
public class SSexo {

	@Autowired
	private ISexoDAO iSexoDAO;

	/**
	 * buscarTodos: Servicio que busca en todos los sexos
	 * almacenados
	 * 
	 * @param No recibe ningun parametro
	 * @return Retorna una lista de sexos 
	 * @throws No dispara ninguna excepción.
	 * 
	 */
	public List<Sexo> buscarTodos() {
		return iSexoDAO.findAll(); 
	}
	
	public Sexo buscarId (int id){
		return iSexoDAO.findOne(id); 
	}
	
	
}
