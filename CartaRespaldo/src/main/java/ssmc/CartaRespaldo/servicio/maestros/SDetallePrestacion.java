package ssmc.CartaRespaldo.servicio.maestros;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ssmc.CartaRespaldo.interfacedao.maestros.IDetallePrestacionDAO;
import ssmc.CartaRespaldo.modelo.maestros.DetallePrestacion;

/**
 * SDetallePrestacion
 * 
 * @author Vanessa Duno
 * @version 1.0
 * 
 */

@Service("SDetallePrestacion")
public class SDetallePrestacion {

	@Autowired
	private IDetallePrestacionDAO iPrestacionDAO;

	/**
	 * buscarPrestaciones: Servicio que busca el detalle de las prestaciones
	 * 
	 * @param Recibe
	 *           int idPrestacion
	 * @return Retorna una lista de PrestacionEstablecimiento
	 * @throws No
	 *             dispara ninguna excepción.
	 * 
	 */
	public List<DetallePrestacion> buscarPrestaciones(int idPrestacion) {
		return iPrestacionDAO.findByPrestacionId(idPrestacion);
	}

	public List<DetallePrestacion> buscarTodos (){
		return iPrestacionDAO.findAll(); 
	}
	
	public List<DetallePrestacion> buscarPorCategorias (String parametro){
		return iPrestacionDAO.buscarPorCategoria(parametro); 
	}
	
	public List<DetallePrestacion> buscarPorCategoriasTraumatologia (String inicio, String fin){
		return iPrestacionDAO.buscarPorCategoriaTraumatologia(inicio, fin); 
	}
}

