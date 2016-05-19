package ssmc.CartaRespaldo.servicio.seguridad;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ssmc.CartaRespaldo.controlador.seguridad.LogActividad;
import ssmc.CartaRespaldo.interfacedao.seguridad.ILogDAO;

/**
 * SLog
 * @author Vanessa Duno
 * @version 1.0
 *
 */

@Service("SLog")
public class SLog {


	@Autowired
	private ILogDAO iLogDAO;
	
	/**
	 * guardar: Servicio que almacena un log en BD
	 * 
	 * @param Recibe
	 *            un objeto Log
	 * @return no retorna ningun objeto ni dato 
	 * @throws No
	 *             dispara ninguna excepción.
	 * 
	 */
	public void guardar (LogActividad log){
		iLogDAO.save(log); 
	}
	
	
}
