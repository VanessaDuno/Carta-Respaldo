package ssmc.CartaRespaldo.controlador.seguridad;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zul.Combobox;
import org.zkoss.zul.Div;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Listitem;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Window;

import ssmc.CartaRespaldo.componentes.Botonera;
import ssmc.CartaRespaldo.componentes.Catalogo;
import ssmc.CartaRespaldo.componentes.Constantes;
import ssmc.CartaRespaldo.controlador.maestros.CGenerico;
import ssmc.CartaRespaldo.modelo.seguridad.Grupo;
import ssmc.CartaRespaldo.modelo.seguridad.MenuGrupo;
import ssmc.CartaRespaldo.modelo.seguridad.OpcionMenu;
import ssmc.CartaRespaldo.modelo.seguridad.UsuarioGrupo;

public class CMenu extends CGenerico {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final static Logger log = Logger
			.getLogger(ssmc.CartaRespaldo.controlador.seguridad.CMenu.class);
	@Wire
	private Window wdwMenu;
	@Wire
	private Div botoneraMenu;
	@Wire
	private Listbox ltbGruposDisponibles;
	@Wire
	private Listbox ltbGruposAgregados;
	@Wire
	private Combobox cmbOpcionPadre;
	@Wire
	private Textbox txtNombreMenu;
	@Wire
	private Textbox txtUrlMenu;
	Botonera botonera;
	private List<Grupo> grupos = new ArrayList<Grupo>();
	private List<MenuGrupo> gruposMenu = new ArrayList<MenuGrupo>();
	private List<OpcionMenu> opcionesMenu = new ArrayList<OpcionMenu>();

	@Wire
	private Div catalogoMenu;
	Catalogo<OpcionMenu> catalogo;
	OpcionMenu menu = new OpcionMenu();
	boolean editar = false;
	int id = 0;

	@Override
	public void inicializar() throws IOException {
		log.info("Metodo inicializar()");
		llenarListas();
		mostrarCatalogo();
		botonera = new Botonera() {


			@Override
			public void guardar() {
				guardarRegistros();
			}

			@Override
			public void limpiar() {
				limpiarCampos();
			}

			@Override
			public void salir() {

			}

			@Override
			public void eliminar() {
			}


		};
		botonera.getChildren().get(0).setVisible(true);
		botonera.getChildren().get(2).setVisible(true);
		botonera.getChildren().get(3).setVisible(true);
		botonera.getChildren().get(1).setVisible(false);
		botoneraMenu.appendChild(botonera);
		log.info("Fin del metodo inicializar()");

	}

	public void limpiarCampos() {
		log.info("Inicio del metodo limpiarCampos()");
		txtNombreMenu.setValue("");
		txtUrlMenu.setValue("");
		cmbOpcionPadre.setValue("");
		List<UsuarioGrupo> usaurioGrupo = new ArrayList<UsuarioGrupo>();
		ltbGruposAgregados.setModel(new ListModelList<UsuarioGrupo>(
				usaurioGrupo));
		llenarListas();
		log.info("Fin del metodo limpiarCampos()");
	}

	public void llenarListas() {
		log.info("Inicio del metodo llenarListas()");
		grupos = servicioGrupo.buscarTodos(true);
		ltbGruposDisponibles.setModel(new ListModelList<Grupo>(grupos));
		opcionesMenu = servicioMenu.buscarHijos(0);
		OpcionMenu opcion = new OpcionMenu();
		opcion.setIdMenu(0);
		opcion.setNombre("Ninguno");
		opcionesMenu.add(opcion);
		cmbOpcionPadre.setModel(new ListModelList<OpcionMenu>(opcionesMenu));
		log.debug(new StringBuilder()
				.append("Fin del metodo llenarListas() con listas,")
				.append(opcionesMenu).append(grupos));

	}

