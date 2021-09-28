package co.edu.DAO.ciclo3backend;


import java.sql.*;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import co.edu.DTO.ciclo3backend.ClienteVO;

public class ClienteDAO {
	public ArrayList<ClienteVO> listaDeClientes() {
		ArrayList<ClienteVO> misClientes = new ArrayList<ClienteVO>();
		Conexion conexion = new Conexion();
		try {
			PreparedStatement consulta = conexion.getConnection().prepareStatement("SELECT * FROM clientes");
			ResultSet res = consulta.executeQuery();
			while (res.next()) {
				ClienteVO cliente = new ClienteVO();
				
				cliente.setCedula_cliente(res.getLong("cedula_cliente"));
				cliente.setDireccion_cliente(res.getString("direccion_cliente"));
				cliente.setEmail_cliente(res.getString("email_cliente"));
				cliente.setNombre_cliente(res.getString("nombre_cliente"));
				cliente.setTelefono_cliente(res.getString("telefono_cliente"));
				misClientes.add(cliente);
			}
			res.close();
			consulta.close();
			conexion.desconectar();
		}catch(Exception e) {
			System.out.println(e);
		}
		return misClientes;
	}
	/***
	 * Busca un cliente por su cédula
	 * @param cedula
	 * @return ArrayList
	 */
	public ArrayList<ClienteVO> buscarClienteCedula(String cedula){
		 
		ArrayList<ClienteVO> misClientes = new ArrayList<ClienteVO>();
		Conexion conexion = new Conexion();
		try {
			PreparedStatement consulta = conexion.getConnection().prepareStatement("SELECT * FROM clientes WHERE cedula_cliente = ?");
			
			consulta.setLong(1, Long.parseLong(cedula));
			ResultSet res = consulta.executeQuery();
			while(res.next()) {
				ClienteVO cliente = new ClienteVO();
				
				cliente.setCedula_cliente(res.getLong("cedula_cliente"));
				cliente.setDireccion_cliente(res.getString("direccion_cliente"));
				cliente.setEmail_cliente(res.getString("email_cliente"));
				cliente.setNombre_cliente(res.getString("nombre_cliente"));
				cliente.setTelefono_cliente(res.getString("telefono_cliente"));
				misClientes.add(cliente);
			}
			res.close();
			consulta.close();
			conexion.desconectar();
		}catch(Exception e){
			System.out.println(e);
			System.out.println("Aquí esta el error");
		}
		return misClientes;
	}
	/***
	 * Busca a un cliente por su cédula
	 * @param cedula
	 * @return True si existe el cliente con esa cédula o false de lo contrario
	 */
	public boolean existeCliente(Long cedula) {
		boolean existe = false;
		Conexion conexion = new Conexion();
		try {
			PreparedStatement consulta = conexion.getConnection().prepareStatement("SELECT * FROM clientes WHERE cedula_cliente = ?");
			
			consulta.setLong(1, cedula);
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
	 * Crea un nuevo cliente
	 * @param cliente
	 * @return True si la creación fue existosa o false de lo contrario
	 */
	public boolean crearCliente(ClienteVO cliente) {
		boolean creado = false;
		
		if(! this.existeCliente(cliente.getCedula_cliente())) {
			Conexion conexion = new Conexion();
			
			try {
				Statement consulta = conexion.getConnection().createStatement();
				
				String crearSql = "INSERT INTO clientes (cedula_cliente, direccion_cliente, email_cliente, nombre_cliente, telefono_cliente) "
						+ " VALUES ("+cliente.getCedula_cliente()+", '"+cliente.getDireccion_cliente()+"', "
								+ "'"+cliente.getEmail_cliente()+"', '"+cliente.getNombre_cliente()+"', "
										+ "'"+cliente.getTelefono_cliente()+"')";
				consulta.executeUpdate(crearSql);
				consulta.close();
				conexion.desconectar();
				creado = true;
			}catch(SQLException e) {
				System.out.println(e);
			}
		}else {
			System.out.println("El cliente ya existe");
		}
		
		return creado;
	}
	
	/***
	 * Actualiza los datos de un cliente
	 * @param cliente
	 * @return True si se modificó al cliente o false de lo contrario
	 */
	public boolean actualizarCliente(ClienteVO cliente) {
		boolean actualizado = false;
		
		if( this.existeCliente(cliente.getCedula_cliente())) {
			Conexion conexion = new Conexion();
			try {
				Statement consulta = conexion.getConnection().createStatement();
				String actualizarSql = "UPDATE clientes SET direccion_cliente ='"+cliente.getDireccion_cliente()+"', "
					+ " email_cliente = '"+cliente.getEmail_cliente()+"', nombre_cliente = '"+cliente.getNombre_cliente()+"', "
					    + " telefono_cliente = '"+cliente.getTelefono_cliente()+"' WHERE cedula_cliente = "+cliente.getCedula_cliente()+"";
				consulta.executeUpdate(actualizarSql);
				
				consulta.close();
				conexion.desconectar();
				actualizado = true;
			}catch(SQLException e) {
				System.out.println(e);
			}
		}else {
			System.out.println("El cliente no existe");
		}
		return actualizado;
	}
	
	public boolean borrarCliente(Long cedula) {
		boolean eliminado = false;
		
		if( this.existeCliente(cedula)) {
			Conexion conexion = new Conexion();
			try {
				Statement consulta = conexion.getConnection().createStatement();
				String eliminarSql = "DELETE FROM clientes WHERE cedula_cliente = "+cedula+" ";
				consulta.executeUpdate(eliminarSql);
				
				consulta.close();
				conexion.desconectar();
				eliminado = true;
			}catch(SQLException e) {
				System.out.println(e);
			}
		}else {
			System.out.println("El cliente no existe");
		}
		
		return eliminado;
	}
	

}
