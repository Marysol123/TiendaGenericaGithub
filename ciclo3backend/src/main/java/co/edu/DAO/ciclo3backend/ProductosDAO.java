package co.edu.DAO.ciclo3backend;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import co.edu.DTO.ciclo3backend.ProductosVO;


public class ProductosDAO {
	public ArrayList<ProductosVO> listaProductos() {
		ArrayList<ProductosVO> misproductos = new ArrayList<ProductosVO>();
		Conexion conexion = new Conexion();
		try {
			PreparedStatement consulta = conexion.getConnection().prepareStatement("SELECT * FROM productos");
			ResultSet res = consulta.executeQuery();
			while (res.next()) {
				
				ProductosVO Producto = new ProductosVO();
				
				Producto.setCodigo_producto(res.getLong("codigo_producto"));
				Producto.setNombre_producto(res.getString ("nombre_producto"));
				Producto.setNitproveedor(res.getLong ("nitproveedor"));
				Producto.setPrecio_compra(res.getDouble ("precio_compra"));
				Producto.setIvacompra(res.getDouble ("ivacompra"));
				Producto.setPrecio_venta(res.getDouble ("precio_venta"));
				misproductos.add(Producto);
										
			}
			res.close();
			consulta.close();
			conexion.desconectar();
		}catch(Exception e) {
			System.out.println(e);
		}
		return misproductos;
	}
	/***
	 * Busca un productos por Codigo del producto
	 * @param Codigo del producto
	 * @return ArrayList
	 */
	public ArrayList<ProductosVO>buscarProductoCod(String codigo_producto){
		 
		ArrayList<ProductosVO> misproductos = new ArrayList<ProductosVO>();
		Conexion conexion = new Conexion();
		try {
			PreparedStatement consulta = conexion.getConnection().prepareStatement("SELECT * FROM productos WHERE codigo_producto = ?");
			
			consulta.setLong(1, Long.parseLong(codigo_producto));
			ResultSet res = consulta.executeQuery();
			while(res.next()) {
				ProductosVO Producto = new ProductosVO();
				
				Producto.setCodigo_producto(res.getLong("codigo_producto"));
				Producto.setNombre_producto(res.getString ("nombre_producto"));
				Producto.setNitproveedor(res.getLong ("nitproveedor"));
				Producto.setPrecio_compra(res.getDouble ("precio_compra"));
				Producto.setIvacompra(res.getDouble ("ivacompra"));
				Producto.setPrecio_venta(res.getDouble ("precio_venta"));
				misproductos.add(Producto);
				
												
			}
			res.close();
			consulta.close();
			conexion.desconectar();
		}catch(Exception e){
			System.out.println(e);
			System.out.println("Aquí esta el error");
		}
		return misproductos;
	}
	/***
	 * Busca  un producto por codigo del producto
	 * @param codigo del productoT
	 * @return True si existe el producto con este codigo asociadp o false si es lo contrario
	 */
	public boolean existeproducto(Long codigo_producto ) {
		boolean existe = false;
		Conexion conexion = new Conexion();
		try {
			PreparedStatement consulta = conexion.getConnection().prepareStatement("SELECT * FROM productos WHERE codigo_producto = ?");
			
			consulta.setLong(1,codigo_producto);
			
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
	 * Crea un nuevo producto
	 * @param producto
	 * @return True si la creación del nuevo producto fue existosa o false si es lo contrario
	 */
	public boolean crearProducto(ProductosVO productos) {
		boolean creado = false;
		
		if(! this.existeproducto(productos.getCodigo_producto())) {
			
			Conexion conexion = new Conexion();
			try {
				
				Statement consulta = conexion.getConnection().createStatement();
				
				
				String crearSql = "INSERT INTO productos (codigo_producto, ivacompra,nitproveedor, nombre_producto, precio_compra, precio_venta) "
						+ " VALUES ("+productos.getCodigo_producto()+","+ productos.getIvacompra()+ ","
								+productos.getNitproveedor()+",'"
										+productos.getNombre_producto()+"',"
										+productos.getIvacompra()+","+productos.getPrecio_venta()+")";
				consulta.executeUpdate(crearSql);
				consulta.close();
				conexion.desconectar();
				creado = true;
			}catch(SQLException e) {
				System.out.println(e);
			}
		}else {
			System.out.println("El producto ya existe");
		}
		
		return creado;
	}
	
	/***
	 * Actualizar los datos de un producto
	 * @param producto
	 * @return True si se modificó el producto o false de lo contrario
	 * */
	
	public boolean actualizarproducto(ProductosVO producto) {
		boolean actualizado = false;
		
		if( this.existeproducto(producto.getCodigo_producto())) {
			Conexion conexion = new Conexion();
			try {
				Statement consulta = conexion.getConnection().createStatement();
				String actualizarSql = "UPDATE productos SET codigo_producto ="+producto.getCodigo_producto()+", "
						+ "ivacompra = "+producto.getIvacompra()+","+ "nombre_producto = '"+producto.getNombre_producto()+"',"
							+ "precio_compra = "+producto.getPrecio_compra()+", "+
					     "precio_venta = "+producto.getPrecio_venta()+" WHERE nitproveedor = "+producto.getNitproveedor()+"";
				consulta.executeUpdate(actualizarSql);
				
				consulta.close();
				conexion.desconectar();
				actualizado = true;
			}catch(SQLException e) {
				System.out.println(e);
			}
		}else {
			System.out.println("El producto no existe");
		}
		return actualizado;
	}
	
	public boolean borrarproducto(Long codigo_producto) {
		boolean eliminado = false;
		
		if( this.existeproducto(codigo_producto)) {
			Conexion conexion = new Conexion();
			try {
				Statement consulta = conexion.getConnection().createStatement();
				String eliminarSql = "DELETE FROM productos WHERE codigo_producto = "+codigo_producto+" ";
				consulta.executeUpdate(eliminarSql);
				
				consulta.close();
				conexion.desconectar();
				eliminado = true;
			}catch(SQLException e) {
				System.out.println(e);
			}
		}else {
			System.out.println("El producto no existe");
		}
		
		return eliminado;
	}
	 

}


