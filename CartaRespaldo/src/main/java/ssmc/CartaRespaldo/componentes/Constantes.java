package ssmc.CartaRespaldo.componentes;

/**
 * Constantes
 * @author Vanessa Duno
 * @version 1.0
 *
 */

public class Constantes {

	public static int activo = 1;
	public static int inactivo = 0;
	
	public static String mensajeCorreo = "Se ha remitido un paciente gran quemado ingrese a la siguiente dirección para ver los antecedentes http://192.168.128.19:8080/RegistroQuemados/"; 
	public static String correoDireccionQuemados = "nicolas.damelio@redsalud.gob.cl";
	public static String asuntoNotificacion = "Notificación de Carta Respaldo";
	public static String correoNotificacion = "notificacion.ssmc@redsalud.gob.cl";
	public static String passwordNotificacion = "ns.123456"; 
	public static String passwordNoCoinciden = "Los password no coinciden";
	public static String expresionRegularClave = "(?=^.{8,}$)((?=.*d)|(?=.*W+))(?![.n])(?=.*[A-Z])(?=.*[a-z]).*$";
	public static String expresionRegularClaveEspeciales = "[^0-9A-Za-z]+";
	public static String contieneNumero = "[0-9]*"; 
	public static String contieneMinuscula = "[a-z]*";
	public static String contieneMayuscula = "[A-Z]*";
	public static String mensajeClave = "Recuerde que su password debe estar compuesto de al menos una letra minúscula, una letra mayúscula, un número y un caracter especial."; 
	
	public static String mensajeAdvertenciaRegistro = "Es obligatorio llenar todos los campos de datos de paciente, registro y diagnóstico de quemadura"; 
	public static String mensajeEdicion = "Recuerde que sólo podrá editar las fotos del quemado."; 
	public static String mensajeEdicionPacienteNN= "El paciente es de tipo NN, edite sus datos básicos. También puede editar las fotos.";
	public static String mensajeIngresarIdentificacionPaciente = "Debe ingresar el documento del paciente"; 
	public static String estiloTextRutPaciente = "background-image: url('/RegistroQuemados/public/imagenes/botones/rechazar.png'); background-position-x: 232px;background-repeat: no-repeat;background-size: 13px; background-position-y: 8px;";
	public static String estiloDivInfo="alert alert-info";
	public static String mensajePacienteNoRegistrado = "El paciente no está registrado, ingrese los datos";
	public static String mensajeSeleccionarTipoDocumento = "Debe seleccionar el tipo de documento";
	public static String mensajeIngresarRutMedico = "Debe ingresar el rut de medico"; 
	public static String estiloTextRutMedico= "background-image: url('/RegistroQuemados/public/imagenes/botones/rechazar.png'); background-position-x: 313px;background-repeat: no-repeat;background-size: 13px; background-position-y: 8px;";
	public static String mensajeMedicoNoRegistrado = "El medico no esta registrado, ingrese los datos";
	public static String mecanismoElectricidad = "Electricidad";
	public static String mensajeSeleccionarRegistro = "Debe seleccionar un registro.";
	public static String mensajeRegistroQuemadoExitoso = "Ha sido enviado de forma exitosa, nos pondremos en contacto";
	public static String estiloDivDanger = "alert alert-danger"; 
	public static String mensajeRutMedicoNoValido = "Rut del médico no es válido";
	public static String mensajeRutPacienteNoValido = "Rut del paciente no es válido"; 
	public static String mensajeRutResponsableNoValido = "Rut del responsable no es válido"; 
	public static String rutaReporte = "/ssmc/CartaRespaldo/reporte/cartaRespaldo.jasper";
	public static String mensajeGranQuemado = "El paciente no cumple con los criterios de gran quemado. Se considera gran quemado a un paciente:\n"
							+ "• Con un índice de gravedad >70 puntos o con quemaduras AB o B > 20% de SC AB o B.\n"
							+ "• Con quemaduras respiratorias/por inhalación de humo.\n"
							+ "• Con quemaduras eléctricas por alta tensión.\n"
							+ "• Quemados con patologías graves asociadas.";
	public static String imagenNoValida = "Imagen no es válida";
	public static String documentoNoValido = "Documento no es válido, debe ser en formato PDF";
	public static String mensajeRegistroSinFotos = "Las fotografías son requeridas para el estudio y aceptación de la derivación. ¿Desea enviar?"; 
	public static String mensajeEdicionExitosa = "Se ha editado el registro exitosamente"; 
	public static String mensajeEliminadoExitoso = "Registro eliminado exitosamente"; 
	public static String mensajeCamposVacios = "Debe llenar todos los campos para completar el registro";
	public static String mensajeRegistroGuardado= "Registro guardado exitosamente";
	public static String mensajePasswordDiferentes = "Los password no coinciden"; 
	public static String mensajeLoginNoExiste = "El login del usuario no existe";
	public static String mensajeRestaurarPassword= "Hemos enviado un correo con su nuevo password";
	public static String mensajeCorreoReinicioPassword= "Ha solicitado reiniciar su password, sus nuevos datos para el inicio de sesión son:";
	public static String mensajeLoginExiste = "El login ya existe.";
	public static String rutaReinicioPassword= "/public/vistas/seguridad/VReinicioPassword.zul"; 
	public static String rutaCambioPassword = "/public/vistas/seguridad/cambiar-password.zul"; 
	public static String mensajeErrorCargos = "La cantidad de cargos configurado debe coincidir con el numero de cargos seleccionados"; 
	public static String mensajeRutNoExisteFonasa = "El Rut ingresado no esta inscrito en FONASA"; 
	public static String observacionSinFonasa = "El paciente no pertenece a FONASA";
	public static String observacionNoEsRed ="El paciente no pertenece a la RED"; 
	
	
	/** Carta Respaldo **/
	
