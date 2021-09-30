package co.edu.ciclo3backend;

import java.util.ArrayList;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.edu.DAO.ciclo3backend.UsuarioDAO;
import co.edu.DTO.ciclo3backend.UsuarioVO;

@RestController
public class ProveedoresController {
	
	@RequestMapping("/listaProveedor")
	public ArrayList<ProveedorVO> listaDeProveedores){
		ProveedorDAO dao = new ProveedorDAO();
	    
	    return dao.listaDeProveedores();
	}
	
	@RequestMapping("/traerProveedor")
	public ArrayList<ProveedorVO> buscarProveedorNit(String nitproveedor){
		ProveedorDAO dao = new ProveedoroDAO();
	    
	    return dao.buscarProveeddorNit(nitproveedor);
	}
	
	@RequestMapping("/crearProveedor")
	public boolean crearProveedor(String nitproveedor, String ciudad_proveedor, String direccion_proveedor, String nombre_proveedor, String telefono_proveedor,
	//		String password, String usuario)
			{
		
		ProveedorVO Proveedor new ProveedorVO();
						
		Proveedor.setnitproveedor(Long.parseLong(nitproveedor));
		Proveedor.setciudad_proveedor (ciudad_proveedor);
		Proveedor.setdireccion_proveedor (direccion_proveedor);
		Proveedor.setnombre_proveedor (nombre_proveedor);
		Proveedor.settelefono_proveedor (telefono_proveedor);
//		
		
		ProveedorDAO dao = new ProveedorDAO();
		
		return dao.crearProveedor(Proveedor);
	}
	
	@RequestMapping("/loginProveedor")
	public boolean existeproveedor(String nitproveedor, String nombre_proveedor){
		ProveedorDAO dao = new ProveedorDAO();
	    
	    return dao.existeproveedor(nitproveedor, nombre_proveedor);
	}
	

	
	/*
	@RequestMapping("/actualizarProveedor")
	public boolean actualizarUsuario(String nitproveedor, String ciudad_proveedor, String direccion_proveedor, String nombre_proveedor, String telefono_proveedor) {
		
		
		ProveedorVO Proveedor = new ProveedorVO();
		
		Proveedor.setnitproveedor(Long.parseLong(nitproveedor));
		Proveedor.setciudad_proveedor (ciudad_proveedor);
		Proveedor.setdireccion_proveedor (direccion_proveedor);
		Proveedor.setnombre_proveedor (nombre_proveedor);
		Proveedor.settelefono_proveedor (telefono_proveedor);
		
		ProveedorDAO dao = new ProveedorDAO();
		
		return dao.actualizarProveeedor(Proveedor);	
	}
	
	@RequestMapping("/borrarProveedor")
	public boolean borrarProveedor(String nitproveedor) {
		ProveedorDAO dao = new ProveedorDAO();
		
		return dao.borrarProveedor(Long.parseLong(nitproveedor));
	}*/
}
