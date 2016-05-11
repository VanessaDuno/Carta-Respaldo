package ssmc.CartaRespaldo.servicio.maestros;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ssmc.CartaRespaldo.interfacedao.maestros.ICategoriaPrestacionDAO;
import ssmc.CartaRespaldo.modelo.maestros.CategoriaPrestacion;

/**
 * SCategoriaPrestacion
 * @author Vanessa Duno
 * @version 1.0
 *
 */

@Service("SCategoriaPrestacion")
public class SCategoriaPrestacion {

	@Autowired
	private ICategoriaPrestacionDAO iCategoriaPrestacionDAO;

	public List<CategoriaPrestacion> buscarPorPrestacion (int id){
		return iCategoriaPrestacionDAO.findByPrestacionId(id); 
	}
	
	
}
