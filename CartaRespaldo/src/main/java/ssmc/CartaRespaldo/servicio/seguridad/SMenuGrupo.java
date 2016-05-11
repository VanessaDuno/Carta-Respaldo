package ssmc.CartaRespaldo.servicio.seguridad;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ssmc.CartaRespaldo.interfacedao.seguridad.IMenuGrupoDAO;
import ssmc.CartaRespaldo.modelo.seguridad.MenuGrupo;

/**
 * SMenuGrupo
 * @author Vanessa Duno
 * @version 1.0
 *
 */

@Service("SMenuGrupo")
public class SMenuGrupo {


	@Autowired
	private IMenuGrupoDAO imenuGrupoDAO;
	
	/**
	 * guardar: Servicio que almacena un MenuGrupo en BD
	 * 
	 * @param Recibe
	 *            un objeto MenuGrupo
	 * @return No retorna ningun objeto ni dato
	 * @throws No
	 *             dispara ninguna excepción.
	 * 
	 */
	public void guardar (MenuGrupo menuGrupo){
		imenuGrupoDAO.save(menuGrupo); 
	}
	
	/**
	 * buscarMenuGrupo: Servicio que busca MenuGrupo por el nombre de la opcionmenu
	 * @param recibe el nombre de la opcion de menu
	 * @return Retorna una List<MenuGrupo>
	 * @throws No
	 *             dispara ninguna excepción.
	 * 
	 */
	public List<MenuGrupo> buscarMenuGrupo (String nombre){
		return imenuGrupoDAO.buscarMenuGrupo(nombre);
	}
	
	/**
	 * eliminarGrupo: Servicio que elimina un MenuGrupo
	 * @param recibe el id del menu a eliminar
	 * @return No retorna ningun objeto ni dato
	 * @throws No
	 *             dispara ninguna excepción.
	 * 
	 */
	public void eliminarGrupo (int id){
		imenuGrupoDAO.delete(id);
	}
	
	/**
	 * buscarNombreMenu: Servicio que busca una lista MenuGrupo por el nombre de la opcionmenu
	 * @param recibe el nombre de la opcion de menu
	 * @return Retorna una List<MenuGrupo>
	 * @throws No
	 *             dispara ninguna excepción.
	 * 
	 */
	public List<MenuGrupo> buscarNombreMenu (String nombre){
		return imenuGrupoDAO.findByOpionMenuNombre(nombre);
	}
	
	/**
	 * buscarGrupoMenu: Servicio que busca un MenuGrupo por menu y por grupo
	 * @param recibe el id del menu y el id del grupo
	 * @return Retorna un objeto MenuGrupo
	 * @throws No
	 *             dispara ninguna excepción.
	 * 
	 */
	public MenuGrupo buscarGrupoMenu (int menu, int grupo){
		return imenuGrupoDAO.findByOpionMenuIdMenuAndGrupoId(menu, grupo);	
		}
	
	/**
	 * buscarId: Servicio que busca un MenuGrupo por id
	 * @param recibe el id del MenuGrupo
	 * @return Retorna un objeto MenuGrupo
	 * @throws No
	 *             dispara ninguna excepción.
	 * 
	 */
	public MenuGrupo buscarId (int id){
		return imenuGrupoDAO.findOne(id); 
	}
}
