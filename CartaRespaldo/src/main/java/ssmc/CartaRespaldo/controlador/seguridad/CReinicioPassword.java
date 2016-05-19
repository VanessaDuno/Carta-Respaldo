package ssmc.CartaRespaldo.controlador.seguridad;

import java.io.IOException;

import org.apache.log4j.Logger;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.keygen.KeyGenerators;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zul.Button;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Window;

import ssmc.CartaRespaldo.componentes.Constantes;
import ssmc.CartaRespaldo.controlador.maestros.CGenerico;
import ssmc.CartaRespaldo.modelo.seguridad.Usuario;

public class CReinicioPassword extends CGenerico {

	@Wire
	private Textbox txtCorreoUsuario;
	@Wire
	private Window wdwRecordar;
	@Wire
	private Button btnRestaurar;
	@Wire
	private Button btnSalir;
	PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
	private static final long serialVersionUID = 6988038390488496987L;
	private final static Logger log = Logger
			.getLogger(ssmc.CartaRespaldo.controlador.seguridad.CReinicioPassword.class);

	@Override
	public void inicializar() throws IOException {
	}

	@Listen("onClick = #btnRestaurar")
	public void restaurar() {
		log.info("Inicio del metodo restaurar()");
		String password = KeyGenerators.string().generateKey();
		String correo;
		Usuario usuario = servicioUsuario
				.buscarUsuarioPorNombre(txtCorreoUsuario.getValue());
		if (usuario != null) {
			log.debug(new StringBuilder().append("Usuario encontrado").append(
					usuario.toString()));
			if (usuario.getEmail() != null) {
				correo = usuario.getEmail();
			} else {
				correo = txtCorreoUsuario.getValue();
			}
			BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
			String hashedPassword = passwordEncoder.encode(password);
			usuario.setPassword(hashedPassword);
			servicioUsuario.guardar(usuario);
			log.info("Generando contraseña");
			String[] correos = { correo };
			enviarEmailNotificacion(
					correos,
					Constantes.mensajeCorreoReinicioPassword
							+ " Usuario: "
							+ usuario.getLogin()
							+ "  "
							+ " Password: " + password, null);
			txtCorreoUsuario.setValue("");
			Messagebox.show(Constantes.mensajeRestaurarPassword,
					"Información", Messagebox.OK, Messagebox.INFORMATION);
			wdwRecordar.onClose();
			prepararLog(
					Constantes.recordarClave,
					"Realizada funcionalidad de reinicio de clave al usuario" + " "
							+ usuario.getLogin());
		} else {
			Messagebox.show(Constantes.mensajeLoginNoExiste, "Error",
					Messagebox.OK, Messagebox.ERROR);
			prepararLog(
					Constantes.recordarClave,
					"Intento de reinicio de clave al usuario no encontrado" + " "
							+ txtCorreoUsuario.getValue());
			log.info("Login de usuario no existe");
		}
		log.info("Fin del metodo restaurar()");
	}
	
	@Listen("onClick = #btnSalir")
	public void salir() {
		wdwRecordar.onClose();
	}

}
