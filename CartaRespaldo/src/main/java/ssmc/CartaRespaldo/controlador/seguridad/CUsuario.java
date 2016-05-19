package ssmc.CartaRespaldo.controlador.seguridad;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zul.Button;
import org.zkoss.zul.Combobox;
import org.zkoss.zul.Div;
import org.zkoss.zul.Label;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Listitem;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Textbox;

import ssmc.CartaRespaldo.componentes.Botonera;
import ssmc.CartaRespaldo.componentes.Catalogo;
import ssmc.CartaRespaldo.componentes.Constantes;
import ssmc.CartaRespaldo.controlador.maestros.CGenerico;
import ssmc.CartaRespaldo.modelo.maestros.Establecimiento;
import ssmc.CartaRespaldo.modelo.maestros.Region;
import ssmc.CartaRespaldo.modelo.seguridad.Grupo;
import ssmc.CartaRespaldo.modelo.seguridad.Usuario;
import ssmc.CartaRespaldo.modelo.seguridad.UsuarioGrupo;

public class CUsuario extends CGenerico {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final static Logger log = Logger
			.getLogger(ssmc.CartaRespaldo.controlador.seguridad.CUsuario.class);

	private List<Grupo> grupos = new ArrayList<Grupo>();
	private List<UsuarioGrupo> listaGruposUsuario = new ArrayList<UsuarioGrupo>();
	private List<Region> regiones = new ArrayList<Region>();
	private List<Establecimiento> establecimientos = new ArrayList<>();
	@Wire
	private Combobox cmbEstablecimientoUsuario;
	@Wire
	private Listbox ltbGruposDisponibles;
	@Wire
	private Listbox ltbGruposAgregados;
	@Wire
	private Textbox txtNombreUsuario;
	@Wire
	private Textbox txtEmailUsuario;
	@Wire
	private Textbox txtLoginUsuario;
	@Wire
	private Textbox txtPasswordUsuario;
	@Wire
	private Textbox txtPassword2Usuario;
	@Wire
	private Button btnAgregarGrupo;
	@Wire
	private Button btnQuitarGrupo;
	@Wire
	private Div divIncorrectos;
	@Wire
	private Div botoneraUsuario;
	@Wire
	private Div catalogoUsuarios;
	@Wire
	private Combobox cmbRegion;

	@Wire
	private Label lblErrorClave;
	Botonera botonera;

	Catalogo<Usuario> catalogo;
	Usuario usuario = new Usuario();
	boolean editar = false;
	int id = 0;
	String password = "";

	@Override
	public void inicializar() throws IOException {
		log.info("Metodo inicializar()");
		llenarListas();
		mostrarCatalogo();
		botonera = new Botonera() {


			@Override
			public void salir() {

			}

			@Override
			public void limpiar() {
				limpiarCampos();
			}

			@Override
			public void guardar() {
				guardarRegistro();
			}

			@Override
			public void eliminar() {
				log.info("Inicio del metodo eliminar");
				usuario.setEstado(false);
				servicioUsuario.guardar(usuario);
				log.debug(new StringBuilder().append("Eliminando usuario:")
						.append(usuario.toString()));
				Messagebox.show(Constantes.mensajeEliminadoExitoso,
						"Información", Messagebox.OK, Messagebox.INFORMATION);
				catalogo.actualizarLista(servicioUsuario.buscarTodos(true));
				limpiarCampos();
				log.info("Fin del metodo eliminar");
			}

		};
		botonera.getChildren().get(0).setVisible(true);
		botonera.getChildren().get(2).setVisible(true);
		botonera.getChildren().get(3).setVisible(true);
		botonera.getChildren().get(1).setVisible(false);
		botoneraUsuario.appendChild(botonera);
		log.info("Fin del metodo inicializar()");
	}

