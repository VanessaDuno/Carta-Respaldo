/**
 * 
 */
package ssmc.CartaRespaldo.preparedstatement;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.naming.NamingException;

import ssmc.CartaRespaldo.controlador.maestros.CGenerico;

/** ConsultarPrestacionesSolicitud
 * @author Vanessa Maria Duno
 * @version 1.0
 */
public class ConsultarPrestacionesSolicitud extends CGenerico {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public String consultarPrestaciones(int id_solicitud){
	Connection con = null;
	PreparedStatement pst = null;

	ResultSet rs = null;
	String motivo =""; 

	try {
		con = Conexion(); 

		String querySql = "select  string_agg((p.descripcion || ' ' || d.nombre) ,', ') as prestacion from detalle_prestacion as d "
				+ "inner join prestacion as p on p.id_prestacion = d.id_prestacion "
				+ "inner join prestacion_solicitud as ps on ps.id_detalle_prestacion = d.id_detalle_prestacion and "
				+ "ps.id_solicitud_traslado = ?";

		pst = con.prepareStatement(querySql);
		pst.setInt(1, id_solicitud);
		
		 rs = pst.executeQuery();

		while (rs.next()) {
		   motivo = rs.getString(1);
		   System.out.println("Clave generada = " + motivo);
		}
	} catch (SQLException | NamingException ex) {
		Logger lgr = Logger
				.getLogger(PreparedStatement.class.getName());
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
			Logger lgr = Logger.getLogger(PreparedStatement.class
					.getName());
			lgr.log(Level.SEVERE, ex.getMessage(), ex);
		}
	}
	return motivo; 
	}

	/* (non-Javadoc)
	 * @see ssmc.CartaRespaldo.controlador.maestros.CGenerico#inicializar()
	 */
	@Override
	public void inicializar() throws IOException {
		// TODO Auto-generated method stub
		
	}
}