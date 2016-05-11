package ssmc.CartaRespaldo.servicio.seguridad;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ssmc.CartaRespaldo.interfacedao.seguridad.IGrupoDAO;
import ssmc.CartaRespaldo.modelo.seguridad.Grupo;

/**
 * SGrupo
 * @author Vanessa Duno
 * @version 1.0
 *
 */

@Service("SGrupo")
public class SGrupo {


	@Autowired
	private IGrupoDAO iGrupoDAO;
	
	/**
	 * buscarTodos: Servicio que busca todos los Grupo activos almacenados
	 * en bd
	 * 
	 * @param recibe un boolean estado
	 * @return Retorna una List <Grupo>
	 * @throws No
	 *             dispara ninguna excepción.
	 * 
	 */
	public List<Grupo> buscarTodos (boolean estado){
		return iGrupoDAO.findByEstado(estado); 
	}
	
	/**
	 * guardar: Servicio que almacena un Grupo en BD
	 * 
	 * @param Recibe
	 *            un objeto Grupo
	 * @return No retorna ningun objeto ni dato
	 * @throws No
	 *             dispara ninguna excepción.
	 * 
	 */
	public void guardar(Grupo grupo){
		iGrupoDAO.save(grupo);
	}

}
