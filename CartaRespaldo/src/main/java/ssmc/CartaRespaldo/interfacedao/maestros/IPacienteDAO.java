package ssmc.CartaRespaldo.interfacedao.maestros;

import org.springframework.data.jpa.repository.JpaRepository;

import ssmc.CartaRespaldo.modelo.maestros.Paciente;

/**
 * IPacienteDAO
 * @author Vanessa Duno
 * @version 1.0
 *
 */

public interface IPacienteDAO extends JpaRepository<Paciente, Integer> {
	
	Paciente findByRut(String rut); 

}
