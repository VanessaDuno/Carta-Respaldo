package ssmc.CartaRespaldo.servicio.transacciones;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ssmc.CartaRespaldo.interfacedao.transacciones.IPrestacionSolicitudDAO;
import ssmc.CartaRespaldo.modelo.transacciones.PrestacionSolicitud;

/**
 * SPrestacionSolicitud
 * @author Vanessa Duno
 * @version 1.0
 *
 */

@Service("SPrestacionSolicitud")
public class SPrestacionSolicitud {

	@Autowired
	private IPrestacionSolicitudDAO iPrestacionSolicitudDAO;


	/**
	 * guardar: Servicio que almacena ua PrestacionSolicitud en BD
	 * 
	 * @param Recibe un objeto PrestacionSolicitud 
	 * @return No retorna ningun objeto ni dato
	 * @throws No dispara ninguna excepción.
	 * 
	 */
	public void guardar(PrestacionSolicitud prestacionSolicitud) {
		iPrestacionSolicitudDAO.save(prestacionSolicitud);
	}
	
	public List<PrestacionSolicitud> prestacionesPorSolicitud (int id){
		return iPrestacionSolicitudDAO.findBySolicitudTrasladoId(id); 
	}
}