	/**
	 * agregarGrupo: agrega en una lista de grupos de usuario un grupo
	 * seleccionado.
	 * 
	 * @param No
	 *            recibe ningun parametro
	 * @return No retorna ningun objeto ni dato
	 * 
	 * @throws No
	 *             dispara ninguna excepción.
	 * 
	 */
	@Listen("onClick = #btnAgregarGrupo")
	public void agregarGrupo() {
		log.info("Inicio del metodo agregarGrupo()");
		List<Listitem> listItem = ltbGruposDisponibles.getItems();
		boolean selecciono = false;
		if (listItem.size() != 0) {
			for (int i = 0; i < listItem.size(); i++) {
				if (listItem.get(i).isSelected()) {
					selecciono = true;
					Grupo grupo = listItem.get(i).getValue();
					log.debug(new StringBuilder().append("Grupo seleccionado:")
							.append(grupo.toString()));
					MenuGrupo menuGrupo = new MenuGrupo();
					menuGrupo.setGrupo(grupo);
					gruposMenu.add(menuGrupo);
					grupos.remove(grupo);
					ltbGruposDisponibles.setModel(new ListModelList<Grupo>(
							grupos));
				}
			}
		}
		if (selecciono) {
			ltbGruposAgregados
					.setModel(new ListModelList<MenuGrupo>(gruposMenu));
		} else {
			Messagebox.show(Constantes.mensajeSeleccionarRegistro, "Alerta",
					Messagebox.OK, Messagebox.EXCLAMATION);
			log.info("No selecciono registro");
		}
		log.info("Inicio del metodo agregarGrupo()");
	}

	/**
	 * quitarGrupo: elimina de la lista de grupos de usaurio un grupo agregado
	 * 
	 * @param No
	 *            recibe ningun parametro
	 * @return No retorna ningun objeto ni dato
	 * 
	 * @throws No
	 *             dispara ninguna excepción.
	 * 
	 */
	@Listen("onClick = #btnQuitarGrupo")
	public void quitarGrupo() {
		log.info("Inicio del metodo quitarGrupo()");
		List<Listitem> listItem = ltbGruposAgregados.getItems();
		if (listItem.size() != 0) {
			for (int i = 0; i < listItem.size(); i++) {
				if (listItem.get(i).isSelected()) {
					MenuGrupo menuGrupo = listItem.get(i).getValue();
					log.debug(new StringBuilder().append("Quitando menu:")
							.append(menuGrupo.toString()));
					gruposMenu.remove(menuGrupo);
					MenuGrupo respuesta = new MenuGrupo();
					if (id != 0) {
						respuesta = servicioMenuGrupo.buscarGrupoMenu(id,
								menuGrupo.getGrupo().getId());
						if (respuesta != null) {
							servicioMenuGrupo.eliminarGrupo(menuGrupo.getId());
						}
					}
					grupos.add(menuGrupo.getGrupo());
				}
			}
			ltbGruposDisponibles.setModel(new ListModelList<Grupo>(grupos));
			ltbGruposAgregados
					.setModel(new ListModelList<MenuGrupo>(gruposMenu));
		}
		log.info("Fin del metodo quitarGrupo()");
	}

	public void guardarRegistros() {
		log.info("Inicio del metodo guardarRegistros()");
		if (txtNombreMenu.getValue().equals("")
				|| cmbOpcionPadre.getValue().equals("")
				|| ltbGruposAgregados.getItemCount() == 0) {
			Messagebox.show(Constantes.mensajeCamposVacios, "Alerta",
					Messagebox.OK, Messagebox.EXCLAMATION);
			log.info("Fin del metodo guardarRegistros(), campos vacios");
		} else {
			if (editar) {
				menu.setNombre(txtNombreMenu.getValue());
				menu.setIdPadre(Integer.valueOf(cmbOpcionPadre
						.getSelectedItem().getContext()));
				if (!txtUrlMenu.getValue().equals("")) {
					menu.setUrl(txtUrlMenu.getValue());
				} else {
					menu.setUrl(null);
				}
				OpcionMenu menuEditado = servicioMenu.guardar(menu);
				List<Listitem> listItem = ltbGruposAgregados.getItems();
				if (listItem.size() != 0) {
					for (int i = 0; i < listItem.size(); i++) {
						MenuGrupo menus = listItem.get(i).getValue();
						menus.setOpionMenu(menuEditado);
						menus.setGrupo(menus.getGrupo());
						servicioMenuGrupo.guardar(menus);
					}
					log.debug(new StringBuilder().append("Editando menu:")
							.append(menu));
				}
			} else {
				OpcionMenu menu = new OpcionMenu();
				menu.setEstado(1);
				menu.setIdPadre(Integer.valueOf(cmbOpcionPadre
						.getSelectedItem().getContext()));
				menu.setNombre(txtNombreMenu.getValue());
				if (!txtUrlMenu.getValue().equals("")) {
					menu.setUrl(txtUrlMenu.getValue());
				} else {
					menu.setUrl(null);
				}
				OpcionMenu menuGuardado = servicioMenu.guardar(menu);

				MenuGrupo menuGrupo = new MenuGrupo();
				List<Listitem> listItem = ltbGruposAgregados.getItems();
				if (listItem.size() != 0) {
					for (int i = 0; i < listItem.size(); i++) {
						MenuGrupo grupos = listItem.get(i).getValue();
						menuGrupo.setOpionMenu(menuGuardado);
						menuGrupo.setGrupo(grupos.getGrupo());
						servicioMenuGrupo.guardar(menuGrupo);
					}
				}
				log.debug(new StringBuilder().append("Guardando menu:").append(
						menuGuardado));
			}
			Messagebox.show(Constantes.mensajeRegistroGuardado, "Información",
					Messagebox.OK, Messagebox.INFORMATION);
			limpiarCampos();
			catalogo.actualizarLista(servicioMenu.buscarTodos(1));
		}
		log.info("Fin del metodo guardarRegistros()");
	}

