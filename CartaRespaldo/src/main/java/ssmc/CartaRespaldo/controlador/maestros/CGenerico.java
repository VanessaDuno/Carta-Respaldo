package ssmc.CartaRespaldo.controlador.maestros;

import java.io.IOException;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.mail.Address;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.util.ByteArrayDataSource;

import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zk.ui.select.annotation.VariableResolver;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zul.Include;
import org.zkoss.zul.Tab;
import org.zkoss.zul.Tabbox;

import ssmc.CartaRespaldo.componentes.Constantes;
import ssmc.CartaRespaldo.controlador.seguridad.LogActividad;
import ssmc.CartaRespaldo.modelo.seguridad.Usuario;
import ssmc.CartaRespaldo.servicio.maestros.SBitacora;
import ssmc.CartaRespaldo.servicio.maestros.SCargo;
import ssmc.CartaRespaldo.servicio.maestros.SCargoEstablecimiento;
import ssmc.CartaRespaldo.servicio.maestros.SCategoriaPrestacion;
import ssmc.CartaRespaldo.servicio.maestros.SComuna;
import ssmc.CartaRespaldo.servicio.maestros.SDetallePrestacion;
import ssmc.CartaRespaldo.servicio.maestros.SDiagnostico;
import ssmc.CartaRespaldo.servicio.maestros.SEstablecimiento;
import ssmc.CartaRespaldo.servicio.maestros.SMotivo;
import ssmc.CartaRespaldo.servicio.maestros.SPaciente;
import ssmc.CartaRespaldo.servicio.maestros.SPrestacion;
import ssmc.CartaRespaldo.servicio.maestros.SProvincia;
import ssmc.CartaRespaldo.servicio.maestros.SRegion;
import ssmc.CartaRespaldo.servicio.maestros.SResponsable;
import ssmc.CartaRespaldo.servicio.maestros.SServicioClinico;
import ssmc.CartaRespaldo.servicio.maestros.SSexo;
import ssmc.CartaRespaldo.servicio.maestros.SUnidad;
import ssmc.CartaRespaldo.servicio.seguridad.SGrupo;
import ssmc.CartaRespaldo.servicio.seguridad.SMenu;
import ssmc.CartaRespaldo.servicio.seguridad.SMenuGrupo;
import ssmc.CartaRespaldo.servicio.seguridad.SUsuario;
import ssmc.CartaRespaldo.servicio.seguridad.SUsuarioGrupo;
import ssmc.CartaRespaldo.servicio.transacciones.SPrestacionSolicitud;
import ssmc.CartaRespaldo.servicio.transacciones.SResponsableSolicitud;
import ssmc.CartaRespaldo.servicio.transacciones.SSolicitudTraslado;
import ssmc.CartaRespaldo.servicio.seguridad.SLog;

/**
 * Controlador CGenerico
 * 
 * Clase abstracta padre de que los controladores de la aplicacion manaja
 * metodos genericos y abstractos usados en el sistema
 * 
 * @author Vanessa Duno / SSMC
 * @version 1.0
 */

@VariableResolver(org.zkoss.zkplus.spring.DelegatingVariableResolver.class)
public abstract class CGenerico extends SelectorComposer<Component> {

	private static final long serialVersionUID = -2264423023637489596L;
	private final static Logger log = Logger
			.getLogger(ssmc.CartaRespaldo.controlador.maestros.CGenerico.class);

	@WireVariable("SMenu")
	protected SMenu servicioMenu;
	@WireVariable("SGrupo")
	protected SGrupo servicioGrupo;
	@WireVariable("SUsuario")
	protected SUsuario servicioUsuario;
	@WireVariable("SUsuarioGrupo")
	protected SUsuarioGrupo servicioUsuarioGrupo;
	@WireVariable("SMenuGrupo")
	protected SMenuGrupo servicioMenuGrupo;
	@WireVariable("SRegion")
	protected SRegion servicioRegion;
	@WireVariable("SEstablecimiento")
	protected SEstablecimiento servicioEstablecimiento;
	@WireVariable("SComuna")
	protected SComuna servicioComuna;
	@WireVariable("SProvincia")
	protected SProvincia servicioProvincia;
	@WireVariable("SSexo")
	protected SSexo servicioSexo;
	@WireVariable("SDiagnostico")
	protected SDiagnostico servicioDiagnostico;
	@WireVariable("SCargo")
	protected SCargo servicioCargo;
	@WireVariable("SPrestacion")
	protected SPrestacion servicioPrestacion;
	@WireVariable("SServicioClinico")
	protected SServicioClinico servicioServicioClinico;
	@WireVariable("SUnidad")
	protected SUnidad servicioUnidad;
	@WireVariable("SMotivo")
	protected SMotivo servicioMotivo;
	@WireVariable("SDetallePrestacion")
	protected SDetallePrestacion servicioDetallePrestacion;
	@WireVariable("SCategoriaPrestacion")
	protected SCategoriaPrestacion servicioCategoriaPrestacion;
	@WireVariable("SPrestacionSolicitud")
	protected SPrestacionSolicitud servicioPrestacionSolicitud;
	@WireVariable("SSolicitudTraslado")
	protected SSolicitudTraslado servicioSolicitudTraslado;
	@WireVariable("SPaciente")
	protected SPaciente servicioPaciente;
	@WireVariable("SBitacora")
	protected SBitacora servicioBitacora;
	@WireVariable("SCargoEstablecimiento")
	protected SCargoEstablecimiento servicioCargoEstablecimiento;
	@WireVariable("SResponsable")
	protected SResponsable servicioResponsable;
	@WireVariable("SResponsableSolicitud")
	protected SResponsableSolicitud servicioResponsableSolicitud;
	@WireVariable("SLog")
	protected SLog servicioLog;

