package ssmc.CartaRespaldo.controlador.transacciones;

import java.io.IOException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.JasperRunManager;
import net.sf.jasperreports.engine.util.JRLoader;

import org.apache.log4j.Logger;
import org.json.JSONException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.Sessions;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zk.ui.util.Clients;
import org.zkoss.zul.Button;
import org.zkoss.zul.Checkbox;
import org.zkoss.zul.Combobox;
import org.zkoss.zul.Datebox;
import org.zkoss.zul.Div;
import org.zkoss.zul.Label;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Radio;
import org.zkoss.zul.Row;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Window;

import ssmc.CartaRespaldo.componentes.Botonera;
import ssmc.CartaRespaldo.componentes.Catalogo;
import ssmc.CartaRespaldo.componentes.Constantes;
import ssmc.CartaRespaldo.controlador.maestros.CGenerico;
import ssmc.CartaRespaldo.enums.EnumEstadoSolicitud;
import ssmc.CartaRespaldo.modelo.maestros.CargosEstablecimiento;
import ssmc.CartaRespaldo.modelo.maestros.CategoriaPrestacion;
import ssmc.CartaRespaldo.modelo.maestros.Comuna;
import ssmc.CartaRespaldo.modelo.maestros.DetallePrestacion;
import ssmc.CartaRespaldo.modelo.maestros.Diagnostico;
import ssmc.CartaRespaldo.modelo.maestros.Establecimiento;
import ssmc.CartaRespaldo.modelo.maestros.Motivo;
import ssmc.CartaRespaldo.modelo.maestros.Paciente;
import ssmc.CartaRespaldo.modelo.maestros.Prestacion;
import ssmc.CartaRespaldo.modelo.maestros.Provincia;
import ssmc.CartaRespaldo.modelo.maestros.Region;
import ssmc.CartaRespaldo.modelo.maestros.Responsable;
import ssmc.CartaRespaldo.modelo.maestros.Sexo;
import ssmc.CartaRespaldo.modelo.maestros.Unidad;
import ssmc.CartaRespaldo.modelo.seguridad.Usuario;
import ssmc.CartaRespaldo.modelo.transacciones.Bitacora;
import ssmc.CartaRespaldo.modelo.transacciones.PrestacionSolicitud;
import ssmc.CartaRespaldo.modelo.transacciones.ResponsableSolicitud;
import ssmc.CartaRespaldo.modelo.transacciones.SolicitudTraslado;
import ws.cl.gov.fonasa.certificadorprevisional.ReplyCertificadorPrevisionalTO;

/**
 * CSolicitudTraslado Controlador encargado de registrar solicitudes de traslado
 * 
 * @author Vanessa Duno
 * @version 1.0
 * 
 */
public class CSolicitudTraslado extends CGenerico {

	private static final long serialVersionUID = 1L;
	private final static Logger log = Logger
			.getLogger(ssmc.CartaRespaldo.controlador.transacciones.CSolicitudTraslado.class);

	private List<Region> regiones = new ArrayList<Region>();
	private List<Provincia> provincias = new ArrayList<Provincia>();
	private List<Comuna> comunas = new ArrayList<Comuna>();
	private List<Establecimiento> establecimientos = new ArrayList<Establecimiento>();
	private List<Sexo> sexos = new ArrayList<Sexo>();
	private List<Region> regionesPaciente = new ArrayList<Region>();
	private List<DetallePrestacion> camas = new ArrayList<DetallePrestacion>();
	private List<Unidad> unidades = new ArrayList<Unidad>();
	private List<Motivo> motivos = new ArrayList<Motivo>();
	private List<Prestacion> prestaciones = new ArrayList<Prestacion>();

	private Usuario usuarioActivo;
	private SolicitudTraslado solicitudTraslado = new SolicitudTraslado();
	SolicitudTraslado solicitudGuardada = new SolicitudTraslado();
	private Bitacora bitacora = new Bitacora();
	private Paciente paciente = new Paciente();
	private List<Responsable> medicosResponsables = new ArrayList<Responsable>();
	private List<Responsable> funcionariosResponsables = new ArrayList<Responsable>();
	private List<CargosEstablecimiento> cargosEstablecimiento = new ArrayList<CargosEstablecimiento>();
	private List<ResponsableSolicitud> responsablesSolicitud = new ArrayList<ResponsableSolicitud>();
	private Paciente pacienteRegistrado = new Paciente();
	private String observacion = "";

	@Wire
	private Combobox cmbProvincia;
	@Wire
	private Combobox cmbComuna;
	@Wire
	private Combobox cmbEstablecimiento;
	@Wire
	private Textbox txtRutPaciente;
	@Wire
	private Textbox txtPrimerNombrePaciente;
	@Wire
	private Textbox txtSegundoNombrePaciente;
	@Wire
	private Textbox txtPrimerApellidoPaciente;
	@Wire
	private Textbox txtSegundoApellidoPaciente;
	@Wire
	private Datebox dtbFechaNacPaciente;
	@Wire
	private Combobox cmbSexo;
	@Wire
	private Combobox cmbRegionPaciente;
	@Wire
	private Textbox txtDireccionPaciente;
	@Wire
	private Combobox cmbPrevisionPaciente;
	@Wire
	private Div divErrorRutPaciente;
	@Wire
	private Label lblErrorRutPaciente;
	@Wire
	private Combobox cmbHospitalizacion;
	@Wire
	private Combobox cmbProcedimiento;
	@Wire
	private Combobox cmbUnidad;
	@Wire
	private Label lblHospitalizacion;
	@Wire
	private Label lblProcedimiento;
	@Wire
	private Div botoneraSolicitud;
	@Wire
	private Label lblRegion;
	@Wire
	private Label lblEstablecimiento;
	@Wire
	private Label lblNombreLaboratorio;
	@Wire
	private Textbox txtLaboratorio;
	@Wire
	private Radio rdoHospital;
	@Wire
	private Radio rdoLaboratorio;
	@Wire
	private Checkbox ckbCama;
	@Wire
	private Checkbox ckbPrestacion;
	@Wire
	private Checkbox ckbGes;
	@Wire
	private Label lblCama;
	@Wire
	private Combobox cmbCama;
	@Wire
	private Combobox cmbTipoCama;
	@Wire
	private Label lblTipoPrestacion;
	@Wire
	private Combobox cmbPrestacion;
	@Wire
	private Combobox cmbTipoPrecedimiento;
	@Wire
	private Checkbox ckbOtraPrestacion;
	@Wire
	private Textbox txtDescripcionPrestacion;
	@Wire
	private Textbox txtDiagnostico;
	@Wire
	private Combobox cmbTipoDerivacion;
	@Wire
	private Window wdwModalPrestaciones;
	@Wire
	private Window wdwModalResponsables;
	@Wire
	private Window wdwSolicitud;
	@Wire
	private Button btnDetallePrestacion;
	@Wire
	private Listbox lbxPrestacionesSolicitud;
	@Wire
	private Textbox txtTelefonoPaciente;
	@Wire
	private Textbox txtFicha;
	@Wire
	private Label lblMotivoCama;
	@Wire
	private Label lblFiltrosEstableciminto;
	@Wire
	private Label lblResponsables;
	@Wire
	private Label lblPrimerApellido;
	@Wire
	private Label lblSegundoApellido;
	@Wire
	private Label lblNombres;
	@Wire
	private Label lblFechaNacimiento;
	@Wire
	private Label lblGenero;
	@Wire
	private Label lblComuna;
	@Wire
	private Label lblDireccion;
	@Wire
	private Label lblPrevision;
	@Wire
	private Label lblTelefono;
	@Wire
	private Combobox cmbMotivoCama;
	@Wire
	private Textbox txtOtroEstablecimiento;
	@Wire
	private Row row1Paciente;
	@Wire
	private Row row2Paciente;
	@Wire
	private Row row3Paciente;
	@Wire
	private Row row4Paciente;
	@Wire
	private Row row5Paciente;
	private boolean rutPaciente = false;
	private boolean isConsulta = false;
	@Wire
	private Textbox txtIdUgcc;
	@Wire
	private Textbox txtBusquedaEstablecimiento;
	@Wire
	private Textbox txtBusquedaRutEstablecimiento;
	@Wire
	private Window wdwDiagnostico;
	private boolean sinFonasa = false;
	private boolean perteneceRed = false;

