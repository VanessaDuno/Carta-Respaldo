/**
 * 
 */
package ssmc.CartaRespaldo.controlador.transacciones;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

import org.zkoss.zk.ui.Sessions;
import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zul.Button;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Listitem;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Window;

import ssmc.CartaRespaldo.controlador.maestros.CGenerico;
import ssmc.CartaRespaldo.modelo.maestros.Responsable;
import ssmc.CartaRespaldo.modelo.transacciones.PrestacionSolicitud;
import ssmc.CartaRespaldo.modelo.transacciones.ResponsableSolicitud;

/**
 * CModalPrestaciones
 * 
 * @author Vanessa Maria Duno
 * @version 1.0
 * 
 */
public class CModalResponsables extends CGenerico {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Wire
	private Listbox lbxResponsables;
	@Wire
	private Textbox txtBusquedaResponsables;
	@Wire
	private Button btnAceptar;
	@Wire
	private Button btnCancelar;
	@Wire
	private Window wdwModalResponsables;
	boolean medico = false;
	boolean funcionario = false;

	List<Responsable> medicosResponsable = new ArrayList<Responsable>();
	List<Responsable> funcionariosResponsables = new ArrayList<Responsable>();
	List<ResponsableSolicitud> responsablesSolicitud = new ArrayList<ResponsableSolicitud>();

	@Override
	public void inicializar() throws IOException {

		HashMap<String, Object> map = (HashMap<String, Object>) Sessions
				.getCurrent().getAttribute("itemsCatalogo");
		if (map != null) {
			medicosResponsable = (List<Responsable>) (map.get("medicos"));
			funcionariosResponsables = (List<Responsable>) (map
					.get("funcionarios"));

			if (medicosResponsable != null) {
				lbxResponsables.setModel(new ListModelList<Responsable>(
						medicosResponsable));
				lbxResponsables.setCheckmark(false);
				lbxResponsables.setCheckmark(true);
				medico = true;
			}
			if (funcionariosResponsables != null) {
				lbxResponsables.setModel(new ListModelList<Responsable>(
						funcionariosResponsables));
				funcionario = true; 
				multiple();
			}

		}
		// marcarPrestaciones();
	}

	public void multiple() {
		lbxResponsables.setCheckmark(false);
		lbxResponsables.setMultiple(false);
		lbxResponsables.setCheckmark(true);
		lbxResponsables.setMultiple(true);

	}

	@Listen("onOK = #txtBusquedaResponsables")
	public void buscar() {
		if (!txtBusquedaResponsables.getValue().equals("")) {
			List<Responsable> lista = new ArrayList<Responsable>();
			for (Responsable detalle : medicosResponsable) {
				if (detalle
						.getNombre()
						.toLowerCase()
						.contains(
								txtBusquedaResponsables.getValue()
										.toLowerCase())) {
					lista.add(detalle);
				}
			}
			lbxResponsables.setModel(new ListModelList<Responsable>(lista));
			lbxResponsables.setCheckmark(false);
			lbxResponsables.setCheckmark(true);
			lbxResponsables.renderAll();
		} else {
			lbxResponsables.setModel(new ListModelList<Responsable>(
					medicosResponsable));
			lbxResponsables.setCheckmark(false);
			lbxResponsables.setCheckmark(true);
			lbxResponsables.renderAll();
		}
	}

	@Listen("onClick = #btnCancelar")
	public void cancelarModal() {
		wdwModalResponsables.detach();
	}

	@Listen("onClick = #btnAceptar")
	public void rescatarPrestaciones() {
		responsablesSolicitud = (List<ResponsableSolicitud>) Sessions.getCurrent().getAttribute("responsablesSolicitud");
		if (responsablesSolicitud == null){
			responsablesSolicitud = new ArrayList<ResponsableSolicitud>();
		}
		if (medico){
		obtenerMedico();
		}
		if (funcionario){
		obtenerFuncionarios();
		}
		Sessions.getCurrent().setAttribute("responsablesSolicitud",
				responsablesSolicitud);
		wdwModalResponsables.detach();
	}

	public void obtenerFuncionarios() {
		Set<Listitem> listItem = lbxResponsables.getSelectedItems();
		for (java.util.Iterator<Listitem> it = listItem.iterator(); it
				.hasNext();) {
			Listitem li = it.next();
			Responsable responsable = li.getValue();
			ResponsableSolicitud rs = new ResponsableSolicitud();
			rs.setResponsable(responsable);

			for (int i = 0; i < responsablesSolicitud.size(); i++) {
				Responsable responsables = responsablesSolicitud.get(i)
						.getResponsable();
				if (responsables.getId() == responsable.getId()) {
					responsablesSolicitud.remove(responsablesSolicitud.get(i));
				}
			}
			responsablesSolicitud.add(rs);
			wdwModalResponsables.detach();
		}
	}

	public void obtenerMedico() {
		Listitem item = lbxResponsables.getSelectedItem();
		Responsable medicoResponsable = item.getValue();
		ResponsableSolicitud rs = new ResponsableSolicitud();
		rs.setResponsable(medicoResponsable);
		responsablesSolicitud.add(rs);
	}

	public void marcarPrestaciones() {
		// for (int i = 0; i < listaPrestacionesSolicitud.size(); i++) {
		// DetallePrestacion prestacion =
		// listaPrestacionesSolicitud.get(i).getPrestacion();
		// Motivo motivo = listaPrestacionesSolicitud.get(i).getMotivo();
		//
		// for (int j = 0; j < lbxResponsables.getItemCount(); j++) {
		// Listitem listItem = lbxResponsables
		// .getItemAtIndex(j);
		// DetallePrestacion dp = listItem.getValue();
		// if (prestacion.getId() == dp.getId()){
		// listItem.setSelected(true);
		// ((Combobox) ((listItem.getChildren().get(1)))
		// .getFirstChild()).setValue(motivo.getNombre());
		// }
		// }
		// }
	}

}