	public Tabbox tabBox;
	public Include contenido;
	protected SimpleDateFormat formatoFecha = new SimpleDateFormat("dd-MM-yyyy");
	public List<Tab> tabs = new ArrayList<Tab>();
	protected DateFormat df = new SimpleDateFormat("HH:mm:ss");
	public final Calendar calendario = Calendar.getInstance();
	public String horaAuditoria = String.valueOf(calendario
			.get(Calendar.HOUR_OF_DAY))
			+ ":"
			+ String.valueOf(calendario.get(Calendar.MINUTE))
			+ ":"
			+ String.valueOf(calendario.get(Calendar.SECOND));
	public java.util.Date fecha = new Date();
	public Timestamp fechaHora = new Timestamp(fecha.getTime());

	Authentication auth = SecurityContextHolder.getContext()
			.getAuthentication();

	private static ApplicationContext applicationContext = new ClassPathXmlApplicationContext(
			"/META-INF/ConfiguracionAplicacion.xml");

	@Override
	public void doAfterCompose(Component comp) throws Exception {
		super.doAfterCompose(comp);
		inicializar();
	}

	public abstract void inicializar() throws IOException;

	public String nombreUsuarioSesion() {
		log.info("Inicio del metodo nombreUsuarioSesion()");
		Authentication sesion = SecurityContextHolder.getContext()
				.getAuthentication();
		log.debug(new StringBuilder().append(
				"Fin del metodo nombreUsuarioSesion(), retorna:").append(
				sesion.getName()));
		ipValida("123123123123");
		return sesion.getName();
	}

	/**
	 * enviarEmailNotificacion: Metodo que envia una notificacion por correo a
	 * email de la red minsal.
	 * 
	 * @param Recibe
	 *            una lista de destinatarios String[] correo, mensajes y un pdf
	 * @return Retorna un boolean de exito o no
	 * 
	 * @throws No
	 *             dispara ninguna excepción.
	 * 
	 */
	public boolean enviarEmailNotificacion(String[] correo, String mensajes,
			byte[] pdf) {
		log.debug(new StringBuilder()
				.append("Inicio del metodo enviarEmailNotificacion, con parametros:")
				.append(correo).append(mensajes).append(pdf));
		try {
			Properties props = new Properties();
			props.setProperty("mail.smtp.host", "mail.minsal.cl");
			props.setProperty("mail.smtp.starttls.enable", "false");
			props.setProperty("mail.smtp.port", "25");
			props.setProperty("mail.smtp.auth", "true");
			props.put("mail.smtp.host", "mail.minsal.cl");
			props.put("mail.transport.protocol", "smtp");

			Session session = Session.getDefaultInstance(props);
			String asunto = Constantes.asuntoNotificacion;
			String remitente = Constantes.correoNotificacion;
			String contrasena = Constantes.passwordNotificacion;
			String mensaje = mensajes;

			MimeMessage message = new MimeMessage(session);

			message.setFrom(new InternetAddress(remitente));

			Address[] receptores = new Address[correo.length];
			int j = 0;
			while (j < correo.length) {
				receptores[j] = new InternetAddress(correo[j]);
				j++;
			}

			message.addRecipients(Message.RecipientType.TO, receptores);
			message.setSubject(asunto);
			BodyPart text = new MimeBodyPart();
			MimeMultipart mimeMultipart = new MimeMultipart();
			if (pdf != null) {
				DataSource dataSource = new ByteArrayDataSource(pdf,
						"application/pdf");
				MimeBodyPart pdfBodyPart = new MimeBodyPart();
				pdfBodyPart.setDataHandler(new DataHandler(dataSource));
				pdfBodyPart.setFileName("RegistroQuemados.pdf");

				mimeMultipart.addBodyPart(pdfBodyPart);
			}
			text.setContent("\n" + mensaje, "text/html");
			mimeMultipart.addBodyPart(text);

			message.setDescription(mensaje);
			message.setContent(mimeMultipart);

			Transport t = session.getTransport("smtp");
			t.connect(remitente, contrasena);
			t.sendMessage(message,
					message.getRecipients(Message.RecipientType.TO));

			t.close();
			log.debug(new StringBuilder()
					.append("Fin del metodo enviarEmailNotificacion, exitoso"));
			return true;
		}

		catch (Exception e) {
			e.printStackTrace();
			log.debug(new StringBuilder()
					.append("Fin del metodo enviarEmailNotificacion, fallido"));
			return false;
		}
	}

