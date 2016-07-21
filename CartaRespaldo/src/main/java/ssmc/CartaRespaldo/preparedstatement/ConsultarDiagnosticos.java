/**
 * 
 */
package ssmc.CartaRespaldo.preparedstatement;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import ssmc.CartaRespaldo.modelo.maestros.Diagnostico;

/**
 * ConsultarPrestacionesSolicitud
 * 
 * @author Vanessa Maria Duno
 * @version 1.0
 */
public class ConsultarDiagnosticos {

	public List<Diagnostico> consultarDiagnosticos() {
		List<Diagnostico> diagnosticos = new ArrayList<Diagnostico>();
		Connection con = null;
		PreparedStatement pst = null;

		String url = "jdbc:postgresql://localhost:5432/carta_respaldo";
		String user = "postgres";
		String password = "postgres";
		ResultSet rs = null;

		try {
			con = DriverManager.getConnection(url, user, password);

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
		} catch (SQLException ex) {
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
}