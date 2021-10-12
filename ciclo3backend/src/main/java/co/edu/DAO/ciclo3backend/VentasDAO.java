package co.edu.DAO.ciclo3backend;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import co.edu.DTO.ciclo3backend.UsuarioVO;

public class VentasDAO {
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
			String insertar = "INSERT INTO ventas VALUES(NULL, "+Long.parseLong(cedulaCliente)+", "+Long.parseLong(cedulaUsuario)+","
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
	
	public boolean registroVentas(int cantidad1, int cantidad2, int cantidad3, Long codigo1, Long codigo2, Long codigo3, 
			Long codigoVenta, double valorVenta1, double valorVenta2, double valorVenta3) {
		boolean rta = false;
		
		Conexion conexion = new Conexion();
		try {
			
			Statement consulta = conexion.getConnection().createStatement();
			
			double valorIva1 = valorVenta1 * 0.19;
			double valorTotal1 = valorVenta1 + valorIva1;
			
			String crearSql1 = "INSERT INTO detalle_ventas (codigo_detalle_venta, cantidad_producto, codigo_producto, "
					+ " codigo_venta, valor_total, valor_venta, valoriva) "
					+ " VALUES (NULL, "+cantidad1+", "+codigo1+" ,"+codigoVenta+", "+valorTotal1+", "+valorVenta1+", "+valorIva1+")";
			
			double valorIva2 = valorVenta2 * 0.19;
			double valorTotal2 = valorVenta2 + valorIva2;
			
			String crearSql2 = "INSERT INTO detalle_ventas (codigo_detalle_venta, cantidad_producto, codigo_producto, "
					+ " codigo_venta, valor_total, valor_venta, valoriva) "
					+ " VALUES (NULL, "+cantidad2+", "+codigo2+" ,"+codigoVenta+", "+valorTotal2+", "+valorVenta2+", "+valorIva2+")";
			
			double valorIva3 = valorVenta3 * 0.19;
			double valorTotal3 = valorVenta3 + valorIva3;
			
			String crearSql3 = "INSERT INTO detalle_ventas (codigo_detalle_venta, cantidad_producto, codigo_producto, "
					+ " codigo_venta, valor_total, valor_venta, valoriva) "
					+ " VALUES (NULL, "+cantidad3+", "+codigo3+" ,"+codigoVenta+", "+valorTotal3+", "+valorVenta3+", "+valorIva3+")";
			
			consulta.executeUpdate(crearSql3);
			
			consulta.close();
			conexion.desconectar();
			rta = true;
		}catch(SQLException e) {
			System.out.println(e);
		}
		
		return rta;
		
		
	}
	
	

}
