package ssmc.CartaRespaldo.interfacedao.seguridad;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import ssmc.CartaRespaldo.modelo.seguridad.UsuarioGrupo;

/**
 * IUsuarioGrupoDAO
 * @author Vanessa Duno
 * @version 1.0
 *
 */

public interface IUsuarioGrupoDAO extends JpaRepository<UsuarioGrupo, Integer>{
	

	@Query ("SELECT m from UsuarioGrupo m, Usuario u where m.usuario.login = ?1 and m.usuario.id = u.id")
	public List<UsuarioGrupo> buscarUsuarioGrupo(String nombre);
	
	UsuarioGrupo findByUsuarioIdAndGrupoId (int usuario, int grupo); 
	
}
