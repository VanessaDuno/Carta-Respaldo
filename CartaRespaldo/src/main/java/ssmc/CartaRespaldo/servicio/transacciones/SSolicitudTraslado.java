package ssmc.CartaRespaldo.servicio.transacciones;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ssmc.CartaRespaldo.interfacedao.transacciones.ISolicitudTrasladoDAO;
import ssmc.CartaRespaldo.modelo.transacciones.SolicitudTraslado;

/**
 * SSolicitudTraslado
 * @author Vanessa Duno
 * @version 1.0
 *
 */

@Service("SSolicitudTraslado")
public class SSolicitudTraslado {

	@Autowired
	private ISolicitudTrasladoDAO iSolicitudTrasladoDAO;


	/**
	 * guardar: Servicio que almacena ua PrestacionSolicitud en BD
	 * 
	 * @param Recibe un objeto PrestacionSolicitud 
	 * @return No retorna ningun objeto ni dato
	 * @throws No dispara ninguna excepción.
	 * 
	 */
	public SolicitudTraslado guardar(SolicitudTraslado solicitudTraslado) {
		return iSolicitudTrasladoDAO.save(solicitudTraslado);
	}
	
	public SolicitudTraslado buscarSolicitud (int id){
		return iSolicitudTrasladoDAO.findOne(id); 
	}
	
	public Integer maxId (){
		return iSolicitudTrasladoDAO.buscarMaxId(); 
	}
	
	public List<SolicitudTraslado> buscarTodos (){
		return iSolicitudTrasladoDAO.findAll(); 
	}
}
