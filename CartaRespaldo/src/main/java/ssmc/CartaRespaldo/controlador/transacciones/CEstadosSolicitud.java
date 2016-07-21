/**
 * 
 */
package ssmc.CartaRespaldo.controlador.transacciones;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;

import javax.imageio.ImageIO;

import org.apache.log4j.Logger;
import org.zkoss.util.media.Media;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.Sessions;
import org.zkoss.zk.ui.event.UploadEvent;
import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zul.Button;
import org.zkoss.zul.Checkbox;
import org.zkoss.zul.Div;
import org.zkoss.zul.Iframe;
import org.zkoss.zul.Image;
import org.zkoss.zul.Label;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Row;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Window;

import ssmc.CartaRespaldo.componentes.Constantes;
import ssmc.CartaRespaldo.componentes.Validador;
import ssmc.CartaRespaldo.controlador.maestros.CGenerico;
import ssmc.CartaRespaldo.enums.EnumEstadoSolicitud;
import ssmc.CartaRespaldo.modelo.transacciones.Bitacora;

/**
 * CEstadosSolicitud
 * 
 * Controlador encargado de realizar los cambios de estado de un traslado
 * 
 * @author Vanessa Maria Duno
 * @version 1.0
 * 
 */
public class CEstadosSolicitud extends CGenerico {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final static Logger log = Logger
			.getLogger(ssmc.CartaRespaldo.controlador.transacciones.CEstadosSolicitud.class);

	private Bitacora bitacora = new Bitacora();
	@Wire
	private Label lblPaciente;
	@Wire
	private Label lblRut;
	@Wire
	private Label lblMotivo;
	@Wire
	private Label lblDiagnostico;
	@Wire
	private Label lblPrimerEstado;
	@Wire
	private Label lblSegundoEstado;
	@Wire
	private Label lblTercerEstado;
	@Wire
	private Label lblCuartoEstado;
	@Wire
	private Label lblFechaPrimerEstado;
	@Wire
	private Label lblFechaSegundoEstado;
	@Wire
	private Label lblFechaTercerEstado;
	@Wire
	private Label lblFechaCuartoEstado;
	@Wire
	private Button btnAceptar;
	@Wire
	private Button btnRechazar;
	@Wire
	private Button btnCancelar;
	@Wire
	private Image imgEstado;
	@Wire
	private Div divPrimerEstado;
	@Wire
	private Div divSegundoEstado;
	@Wire
	private Div divTercerEstado;
	@Wire
	private Div divCuartoEstado;
	@Wire
	private Image imagen;
	@Wire
	private Window wdwEstadoTraslado;
	@Wire
	private Row rowAdjuntar;
	@Wire
	private Div divAdjuntar;
	@Wire
	private Checkbox ckbxValidar;
	@Wire
	private Textbox txtObservacionCierreClinico;
	@Wire
	private Row rowCierreClinico;
	private Media media;
	@Wire
	private Div divQuintoEstado;
	@Wire
	private Label lblQuintoEstado;
	@Wire
	private Label lblFechaQuintoEstado;
	@Wire
	private Row rowCierreAdministrativo;
	@Wire
	private Textbox txtRecepcionCuenta;
	@Wire
	private Textbox txtEpicrisis;
	@Wire
	private Row rowCierreClinicoObs;
	@Wire
	private Row rowCierreAdministrativoPdf;
	@Wire
	private Iframe ifmPdf;
	@Wire
	private Div divCierreAdministrativo;
	@Wire
	private Label lblCierreAdministrativo;
	@Wire
	private Row rowAnulacion;
	@Wire
	private Textbox txtMotivoAnulacion;

	@Override
	public void inicializar() throws IOException {
		log.info("Metodo inicializar ()");
		HashMap<String, Object> map = (HashMap<String, Object>) Sessions
				.getCurrent().getAttribute("itemsCatalogo");
		if (map != null) {
			bitacora = (Bitacora) map.get("bitacora");
			String motivo = (String) map.get("motivo");
			seguimientoEstado();
			lblPaciente
					.setValue(formatearNombre(bitacora.getTraslado()
							.getPaciente().getNombres()
							+ " "
							+ bitacora.getTraslado().getPaciente()
									.getPrimerApellido()));
			lblRut.setValue(bitacora.getTraslado().getPaciente().getRut());
			lblDiagnostico.setValue(bitacora.getTraslado().getDiagnostico().getNombre());
			lblMotivo.setValue(cortarCadena(motivo));
		}
		log.info("Fin del metodo inicializar()");

	}

