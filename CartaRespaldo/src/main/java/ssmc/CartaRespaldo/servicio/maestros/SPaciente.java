package ssmc.CartaRespaldo.servicio.maestros;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ssmc.CartaRespaldo.interfacedao.maestros.IPacienteDAO;
import ssmc.CartaRespaldo.modelo.maestros.Paciente;

/**
 * SPaciente
 * @author Vanessa Duno
 * @version 1.0
 *
 */

@Service("SPaciente")
public class SPaciente {

	@Autowired
	private IPacienteDAO iPacienteDAO;

	/**
	 * guardar: Servicio que almacena un paciente en BD
	 * 
	 * @param Recibe un objeto Paciente 
	 * @return Retorna un objeto Paciente.
	 * @throws No dispara ninguna excepción.
	 * 
	 */
	public Paciente guardar(Paciente paciente) {
		return iPacienteDAO.save(paciente);
	}
	
	public Paciente buscarRut (String rut){
		return iPacienteDAO.findByRut(rut); 
	}
	
}
