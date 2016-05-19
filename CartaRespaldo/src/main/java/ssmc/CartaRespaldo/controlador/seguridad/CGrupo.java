package ssmc.CartaRespaldo.controlador.seguridad;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zul.Div;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Listitem;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Textbox;

import ssmc.CartaRespaldo.componentes.Botonera;
import ssmc.CartaRespaldo.componentes.Catalogo;
import ssmc.CartaRespaldo.componentes.Constantes;
import ssmc.CartaRespaldo.controlador.maestros.CGenerico;
import ssmc.CartaRespaldo.modelo.seguridad.Grupo;

public class CGrupo extends CGenerico{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final static Logger log = Logger
			.getLogger(ssmc.CartaRespaldo.controlador.seguridad.CGrupo.class);
	Botonera botonera;
	@Wire
	private Div botoneraGrupo;
	@Wire
	private Textbox txtNombreGrupo;
	@Wire
	private Listbox lbxGrupo;

	Catalogo<Grupo> catalogo;
	List<Grupo> grupos = new ArrayList<Grupo>();
	int id = 0;
	Grupo grupo = new Grupo (); 
	boolean editar = false; 
	@Override
	public void inicializar() throws IOException {
		log.info("Metodo inicializar ()");
		grupos = servicioGrupo.buscarTodos(true); 
		lbxGrupo.setModel(new ListModelList<Grupo>(grupos));
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
				log.info("Inicio del metodo eliminar()");
				grupo.setEstado(false);
				servicioGrupo.guardar(grupo);
				log.debug(new StringBuilder().append("Eliminando grupo:").append(grupo));
				Messagebox.show(Constantes.mensajeEliminadoExitoso, "Información", Messagebox.OK, Messagebox.INFORMATION); 
				lbxGrupo.setModel(new ListModelList<Grupo>(servicioGrupo.buscarTodos(true)));
				limpiarCampos();
				log.info("Fin del metodo eliminar()");
			}
		};
		botonera.getChildren().get(0).setVisible(true);
		botonera.getChildren().get(2).setVisible(true);
		botonera.getChildren().get(3).setVisible(true);
		botonera.getChildren().get(1).setVisible(false);
		botoneraGrupo.appendChild(botonera);
		log.info("Fin del metodo inicializar()");
	}
	
	public void guardarRegistro (){
		log.info("Inicio del metodo guardarRegistro()");
		if (txtNombreGrupo.getValue() == ""){
			Messagebox.show(Constantes.mensajeCamposVacios, "Alerta", Messagebox.OK, Messagebox.EXCLAMATION);
			log.info("Fin del metodo guardarRegistro(), campos vacios");
		}
		else {
			if (editar){
				grupo.setNombre(txtNombreGrupo.getValue());
				servicioGrupo.guardar(grupo);
				log.debug(new StringBuilder().append("Editando grupo:").append(grupo));
			}
			else {
				Grupo grupoNuevo = new Grupo(); 
				grupoNuevo.setNombre(txtNombreGrupo.getValue());
				grupoNuevo.setEstado(true);
				servicioGrupo.guardar(grupoNuevo);
				log.debug(new StringBuilder().append("Guardando grupo:").append(grupoNuevo));
			}
			Messagebox.show(Constantes.mensajeRegistroGuardado, "Información", Messagebox.OK, Messagebox.INFORMATION); 
			limpiarCampos();
			lbxGrupo.setModel(new ListModelList<Grupo>(servicioGrupo.buscarTodos(true)));
		}
		log.info("Fin del metodo guardarRegistro()");
	}

	public void limpiarCampos(){
		log.info("Inicio del metodo limpiarCampos()");
		txtNombreGrupo.setValue("");
		editar = false;
		ocultarBotones();
		log.info("Fin del metodo limpiarCampos()");
	}


	private void llenarCampos(Grupo grupo) {
		txtNombreGrupo.setValue(grupo.getNombre());
		id = grupo.getId();
		mostrarBotones();
	}
	
	public void mostrarBotones (){
		botonera.getChildren().get(1).setVisible(true);
		botoneraGrupo.appendChild(botonera);
	}
	
	public void ocultarBotones (){
		botonera.getChildren().get(1).setVisible(false);
		botoneraGrupo.appendChild(botonera);
	}
	
	@Listen("onClick = #lbxGrupo")
	public void seleccionarRegistro (){
		Listitem itemSeleccionado = lbxGrupo.getSelectedItem();
		if (itemSeleccionado != null){
			grupo = itemSeleccionado.getValue(); 
			llenarCampos(grupo);
			editar = true;
		}
	}
}
