package co.edu.ciclo3backend;

import java.util.ArrayList;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.edu.DAO.ciclo3backend.VentasDAO;
import co.edu.DTO.ciclo3backend.VentasVO;

@RestController
public class VentasController {
	@RequestMapping("/listaVentas")
	@CrossOrigin(origins="*")
	public ArrayList<VentasVO> listaDeVentas(){
		VentasDAO dao = new VentasDAO();
	    
	    return dao.listaDeVentas();
	}
	
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
	public int registroVentas(String cedula_cliente, String cedula_usuario, String ivaventa, String total_venta, String valor_venta, 
			String cantidad_producto1, String codigo_producto1, String valor_venta1, String valoriva1, String valor_total1,
			String cantidad_producto2, String codigo_producto2, String valor_venta2, String valoriva2, String valor_total2,
			String cantidad_producto3, String codigo_producto3, String valor_venta3, String valoriva3, String valor_total3) {
		VentasDAO dao = new VentasDAO();
		
		return dao.registroVentas(Long.parseLong(cedula_cliente), Long.parseLong(cedula_usuario), Double.parseDouble(ivaventa), Double.parseDouble(total_venta), Double.parseDouble(valor_venta),  
				Integer.parseInt(cantidad_producto1), Long.parseLong(codigo_producto1), Double.parseDouble(valor_venta1), Double.parseDouble(valoriva1), Double.parseDouble(valor_total1),
				Integer.parseInt(cantidad_producto2), Long.parseLong(codigo_producto2), Double.parseDouble(valor_venta2), Double.parseDouble(valoriva2), Double.parseDouble(valor_total2),
				Integer.parseInt(cantidad_producto3), Long.parseLong(codigo_producto3), Double.parseDouble(valor_venta3), Double.parseDouble(valoriva3), Double.parseDouble(valor_total3)
				);
		
		
	}
	

}
