/**
 * 
 */
package ssmc.CartaRespaldo.controlador.maestros;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.apache.log4j.Logger;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zul.Div;
import org.zkoss.zul.Label;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Listitem;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Spinner;
import org.zkoss.zul.Window;

import ssmc.CartaRespaldo.componentes.Botonera;
import ssmc.CartaRespaldo.componentes.Constantes;
import ssmc.CartaRespaldo.modelo.maestros.Cargo;
import ssmc.CartaRespaldo.modelo.maestros.CargosEstablecimiento;
import ssmc.CartaRespaldo.modelo.maestros.Establecimiento;
import ssmc.CartaRespaldo.modelo.seguridad.Usuario;

/**
 * CResponsableEstablecimiento
 * 
 * Controlador encargado de realizar las funciones necesarias para agregar y
 * actualizar cargos responsables firmantes de las cartas respaldo por
 * establecimientos
 * 
 * @author Vanessa Maria Duno
 * @version 1.0
 * 
 */
public class CResponsableEstablecimiento extends CGenerico {

	@Wire
	private Spinner spnFirmas;
	@Wire
	private Listbox lbxCargos;
	@Wire
	private Div botoneraResponsables;
	@Wire
	private Window wdwResponsables;
	@Wire
	private Div divError;
	@Wire
	private Label lblError;
	Botonera botonera;
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final static Logger log = Logger
			.getLogger(ssmc.CartaRespaldo.controlador.maestros.CResponsableEstablecimiento.class);
	private List<Cargo> listaCargos = new ArrayList<Cargo>();
	private List<CargosEstablecimiento> cargosEstablecimiento = new ArrayList<CargosEstablecimiento>();
	List<CargosEstablecimiento> cargosRegistrados = new ArrayList<CargosEstablecimiento>();
	Usuario usuario = new Usuario();

	Establecimiento establecimiento = new Establecimiento();

	@Override
	public void inicializar() throws IOException {
		llenarListas();
		establecimiento = usuarioActivo().getEstablecimiento();
		buscarCargos();
		marcarCargos();
		botonera = new Botonera() {

			@Override
			public void salir() {
				wdwResponsables.detach();
			}

			@Override
			public void limpiar() {
				limpiarCampos();
			}

			@Override
			public void guardar() {
				guardarCargos();
			}

			@Override
			public void eliminar() {

			}
		};
		botonera.getChildren().get(0).setVisible(true);
		botonera.getChildren().get(1).setVisible(false);
		botonera.getChildren().get(2).setVisible(true);
		botonera.getChildren().get(3).setVisible(true);
		botoneraResponsables.appendChild(botonera);
		log.info("Fin del metodo inicializar ()");
	}

	public void llenarListas() {
		log.info("Inicio del metodo llenarListas()");
		listaCargos = servicioCargo.buscarTodos();
		lbxCargos.setModel(new ListModelList<Cargo>(listaCargos));
		multiple();
		usuario = usuarioActivo();
		spnFirmas.setValue(usuario.getEstablecimiento().getCantidadFirmantes());
		log.debug(new StringBuilder(
				"Fin del metodo llenarListas(), con objetos").append(
				listaCargos.toString()).append(usuario));
	}

	public void multiple() {
		lbxCargos.setMultiple(false);
		lbxCargos.setCheckmark(false);
		lbxCargos.setMultiple(true);
		lbxCargos.setCheckmark(true);
	}

	public void limpiarCampos() {
		spnFirmas.setValue(0);
		divError.setVisible(false);
		llenarListas();

	}

	public boolean validarCampos() {
		if (spnFirmas.getValue() == 0) {
			return false;
		} else {
			return true;
		}
	}

