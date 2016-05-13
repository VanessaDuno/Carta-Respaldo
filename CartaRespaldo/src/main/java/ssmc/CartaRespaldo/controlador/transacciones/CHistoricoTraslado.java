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
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zk.ui.util.Clients;
import org.zkoss.zul.Label;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Listcell;
import org.zkoss.zul.Listitem;
import org.zkoss.zul.Messagebox;

import ssmc.CartaRespaldo.componentes.Constantes;
import ssmc.CartaRespaldo.controlador.maestros.CGenerico;
import ssmc.CartaRespaldo.modelo.seguridad.Usuario;
import ssmc.CartaRespaldo.modelo.transacciones.Bitacora;
import ssmc.CartaRespaldo.modelo.transacciones.PrestacionSolicitud;
import ssmc.CartaRespaldo.modelo.transacciones.ResponsableSolicitud;
import ssmc.CartaRespaldo.modelo.transacciones.SolicitudTraslado;

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

	@Wire
	private Listbox lbxTraslado;
	private boolean rescate;
	private int contador;

	@Override
	public void inicializar() throws IOException {

		llenarLista();
		if (rescate) {
			Messagebox.show("Tiene" + " " + contador + " "
					+ "pacientes que debe rescatar.", "Advertencia",
					Messagebox.OK, Messagebox.EXCLAMATION);
		}
	}

	public void llenarLista() {
		historicoTraslado = servicioBitacora.buscarEstatus("Creada");
		lbxTraslado.setModel(new ListModelList<Bitacora>(historicoTraslado));
		semaforoLista();
	}

	public void semaforoLista() {
		lbxTraslado.renderAll();
		contador = 0;
		List<Listitem> listItem = lbxTraslado.getItems();
		if (listItem.size() != 0) {
			for (int i = 0; i < listItem.size(); i++) {
				Bitacora bitacora = listItem.get(i).getValue();
				String otraPrestacion= bitacora.getTraslado().getDescripcion(); 
				((Label) ((listItem.get(i).getChildren().get(4)))
						.getFirstChild()).setValue(consultarPrestacionesSolicitud(bitacora.getTraslado().getId()) + otraPrestacion);
				int dia = diferenciaEnDias(fechaHora, bitacora.getFecha());
				Listcell lc = (Listcell) listItem.get(i).getChildren().get(5);
				if (dia > 15) {
					lc.setStyle("background: rgba(220, 86, 86, 1)");
					lc.setClass("parpadea text");
					rescate = true;
					contador++;
				} else if (dia > 10 && dia <= 15) {
					lc.setStyle("background: rgba(249, 253, 86, 1)");
				} else if (dia <= 10) {
					lc.setStyle("background: rgba(86, 220, 97, 1)");
				}
			}
		}
	}

	public int diferenciaEnDias(Timestamp fechaMayor, Timestamp fechaMenor) {
		long diferenciaEn_ms = fechaMayor.getTime() - fechaMenor.getTime();
		long dias = diferenciaEn_ms / (1000 * 60 * 60 * 24);
		return (int) dias;
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

		p.put("funcionario0", "");
		p.put("funcionario1", "");
		p.put("funcionario2", "");
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
						+ prestacionesSolicitud.get(i).getPrestacion().getPrestacion()
								.getDescripcion()
						+ " "
						+ cortarCadena(prestacionesSolicitud.get(i).getPrestacion()
								.getNombre());
			} else {
				prestacionesPaciente = prestacionesPaciente
						+ ", "
						+ prestacionesSolicitud.get(i).getPrestacion().getPrestacion()
								.getDescripcion()
						+ " "
						+ cortarCadena(prestacionesSolicitud.get(i).getPrestacion()
								.getNombre());
			}
		}
		return prestacionesPaciente; 
	}
}