	Diagnostico diagnostico = new Diagnostico();

	Botonera botonera;
	List<PrestacionSolicitud> prestacionesSolicitud = new ArrayList<PrestacionSolicitud>();
	ReplyCertificadorPrevisionalTO respuesta = new ReplyCertificadorPrevisionalTO();

	Catalogo<DetallePrestacion> catalogo;

	@Override
	public void inicializar() throws IOException {
		llenarCombos();
		lblResponsables.setValue("Recuerde que debe seleccionar" + " "
				+ usuarioActivo.getEstablecimiento().getCantidadFirmantes()
				+ " " + "responsables de la Carta Respaldo.");

		botonera = new Botonera() {

			@Override
			public void salir() {
				wdwSolicitud.detach();
				borrarDataSesion();
			}

			@Override
			public void limpiar() {
				limpiarCampos();
				borrarDataSesion();
			}

			@Override
			public void guardar() {
				if (validarCampos()) {
					if (usuarioActivo.getEstablecimiento()
							.getCantidadFirmantes() == recuperarDataSession()
							.size()) {
						if (sinFonasa || !perteneceRed) {
							Messagebox
									.show("Paciente posee restricciones -"+ lblErrorRutPaciente.getValue()+", ¿Desea generar la carta?",
											"Alerta",
											Messagebox.OK | Messagebox.CANCEL,
											Messagebox.QUESTION,
											new org.zkoss.zk.ui.event.EventListener<Event>() {
												public void onEvent(Event evt)
														throws InterruptedException,
														JSONException {
													if (evt.getName().equals(
															"onOK")) {
														instruccionesGuardar();
													}
												}
											});
						} else {
							instruccionesGuardar();
						}
					} else {
						Messagebox
								.show("La cantidad de cargos configurados debe coincidir con el numero de cargos seleccionados como responsables",
										"Información", Messagebox.OK,
										Messagebox.INFORMATION);
					}
				} else {
					Messagebox.show(Constantes.mensajeCamposVacios,
							"Advertencia", Messagebox.OK,
							Messagebox.EXCLAMATION);
				}
			}

			@Override
			public void eliminar() {

			}
		};
		botonera.getChildren().get(0).setVisible(true);
		botonera.getChildren().get(1).setVisible(false);
		botonera.getChildren().get(2).setVisible(true);
		botonera.getChildren().get(3).setVisible(true);
		botoneraSolicitud.appendChild(botonera);
		log.info("Fin del metodo inicializar ()");
	}

	public void instruccionesGuardar() {
		prepararObjetosGuardar();
		Messagebox.show(Constantes.mensajeRegistroGuardado, "Información",
				Messagebox.OK, Messagebox.INFORMATION);
		try {
			generarReporteQuemado(solicitudGuardada.getId());
		} catch (JSONException e) {
			e.printStackTrace();
		}
		borrarDataSesion();
		limpiarCampos();
	}

	public void obtenerDiagnostico() {
		String diagnosticoMap = (String) Sessions.getCurrent().getAttribute(
				"diagnostico");
		txtDiagnostico = (Textbox) Sessions.getCurrent().getAttribute("text");
		if (diagnosticoMap != null) {
			txtDiagnostico.setValue(diagnosticoMap);
		}
	}

	public void borrarDataSesion() {
		Sessions.getCurrent().setAttribute("prestacionesSolicitud", null);
		Sessions.getCurrent().setAttribute("responsablesSolicitud", null);
		Sessions.getCurrent().setAttribute("itemsCatalogo", null);
		Sessions.getCurrent().setAttribute("diagnostico", null);
	}

	public List<ResponsableSolicitud> recuperarDataSession() {
		List<ResponsableSolicitud> responsablesSolicitud = new ArrayList<ResponsableSolicitud>();
		List<ResponsableSolicitud> responsablesSolicitud1 = new ArrayList<ResponsableSolicitud>();
		responsablesSolicitud = (List<ResponsableSolicitud>) Sessions
				.getCurrent().getAttribute("responsablesSolicitud");
		if (responsablesSolicitud != null) {
			return responsablesSolicitud;
		} else {
			return responsablesSolicitud1;
		}
	}

