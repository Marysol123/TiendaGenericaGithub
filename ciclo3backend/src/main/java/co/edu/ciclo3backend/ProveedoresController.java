package co.edu.ciclo3backend;
//test
import java.util.ArrayList;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import co.edu.DAO.ciclo3backend.ProveedoresDAO;
import co.edu.DTO.ciclo3backend.ProveedoresVO;

@RestController
public class ProveedoresController {
	
	@RequestMapping("/listaProveedor")
	@CrossOrigin(origins="*")
	public ArrayList<ProveedoresVO> listaDeProveedores(){
		ProveedoresDAO dao = new ProveedoresDAO();
	    
	    return dao.listaProveedores();
	}
	
	@RequestMapping("/traerProveedor")
	@CrossOrigin(origins="*")
	public ArrayList<ProveedoresVO> buscarProveedorNit(String nitproveedor){
		ProveedoresDAO dao = new ProveedoresDAO();
	    
	    return dao.buscarProveedorNit(nitproveedor);
	}
	

	@RequestMapping("/crearProveedor")
	@CrossOrigin(origins="*")
	public boolean crearProveedor(String nitproveedor, String ciudad_proveedor, String direccion_proveedor, String nombre_proveedor, String telefono_proveedor) {
		
		ProveedoresVO Proveedor = new ProveedoresVO();
		

		Proveedor.setNitproveedor(Long.parseLong(nitproveedor));
		Proveedor.setCiudad_proveedor (ciudad_proveedor);
		Proveedor.setDireccion_proveedor (direccion_proveedor);
		Proveedor.setNombre_proveedor (nombre_proveedor);
		Proveedor.setTelefono_proveedor (telefono_proveedor);
		
		
		ProveedoresDAO dao = new ProveedoresDAO();
		
		return dao.crearProveedor(Proveedor);
	}

	@RequestMapping("/actualizarProveedor")
	@CrossOrigin(origins="*")
	public boolean actualizarUsuario(String nitproveedor, String ciudad_proveedor, String direccion_proveedor, String nombre_proveedor, String telefono_proveedor) {
		
		
		ProveedoresVO Proveedor = new ProveedoresVO();
		
		
		Proveedor.setNitproveedor(Long.parseLong(nitproveedor));
		Proveedor.setCiudad_proveedor (ciudad_proveedor);
		Proveedor.setDireccion_proveedor (direccion_proveedor);
		Proveedor.setNombre_proveedor (nombre_proveedor);
		Proveedor.setTelefono_proveedor (telefono_proveedor);
		
		ProveedoresDAO dao = new ProveedoresDAO();
		
		return dao.actualizarproveedor(Proveedor);	
	}
	
	@RequestMapping("/borrarProveedor")
	@CrossOrigin(origins="*")
	public boolean borrarProveedor(String nitproveedor) {
		ProveedoresDAO dao = new ProveedoresDAO();
		
		return dao.borrarproveedor(Long.parseLong(nitproveedor));
	}
}
