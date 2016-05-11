package ssmc.CartaRespaldo.servicio.maestros;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ssmc.CartaRespaldo.interfacedao.maestros.IMotivoDAO;
import ssmc.CartaRespaldo.modelo.maestros.Motivo;

/**
 * SMotivo
 * @author Vanessa Duno
 * @version 1.0
 *
 */

@Service("SMotivo")
public class SMotivo {

	@Autowired
	private IMotivoDAO iMotivoDAO;

	/**
	 * buscarTodos: Servicio que busca en todos los motivos
	 * almacenados
	 * 
	 * @param No recibe ningun parametro
	 * @return Retorna una lista de motivos  
	 * @throws No dispara ninguna excepción.
	 * 
	 */
	public List<Motivo> buscarTodos() {
		return iMotivoDAO.findAll(); 
	}
	
	public Motivo buscarId (int id){
		return iMotivoDAO.findOne(id); 
	}
	
	
}
