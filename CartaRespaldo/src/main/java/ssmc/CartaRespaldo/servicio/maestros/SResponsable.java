package ssmc.CartaRespaldo.servicio.maestros;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ssmc.CartaRespaldo.interfacedao.maestros.IResponsableDAO;
import ssmc.CartaRespaldo.modelo.maestros.Responsable;

/**
 * SResponsable
 * @author Vanessa Duno
 * @version 1.0
 *
 */

@Service("SResponsable")
public class SResponsable {

	@Autowired
	private IResponsableDAO iResponsableDAO;


	public List<Responsable> buscarEstablecimientoCargo(int idEstablecimiento, int idCargo) {
		return iResponsableDAO.findByEstablecimientoIdAndCargoId(idEstablecimiento, idCargo); 
	}
	
	
}