	public void llenarCombos() {
		usuarioActivo = usuarioActivo();
		regiones = servicioRegion.buscarTodos();
		sexos = servicioSexo.buscarTodos();
		cmbSexo.setModel(new ListModelList<Sexo>(sexos));
		regionesPaciente = servicioRegion.buscarTodos();
		cmbRegionPaciente.setModel(new ListModelList<Region>(regionesPaciente));

		prestaciones = servicioPrestacion.buscarPrestaciones(2);
		camas = servicioDetallePrestacion.buscarPrestaciones(1);

		cmbCama.setModel(new ListModelList<DetallePrestacion>(camas));
		cmbPrestacion.setModel(new ListModelList<Prestacion>(prestaciones));
		unidades = servicioUnidad.buscarPorEstablecimiento(usuarioActivo
				.getEstablecimiento().getId());
		cmbUnidad.setModel(new ListModelList<Unidad>(unidades));
		motivos = servicioMotivo.buscarTodos();
		cmbMotivoCama.setModel(new ListModelList<Motivo>(motivos));

	}

	@Listen("onChange = #cmbRegionPaciente")
	public void cargarProvincias() {
		if (!cmbRegionPaciente.getValue().equals("")) {
			int idRegion = Integer.valueOf(cmbRegionPaciente.getSelectedItem()
					.getContext());
			provincias = servicioProvincia.buscarPorRegion(idRegion);
			cmbProvincia.setModel(new ListModelList<Provincia>(provincias));
		}
	}

	@Listen("onChange = #cmbProvincia")
	public void cargarComunas() {
		if (!cmbProvincia.getValue().equals("")) {
			int idProvincia = Integer.valueOf(cmbProvincia.getSelectedItem()
					.getContext());
			comunas = servicioComuna.buscarPorProvincia(idProvincia);
			cmbComuna.setModel(new ListModelList<Comuna>(comunas));
		}
	}

	@Listen("onChange = #txtRutPaciente")
	public void validarRutPaciente() {
		log.info("Inicio del metodo validarRutPaciente()");
		if (txtRutPaciente.getValue() != null) {
			if (!validarRut(txtRutPaciente.getValue())) {
				divErrorRutPaciente.setVisible(true);
				lblErrorRutPaciente
						.setValue(Constantes.mensajeRutPacienteNoValido);
				rutPaciente = true;
				log.debug(new StringBuilder().append("No valido rut").append(
						txtRutPaciente.getValue()));
				limpiarCamposPaciente();
				inhabilitarRows();
			} else {
				divErrorRutPaciente.setVisible(false);
				String rut = formatearRut(txtRutPaciente.getValue());
				txtRutPaciente.setValue(rut);
				log.debug(new StringBuilder().append("Valido rut:").append(
						txtRutPaciente.getValue()));
				rutPaciente = false;
				int posicion = rut.indexOf("-");
				String rutParteEntera = "";
				if (posicion != -1) {
					rutParteEntera = rut.substring(0, posicion);
				}
				String verificador = rut.substring(posicion + 1, rut.length());
				recibirDatosWS(rutParteEntera, verificador);
			}
		}
		log.info("Fin del metodo validarRutPaciente()");
	}

	@Listen("onClick = #ckbCama")
	public void trasladoCama() {
		if (ckbCama.isChecked()) {
			lblCama.setVisible(true);
			cmbTipoCama.setVisible(true);
			cmbCama.setVisible(true);
			lblMotivoCama.setVisible(true);
			cmbMotivoCama.setVisible(true);
		} else {
			lblCama.setVisible(false);
			cmbTipoCama.setVisible(false);
			cmbCama.setVisible(false);
			lblMotivoCama.setVisible(false);
			cmbMotivoCama.setVisible(false);
		}
	}

	@Listen("onClick = #ckbPrestacion")
	public void trasladoPrestacion() {
		if (ckbPrestacion.isChecked()) {
			lblTipoPrestacion.setVisible(true);
			cmbPrestacion.setVisible(true);
			ckbOtraPrestacion.setVisible(true);
			txtDescripcionPrestacion.setVisible(true);
		} else {
			lblTipoPrestacion.setVisible(false);
			cmbPrestacion.setVisible(false);
			ckbOtraPrestacion.setVisible(false);
			txtDescripcionPrestacion.setVisible(false);
			cmbTipoPrecedimiento.setVisible(false);
		}
	}

	@Listen("onChange = #cmbPrestacion")
	public void prestacionProcedimiento() {
		if (cmbPrestacion.getValue().equals("CONSULTAS")) {
			isConsulta = true;
			mostrarCatalogo();
			cmbTipoPrecedimiento.setVisible(false);
		} else {
			cmbTipoPrecedimiento.setValue("");
			cmbTipoPrecedimiento.setVisible(true);
			Prestacion prestacion = cmbPrestacion.getSelectedItem().getValue();
			List<CategoriaPrestacion> categorias = new ArrayList<CategoriaPrestacion>();
			categorias = servicioCategoriaPrestacion
					.buscarPorPrestacion(prestacion.getId());
			cmbTipoPrecedimiento
					.setModel(new ListModelList<CategoriaPrestacion>(categorias));
			isConsulta = false;
		}
	}

	@Listen("onClick = #rdoHospital")
	public void destinoClinicaHospital() {
		cmbEstablecimiento.setVisible(true);
		lblEstablecimiento.setVisible(true);
		txtBusquedaEstablecimiento.setVisible(true);
		txtBusquedaRutEstablecimiento.setVisible(true);
		lblFiltrosEstableciminto.setVisible(true);
		lblNombreLaboratorio.setVisible(false);
		txtLaboratorio.setVisible(false);
		establecimientos = servicioEstablecimiento.buscarRegion(13, false);
		Establecimiento es = new Establecimiento();
		es.setNombre("Otro");
		es.setRut("0");
		es.setId(500);
		establecimientos.add(es);
		cmbEstablecimiento.setModel(new ListModelList<Establecimiento>(
				establecimientos));
	}

	@Listen("onBlur = #cmbEstablecimiento")
	public void comboEstablecimiento() {
		if (cmbEstablecimiento.getSelectedItem() != null) {
			if (cmbEstablecimiento.getValue().equals("Otro - 0")) {
				txtOtroEstablecimiento.setVisible(true);
			} else {
				txtOtroEstablecimiento.setVisible(false);
			}
		}
	}

	@Listen("onClick = #rdoLaboratorio")
	public void destinoLaboratorio() {
		lblNombreLaboratorio.setVisible(true);
		txtLaboratorio.setVisible(true);
		cmbEstablecimiento.setVisible(false);
		lblEstablecimiento.setVisible(false);
		txtOtroEstablecimiento.setVisible(false);
		txtBusquedaEstablecimiento.setVisible(false);
		txtBusquedaRutEstablecimiento.setVisible(false);
		lblFiltrosEstableciminto.setVisible(false);
	}

