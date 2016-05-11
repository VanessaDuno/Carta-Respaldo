package ssmc.CartaRespaldo.servicio.seguridad;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ssmc.CartaRespaldo.interfacedao.seguridad.IUsuarioDAO;
import ssmc.CartaRespaldo.modelo.seguridad.Usuario;

/**
 * SUsuario
 * @author Vanessa Duno
 * @version 1.0
 *
 */

@Service("SUsuario")
public class SUsuario {


	@Autowired
	private IUsuarioDAO iUsuarioDAO;
	
	/**
	 * buscarUsuarioPorNombre: Servicio que busca un usuario por login
	 * 
	 * @param Recibe el login del Usuario 
	 * @return Retorna un objeto Usuario 
	 * @throws No
	 *             dispara ninguna excepción.
	 * 
	 */
	public Usuario buscarUsuarioPorNombre (String nombre){
		return iUsuarioDAO.findByLogin(nombre); 
	}
	
	/**
	 * guardar: Servicio que almacena un Usuario en BD
	 * 
	 * @param Recibe
	 *            un objeto Usuario
	 * @return Retorna un objeto Usuario 
	 * @throws No
	 *             dispara ninguna excepción.
	 * 
	 */
	public Usuario guardar (Usuario usuario){
		return iUsuarioDAO.save(usuario); 
	}
	
	/**
	 * buscarTodosS: Servicio que busca todos los usuarios almacenados en BD
	 * 
	 * @param No recibe ningun parametro 
	 * @return Retorna una  List<Usuario>
	 * @throws No
	 *             dispara ninguna excepción.
	 * 
	 */
	public List<Usuario> buscarTodosS (){
		return iUsuarioDAO.findAll();
	}
	
	/**
	 * buscarTodos: Servicio que busca los usuarios almacenados en BD segun el estatus 
	 * 
	 * @param  Recibe el estado 
	 * @return Retorna una  List<Usuario>
	 * @throws No
	 *             dispara ninguna excepción.
	 * 
	 */
	public List<Usuario> buscarTodos (boolean estado){
		return iUsuarioDAO.findByEstado(estado); 
	}
	
}
