package ssmc.CartaRespaldo.servicio.maestros;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ssmc.CartaRespaldo.interfacedao.maestros.IEstablecimientoDAO;
import ssmc.CartaRespaldo.modelo.maestros.Establecimiento;

/**
 * SHospital
 * @author Vanessa Duno
 * @version 1.0
 *
 */

@Service("SEstablecimiento")
public class SEstablecimiento {

	@Autowired
	private IEstablecimientoDAO iEstablecimientoDAO;

	/**
	 * guardar: Servicio que almacena un hospital en BD
	 * 
	 * @param Recibe un objeto Hospital 
	 * @return No retorna ningun objeto ni dato
	 * @throws No dispara ninguna excepción.
	 * 
	 */
	public void guardar(Establecimiento hospital) {
		iEstablecimientoDAO.save(hospital);
	}
	
	public Establecimiento guardarRetorno(Establecimiento hospital) {
		return iEstablecimientoDAO.save(hospital);
	}
	/**
	 * buscarTodos: Servicio que busca en todos los hospitales
	 * almacenados
	 * 
	 * @param No recibe ningun parametro
	 * @return Retorna una lista de Hospitales 
	 * @throws No dispara ninguna excepción.
	 * 
	 */
	public List<Establecimiento> buscarTodos() {
		return iEstablecimientoDAO.findAll();
	}
	
	public Establecimiento buscarId (int id){
		return iEstablecimientoDAO.findOne(id);
	}
	
	public List<Establecimiento> buscarRegion (int region, boolean destino){
		return iEstablecimientoDAO.findByRegionIdAndIsDestinoOrderByNombreAsc(region, destino); 
	}
	
	public List<Establecimiento> buscarEstablecimientosOrigen (boolean estado){
		return iEstablecimientoDAO.findByIsDestino(estado); 
	}

	
}
