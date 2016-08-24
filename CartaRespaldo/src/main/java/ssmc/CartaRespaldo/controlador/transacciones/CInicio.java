package ssmc.CartaRespaldo.controlador.transacciones;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zul.Image;
import org.zkoss.zul.Include;
import org.zkoss.zul.Label;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Listitem;
import org.zkoss.zul.West;

import ssmc.CartaRespaldo.componentes.Constantes;
import ssmc.CartaRespaldo.controlador.maestros.CGenerico;
import ssmc.CartaRespaldo.modelo.seguridad.OpcionMenu;
import ssmc.CartaRespaldo.modelo.seguridad.Usuario;

public class CInicio extends CGenerico {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final static Logger log = Logger
			.getLogger(ssmc.CartaRespaldo.controlador.transacciones.CInicio.class);
	@Wire
	private Listbox lbxMenu;
	@Wire
	private Listbox lbxHijos;
	@Wire
	private Include contenido;
	@Wire
	private Label lblUsuario;
	@Wire
	private Image imgEncabezado;

	List<OpcionMenu> listaHijos2 = new ArrayList<OpcionMenu>();
	HttpServletResponse response;
	@Wire
	private West westAplicacion;
	String ruta = null;

	Usuario usuario = new Usuario();

	@Override
	public void inicializar() throws IOException {
		log.info("Metodo inicializar()");
		Authentication auth = SecurityContextHolder.getContext()
				.getAuthentication();

		if (auth.getPrincipal().equals("anonymousUser")) {
			Executions.sendRedirect("/index.zul");
			return;
		} else {
			usuario = usuarioActivo();
			lblUsuario.setValue(usuario.getNombre());
			if (usuario.isCambiada()) {
				List<OpcionMenu> listaM = new ArrayList<OpcionMenu>();
				listaM = servicioMenu
						.buscarOpcionMenuUsuario(auth.getName(), 0);
				lbxMenu.setModel(new ListModelList<OpcionMenu>(listaM));
				log.debug(new StringBuilder()
						.append("Ingresando al sistema el usuario:")
						.append(usuario.toString()).append(",con menu:")
						.append(listaM));
			} else {
				String ruta = Constantes.rutaCambioPassword;
				contenido.setSrc(ruta);
				log.info("Ingreso inicial del usuario");
			}

		}
		encabezadoEstablecimiento();
		log.info("Fin del metodo inicializar()");

	}

	@Listen("onClick = #lblUsuario")
	public void ocultarHijos() {
		log.info("Inicio del metodo ocultarHijos()");
		lbxHijos.setVisible(false);
		westAplicacion.setVisible(false);
		westAplicacion.setSize("0%");
		log.info("Fin del metodo ocultarHijos()");
	}

	/**
	 * seleccionarOpcion: recupera item seleccionado en el menu de principal de
	 * opciones y muestra la vista correspondiente
	 * 
	 * @param no
	 *            recibe ningun parametro
	 * @return no retorna ningun parametro
	 * 
	 * @throws dispara
	 *             una excepcion JSONException
	 * 
	 */
	@Listen("onClick = #lbxMenu")
	public void seleccionarOpcion() {
		log.info("Inicio del metodo seleccionarOpcion()");
		boolean abrir = false;
		OpcionMenu menuItem = new OpcionMenu();
		List<Listitem> listItem = lbxMenu.getItems();
		if (listItem.size() != 0) {
			for (int i = 0; i < listItem.size(); i++) {
				if (listItem.get(i).isSelected()) {
					OpcionMenu menu = listItem.get(i).getValue();

					int item = Integer.valueOf(menu.getIdMenu());
					abrir = true;
					menuItem = servicioMenu.buscarId(item);
					log.debug(new StringBuilder().append(
							"Opcion de menu seleccionada:").append(
							menuItem.toString()));

				}
			}
			if (abrir) {
				if (menuItem.getUrl() == null) {
					listaHijos2 = servicioMenu
							.buscarHijos(menuItem.getIdMenu());
					lbxHijos.setModel(new ListModelList<OpcionMenu>(listaHijos2));
					lbxHijos.setVisible(true);
					westAplicacion.setVisible(true);
					westAplicacion.setSize("15%");
					contenido.setSrc(null);
				} else {
					if (ruta == null) {
						ruta = "/public/vistas/" + menuItem.getUrl() + ".zul";
						contenido.setSrc(ruta);
						log.debug(new StringBuilder().append("Abrir opcion: ")
								.append(ruta));
					} else {
						contenido.setSrc(null);
						ruta = "/public/vistas/" + menuItem.getUrl() + ".zul";
						contenido.setSrc(ruta);
						log.debug(new StringBuilder().append("Abrir opcion: ")
								.append(ruta));
					}
					lbxHijos.setVisible(false);
					westAplicacion.setVisible(false);
					westAplicacion.setSize("0%");
				}
			}
		}
		log.info("Fin del metodo seleccionarOpcion()");

	}

	/**
	 * seleccionarOpcionHija: recupera item seleccionado en el menu secundario
	 * de sistema y clinico de opciones y muestra la vista correspondiente
	 * 
	 * @param no
	 *            recibe ningun parametro
	 * @return no retorna ningun parametro
	 * 
	 * @throws dispara
	 *             una excepcion JSONException
	 * 
	 */
	@Listen("onClick = #lbxHijos")
	public void seleccionarOpcionHija() {
		log.info("Inicio del metodo seleccionarOpcionHija()");
		boolean abrir = false;
		OpcionMenu menuItem = new OpcionMenu();
		List<Listitem> listItem = lbxHijos.getItems();
		if (listItem.size() != 0) {
			for (int i = 0; i < listItem.size(); i++) {
				if (listItem.get(i).isSelected()) {
					OpcionMenu menu = listItem.get(i).getValue();
					log.debug(new StringBuilder().append(
							"Opcion de menu seleccionada:").append(
							menu.toString()));
					int item = Integer.valueOf(menu.getIdMenu());
					abrir = true;
					menuItem = servicioMenu.buscarId(item);
				}
			}
			if (abrir) {
				String ruta = "/public/vistas/" + menuItem.getUrl() + ".zul";
				contenido.setSrc(ruta);
				log.debug(new StringBuilder().append("Abrir opcion: ").append(
						ruta));
			}
		}
		log.info("Fin del metodo seleccionarOpcionHija()");
	}

	@Listen("onClick = #lblUsuario")
	public void abrirCambioClave() {
		contenido.setSrc(null);
		ruta = "/public/vistas/seguridad/cambiar-password.zul";
		contenido.setSrc(ruta);
		log.debug(new StringBuilder().append("Abrir opcion: ").append(ruta));
	}

	public void encabezadoEstablecimiento() {
		if (usuario.getEstablecimiento().getId() == 1) {
			imgEncabezado.setSrc("public/imagenes/generales/BannerExtraSistema-HCSBA.png");
		}
		else if (usuario.getEstablecimiento().getId() == 2) {
			imgEncabezado.setSrc("public/imagenes/generales/BannerExtraSistema-HEC.png");
		}
		else if (usuario.getEstablecimiento().getId() == 3) {
			imgEncabezado.setSrc("public/imagenes/generales/BannerExtraSistema-HUAP.png");
		}
		else if (usuario.getEstablecimiento().getId() == 4) {
			imgEncabezado.setSrc("public/imagenes/generales/EncabezadoSsmc.png");
		}
	}

}