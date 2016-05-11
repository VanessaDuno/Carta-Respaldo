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
import org.zkoss.zul.Combobox;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Listitem;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Window;

import ssmc.CartaRespaldo.controlador.maestros.CGenerico;
import ssmc.CartaRespaldo.modelo.maestros.DetallePrestacion;
import ssmc.CartaRespaldo.modelo.maestros.Motivo;
import ssmc.CartaRespaldo.modelo.transacciones.PrestacionSolicitud;

/**
 * CModalPrestaciones
 * 
 * @author Vanessa Maria Duno
 * @version 1.0
 * 
 */
public class CModalPrestaciones extends CGenerico {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Wire
	private Listbox lbxPrestaciones;
	@Wire
	private Textbox txtBusquedaPrestaciones; 
	@Wire
	private Combobox cmbMotivo;
	@Wire 
	private Button btnAceptar;
	@Wire 
	private Button btnCancelar;
	@Wire
	private Window wdwModalPrestaciones;
	
	List<DetallePrestacion> prestaciones = new ArrayList<DetallePrestacion>();
	
	List<DetallePrestacion> prestacionesExamenes = new ArrayList<DetallePrestacion>();
	List<DetallePrestacion> prestacionesProcedimientos = new ArrayList<DetallePrestacion>();
	List<DetallePrestacion> prestacionesIntervensiones = new ArrayList<DetallePrestacion>();
	List<DetallePrestacion> prestacionesConsultas = new ArrayList<DetallePrestacion>();
	
	List<PrestacionSolicitud> listaPrestacionesSolicitud = new ArrayList<PrestacionSolicitud>(); 
	private List<Motivo> motivos = new ArrayList<Motivo>();
	
	boolean examenes = false;
	boolean procedimientos = false;
	boolean intervesiones = false;
	boolean consultas = false; 

	@Override
	public void inicializar() throws IOException {

		HashMap<String, Object> map = (HashMap<String, Object>) Sessions
				.getCurrent().getAttribute("itemsCatalogo");
		if (map != null) {
			prestacionesExamenes = (List<DetallePrestacion>) (map.get("prestacionesExamenes"));
			prestacionesProcedimientos = (List<DetallePrestacion>) (map.get("prestacionesProcedimientos"));
			prestacionesIntervensiones = (List<DetallePrestacion>) (map.get("prestacionesIntervensiones"));
			prestacionesConsultas = (List<DetallePrestacion>) (map.get("prestacionesConsultas"));
			examenes = (boolean) map.get("examenes");
			procedimientos = (boolean) map.get("procedimientos");
			intervesiones = (boolean) map.get("intervesiones");
			consultas = (boolean) map.get("consultas");			
			listaPrestacionesSolicitud = (List<PrestacionSolicitud>) map.get("prestacionesSolicitud");
			if (listaPrestacionesSolicitud == null){
				listaPrestacionesSolicitud = new ArrayList<PrestacionSolicitud>();
			}
		}
		if (examenes){
			lbxPrestaciones.setModel(new ListModelList<DetallePrestacion>(prestacionesExamenes));
		}
		else if (procedimientos){
			lbxPrestaciones.setModel(new ListModelList<DetallePrestacion>(prestacionesProcedimientos));
		}
		else if (intervesiones){
			lbxPrestaciones.setModel(new ListModelList<DetallePrestacion>(prestacionesIntervensiones));
		}
		else if (consultas){
			lbxPrestaciones.setModel(new ListModelList<DetallePrestacion>(prestacionesConsultas));
		}
		multiple();
		lbxPrestaciones.renderAll();
		motivos = servicioMotivo.buscarTodos();
		cargarMotivos();
		marcarPrestaciones();
	}
	
	public void cargarMotivos (){
		List<Listitem> listItem = lbxPrestaciones.getItems();
		if (listItem.size() != 0) {
			for (int i = 0; i < listItem.size(); i++) {
				((Combobox) ((listItem.get(i).getChildren().get(1)))
							.getFirstChild()).setModel(new ListModelList<Motivo>(motivos));
			}
		}
	}
	
	public void multiple() {
		lbxPrestaciones.setCheckmark(false);
		lbxPrestaciones.setMultiple(false);
		lbxPrestaciones.setCheckmark(true);
		lbxPrestaciones.setMultiple(true);


	}
	