	public String damePath() {
		return Executions.getCurrent().getContextPath() + "/";
	}

	public List<String> obtenerPropiedades() {
		List<String> arreglo = new ArrayList<String>();
		DriverManagerDataSource ds = (DriverManagerDataSource) applicationContext
				.getBean("dataSource");
		arreglo.add(ds.getUsername());
		arreglo.add(ds.getPassword());
		arreglo.add(ds.getUrl());
		return arreglo;
	}

	/**
	 * usuarioActivo: Metodo que obtiene el usuario activo logueado
	 * 
	 * @param No
	 *            recibe ningun parametro
	 * @return Retorna un objeto de tipo Usuario
	 * 
	 * @throws No
	 *             dispara ninguna excepción.
	 * 
	 */
	public Usuario usuarioActivo() {
		log.info("Inicio del metodo  usuarioActivo()");
		Usuario usuario = new Usuario();
		usuario = servicioUsuario.buscarUsuarioPorNombre(auth.getName());
		log.debug(new StringBuilder().append(
				"Fin del metodo  usuarioActivo(), retorna").append(
				usuario.toString()));
		return usuario;
	}

	/**
	 * validarRut: Metodo que verifica la validez de un rut
	 * 
	 * @param Recibe
	 *            el rut a verificar
	 * @return Retorna el exito o no de la operacion
	 * 
	 * @throws No
	 *             dispara ninguna excepción.
	 * 
	 */
	public static boolean validarRut(String rut) {
		log.debug(new StringBuilder().append(
				"Inicio del metodo validarRut. con rut: ").append(rut));
		boolean validacion = false;
		try {
			rut = rut.toUpperCase();
			rut = rut.replace(".", "");
			rut = rut.replace("-", "");
			int rutAux = Integer.parseInt(rut.substring(0, rut.length() - 1));

			char dv = rut.charAt(rut.length() - 1);

			int m = 0, s = 1;
			for (; rutAux != 0; rutAux /= 10) {
				s = (s + rutAux % 10 * (9 - m++ % 6)) % 11;
			}
			if (dv == (char) (s != 0 ? s + 47 : 75)) {
				validacion = true;
				log.debug("Fin del metodo validarRut, resultado exitoso");
			}

		} catch (java.lang.NumberFormatException e) {
			log.error("Error al formatear numero");
		} catch (Exception e) {
		}
		log.debug("Fin del metodo validarRut, resultado fallido");
		return validacion;

	}

	/**
	 * formatearRut: Metodo que da formato al rut, ejemplo: 1111111-1
	 * 
	 * @param Recibe
	 *            el rut a formatear
	 * @return Retorna el rut formateado
	 * 
	 * @throws No
	 *             dispara ninguna excepción.
	 * 
	 */
	public String formatearRut(String rut) {
		log.debug(new StringBuilder().append(
				"Inicio del metodo formatearRut, con rut: ").append(rut));
		String rutFormateado;
		if (rut.length() >= 8) {
			if (rut.indexOf("-") != -1) {
				rutFormateado = rut.replace(".", "");
			} else {
				rutFormateado = rut.replace(".", "");
				if (rutFormateado.length() == 8) {
					rutFormateado = rutFormateado.substring(0, 7) + "-"
							+ rutFormateado.substring(7);
				} else {
					rutFormateado = rutFormateado.substring(0, 8) + "-"
							+ rutFormateado.substring(8);
				}
			}
			log.debug(new StringBuilder().append(
					"Fin del metodo formatearRut, con rut retornado: ").append(
					rutFormateado));
			return rutFormateado;
		}
		return rut;
	}

