package co.edu.DAO.ciclo3backend;

import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import co.edu.DTO.ciclo3backend.UsuarioVO;
import co.edu.DTO.ciclo3backend.VentasVO;

public class VentasDAO {
	public ArrayList<VentasVO> listaDeVentas() {
		ArrayList<VentasVO> misVentas = new ArrayList<VentasVO>();
		Conexion conexion = new Conexion();
		try {
			PreparedStatement consulta = conexion.getConnection().prepareStatement("SELECT cedula_cliente FROM ventas");
			ResultSet res = consulta.executeQuery();
			Set<String> cedulas = new HashSet<String> (); 
			while (res.next()) {
				String cedula = res.getString("cedula_cliente");
				cedulas.add(cedula);
			}
			res.close();
			consulta.close();
			
			
			for (String cedula : cedulas) {
				PreparedStatement consulta2 = conexion.getConnection().prepareStatement("SELECT total_venta FROM ventas "
						+ "WHERE cedula_cliente = '"+cedula+"'   ");
				ResultSet res2 = consulta2.executeQuery();
				double valor_venta = 0;	
				
				while(res2.next()) {
					valor_venta = valor_venta + res2.getDouble("total_venta");
				}
				VentasVO ventas = new VentasVO();
				String nombre = this.buscarClienteCedula(cedula);
				ventas.setCedula_cliente(Long.parseLong(cedula));
				ventas.setNombre_cliente(nombre);
				ventas.setValorTotalVentas((long) valor_venta);
				misVentas.add(ventas);
				
				res2.close();
				consulta2.close();
			}
			
			conexion.desconectar();
		}catch(Exception e) {
			System.out.println(e);
		}
		return misVentas;
	}
	
	
	public String buscarClienteCedula(String cedula) {
		String nombre ="";
		Conexion conexion = new Conexion();
		try {
			PreparedStatement consulta = conexion.getConnection().prepareStatement("SELECT nombre_cliente "
					+ " FROM clientes WHERE cedula_cliente = ?");
			consulta.setLong(1, Long.parseLong(cedula));
			ResultSet res = consulta.executeQuery();
			while(res.next()) {
				nombre = res.getString("nombre_cliente");	
			}
			res.close();
			consulta.close();
			conexion.desconectar();
		}catch(Exception e) {
			System.out.println(e);
		}
		return nombre;
	}
	public String buscarNombreProducto(String codigoProducto) {
		String cantidad = "";
		Conexion conexion = new Conexion();
		try {
			PreparedStatement consulta = conexion.getConnection().prepareStatement("SELECT nombre_producto "
					+ " FROM productos WHERE codigo_producto = ?");
			consulta.setLong(1, Long.parseLong(codigoProducto));
			ResultSet res = consulta.executeQuery();
			while(res.next()) {
				cantidad = res.getString("nombre_producto");	
			}
			res.close();
			consulta.close();
			conexion.desconectar();
		}catch(Exception e) {
			System.out.println(e);
		}
		return cantidad;	
	}
	
	public float traerPrecio(String codigoProducto) {
		float precio = 0;
		Conexion conexion = new Conexion();
		try {
			PreparedStatement consulta = conexion.getConnection().prepareStatement("SELECT precio_venta "
					+ " FROM productos WHERE codigo_producto = ?");
			consulta.setLong(1, Long.parseLong(codigoProducto));
			ResultSet res = consulta.executeQuery();
			while(res.next()) {
				precio = res.getFloat("precio_venta");
			}
			res.close();
			consulta.close();
			conexion.desconectar();
		}catch(Exception e) {
			System.out.println(e);
		}
		return precio;	
	}
	
