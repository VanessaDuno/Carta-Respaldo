package ssmc.CartaRespaldo.servicio.maestros;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ssmc.CartaRespaldo.interfacedao.maestros.IDiagnosticoDAO;
import ssmc.CartaRespaldo.modelo.maestros.Diagnostico;

/**
 * SDiagnostico
 * @author Vanessa Duno
 * @version 1.0
 *
 */

@Service("SDiagnostico")
public class SDiagnostico {

	@Autowired
	private IDiagnosticoDAO iDiagnosticoDAO;

	/**
	 * buscarTodos: Servicio que busca en todos los diagnosticos
	 * almacenados
	 * 
	 * @param No recibe ningun parametro
	 * @return Retorna una lista de Diagnostico 
	 * @throws No dispara ninguna excepción.
	 * 
	 */
	public List<Diagnostico> buscarTodos() {
		return iDiagnosticoDAO.findAll(); 
	}
	
	public Diagnostico buscarId (int id){
		return iDiagnosticoDAO.findOne(id); 
	}
	
	
}
