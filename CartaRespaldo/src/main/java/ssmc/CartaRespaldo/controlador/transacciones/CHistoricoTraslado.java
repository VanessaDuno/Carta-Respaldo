/**
 * 
 */
package ssmc.CartaRespaldo.controlador.transacciones;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.JasperRunManager;
import net.sf.jasperreports.engine.util.JRLoader;

import org.apache.log4j.Logger;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFCreationHelper;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.json.JSONException;
import org.zkoss.zhtml.Messagebox;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.Sessions;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zk.ui.util.Clients;
import org.zkoss.zul.Column;
import org.zkoss.zul.Combobox;
import org.zkoss.zul.Div;
import org.zkoss.zul.Filedownload;
import org.zkoss.zul.Grid;
import org.zkoss.zul.Label;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Listcell;
import org.zkoss.zul.Listhead;
import org.zkoss.zul.Listheader;
import org.zkoss.zul.Listitem;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Window;

import ssmc.CartaRespaldo.componentes.Constantes;
import ssmc.CartaRespaldo.controlador.maestros.CGenerico;
import ssmc.CartaRespaldo.enums.EnumEstadoSolicitud;
import ssmc.CartaRespaldo.modelo.maestros.DetallePrestacion;
import ssmc.CartaRespaldo.modelo.maestros.Establecimiento;
import ssmc.CartaRespaldo.modelo.seguridad.Usuario;
import ssmc.CartaRespaldo.modelo.transacciones.Bitacora;
import ssmc.CartaRespaldo.modelo.transacciones.PrestacionSolicitud;
import ssmc.CartaRespaldo.modelo.transacciones.ResponsableSolicitud;
import ssmc.CartaRespaldo.modelo.transacciones.SolicitudTraslado;
import ssmc.CartaRespaldo.preparedstatement.ConsultarPrestacionesSolicitud;

/**
 * CHistoricoTraslado
 * 
 * Controlador encargado de mostrar el historico de cartas de respaldo por
 * establecimiento y acciones en los registros
 * 
 * @author Vanessa Maria Duno
 * @version 1.0
 * 
 */
public class CHistoricoTraslado extends CGenerico {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final static Logger log = Logger
			.getLogger(ssmc.CartaRespaldo.controlador.transacciones.CHistoricoTraslado.class);
	private List<Bitacora> historicoTraslado = new ArrayList<Bitacora>();

	private List<Establecimiento> establecimientosOrigen = new ArrayList<>();
	List<Bitacora> lista = new ArrayList<Bitacora>();

	@Wire
	private Listbox lbxTraslado;
	@Wire
	private Textbox txtDestino;
	@Wire
	private Combobox cmbOrigen;
	@Wire
	private Combobox cmbEstatus;
	@Wire
	private Combobox cmbMeses;
	@Wire
	private Textbox txtFiltroRut;
	@Wire
	private Textbox txtFiltroId;
	@Wire
	private Listheader lhdEstablecimientoOrigen;
	@Wire
	private Grid gdFiltros;
	@Wire
	private Column columna1;
	@Wire
	private Column columna2;
	@Wire
	private Column columna3;
	@Wire
	private Column columna4;
	@Wire
	private Column columna5;
	@Wire
	private Column columna6;
	@Wire
	private Column columna7;
	@Wire
	private Column columna8;
	@Wire
	private Window wdwEstadoTraslado;
	@Wire
	private Window wdwModalRestricciones;
	@Wire
	private Combobox cmbTipoCama;
	Usuario usuario = new Usuario();
	ConsultarPrestacionesSolicitud cs = new ConsultarPrestacionesSolicitud();
	private List<DetallePrestacion> camas = new ArrayList<DetallePrestacion>();
	boolean isSsmc = false;

	@Override
	public void inicializar() throws IOException {
		log.info("Metodo inicializar ()");
		usuario = usuarioActivo();
		llenarLista();
		log.info("Fin del metodo inicializar()");
	}

	/**
	 * llenarLista: Metodo llena todas las lista mostradas en el historico de
	 * cartas de respaldo
	 * 
	 * @param No
	 *            Recibe ningun parametro
	 * @return No Retorna ningun dato ni objeto
	 * 
	 * @throws No
	 *             dispara ninguna excepción.
	 * 
	 */
	public void llenarLista() {
		log.info("Inicio del metodo llenarLista()");
		if (usuario.getEstablecimiento().getId() == 4) {
			log.info("Usuario es de SSMC");
			isSsmc = true;
			historicoTraslado = servicioBitacora.buscarEstado(true);
			lbxTraslado
					.setModel(new ListModelList<Bitacora>(historicoTraslado));
			lhdEstablecimientoOrigen.setVisible(true);
		} else {
			log.info("Usuario es de Establecimientos");
			isSsmc = false;
			historicoTraslado = servicioBitacora.buscarPorEstablecimiento(
					usuario.getEstablecimiento().getId(), true);
			lbxTraslado
					.setModel(new ListModelList<Bitacora>(historicoTraslado));
		}
		establecimientosOrigen = servicioEstablecimiento
				.buscarEstablecimientosOrigen();
		cmbOrigen.setModel(new ListModelList<Establecimiento>(
				establecimientosOrigen));
		camas = servicioDetallePrestacion.buscarPrestaciones(1);

		cmbTipoCama.setModel(new ListModelList<DetallePrestacion>(camas));
		ArrayList<String> listaEstatus = new ArrayList<String>();
		listaEstatus.add(EnumEstadoSolicitud.CREADA.getEstado());
		listaEstatus.add(EnumEstadoSolicitud.PORVALIDAR.getEstado());
		listaEstatus.add(EnumEstadoSolicitud.TRASLADO.getEstado());
		listaEstatus.add(EnumEstadoSolicitud.ANULADA.getEstado());
		listaEstatus.add(EnumEstadoSolicitud.CIERRECLINICO.getEstado());
		listaEstatus.add(EnumEstadoSolicitud.RECEPCIONCUENTA.getEstado());
		listaEstatus.add(EnumEstadoSolicitud.VISACION.getEstado());
		listaEstatus.add(EnumEstadoSolicitud.CIERREADMINISTRATIVO.getEstado());
		cmbEstatus.setModel(new ListModelList<String>(listaEstatus));
		semaforoLista();
		log.info("Fin del metodo llenarLista()");

	}