	public Paciente prepararPaciente() {
		Paciente pacienteGuardado = new Paciente();
		String text = lblFechaNacimiento.getValue() + " " + "00:00:00";
		Timestamp ts = Timestamp.valueOf(text);
		paciente.setFechaNacimiento(ts);
		paciente.setDomicilio(lblDireccion.getValue().trim());
		paciente.setNombres(lblNombres.getValue().trim());
		paciente.setRut(txtRutPaciente.getValue());
		paciente.setPrimerApellido(lblPrimerApellido.getValue().trim());
		paciente.setSegundoApellido(lblSegundoApellido.getValue().trim());
		paciente.setComuna(servicioComuna.buscarCodigo(Integer
				.valueOf(respuesta.getBeneficiarioTO().getCdgComuna())));
		paciente.setSexo(lblGenero.getValue().trim());
		paciente.setPrevision(respuesta.getAfiliadoTO().getTramo().trim());
		paciente.setTelefono(lblTelefono.getValue().trim());
		pacienteGuardado = servicioPaciente.guardar(paciente);
		/**
		 * ya va + Paciente pacienteGuardado = new Paciente();
		 * paciente.setRut(txtRutPaciente.getValue());7uy
		 * paciente.setPrimerApellido(txtPrimerApellidoPaciente.getValue());
		 * paciente.setNombres(txtPrimerNombrePaciente.getValue() + " " +
		 * txtSegundoNombrePaciente.getValue()); paciente.setComuna((Comuna)
		 * cmbComuna.getSelectedItem().getValue());
		 * paciente.setDomicilio(txtDireccionPaciente.getValue());
		 * paciente.setPrevision(cmbPrevisionPaciente.getValue());
		 * paciente.setSexo((Sexo) cmbSexo.getSelectedItem().getValue());
		 * paciente.setSegundoApellido(txtSegundoApellidoPaciente.getValue());
		 * Date fecha = dtbFechaNacPaciente.getValue(); Timestamp
		 * fechaNacimiento = new Timestamp(fecha.getTime());
		 * paciente.setFechaNacimiento(fechaNacimiento);
		 * paciente.setTelefono(txtTelefonoPaciente.getValue());
		 * pacienteGuardado = servicioPaciente.guardar(paciente);
		 **/
		return pacienteGuardado;
	}

	public void prepararBitacora(SolicitudTraslado solicitudTraslado) {
		bitacora.setFecha(fechaHora);
		bitacora.setEstatus(EnumEstadoSolicitud.CREADA.getEstado());
		bitacora.setTraslado(solicitudTraslado);
		bitacora.setUsuario(usuarioActivo);
		bitacora.setValidada(false);
		bitacora.setActivo(true);
		servicioBitacora.guardar(bitacora);
	}

	public void prepararPrestaciones(SolicitudTraslado solicitudTraslado) {
		PrestacionSolicitud ps = new PrestacionSolicitud();
		prestacionesSolicitud = (List<PrestacionSolicitud>) Sessions
				.getCurrent().getAttribute("prestacionesSolicitud");
		if (ckbCama.isChecked()) {
			ps.setPrestacion((DetallePrestacion) cmbCama.getSelectedItem()
					.getValue());
			ps.setTipoCama(cmbTipoCama.getValue());
			ps.setMotivo((Motivo) cmbMotivoCama.getSelectedItem().getValue());
			if (prestacionesSolicitud == null) {
				prestacionesSolicitud = new ArrayList<PrestacionSolicitud>();
			}
			prestacionesSolicitud.add(ps);
		}
		for (int i = 0; i < prestacionesSolicitud.size(); i++) {
			prestacionesSolicitud.get(i)
					.setSolicitudTraslado(solicitudTraslado);
			servicioPrestacionSolicitud.guardar(prestacionesSolicitud.get(i));
		}
	}

	public void prepararResponsables(SolicitudTraslado solicitudTraslado) {
		responsablesSolicitud = (List<ResponsableSolicitud>) Sessions
				.getCurrent().getAttribute("responsablesSolicitud");
		if (responsablesSolicitud != null) {
			for (int i = 0; i < responsablesSolicitud.size(); i++) {
				responsablesSolicitud.get(i).setSolicitudTraslado(
						solicitudTraslado);
				servicioResponsableSolicitud.guardar(responsablesSolicitud
						.get(i));
			}
		}
	}

	public Diagnostico prepararDiagnostico() {
		return diagnostico = servicioDiagnostico.buscarNombre(txtDiagnostico
				.getValue());
	}

	public void prepararObjetosGuardar() {
		if (pacienteRegistrado == null) {
			solicitudTraslado.setPaciente(prepararPaciente());
		} else {
			servicioPaciente.guardar(pacienteRegistrado);
			solicitudTraslado.setPaciente(pacienteRegistrado);
		}
		if (rdoHospital.isChecked()) {
			if (cmbEstablecimiento.getValue().equals("Otro")) {
				Establecimiento establecimiento = new Establecimiento();
				Establecimiento establecimientoGuardado = new Establecimiento();
				establecimiento.setNombre(txtOtroEstablecimiento.getValue());
				establecimiento.setDestino(false);
				establecimiento.setRegion(usuarioActivo.getEstablecimiento()
						.getRegion());
				establecimientoGuardado = servicioEstablecimiento
						.guardarRetorno(establecimiento);
				solicitudTraslado.setEstablecimiento(establecimientoGuardado);
			} else {
				solicitudTraslado
						.setEstablecimiento((Establecimiento) cmbEstablecimiento
								.getSelectedItem().getValue());
			}
		} else if (rdoLaboratorio.isChecked()) {
			Establecimiento establecimiento = new Establecimiento();
			Establecimiento establecimientoGuardado = new Establecimiento();
			establecimiento.setNombre(txtLaboratorio.getValue());
			establecimiento.setDestino(false);
			establecimiento.setRegion(usuarioActivo.getEstablecimiento()
					.getRegion());
			establecimientoGuardado = servicioEstablecimiento
					.guardarRetorno(establecimiento);
			solicitudTraslado.setEstablecimiento(establecimientoGuardado);
		}

		solicitudTraslado.setDiagnostico(prepararDiagnostico());
		solicitudTraslado.setTipoDerivacion(cmbTipoDerivacion.getValue());
		solicitudTraslado.setFicha(txtFicha.getValue());
		solicitudTraslado.setFolio(rellenarCeros(servicioSolicitudTraslado
				.maxId() + 1));
		solicitudTraslado.setUnidad((Unidad) cmbUnidad.getSelectedItem()
				.getValue());
		solicitudTraslado.setDescripcion(txtDescripcionPrestacion.getValue());
		solicitudTraslado.setIdUgcc(txtIdUgcc.getValue());
		if (ckbGes.isChecked()) {
			solicitudTraslado.setGes(true);
		} else {
			solicitudTraslado.setGes(false);
		}
		if (sinFonasa) {
			solicitudTraslado.setObservacion(Constantes.observacionSinFonasa);
			solicitudTraslado.setObservacionRestriccion(observacion);
		} else if (!perteneceRed) {
			solicitudTraslado.setObservacion(Constantes.observacionNoEsRed);
			solicitudTraslado.setObservacionRestriccion(observacion);
		}
		solicitudGuardada = servicioSolicitudTraslado
				.guardar(solicitudTraslado);
		prepararBitacora(solicitudGuardada);
		prepararPrestaciones(solicitudGuardada);
		prepararResponsables(solicitudGuardada);
	}