	public void llenarListas() {
		log.info("Inicio del metodo llenarListas()");
		grupos = servicioGrupo.buscarTodos(true);
		ltbGruposDisponibles.setModel(new ListModelList<Grupo>(grupos));
		regiones = servicioRegion.buscarTodos();
		cmbRegion.setModel(new ListModelList<Region>(regiones));
		log.debug(new StringBuilder()
				.append("fin del metodo llenarListas(), con listas")
				.append(grupos).append(regiones));
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
					log.info(new StringBuilder().append("Grupo seleccionado:")
							.append(grupo.toString()));
					UsuarioGrupo usuarioGrupo = new UsuarioGrupo();
					usuarioGrupo.setGrupo(grupo);
					listaGruposUsuario.add(usuarioGrupo);
					grupos.remove(grupo);
					ltbGruposDisponibles.setModel(new ListModelList<Grupo>(
							grupos));
				}
			}
		}
		if (selecciono) {
			ltbGruposAgregados.setModel(new ListModelList<UsuarioGrupo>(
					listaGruposUsuario));
		} else {
			Messagebox.show(Constantes.mensajeSeleccionarRegistro, "Alerta",
					Messagebox.OK, Messagebox.EXCLAMATION);
			log.info("Fin del metodo agregarGrupo(), no selecciono registro");
		}
		log.info("Fin del metodo agregarGrupo()");
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
					UsuarioGrupo usuarioGrupo = listItem.get(i).getValue();
					log.debug(new StringBuilder().append("Quitando grupo:")
							.append(usuarioGrupo));
					listaGruposUsuario.remove(usuarioGrupo);
					UsuarioGrupo respuesta = new UsuarioGrupo();
					if (id != 0) {
						respuesta = servicioUsuarioGrupo.buscarUsuarioGrupo(id,
								usuarioGrupo.getGrupo().getId());
						if (respuesta != null) {
							servicioUsuarioGrupo.eliminarGrupo(usuarioGrupo
									.getId());
						}
					}
					grupos.add(usuarioGrupo.getGrupo());
				}
			}
			ltbGruposDisponibles.setModel(new ListModelList<Grupo>(grupos));
			ltbGruposAgregados.setModel(new ListModelList<UsuarioGrupo>(
					listaGruposUsuario));
		}
		log.info("Fin del metodo quitarGrupo()");
	}

	public boolean verificarPassword() {
		log.info("Inicio del metodo verificarPassword()");
		if (txtPasswordUsuario.getValue() == ""
				|| txtPassword2Usuario.getValue() == "") {

		} else {
			if (!txtPasswordUsuario.getValue().equals(
					txtPassword2Usuario.getValue())) {
				divIncorrectos.setVisible(true);
				lblErrorClave.setValue(Constantes.passwordNoCoinciden);
				log.info("Fin del metodo verificarPassword(), password no coinciden");
				return false;
			} else {
				if (completitudClave(txtPasswordUsuario.getValue())) {
					divIncorrectos.setVisible(false);
					log.info("Fin del metodo verificarPassword(), password correctos");
					return true;
				} else {
					divIncorrectos.setVisible(true);
					lblErrorClave.setValue(Constantes.mensajeClave);
					log.info("Fin del metodo verificarPassword(), password no cumple con criterios de completitud");
					return false;
				}
			}
		}
		log.info("Fin del metodo verificarPassword()");
		return false;
	}

	@Listen("onChange = #txtPasswordUsuario")
	public void verificar() {
		verificarPassword();
	}

	@Listen("onChange = #txtPassword2Usuario")
	public void verificarDos() {
		verificarPassword();
	}

	public void guardarRegistro() {
		log.info("Inicio del metodo guardarRegistro()");
		if (txtNombreUsuario.getValue().equals("")
				|| txtLoginUsuario.getValue().equals("")
				|| txtEmailUsuario.getValue().equals("")
				|| txtPassword2Usuario.getValue().equals("")
				|| txtPasswordUsuario.getValue().equals("")
				|| cmbEstablecimientoUsuario.getValue().equals("")
				|| ltbGruposAgregados.getItemCount() == 0) {
			Messagebox.show(Constantes.mensajeCamposVacios, "Alerta",
					Messagebox.OK, Messagebox.EXCLAMATION);
			log.info("Fin del metodo guardarRegistro(), campos vacios");
		} else {
			if (verificarPassword()) {
				BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
				String hashedPassword = passwordEncoder
						.encode(txtPasswordUsuario.getValue());
				if (editar) {
					usuario.setEmail(txtEmailUsuario.getValue());
					if (cmbEstablecimientoUsuario.getChildren().size() != 0) {
						usuario.setEstablecimiento(servicioEstablecimiento.buscarId(Integer
								.valueOf(cmbEstablecimientoUsuario
										.getSelectedItem().getContext())));
					}
					usuario.setNombre(txtNombreUsuario.getValue());
					if (!password.equals(txtPasswordUsuario.getValue())) {
						usuario.setPassword(hashedPassword);
					}
					Usuario usuarioEditado = servicioUsuario.guardar(usuario);
					log.debug(new StringBuilder().append("Editando usuario:")
							.append(usuario.toString()));
					List<Listitem> listItem = ltbGruposAgregados.getItems();
					if (listItem.size() != 0) {
						for (int i = 0; i < listItem.size(); i++) {
							UsuarioGrupo grupos = listItem.get(i).getValue();
							grupos.setUsuario(usuarioEditado);
							grupos.setGrupo(grupos.getGrupo());
							servicioUsuarioGrupo.guardar(grupos);
						}
					}
					Messagebox.show(Constantes.mensajeRegistroGuardado,
							"Información", Messagebox.OK,
							Messagebox.INFORMATION);
					prepararLog(Constantes.edicionUsuario, "Editado el usuario"
							+" "+usuario.getLogin());
					limpiarCampos();
					catalogo.actualizarLista(servicioUsuario.buscarTodos(true));
				}

				else {
					Usuario usuarioLogin = servicioUsuario
							.buscarUsuarioPorNombre(txtLoginUsuario.getValue());
					if (usuarioLogin == null) {
						Usuario usuario = new Usuario();
						usuario.setEmail(txtEmailUsuario.getValue());
						usuario.setEstado(true);
						usuario.setEstablecimiento(servicioEstablecimiento.buscarId(Integer
								.valueOf(cmbEstablecimientoUsuario
										.getSelectedItem().getContext())));
						usuario.setLogin(txtLoginUsuario.getValue());
						usuario.setNombre(txtNombreUsuario.getValue());
						usuario.setCambiada(false);
						usuario.setPassword(hashedPassword);
						Usuario usuarioGuardado = servicioUsuario
								.guardar(usuario);
						log.debug(new StringBuilder().append(
								"Guardando usuario:")
								.append(usuario.toString()));
						UsuarioGrupo usuarioGrupo = new UsuarioGrupo();
						List<Listitem> listItem = ltbGruposAgregados.getItems();
						if (listItem.size() != 0) {
							for (int i = 0; i < listItem.size(); i++) {
								UsuarioGrupo grupos = listItem.get(i)
										.getValue();
								usuarioGrupo.setUsuario(usuarioGuardado);
								usuarioGrupo.setGrupo(grupos.getGrupo());
								servicioUsuarioGrupo.guardar(usuarioGrupo);
							}
						}
						Messagebox.show(Constantes.mensajeRegistroGuardado,
								"Información", Messagebox.OK,
								Messagebox.INFORMATION);
						limpiarCampos();
						prepararLog(Constantes.creacionUsuario, "Creado el usuario"
								+" "+usuario.getLogin());
						catalogo.actualizarLista(servicioUsuario
								.buscarTodos(true));
					} else {
						divIncorrectos.setVisible(true);
						lblErrorClave.setValue(Constantes.mensajeLoginExiste);
					}
				}

			}
		}
		log.info("Fin del metodo guardarRegistro()");
	}

	public void limpiarCampos() {
		log.info("Inicio del metodo limpiarCampos()");
		txtEmailUsuario.setValue("");
		txtLoginUsuario.setValue("");
		txtNombreUsuario.setValue("");
		txtPassword2Usuario.setValue("");
		txtPasswordUsuario.setValue("");
		cmbEstablecimientoUsuario.setValue("");
		txtLoginUsuario.setDisabled(false);
		List<UsuarioGrupo> usaurioGrupo = new ArrayList<UsuarioGrupo>();
		ltbGruposAgregados.setModel(new ListModelList<UsuarioGrupo>(
				usaurioGrupo));
		llenarListas();
		id = 0;
		editar = false;
		password = "";
		cmbRegion.setValue("");
		divIncorrectos.setVisible(false);
		ocultarBotones();
		log.info("Fin del metodo limpiarCampos()");
	}

	public void mostrarCatalogo() {
		log.info("Inicio del metodo mostrarCatalogo()");
		final List<Usuario> usuarios = servicioUsuario.buscarTodos(true);
		catalogo = new Catalogo<Usuario>(catalogoUsuarios,
				"Catalogo de Usuario", usuarios, "Nombre", "Login", "Email") {

			@Override
			protected List<Usuario> buscar(List<String> valores) {
				List<Usuario> lista = new ArrayList<Usuario>();

				for (Usuario usuario : usuarios) {
					if (usuario.getNombre().toLowerCase()
							.contains(valores.get(0).toLowerCase())
							&& usuario.getLogin().toLowerCase()
									.contains(valores.get(1).toLowerCase())
							&& usuario.getEmail().toLowerCase()
									.contains(valores.get(2).toLowerCase())) {
						lista.add(usuario);

					}
				}
				return lista;

			}

			@Override
			protected String[] crearRegistros(Usuario usuario) {
				String[] registros = new String[3];
				registros[0] = usuario.getNombre();
				registros[1] = usuario.getLogin();
				registros[2] = usuario.getEmail();
				log.debug(new StringBuilder().append("Lista retornada- ")
						.append(registros.toString()));
				return registros;
			}
		};
		catalogo.setParent(catalogoUsuarios);
		log.info("Fin del metodo mostrarCatalogo()");
	}

	@Listen("onSeleccion = #catalogoUsuarios")
	public void seleccinar() {
		log.info("Inicio del metodo seleccinar()");
		usuario = catalogo.objetoSeleccionadoDelCatalogo();
		llenarCampos(usuario);
		editar = true;
		mostrarBotones();
		log.info("Fin del metodo seleccinar()");
	}

	private void llenarCampos(Usuario usuario) {
		log.debug(new StringBuilder().append(
				"Inicio del metodo llenarCampos, con objeto: ").append(usuario));
		txtNombreUsuario.setValue(usuario.getNombre());
		txtLoginUsuario.setValue(usuario.getLogin());
		txtLoginUsuario.setDisabled(true);
		txtEmailUsuario.setValue(usuario.getEmail());
		password = usuario.getPassword();
		txtPasswordUsuario.setValue(usuario.getPassword());
		txtPassword2Usuario.setValue(usuario.getPassword());
		cmbEstablecimientoUsuario.setValue(usuario.getEstablecimiento().getNombre());
		cmbEstablecimientoUsuario.setContext(String.valueOf(usuario
				.getEstablecimiento().getId()));
		id = usuario.getId();
		listaGruposUsuario = servicioUsuarioGrupo.buscarUsuario(usuario
				.getLogin());
		ltbGruposAgregados.setModel(new ListModelList<UsuarioGrupo>(
				listaGruposUsuario));
		eliminarRepetidos();
		log.info("Fin del metodo llenarCampos");

	}

	public void eliminarRepetidos() {
		log.info("Inicio del metodo eliminarRepetidos()");
		for (int i = 0; i < listaGruposUsuario.size(); i++) {
			for (int j = 0; j < grupos.size(); j++) {
				if (listaGruposUsuario.get(i).getGrupo().getNombre()
						.equals(grupos.get(j).getNombre())) {
					grupos.remove(grupos.get(j));
				}
			}
		}
		ltbGruposDisponibles.setModel(new ListModelList<Grupo>(grupos));
		log.info("Fin" + " del metodo eliminarRepetidos()");
	}

	@Listen("onClick = #rdgTipoPublico")
	public void buscarHospitalesRegion() {
		log.info("Inicio del metodo buscarHospitalesRegion");
		cmbEstablecimientoUsuario.setValue("");
		if (cmbRegion.getValue() != null) {
			Integer idRegion = Integer.parseInt(cmbRegion.getSelectedItem()
					.getContext());
			establecimientos = servicioEstablecimiento
					.buscarRegion(idRegion);
			cmbEstablecimientoUsuario.setModel(new ListModelList<Establecimiento>(
					establecimientos));
		}
		log.debug(new StringBuilder().append(
				"Fin del metodo buscarHospitalesRegion(), con lista:").append(
				establecimientos));
	}

	@Listen("onChange = #cmbRegion")
	public void buscarHospitales() {
		buscarHospitalesRegion();
	}

	public void mostrarBotones() {
		botonera.getChildren().get(1).setVisible(true);
		botoneraUsuario.appendChild(botonera);
	}

	public void ocultarBotones() {
		botonera.getChildren().get(1).setVisible(false);
		botoneraUsuario.appendChild(botonera);
	}

}