	public int confirmarVenta(String cedulaCliente, String cedulaUsuario, double ivaVenta, double totalVenta, double valorVenta) {
		int rta = 0;
		
		Conexion conexion = new Conexion();
			
		try {	
			
			String insertar = "INSERT INTO ventas (cedula_cliente, cedula_usuario, ivaventa, total_venta, valor_venta)  "
					+ "VALUES("+Long.parseLong(cedulaCliente)+", "+Long.parseLong(cedulaUsuario)+","
					+ " "+ivaVenta+", "+totalVenta+", "+valorVenta+" )";
			PreparedStatement consulta = conexion.getConnection().prepareStatement(insertar, Statement.RETURN_GENERATED_KEYS);	
			int affectedRows = consulta.executeUpdate();
			if (affectedRows == 0) {
		        throw new SQLException("No se pudo guardar");
		    }
			ResultSet generatedKeys = consulta.getGeneratedKeys();
			if (generatedKeys.next()) {
		         int idGenerado = generatedKeys.getInt(1);
		         rta = idGenerado;
		    }
		    
			
			generatedKeys.close();
			consulta.close();
			conexion.desconectar();
				
			}catch(SQLException e) {
				System.out.println(e);
			}
		return rta;
	}
	
	public int registroVentas(
			long cedula_cliente, long cedula_usuario, double ivaventa, double total_venta, double valor_venta,  
			int cantidad_producto1, long codigo_producto1, double valor_venta1, double valoriva1, double valor_total1,
			int cantidad_producto2, long codigo_producto2, double valor_venta2, double valoriva2, double valor_total2,
			int cantidad_producto3, long codigo_producto3, double valor_venta3, double valoriva3, double valor_total3
			) {
		int rta = 0;
		
		Conexion conexion = new Conexion();
		try {
			conexion.getConnection().setAutoCommit(false);
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			
			String insertVentas = "INSERT INTO ventas(cedula_cliente, "
					+ "cedula_usuario, ivaventa, total_venta, valor_venta) "
					+ "VALUES (?,?,?,?,?)";
			
			PreparedStatement consulta = conexion.getConnection().prepareStatement(insertVentas, Statement.RETURN_GENERATED_KEYS);
			
			consulta.setLong(1, cedula_cliente);
			consulta.setLong(2, cedula_usuario);
			consulta.setDouble(3, ivaventa);
			consulta.setDouble(4, total_venta);
			consulta.setDouble(5, valor_venta);
			
			int rowAffected = consulta.executeUpdate();
			
			// Obtener PK generada
			ResultSet rs = consulta.getGeneratedKeys();
            int candidateId = 0;
            if(rs.next())
                candidateId = rs.getInt(1);
            
            if(rowAffected == 1)
            {
            	// INGRESO LOS DETALLADOS DE VENTAS
            	String insertDetalle = "INSERT INTO detalle_ventas(cantidad_producto, "
    					+ "codigo_producto, codigo_venta, valor_total, valor_venta, valoriva) "
    					+ "VALUES (?,?,?,?,?,?)";
            	
				if(codigo_producto1 != -1) {
					consulta = conexion.getConnection().prepareStatement(insertDetalle);
	    			consulta.setInt(1, cantidad_producto1);
	    			consulta.setLong(2, codigo_producto1);
	    			consulta.setLong(3, candidateId);
	    			consulta.setDouble(4, valor_total1);
	    			consulta.setDouble(5, valor_venta1);
	    			consulta.setDouble(6, valoriva1);
	    			consulta.executeUpdate();
				}
				if(codigo_producto2 != -1) {
					consulta = conexion.getConnection().prepareStatement(insertDetalle);
	    			consulta.setInt(1, cantidad_producto2);
	    			consulta.setLong(2, codigo_producto2);
	    			consulta.setLong(3, candidateId);
	    			consulta.setDouble(4, valor_total2);
	    			consulta.setDouble(5, valor_venta2);
	    			consulta.setDouble(6, valoriva2);
	    			consulta.executeUpdate();
				}
				if(codigo_producto3 != -1) {
					consulta = conexion.getConnection().prepareStatement(insertDetalle);
	    			consulta.setInt(1, cantidad_producto3);
	    			consulta.setLong(2, codigo_producto3);
	    			consulta.setLong(3, candidateId);
	    			consulta.setDouble(4, valor_total3);
	    			consulta.setDouble(5, valor_venta3);
	    			consulta.setDouble(6, valoriva3);
	    			consulta.executeUpdate();
				}
				conexion.getConnection().commit();
				rta = candidateId;
            } else {
            	conexion.getConnection().rollback();
            }
			consulta.close();
			conexion.desconectar();
		}catch(SQLException e) {
			System.out.println(e);
		}		
		return rta;

	}
	
	

}
