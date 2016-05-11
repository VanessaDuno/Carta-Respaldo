package ssmc.CartaRespaldo.servicio.transacciones;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ssmc.CartaRespaldo.interfacedao.transacciones.IResponsableSolicitudDAO;
import ssmc.CartaRespaldo.modelo.transacciones.ResponsableSolicitud;

/**
 * SResponsableSolicitud
 * @author Vanessa Duno
 * @version 1.0
 *
 */

@Service("SResponsableSolicitud")
public class SResponsableSolicitud {

	@Autowired
	private IResponsableSolicitudDAO iResponsableSolicitudDAO;


	/**
	 * guardar: Servicio que almacena una ResponsableSolicitud en BD
	 * 
	 * @param Recibe un objeto ResponsableSolicitud 
	 * @return No retorna ningun objeto ni dato
	 * @throws No dispara ninguna excepción.
	 * 
	 */
	public void guardar(ResponsableSolicitud responsableSolicitud) {
		iResponsableSolicitudDAO.save(responsableSolicitud);
	}
	
	public List<ResponsableSolicitud> responsablesSolicitud (int idSolicitud){
		return iResponsableSolicitudDAO.findBySolicitudTrasladoId(idSolicitud); 
	}
	
}
