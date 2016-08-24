/**
 * 
 */
package ssmc.CartaRespaldo.controlador.transacciones;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.zkoss.zk.ui.Sessions;
import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Tree;
import org.zkoss.zul.TreeModel;
import org.zkoss.zul.Window;

import ssmc.CartaRespaldo.arbol.MArbol;
import ssmc.CartaRespaldo.arbol.Nodos;
import ssmc.CartaRespaldo.controlador.maestros.CGenerico;
import ssmc.CartaRespaldo.modelo.maestros.Diagnostico;
import ssmc.CartaRespaldo.preparedstatement.ConsultarDiagnosticos;
import ssmc.CartaRespaldo.preparedstatement.ConsultarDiagnosticosPorNombre;

/**
 * @author Vanessa Maria Duno
 * 
 */
public class CModalDiagnostico extends CGenerico {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Wire
	private Tree arbolDiagnostico;
	@Wire
	private Window wdwDiagnostico;
	TreeModel _model;
	Textbox txt;
	@Wire
	private Textbox txtBuscarDiagnostico;
	boolean filtro = false;

	@Override
	public void inicializar() throws IOException {
		HashMap<String, Object> map = (HashMap<String, Object>) Sessions
				.getCurrent().getAttribute("itemsCatalogo");
		if (map != null) {
			txt = (Textbox) map.get("textbox");
		}
		arbolDiagnostico.setModel(getModel());
	}

	/* Permite asignarle los nodos cargados con el metodo getFooRoot() al arbol */
	public TreeModel getModel() {
		if (_model == null) {
			_model = new MArbol(getFooRoot());
		}
		return _model;
	}

	private Nodos getFooRoot() {

		Nodos root = new Nodos(null, 0, "", "");
		Nodos oneLevelNode = new Nodos(null, 0, "", "");
		Nodos twoLevelNode = new Nodos(null, 0, "", "");
		Nodos threeLevelNode = new Nodos(null, 0, "", "");
		Nodos fourLevelNode = new Nodos(null, 0, "", "");
		List<Diagnostico> arboles = new ArrayList<Diagnostico>();
		if (!filtro) {
			ConsultarDiagnosticos cs = new ConsultarDiagnosticos();
			arboles = cs.consultarDiagnosticos();
		} else {
			ConsultarDiagnosticosPorNombre dn = new ConsultarDiagnosticosPorNombre();
			arboles = dn.consultarDiagnosticos(txtBuscarDiagnostico.getValue().toUpperCase());
			for (int i = 0; i < arboles.size(); i++) {
				arboles.get(i).setPadre(0);
			}
		}

		long temp1, temp2, temp3 = 0;

		for (int i = 0; i < arboles.size(); i++) {

			if (arboles.get(i).getPadre() == 0) {
				oneLevelNode = new Nodos(root, i, arboles.get(i).getNombre(),
						"");
				root.appendChild(oneLevelNode);
				temp1 = arboles.get(i).getId();
				arboles.remove(i);

				for (int j = i; j < arboles.size(); j++) {

					if (temp1 == arboles.get(j).getPadre()) {
						twoLevelNode = new Nodos(oneLevelNode, i, arboles
								.get(j).getNombre(), "");
						oneLevelNode.appendChild(twoLevelNode);
						temp2 = arboles.get(j).getId();
						arboles.remove(j);

						for (int k = j; k < arboles.size(); k++) {

							if (temp2 == arboles.get(k).getPadre()) {
								threeLevelNode = new Nodos(twoLevelNode, i,
										arboles.get(k).getNombre(), "");
								twoLevelNode.appendChild(threeLevelNode);
								temp3 = arboles.get(k).getId();
								arboles.remove(k);

								for (int z = k; z < arboles.size(); z++) {

									if (temp3 == arboles.get(z).getPadre()) {
										fourLevelNode = new Nodos(
												threeLevelNode, i, arboles.get(
														z).getNombre(), "");
										threeLevelNode
												.appendChild(fourLevelNode);
										arboles.remove(z);

										z = z - 1;
									}
								}
								k = k - 1;
							}
						}
						j = j - 1;
					}
				}
				i = i - 1;
			}
		}
		return root;
	}

	@Listen("onClick = #arbolDiagnostico")
	public void selectedNode() {
		if (arbolDiagnostico.getSelectedItem() != null) {
			String item = String.valueOf(arbolDiagnostico.getSelectedItem()
					.getValue());
			Diagnostico d = new Diagnostico();
			d = servicioDiagnostico.buscarNombre(item);
			if (d.isHoja()) {
				Sessions.getCurrent().setAttribute("diagnostico", item);
				Sessions.getCurrent().setAttribute("text", txt);
				wdwDiagnostico.onClose();
				CSolicitudTraslado st = new CSolicitudTraslado();
				st.obtenerDiagnostico();
			}
		}
	}

	@Listen("onOK = #txtBuscarDiagnostico")
	public void crearArbolPorNombre() {
		_model = null;
		if (!txtBuscarDiagnostico.getValue().equals("")) {
			filtro = true;
		} else {
			filtro = false;
		}
		arbolDiagnostico.setModel(getModel());
	}

}
