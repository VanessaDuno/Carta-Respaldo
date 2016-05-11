package ssmc.CartaRespaldo.controlador.reporte;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ssmc.CartaRespaldo.controlador.transacciones.CSolicitudTraslado;
import net.sf.jasperreports.engine.JRException;

/**
 * Reportero
 * @author Vanessa Duno
 * @version 1.0
 *
 */

/**
 * Servlet implementation class Reportero
 */
@WebServlet("/Reportero")
public class Reportero extends HttpServlet {

	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Reportero() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		CSolicitudTraslado traslado = new CSolicitudTraslado(); 
		ServletOutputStream out;
		String par2 = request.getParameter("valor1");
		byte[] fichero = null;
		try {
			if (par2 != null){
				fichero = traslado.reporteTraslado(par2);
			}
			
		} catch (JRException e1) {

			e1.printStackTrace();
		}
		response.setContentType("application/pdf");
		response.setHeader("Content-disposition",
				"inline; filename=Reporte.pdf");
		response.setHeader("Cache-Control", "max-age=30");
		response.setHeader("Pragma", "No-cache");
		response.setDateHeader("Expires", 0);
		response.setContentLength(fichero.length);
		out = response.getOutputStream();
		out.write(fichero, 0, fichero.length);
		out.flush();
		out.close();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