	/**
	 * completitudClave: Metodo que verifica la completitud de una clave,
	 * evaluando que contenga numeros, letras y caracteres especiales
	 * 
	 * @param Recibe
	 *            la clave a verificar
	 * @return Retorna exito o no de la operacion
	 * 
	 * @throws No
	 *             dispara ninguna excepción.
	 * 
	 */
	public boolean completitudClave(String clave) {
		log.debug(new StringBuilder()
				.append("Inicio del metodo completitudClave"));
		boolean contieneNumero = false;
		boolean contieneLetrasMinusculas = false;
		boolean contieneLetrasMayusculas = false;
		boolean contieneCaracteres = false;
		String numeros = "0123456789";
		String letras = "abcdefghijklmnopqrstuvwxyz";
		String caracteres = "!#$%&/()=?¡+*-´}{[].:,;";

		for (int i = 0; i < numeros.length(); i++) {
			if (i < clave.length()) {
				if (numeros.indexOf(clave.charAt(i), 0) != -1) {
					contieneNumero = true;
					break;
				}
			} else {
				break;
			}
		}
		for (int i = 0; i < letras.length(); i++) {
			if (i < clave.length()) {
				if (letras.indexOf(clave.charAt(i), 0) != -1) {
					contieneLetrasMinusculas = true;
					break;
				}
			} else {
				break;
			}
		}
		for (int i = 0; i < caracteres.length(); i++) {
			if (i < clave.length()) {
				if (caracteres.indexOf(clave.charAt(i), 0) != -1) {
					contieneCaracteres = true;
					break;
				}
			} else {
				break;
			}
		}
		for (int i = 0; i < letras.toUpperCase().length(); i++) {
			if (i < clave.length()) {
				if (caracteres.indexOf(clave.charAt(i), 0) != -1) {
					contieneLetrasMayusculas = true;
					break;
				}
			} else {
				break;
			}
		}

		if (contieneCaracteres && contieneLetrasMinusculas && contieneNumero
				&& contieneLetrasMayusculas) {
			log.debug(new StringBuilder()
					.append("Fin de metodo completitudClave con resultado exitoso"));
			return true;
		} else {
			log.debug(new StringBuilder()
					.append("Fin de metodo completitudClave con resultado fallido"));
			return false;
		}

	}

	public int calcularEdad(Timestamp fecha) {
		log.info("Inicio de metodo calcularEdad()");
		Date fechaActual = new Date();
		SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
		String hoy = formato.format(fechaActual);
		String fechaNac = formato.format(fecha.getTime());
		String[] dat1 = fechaNac.split("/");
		String[] dat2 = hoy.split("/");
		int anos = Integer.parseInt(dat2[2]) - Integer.parseInt(dat1[2]);
		int mes = Integer.parseInt(dat2[1]) - Integer.parseInt(dat1[1]);
		if (mes < 0) {
			anos = anos - 1;
		} else if (mes == 0) {
			int dia = Integer.parseInt(dat2[0]) - Integer.parseInt(dat1[0]);
			if (dia > 0) {
				anos = anos - 1;
			}
		}
		return anos;
	}

	public String cortarCadena(String cadena) {
		int posicion = cadena.indexOf("(");
		if (posicion != -1) {
			cadena = cadena.substring(0, posicion);
		}
		return cadena;
	}

	public static SSolicitudTraslado getServicioSolicitudTraslado() {
		return applicationContext.getBean(SSolicitudTraslado.class);
	}

	public static SPrestacionSolicitud getServicioPrestacionSolicitud() {
		return applicationContext.getBean(SPrestacionSolicitud.class);
	}

	public static SUsuario getServicioUsuario() {
		return applicationContext.getBean(SUsuario.class);
	}

	public static SResponsableSolicitud getServicioResponsableSolicitud() {
		return applicationContext.getBean(SResponsableSolicitud.class);
	}

	/**
	 * prepararLog: Metodo que guarda log de la acrividades realizadas por un
	 * usuario en base de datos
	 * 
	 * @param Recibe
	 *            String actividad, String descripcion
	 * @return No retorna ningun parametro ni objeto
	 * 
	 * @throws No
	 *             dispara ninguna excepción.
	 * 
	 */
	public void prepararLog(String actividad, String descripcion) {
		java.util.Date fecha = new Date();
		Timestamp fechaSistema = new Timestamp(fecha.getTime());
		LogActividad log = new LogActividad();
		log.setActividad(actividad);
		log.setDescripcion(descripcion);
		log.setLoginUsuario(auth.getName());
		log.setFecha(fechaSistema);
		servicioLog.guardar(log);
	}

	public void ipValida(String cadena) {
		String n1 = "";
		String n2 = "";
		String n3 = "";
		String n4 = "";
		int tamannoCadena = cadena.length();
		if (tamannoCadena == 12) {
			n1 = cadena.substring(0, 2);
			n2 = cadena.substring(3, 5);
			n3 = cadena.substring(6, 8);
			n4 = cadena.substring(9, 11);
			if (Integer.valueOf(n1) <= 255 && Integer.valueOf(n2) <= 255
					&& Integer.valueOf(n3) <= 255 && Integer.valueOf(n4) <= 255) {
				System.out.println(n1 + "." + n2 + "." + n3  + "." + n4 );
			}
		}
		else {
		for (int i = 0; i < cadena.length(); i++) {
		
		}
		}
	}
}
