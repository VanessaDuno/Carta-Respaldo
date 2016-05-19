package ssmc.CartaRespaldo.servicio.maestros;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ssmc.CartaRespaldo.interfacedao.maestros.ICargoEstablecimientoDAO;
import ssmc.CartaRespaldo.modelo.maestros.CargosEstablecimiento;

/**
 * SCargoEstablecimiento
 * @author Vanessa Duno
 * @version 1.0
 *
 */

@Service("SCargoEstablecimiento")
public class SCargoEstablecimiento {

	@Autowired
	private ICargoEstablecimientoDAO iCargoEstablecimientoDAO;

	/**
	 * guardar: Servicio que almacena una CargoEstablecimiento en BD
	 * 
	 * @param Recibe un objeto CargoEstablecimiento 
	 * @return No retorna ningun objeto ni dato 
	 * @throws No dispara ninguna excepción.
	 * 
	 */
	public void guardar(CargosEstablecimiento cargosEstablecimiento) {
		iCargoEstablecimientoDAO.save(cargosEstablecimiento);
	}
	
	public List<CargosEstablecimiento> cargosEstablecimientos (int idEstablecimiento){
		return iCargoEstablecimientoDAO.findByEstablecimientoId(idEstablecimiento); 
	}
	
	public void eliminarCargos (List<CargosEstablecimiento> cargos){
		iCargoEstablecimientoDAO.delete(cargos);
	}
}
