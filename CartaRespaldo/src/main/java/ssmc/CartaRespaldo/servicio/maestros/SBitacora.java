package ssmc.CartaRespaldo.servicio.maestros;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ssmc.CartaRespaldo.interfacedao.maestros.IBitacoraDAO;
import ssmc.CartaRespaldo.modelo.transacciones.Bitacora;

/**
 * SBitacora
 * @author Vanessa Duno
 * @version 1.0
 *
 */

@Service("SBitacora")
public class SBitacora {

	@Autowired
	private IBitacoraDAO iBitacoraDAO;

	/**
	 * guardar: Servicio que almacena una bitacora en BD
	 * 
	 * @param Recibe un objeto Bitacora 
	 * @return No retorna ningun objeto ni dato 
	 * @throws No dispara ninguna excepción.
	 * 
	 */
	public void guardar(Bitacora bitacora) {
		 iBitacoraDAO.save(bitacora);
	}
	
	
}