	@Listen("onChange = #cmbTipoPrecedimiento")
	public void buscarPrestaciones() {
		mostrarCatalogo();
	}

	public void mostrarCatalogo() {
		List<DetallePrestacion> prestacionesExamenes = new ArrayList<DetallePrestacion>();
		List<DetallePrestacion> prestacionesProcedimientos = new ArrayList<DetallePrestacion>();
		List<DetallePrestacion> prestacionesIntervensiones = new ArrayList<DetallePrestacion>();
		List<DetallePrestacion> prestacionesConsultas = new ArrayList<DetallePrestacion>();
		prestacionesSolicitud = (List<PrestacionSolicitud>) Sessions
				.getCurrent().getAttribute("prestacionesSolicitud");
		boolean examenes = false;
		boolean procedimientos = false;
		boolean intervesiones = false;
		boolean consultas = false;
		CategoriaPrestacion categoria = new CategoriaPrestacion();
		if (!isConsulta) {
			categoria = cmbTipoPrecedimiento.getSelectedItem().getValue();
		}
		if (Integer.valueOf(cmbPrestacion.getSelectedItem().getContext()) == 2) {
			prestacionesExamenes = servicioDetallePrestacion
					.buscarPorCategorias(categoria.getCodigo());
			examenes = true;
		} else if (Integer
				.valueOf(cmbPrestacion.getSelectedItem().getContext()) == 3) {
			if (cmbTipoPrecedimiento.getValue().equals(
					Constantes.nombreProcedimientoTraumatologia)) {
				prestacionesProcedimientos = servicioDetallePrestacion
						.buscarPorCategoriasTraumatologia(
								Constantes.codigoDesdeProcedimientos,
								Constantes.codigoHastaProcedimientos);
			} else {
				prestacionesProcedimientos = servicioDetallePrestacion
						.buscarPorCategorias(categoria.getCodigo());
			}
			procedimientos = true;
		} else if (Integer
				.valueOf(cmbPrestacion.getSelectedItem().getContext()) == 4) {
			if (cmbTipoPrecedimiento.getValue().equals(
					Constantes.nombreIntervencionTraumatologia)) {
				prestacionesIntervensiones = servicioDetallePrestacion
						.buscarPorCategoriasTraumatologia(
								Constantes.codigoDesdeIntervenciones,
								Constantes.codigoHastaIntenvenciones);
			} else {
				prestacionesIntervensiones = servicioDetallePrestacion
						.buscarPorCategorias(categoria.getCodigo());
			}
			intervesiones = true;
		} else if (Integer
				.valueOf(cmbPrestacion.getSelectedItem().getContext()) == 5) {

			prestacionesConsultas = servicioDetallePrestacion
					.buscarPorCategorias("0101");
			consultas = true;
		}
		final HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("prestacionesExamenes", prestacionesExamenes);
		map.put("prestacionesProcedimientos", prestacionesProcedimientos);
		map.put("prestacionesIntervensiones", prestacionesIntervensiones);
		map.put("prestacionesConsultas", prestacionesConsultas);
		map.put("examenes", examenes);
		map.put("procedimientos", procedimientos);
		map.put("intervesiones", intervesiones);
		map.put("consultas", consultas);
		map.put("prestacionesSolicitud", prestacionesSolicitud);
		Sessions.getCurrent().setAttribute("itemsCatalogo", map);
		wdwModalPrestaciones = (Window) Executions
				.createComponents(
						"public/vistas/transacciones/modal-prestaciones.zul",
						null, map);
		wdwModalPrestaciones.doModal();
	}

	@Listen("onClick = #btnDetallePrestacion")
	public void verDetallePrestaciones() {
		llenarListaDetalle();
	}

	public void llenarListaDetalle() {
		lbxPrestacionesSolicitud.setVisible(true);
		List<PrestacionSolicitud> prestacionesSolicitud = new ArrayList<PrestacionSolicitud>();
		prestacionesSolicitud = (List<PrestacionSolicitud>) Sessions
				.getCurrent().getAttribute("prestacionesSolicitud");
		if (prestacionesSolicitud != null) {
			lbxPrestacionesSolicitud
					.setModel(new ListModelList<PrestacionSolicitud>(
							prestacionesSolicitud));
		}
	}

