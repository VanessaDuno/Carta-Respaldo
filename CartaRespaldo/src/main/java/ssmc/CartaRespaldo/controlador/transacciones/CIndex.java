package ssmc.CartaRespaldo.controlador.transacciones;

import java.io.IOException;

import org.apache.log4j.Logger;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zul.Button;
import org.zkoss.zul.Div;
import org.zkoss.zul.Window;

import ssmc.CartaRespaldo.componentes.Constantes;
import ssmc.CartaRespaldo.controlador.maestros.CGenerico;

public class CIndex extends CGenerico {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final static Logger log = Logger
			.getLogger(ssmc.CartaRespaldo.controlador.transacciones.CIndex.class);
	
	@Wire
	private Button btnIngresar;
	@Wire
	private Div mensaje;

	@Override
	public void inicializar() throws IOException {

	}

	@Listen("onClick = #btnIngresar")
	public void error() {
		mensaje.setStyle("display:block");
	}

	@Listen("onClick = #lblOlvidoClave")
	public void abrirVentana() {
		log.info("Inicio del metodo abrirVentana()");
		Window window = (Window) Executions.createComponents(
				Constantes.rutaReinicioPassword, null, null);
		window.setPosition("center");
		window.doModal();
		log.debug(new StringBuilder().append("Fin del metodo abrirVentana(), con ruta:").append(Constantes.rutaReinicioPassword));
	}

}