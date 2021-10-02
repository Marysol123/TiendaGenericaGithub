package co.edu.DAO.ciclo3backend;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import co.edu.DTO.ciclo3backend.ProveedoresVO;

public class ProveedoresDAO {
	public ArrayList<ProveedoresVO> listaProveedores() {
		ArrayList<ProveedoresVO> misproveedores = new ArrayList<ProveedoresVO>();
		Conexion conexion = new Conexion();
		try {
			PreparedStatement consulta = conexion.getConnection().prepareStatement("SELECT * FROM proveedores");
			ResultSet res = consulta.executeQuery();
			while (res.next()) {
				
				ProveedoresVO Proveedor = new ProveedoresVO();
				
				Proveedor.setNitproveedor(res.getLong("nitproveedor"));
				Proveedor.setCiudad_proveedor(res.getString ("ciudad_proveedor"));
				Proveedor.setDireccion_proveedor(res.getString ("direccion_proveedor"));
				Proveedor.setNombre_proveedor(res.getString ("nombre_proveedor"));
				Proveedor.setTelefono_proveedor(res.getString ("telefono_proveedor"));
				misproveedores.add(Proveedor);
				
							
			}
			res.close();
			consulta.close();
			conexion.desconectar();
		}catch(Exception e) {
			System.out.println(e);
		}
		return misproveedores;
	}
	/***
	 * Busca un proveedor por NIT
	 * @param NIT
	 * @return ArrayList
	 */
	public ArrayList<ProveedoresVO> buscarProveedorNit(String nitproveedor){
		 
		ArrayList<ProveedoresVO> misproveedores = new ArrayList<ProveedoresVO>();
		Conexion conexion = new Conexion();
		try {
			PreparedStatement consulta = conexion.getConnection().prepareStatement("SELECT * FROM proveedores WHERE nitproveedor = ?");
			
			consulta.setLong(1, Long.parseLong(nitproveedor));
			ResultSet res = consulta.executeQuery();
			while(res.next()) {
				ProveedoresVO Proveedor = new ProveedoresVO();
				
				Proveedor.setNitproveedor(res.getLong("nitproveedor"));
				Proveedor.setCiudad_proveedor(res.getString ("ciudad_proveedor"));
				Proveedor.setDireccion_proveedor(res.getString ("direccion_proveedor"));
				Proveedor.setNombre_proveedor(res.getString ("nombre_proveedor"));
				Proveedor.setTelefono_proveedor(res.getString ("telefono_proveedor"));
				misproveedores.add(Proveedor);
								
			}
			res.close();
			consulta.close();
			conexion.desconectar();
		}catch(Exception e){
			System.out.println(e);
			System.out.println("Aquí esta el error");
		}
		return misproveedores;
	}
	/***
	 * Busca a un proveedor por NIT
	 * @param NIT
	 * @return True si existe el proveedor con este NIT asociadp o false si es lo contrario
	 */
	public boolean existeproveedor(Long nitproveedor ) {
		boolean existe = false;
		Conexion conexion = new Conexion();
		try {
			PreparedStatement consulta = conexion.getConnection().prepareStatement("SELECT * FROM proveedores WHERE nitproveedor = ?");
			
			consulta.setLong(1,nitproveedor);
			
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
	 * Crea un nuevo proveedor
	 * @param proveedor
	 * @return True si la creación cel nuevo proveedor fue existosa o false si es lo contrario
	 */
	public boolean crearProveedor(ProveedoresVO proveedores) {
		boolean creado = false;
		
		if(! this.existeproveedor(proveedores.getNitproveedor())) {
			
			Conexion conexion = new Conexion();
			try {
				
				Statement consulta = conexion.getConnection().createStatement();
				
				
				String crearSql = "INSERT INTO proveedores (nitproveedor, ciudad_proveedor, direccion_proveedor, nombre_proveedor, telefono_proveedor) "
						+ " VALUES ("+proveedores.getNitproveedor()+", '"+proveedores.getCiudad_proveedor()+"', "
								+ "'"+proveedores.getDireccion_proveedor()+"', '"+proveedores.getNombre_proveedor()+"', "
										+ "'"+proveedores.getTelefono_proveedor()+"')";
				consulta.executeUpdate(crearSql);
				consulta.close();
				conexion.desconectar();
				creado = true;
			}catch(SQLException e) {
				System.out.println(e);
			}
		}else {
			System.out.println("El proveedor ya existe");
		}
		
		return creado;
	}
	
	/***
	 * Actualizar los datos de un proveedor
	 * @param proveedor
	 * @return True si se modificó el proveedor o false de lo contrario
	 * */
	
	public boolean actualizarproveedor(ProveedoresVO proveedor) {
		boolean actualizado = false;
		
		if( this.existeproveedor(proveedor.getNitproveedor())) {
			Conexion conexion = new Conexion();
			try {
				Statement consulta = conexion.getConnection().createStatement();
				String actualizarSql = "UPDATE proveedores SET ciudad_proveedor ='"+proveedor.getCiudad_proveedor()+"', "
					+ " direccion_proveedor = '"+proveedor.getDireccion_proveedor()+"', nombre_proveedor = '"+proveedor.getNombre_proveedor()+"', "
					    + " telefono_proveedor = '"+proveedor.getTelefono_proveedor()+"' WHERE nitproveedor = "+proveedor.getNitproveedor()+"";
				consulta.executeUpdate(actualizarSql);
				
				consulta.close();
				conexion.desconectar();
				actualizado = true;
			}catch(SQLException e) {
				System.out.println(e);
			}
		}else {
			System.out.println("El proveedor no existe");
		}
		return actualizado;
	}
	
	public boolean borrarproveedor(Long nitproveedor) {
		boolean eliminado = false;
		
		if( this.existeproveedor(nitproveedor)) {
			Conexion conexion = new Conexion();
			try {
				Statement consulta = conexion.getConnection().createStatement();
				String eliminarSql = "DELETE FROM proveedores WHERE nitproveedor = "+nitproveedor+" ";
				consulta.executeUpdate(eliminarSql);
				
				consulta.close();
				conexion.desconectar();
				eliminado = true;
			}catch(SQLException e) {
				System.out.println(e);
			}
		}else {
			System.out.println("El proveedor no existe");
		}
		
		return eliminado;
	}
	 

}