	public void limpiarCampos() {
		txtDescripcionPrestacion.setValue("");
		txtDiagnostico.setValue("");
		txtDireccionPaciente.setValue("");
		txtLaboratorio.setValue("");
		txtPrimerApellidoPaciente.setValue("");
		txtPrimerNombrePaciente.setValue("");
		txtRutPaciente.setValue("");
		txtSegundoApellidoPaciente.setValue("");
		txtSegundoNombrePaciente.setValue("");
		txtTelefonoPaciente.setValue("");
		cmbCama.setValue("");
		cmbComuna.setValue("");
		cmbEstablecimiento.setValue("");
		cmbPrestacion.setValue("");
		cmbPrevisionPaciente.setValue("");
		cmbProvincia.setValue("");
		cmbRegionPaciente.setValue("");
		cmbSexo.setValue("");
		cmbTipoCama.setValue("");
		cmbTipoDerivacion.setValue("");
		cmbTipoPrecedimiento.setValue("");
		cmbUnidad.setValue("");
		dtbFechaNacPaciente.setValue(null);
		ckbCama.setChecked(false);
		ckbGes.setChecked(false);
		ckbPrestacion.setChecked(false);
		lbxPrestacionesSolicitud.setVisible(false);
		if (rdoHospital.isChecked()) {
			rdoHospital.setChecked(false);
		}
		if (rdoLaboratorio.isChecked()) {
			rdoLaboratorio.setChecked(false);
		}
		lblNombreLaboratorio.setVisible(false);
		txtLaboratorio.setVisible(false);
		lblEstablecimiento.setVisible(false);
		cmbEstablecimiento.setVisible(false);
		lblCama.setVisible(false);
		cmbCama.setVisible(false);
		cmbTipoCama.setVisible(false);
		lblTipoPrestacion.setVisible(false);
		cmbPrestacion.setVisible(false);
		cmbTipoPrecedimiento.setVisible(false);
		ckbOtraPrestacion.setVisible(false);
		txtDescripcionPrestacion.setVisible(false);
		lblErrorRutPaciente.setVisible(false);
		txtFicha.setValue("");
		lblMotivoCama.setVisible(false);
		cmbMotivoCama.setVisible(false);
		cmbMotivoCama.setValue("");
		txtOtroEstablecimiento.setValue("");
		txtOtroEstablecimiento.setVisible(false);
		txtIdUgcc.setValue("");
		txtBusquedaEstablecimiento.setVisible(false);
		txtBusquedaRutEstablecimiento.setVisible(false);
		txtBusquedaEstablecimiento.setValue("");
		txtBusquedaRutEstablecimiento.setValue("");
		inhabilitarRows();
		lblFiltrosEstableciminto.setVisible(false);
		divErrorRutPaciente.setVisible(false);
		lblErrorRutPaciente.setValue("");
		sinFonasa = false;
		perteneceRed = false;
		observacion = "";
	}

	public void limpiarCamposPaciente() {
		txtDireccionPaciente.setValue("");
		txtPrimerApellidoPaciente.setValue("");
		txtPrimerNombrePaciente.setValue("");
		txtSegundoApellidoPaciente.setValue("");
		txtSegundoNombrePaciente.setValue("");
		txtTelefonoPaciente.setValue("");
		cmbComuna.setValue("");
		cmbPrevisionPaciente.setValue("");
		cmbProvincia.setValue("");
		cmbRegionPaciente.setValue("");
		dtbFechaNacPaciente.setValue(null);
		cmbSexo.setValue("");
		inhabilitarRows();
	}

	public boolean validarCampos() {

		if (txtDiagnostico.getValue().equals("")
				|| lblDireccion.getValue().equals("")
				|| lblNombres.getValue().equals("")
				|| txtRutPaciente.getValue().equals("")
				|| lblSegundoApellido.getValue().equals("")
				|| lblPrimerApellido.getValue().equals("")
				|| lblComuna.getValue().equals("")
				|| lblGenero.getValue().equals("")
				|| cmbTipoDerivacion.getSelectedItem() == null
				|| cmbUnidad.getSelectedItem() == null
				|| lblFechaNacimiento.getValue().equals("")
				|| txtFicha.getValue().equals("")
				|| txtIdUgcc.getValue().equals("")) {
			cmbUnidad.setStyle("    border-color: red !important;");
			return false;
		} else {
			if (rdoHospital.isChecked()) {
				if (cmbEstablecimiento.getSelectedItem() == null) {
					return false;
				}
			}
			if (rdoLaboratorio.isChecked()) {
				if (txtLaboratorio.getValue().equals("")) {
					return false;
				}
			}
			if (ckbCama.isChecked()) {
				if (cmbCama.getSelectedItem() == null
						|| cmbTipoCama.getSelectedItem() == null
						|| cmbMotivoCama.getSelectedItem() == null) {
					return false;
				}
			}
			if (ckbPrestacion.isChecked()) {
				prestacionesSolicitud = (List<PrestacionSolicitud>) Sessions
						.getCurrent().getAttribute("prestacionesSolicitud");
				if (prestacionesSolicitud == null) {
					return false;
				}

			}
			if (ckbOtraPrestacion.isChecked()) {
				if (txtDescripcionPrestacion.getValue().equals("")) {
					return false;
				}
			}
			return true;
		}
	}

	public void generarReporteQuemado(int id) throws JSONException {
		Clients.evalJavaScript("window.open('/CartaRespaldo/Reportero?valor1="
				+ id
				+ "','','top=100,left=200,height=600,width=800,scrollbars=1,resizable=1')");
	}