	/**
	 * semaforoLista: Metodo asigna los colores correspondientes a cada registro
	 * segun la cantidad de días calculados: Verde -> dias <= 7 Amarillo -> 7 <
	 * dias >= 21 Rojo -> dias > 21 Blanco -> Tipo de derivación paquete GRD
	 * 
	 * 
	 * @param No
	 *            Recibe ningun parametro
	 * @return No Retorna ningun dato ni objeto
	 * 
	 * @throws No
	 *             dispara ninguna excepción.
	 * 
	 */
	public void semaforoLista() {
		log.info("Inicio del metodo semaforoLista()");
		List<Listitem> listItem = lbxTraslado.getItems();

		if (listItem.size() != 0) {
			for (int i = 0; i < listItem.size(); i++) {
				lbxTraslado.renderItem(listItem.get(i));
				Bitacora bitacora = listItem.get(i).getValue();
				String otraPrestacion = bitacora.getTraslado().getDescripcion();
				((Label) ((listItem.get(i).getChildren().get(3)))
						.getFirstChild()).setValue(cs
						.consultarPrestaciones(bitacora.getTraslado().getId())
						+ " " + otraPrestacion);

				int dia = diferenciaEnDias(fechaHora, bitacora.getFecha());
				Label lc = ((Label) ((listItem.get(i).getChildren().get(6)))
						.getFirstChild());
				if (isSsmc) {
					Listcell lcc = (Listcell) listItem.get(i).getChildren()
							.get(1);
					lcc.setVisible(true);
					filtrosSsmc();
				} else {
					filtrosEstablecimientos();
				}
				String dias = "";
				if (dia < 10) {
					dias = "0" + dia;
				} else {
					dias = String.valueOf(dia);
				}
				((Label) ((listItem.get(i).getChildren().get(6)))
						.getFirstChild()).setValue(String.valueOf(dias));
				if (Integer.valueOf(dias) > 21) {
					lc.setStyle("background: rgba(220, 86, 86, 1); color:white");
					lc.setClass("parpadea text");
				} else if (Integer.valueOf(dias) > 7
						&& Integer.valueOf(dias) <= 21) {
					lc.setStyle("background: yellow;");
					lc.setClass("text");
				} else if (Integer.valueOf(dias) <= 7) {
					lc.setStyle("background: rgba(86, 220, 97, 1)");
					lc.setClass("text");
				}
				if (bitacora.getTraslado().getTipoDerivacion().toUpperCase()
						.contains("GRD")
						|| bitacora.getEstatus().equals(
								EnumEstadoSolicitud.CIERRECLINICO.getEstado())
						|| bitacora.getEstatus()
								.equals(EnumEstadoSolicitud.RECEPCIONCUENTA
										.getEstado())
						|| bitacora.getEstatus().equals(
								EnumEstadoSolicitud.VISACION.getEstado())
						|| bitacora.getEstatus().equals(
								EnumEstadoSolicitud.CIERREADMINISTRATIVO
										.getEstado())
						|| bitacora.getEstatus().equals(
								EnumEstadoSolicitud.ANULADA.getEstado())) {
					lc.setStyle("background: white");
					lc.setClass("text");
				}
				if (bitacora.getTraslado().getObservacion() == null) {
					Div div = ((Div) ((listItem.get(i).getChildren().get(7)))
							.getFirstChild());
					div.setVisible(false);
				}
			}
		}
		log.info("Fin del metodo semaforoLista() ");
	}

	public void filtrosSsmc() {
		txtDestino.setVisible(true);
		cmbMeses.setVisible(true);
		cmbOrigen.setVisible(true);
		cmbEstatus.setVisible(true);
		txtFiltroId.setVisible(false);
		txtFiltroRut.setVisible(false);
		columna1.setWidth("20%");
		columna2.setWidth("20%");
		columna3.setWidth("30%");
		columna4.setWidth("20%");
		columna8.setWidth("10%");
		columna5.setVisible(false);
		columna6.setVisible(false);
		columna7.setVisible(false);
	}

