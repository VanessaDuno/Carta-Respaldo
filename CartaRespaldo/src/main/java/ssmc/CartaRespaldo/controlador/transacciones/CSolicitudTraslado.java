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
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Window;

import ssmc.CartaRespaldo.componentes.Botonera;
import ssmc.CartaRespaldo.componentes.Catalogo;
import ssmc.CartaRespaldo.componentes.Constantes;
import ssmc.CartaRespaldo.controlador.maestros.CGenerico;
import ssmc.CartaRespaldo.modelo.maestros.CargosEstablecimiento;
import ssmc.CartaRespaldo.modelo.maestros.CategoriaPrestacion;
import ssmc.CartaRespaldo.modelo.maestros.Comuna;
import ssmc.CartaRespaldo.modelo.maestros.DetallePrestacion;
import ssmc.CartaRespaldo.modelo.maestros.Establecimiento;
import ssmc.CartaRespaldo.modelo.maestros.Motivo;
import ssmc.CartaRespaldo.modelo.maestros.Paciente;
import ssmc.CartaRespaldo.modelo.maestros.Prestacion;
import ssmc.CartaRespaldo.modelo.maestros.Provincia;
import ssmc.CartaRespaldo.modelo.maestros.Region;
import ssmc.CartaRespaldo.modelo.maestros.Responsable;
import ssmc.CartaRespaldo.modelo.maestros.ServicioClinico;
import ssmc.CartaRespaldo.modelo.maestros.Sexo;
import ssmc.CartaRespaldo.modelo.maestros.Unidad;
import ssmc.CartaRespaldo.modelo.seguridad.Usuario;
import ssmc.CartaRespaldo.modelo.transacciones.Bitacora;
import ssmc.CartaRespaldo.modelo.transacciones.PrestacionSolicitud;
import ssmc.CartaRespaldo.modelo.transacciones.ResponsableSolicitud;
import ssmc.CartaRespaldo.modelo.transacciones.SolicitudTraslado;

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
	private List<ServicioClinico> servicios = new ArrayList<ServicioClinico>();
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

	@Wire
	private Combobox cmbRegion;
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
	private Combobox cmbServicio;
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
	private Label lblResponsables;
	@Wire
	private Combobox cmbMotivoCama;
	private boolean rutPaciente = false;
	private boolean isConsulta = false;

	Botonera botonera;
	List<PrestacionSolicitud> prestacionesSolicitud = new ArrayList<PrestacionSolicitud>();

	Catalogo<DetallePrestacion> catalogo;

	@Override
	public void inicializar() throws IOException {
		llenarCombos();
		lblResponsables.setValue("Recuerde que debe seleccionar" + " "
				+ usuarioActivo.getEstablecimiento().getCantidadFirmantes() + " "
				+ "responsables de la Carta Respaldo.");
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
				try {
					generarReporteQuemado(17);
				} catch (JSONException e) {
					e.printStackTrace();
				}
			}

			@Override
			public void guardar() {
				if (validarCampos()) {
					prepararObjetosGuardar();
					Messagebox.show(Constantes.mensajeRegistroGuardado,
							"Información", Messagebox.OK,
							Messagebox.INFORMATION);
					try {
						generarReporteQuemado(solicitudGuardada.getId());
					} catch (JSONException e) {
						e.printStackTrace();
					}
					borrarDataSesion();
					limpiarCampos();
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

	public void borrarDataSesion() {
		Sessions.getCurrent().setAttribute("prestacionesSolicitud", null);
		Sessions.getCurrent().setAttribute("itemsCatalogo", null);
	}

	public void llenarCombos() {
		usuarioActivo = usuarioActivo();
		regiones = servicioRegion.buscarTodos();
		cmbRegion.setModel(new ListModelList<Region>(regiones));
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

	@Listen("onChange = #cmbRegion")
	public void cargarEstablecimientos() {
		if (!cmbRegion.getValue().equals("")) {
			int idRegion = Integer.valueOf(cmbRegion.getSelectedItem()
					.getContext());
			establecimientos = servicioEstablecimiento.buscarRegion(idRegion);
			cmbEstablecimiento.setModel(new ListModelList<Establecimiento>(
					establecimientos));
		}
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

	@Listen("onChange = #cmbUnidad")
	public void cargarServicios() {
		if (!cmbUnidad.getValue().equals("")) {
			int idUnidad = Integer.valueOf(cmbUnidad.getSelectedItem()
					.getContext());
			servicios = servicioServicioClinico.buscarPorUnidad(idUnidad);
			cmbServicio.setModel(new ListModelList<ServicioClinico>(servicios));
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
			} else {
				divErrorRutPaciente.setVisible(false);
				txtRutPaciente
						.setValue(formatearRut(txtRutPaciente.getValue()));
				log.debug(new StringBuilder().append("Valido rut:").append(
						txtRutPaciente.getValue()));
				rutPaciente = false;
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
			mostrarCatalogo();
			isConsulta = true;
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
		cmbRegion.setVisible(true);
		cmbEstablecimiento.setVisible(true);
		lblRegion.setVisible(true);
		lblEstablecimiento.setVisible(true);
		lblNombreLaboratorio.setVisible(false);
		txtLaboratorio.setVisible(false);
	}

	@Listen("onClick = #rdoLaboratorio")
	public void destinoLaboratorio() {
		lblNombreLaboratorio.setVisible(true);
		txtLaboratorio.setVisible(true);
		cmbRegion.setVisible(false);
		cmbEstablecimiento.setVisible(false);
		lblRegion.setVisible(false);
		lblEstablecimiento.setVisible(false);
	}

	public Paciente prepararPaciente() {
		Paciente pacienteGuardado = new Paciente();
		paciente.setRut(txtRutPaciente.getValue());
		paciente.setPrimerApellido(txtPrimerApellidoPaciente.getValue());
		paciente.setNombres(txtPrimerNombrePaciente.getValue() + " "
				+ txtSegundoNombrePaciente.getValue());
		paciente.setComuna((Comuna) cmbComuna.getSelectedItem().getValue());
		paciente.setDomicilio(txtDireccionPaciente.getValue());
		paciente.setPrevision(cmbPrevisionPaciente.getValue());
		paciente.setSexo((Sexo) cmbSexo.getSelectedItem().getValue());
		paciente.setSegundoApellido(txtSegundoApellidoPaciente.getValue());
		Date fecha = dtbFechaNacPaciente.getValue();
		Timestamp fechaNacimiento = new Timestamp(fecha.getTime());
		paciente.setFechaNacimiento(fechaNacimiento);
		paciente.setTelefono(txtTelefonoPaciente.getValue());
		pacienteGuardado = servicioPaciente.guardar(paciente);
		return pacienteGuardado;
	}

	public void prepararBitacora(SolicitudTraslado solicitudTraslado) {
		bitacora.setFecha(fechaHora);
		bitacora.setEstatus("Creada");
		bitacora.setTraslado(solicitudTraslado);
		bitacora.setUsuario(usuarioActivo);
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

	public void prepararObjetosGuardar() {
		solicitudTraslado.setPaciente(prepararPaciente());
		if (rdoHospital.isChecked()) {
			solicitudTraslado
					.setEstablecimiento((Establecimiento) cmbEstablecimiento
							.getSelectedItem().getValue());
		} else if (rdoLaboratorio.isChecked()) {
			Establecimiento establecimiento = new Establecimiento();
			Establecimiento establecimientoGuardado = new Establecimiento();
			establecimiento.setNombre(txtLaboratorio.getValue());
			establecimiento.setDestino(false);
			establecimientoGuardado = servicioEstablecimiento
					.guardarRetorno(establecimiento);
			solicitudTraslado.setEstablecimiento(establecimientoGuardado);
		}
		solicitudTraslado.setServicioClinico((ServicioClinico) cmbServicio
				.getSelectedItem().getValue());
		solicitudTraslado.setDiagnostico(txtDiagnostico.getValue());
		solicitudTraslado.setTipoDerivacion(cmbTipoDerivacion.getValue());
		solicitudTraslado.setFicha(txtFicha.getValue());
		solicitudTraslado.setFolio(rellenarCeros(servicioSolicitudTraslado
				.maxId() + 1));
		solicitudTraslado.setUnidad((Unidad) cmbUnidad.getSelectedItem()
				.getValue());
		solicitudTraslado.setDescripcion(txtDescripcionPrestacion.getValue());
		if (ckbGes.isChecked()) {
			solicitudTraslado.setGes(true);
		} else {
			solicitudTraslado.setGes(false);
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
		cmbRegion.setValue("");
		cmbRegionPaciente.setValue("");
		cmbServicio.setValue("");
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
		lblRegion.setVisible(false);
		cmbRegion.setVisible(false);
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
	}

	public boolean validarCampos() {

		if (txtDiagnostico.getValue().equals("")
				|| txtDireccionPaciente.getValue().equals("")
				|| txtPrimerApellidoPaciente.getValue().equals("")
				|| txtPrimerNombrePaciente.getValue().equals("")
				|| txtRutPaciente.getValue().equals("")
				|| txtSegundoApellidoPaciente.getValue().equals("")
				|| txtSegundoNombrePaciente.getValue().equals("")
				|| txtTelefonoPaciente.getValue().equals("")
				|| cmbComuna.getSelectedItem() == null
				|| cmbRegionPaciente.getSelectedItem() == null
				|| cmbPrevisionPaciente.getSelectedItem() == null
				|| cmbProvincia.getSelectedItem() == null
				|| cmbServicio.getSelectedItem() == null
				|| cmbSexo.getSelectedItem() == null
				|| cmbTipoDerivacion.getSelectedItem() == null
				|| cmbUnidad.getSelectedItem() == null
				|| dtbFechaNacPaciente.getValue().equals("")
				|| txtFicha.getValue().equals("")) {
			return false;
		} else {
			if (rdoHospital.isChecked()) {
				if (cmbRegion.getSelectedItem() == null
						|| cmbEstablecimiento.getSelectedItem() == null) {
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

	public String cortarCadena(String cadena) {
		int posicion = cadena.indexOf("(");
		if (posicion != -1) {
			cadena = cadena.substring(0, posicion);
		}
		return cadena;
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
				+ solicitudTraslado.getPaciente().getPrevision().toUpperCase());
		p.put("telefonoPaciente", solicitudTraslado.getPaciente().getTelefono());
		p.put("domicilioPaciente", solicitudTraslado.getPaciente()
				.getDomicilio());
		p.put("comunaPaciente", solicitudTraslado.getPaciente().getComuna()
				.getNombre());
		p.put("diagnosticoPaciente", solicitudTraslado.getDiagnostico());
		p.put("unidadDerivadora", solicitudTraslado.getUnidad().getNombre());
		p.put("rutEstablecimientoDeriva", usuarioActivo.getEstablecimiento()
				.getRut());
		p.put("direccionEstablecimientoDeriva", usuarioActivo
				.getEstablecimiento().getDireccion());
		p.put("funcionarioUno", responsables.get(0).getResponsable().getNombre());
		p.put("funcionarioDos", responsables.get(1).getResponsable().getNombre());
		p.put("funcionarioTres", responsables.get(2).getResponsable().getNombre());
		p.put("cargoUno", responsables.get(0).getResponsable().getCargo().getNombre());
		p.put("cargoDos", responsables.get(1).getResponsable().getCargo().getNombre());
		p.put("cargoTres", responsables.get(2).getResponsable().getCargo().getNombre());

		p.put("telefonoHospital", usuarioActivo.getEstablecimiento()
				.getTelefono());
		p.put("motivoTraslado", prestacionesPaciente);
		if (usuarioActivo.getEstablecimiento().getId() == 27) {
			p.put("logoEstablecimiento", "/ssmc/CartaRespaldo/reporte/huap.jpg");
			p.put("acronimoHospital", "HUAP");
		} else if (usuarioActivo.getEstablecimiento().getId() == 25) {
			p.put("logoEstablecimiento",
					"/ssmc/CartaRespaldo/reporte/sanborja.jpg");
			p.put("acronimoHospital", "HCSBA");
		} else if (usuarioActivo.getEstablecimiento().getId() == 26) {
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

	public int calcularEdad(Timestamp fecha) {
		log.info("Inicio de metodo calcularEdad()");
		Date fechaActual = new Date();
		SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
		String hoy = formato.format(fechaActual);
		String fechaNac = formato.format(fecha.getTime());
		String[] dat1 = fechaNac.split("/");
		String[] dat2 = hoy.split("/");
		int anos = Integer.parseInt(dat2[2]) - Integer.parseInt(dat1[2]);
		int mes = Integer.parseInt(dat2[1]) - Integer.parseInt(dat1[1]);
		if (mes < 0) {
			anos = anos - 1;
		} else if (mes == 0) {
			int dia = Integer.parseInt(dat2[0]) - Integer.parseInt(dat1[0]);
			if (dia > 0) {
				anos = anos - 1;
			}
		}
		return anos;
	}

	@Listen("onClick = #btnMedico")
	public void buscarMedicos() {
		medicosResponsables = servicioResponsable.buscarEstablecimientoCargo(
				usuarioActivo.getEstablecimiento().getId(), 1);
		final HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("medicos", medicosResponsables);
		map.put("isMedico", true); 
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
		Sessions.getCurrent().setAttribute("itemsCatalogo", map);
		wdwModalResponsables = (Window) Executions
				.createComponents(
						"public/vistas/transacciones/modal-responsables.zul",
						null, map);
		wdwModalResponsables.doModal();
	}
}