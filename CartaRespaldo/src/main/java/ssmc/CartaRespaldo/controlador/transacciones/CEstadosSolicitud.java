/**
 * 
 */
package ssmc.CartaRespaldo.controlador.transacciones;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
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
import org.zkoss.zul.Combobox;
import org.zkoss.zul.Datebox;
import org.zkoss.zul.Div;
import org.zkoss.zul.Doublespinner;
import org.zkoss.zul.Hbox;
import org.zkoss.zul.Iframe;
import org.zkoss.zul.Image;
import org.zkoss.zul.Label;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Row;
import org.zkoss.zul.Spinner;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Window;

import ssmc.CartaRespaldo.componentes.Constantes;
import ssmc.CartaRespaldo.componentes.Validador;
import ssmc.CartaRespaldo.controlador.maestros.CGenerico;
import ssmc.CartaRespaldo.enums.EnumEstadoSolicitud;
import ssmc.CartaRespaldo.enums.EnumMotivoCierreClinico;
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
	private Label lblFechaSextoEstado;
	@Wire
	private Label lblFechaSeptimoEstado;
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
	private Media mediaActaValorizacion;
	private Media mediaActaAuditoria;
	private Media mediaMemorandum;
	@Wire
	private Div divQuintoEstado;
	@Wire
	private Div divSextoEstado;
	@Wire
	private Div divSeptimoEstado;
	@Wire
	private Label lblQuintoEstado;
	@Wire
	private Label lblSextoEstado;
	@Wire
	private Label lblSeptimoEstado;
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
	private Row rowEpicrisisPdf;
	@Wire
	private Textbox txtMotivoAnulacion;
	@Wire
	private Textbox txtidSigfe;
	@Wire
	private Row rowIdSigfe;
	@Wire
	private Row rowMotivoCierreClinico;
	@Wire
	private Combobox cmbMotivoCierreClinico;
	@Wire
	private Row rowOrdenCompra;
	@Wire
	private Textbox txtOrdenCompra;
	@Wire
	private Row rowNumeroFactura;
	@Wire
	private Textbox txtNumeroFactura;
	@Wire
	private Row rowFechaRecepcion;
	@Wire
	private Datebox dtbFechaRecepcion;
	@Wire
	private Row rowMontoTotal;
	@Wire
	private Doublespinner dsnpMontoTotalCobrado;
	@Wire
	private Row rowfechaIngresoCuenta;
	@Wire
	private Datebox dtbFechaIngresoCuenta;
	@Wire
	private Row rowObservacion;
	@Wire
	private Textbox txtObservacionVisacion;
	@Wire
	private Row rowMontoDescuento;
	@Wire
	private Doublespinner dspnMontoDescuento;
	@Wire
	private Row rowDiasCamaUci;
	@Wire
	private Spinner spnDiasCamaUci;
	@Wire
	private Row rowDiasCamaUti;
	@Wire
	private Spinner spnDiasCamaUti;
	@Wire
	private Row rowDiasCamaBasica;
	@Wire
	private Spinner spnDiasCamaBasica;
	@Wire
	private Row rowMontoPago;
	@Wire
	private Doublespinner dspnMontoPago;
	@Wire
	private Row rowPrestacionesAdicionales;
	@Wire
	private Doublespinner dspnPrestacionesAdicionales;
	@Wire
	private Div divInformacion;
	@Wire
	private Label lblInformacion;
	private String motivo = "";
	@Wire
	private Div divInfoValidacion;
	@Wire
	private Label lblInfoValidacion;
	@Wire
	private Label lblInfoValidacion1;
	@Wire
	private Label lblInfoValidacion2;
	@Wire
	private Label lblInfoValidacion3;
	@Wire
	private Div divInfoRecepcion;
	@Wire
	private Label lblInfoRecepcion;
	@Wire
	private Label lblInfoRecepcion1;
	@Wire
	private Label lblInfoRecepcion2;
	@Wire
	private Label lblInfoRecepcion3;
	@Wire
	private Div divInfoVisacion;
	@Wire
	private Label lblInfoVisacion;
	@Wire
	private Label lblInfoVisacion1;
	@Wire
	private Label lblInfoVisacion2;
	@Wire
	private Label lblInfoVisacion3;
	@Wire
	private Label lblInfoVisacion4;
	@Wire
	private Label lblInfoVisacion5;
	@Wire
	private Label lblInfoVisacion6;
	@Wire
	private Label lblInfoVisacion7;
	@Wire
	private Label lblInfoVisacion8;
	@Wire
	private Div divDocumentos;
	@Wire
	private Label lblDocumentos;
	@Wire
	private Row rowFechaIngreso;
	@Wire
	private Datebox dtbFechaIngreso;
	@Wire
	private Row rowFechaEgreso;
	@Wire
	private Datebox dtbFechaEgreso;
	@Wire
	private Row rowTipoCuenta;
	@Wire
	private Combobox cmbTipoCuenta;
	@Wire
	private Div divTipoCuenta;
	@Wire
	private Label lblTipoCuenta;
	@Wire
	private Hbox hbxLinks;
	@Wire
	private Hbox hbxLabels;
	@Wire
	private Label lblActaValorizacion;
	@Wire
	private Label lblActaAuditoria;
	@Wire
	private Label lblMemorandum;
	String rutaAuditoria;
	String rutaValorizacion;
	String rutaMemorandum;
	@Wire
	private Window wdwModalDocumentos;
	@Wire
	private Button btnVerInformeEpicrisis;

	@Override
	public void inicializar() throws IOException {
		log.info("Metodo inicializar ()");
		HashMap<String, Object> map = (HashMap<String, Object>) Sessions
				.getCurrent().getAttribute("itemsCatalogo");
		if (map != null) {
			bitacora = (Bitacora) map.get("bitacora");
			motivo = (String) map.get("motivo");
			seguimientoEstado();
			lblPaciente
					.setValue(formatearNombre(bitacora.getTraslado()
							.getPaciente().getNombres()
							+ " "
							+ bitacora.getTraslado().getPaciente()
									.getPrimerApellido()));
			lblRut.setValue(bitacora.getTraslado().getPaciente().getRut());
			lblDiagnostico.setValue(bitacora.getTraslado().getDiagnostico()
					.getNombre());
			lblMotivo.setValue(cortarCadena(motivo));

		}
		if (bitacora.getTraslado().getObservacion() != null) {
			Messagebox.show("Paciente posee restricciones -"
					+ bitacora.getTraslado().getObservacion() + ", "
					+ bitacora.getTraslado().getObservacionRestriccion(),
					"Alerta", Messagebox.OK, Messagebox.EXCLAMATION);

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
		lblQuintoEstado.setValue(EnumEstadoSolicitud.RECEPCIONCUENTA
				.getEstado());
		lblSextoEstado.setValue(EnumEstadoSolicitud.VISACION.getEstado());
		lblSeptimoEstado.setValue(EnumEstadoSolicitud.CIERREADMINISTRATIVO
				.getEstado());
		lblSeptimoEstado.setStyle("font-size:12px;");
		log.info("Fin del metodo llenarEstados()");
	}

	public void estadoCreada() {
		imgEstado.setSrc(Constantes.rutaEstadoCreada);
		divSegundoEstado.setClass("div-estado-activo");
		divTercerEstado.setClass("div-estados-inhabilitados");
		divCuartoEstado.setClass("div-estados-inhabilitados");
		divQuintoEstado.setClass("div-estados-inhabilitados");
		divSextoEstado.setClass("div-estados-inhabilitados");
		divSeptimoEstado.setClass("div-estados-inhabilitados");
		btnAceptar.setLabel(EnumEstadoSolicitud.PORVALIDAR.getEstado());
		btnRechazar.setVisible(true);
		rowAdjuntar.setVisible(true);
		divAdjuntar.setVisible(true);
		rowAnulacion.setVisible(true);
		lblFechaPrimerEstado.setValue(formatoFecha.format(bitacora.getFecha())
				+ " " + df.format(bitacora.getFecha()));
		imagen.setHeight("310px");
		log.info(new StringBuilder("El registro esta en estatus:")
				.append(EnumEstadoSolicitud.CREADA.getEstado()));
	}

	public void estadoPorValidar() {
		imgEstado.setSrc(Constantes.rutaEstadoPorValidar);
		divTercerEstado.setClass("div-estado-activo");
		divCuartoEstado.setClass("div-estados-inhabilitados");
		divQuintoEstado.setClass("div-estados-inhabilitados");
		divSextoEstado.setClass("div-estados-inhabilitados");
		divSeptimoEstado.setClass("div-estados-inhabilitados");
		btnAceptar.setLabel("Validar");
		btnRechazar.setVisible(false);
		divAdjuntar.setVisible(true);
		BufferedImage imag;

		try {
			imag = ImageIO.read(new ByteArrayInputStream(bitacora.getCarta()));
			imagen.setContent(imag);
			imagen.setVisible(true);
		} catch (IOException e) {
			e.printStackTrace();
		}
		ckbxValidar.setVisible(true);
		Timestamp primeraFecha = servicioBitacora.buscarTrasladooEstado(
				bitacora.getTraslado().getId(),
				EnumEstadoSolicitud.CREADA.getEstado()).getFecha();
		lblFechaSegundoEstado.setValue(formatoFecha.format(bitacora.getFecha())
				+ " " + df.format(bitacora.getFecha()));
		lblFechaPrimerEstado.setValue(formatoFecha.format(primeraFecha) + " "
				+ df.format(primeraFecha));
		log.info(new StringBuilder("El registro esta en estatus:")
				.append(EnumEstadoSolicitud.PORVALIDAR.getEstado()));
	}

	public void estadoTraslado() {
		imgEstado.setSrc(Constantes.rutaEstadoTraslado);
		divCuartoEstado.setClass("div-estado-activo");
		divQuintoEstado.setClass("div-estados-inhabilitados");
		divSextoEstado.setClass("div-estados-inhabilitados");
		divSeptimoEstado.setClass("div-estados-inhabilitados");
		btnAceptar.setLabel(EnumEstadoSolicitud.CIERRECLINICO.getEstado());
		btnRechazar.setVisible(false);
		rowCierreClinico.setVisible(true);
		rowCierreClinicoObs.setVisible(true);
		rowMotivoCierreClinico.setVisible(true);
		wdwEstadoTraslado.setHeight("97%");
		rowEpicrisisPdf.setVisible(true);
		ifmPdf.setWidth("500px");
		ifmPdf.setHeight("180px");
		Timestamp primeraFecha = servicioBitacora.buscarTrasladooEstado(
				bitacora.getTraslado().getId(),
				EnumEstadoSolicitud.CREADA.getEstado()).getFecha();
		Timestamp segundaFecha = servicioBitacora.buscarTrasladooEstado(
				bitacora.getTraslado().getId(),
				EnumEstadoSolicitud.PORVALIDAR.getEstado()).getFecha();
		lblFechaTercerEstado.setValue(formatoFecha.format(bitacora.getFecha())
				+ df.format(bitacora.getFecha()));
		lblFechaPrimerEstado.setValue(formatoFecha.format(primeraFecha) + " "
				+ df.format(primeraFecha));
		lblFechaSegundoEstado.setValue(formatoFecha.format(segundaFecha) + " "
				+ df.format(segundaFecha));
		llenarComboMotivoCierreClinico();
		log.info(new StringBuilder("El registro esta en estatus:")
				.append(EnumEstadoSolicitud.TRASLADO.getEstado()));
	}

	public void estadoAnulada() {
		imgEstado.setSrc(Constantes.rutaEstadoAnulada);
		divSegundoEstado.setClass("alert alert-danger");
		divTercerEstado.setClass("div-estados-inhabilitados");
		divCuartoEstado.setClass("div-estados-inhabilitados");
		divQuintoEstado.setClass("div-estados-inhabilitados");
		divSextoEstado.setClass("div-estados-inhabilitados");
		divSeptimoEstado.setClass("div-estados-inhabilitados");
		btnAceptar.setVisible(false);
		Timestamp primeraFecha = servicioBitacora.buscarTrasladooEstado(
				bitacora.getTraslado().getId(),
				EnumEstadoSolicitud.CREADA.getEstado()).getFecha();
		lblFechaPrimerEstado.setValue(formatoFecha.format(primeraFecha) + " "
				+ df.format(primeraFecha));
		lblFechaSegundoEstado.setValue(formatoFecha.format(bitacora.getFecha())
				+ " " + df.format(bitacora.getFecha()));
		lblSegundoEstado.setValue(EnumEstadoSolicitud.ANULADA.getEstado());
		wdwEstadoTraslado.setHeight("65%");
		divCierreAdministrativo.setVisible(true);
		lblCierreAdministrativo.setValue("Motivo del anulación:"
				+ bitacora.getMotivoAnulacion());
		log.info(new StringBuilder("El registro esta en estatus:")
				.append(EnumEstadoSolicitud.ANULADA.getEstado()));
	}

	public void estadoCierreClinico() {
		divQuintoEstado.setClass("div-estado-activo");
		divSextoEstado.setClass("div-estados-inhabilitados");
		divSeptimoEstado.setClass("div-estados-inhabilitados");
		imgEstado.setSrc(Constantes.rutaEstadoCierreClinico);
		btnRechazar.setVisible(false);
		Timestamp primeraFecha = servicioBitacora.buscarTrasladooEstado(
				bitacora.getTraslado().getId(),
				EnumEstadoSolicitud.CREADA.getEstado()).getFecha();
		Timestamp segundaFecha = servicioBitacora.buscarTrasladooEstado(
				bitacora.getTraslado().getId(),
				EnumEstadoSolicitud.PORVALIDAR.getEstado()).getFecha();
		Timestamp terceraFecha = servicioBitacora.buscarTrasladooEstado(
				bitacora.getTraslado().getId(),
				EnumEstadoSolicitud.TRASLADO.getEstado()).getFecha();
		lblFechaCuartoEstado.setValue(formatoFecha.format(bitacora.getFecha())
				+ " " + df.format(bitacora.getFecha()));
		btnAceptar.setLabel(EnumEstadoSolicitud.RECEPCIONCUENTA.getEstado());
		lblFechaPrimerEstado.setValue(formatoFecha.format(primeraFecha) + " "
				+ df.format(primeraFecha));
		lblFechaSegundoEstado.setValue(formatoFecha.format(segundaFecha)
				+ df.format(segundaFecha));
		lblFechaTercerEstado.setValue(formatoFecha.format(terceraFecha)
				+ df.format(terceraFecha));
		rowCierreAdministrativo.setVisible(true);

		rowNumeroFactura.setVisible(true);
		rowFechaRecepcion.setVisible(true);
		rowMontoTotal.setVisible(true);
		rowFechaIngreso.setVisible(true);
		rowFechaEgreso.setVisible(true);
		rowTipoCuenta.setVisible(true);
		wdwEstadoTraslado.setHeight("85%");
		log.info(new StringBuilder("El registro esta en estatus:")
				.append(EnumEstadoSolicitud.CIERRECLINICO.getEstado()));
	}

	public void estadoCierreAdministrativo() {
		imgEstado.setSrc(Constantes.rutaEstadoCierreAdministrativo);
		btnRechazar.setVisible(false);
		lblFechaQuintoEstado.setValue(formatoFecha.format(bitacora.getFecha())
				+ " " + df.format(bitacora.getFecha()));
		wdwEstadoTraslado.setHeight("100%");
		lblQuintoEstado.setStyle("font-size:12px;");
		Bitacora primerEstado = servicioBitacora.buscarTrasladooEstado(bitacora
				.getTraslado().getId(), EnumEstadoSolicitud.CREADA.getEstado());
		Bitacora segundoEstado = servicioBitacora.buscarTrasladooEstado(
				bitacora.getTraslado().getId(),
				EnumEstadoSolicitud.PORVALIDAR.getEstado());
		Bitacora tercerEstado = servicioBitacora.buscarTrasladooEstado(bitacora
				.getTraslado().getId(), EnumEstadoSolicitud.TRASLADO
				.getEstado());
		Bitacora cuartoEstado = servicioBitacora.buscarTrasladooEstado(bitacora
				.getTraslado().getId(), EnumEstadoSolicitud.CIERRECLINICO
				.getEstado());
		Bitacora quintoEstado = servicioBitacora.buscarTrasladooEstado(bitacora
				.getTraslado().getId(), EnumEstadoSolicitud.RECEPCIONCUENTA
				.getEstado());
		Bitacora sextoEstado = servicioBitacora.buscarTrasladooEstado(bitacora
				.getTraslado().getId(), EnumEstadoSolicitud.VISACION
				.getEstado());
		Timestamp primeraFecha = primerEstado.getFecha();
		Timestamp segundaFecha = segundoEstado.getFecha();
		Timestamp terceraFecha = tercerEstado.getFecha();
		Timestamp cuartaFecha = cuartoEstado.getFecha();
		Timestamp quintaFecha = quintoEstado.getFecha();
		Timestamp sextaFecha = sextoEstado.getFecha();

		lblFechaPrimerEstado.setValue(formatoFecha.format(primeraFecha) + " "
				+ df.format(primeraFecha));
		lblFechaSegundoEstado.setValue(formatoFecha.format(segundaFecha) + " "
				+ df.format(segundaFecha));
		lblFechaTercerEstado.setValue(formatoFecha.format(terceraFecha) + " "
				+ df.format(terceraFecha));
		lblFechaCuartoEstado.setValue(formatoFecha.format(cuartaFecha) + " "
				+ df.format(cuartaFecha));
		lblFechaQuintoEstado.setValue(formatoFecha.format(quintaFecha) + " "
				+ df.format(bitacora.getFecha()));
		lblFechaSextoEstado.setValue(formatoFecha.format(sextaFecha) + " "
				+ df.format(bitacora.getFecha()));
		lblFechaSeptimoEstado.setValue(formatoFecha.format(bitacora.getFecha())
				+ " " + df.format(bitacora.getFecha()));
		btnAceptar.setLabel("Aceptar");
		wdwEstadoTraslado.setHeight("100%");
		divInfoRecepcion.setVisible(true);
		divInfoValidacion.setVisible(true);
		divInfoVisacion.setVisible(true);
		divDocumentos.setVisible(true);

		/** INFORMACIÓN GENERAL **/
		lblInfoValidacion.setValue("Validador de la carta: "
				+ tercerEstado.getUsuario().getNombre());
		lblInfoValidacion1.setValue("Establecimiento destino: "
				+ bitacora.getTraslado().getEstablecimiento().getNombre());
		lblInfoValidacion2.setValue("Fecha de ingrego: ");
		lblInfoValidacion3.setValue("Fecha de egreso: ");

		/** INFORMACIÓN DE LA RECEPCIÓN **/
		lblInfoRecepcion.setValue("Id cuenta: " + quintoEstado.getCuenta());
		lblInfoRecepcion1.setValue("Número de factura: "
				+ quintoEstado.getNumeroFactura());
		lblInfoRecepcion2.setValue("Fecha de recepción: "
				+ formatoFecha.format(quintoEstado.getFechaRecepcion()));
		BigDecimal monto = new BigDecimal(quintoEstado.getMontoCobrado());
		lblInfoRecepcion3.setValue("Monto cobrado: " + monto);

		/** INFORMACIÓN DE LA VISACIÓN **/
		lblInfoVisacion.setValue("Fecha de ingreso de la cuenta: "
				+ formatoFecha.format(sextoEstado.getFechaIngresoCuenta()));
		lblInfoVisacion1.setValue("Observación: "
				+ sextoEstado.getObservacionVisacion());
		BigDecimal descuento = new BigDecimal(sextoEstado.getMontoDescuento());
		lblInfoVisacion2.setValue("Monto descuento: " + descuento);
		if (motivo.contains("CAMA")) {
			lblInfoVisacion3.setValue("Total días cama UCI: "
					+ sextoEstado.getTotalDiasCamaUci());
			lblInfoVisacion4.setValue("Total días cama UTI: "
					+ sextoEstado.getTotalDiasCamaUti());
			lblInfoVisacion5.setValue("Total días cama Básica: "
					+ sextoEstado.getTotalDiasCamaBasica());
		}
		lblInfoVisacion6.setValue("Prestaciones adicionales: "
				+ sextoEstado.getPrestacionesAdicionales());
		BigDecimal pago = new BigDecimal(sextoEstado.getMontoPago());
		lblInfoVisacion7.setValue("Monto pago: " + pago);
		if (cuartoEstado.getEpicrisisInforme() == null){
			btnVerInformeEpicrisis.setVisible(false); 
		}
	}

	public void estadoRecepcionCuenta() {
		imgEstado.setSrc(Constantes.rutaEstadoRecepcionCuenta);
		divSextoEstado.setClass("div-estado-activo");
		divSeptimoEstado.setClass("div-estados-inhabilitados");
		btnAceptar.setLabel(EnumEstadoSolicitud.VISACION.getEstado());
		rowfechaIngresoCuenta.setVisible(true);
		rowObservacion.setVisible(true);
		rowMontoDescuento.setVisible(true);
		if (motivo.contains("CAMA")) {
			rowDiasCamaUci.setVisible(true);
			rowDiasCamaUti.setVisible(true);
			rowDiasCamaBasica.setVisible(true);
		}
		rowMontoPago.setVisible(true);
		rowPrestacionesAdicionales.setVisible(true);
		Timestamp primeraFecha = servicioBitacora.buscarTrasladooEstado(
				bitacora.getTraslado().getId(),
				EnumEstadoSolicitud.CREADA.getEstado()).getFecha();
		Timestamp segundaFecha = servicioBitacora.buscarTrasladooEstado(
				bitacora.getTraslado().getId(),
				EnumEstadoSolicitud.PORVALIDAR.getEstado()).getFecha();
		Timestamp terceraFecha = servicioBitacora.buscarTrasladooEstado(
				bitacora.getTraslado().getId(),
				EnumEstadoSolicitud.TRASLADO.getEstado()).getFecha();
		Timestamp cuartaFecha = servicioBitacora.buscarTrasladooEstado(
				bitacora.getTraslado().getId(),
				EnumEstadoSolicitud.CIERRECLINICO.getEstado()).getFecha();

		lblFechaCuartoEstado.setValue(formatoFecha.format(cuartaFecha) + " "
				+ df.format(bitacora.getFecha()));
		lblFechaPrimerEstado.setValue(formatoFecha.format(primeraFecha) + " "
				+ df.format(primeraFecha));
		lblFechaSegundoEstado.setValue(formatoFecha.format(segundaFecha)
				+ df.format(segundaFecha));
		lblFechaTercerEstado.setValue(formatoFecha.format(terceraFecha)
				+ df.format(terceraFecha));
		lblFechaQuintoEstado.setValue(formatoFecha.format(bitacora.getFecha())
				+ " " + df.format(bitacora.getFecha()));
		wdwEstadoTraslado.setHeight("100%");
		divInformacion.setVisible(true);

		double monto = bitacora.getMontoCobrado();

		BigDecimal bd1 = new BigDecimal(monto);
		lblInformacion
				.setValue("Información de la cuenta recepcionada - Id cuenta: "
						+ bitacora.getCuenta()
						+ " , Número de factura: "
						+ bitacora.getNumeroFactura()
						+ " , Fecha: "
						+ formatoFecha.format(bitacora.getFechaRecepcion())
						+ ", Monto cobrado: "
						+ bd1
						+ ", Fecha de ingreso al establecimiento: "
						+ formatoFecha.format(bitacora
								.getFechaIngresoPaciente())
						+ ", Fecha de egreso al establecimiento: "
						+ formatoFecha.format(bitacora.getFechaEgresoPaciente()));
		divTipoCuenta.setVisible(true);
		lblTipoCuenta.setValue("El tipo de cuenta es: "
				+ bitacora.getTipoCuenta());
		hbxLinks.setVisible(true);
		hbxLabels.setVisible(true);

	}

	public void estadoVisacion() {
		imgEstado.setSrc(Constantes.rutaEstadoVisacion);
		divSeptimoEstado.setClass("div-estado-activo");
		btnAceptar.setLabel(EnumEstadoSolicitud.CIERREADMINISTRATIVO
				.getEstado());
		rowCierreAdministrativoPdf.setVisible(true);
		rowIdSigfe.setVisible(true);
		rowOrdenCompra.setVisible(true);
		Timestamp primeraFecha = servicioBitacora.buscarTrasladooEstado(
				bitacora.getTraslado().getId(),
				EnumEstadoSolicitud.CREADA.getEstado()).getFecha();
		Timestamp segundaFecha = servicioBitacora.buscarTrasladooEstado(
				bitacora.getTraslado().getId(),
				EnumEstadoSolicitud.PORVALIDAR.getEstado()).getFecha();
		Timestamp terceraFecha = servicioBitacora.buscarTrasladooEstado(
				bitacora.getTraslado().getId(),
				EnumEstadoSolicitud.TRASLADO.getEstado()).getFecha();
		Timestamp cuartaFecha = servicioBitacora.buscarTrasladooEstado(
				bitacora.getTraslado().getId(),
				EnumEstadoSolicitud.CIERRECLINICO.getEstado()).getFecha();
		Timestamp quintaFecha = servicioBitacora.buscarTrasladooEstado(
				bitacora.getTraslado().getId(),
				EnumEstadoSolicitud.RECEPCIONCUENTA.getEstado()).getFecha();

		lblFechaCuartoEstado.setValue(formatoFecha.format(cuartaFecha) + " "
				+ df.format(bitacora.getFecha()));
		lblFechaPrimerEstado.setValue(formatoFecha.format(primeraFecha) + " "
				+ df.format(primeraFecha));
		lblFechaSegundoEstado.setValue(formatoFecha.format(segundaFecha)
				+ df.format(segundaFecha));
		lblFechaTercerEstado.setValue(formatoFecha.format(terceraFecha)
				+ df.format(terceraFecha));
		lblFechaQuintoEstado.setValue(formatoFecha.format(quintaFecha) + " "
				+ df.format(bitacora.getFecha()));
		lblFechaSextoEstado.setValue(formatoFecha.format(bitacora.getFecha())
				+ " " + df.format(bitacora.getFecha()));
		wdwEstadoTraslado.setHeight("92%");

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
			estadoCreada();
		} else if (bitacora.getEstatus().equals(
				EnumEstadoSolicitud.PORVALIDAR.getEstado())) {
			estadoPorValidar();
		} else if (bitacora.getEstatus().equals(
				EnumEstadoSolicitud.TRASLADO.getEstado())) {
			estadoTraslado();
		} else if (bitacora.getEstatus().equals(
				EnumEstadoSolicitud.ANULADA.getEstado())) {
			estadoAnulada();
		} else if (bitacora.getEstatus().equals(
				EnumEstadoSolicitud.CIERRECLINICO.getEstado())) {
			estadoCierreClinico();
		} else if (bitacora.getEstatus().equals(
				EnumEstadoSolicitud.RECEPCIONCUENTA.getEstado())) {
			estadoRecepcionCuenta();
		} else if (bitacora.getEstatus().equals(
				EnumEstadoSolicitud.VISACION.getEstado())) {
			estadoVisacion();
		} else if (bitacora.getEstatus().equals(
				EnumEstadoSolicitud.CIERREADMINISTRATIVO.getEstado())) {
			estadoCierreAdministrativo();
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
		// log.info("Inicio del metodo mostrarPdf()");
		// try {
		// int numero = (int) Math.random();
		// String ruta = System.getProperty("com.sun.aas.instanceRoot")
		// + "/applications/CartaRespaldo/public/vistas/generado"
		// + numero + ".pdf";
		// log.info("Ruta:" + ruta);
		// log.info("Ruta server:"
		// + System.getProperty("com.sun.aas.instanceRoot"));
		// FileOutputStream fileOuputStream = new FileOutputStream(ruta);
		// fileOuputStream.write(bitacora.getArchivoCuenta());
		// fileOuputStream.close();
		// log.info("Ruta Iframe:" + "public/vistas/generado" + numero
		// + ".pdf");
		// ifmPdf.setSrc("public/vistas/generado" + numero + ".pdf");
		// ifmPdf.setVisible(true);
		// File file = new File("ruta");
		// file.delete();
		// } catch (Exception e) {
		// log.error(new StringBuilder("Error en el metodo mostrarPdf()")
		// .append(e));
		// e.printStackTrace();
		// }
		// log.info("Fin del metodo mostrarPdf()");
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

	@Listen("onUpload = #fudEpicrisis")
	public void capturarEpicrisisAdjuntada(UploadEvent event)
			throws IOException {
		log.info("Inicio del metodo capturarEpicrisisAdjuntada()");
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
		log.info("Fin del metodo capturarEpicrisisAdjuntada()");
	}

	@Listen("onUpload = #btnActaValorizacion")
	public void capturarActaValorizacion(UploadEvent event) throws IOException {
		log.info("Inicio del metodo capturarActaValorizacion()");
		mediaActaValorizacion = event.getMedia();
		if (Validador.validarTipoDocumento(mediaActaValorizacion)
				&& Validador.validarTamannoDocumento(mediaActaValorizacion)) {
			lblActaValorizacion.setValue(mediaActaValorizacion.getName());
			log.info("Documento Valido");
		} else {
			Messagebox.show(Constantes.documentoNoValido, "Alerta",
					Messagebox.OK, Messagebox.EXCLAMATION);
			log.error("Documento no Valido");
		}
		log.info("Fin del metodo capturarActaValorizacion()");
	}

	@Listen("onUpload = #btnActaAuditoria")
	public void capturarActaAuditoria(UploadEvent event) throws IOException {
		log.info("Inicio del metodo capturarActaAuditoria()");
		mediaActaAuditoria = event.getMedia();
		if (Validador.validarTipoDocumento(mediaActaAuditoria)
				&& Validador.validarTamannoDocumento(mediaActaAuditoria)) {
			lblActaAuditoria.setValue(mediaActaAuditoria.getName());
			log.info("Documento Valido");
		} else {
			Messagebox.show(Constantes.documentoNoValido, "Alerta",
					Messagebox.OK, Messagebox.EXCLAMATION);
			log.error("Documento no Valido");
		}
		log.info("Fin del metodo capturarActaAuditoria()");
	}

	@Listen("onUpload = #btnMemorandum")
	public void capturarMemorandum(UploadEvent event) throws IOException {
		log.info("Inicio del metodo capturarMemorandum()");
		mediaMemorandum = event.getMedia();
		if (Validador.validarTipoDocumento(mediaMemorandum)
				&& Validador.validarTamannoDocumento(mediaMemorandum)) {
			lblMemorandum.setValue(mediaMemorandum.getName());
			log.info("Documento Valido");
		} else {
			Messagebox.show(Constantes.documentoNoValido, "Alerta",
					Messagebox.OK, Messagebox.EXCLAMATION);
			log.error("Documento no Valido");
		}
		log.info("Fin del metodo capturarMemorandum()");
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

	public void cambiarAPorValidar(Bitacora bq) {
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
	}

	public void cambiarATraslado(Bitacora bq) {
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
	}

	public void cambiarACierreClinico(Bitacora bq) {
		if (validarCierreClinico()) {
			inhabilitarEstado();
			bq.setEstatus(EnumEstadoSolicitud.CIERRECLINICO.getEstado());
			bq.setUsuario(usuarioActivo());
			bq.setObservacionCierreClinico(txtObservacionCierreClinico
					.getValue());
			bq.setEpicrisis(txtEpicrisis.getValue());
			bq.setMotivoCierreClinico(cmbMotivoCierreClinico.getValue());
			if (media != null) {
				bq.setEpicrisisInforme(guardarEpicrisis(bq));
			}
			servicioBitacora.guardar(bq);
			cancelar();
			Executions.sendRedirect(null);
		} else {
			Messagebox.show(Constantes.mensajeCamposVacios, "Advertencia",
					Messagebox.OK, Messagebox.EXCLAMATION);
		}
	}

	public void cambiarARecepcionCuenta(Bitacora bq) {
		if (validarRecepcionCuenta()) {
			inhabilitarEstado();
			bq.setEstatus(EnumEstadoSolicitud.RECEPCIONCUENTA.getEstado());
			bq.setCuenta(txtRecepcionCuenta.getValue());
			bq.setUsuario(usuarioActivo());
			bq.setNumeroFactura(txtNumeroFactura.getValue());
			Date fecha = dtbFechaRecepcion.getValue();
			Timestamp fechaRecepcion = new Timestamp(fecha.getTime());
			bq.setFechaRecepcion(fechaRecepcion);
			Date fechaIngreso = dtbFechaIngreso.getValue();
			Timestamp fechaIng = new Timestamp(fechaIngreso.getTime());
			Date fechaEgreso = dtbFechaEgreso.getValue();
			Timestamp fechaEgr = new Timestamp(fechaEgreso.getTime());
			bq.setFechaIngresoPaciente(fechaIng);
			bq.setFechaEgresoPaciente(fechaEgr);
			bq.setMontoCobrado(dsnpMontoTotalCobrado.getValue());
			bq.setTipoCuenta(cmbTipoCuenta.getValue());
			servicioBitacora.guardar(bq);
			cancelar();
			Executions.sendRedirect(null);
		} else {
			Messagebox.show(Constantes.mensajeCamposVacios, "Advertencia",
					Messagebox.OK, Messagebox.EXCLAMATION);
		}
	}

	public void cambiarAVisacion(Bitacora bq) {
		if (validarVisacion()) {
			inhabilitarEstado();
			bq.setEstatus(EnumEstadoSolicitud.VISACION.getEstado());
			bq.setUsuario(usuarioActivo());
			Date fecha = dtbFechaIngresoCuenta.getValue();
			Timestamp fechaIngresoCuenta = new Timestamp(fecha.getTime());
			bq.setFechaIngresoCuenta(fechaIngresoCuenta);
			bq.setObservacionVisacion(txtObservacionVisacion.getValue());
			bq.setMontoDescuento(dspnMontoDescuento.getValue());
			bq.setTotalDiasCamaUci(spnDiasCamaUci.getValue());
			bq.setTotalDiasCamaUti(spnDiasCamaUti.getValue());
			bq.setTotalDiasCamaBasica(spnDiasCamaBasica.getValue());
			bq.setMontoPago(dspnMontoPago.getValue());
			bq.setPrestacionesAdicionales(dspnPrestacionesAdicionales
					.getValue());
			guardarArchivosServidor(bq);
			bq.setRutaActaAuditoria(rutaAuditoria);
			bq.setRutaActaValorizacion(rutaValorizacion);
			bq.setRutaMemorandum(rutaMemorandum);
			servicioBitacora.guardar(bq);

			cancelar();
			Executions.sendRedirect(null);
		} else {
			Messagebox.show(Constantes.mensajeCamposVacios, "Advertencia",
					Messagebox.OK, Messagebox.EXCLAMATION);
		}
	}

	public String crearDirectorio(Bitacora bq) {
		String archivo = "solicitud_traslado_" + bq.getTraslado().getId();
		File directorio = new File(
				"C:\\glassfish4\\glassfish\\domains\\domain1\\eclipseApps\\CartaRespaldo\\public\\documentos\\"
						+ archivo + "\\");
		if (!directorio.exists()) {
			directorio.mkdirs();
		}
		return archivo;
	}

	public void cambiarACierreAdministrativo(Bitacora bq) {
		if (validarCierreAdministrativo()) {
			inhabilitarEstado();
			bq.setEstatus(EnumEstadoSolicitud.CIERREADMINISTRATIVO.getEstado());
			bq.setUsuario(usuarioActivo());
			bq.setIdSigfe(txtidSigfe.getValue());
			bq.setOrdenCompra(txtOrdenCompra.getValue());
			bq.setResolucion(guardarResolucion(bq));
			servicioBitacora.guardar(bq);
			cancelar();
			Executions.sendRedirect(null);
		} else {
			Messagebox.show(Constantes.mensajeCamposVacios, "Advertencia",
					Messagebox.OK, Messagebox.EXCLAMATION);
		}
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
			cambiarAPorValidar(bq);

		} else if (bitacora.getEstatus().equals(
				EnumEstadoSolicitud.PORVALIDAR.getEstado())) {
			cambiarATraslado(bq);

		} else if (bitacora.getEstatus().equals(
				EnumEstadoSolicitud.TRASLADO.getEstado())) {
			cambiarACierreClinico(bq);

		} else if (bitacora.getEstatus().equals(
				EnumEstadoSolicitud.CIERRECLINICO.getEstado())) {
			cambiarARecepcionCuenta(bq);
		} else if (bitacora.getEstatus().equals(
				EnumEstadoSolicitud.RECEPCIONCUENTA.getEstado())) {
			cambiarAVisacion(bq);
		} else if (bitacora.getEstatus().equals(
				EnumEstadoSolicitud.VISACION.getEstado())) {
			cambiarACierreAdministrativo(bq);
		}
		log.info("Fin del metodo cambiarEstado()");
	}

	@Listen("onClick = #btnCancelar")
	public void cancelar() {
		wdwEstadoTraslado.onClose();
	}

	/**
	 * rechazarCaso: Metodo que cambia el estado de una solicitud a anulado y
	 * guarda los valores necesarios de ese estado.
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
			bq.setUsuario(usuarioActivo());
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
				|| cmbMotivoCierreClinico.getValue().equals("")) {
			return false;
		} else if (txtEpicrisis.getValue().equals("") || media == null) {
			return true;
		} else {
			return false;
		}
	}

	public boolean validarCierreAdministrativo() {
		if (txtidSigfe.getValue().equals("") || media == null
				|| txtOrdenCompra.getValue().equals("")) {
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

	public boolean validarRecepcionCuenta() {
		if (txtRecepcionCuenta.getValue().equals("")
				|| txtNumeroFactura.getValue().equals("")
				|| dtbFechaRecepcion.getValue() == null
				|| dtbFechaIngreso.getValue() == null
				|| dtbFechaEgreso.getValue() == null
				|| cmbTipoCuenta.getValue().equals("")
				|| dsnpMontoTotalCobrado.getValue() == 0) {
			return false;
		} else {
			return true;
		}
	}

	public boolean validarVisacion() {
		if (dtbFechaIngresoCuenta.getValue() == null
				|| dspnMontoPago.getValue() == 0 || mediaActaAuditoria == null
				|| mediaActaValorizacion == null || mediaMemorandum == null) {
			return false;
		} else {
			return true;
		}
	}

	public void llenarComboMotivoCierreClinico() {
		ArrayList<String> listaMotivo = new ArrayList<String>();
		listaMotivo.add(EnumMotivoCierreClinico.ALTACLINICA.getMotivo());
		listaMotivo.add(EnumMotivoCierreClinico.DERIVACIONOTROCENTRO
				.getMotivo());
		listaMotivo.add(EnumMotivoCierreClinico.FALLECIMIENTO.getMotivo());
		listaMotivo.add(EnumMotivoCierreClinico.RESCATE.getMotivo());
		cmbMotivoCierreClinico.setModel(new ListModelList<String>(listaMotivo));
	}

	public void guardarArchivosServidor(Bitacora bitacora) {

		try {

			String ruta = crearDirectorio(bitacora);
			rutaAuditoria = System.getProperty("com.sun.aas.instanceRoot")
					+ "\\eclipseApps\\CartaRespaldo\\public\\documentos\\"
					+ ruta + "\\" + bitacora.getTraslado().getId()
					+ "Acta_Auditoria" + formatoFecha.format(fechaHora)
					+ ".pdf";
			rutaValorizacion = System.getProperty("com.sun.aas.instanceRoot")
					+ "\\eclipseApps\\CartaRespaldo\\public\\documentos\\"
					+ ruta + "\\" + bitacora.getTraslado().getId()
					+ "Acta_Valorizacion" + formatoFecha.format(fechaHora)
					+ ".pdf";
			rutaMemorandum = System.getProperty("com.sun.aas.instanceRoot")
					+ "\\eclipseApps\\CartaRespaldo\\public\\documentos\\"
					+ ruta + "\\" + bitacora.getTraslado().getId()
					+ "Memorandum" + formatoFecha.format(fechaHora) + ".pdf";
			FileOutputStream fileOuputStreamAuditoria = new FileOutputStream(
					rutaAuditoria);
			FileOutputStream fileOuputStreamValorizacion = new FileOutputStream(
					rutaValorizacion);
			FileOutputStream fileOuputStreamMemorandum = new FileOutputStream(
					rutaMemorandum);
			fileOuputStreamAuditoria.write(mediaActaAuditoria.getByteData());
			fileOuputStreamValorizacion.write(mediaActaValorizacion
					.getByteData());
			fileOuputStreamMemorandum.write(mediaMemorandum.getByteData());
			fileOuputStreamAuditoria.close();
			fileOuputStreamValorizacion.close();
			fileOuputStreamMemorandum.close();

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public String guardarResolucion(Bitacora bitacora) {
		String rutaResolucion = "";
		try {
			rutaResolucion = System.getProperty("com.sun.aas.instanceRoot")
					+ "\\eclipseApps\\CartaRespaldo\\public\\documentos\\solicitud_traslado_"
					+ bitacora.getTraslado().getId() + "\\"
					+ bitacora.getTraslado().getId() + "Resolucion"
					+ formatoFecha.format(fechaHora) + ".pdf";
			FileOutputStream fileOuputStreamResolucion = new FileOutputStream(
					rutaResolucion);
			fileOuputStreamResolucion.write(media.getByteData());
		} catch (IOException e) {
			e.printStackTrace();
		}
		return rutaResolucion;

	}

	public String guardarEpicrisis(Bitacora bitacora) {
		String ruta = crearDirectorio(bitacora);
		String rutaEpicrisis = "";
		try {
			rutaEpicrisis = System.getProperty("com.sun.aas.instanceRoot")
					+ "\\eclipseApps\\CartaRespaldo\\public\\documentos\\"
					+ ruta + "\\" + bitacora.getTraslado().getId()
					+ "Informe_Epicrisis" + formatoFecha.format(fechaHora)
					+ ".pdf";
			FileOutputStream fileOuputStreamEpicrisis = new FileOutputStream(
					rutaEpicrisis);
			fileOuputStreamEpicrisis.write(media.getByteData());
		} catch (IOException e) {
			e.printStackTrace();
		}
		return rutaEpicrisis;

	}

	public void verModalRestriccion(String ruta) {
		final HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("ruta", ruta);
		Sessions.getCurrent().setAttribute("itemsCatalogo", map);
		wdwModalDocumentos = (Window) Executions.createComponents(
				"public/vistas/transacciones/modal-documentos.zul", null, map);
		wdwModalDocumentos.doModal();
	}

	@Listen("onClick = #btnVerActaValorizacion")
	public void verActaValorizacion() {
		Bitacora sextoEstado = servicioBitacora.buscarTrasladooEstado(bitacora
				.getTraslado().getId(), EnumEstadoSolicitud.VISACION
				.getEstado());
		verModalRestriccion(sextoEstado.getRutaActaValorizacion());
	}

	@Listen("onClick = #btnVerActaAuditoria")
	public void verActaAuditoria() {
		Bitacora sextoEstado = servicioBitacora.buscarTrasladooEstado(bitacora
				.getTraslado().getId(), EnumEstadoSolicitud.VISACION
				.getEstado());
		verModalRestriccion(sextoEstado.getRutaActaAuditoria());
	}

	@Listen("onClick = #btnVerMemorandum")
	public void verMemoramdum() {
		Bitacora sextoEstado = servicioBitacora.buscarTrasladooEstado(bitacora
				.getTraslado().getId(), EnumEstadoSolicitud.VISACION
				.getEstado());
		verModalRestriccion(sextoEstado.getRutaMemorandum());
	}

	@Listen("onClick = #btnVerResolucion")
	public void verResolucion() {
		Bitacora septimoEstado = servicioBitacora.buscarTrasladooEstado(
				bitacora.getTraslado().getId(),
				EnumEstadoSolicitud.CIERREADMINISTRATIVO.getEstado());
		verModalRestriccion(septimoEstado.getResolucion());
	}

	@Listen("onClick = #btnVerInformeEpicrisis")
	public void verEpicrisis() {
		Bitacora quintoEstado = servicioBitacora.buscarTrasladooEstado(
				bitacora.getTraslado().getId(),
				EnumEstadoSolicitud.CIERRECLINICO.getEstado());
		verModalRestriccion(quintoEstado.getEpicrisisInforme());
	}
}
