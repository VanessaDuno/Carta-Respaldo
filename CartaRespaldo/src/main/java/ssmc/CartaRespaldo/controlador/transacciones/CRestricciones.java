/**
 * 
 */
package ssmc.CartaRespaldo.controlador.transacciones;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Timestamp;
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
 * CRestricciones
 * 
 * Controlador encargado de mostrar las restricciones a pacientes
 * 
 * @author Vanessa Maria Duno
 * @version 1.0
 * 
 */
public class CRestricciones extends CGenerico {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final static Logger log = Logger
			.getLogger(ssmc.CartaRespaldo.controlador.transacciones.CRestricciones.class);

	private Bitacora bitacora = new Bitacora();
	@Wire
	private Label lblPaciente;
	@Wire
	private Label lblRut;
	@Wire
	private Label lblObservacion;
	@Wire
	private Label lblObservacionR;

	@Override
	public void inicializar() throws IOException {
		log.info("Metodo inicializar ()");
		HashMap<String, Object> map = (HashMap<String, Object>) Sessions
				.getCurrent().getAttribute("itemsCatalogo");
		if (map != null) {
			bitacora = (Bitacora) map.get("bitacora");
			lblPaciente
					.setValue(formatearNombre(bitacora.getTraslado()
							.getPaciente().getNombres()
							+ " "
							+ bitacora.getTraslado().getPaciente()
									.getPrimerApellido()));
			lblRut.setValue(bitacora.getTraslado().getPaciente().getRut());
			lblObservacion.setValue(bitacora.getTraslado().getObservacion());
			lblObservacionR.setValue(bitacora.getTraslado().getObservacionRestriccion());
		}
		log.info("Fin del metodo inicializar()");

	}

}
