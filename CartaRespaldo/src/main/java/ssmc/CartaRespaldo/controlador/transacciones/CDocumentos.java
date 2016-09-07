/**
 * 
 */
package ssmc.CartaRespaldo.controlador.transacciones;

import java.io.IOException;
import java.util.HashMap;

import org.zkoss.zk.ui.Sessions;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zul.Iframe;

import ssmc.CartaRespaldo.controlador.maestros.CGenerico;

/**
 * @author Vanessa Maria Duno
 *
 */
public class CDocumentos extends CGenerico{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	String ruta = ""; 
	@Wire
	private Iframe ifmDocumento; 

	@Override
	public void inicializar() throws IOException {
		HashMap<String, Object> map = (HashMap<String, Object>) Sessions
				.getCurrent().getAttribute("itemsCatalogo");
		if (map != null) {
			ruta = (String) map.get("ruta");
			ruta = ruta.replace("\\", "/");
			int posicion = ruta.indexOf("/public");
			ruta = ruta.substring(posicion, ruta.length());
			ifmDocumento.setSrc(ruta);
		}
	}

}
