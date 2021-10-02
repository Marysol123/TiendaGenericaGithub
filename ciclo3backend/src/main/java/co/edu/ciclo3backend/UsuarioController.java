package co.edu.ciclo3backend;

import java.util.ArrayList;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.edu.DAO.ciclo3backend.UsuarioDAO;
import co.edu.DTO.ciclo3backend.UsuarioVO;

@RestController
public class UsuarioController {
	
	@RequestMapping("/listaUsuario")
	public ArrayList<UsuarioVO> listaDeusuario(){
		UsuarioDAO dao = new UsuarioDAO();
	    
	    return dao.listaDeusuario();
	}
	
	@RequestMapping("/traerUsuario")
	public ArrayList<UsuarioVO> buscarUsuarioCedula(String cedula){
		UsuarioDAO dao = new UsuarioDAO();
	    
	    return dao.buscarUsuarioCedula(cedula);
	}
	
	@RequestMapping("/crearUsuario")
	public boolean crearUsuario(String cedula_usuario, String email_usuario, String nombre_usuario,
			String password, String usuario) {
		
		UsuarioVO Usuario = new UsuarioVO();
		
		Usuario.setCedula_usuario(Long.parseLong(cedula_usuario));
		Usuario.setEmail_usuario(email_usuario);
		Usuario.setNombre_usuario(nombre_usuario);
		Usuario.setpassword(password);
		Usuario.setusuario(usuario);
		
		UsuarioDAO dao = new UsuarioDAO();
		
		return dao.crearUsuario(Usuario);
	}
	
	@RequestMapping("/loginUsuario")
	public boolean loginUsuario(String password, String usuario){
		UsuarioDAO dao = new UsuarioDAO();
	    
	    return dao.existeusuario(password, usuario);
	}
	

	

	@RequestMapping("/actualizarUsuario")
	public boolean actualizarUsuario(String cedula_usuario, String email_usuario, String nombre_usuario,
			String password, String usuario) {
		
		
		UsuarioVO Usuario = new UsuarioVO();
		
		Usuario.setCedula_usuario(Long.parseLong(cedula_usuario));
		Usuario.setEmail_usuario(email_usuario);
		Usuario.setNombre_usuario(nombre_usuario);
		Usuario.setpassword(password);
		Usuario.setusuario(usuario);
		
		UsuarioDAO dao = new UsuarioDAO();
		
		
		return dao.actualizarusuario(Usuario);	
	}
	
	
	
	@RequestMapping("/borrarUsuario")
	public boolean borrarUsuario(String cedula) {
		UsuarioDAO dao = new UsuarioDAO();
		
		return dao.borrarUsuario(Long.parseLong(cedula));
	}
}
