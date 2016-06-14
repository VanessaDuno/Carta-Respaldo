/**
 * 
 */
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

import org.json.JSONException;
import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zk.ui.util.Clients;
import org.zkoss.zul.Column;
import org.zkoss.zul.Combobox;
import org.zkoss.zul.Grid;
import org.zkoss.zul.Label;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Listcell;
import org.zkoss.zul.Listheader;
import org.zkoss.zul.Listitem;
import org.zkoss.zul.Textbox;

import ssmc.CartaRespaldo.componentes.Constantes;
import ssmc.CartaRespaldo.controlador.maestros.CGenerico;
import ssmc.CartaRespaldo.modelo.maestros.Establecimiento;
import ssmc.CartaRespaldo.modelo.seguridad.Usuario;
import ssmc.CartaRespaldo.modelo.transacciones.Bitacora;
import ssmc.CartaRespaldo.modelo.transacciones.PrestacionSolicitud;
import ssmc.CartaRespaldo.modelo.transacciones.ResponsableSolicitud;
import ssmc.CartaRespaldo.modelo.transacciones.SolicitudTraslado;
import ssmc.CartaRespaldo.preparedstatement.ConsultarPrestacionesSolicitud;

/**
 * @author Vanessa Maria Duno
 * 
 */
public class CHistoricoTraslado extends CGenerico {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
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
	Usuario usuario = new Usuario();
	ConsultarPrestacionesSolicitud cs = new ConsultarPrestacionesSolicitud();
	boolean isSsmc = false;

	@Override
	public void inicializar() throws IOException {
		usuario = usuarioActivo();
		llenarLista();
	}

	public void llenarLista() {
		if (usuario.getEstablecimiento().getId() == 4) {
			isSsmc = true;
			historicoTraslado = servicioBitacora.buscarTodos();
			lbxTraslado
					.setModel(new ListModelList<Bitacora>(historicoTraslado));
			lhdEstablecimientoOrigen.setVisible(true);
		} else {
			isSsmc = false;
			historicoTraslado = servicioBitacora
					.buscarPorEstablecimiento(usuario.getEstablecimiento()
							.getId());
			lbxTraslado
					.setModel(new ListModelList<Bitacora>(historicoTraslado));
		}
		establecimientosOrigen = servicioEstablecimiento
				.buscarEstablecimientosOrigen();
		cmbOrigen.setModel(new ListModelList<Establecimiento>(
				establecimientosOrigen));
		semaforoLista();

	}

	public void semaforoLista() {
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
				if (dia > 15) {
					lc.setStyle("background: rgba(220, 86, 86, 1); color:white");
					lc.setClass("parpadea text");
				} else if (dia > 10 && dia <= 15) {
					lc.setStyle("background: rgba(249, 253, 86, 1)");
					lc.setClass("text");
				} else if (dia <= 10) {
					lc.setStyle("background: rgba(86, 220, 97, 1)");
					lc.setClass("text");
				}
			}
		}
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
		columna7.setWidth("10%");
		columna5.setVisible(false);
		columna6.setVisible(false);
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
		columna5.setWidth("30%");
		columna6.setWidth("30%");
		columna2.setWidth("30%");
		columna7.setWidth("10%");
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
		p.put("diagnosticoPaciente", solicitudTraslado.getDiagnostico());
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

	@Listen("onClick = #lbxTraslado")
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
	}

	public void filtrarLista() {
		List<Bitacora> lista = new ArrayList<Bitacora>();
		for (Bitacora bitacora : historicoTraslado) {
			if (cmbMeses.getSelectedItem() != null){
			if (bitacora.getEstatus().contains(cmbEstatus.getValue())
					&& bitacora.getTraslado().getUnidad().getEstablecimiento()
							.getNombre().contains(cmbOrigen.getValue())
					&& Integer.valueOf(formatoFechaMeses.format(bitacora
							.getFecha())) == Integer.valueOf(cmbMeses
							.getSelectedItem().getContext())
					&& bitacora.getTraslado().getEstablecimiento().getNombre()
							.toLowerCase()
							.contains(txtDestino.getValue().toLowerCase())) {
				lista.add(bitacora);
			}
			}
			else{
				if (bitacora.getEstatus().contains(cmbEstatus.getValue())
						&& bitacora.getTraslado().getUnidad().getEstablecimiento()
								.getNombre().contains(cmbOrigen.getValue())
						&& bitacora.getTraslado().getEstablecimiento().getNombre()
								.toLowerCase()
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
			if (!txtFiltroId.getValue().equals("")){
			if (bitacora.getTraslado().getPaciente().getRut()
					.contains(formatearRut(txtFiltroRut.getValue()))
					&& bitacora.getTraslado().getId() == Integer
							.valueOf(txtFiltroId.getValue())
					&& bitacora.getTraslado().getEstablecimiento().getNombre()
							.toLowerCase()
							.contains(txtDestino.getValue().toLowerCase())) {
				lista.add(bitacora);
			}
			}
			else{
				if (bitacora.getTraslado().getPaciente().getRut()
						.contains(formatearRut(txtFiltroRut.getValue()))
						&& bitacora.getTraslado().getEstablecimiento().getNombre()
								.toLowerCase()
								.contains(txtDestino.getValue().toLowerCase())) {
					lista.add(bitacora);
			}
		}
		lbxTraslado.setModel(new ListModelList<Bitacora>(lista));
		lbxTraslado.renderAll();

		semaforoLista();
	}
	}
}
