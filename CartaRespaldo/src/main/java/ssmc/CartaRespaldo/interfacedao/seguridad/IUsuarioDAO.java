package ssmc.CartaRespaldo.interfacedao.seguridad;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import ssmc.CartaRespaldo.modelo.seguridad.Usuario;

/**
 * IUsuarioDAO
 * @author Vanessa Duno
 * @version 1.0
 *
 */

public interface IUsuarioDAO extends JpaRepository<Usuario, Integer>{

	Usuario findByLogin(String nombre);
	
	public List<Usuario> findByEstado (boolean estado); 
}