	public static String codigoDesdeProcedimientos = "2104001";
	public static String codigoHastaProcedimientos = "2104039";
	public static String codigoDesdeIntervenciones = "2104040";
	public static String codigoHastaIntenvenciones = "2104202";
	
	public static String nombreProcedimientoTraumatologia = "PROCEDIMIENTOS TRAUMATOLOGIA"; 
	public static String nombreIntervencionTraumatologia = "CIRUGIA TRAUMATOLOGIA"; 
	
	/** Definición de actividades de log **/
	
	public static String inicioSesion = "Entrada al sistema";
	public static String finSesion = "Salida del sistema";
	public static String creacionUsuario = "Se crea un usuario";
	public static String edicionUsuario = "Se edita un usuario";
	public static String cambioClave = "Clave de ingreso al sistema cambiada";
	public static String recordarClave = "Olvidó clave de ingreso al sistema cambiada";
	public static String registroQuemado = "Registrado gran quemado";
	public static String edicionQuemado = "Editado registro gran quemado";
	public static String consultaQuemado = "Consulta de ficha gran quemado";
	public static String fotoQuemado = "Consulta de foto"
			+ "s de gran quemado";
	
	
	/** Constantes WS Fonasa **/
	public static int canal = 1;
	public static int tipoEmisor = 1;
	public static int tipoUsuario = 1; 
	public static int entidad = 61606800;
	public static int claveEntidad = 6160; 
	
	/** Rutas **/
	
	public static String rutaEstadoCreada = "/public/imagenes/generales/creada.png";
	public static String rutaEstadoTraslado = "/public/imagenes/generales/traslado.png";
	public static String rutaEstadoAnulada = "/public/imagenes/generales/anulada.png";
	public static String rutaEstadoPorValidar =  "/public/imagenes/generales/por_validar.png";
	public static String rutaEstadoCierreClinico = "/public/imagenes/generales/cierre_clinico.png";
	public static String rutaEstadoCierreAdministrativo = "/public/imagenes/generales/cierre_administrativo.png"; 
	public static String rutaAplicacionServidor = "\\eclipseApps\\CartaRespaldo\\public\\temporal\\generado";
	public static String rutaDirectorioTemporal = "public/temporal/generado"; 
	public static String extensionPdf = ".pdf"; 
	
	
	/** Codigos de comunas de la red **/
	
	public static String codigoPedroAguierreCerda = "13121";
	public static String codigoSantiago = "13101";
	public static String codigoEstacionCentral = "13106";
	public static String codigoMaipu = "13119";
	public static String codigoCerrillos = "13102"; 
	
	/** Codigos de bloqueo **/
	
	public static String codigoBloqueadoIsapre = "01901"; 
	public static String codigoBloqueadoFallecido = "01902";
	public static String codigoBloqueadoSinCotizaciones = "01903";
	public static String codigoBloqueadoSinInformacion = "01906";
	public static String codigoBloqueadoCapredena = "01911";
	public static String codigoBloqueadoAuditoria = "01914";
	public static String codigoBloqueadoCargaIsapre = "01951";
	public static String codigoBloqueadoCagaSinCotizaciones = "01953";
	public static String codigoBloqueadoCargaCapredena = "01957";
	public static String codigoBloqueadoRutTitularFueraRango = "01970";
	public static String codigoBloqueadoRutCargaFueraRango = "01971";
	public static String codigoBloqueadoDipreca = "01972";
	public static String codigoBloqueadoCargaDipreca = "01973";
	public static String codigoBloqueadoCargaAuditoria = "01976";

	
			
	
	
}
