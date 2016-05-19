package ssmc.CartaRespaldo.modelo.transacciones;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import ssmc.CartaRespaldo.modelo.maestros.Establecimiento;
import ssmc.CartaRespaldo.modelo.maestros.Paciente;
import ssmc.CartaRespaldo.modelo.maestros.ServicioClinico;
import ssmc.CartaRespaldo.modelo.maestros.Unidad;

/**
 * SolicitudTraslado
 * @author Vanessa Duno
 * @version 1.0
 *
 */

@Entity
@Table(name="solicitud_traslado")
public class SolicitudTraslado implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	@Column(name= "id_solicitud_traslado")
	private int id;
	
	@Column(name="folio")
	private String folio; 
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="id_paciente")
	private Paciente paciente;
	
	@Column(name="diagnostico")
	private String  diagnostico; 
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="id_establecimiento")
	private Establecimiento establecimiento;
	
	@Column(name="tipo_derivacion")
	private String  tipoDerivacion;
	
	@Column(name="isges")
	private boolean isGes;
	
//	@ManyToOne(fetch = FetchType.LAZY)
//	@JoinColumn(name="id_servicio_clinico")
//	private ServicioClinico servicioClinico; 
	
	@Column(name="ficha")
	private String  ficha; 
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="id_unidad")
	private Unidad unidad;
	
	@Column(name = "descripcion")
	private String descripcion; 
	
	public SolicitudTraslado() {
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFolio() {
		return folio;
	}

	public void setFolio(String folio) {
		this.folio = folio;
	}

	public Paciente getPaciente() {
		return paciente;
	}

	public void setPaciente(Paciente paciente) {
		this.paciente = paciente;
	}


	public Establecimiento getEstablecimiento() {
		return establecimiento;
	}

	public void setEstablecimiento(Establecimiento establecimiento) {
		this.establecimiento = establecimiento;
	}


	public boolean isGes() {
		return isGes;
	}

	public void setGes(boolean isGes) {
		this.isGes = isGes;
	}

//	public ServicioClinico getServicioClinico() {
//		return servicioClinico;
//	}
//
//	public void setServicioClinico(ServicioClinico servicioClinico) {
//		this.servicioClinico = servicioClinico;
//	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("SolicitudTraslado [id=");
		builder.append(id);
		builder.append(", folio=");
		builder.append(folio);
		builder.append(", paciente=");
		builder.append(paciente);
		builder.append(", diagnostico=");
		builder.append(diagnostico);
		builder.append(", establecimiento=");
		builder.append(establecimiento);
		builder.append(", tipoDerivacion=");
		builder.append(tipoDerivacion);
		builder.append(", isGes=");
		builder.append(isGes);
//		builder.append(", servicioClinico=");
//		builder.append(servicioClinico);
		builder.append(", ficha=");
		builder.append(ficha);
		builder.append(", unidad=");
		builder.append(unidad);
		builder.append(", descripcion=");
		builder.append(descripcion);
		builder.append("]");
		return builder.toString();
	}

	public String getDiagnostico() {
		return diagnostico;
	}

	public void setDiagnostico(String diagnostico) {
		this.diagnostico = diagnostico;
	}

	public String getTipoDerivacion() {
		return tipoDerivacion;
	}

	public void setTipoDerivacion(String tipoDerivacion) {
		this.tipoDerivacion = tipoDerivacion;
	}

	public String getFicha() {
		return ficha;
	}

	public void setFicha(String ficha) {
		this.ficha = ficha;
	}

	public Unidad getUnidad() {
		return unidad;
	}

	public void setUnidad(Unidad unidad) {
		this.unidad = unidad;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
}