	/**
	 * llenarEstados: Metodo asigna valores a las etiquetas de cada estado
	 * 
	 * @param No
	 *            Recibe ningun parametro
	 * @return No Retorna ningun dato ni objeto
	 * 
	 * @throws No
	 *             dispara ninguna excepción.
	 * 
	 */
	public void llenarEstados() {
		log.info("Inicio del metodo llenarEstados()");
		lblPrimerEstado.setValue(EnumEstadoSolicitud.CREADA.getEstado());
		lblSegundoEstado.setValue(EnumEstadoSolicitud.PORVALIDAR.getEstado());
		lblTercerEstado.setValue(EnumEstadoSolicitud.TRASLADO.getEstado());
		lblCuartoEstado.setValue(EnumEstadoSolicitud.CIERRECLINICO.getEstado());
		lblQuintoEstado.setValue(EnumEstadoSolicitud.CIERREADMINISTRATIVO
				.getEstado());
		lblQuintoEstado.setStyle("font-size:12px;");
		log.info("Fin del metodo llenarEstados()");
	}

	/**
	 * seguimientoEstado: Metodo que verifica el estado de la bitacora
	 * seleccionada, da estilos necesarios a la vista y muestra los componentes
	 * necesarios para cada estado.
	 * 
	 * @param No
	 *            Recibe ningun parametro
	 * @return No Retorna ningun dato ni objeto
	 * 
	 * @throws No
	 *             dispara ninguna excepción.
	 * 
	 */
	public void seguimientoEstado() {
		log.info("Inicio del metodo seguimientoEstado()");
		llenarEstados();
		if (bitacora.getEstatus()
				.equals(EnumEstadoSolicitud.CREADA.getEstado())) {
			imgEstado.setSrc(Constantes.rutaEstadoCreada);
			divSegundoEstado.setClass("div-estados-inhabilitados");
			divTercerEstado.setClass("div-estados-inhabilitados");
			divCuartoEstado.setClass("div-estados-inhabilitados");
			divQuintoEstado.setClass("div-estados-inhabilitados");
			btnAceptar.setLabel(EnumEstadoSolicitud.PORVALIDAR.getEstado());
			btnRechazar.setVisible(true);
			rowAdjuntar.setVisible(true);
			divAdjuntar.setVisible(true);
			rowAnulacion.setVisible(true);
			lblFechaPrimerEstado.setValue(formatoFecha.format(bitacora
					.getFecha()));
			imagen.setHeight("310px");
			log.info(new StringBuilder("El registro esta en estatus:")
					.append(EnumEstadoSolicitud.CREADA.getEstado()));

		} else if (bitacora.getEstatus().equals(
				EnumEstadoSolicitud.PORVALIDAR.getEstado())) {
			imgEstado.setSrc(Constantes.rutaEstadoPorValidar);
			divTercerEstado.setClass("div-estados-inhabilitados");
			divCuartoEstado.setClass("div-estados-inhabilitados");
			divQuintoEstado.setClass("div-estados-inhabilitados");
			btnAceptar.setLabel("Validar");
			btnRechazar.setVisible(false);
			divAdjuntar.setVisible(true);
			BufferedImage imag;

			try {
				imag = ImageIO.read(new ByteArrayInputStream(bitacora
						.getCarta()));
				imagen.setContent(imag);
				imagen.setVisible(true);
			} catch (IOException e) {
				e.printStackTrace();
			}
			ckbxValidar.setVisible(true);
			lblFechaSegundoEstado.setValue(formatoFecha.format(bitacora
					.getFecha()));
			lblFechaPrimerEstado
					.setValue(formatoFecha.format(servicioBitacora
							.buscarTrasladooEstado(
									bitacora.getTraslado().getId(),
									EnumEstadoSolicitud.CREADA.getEstado())
							.getFecha()));
			log.info(new StringBuilder("El registro esta en estatus:")
					.append(EnumEstadoSolicitud.PORVALIDAR.getEstado()));
		} else if (bitacora.getEstatus().equals(
				EnumEstadoSolicitud.TRASLADO.getEstado())) {

			imgEstado.setSrc(Constantes.rutaEstadoTraslado);
			divCuartoEstado.setClass("div-estados-inhabilitados");
			divQuintoEstado.setClass("div-estados-inhabilitados");
			btnAceptar.setLabel(EnumEstadoSolicitud.CIERRECLINICO.getEstado());
			btnRechazar.setVisible(false);
			rowCierreClinico.setVisible(true);
			rowCierreClinicoObs.setVisible(true);
			wdwEstadoTraslado.setHeight("65%");
			lblFechaTercerEstado.setValue(formatoFecha.format(bitacora
					.getFecha()));
			lblFechaPrimerEstado
					.setValue(formatoFecha.format(servicioBitacora
							.buscarTrasladooEstado(
									bitacora.getTraslado().getId(),
									EnumEstadoSolicitud.CREADA.getEstado())
							.getFecha()));
			lblFechaSegundoEstado.setValue(formatoFecha.format(servicioBitacora
					.buscarTrasladooEstado(bitacora.getTraslado().getId(),
							EnumEstadoSolicitud.PORVALIDAR.getEstado())
					.getFecha()));
			log.info(new StringBuilder("El registro esta en estatus:")
					.append(EnumEstadoSolicitud.TRASLADO.getEstado()));

		} else if (bitacora.getEstatus().equals(
				EnumEstadoSolicitud.ANULADA.getEstado())) {
			imgEstado.setSrc(Constantes.rutaEstadoAnulada);
			divSegundoEstado.setClass("alert alert-danger");
			divTercerEstado.setClass("div-estados-inhabilitados");
			divCuartoEstado.setClass("div-estados-inhabilitados");
			divQuintoEstado.setClass("div-estados-inhabilitados");
			btnAceptar.setVisible(false);
			lblFechaPrimerEstado
					.setValue(formatoFecha.format(servicioBitacora
							.buscarTrasladooEstado(
									bitacora.getTraslado().getId(),
									EnumEstadoSolicitud.CREADA.getEstado())
							.getFecha()));
			lblFechaSegundoEstado.setValue(formatoFecha.format(bitacora
					.getFecha()));
			lblSegundoEstado.setValue(EnumEstadoSolicitud.ANULADA.getEstado());
			wdwEstadoTraslado.setHeight("65%");
			divCierreAdministrativo.setVisible(true);
			lblCierreAdministrativo.setValue("Motivo del anulación:"
					+ bitacora.getMotivoAnulacion());
			log.info(new StringBuilder("El registro esta en estatus:")
					.append(EnumEstadoSolicitud.ANULADA.getEstado()));

		} else if (bitacora.getEstatus().equals(
				EnumEstadoSolicitud.CIERRECLINICO.getEstado())) {
			divQuintoEstado.setClass("div-estados-inhabilitados");
			imgEstado.setSrc(Constantes.rutaEstadoCierreClinico);
			btnRechazar.setVisible(false);
			lblFechaCuartoEstado.setValue(formatoFecha.format(bitacora
					.getFecha()));
			btnAceptar.setLabel(EnumEstadoSolicitud.CIERREADMINISTRATIVO
					.getEstado());
			lblFechaPrimerEstado
					.setValue(formatoFecha.format(servicioBitacora
							.buscarTrasladooEstado(
									bitacora.getTraslado().getId(),
									EnumEstadoSolicitud.CREADA.getEstado())
							.getFecha()));
			lblFechaSegundoEstado.setValue(formatoFecha.format(servicioBitacora
					.buscarTrasladooEstado(bitacora.getTraslado().getId(),
							EnumEstadoSolicitud.PORVALIDAR.getEstado())
					.getFecha()));
			lblFechaTercerEstado.setValue(formatoFecha.format(servicioBitacora
					.buscarTrasladooEstado(bitacora.getTraslado().getId(),
							EnumEstadoSolicitud.TRASLADO.getEstado())
					.getFecha()));
			rowCierreAdministrativo.setVisible(true);
			rowCierreAdministrativoPdf.setVisible(true);
			log.info(new StringBuilder("El registro esta en estatus:")
					.append(EnumEstadoSolicitud.CIERRECLINICO.getEstado()));
		} else if (bitacora.getEstatus().equals(
				EnumEstadoSolicitud.CIERREADMINISTRATIVO.getEstado())) {
			imgEstado.setSrc(Constantes.rutaEstadoCierreAdministrativo);
			btnAceptar.setVisible(false);
			btnRechazar.setVisible(false);
			lblFechaQuintoEstado.setValue(formatoFecha.format(bitacora
					.getFecha()));
			wdwEstadoTraslado.setHeight("100%");
			lblQuintoEstado.setStyle("font-size:12px;");
			lblFechaPrimerEstado
					.setValue(formatoFecha.format(servicioBitacora
							.buscarTrasladooEstado(
									bitacora.getTraslado().getId(),
									EnumEstadoSolicitud.CREADA.getEstado())
							.getFecha()));
			lblFechaSegundoEstado.setValue(formatoFecha.format(servicioBitacora
					.buscarTrasladooEstado(bitacora.getTraslado().getId(),
							EnumEstadoSolicitud.PORVALIDAR.getEstado())
					.getFecha()));
			lblFechaTercerEstado.setValue(formatoFecha.format(servicioBitacora
					.buscarTrasladooEstado(bitacora.getTraslado().getId(),
							EnumEstadoSolicitud.TRASLADO.getEstado())
					.getFecha()));
			lblFechaCuartoEstado.setValue(formatoFecha.format(servicioBitacora
					.buscarTrasladooEstado(bitacora.getTraslado().getId(),
							EnumEstadoSolicitud.CIERRECLINICO.getEstado())
					.getFecha()));
			divCierreAdministrativo.setVisible(true);
			lblCierreAdministrativo.setValue("La cuenta registrada es: "
					+ bitacora.getCuenta());
			mostrarPdf(bitacora);
			log.info(new StringBuilder("El registro esta en estatus:")
					.append(EnumEstadoSolicitud.CIERREADMINISTRATIVO
							.getEstado()));
		}
		log.info("Fin del metodo seguimientoEstado()");
	}

