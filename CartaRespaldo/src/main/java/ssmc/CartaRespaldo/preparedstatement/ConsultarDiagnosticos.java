/**
 * 
 */
package ssmc.CartaRespaldo.preparedstatement;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.naming.NamingException;

import ssmc.CartaRespaldo.controlador.maestros.CGenerico;
import ssmc.CartaRespaldo.modelo.maestros.Diagnostico;

/**
 * ConsultarPrestacionesSolicitud
 * 
 * @author Vanessa Maria Duno
 * @version 1.0
 */
public class ConsultarDiagnosticos extends CGenerico {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public List<Diagnostico> consultarDiagnosticos() {
		List<Diagnostico> diagnosticos = new ArrayList<Diagnostico>();
		Connection con = null;
		PreparedStatement pst = null;

		ResultSet rs = null;

		try {
			con = Conexion(); 
			String querySql = "select id_diagnostico, codigo_cie, nombre, padre,hoja from diagnostico";

			pst = con.prepareStatement(querySql);

			rs = pst.executeQuery();
			while (rs.next()) {
				Diagnostico d = new Diagnostico(); 
				d.setId(rs.getLong(1));
				d.setCodigoCie(rs.getString(2));
				d.setNombre(rs.getString(3));
				d.setPadre(rs.getLong(4));
				d.setHoja(rs.getBoolean(5));
				diagnosticos.add(d); 
			}
		} catch (SQLException | NamingException ex) {
			Logger lgr = Logger.getLogger(PreparedStatement.class.getName());
			lgr.log(Level.SEVERE, ex.getMessage(), ex);
		} finally {
			try {
				if (pst != null) {
					pst.close();
				}
				if (con != null) {
					con.close();
				}
			} catch (SQLException ex) {
				Logger lgr = Logger
						.getLogger(PreparedStatement.class.getName());
				lgr.log(Level.SEVERE, ex.getMessage(), ex);
			}
		}
		return diagnosticos;
	}

	/* (non-Javadoc)
	 * @see ssmc.CartaRespaldo.controlador.maestros.CGenerico#inicializar()
	 */
	@Override
	public void inicializar() throws IOException {
		// TODO Auto-generated method stub
		
	}
}