	public void mostrarCatalogo() {
		log.info("Inicio del metodo mostrarCatalogo() ");
		final List<OpcionMenu> opciones = servicioMenu.buscarTodos(1);
		catalogo = new Catalogo<OpcionMenu>(catalogoMenu, "Catalogo de Menú",
				opciones, "Nombre", "Url") {

			@Override
			protected List<OpcionMenu> buscar(List<String> valores) {
				List<OpcionMenu> lista = new ArrayList<OpcionMenu>();

				for (OpcionMenu opcion : opciones) {
					if (opcion.getUrl() == null) {
						opcion.setUrl("");
					}
					if (opcion.getNombre().toLowerCase()
							.contains(valores.get(0).toLowerCase())
							&& opcion.getUrl().toLowerCase()
									.contains(valores.get(1).toLowerCase())) {
						lista.add(opcion);

					}
				}
				return lista;

			}

			@Override
			protected String[] crearRegistros(OpcionMenu opcion) {
				String[] registros = new String[2];
				registros[0] = opcion.getNombre();
				registros[1] = opcion.getUrl();
				log.debug(new StringBuilder().append("Lista retornada:")
						.append(registros));
				return registros;
			}
		};
		catalogo.setParent(catalogoMenu);
		log.info("Fin del metodo mostrarCatalogo() ");
	}

	@Listen("onSeleccion = #catalogoMenu")
	public void seleccinar() {
		log.info("Inicio del metodo seleccinar()");
		limpiarCampos();
		menu = catalogo.objetoSeleccionadoDelCatalogo();
		llenarCampos(menu);
		editar = true;
		log.info("Fin del metodo seleccinar()");
	}

	private void llenarCampos(OpcionMenu menu) {
		log.debug(new StringBuilder().append(
				"Inicio del metodo llenarCampos(), con objeto").append(
				menu.toString()));
		txtNombreMenu.setValue(menu.getNombre());
		txtUrlMenu.setValue(menu.getUrl());
		if (menu.getIdPadre() != 0) {
			cmbOpcionPadre.setValue(servicioMenu.buscarId(menu.getIdPadre())
					.getNombre());
		} else {
			cmbOpcionPadre.setValue("Ninguno");
		}
		id = menu.getIdMenu();
		gruposMenu = servicioMenuGrupo.buscarMenuGrupo(menu.getNombre());
		ltbGruposAgregados.setModel(new ListModelList<MenuGrupo>(gruposMenu));

		
		eliminarRepetidos();
		log.info("Fin del metodo llenarCampos");

	}

	public void eliminarRepetidos() {
		log.info("Inicio del metodo eliminarRepetidos()");
		for (int i = 0; i < gruposMenu.size(); i++) {
			for (int j = 0; j < grupos.size(); j++) {
				if (gruposMenu.get(i).getGrupo().getNombre()
						.equals(grupos.get(j).getNombre())) {
					grupos.remove(grupos.get(j));
				}
			}
		}
		ltbGruposDisponibles.setModel(new ListModelList<Grupo>(grupos));
		log.info("Fin del metodo eliminarRepetidos()");
	}
}