	/**
	 * mostrarPdf: Metodo que busca convierte byte a un documento pdf y es
	 * guardado en un archivo temporal del servidor para ser mostrado en el
	 * estado de cierre administrativo
	 * 
	 * @param Recibe
	 *            un objeto Bitacora
	 * @return No Retorna ningun dato ni objeto
	 * 
	 * @throws No
	 *             dispara ninguna excepción.
	 * 
	 */
	public void mostrarPdf(Bitacora bitacora) {
		log.info("Inicio del metodo mostrarPdf()");
		try {
			int numero = (int) Math.random();
			String ruta = System.getProperty("com.sun.aas.instanceRoot")
					+ "\\eclipseApps\\CartaRespaldo\\public\\temporal\\generado"
					+ numero + ".pdf";
			FileOutputStream fileOuputStream = new FileOutputStream(ruta);
			fileOuputStream.write(bitacora.getArchivoCuenta());
			fileOuputStream.close();
			ifmPdf.setSrc("public/temporal/generado" + numero + ".pdf");
			ifmPdf.setVisible(true);
			File file = new File("ruta");
			file.delete();
		} catch (Exception e) {
			log.error(new StringBuilder("Error en el metodo mostrarPdf()")
					.append(e));
			e.printStackTrace();
		}
		log.info("Fin del metodo mostrarPdf()");
	}

