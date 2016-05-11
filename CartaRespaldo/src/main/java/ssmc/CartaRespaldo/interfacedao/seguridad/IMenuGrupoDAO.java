package ssmc.CartaRespaldo.interfacedao.seguridad;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import ssmc.CartaRespaldo.modelo.seguridad.MenuGrupo;

/**
 * IMenuGrupoDAO
 * @author Vanessa Duno
 * @version 1.0
 *
 */

public interface IMenuGrupoDAO extends JpaRepository<MenuGrupo, Integer>{
	
	@Query ("SELECT m from MenuGrupo m, OpcionMenu u where m.opionMenu.nombre = ?1 and m.opionMenu.id = u.idMenu")
	public List<MenuGrupo> buscarMenuGrupo(String nombre);
	
	List<MenuGrupo> findByOpionMenuNombre(String nombre);
	
	MenuGrupo findByOpionMenuIdMenuAndGrupoId (int menu, int grupo); 
	
}
