package ssmc.CartaRespaldo.servicio.maestros;

import java.util.List;

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
	
	public List<Bitacora> buscarEstatus (String estatus){
		return iBitacoraDAO.findByEstatusOrderByFechaAsc(estatus); 
	}
	
	public List<Bitacora> buscarTodos (){
		return iBitacoraDAO.findAll();
	}
	
	public List<Bitacora> buscarPorEstablecimiento(int idEstablecimiento, boolean activo) {
		return iBitacoraDAO.findByTrasladoUnidadEstablecimientoIdAndActivoOrderByFechaAsc(idEstablecimiento, activo);
	}
	
	public Bitacora buscarEstadoActivo(int idTraslado, boolean activo){
		return iBitacoraDAO.findByTrasladoIdAndActivo(idTraslado, activo);
	}
	
	public Bitacora buscarTrasladooEstado (int idTraslado, String estado){
		return iBitacoraDAO.findByTrasladoIdAndEstatus(idTraslado, estado); 
	}
	
	public List<Bitacora> buscarEstado (boolean estado){
		return iBitacoraDAO.findByActivo(estado); 
	}
	
}
