package ssmc.CartaRespaldo.modelo.maestros;

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

import java.sql.Timestamp;

/**
 * Paciente
 * @author Vanessa Duno
 * @version 1.0
 *
 */

@Entity
@Table(name = "paciente")
public class Paciente implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_paciente")
	private int id;

	@Column(name = "rut")
	private String rut;

	@Column(name = "nombres")
	private String nombres;

	@Column(name = "primer_apellido")
	private String primerApellido;

	@Column(name = "segundo_apellido")
	private String segundoApellido;

	@Column(name = "fecha_nacimiento")
	private Timestamp fechaNacimiento;

	@Column(name = "responsable")
	private String responsable;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_comuna")
	private Comuna comuna;

	@Column(name = "domicilio")
	private String domicilio;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_sexo")
	private Sexo sexo;

	@Column(name = "prevision")
	private String prevision;
	
	@Column (name = "telefono")
	private String telefono; 

	public Paciente() {
		super();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getRut() {
		return rut;
	}

	public void setRut(String rut) {
		this.rut = rut;
	}

	public String getNombres() {
		return nombres;
	}

	public void setNombres(String nombres) {
		this.nombres = nombres;
	}

	public String getPrimerApellido() {
		return primerApellido;
	}

	public void setPrimerApellido(String primerApellido) {
		this.primerApellido = primerApellido;
	}

	public String getSegundoApellido() {
		return segundoApellido;
	}

	public void setSegundoApellido(String segundoApellido) {
		this.segundoApellido = segundoApellido;
	}

	public Timestamp getFechaNacimiento() {
		return fechaNacimiento;
	}

	public void setFechaNacimiento(Timestamp fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}

	public String getResponsable() {
		return responsable;
	}

	public void setResponsable(String responsable) {
		this.responsable = responsable;
	}

	public Comuna getComuna() {
		return comuna;
	}

	public void setComuna(Comuna comuna) {
		this.comuna = comuna;
	}

	public String getDomicilio() {
		return domicilio;
	}

	public void setDomicilio(String domicilio) {
		this.domicilio = domicilio;
	}

	public Sexo getSexo() {
		return sexo;
	}

	public void setSexo(Sexo sexo) {
		this.sexo = sexo;
	}

	public String getPrevision() {
		return prevision;
	}

	public void setPrevision(String prevision) {
		this.prevision = prevision;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Paciente [id=");
		builder.append(id);
		builder.append(", rut=");
		builder.append(rut);
		builder.append(", nombres=");
		builder.append(nombres);
		builder.append(", primerApellido=");
		builder.append(primerApellido);
		builder.append(", segundoApellido=");
		builder.append(segundoApellido);
		builder.append(", fechaNacimiento=");
		builder.append(fechaNacimiento);
		builder.append(", responsable=");
		builder.append(responsable);
		builder.append(", comuna=");
		builder.append(comuna);
		builder.append(", domicilio=");
		builder.append(domicilio);
		builder.append(", sexo=");
		builder.append(sexo);
		builder.append(", prevision=");
		builder.append(prevision);
		builder.append(", telefono=");
		builder.append(telefono);
		builder.append("]");
		return builder.toString();
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
}