package ssmc.CartaRespaldo.controlador.seguridad;

import java.io.IOException;

import org.apache.log4j.Logger;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zul.Div;
import org.zkoss.zul.Label;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Textbox;

import ssmc.CartaRespaldo.componentes.Botonera;
import ssmc.CartaRespaldo.componentes.Constantes;
import ssmc.CartaRespaldo.controlador.maestros.CGenerico;
import ssmc.CartaRespaldo.modelo.seguridad.Usuario;

public class CCambiarPassword extends CGenerico {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final static Logger log = Logger
			.getLogger(ssmc.CartaRespaldo.controlador.seguridad.CCambiarPassword.class);

	Botonera botonera;

	@Wire
	private Div botoneraCambiarPassword;
	@Wire
	private Div divCambiarClave;
	@Wire
	private Textbox txtClaveUsuarioNueva;
	@Wire
	private Textbox txtClaveUsuarioConfirmar;
	@Wire
	private Div divIncorrectos;
	@Wire
	private Label lblError;
	Usuario usuario = new Usuario();

	@Override
	public void inicializar() throws IOException {
		log.info("Metodo inicializar()");
		usuario = usuarioActivo();
		botonera = new Botonera() {

			@Override
			public void salir() {
				divCambiarClave.detach();
			}

			@Override
			public void limpiar() {
				limpiarCampos();

			}

			@Override
			public void guardar() {
				guardarRegitro();
			}

			@Override
			public void eliminar() {

			}
		};
		botonera.getChildren().get(0).setVisible(true);
		botonera.getChildren().get(1).setVisible(false);
		botonera.getChildren().get(2).setVisible(true);
		botonera.getChildren().get(3).setVisible(true);
		botoneraCambiarPassword.appendChild(botonera);
		log.info("Fin del metodo inicializar ()");

	}

	public boolean verificarPassword() {
		log.info("Inicio del metodo verificarPassword()");
		if (txtClaveUsuarioNueva.getValue() == ""
				|| txtClaveUsuarioConfirmar.getValue() == "") {

		} else {
			if (!txtClaveUsuarioNueva.getValue().equals(
					txtClaveUsuarioConfirmar.getValue())) {
				divIncorrectos.setVisible(true);
				lblError.setValue(Constantes.mensajePasswordDiferentes);
				log.info("Fin del metodo verificarPassword(), password no coinciden");
				return false;
			} else {
				if (completitudClave(txtClaveUsuarioNueva.getValue())) {
					divIncorrectos.setVisible(false);
					log.info("Fin del metodo verificarPassword(), password correctos");
					return true;

				} else {
					divIncorrectos.setVisible(true);
					lblError.setValue(Constantes.mensajeClave);
					log.info("Fin del metodo verificarPassword(), password no cumple con criterios de completitud");
					return false;
				}

			}
		}
		log.info("Fin del metodo verificarPassword()");
		return false;
	}

	@Listen("onChange = #txtClaveUsuarioNueva")
	public void verificar() {
		verificarPassword();
	}

	@Listen("onChange = #txtClaveUsuarioConfirmar")
	public void verificarDos() {
		verificarPassword();
	}

	public void guardarRegitro() {
		log.info("Inicio del metodo guardarRegitro()");
		if (txtClaveUsuarioNueva.getValue() == ""
				|| txtClaveUsuarioConfirmar.getValue() == "") {
			Messagebox.show(
					Constantes.mensajeCamposVacios,
					"Alerta", Messagebox.OK, Messagebox.EXCLAMATION);
			log.info("Fin del metodo guardarRegitro(), campos vacios");
		} else {
			if (verificarPassword()) {
				BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
				String hashedPassword = passwordEncoder
						.encode(txtClaveUsuarioNueva.getValue());
				usuario.setPassword(hashedPassword);
				if (!usuario.isCambiada()) {
					usuario.setCambiada(true);
					log.info("Primer ingreso de usuario");
					Executions.sendRedirect(null);
				}
				servicioUsuario.guardar(usuario);
				log.info("Cambiando clave de ingreso");
				Messagebox.show(Constantes.mensajeRegistroGuardado,
						"Información", Messagebox.OK, Messagebox.INFORMATION);
				limpiarCampos();

			}

		}
		log.info("Fin del metodo guardarRegitro()");
	}

	public void limpiarCampos() {
		log.info("Inicio del metodo limpiarCampos()");
		txtClaveUsuarioConfirmar.setValue("");
		txtClaveUsuarioNueva.setValue("");
		log.info("Fin del metodo limpiarCampos()");
	}

}
