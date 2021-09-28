package co.edu.ciclo3backend;

import java.util.ArrayList;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.edu.DAO.ciclo3backend.ClienteDAO;
import co.edu.DTO.ciclo3backend.ClienteVO;

@RestController
public class ClienteController {
	
	@RequestMapping("/listaClientes")
	public ArrayList<ClienteVO> listaDeClientes(){
	    ClienteDAO dao = new ClienteDAO();
	    
	    return dao.listaDeClientes();
	}
	
	@RequestMapping("/traerCliente")
	public ArrayList<ClienteVO> buscarClienteCedula(String cedula){
	    ClienteDAO dao = new ClienteDAO();
	    
	    return dao.buscarClienteCedula(cedula);
	}
	
	@RequestMapping("/crearCliente")
	public boolean crearCliente(String cedula, String direccion, String email,
			String nombre, String telefono) {
		
		ClienteVO cliente = new ClienteVO();
		
		cliente.setCedula_cliente(Long.parseLong(cedula));
		cliente.setDireccion_cliente(direccion);
		cliente.setEmail_cliente(email);
		cliente.setNombre_cliente(nombre);
		cliente.setTelefono_cliente(telefono);
		
		ClienteDAO dao = new ClienteDAO();
		
		return dao.crearCliente(cliente);
	}
	
	@RequestMapping("/actualizarCliente")
	public boolean actualizarCliente(String cedula, String direccion, String email,
			String nombre, String telefono) {
		
		
        ClienteVO cliente = new ClienteVO();
		
		cliente.setCedula_cliente(Long.parseLong(cedula));
		cliente.setDireccion_cliente(direccion);
		cliente.setEmail_cliente(email);
		cliente.setNombre_cliente(nombre);
		cliente.setTelefono_cliente(telefono);
		
		ClienteDAO dao = new ClienteDAO();
		
		return dao.actualizarCliente(cliente);	
	}
	
	@RequestMapping("/borrarCliente")
	public boolean borrarCliente(String cedula) {
		ClienteDAO dao = new ClienteDAO();
		
		return dao.borrarCliente(Long.parseLong(cedula));
	}
}
