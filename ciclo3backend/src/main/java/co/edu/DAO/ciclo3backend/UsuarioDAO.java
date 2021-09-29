package co.edu.DAO.ciclo3backend;


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import co.edu.DTO.ciclo3backend.UsuarioVO;

public class UsuarioDAO {
	public ArrayList<UsuarioVO> listaDeusuario() {
		ArrayList<UsuarioVO> misusuarios = new ArrayList<UsuarioVO>();
		Conexion conexion = new Conexion();
		try {
			PreparedStatement consulta = conexion.getConnection().prepareStatement("SELECT * FROM usuarios");
			ResultSet res = consulta.executeQuery();
			while (res.next()) {
				
				UsuarioVO usuario = new UsuarioVO();
				
				usuario.setCedula_usuario(res.getLong("cedula_usuario"));
				usuario.setEmail_usuario(res.getString("email_usuario"));
				usuario.setNombre_usuario(res.getString("nombre_usuario"));
				usuario.setusuario(res.getString("password"));
				usuario.setpassword(res.getString("usuario"));
				misusuarios.add(usuario);
			}
			res.close();
			consulta.close();
			conexion.desconectar();
		}catch(Exception e) {
			System.out.println(e);
		}
		return misusuarios;
	}
	/***
	 * Busca un usuario por su cédula
	 * @param cedula
	 * @return ArrayList
	 */
	public ArrayList<UsuarioVO> buscarUsuarioCedula(String cedula){
		 
		ArrayList<UsuarioVO> misusuarios = new ArrayList<UsuarioVO>();
		Conexion conexion = new Conexion();
		try {
			PreparedStatement consulta = conexion.getConnection().prepareStatement("SELECT * FROM usuarios WHERE cedula_usuario = ?");
			
			consulta.setLong(1, Long.parseLong(cedula));
			ResultSet res = consulta.executeQuery();
			while(res.next()) {
				UsuarioVO usuario = new UsuarioVO();
				
				usuario.setCedula_usuario(res.getLong("Cedula_usuario"));
				usuario.setNombre_usuario(res.getString("nombre_usuario"));
				usuario.setEmail_usuario(res.getString("email_usuario"));
				usuario.setusuario(res.getString("nombre_usuario"));
				usuario.setpassword(res.getString("password_usuario"));
				misusuarios.add(usuario);
			}
			res.close();
			consulta.close();
			conexion.desconectar();
		}catch(Exception e){
			System.out.println(e);
			System.out.println("Aquí esta el error");
		}
		return misusuarios;
	}
	/***
	 * Busca a un usuario por su cédula
	 * @param cedula
	 * @return True si existe el usuario con esa cédula o false de lo contrario
	 */
	public boolean existeusuario(String password, String usuario ) {
		boolean existe = false;
		Conexion conexion = new Conexion();
		try {
			PreparedStatement consulta = conexion.getConnection().prepareStatement("SELECT * FROM usuarios WHERE password = '"+password+"' and usuario = '"+usuario+"'");
			
			consulta.setString(1,usuario);
			consulta.setString(2,usuario);
			ResultSet res = consulta.executeQuery();
			if(res.next()) {
				existe = true;
			}
			res.close();
			consulta.close();
			conexion.desconectar();
		}catch(Exception e) {
			System.out.println(e);
		}
		return existe;
	}
	
	/***
	 * Crea un nuevo usuario
	 * @param usuario
	 * @return True si la creación fue existosa o false de lo contrario
	 */
	public boolean crearUsuario(UsuarioVO usuario) {
		boolean creado = false;
		
		if(! this.existeusuario(usuario.getusuario(), usuario.getpassword())) {
			
			Conexion conexion = new Conexion();
			try {
				
				Statement consulta = conexion.getConnection().createStatement();
				
				
				String crearSql = "INSERT INTO usuarios (cedula_usuario, email_usuario, nombre_usuario, password, usuario) "
						+ " VALUES ("+usuario.getCedula_usuario()+", '"+usuario.getNombre_usuario()+"', "
								+ "'"+usuario.getEmail_usuario()+"', '"+usuario.getusuario()+"', "
										+ "'"+usuario.getpassword()+"')";
				consulta.executeUpdate(crearSql);
				consulta.close();
				conexion.desconectar();
				creado = true;
			}catch(SQLException e) {
				System.out.println(e);
			}
		}else {
			System.out.println("El usuario ya existe");
		}
		
		return creado;
	}
	
	/***
	 * Actualiza los datos de un usuario
	 * @param usuario
	 * @return True si se modificó al usuario o false de lo contrario
	
	public boolean actualizarusuario(usuarioVO usuario) {
		boolean actualizado = false;
		
		if( this.existeusuario(usuario.getCedula_usuario())) {
			Conexion conexion = new Conexion();
			try {
				Statement consulta = conexion.getConnection().createStatement();
				String actualizarSql = "UPDATE usuarios SET direccion_usuario ='"+usuario.getDireccion_usuario()+"', "
					+ " email_usuario = '"+usuario.getEmail_usuario()+"', nombre_usuario = '"+usuario.getNombre_usuario()+"', "
					    + " telefono_usuario = '"+usuario.getTelefono_usuario()+"' WHERE cedula_usuario = "+usuario.getCedula_usuario()+"";
				consulta.executeUpdate(actualizarSql);
				
				consulta.close();
				conexion.desconectar();
				actualizado = true;
			}catch(SQLException e) {
				System.out.println(e);
			}
		}else {
			System.out.println("El usuario no existe");
		}
		return actualizado;
	}
	
	public boolean borrarusuario(Long cedula) {
		boolean eliminado = false;
		
		if( this.existeusuario(cedula)) {
			Conexion conexion = new Conexion();
			try {
				Statement consulta = conexion.getConnection().createStatement();
				String eliminarSql = "DELETE FROM usuarios WHERE cedula_usuario = "+cedula+" ";
				consulta.executeUpdate(eliminarSql);
				
				consulta.close();
				conexion.desconectar();
				eliminado = true;
			}catch(SQLException e) {
				System.out.println(e);
			}
		}else {
			System.out.println("El usuario no existe");
		}
		
		return eliminado;
	}
	 */

}