	public void filtrosEstablecimientos() {
		cmbMeses.setVisible(false);
		cmbOrigen.setVisible(false);
		cmbEstatus.setVisible(false);
		txtFiltroId.setVisible(true);
		txtFiltroRut.setVisible(true);
		columna1.setVisible(false);
		columna3.setVisible(false);
		columna4.setVisible(false);
		columna5.setWidth("23%");
		columna6.setWidth("23%");
		columna2.setWidth("22%");
		columna8.setWidth("10%");
		columna7.setWidth("22%");
	}

	public int diferenciaEnDias(Timestamp fechaMayor, Timestamp fechaMenor) {
		long diferenciaEn_ms = fechaMayor.getTime() - fechaMenor.getTime();
		long dias = diferenciaEn_ms / (1000 * 60 * 60 * 24);
		return (int) dias;
	}

	public void generarReporteQuemado(int id) throws JSONException {
		Clients.evalJavaScript("window.open('/CartaRespaldo/Reportero?valor2="
				+ id
				+ "','','top=100,left=200,height=600,width=800,scrollbars=1,resizable=1')");
	}

	/**
	 * reporteTraslado: Metodo que se encarga de generar PDF con la carta de
	 * respaldo
	 * 
	 * @param Recibe
	 *            el id de la carta
	 * @return Retorna un arreglo de byte[]
	 * 
	 * @throws Dispara
	 *             JRException, IOException
	 * 
	 */
	public byte[] reporteTraslado(String id) throws JRException, IOException {
		byte[] fichero = null;
		int idSolicitud = Integer.valueOf(id);
		SolicitudTraslado solicitudTraslado = getServicioSolicitudTraslado()
				.buscarSolicitud(idSolicitud);
		List<PrestacionSolicitud> prestaciones = getServicioPrestacionSolicitud()
				.prestacionesPorSolicitud(idSolicitud);
		List<ResponsableSolicitud> responsables = getServicioResponsableSolicitud()
				.responsablesSolicitud(idSolicitud);
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
		p.put("establecimientoOrigen", solicitudTraslado.getUnidad()
				.getEstablecimiento().getNombre());
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
		p.put("diagnosticoPaciente", solicitudTraslado.getDiagnostico()
				.getNombre());
		p.put("unidadDerivadora", solicitudTraslado.getUnidad().getNombre());
		p.put("rutEstablecimientoDeriva", solicitudTraslado.getUnidad()
				.getEstablecimiento().getRut());
		p.put("direccionEstablecimientoDeriva", solicitudTraslado.getUnidad()
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

		p.put("telefonoHospital", solicitudTraslado.getUnidad()
				.getEstablecimiento().getTelefono());
		p.put("motivoTraslado", prestacionesPaciente);
		if (solicitudTraslado.getUnidad().getEstablecimiento().getId() == 3) {
			p.put("logoEstablecimiento", "/ssmc/CartaRespaldo/reporte/huap.jpg");
			p.put("acronimoHospital", "HUAP");
		} else if (solicitudTraslado.getUnidad().getEstablecimiento().getId() == 1) {
			p.put("logoEstablecimiento",
					"/ssmc/CartaRespaldo/reporte/sanborja.jpg");
			p.put("acronimoHospital", "HCSBA");
		} else if (solicitudTraslado.getUnidad().getEstablecimiento().getId() == 2) {
			p.put("logoEstablecimiento", "/ssmc/CartaRespaldo/reporte/gec.jpg");
			p.put("acronimoHospital", "HEC");
		}
		p.put("logoMinsal", "/ssmc/CartaRespaldo/reporte/minsal.jpg");
		JasperReport reporte = (JasperReport) JRLoader.loadObject(getClass()
				.getResource(Constantes.rutaReporte));
		fichero = JasperRunManager.runReportToPdf(reporte, p);

		return fichero;
	}

	@Listen("onClick = #btnCarta")
	public void generarReporte() {
		try {
			Listitem listitem = lbxTraslado.getSelectedItem();
			if (listitem != null) {
				Bitacora bitacora = listitem.getValue();
				generarReporteQuemado(bitacora.getTraslado().getId());
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}

	public String consultarPrestacionesSolicitud(int idSolicitud) {
		List<PrestacionSolicitud> prestacionesSolicitud = servicioPrestacionSolicitud
				.prestacionesPorSolicitud(idSolicitud);
		String prestacionesPaciente = "";
		for (int i = 0; i < prestacionesSolicitud.size(); i++) {
			if (prestacionesPaciente.equals("")) {
				prestacionesPaciente = prestacionesPaciente
						+ prestacionesSolicitud.get(i).getPrestacion()
								.getPrestacion().getDescripcion()
						+ " "
						+ cortarCadena(prestacionesSolicitud.get(i)
								.getPrestacion().getNombre());
			} else {
				prestacionesPaciente = prestacionesPaciente
						+ ", "
						+ prestacionesSolicitud.get(i).getPrestacion()
								.getPrestacion().getDescripcion()
						+ " "
						+ cortarCadena(prestacionesSolicitud.get(i)
								.getPrestacion().getNombre());
			}
		}
		return prestacionesPaciente;
	}

	@Listen("onOK = #txtDestino")
	public void buscarEstablecimientoDestino() {
		if (isSsmc) {
			filtrarLista();
		} else {
			filtrarListaEstablecimiento();
		}
	}

	@Listen("onChange = #cmbMeses")
	public void filtrarPorMeses() {
		filtrarLista();
	}

	@Listen("onChange = #cmbOrigen")
	public void filtrarPorEstablecimientoOrigen() {
		filtrarLista();
	}

	@Listen("onChange = #cmbEstatus")
	public void filtrarPorEstatus() {
		filtrarLista();
	}

	@Listen("onChange = #cmbTipoCama")
	public void filtrarPorTipoCama() {
		filtrarListaEstablecimiento();
	}

	@Listen("onOK = #txtFiltroId")
	public void filtrarPorId() {
		filtrarListaEstablecimiento();
	}

	@Listen("onOK = #txtFiltroRut")
	public void filtrarPorRut() {
		filtrarListaEstablecimiento();
	}

	@Listen("onClick = #btnLimpiar")
	public void limpiarFiltros() {
		llenarLista();
		cmbOrigen.setValue("");
		txtDestino.setValue("");
		cmbEstatus.setValue("");
		cmbMeses.setValue("");
		txtFiltroRut.setValue("");
		txtFiltroId.setValue("");
		cmbTipoCama.setValue("");
	}

	public void filtrarLista() {
		List<Bitacora> lista = new ArrayList<Bitacora>();
		for (Bitacora bitacora : historicoTraslado) {
			if (cmbMeses.getSelectedItem() != null) {
				if (bitacora.getEstatus().contains(cmbEstatus.getValue())
						&& bitacora.getTraslado().getUnidad()
								.getEstablecimiento().getNombre()
								.contains(cmbOrigen.getValue())
						&& Integer.valueOf(formatoFechaMeses.format(bitacora
								.getFecha())) == Integer.valueOf(cmbMeses
								.getSelectedItem().getContext())
						&& bitacora.getTraslado().getEstablecimiento()
								.getNombre().toLowerCase()
								.contains(txtDestino.getValue().toLowerCase())) {
					lista.add(bitacora);
				}
			} else {
				if (bitacora.getEstatus().contains(cmbEstatus.getValue())
						&& bitacora.getTraslado().getUnidad()
								.getEstablecimiento().getNombre()
								.contains(cmbOrigen.getValue())
						&& bitacora.getTraslado().getEstablecimiento()
								.getNombre().toLowerCase()
								.contains(txtDestino.getValue().toLowerCase())) {
					lista.add(bitacora);
				}
			}
		}
		lbxTraslado.setModel(new ListModelList<Bitacora>(lista));
		lbxTraslado.renderAll();

		semaforoLista();
	}

	public void filtrarListaEstablecimiento() {
		List<Bitacora> lista = new ArrayList<Bitacora>();
		for (Bitacora bitacora : historicoTraslado) {
			if (!txtFiltroId.getValue().equals("")) {
				if (bitacora.getTraslado().getPaciente().getRut()
						.contains(formatearRut(txtFiltroRut.getValue()))
						&& bitacora.getTraslado().getId() == Integer
								.valueOf(txtFiltroId.getValue())
						&& bitacora.getTraslado().getEstablecimiento()
								.getNombre().toLowerCase()
								.contains(txtDestino.getValue().toLowerCase())
						&& cs.consultarPrestaciones(
								bitacora.getTraslado().getId()).contains(
								cmbTipoCama.getValue())) {
					lista.add(bitacora);
				}
			} else {
				if (bitacora.getTraslado().getPaciente().getRut()
						.contains(formatearRut(txtFiltroRut.getValue()))
						&& bitacora.getTraslado().getEstablecimiento()
								.getNombre().toLowerCase()
								.contains(txtDestino.getValue().toLowerCase())
						&& cs.consultarPrestaciones(
								bitacora.getTraslado().getId()).contains(
								cmbTipoCama.getValue())) {
					lista.add(bitacora);
				}
			}
			lbxTraslado.setModel(new ListModelList<Bitacora>(lista));
			lbxTraslado.renderAll();

			semaforoLista();
		}
	}

	@Listen("onClick = #btnCambiarEstado")
	public void cambiarEstado() {
		if (lbxTraslado.getSelectedItem() != null) {
			Bitacora bitacora = lbxTraslado.getSelectedItem().getValue();
			if (bitacora != null) {
				final HashMap<String, Object> map = new HashMap<String, Object>();
				map.put("bitacora", bitacora);
				Label label = (Label) lbxTraslado.getSelectedItem()
						.getChildren().get(3).getFirstChild();
				map.put("motivo", label.getValue());
				Sessions.getCurrent().setAttribute("itemsCatalogo", map);
				wdwEstadoTraslado = (Window) Executions.createComponents(
						"public/vistas/transacciones/estados-traslado.zul",
						null, map);
				wdwEstadoTraslado.doModal();
			}
		} else {
			Messagebox.show(Constantes.mensajeSeleccionarRegistro, "Alerta",
					Messagebox.OK, Messagebox.EXCLAMATION);
		}
	}

	@Listen("onDoubleClick= #lbxTraslado")
	public void verModalRestriccion() {
		if (lbxTraslado.getSelectedItem() != null) {
			Bitacora bitacora = lbxTraslado.getSelectedItem().getValue();
			if (bitacora.getTraslado().getObservacion() != null) {
				final HashMap<String, Object> map = new HashMap<String, Object>();
				map.put("bitacora", bitacora);

				Sessions.getCurrent().setAttribute("itemsCatalogo", map);
				wdwModalRestricciones = (Window) Executions.createComponents(
						"public/vistas/transacciones/modal-restricciones.zul",
						null, map);

				wdwModalRestricciones.doModal();
			}
		}
	}

	public void exportar() {
		if (lbxTraslado.getItemCount() != 0) {
			String s = ";";
			final StringBuffer sb = new StringBuffer();

			for (Object head : lbxTraslado.getHeads()) {
				String h = "";
				if (head instanceof Listhead) {
					for (Object header : ((Listhead) head).getChildren()) {
						h += ((Listheader) header).getLabel() + s;
					}
					sb.append(h + "\n");
				}
			}
			for (Object item : lbxTraslado.getItems()) {
				String i = "";
				for (Object cell : ((Listitem) item).getChildren()) {
					i += ((Listcell) cell).getLabel() + s;
				}
				sb.append(i + "\n");
			}
			Messagebox.show("Exportar", "Alerta", Messagebox.OK
					| Messagebox.CANCEL, Messagebox.QUESTION,
					new org.zkoss.zk.ui.event.EventListener<Event>() {
						public void onEvent(Event evt)
								throws InterruptedException {
							if (evt.getName().equals("onOK")) {
								Filedownload.save(sb.toString().getBytes(),
										"text/plain", "datos.csv");
							}
						}
					});
		} else {
		}
	}

//	@Listen("onClick = #btnExportar")
	public void generarArchivo() throws IOException {
		lbxTraslado.renderAll();
		List<Listitem> listItem = lbxTraslado.getItems();
		if (listItem.size() != 0) {
			Calendar c1 = Calendar.getInstance();
			int mes = c1.get(Calendar.MONTH) + 1;
			int dia = c1.get(Calendar.DATE);
			String fecha = (((dia < 10) ? "0" : "") + dia) + "-"
					+ (((mes < 10) ? "0" : "") + mes) + "-"
					+ c1.get(Calendar.YEAR);

			XSSFWorkbook wb = new XSSFWorkbook();
			XSSFSheet sheet = wb.createSheet(fecha);

			XSSFCreationHelper createHelper = wb.getCreationHelper();
			XSSFCellStyle cellStyle = wb.createCellStyle();
			cellStyle.setDataFormat(createHelper.createDataFormat().getFormat(
					"dd/MM/yyyy hh:mm:ss"));

			XSSFRow headerRow = sheet.createRow(0);
			headerRow.createCell(0).setCellValue("#");
			headerRow.createCell(1).setCellValue("FECHA");
			headerRow.createCell(2).setCellValue("ESTABLECIMIENTO DESTINO");
			headerRow.createCell(3).setCellValue("ESTABLECIMIENTO ORIGEN");
			headerRow.createCell(4).setCellValue("MOTIVO");
			headerRow.createCell(5).setCellValue("CODIGO DIAGNOSTICO");
			headerRow.createCell(6).setCellValue("DESCRIPCIÓN DIAGNOSTICO");
			headerRow.createCell(7).setCellValue("ESTADO");
			headerRow.createCell(8).setCellValue("OBSERVACIÓN");
			headerRow.createCell(9).setCellValue("RESTRICCIÓN");

			for (int i = 0; i < listItem.size(); i++) {
				Bitacora bitacora = listItem.get(i).getValue();

				XSSFRow row = sheet.createRow(i + 1);
				row.createCell(0).setCellValue(i + 1);
				row.createCell(1).setCellValue(bitacora.getFecha());
				sheet.getRow(i + 1).getCell(1).setCellStyle(cellStyle);
				row.createCell(2)
						.setCellValue(
								bitacora.getTraslado().getEstablecimiento()
										.getNombre());
				row.createCell(3).setCellValue(
						bitacora.getTraslado().getUnidad().getEstablecimiento()
								.getNombre());
				row.createCell(4)
						.setCellValue(
								cs.consultarPrestaciones(bitacora.getTraslado()
										.getId()));
				String cadena = bitacora.getTraslado().getDiagnostico()
						.getNombre();
				String codigo = "";
				int posicion = cadena.indexOf("-");
				if (posicion != -1) {
					codigo = cadena.substring(0, posicion);
					cadena = cadena.substring(posicion + 1, cadena.length());
				}
				row.createCell(5).setCellValue(codigo);
				row.createCell(6).setCellValue(cadena);
				row.createCell(7).setCellValue(bitacora.getEstatus());
				row.createCell(8).setCellValue(
						bitacora.getTraslado().getObservacion());
				row.createCell(9).setCellValue(
						bitacora.getTraslado().getObservacionRestriccion());

			}

			sheet.autoSizeColumn(0);
			sheet.setColumnWidth(1, 5000);
			sheet.autoSizeColumn(2);
			sheet.autoSizeColumn(3);
			sheet.autoSizeColumn(4);
			sheet.autoSizeColumn(5);
			sheet.autoSizeColumn(6);
			sheet.autoSizeColumn(7);
			sheet.autoSizeColumn(8);

			File ff = new File("Historico" + fecha + ".xlsx");
			FileOutputStream foss = new FileOutputStream(ff);
			wb.write(foss);

			Filedownload.save(ff, null);
		} else {
			Messagebox.show("No existen datos actualizados", "carta respaldo",
					Messagebox.OK, Messagebox.INFORMATION);
		}

	}

	@Listen("onClick = #btnExportar")
	public void generarArchivoExcel() throws IOException {
		lbxTraslado.renderAll();
		List<Listitem> listItem = lbxTraslado.getItems();
		if (listItem.size() != 0) {
			Calendar c1 = Calendar.getInstance();
			int mes = c1.get(Calendar.MONTH) + 1;
			int dia = c1.get(Calendar.DATE);
			String fecha = (((dia < 10) ? "0" : "") + dia) + "-"
					+ (((mes < 10) ? "0" : "") + mes) + "-"
					+ c1.get(Calendar.YEAR);

			XSSFWorkbook wb = new XSSFWorkbook();
			XSSFSheet sheet = wb.createSheet(fecha);

			XSSFCreationHelper createHelper = wb.getCreationHelper();
			XSSFCellStyle cellStyle = wb.createCellStyle();
			cellStyle.setDataFormat(createHelper.createDataFormat().getFormat(
					"dd/MM/yyyy hh:mm:ss"));

			XSSFRow headerRow = sheet.createRow(0);
			headerRow.createCell(0).setCellValue("Nº");
			headerRow.createCell(1).setCellValue("ESTABLECIMIENTO");
			headerRow.createCell(2).setCellValue("FECHA INGRESO EST.");
			headerRow.createCell(3).setCellValue("FECHA INGRESO SSMC");
			headerRow.createCell(4).setCellValue("FECHA RECEPCION UGCF");
			headerRow.createCell(5).setCellValue("PRESTADOR");
			headerRow.createCell(6).setCellValue("NOMBRE PACIENTE");
			headerRow.createCell(7).setCellValue("RUT");
			headerRow.createCell(8).setCellValue("DV");
			headerRow.createCell(9).setCellValue("GENERO");
			headerRow.createCell(10).setCellValue("TRAMO FONASA");
			headerRow.createCell(11).setCellValue("COMUNA");
			headerRow.createCell(12).setCellValue("H DERIVADOR");
			headerRow.createCell(13).setCellValue("FECHA NACIMIENTO");
			headerRow.createCell(14).setCellValue("EDAD");
			headerRow.createCell(15).setCellValue("TRAMOS DE EDAD");
			headerRow.createCell(16).setCellValue("Nº CARTA RESPALDO");
			headerRow.createCell(17).setCellValue("ID DERIVACION");
			headerRow.createCell(18).setCellValue("(1) TIPO CAMA");
			headerRow.createCell(19).setCellValue("(2) PRESTACION");
			headerRow.createCell(20).setCellValue("(3) DETALLE");
			headerRow.createCell(21).setCellValue("FECHA INGRESO");
			headerRow.createCell(22).setCellValue("FECHA ALTA");
			headerRow.createCell(23).setCellValue("(4) DIAGNOSTICO");
			headerRow.createCell(24).setCellValue("GRUPO DIAGNOSTICO");
			headerRow.createCell(25).setCellValue("(4) MOTIVO TRASLADO");
			headerRow.createCell(26).setCellValue("(5) TIPO DE DERIVACION");
			headerRow.createCell(27).setCellValue("Nº CUENTA");
			headerRow.createCell(28).setCellValue("Nº FACTURA");
			headerRow.createCell(29).setCellValue("Nº DIAS CAMA");
			headerRow.createCell(30).setCellValue("VALOR DIA CAMA AGUDA");
			headerRow.createCell(31).setCellValue("Nº DIAS CAMA");
			headerRow.createCell(32).setCellValue("VALOR DIA CAMA UTI");
			headerRow.createCell(33).setCellValue("Nº DIAS CAMA");
			headerRow.createCell(34).setCellValue("VALOR DIA CAMA UCI");
			headerRow.createCell(35).setCellValue("TOTAL DIAS CAMA");
			headerRow.createCell(36).setCellValue("MONTO TOTAL DIAS CAMA");
			headerRow.createCell(37).setCellValue(
					"MONTO PRESTACIONES  ADICIONALES");
			headerRow.createCell(38).setCellValue("TOTAL COBRADO");
			headerRow.createCell(39).setCellValue("MONTO DESCUENTO");
			headerRow.createCell(40).setCellValue("MONTO A PAGO");
			headerRow.createCell(41).setCellValue("OBSERVACIONES");
			headerRow.createCell(42).setCellValue("MEMO VISA");
			headerRow.createCell(43).setCellValue("FECHA");
			headerRow.createCell(44)
					.setCellValue("TIEMPO DEMORA UGCF-VISACION");
			headerRow.createCell(45).setCellValue(
					"TIEMPO DEMORA ESTABLECIMIENTO-SERVICIO");
			headerRow.createCell(46)
					.setCellValue("TIEMPO DEMORA SERVICIO-UGCF");
			headerRow.createCell(47).setCellValue("NOMINA SISDOC");
			headerRow.createCell(48).setCellValue("FECHA");
			headerRow.createCell(49).setCellValue(
					"FECHA RECEPCION OF. DE PARTES");

			
			for (int i = 0; i < listItem.size(); i++) {
				Bitacora bitacora = listItem.get(i).getValue();

				
				Bitacora recepcionCuenta = servicioBitacora
						.buscarTrasladooEstado(bitacora.getTraslado().getId(),
								EnumEstadoSolicitud.RECEPCIONCUENTA.getEstado());
				Bitacora visacion = servicioBitacora.buscarTrasladooEstado(
						bitacora.getTraslado().getId(),
						EnumEstadoSolicitud.VISACION.getEstado());
				
				XSSFRow row = sheet.createRow(i + 1);
				row.createCell(0).setCellValue(i + 1);
				row.createCell(1).setCellValue(bitacora.getUsuario().getEstablecimiento().getNombre());
				
				if (recepcionCuenta != null){
					row.createCell(2)
					.setCellValue(
							formatoFecha.format(recepcionCuenta
									.getFechaRecepcion()));
					row.createCell(21).setCellValue(formatoFecha.format(recepcionCuenta.getFechaIngresoPaciente()));
					row.createCell(22).setCellValue(formatoFecha.format(recepcionCuenta.getFechaEgresoPaciente()));
					row.createCell(27).setCellValue(recepcionCuenta.getCuenta());
					row.createCell(28).setCellValue(recepcionCuenta.getNumeroFactura());
					row.createCell(38).setCellValue(recepcionCuenta.getMontoCobrado());
				}
				else{
					row.createCell(2).setCellValue("");
					row.createCell(21).setCellValue("");
					row.createCell(22).setCellValue("");
					row.createCell(27).setCellValue("");
					row.createCell(28).setCellValue("");
					row.createCell(38).setCellValue("");
				}
				
				if (visacion != null){
					row.createCell(4).setCellValue(
							formatoFecha.format(visacion.getFechaIngresoCuenta()));
					row.createCell(3).setCellValue(
							formatoFecha.format(visacion.getFechaIngresoSsmc()));
					row.createCell(37).setCellValue(visacion.getPrestacionesAdicionales());
					row.createCell(39).setCellValue(visacion.getMontoDescuento());
					row.createCell(40).setCellValue(visacion.getMontoPago());
					row.createCell(41).setCellValue(visacion.getObservacionVisacion());
					row.createCell(29).setCellValue(visacion.getTotalDiasCamaBasica());
					row.createCell(31).setCellValue(visacion.getTotalDiasCamaUti());
					row.createCell(33).setCellValue(visacion.getTotalDiasCamaUci());
					row.createCell(35).setCellValue(visacion.getTotalDiasCamaBasica()+visacion.getTotalDiasCamaUti()+visacion.getTotalDiasCamaUci());
					row.createCell(45).setCellValue((visacion.getFechaIngresoSsmc().getTime() - recepcionCuenta.getFechaRecepcion().getTime()) / (1000 * 60 * 60 * 24));
					row.createCell(46).setCellValue((visacion.getFechaIngresoSsmc().getTime()  - visacion.getFechaIngresoCuenta().getTime()) / (1000 * 60 * 60 * 24));
					if (visacion.isDocumentacionVisacion()){
						row.createCell(43).setCellValue(formatoFecha.format(visacion.getFechaMemo()));
						row.createCell(44).setCellValue((visacion.getFechaMemo().getTime()  - visacion.getFechaIngresoCuenta().getTime()) / (1000 * 60 * 60 * 24));	
						row.createCell(47).setCellValue(visacion.getNumeroSisdoc());
						row.createCell(48).setCellValue(formatoFecha.format(visacion.getFechaNominaSisdoc()));	
						row.createCell(49).setCellValue(formatoFecha.format(visacion.getFechaRecepcionMemo()));
					}
					else{
						row.createCell(43).setCellValue("");
						row.createCell(44).setCellValue("");	
						row.createCell(47).setCellValue("");
						row.createCell(48).setCellValue("");	
						row.createCell(49).setCellValue("");
					}
				}
				else{
					row.createCell(3).setCellValue("");
					row.createCell(4).setCellValue("");
					row.createCell(37).setCellValue("");
					row.createCell(39).setCellValue("");
					row.createCell(40).setCellValue("");
					row.createCell(41).setCellValue("");
					row.createCell(45).setCellValue("");
					row.createCell(46).setCellValue("");
				}		
				row.createCell(5)
						.setCellValue(
								bitacora.getTraslado().getEstablecimiento()
										.getNombre());
				row.createCell(6).setCellValue(
						bitacora.getTraslado().getPaciente().getNombres()
								+ " "
								+ bitacora.getTraslado().getPaciente()
										.getPrimerApellido()
								+ " "
								+ bitacora.getTraslado().getPaciente()
										.getSegundoApellido());
				
				String rut = bitacora.getTraslado().getPaciente().getRut();
				String dv = "";
				int posi = rut.indexOf("-");
				if (posi != -1) {
					dv = rut.substring(0, posi);
					rut = rut.substring(posi + 1, rut.length());
				}
				row.createCell(7).setCellValue(dv);
				row.createCell(8).setCellValue(rut);
				row.createCell(9).setCellValue(bitacora.getTraslado().getPaciente().getSexo());
				row.createCell(10).setCellValue(bitacora.getTraslado().getPaciente().getPrevision());
				row.createCell(11).setCellValue(bitacora.getTraslado().getPaciente().getComuna().getNombre());
				row.createCell(12).setCellValue(bitacora.getTraslado().getUnidad().getEstablecimiento().getNombre());
				row.createCell(13).setCellValue(formatoFecha.format(bitacora.getTraslado().getPaciente().getFechaNacimiento()));
				int edad= calcularEdad(bitacora.getTraslado().getPaciente().getFechaNacimiento());
				row.createCell(14).setCellValue(edad);

				if (edad < 15){
					row.createCell(15).setCellValue(Constantes.tramo0a14);
				}
				else if (edad >= 15 && edad < 60){
					row.createCell(15).setCellValue(Constantes.tramo15a60);
				}
				else if (edad >= 60){
					row.createCell(15).setCellValue(Constantes.tramo60oMas);
				}
				row.createCell(16).setCellValue(bitacora.getTraslado().getId());
				row.createCell(17).setCellValue(bitacora.getTraslado().getIdUgcc());
				
			

				List<PrestacionSolicitud> prestacionesSolicitud = new ArrayList<PrestacionSolicitud>();
				prestacionesSolicitud = servicioPrestacionSolicitud.prestacionesPorSolicitud(bitacora.getTraslado().getId());
				String motivo = ""; 
				String cama = "";
				String detalle="";
				String prestacion= ""; 
				
				for (int j = 0; j < prestacionesSolicitud.size(); j++) {
					motivo = motivo +" " + prestacionesSolicitud.get(j).getMotivo().getNombre();
					 
					if (prestacionesSolicitud.get(j).getTipoCama() != null){
						cama = cama + " " +prestacionesSolicitud.get(j).getPrestacion().getNombre(); 
					}
					else{
						detalle = detalle + " " + prestacionesSolicitud.get(j).getPrestacion().getNombre();
						prestacion = prestacion + " " +prestacionesSolicitud.get(j).getPrestacion().getPrestacion().getDescripcion(); 
					}
				}
				
			
				row.createCell(18).setCellValue(cama);
				row.createCell(19).setCellValue(prestacion);
				row.createCell(20).setCellValue(detalle);
				
				String diagnostico = bitacora.getTraslado().getDiagnostico()
						.getNombre();
				String codigo = "";
				int posicion = diagnostico.indexOf("-");
				if (posicion != -1) {
					codigo = diagnostico.substring(0, posicion);
					diagnostico = diagnostico.substring(posicion + 1, diagnostico.length());
				}
				row.createCell(23).setCellValue(diagnostico);
				row.createCell(24).setCellValue(codigo);

				row.createCell(25).setCellValue(motivo);
				row.createCell(26).setCellValue(bitacora.getTraslado().getTipoDerivacion());

			
				//VALOR DIA CAMA GUDA
				row.createCell(30).setCellValue("");

				//VALOR DIA CAMA UTI
				row.createCell(32).setCellValue("");

				//VALOR DIA CAMA UCI
				row.createCell(34).setCellValue("");

				//MONTO TOTAL DIAS CAMA
				row.createCell(36).setCellValue("");
				
				//Memo visa
				row.createCell(42).setCellValue("");
					
			}

			sheet.autoSizeColumn(0);
			sheet.autoSizeColumn(1);
			sheet.autoSizeColumn(2);
			sheet.autoSizeColumn(3);
			sheet.autoSizeColumn(4);
			sheet.autoSizeColumn(5);
			sheet.autoSizeColumn(6);
			sheet.autoSizeColumn(7);
			sheet.autoSizeColumn(8);
			sheet.autoSizeColumn(9);
			sheet.autoSizeColumn(10);
			sheet.autoSizeColumn(11);
			sheet.autoSizeColumn(12);
			sheet.autoSizeColumn(13);
			sheet.autoSizeColumn(14);
			sheet.autoSizeColumn(15);
			sheet.autoSizeColumn(16);
			sheet.autoSizeColumn(17);
			sheet.autoSizeColumn(18);
			sheet.autoSizeColumn(19);
			sheet.autoSizeColumn(20);
			sheet.autoSizeColumn(21);
			sheet.autoSizeColumn(22);
			sheet.autoSizeColumn(23);
			sheet.autoSizeColumn(24);
			sheet.autoSizeColumn(25);
			sheet.autoSizeColumn(26);
			sheet.autoSizeColumn(27);
			sheet.autoSizeColumn(28);
			sheet.autoSizeColumn(29);
			sheet.autoSizeColumn(30);
			sheet.autoSizeColumn(31);
			sheet.autoSizeColumn(32);
			sheet.autoSizeColumn(33);
			sheet.autoSizeColumn(34);
			sheet.autoSizeColumn(35);
			sheet.autoSizeColumn(36);
			sheet.autoSizeColumn(37);
			sheet.autoSizeColumn(38);
			sheet.autoSizeColumn(39);
			sheet.autoSizeColumn(40);
			sheet.autoSizeColumn(41);
			sheet.autoSizeColumn(42);
			sheet.autoSizeColumn(43);
			sheet.autoSizeColumn(44);
			sheet.autoSizeColumn(45);
			sheet.autoSizeColumn(46);
			sheet.autoSizeColumn(47);
			sheet.autoSizeColumn(48);
			sheet.autoSizeColumn(49);

			File ff = new File("Historico" + fecha + ".xlsx");
			FileOutputStream foss = new FileOutputStream(ff);
			wb.write(foss);

			Filedownload.save(ff, null);
		} else {
			Messagebox.show("No existen datos actualizados", "carta respaldo",
					Messagebox.OK, Messagebox.INFORMATION);
		}

	}
}
