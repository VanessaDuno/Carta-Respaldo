package ssmc.CartaRespaldo.servicio.seguridad;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ssmc.CartaRespaldo.interfacedao.seguridad.IUsuarioGrupoDAO;
import ssmc.CartaRespaldo.modelo.seguridad.UsuarioGrupo;

/**
 * SUsuarioGrupo
 * @author Vanessa Duno
 * @version 1.0
 *
 */

@Service("SUsuarioGrupo")
public class SUsuarioGrupo {


	@Autowired
	private IUsuarioGrupoDAO iUsuarioGrupoDAO;
	
	/**
	 * buscarUsuario: Servicio que busca usuarioGrupo por login
	 * 
	 * @param Recibe el login del Usuario 
	 * @return Retorna una List<UsuarioGrupo> 
	 * @throws No
	 *             dispara ninguna excepción.
	 * 
	 */
	public List<UsuarioGrupo> buscarUsuario (String nombre){
		return iUsuarioGrupoDAO.buscarUsuarioGrupo(nombre);
	}
	
	/**
	 * guardar: Servicio que almacena un UsuarioGrupo en BD
	 * 
	 * @param Recibe
	 *            un objeto UsuarioGrupo
	 * @return No retorna ningun objeto ni dato
	 * @throws No
	 *             dispara ninguna excepción.
	 * 
	 */
	public void guardar (UsuarioGrupo usuarioGrupo){
		iUsuarioGrupoDAO.save(usuarioGrupo); 
	}
	
	/**
	 * buscarTodos: Servicio que busca todos los usuarioGrupo almacenados
	 * 
	 * @param no recibe ningun parametro
	 * @return Retorna una List<UsuarioGrupo> 
	 * @throws No
	 *             dispara ninguna excepción.
	 * 
	 */
	public List<UsuarioGrupo> buscarTodos(){
		return iUsuarioGrupoDAO.findAll();
	}
	
	/**
	 * eliminarGrupo: Servicio elimina un usuariogrupo
	 * 
	 * @param recibe el id del usuariogrupo a eliminar
	 * @return No retorna ningun objeto ni dato
	 * @throws No
	 *             dispara ninguna excepción.
	 * 
	 */
	public void eliminarGrupo (int id){
		iUsuarioGrupoDAO.delete(id);
	}
	
	/**
	 * buscarUsuarioGrupo: Servicio que busca usuarioGrupo por usuario y grupo
	 * 
	 * @param Recibe el id usuario, id del grupo
	 * @return Retorna un objeto UsuarioGrupo 
	 * @throws No
	 *             dispara ninguna excepción.
	 * 
	 */
	public UsuarioGrupo buscarUsuarioGrupo (int usuario, int grupo){
		 return iUsuarioGrupoDAO.findByUsuarioIdAndGrupoId(usuario, grupo); 
	}
	
	/**
	 * buscarId: Servicio que busca usuarioGrupo por id
	 * 
	 * @param Recibe el id del usuariogrupo
	 * @return Retorna un objeto UsuarioGrupo 
	 * @throws No
	 *             dispara ninguna excepción.
	 * 
	 */
	public UsuarioGrupo  buscarId (int id){
		return iUsuarioGrupoDAO.findOne(id); 
	}
}