	/**
	 * capturarCartaAdjuntada: Metodo que obtiene y muestra una imagen adjuntada
	 * 
	 * @param Recibe
	 *            un objeto UploadEvent event
	 * @return No Retorna ningun dato ni objeto
	 * 
	 * @throws Dispara
	 *             una excepcion de tipo IOException
	 * 
	 */
	@Listen("onUpload = #fudCarta")
	public void capturarCartaAdjuntada(UploadEvent event) throws IOException {
		log.info("Inicio del metodo capturarCartaAdjuntada()");
		media = event.getMedia();
		if (Validador.validarTipoImagen(media)
				&& Validador.validarTamannoImagen(media)) {
			if (media instanceof org.zkoss.image.Image) {
				BufferedImage imag;
				imag = ImageIO.read(new ByteArrayInputStream(media
						.getByteData()));
				imagen.setContent(imag);
				imagen.setVisible(true);
				log.info("Imagen Valida");
			} else {
				Messagebox.show(Constantes.imagenNoValida, "Alerta",
						Messagebox.OK, Messagebox.EXCLAMATION);
				log.error("Imagen no Valida");
			}
		} else {
			Messagebox.show(Constantes.imagenNoValida, "Alerta", Messagebox.OK,
					Messagebox.EXCLAMATION);
			log.error("Imagen no Valida");
		}
		log.info("Fin del metodo capturarCartaAdjuntada()");
	}

