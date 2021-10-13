package co.edu.ciclo3backend;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.edu.DAO.ciclo3backend.VentasDAO;

@RestController
public class VentasController {
	@RequestMapping("/nombreCliente")
	@CrossOrigin(origins="*")
	public String buscarClienteCedula(String cedula) {
		VentasDAO dao = new VentasDAO();
		
		return dao.buscarClienteCedula(cedula);
	}
	@RequestMapping("/nombreProducto")
	@CrossOrigin(origins="*")
	public String buscarNombreProducto(String codigoProducto) {
		VentasDAO dao = new VentasDAO();
		
		return dao.buscarNombreProducto(codigoProducto);
	}
	@RequestMapping("/traerPrecio")
	@CrossOrigin(origins="*")
	public float traerPrecio(String codigoProducto) {
		VentasDAO dao = new VentasDAO();
		
		return dao.traerPrecio(codigoProducto);
	}
	@RequestMapping("/confirmarVenta")
	@CrossOrigin(origins="*")
	public int confirmarVenta(String cedulaCliente, String cedulaUsuario, String ivaVenta, String totalVenta, String valorVenta){
		
		VentasDAO dao = new VentasDAO();
		return dao.confirmarVenta(cedulaCliente, cedulaUsuario, Double.parseDouble(ivaVenta), Double.parseDouble(totalVenta), 
				Double.parseDouble(valorVenta)  );
		
		
	}
	
	@RequestMapping("/registroVentas")
	@CrossOrigin(origins="*")
	public boolean registroVentas(String cantidad1, String cantidad2, String cantidad3, String codigo1, String codigo2, 
			String codigo3, String codigoVenta, String valorVenta1, String valorVenta2, String valorVenta3 ) {
		VentasDAO dao = new VentasDAO();
		return dao.registroVentas(Integer.parseInt(cantidad1), Integer.parseInt(cantidad2), Integer.parseInt(cantidad3), 
				Long.parseLong(codigo1), Long.parseLong(codigo2), Long.parseLong(codigo3), Long.parseLong(codigoVenta), 
				Double.parseDouble(valorVenta1), Double.parseDouble(valorVenta2), Double.parseDouble(valorVenta3));
		  
			
	}
	

}