	@Listen ("onOK = #txtBusquedaPrestaciones")
	public void buscar (){
		if (examenes){
			prestaciones = prestacionesExamenes;
		}
		else if (procedimientos){
			prestaciones = prestacionesProcedimientos;
		}
		else if (intervesiones){
			prestaciones = prestacionesIntervensiones;
		}
		else if (consultas){
			prestaciones = prestacionesConsultas;
		}
		if (!txtBusquedaPrestaciones.getValue().equals("")){
			List<DetallePrestacion> lista = new ArrayList<DetallePrestacion>();
			for (DetallePrestacion detalle : prestaciones) {
				if (detalle.getNombre().toLowerCase()
						.contains(txtBusquedaPrestaciones.getValue().toLowerCase())) {
					lista.add(detalle);
				}
			}
			lbxPrestaciones.setModel(new ListModelList<DetallePrestacion>(lista));
			multiple();
			lbxPrestaciones.renderAll();
		}
		else {
			lbxPrestaciones.setModel(new ListModelList<DetallePrestacion>(prestaciones));
			multiple();
			lbxPrestaciones.renderAll();
		}
		cargarMotivos();

	}
	
	@Listen ("onClick = #btnCancelar")
	public void cancelarModal (){
		wdwModalPrestaciones.detach();
	}
	
	@Listen ("onClick = #btnAceptar")
	public void rescatarPrestaciones (){
		obtenerPrestaciones(); 
		Sessions.getCurrent().setAttribute("prestacionesExamenes", prestacionesExamenes);
		Sessions.getCurrent().setAttribute("prestacionesProcedimientos", prestacionesProcedimientos);
		Sessions.getCurrent().setAttribute("prestacionesIntervensiones", prestacionesIntervensiones);
		Sessions.getCurrent().setAttribute("prestacionesConsultas", prestacionesConsultas);
		Sessions.getCurrent().setAttribute("prestacionesSolicitud", listaPrestacionesSolicitud);
	}
	
	public void obtenerPrestaciones() {
		Set<Listitem> listItem = lbxPrestaciones.getSelectedItems();
		for (java.util.Iterator<Listitem> it = listItem.iterator(); it
				.hasNext();) {
			Listitem li = it.next();
			DetallePrestacion prestacion = li.getValue();
			PrestacionSolicitud prestacionesSolicitud = new PrestacionSolicitud();
			prestacionesSolicitud.setPrestacion(prestacion);
			if (!((Combobox) ((li.getChildren().get(1)))
					.getFirstChild()).getValue().equals("")){
			Motivo motivo = ((Combobox) ((li.getChildren().get(1)))
					.getFirstChild()).getSelectedItem().getValue();
			prestacionesSolicitud.setMotivo(motivo);
			for (int i = 0; i < listaPrestacionesSolicitud.size(); i++) {
				DetallePrestacion dp = listaPrestacionesSolicitud.get(i).getPrestacion();
				if (dp.getId() == prestacion.getId()){
					listaPrestacionesSolicitud.remove(listaPrestacionesSolicitud.get(i)); 
				}
			}
			listaPrestacionesSolicitud.add(prestacionesSolicitud);
			wdwModalPrestaciones.detach();
		}
			else {
				Messagebox.show("Debe indicar el motivo de la prestación",
						"Alerta", Messagebox.OK, Messagebox.EXCLAMATION);
			}
		}
	}
	
	public void marcarPrestaciones (){
		for (int i = 0; i < listaPrestacionesSolicitud.size(); i++) {
			DetallePrestacion prestacion = listaPrestacionesSolicitud.get(i).getPrestacion(); 
			Motivo motivo = listaPrestacionesSolicitud.get(i).getMotivo();
			
			for (int j = 0; j < lbxPrestaciones.getItemCount(); j++) {
				Listitem listItem = lbxPrestaciones
						.getItemAtIndex(j);
				DetallePrestacion dp = listItem.getValue();
				if (prestacion.getId() == dp.getId()){
					listItem.setSelected(true);
					((Combobox) ((listItem.getChildren().get(1)))
							.getFirstChild()).setValue(motivo.getNombre());
				}
				}
			}
		}
	

}