	/**
	 * capturarCuentaAdjuntada: Metodo que obtiene y muestra un PDF adjuntado
	 * 
	 * @param Recibe
	 *            un objeto UploadEvent event
	 * @return No Retorna ningun dato ni objeto
	 * 
	 * @throws Dispara
	 *             una excepcion de tipo IOException
	 * 
	 */
	@Listen("onUpload = #fudCuenta")
	public void capturarCuentaAdjuntada(UploadEvent event) throws IOException {
		log.info("Inicio del metodo capturarCuentaAdjuntada()");
		media = event.getMedia();
		if (Validador.validarTipoDocumento(media)
				&& Validador.validarTamannoDocumento(media)) {

			ifmPdf.setContent(media);
			ifmPdf.setVisible(true);
			log.info("Documento Valido");
		} else {
			Messagebox.show(Constantes.documentoNoValido, "Alerta",
					Messagebox.OK, Messagebox.EXCLAMATION);
			log.error("Documento no Valido");
		}
		log.info("Fin del metodo capturarCuentaAdjuntada()");
	}

	/**
	 * inhabilitarEstado: Metodo que coloca el estado anterior inactivo
	 * 
	 * @param No
	 *            recibe ningun parametro
	 * @return No Retorna ningun dato ni objeto
	 * 
	 * @throws No
	 *             dispara ninguna excepción.
	 * 
	 */
	public void inhabilitarEstado() {
		log.info("Inicio del metodo inhabilitarEstado()");
		Bitacora b = servicioBitacora.buscarEstadoActivo(bitacora.getTraslado()
				.getId(), true);
		b.setActivo(false);
		servicioBitacora.guardar(b);
		log.info("Fin del metodo inhabilitarEstado()");
	}

