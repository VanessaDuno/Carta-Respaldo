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

/**
 * CResponsableEstablecimiento
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
	private Div  divError; 
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

	Establecimiento establecimiento = new Establecimiento();

	@Override
	public void inicializar() throws IOException {
		llenarListas();
		establecimiento = usuarioActivo().getEstablecimiento();
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
		listaCargos = servicioCargo.buscarTodos();
		lbxCargos.setModel(new ListModelList<Cargo>(listaCargos));
		multiple();
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
		
	}

	public boolean validarCampos() {
		if (spnFirmas.getValue() == 0) {
			return false;
		} else {
			return true;
		}
	}

	public void obtenerCargos() {
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
	}

	public void guardarCargos() {
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
				Messagebox.show(Constantes.mensajeRegistroGuardado,
						"Información", Messagebox.OK, Messagebox.INFORMATION);

				limpiarCampos();
			}
			else {
				divError.setVisible(true);
				lblError.setValue("La cantidad de cargos configurado debe coincidir con el numero de cargos seleccionados");
			}
		}
		else{
			divError.setVisible(true);
			lblError.setValue(Constantes.mensajeCamposVacios);
		}
		cargosEstablecimiento = new ArrayList<CargosEstablecimiento>();
	}
}
