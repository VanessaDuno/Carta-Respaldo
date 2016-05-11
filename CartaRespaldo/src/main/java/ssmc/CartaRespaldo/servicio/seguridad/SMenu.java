package ssmc.CartaRespaldo.servicio.seguridad;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ssmc.CartaRespaldo.interfacedao.seguridad.IMenuDAO;
import ssmc.CartaRespaldo.modelo.seguridad.OpcionMenu;

/**
 * SMenu
 * @author Vanessa Duno
 * @version 1.0
 *
 */

@Service("SMenu")
public class SMenu {


	@Autowired
	private IMenuDAO iMenuDAO;
	
	/**
	 * buscarTodos: Servicio que busca todas las OpcionMenu activos almacenados
	 * en bd
	 * 
	 * @param recibe un boolean estado
	 * @return Retorna una List <OpcionMenu>
	 * @throws No
	 *             dispara ninguna excepción.
	 * 
	 */
	public List<OpcionMenu> buscarTodos (int estado){
		return iMenuDAO.findByEstado(estado);
	}
	
	/**
	 * buscarId: Servicio que busca toda una OpcionMenu por id
	 * 
	 * @param recibe entero id menu
	 * @return Retorna un objeto OpcionMenu
	 * @throws No
	 *             dispara ninguna excepción.
	 * 
	 */
	public OpcionMenu buscarId (int id){
		return iMenuDAO.findByIdMenu(id); 
	}
	
	/**
	 * buscarOpcionMenuUsuario: Servicio que busca todas las OpcionMenu 
	 * para un usuario logueado
	 * @param recibe un string login y el padre de la opcion
	 * @return Retorna una List <OpcionMenu>
	 * @throws No
	 *             dispara ninguna excepción.
	 * 
	 */
	public List<OpcionMenu> buscarOpcionMenuUsuario (String login, int idPadre){
		return iMenuDAO.buscarOpcionesMenuUsuario(login, idPadre); 
	}
	
	/**
	 * buscarHijos: Servicio que busca los submenu de una opcion de menu
	 * @param recibe el id del padre de la opcionmenu
	 * @return Retorna una List <OpcionMenu>
	 * @throws No
	 *             dispara ninguna excepción.
	 * 
	 */
	public List<OpcionMenu> buscarHijos (int idPadre){
		return iMenuDAO.findByIdPadre(idPadre); 
	}
	
	/**
	 * guardar: Servicio que almacena una OpcionMenu en BD
	 * 
	 * @param Recibe
	 *            un objeto OpcionMenu
	 * @return retorna un objeto OpcionMenu
	 * @throws No
	 *             dispara ninguna excepción.
	 * 
	 */
	public OpcionMenu guardar (OpcionMenu menu){
		return iMenuDAO.save(menu);
	}
	
	/**
	 * buscarNombre: Servicio que busca una opcionmenu por su nombre
	 * @param recibe el nombre de la opcion de menu
	 * @return Retorna un objeto OpcionMenu
	 * @throws No
	 *             dispara ninguna excepción.
	 * 
	 */
	public OpcionMenu buscarNombre (String nombre){
		return iMenuDAO.findByNombre(nombre);
	}
}