	/**
	 * cambiarEstado: Metodo que cambia el estado de una solicitud y guarda los
	 * valores necesarios depende el estado
	 * 
	 * @param No
	 *            recibe ningun parametro
	 * @return No Retorna ningun dato ni objeto
	 * 
	 * @throws No
	 *             dispara ninguna excepción.
	 * 
	 */
	@Listen("onClick = #btnAceptar")
	public void cambiarEstado() {
		log.info("Inicio del metodo cambiarEstado()");
		Bitacora bq = new Bitacora();
		bq.setActivo(true);
		bq.setFecha(fechaHora);
		bq.setTraslado(bitacora.getTraslado());
		if (bitacora.getEstatus()
				.equals(EnumEstadoSolicitud.CREADA.getEstado())) {
			if (validarEstadoPorValidar()) {
				inhabilitarEstado();
				bq.setEstatus(EnumEstadoSolicitud.PORVALIDAR.getEstado());
				bq.setCarta(media.getByteData());
				bq.setUsuario(usuarioActivo());
				servicioBitacora.guardar(bq);
				cancelar();
				Executions.sendRedirect(null);
			} else {
				Messagebox.show(Constantes.mensajeCamposVacios, "Advertencia",
						Messagebox.OK, Messagebox.EXCLAMATION);
			}

		} else if (bitacora.getEstatus().equals(
				EnumEstadoSolicitud.PORVALIDAR.getEstado())) {
			if (validarTraslado()) {
				inhabilitarEstado();
				bq.setEstatus(EnumEstadoSolicitud.TRASLADO.getEstado());
				bq.setValidada(true);
				bq.setUsuario(usuarioActivo());
				servicioBitacora.guardar(bq);
				cancelar();
				Executions.sendRedirect(null);
			} else {
				Messagebox.show(Constantes.mensajeCamposVacios, "Advertencia",
						Messagebox.OK, Messagebox.EXCLAMATION);
			}

		} else if (bitacora.getEstatus().equals(
				EnumEstadoSolicitud.TRASLADO.getEstado())) {
			if (validarCierreClinico()) {
				inhabilitarEstado();
				bq.setEstatus(EnumEstadoSolicitud.CIERRECLINICO.getEstado());
				bq.setUsuario(usuarioActivo());
				bq.setObservacionCierreClinico(txtObservacionCierreClinico
						.getValue());
				bq.setEpicrisis(txtEpicrisis.getValue());
				servicioBitacora.guardar(bq);
				cancelar();
				Executions.sendRedirect(null);
			} else {
				Messagebox.show(Constantes.mensajeCamposVacios, "Advertencia",
						Messagebox.OK, Messagebox.EXCLAMATION);
			}
		} else if (bitacora.getEstatus().equals(
				EnumEstadoSolicitud.CIERRECLINICO.getEstado())) {
			if (validarCierreAdministrativo()) {
				inhabilitarEstado();
				bq.setEstatus(EnumEstadoSolicitud.CIERREADMINISTRATIVO
						.getEstado());
				bq.setUsuario(usuarioActivo());
				bq.setCuenta(txtRecepcionCuenta.getValue());
				bq.setArchivoCuenta(media.getByteData());
				servicioBitacora.guardar(bq);
				cancelar();
				Executions.sendRedirect(null);
			} else {
				Messagebox.show(Constantes.mensajeCamposVacios, "Advertencia",
						Messagebox.OK, Messagebox.EXCLAMATION);
			}
		}
		log.info("Fin del metodo cambiarEstado()");
	}

	@Listen("onClick = #btnCancelar")
	public void cancelar() {
		wdwEstadoTraslado.onClose();
	}

	/**
	 * rechazarCaso: Metodo que cambia el estado de una solicitud a anulado y guarda los
	 * valores necesarios de ese estado. 
	 * 
	 * @param No
	 *            recibe ningun parametro
	 * @return No Retorna ningun dato ni objeto
	 * 
	 * @throws No
	 *             dispara ninguna excepción.
	 * 
	 */
	@Listen("onClick = #btnRechazar")
	public void rechazarCaso() {
		log.info("Inicio del metodo rechazarCaso()");
		Bitacora bq = new Bitacora();
		bq.setActivo(true);
		bq.setFecha(fechaHora);
		bq.setTraslado(bitacora.getTraslado());
		if (validarAnulacion()) {
			inhabilitarEstado();
			bq.setEstatus(EnumEstadoSolicitud.ANULADA.getEstado());
			bq.setMotivoAnulacion(txtMotivoAnulacion.getValue());
			servicioBitacora.guardar(bq);
			cancelar();
			Executions.sendRedirect(null);
		} else {
			Messagebox.show(Constantes.mensajeCamposVacios, "Advertencia",
					Messagebox.OK, Messagebox.EXCLAMATION);
		}
		log.info("Fin del metodo rechazarCaso()");
	}

	public boolean validarEstadoPorValidar() {
		if (media == null) {
			return false;
		} else {
			return true;
		}
	}

	public boolean validarTraslado() {
		if (ckbxValidar.isChecked()) {
			return true;
		} else {
			return false;
		}
	}

	public boolean validarCierreClinico() {
		if (txtObservacionCierreClinico.getValue().equals("")
				|| txtEpicrisis.getValue().equals("")) {
			return false;
		} else {
			return true;
		}
	}

	public boolean validarCierreAdministrativo() {
		if (txtRecepcionCuenta.getValue().equals("") || media == null) {
			return false;
		} else {
			return true;
		}
	}

	public boolean validarAnulacion() {
		if (txtMotivoAnulacion.getValue().equals("")) {
			return false;
		} else {
			return true;
		}
	}

}