	public byte[] reporteTraslado(String id) throws JRException, IOException {
		byte[] fichero = null;
		int idSolicitud = Integer.valueOf(id);
		SolicitudTraslado solicitudTraslado = getServicioSolicitudTraslado()
				.buscarSolicitud(idSolicitud);
		List<PrestacionSolicitud> prestaciones = getServicioPrestacionSolicitud()
				.prestacionesPorSolicitud(idSolicitud);
		List<ResponsableSolicitud> responsables = getServicioResponsableSolicitud()
				.responsablesSolicitud(idSolicitud);
		Usuario usuarioActivo = new Usuario();
		Authentication auth = SecurityContextHolder.getContext()
				.getAuthentication();
		usuarioActivo = getServicioUsuario().buscarUsuarioPorNombre(
				auth.getName());

		Map<String, Object> p = new HashMap<String, Object>();
		String prestacionesPaciente = "";
		if (solicitudTraslado.getDescripcion() != null) {
			prestacionesPaciente = solicitudTraslado.getDescripcion();
		}
		for (int i = 0; i < prestaciones.size(); i++) {
			if (prestacionesPaciente.equals("")) {
				prestacionesPaciente = prestacionesPaciente
						+ prestaciones.get(i).getPrestacion().getPrestacion()
								.getDescripcion()
						+ " "
						+ cortarCadena(prestaciones.get(i).getPrestacion()
								.getNombre());
			} else {
				prestacionesPaciente = prestacionesPaciente
						+ ", "
						+ prestaciones.get(i).getPrestacion().getPrestacion()
								.getDescripcion()
						+ " "
						+ cortarCadena(prestaciones.get(i).getPrestacion()
								.getNombre());
			}
		}
		p.put("paciente", solicitudTraslado.getPaciente().getNombres()
				.toUpperCase()
				+ " "
				+ solicitudTraslado.getPaciente().getPrimerApellido()
						.toUpperCase()
				+ " "
				+ solicitudTraslado.getPaciente().getSegundoApellido()
						.toUpperCase());
		SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
		Date fechaActual = new Date();
		String hoy = formato.format(fechaActual);

		p.put("fecha", "Santiago," + hoy);
		p.put("folio", solicitudTraslado.getFolio());
		p.put("establecimientoDestino", solicitudTraslado.getEstablecimiento()
				.getNombre());
		p.put("establecimientoOrigen", usuarioActivo.getEstablecimiento()
				.getNombre());
		p.put("rutPaciente", solicitudTraslado.getPaciente().getRut());
		p.put("numeroFicha", solicitudTraslado.getFicha());
		p.put("edadPaciente", calcularEdad(solicitudTraslado.getPaciente()
				.getFechaNacimiento()));
		p.put("previsionPaciente", "FONASA" + " "
				+ solicitudTraslado.getPaciente().getPrevision());
		p.put("telefonoPaciente", solicitudTraslado.getPaciente().getTelefono());
		p.put("domicilioPaciente", solicitudTraslado.getPaciente()
				.getDomicilio());
		p.put("comunaPaciente", solicitudTraslado.getPaciente().getComuna()
				.getNombre());
		p.put("diagnosticoPaciente", solicitudTraslado.getDiagnostico()
				.getNombre());
		p.put("unidadDerivadora", solicitudTraslado.getUnidad().getNombre());
		p.put("rutEstablecimientoDeriva", usuarioActivo.getEstablecimiento()
				.getRut());
		p.put("direccionEstablecimientoDeriva", usuarioActivo
				.getEstablecimiento().getDireccion());

		p.put("funcionario0", "NO APLICA");
		p.put("funcionario1", "NO APLICA");
		p.put("funcionario2", "NO APLICA");
		p.put("cargo0", "");
		p.put("cargo1", "");
		p.put("cargo2", "");

		for (int j = 0; j < responsables.size(); j++) {
			p.put("funcionario" + j, responsables.get(j).getResponsable()
					.getNombre());
			p.put("cargo" + j, responsables.get(j).getResponsable().getCargo()
					.getNombre());
		}

		p.put("telefonoHospital", usuarioActivo.getEstablecimiento()
				.getTelefono());
		p.put("motivoTraslado", prestacionesPaciente);
		if (usuarioActivo.getEstablecimiento().getId() == 3) {
			p.put("logoEstablecimiento", "/ssmc/CartaRespaldo/reporte/huap.jpg");
			p.put("acronimoHospital", "HUAP");
		} else if (usuarioActivo.getEstablecimiento().getId() == 1) {
			p.put("logoEstablecimiento",
					"/ssmc/CartaRespaldo/reporte/sanborja.jpg");
			p.put("acronimoHospital", "HCSBA");
		} else if (usuarioActivo.getEstablecimiento().getId() == 2) {
			p.put("logoEstablecimiento", "/ssmc/CartaRespaldo/reporte/gec.jpg");
			p.put("acronimoHospital", "HEC");
		}
		p.put("logoMinsal", "/ssmc/CartaRespaldo/reporte/minsal.jpg");
		JasperReport reporte = (JasperReport) JRLoader.loadObject(getClass()
				.getResource(Constantes.rutaReporte));
		fichero = JasperRunManager.runReportToPdf(reporte, p);

		return fichero;
	}

	public String rellenarCeros(int id) {
		String idS = "";
		idS = String.format("%06d", id);
		return idS;
	}

	@Listen("onClick = #btnMedico")
	public void buscarMedicos() {
		medicosResponsables = servicioResponsable.buscarEstablecimientoCargo(
				usuarioActivo.getEstablecimiento().getId(), 1);
		final HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("medicos", medicosResponsables);
		map.put("isMedico", true);
		map.put("isFuncionario", false);
		Sessions.getCurrent().setAttribute("itemsCatalogo", map);
		wdwModalResponsables = (Window) Executions
				.createComponents(
						"public/vistas/transacciones/modal-responsables.zul",
						null, map);
		wdwModalResponsables.doModal();
	}

	@Listen("onClick = #btnFuncionarios")
	public void buscarFuncionarios() {
		funcionariosResponsables = new ArrayList<Responsable>();
		cargosEstablecimiento = servicioCargoEstablecimiento
				.cargosEstablecimientos(usuarioActivo.getEstablecimiento()
						.getId());
		for (int i = 0; i < cargosEstablecimiento.size(); i++) {
			if (cargosEstablecimiento.get(i).getCargo().getId() != 1) {
				funcionariosResponsables
						.addAll(servicioResponsable
								.buscarEstablecimientoCargo(usuarioActivo
										.getEstablecimiento().getId(),
										cargosEstablecimiento.get(i).getCargo()
												.getId()));

			}
		}
		final HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("funcionarios", funcionariosResponsables);
		map.put("isFuncionario", true);
		map.put("isMedico", false);
		Sessions.getCurrent().setAttribute("itemsCatalogo", map);
		wdwModalResponsables = (Window) Executions
				.createComponents(
						"public/vistas/transacciones/modal-responsables.zul",
						null, map);
		wdwModalResponsables.doModal();
	}

	@Listen("onOK = #txtRutPaciente")
	public void buscarRut() {
		validarRutPaciente();
	}

	@Listen("onChange= #txtBusquedaEstablecimiento")
	public void filtrarEstablecimiento() {
		cmbEstablecimiento.setValue("");
		List<Establecimiento> lista = new ArrayList<Establecimiento>();
		for (Establecimiento establecimiento : establecimientos) {
			if (establecimiento
					.getNombre()
					.toLowerCase()
					.contains(
							txtBusquedaEstablecimiento.getValue().toLowerCase())) {
				lista.add(establecimiento);
			}
		}
		cmbEstablecimiento.setModel(new ListModelList<Establecimiento>(lista));
		cmbEstablecimiento.setStyle("height: 350px !important;");
	}

	@Listen("onChange= #txtBusquedaRutEstablecimiento")
	public void filtrarRutEstablecimiento() {
		cmbEstablecimiento.setValue("");
		List<Establecimiento> lista = new ArrayList<Establecimiento>();
		if (!txtBusquedaRutEstablecimiento.getValue().equals("")) {
			String rut = formatearRut(txtBusquedaRutEstablecimiento.getValue());
			for (Establecimiento establecimiento : establecimientos) {
				if (establecimiento.getRut().equals(rut)) {
					lista.add(establecimiento);
					log.info(new StringBuilder(
							"Se Agrego a la lista Establecimiento:")
							.append(establecimiento));
				}
			}
			cmbEstablecimiento.setModel(new ListModelList<Establecimiento>(
					lista));
			cmbEstablecimiento.setStyle("height: 350px !important;");
		} else {
			cmbEstablecimiento.setModel(new ListModelList<Establecimiento>(
					establecimientos));
		}

	}

