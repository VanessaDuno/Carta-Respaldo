package ssmc.CartaRespaldo.interfacedao.seguridad;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import ssmc.CartaRespaldo.modelo.seguridad.OpcionMenu;

/**
 * IMenuDAO
 * @author Vanessa Duno
 * @version 1.0
 *
 */
public interface IMenuDAO extends JpaRepository<OpcionMenu, Integer>{

	OpcionMenu findByIdMenu (int idMenu);
	
	List <OpcionMenu> findByEstado(int estado); 
	

	@Query ("SELECT Distinct(m) from MenuGrupo  mg , OpcionMenu  m, Grupo  g, UsuarioGrupo  ug, Usuario  u where m.idMenu = mg.opionMenu.idMenu and g.id = mg.grupo.id and  mg.grupo.id = ug.grupo.id and  ug.usuario.id = u.id and u.login =  ?1 and m.idPadre = ?2")
	public List<OpcionMenu> buscarOpcionesMenuUsuario (String login, int idPadre); 
	
	List<OpcionMenu> findByIdPadre (int idPadre); 
	
	OpcionMenu findByNombre (String nombre); 
}