	/**
	 * obtenerCargos: Metodo que obtiene los cargos firmantes de un
	 * establecimiento
	 * 
	 * @param No
	 *            Recibe ningun parametro
	 * @return No Retorna ningun dato ni objeto
	 * 
	 * @throws No
	 *             dispara ninguna excepci�n.
	 * 
	 */
	public void obtenerCargos() {
		log.info("Inicio del metodo obtenerCargos()");
		Set<Listitem> listItem = lbxCargos.getSelectedItems();
		for (java.util.Iterator<Listitem> it = listItem.iterator(); it
				.hasNext();) {
			Listitem li = it.next();
			Cargo cargo = li.getValue();
			CargosEstablecimiento cargoEs = new CargosEstablecimiento();
			cargoEs.setCargo(cargo);
			cargoEs.setEstablecimiento(establecimiento);
			cargosEstablecimiento.add(cargoEs);
		}
		log.debug(new StringBuilder(
				"Fin de metodo obtenerCargos(), con cargos:")
				.append(cargosEstablecimiento));
	}

	/**
	 * guardarCargos: Metodo que guarda los cargos configurados a un
	 * establecimiento
	 * 
	 * @param No
	 *            Recibe ningun parametro
	 * @return No Retorna ningun dato ni objeto
	 * 
	 * @throws No
	 *             dispara ninguna excepci�n.
	 * 
	 */
	public void guardarCargos() {
		log.info("Inicio del metodo guardarCargos()");
		eliminarCargos();
		obtenerCargos();
		if (validarCampos()) {
			int cantidadCargos = spnFirmas.getValue();
			int tamannoLista = cargosEstablecimiento.size();
			if (cantidadCargos == tamannoLista) {
				establecimiento.setCantidadFirmantes(cantidadCargos);
				servicioEstablecimiento.guardar(establecimiento);
				for (int i = 0; i < cargosEstablecimiento.size(); i++) {
					CargosEstablecimiento ce = cargosEstablecimiento.get(i);
					servicioCargoEstablecimiento.guardar(ce);
				}
				log.debug(new StringBuilder(
						"Exito del metodo guardar con datos").append(
						establecimiento).append(cargosEstablecimiento));
				Messagebox.show(Constantes.mensajeRegistroGuardado,
						"Informaci�n", Messagebox.OK, Messagebox.INFORMATION);
			} else {
				divError.setVisible(true);
				lblError.setValue(Constantes.mensajeErrorCargos);
			}
		} else {
			divError.setVisible(true);
			lblError.setValue(Constantes.mensajeCamposVacios);
		}
		cargosEstablecimiento = new ArrayList<CargosEstablecimiento>();
	}

	public void buscarCargos() {
		log.info("Inicio del metodo buscarCargos()");
		cargosRegistrados = new ArrayList<CargosEstablecimiento>();
		cargosRegistrados = servicioCargoEstablecimiento
				.cargosEstablecimientos(usuario.getEstablecimiento().getId());
		log.info("Fin del metodo buscarCargos()");
	}

	/**
	 * marcarCargos: Metodo que marca en la lista multiple los cargos
	 * configurados por el estableciemiento
	 * 
	 * @param No
	 *            Recibe ningun parametro
	 * @return No Retorna ningun dato ni objeto
	 * 
	 * @throws No
	 *             dispara ninguna excepci�n.
	 * 
	 */
	public void marcarCargos() {
		log.info("Inicio del metodo marcarCargos()");
		lbxCargos.renderAll();
		for (int i = 0; i < cargosRegistrados.size(); i++) {
			Cargo cargo = cargosRegistrados.get(i).getCargo();

			for (int g = 0; g < lbxCargos.getItemCount(); g++) {
				Listitem listItem = lbxCargos.getItemAtIndex(g);
				Cargo c = listItem.getValue();
				if (c.getId() == cargo.getId()) {
					listItem.setSelected(true);
				}
			}
		}
		log.info("Fin del metodo marcarCargos()");
	}

	public void eliminarCargos() {
		servicioCargoEstablecimiento.eliminarCargos(cargosRegistrados);
	}
}