	public void recibirDatosWS(String rut, String verificador) {
		sinFonasa = false;
		perteneceRed = false;
		respuesta = consumirWsFonasa(Integer.parseInt(rut), verificador);
		if (respuesta.getReplyTO().getEstado() == 0
				&& respuesta.getReplyTO().getErrorM().equals("")) {
			habilitarRows();
			lblPrimerApellido.setValue(respuesta.getBeneficiarioTO()
					.getApell1());
			lblSegundoApellido.setValue(respuesta.getBeneficiarioTO()
					.getApell2());
			lblNombres.setValue(respuesta.getBeneficiarioTO().getNombres());
			lblFechaNacimiento.setValue(respuesta.getBeneficiarioTO()
					.getFechaNacimiento());
			lblGenero.setValue(respuesta.getBeneficiarioTO().getGeneroDes());
			lblComuna.setValue(respuesta.getBeneficiarioTO().getDesComuna());
			lblDireccion.setValue(respuesta.getBeneficiarioTO().getDireccion());
			lblPrevision.setValue(respuesta.getAfiliadoTO().getTramo());
			lblTelefono.setValue(respuesta.getBeneficiarioTO().getTelefono());
			pacienteRegistrado = servicioPaciente
					.buscarRut(formatearRut(txtRutPaciente.getValue()));
			if (pacienteRegistrado != null) {
				pacienteRegistrado.setDomicilio(respuesta.getBeneficiarioTO()
						.getDireccion());
				pacienteRegistrado.setComuna(servicioComuna
						.buscarCodigo(Integer.valueOf(respuesta
								.getBeneficiarioTO().getCdgComuna())));
				pacienteRegistrado.setPrevision(respuesta.getAfiliadoTO()
						.getTramo().trim());
				pacienteRegistrado.setTelefono(respuesta.getBeneficiarioTO()
						.getTelefono());
			}
			String codigoBloqueo = respuesta.getCodcybl();
			if (codigoBloqueo.equals(Constantes.codigoBloqueadoAuditoria)
					|| codigoBloqueo
							.equals(Constantes.codigoBloqueadoCagaSinCotizaciones)
					|| codigoBloqueo
							.equals(Constantes.codigoBloqueadoCapredena)
					|| codigoBloqueo
							.equals(Constantes.codigoBloqueadoCargaAuditoria)
					|| codigoBloqueo
							.equals(Constantes.codigoBloqueadoCargaCapredena)
					|| codigoBloqueo
							.equals(Constantes.codigoBloqueadoCargaDipreca)
					|| codigoBloqueo
							.equals(Constantes.codigoBloqueadoCargaIsapre)
					|| codigoBloqueo.equals(Constantes.codigoBloqueadoDipreca)
					|| codigoBloqueo
							.equals(Constantes.codigoBloqueadoFallecido)
					|| codigoBloqueo.equals(Constantes.codigoBloqueadoIsapre)
					|| codigoBloqueo
							.equals(Constantes.codigoBloqueadoRutCargaFueraRango)
					|| codigoBloqueo
							.equals(Constantes.codigoBloqueadoRutTitularFueraRango)
					|| codigoBloqueo
							.equals(Constantes.codigoBloqueadoSinCotizaciones)
					|| codigoBloqueo
							.equals(Constantes.codigoBloqueadoSinInformacion)) {
				sinFonasa = true;
				divErrorRutPaciente.setVisible(true);
				lblErrorRutPaciente.setValue(respuesta.getCoddesc());
				lblErrorRutPaciente.setVisible(true);
				observacion = respuesta.getCoddesc();
			} else {
				sinFonasa = false;
				if (respuesta.getBeneficiarioTO().getCdgComuna()
						.equals(Constantes.codigoCerrillos)
						|| respuesta.getBeneficiarioTO().getCdgComuna()
								.equals(Constantes.codigoEstacionCentral)
						|| respuesta.getBeneficiarioTO().getCdgComuna()
								.equals(Constantes.codigoMaipu)
						|| respuesta.getBeneficiarioTO().getCdgComuna()
								.equals(Constantes.codigoPedroAguierreCerda)
						|| respuesta.getBeneficiarioTO().getCdgComuna()
								.equals(Constantes.codigoSantiago)) {
					perteneceRed = true;
				} else {
					divErrorRutPaciente.setVisible(true);
					lblErrorRutPaciente
							.setValue("El Paciente no pertenece a la Red");
					lblErrorRutPaciente.setVisible(true);
					observacion = "La comuna del paciente es: "
							+ respuesta.getBeneficiarioTO().getDesComuna();
				}
			}
		} else {
			inhabilitarRows();
			Messagebox.show(Constantes.mensajeRutNoExisteFonasa, "Error",
					Messagebox.OK, Messagebox.ERROR);
		}
	}

	public void habilitarRows() {
		row1Paciente.setVisible(true);
		row2Paciente.setVisible(true);
		row3Paciente.setVisible(true);
		row4Paciente.setVisible(true);
		row5Paciente.setVisible(true);
	}

	public void inhabilitarRows() {
		row1Paciente.setVisible(false);
		row2Paciente.setVisible(false);
		row3Paciente.setVisible(false);
		row4Paciente.setVisible(false);
		row5Paciente.setVisible(false);
		lblPrimerApellido.setValue("");
		lblSegundoApellido.setValue("");
		lblNombres.setValue("");
		lblFechaNacimiento.setValue("");
		lblGenero.setValue("");
		lblComuna.setValue("");
		lblDireccion.setValue("");
		lblPrevision.setValue("");
		lblTelefono.setValue("");
		perteneceRed = false;
		sinFonasa = false;

	}

	@Listen("onClick = #txtDiagnostico")
	public void modalDiagnostico() {
		final HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("textbox", txtDiagnostico);
		Sessions.getCurrent().setAttribute("itemsCatalogo", map);
		wdwDiagnostico = (Window) Executions.createComponents(
				"public/vistas/transacciones/modal-diagnostico.zul", null, map);
		wdwDiagnostico.doModal();
	}
}