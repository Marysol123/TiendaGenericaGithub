
package co.edu.ciclo3backend;
//notaaaaaa

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import org.apache.tomcat.jni.File;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import co.edu.DAO.ciclo3backend.ProductosDAO;
import co.edu.DTO.ciclo3backend.ProductosVO;

@RestController
public class ProductosController {
	
	@RequestMapping("/listaProductos")
	@CrossOrigin(origins="*")
	public ArrayList<ProductosVO> listaDeProductos(){
		ProductosDAO dao = new ProductosDAO();
	    
	    return dao.listaProductos();
	}
	
	@RequestMapping("/traerProducto")
	@CrossOrigin(origins="*")
	public ArrayList<ProductosVO> buscarProductoCod(String codigo_producto){
		ProductosDAO dao = new ProductosDAO();
	    
	    return dao.buscarProductoCod(codigo_producto);
	}
	

	@RequestMapping("/crearProducto")
	@CrossOrigin(origins="*")
	public boolean crearProducto(String codigo_producto, String nombre_producto, long nitproveedor, double precio_compra, double ivacompra, double precio_venta) {
		
		ProductosVO Producto = new ProductosVO();
		

		Producto.setCodigo_producto(Long.parseLong(codigo_producto));
		Producto.setNombre_producto(nombre_producto);
		Producto.setNitproveedor(nitproveedor);
		Producto.setPrecio_compra(precio_compra);
		Producto.setIvacompra(ivacompra);
		Producto.setPrecio_venta(precio_venta);
		
		
		ProductosDAO dao = new ProductosDAO();
		
		return dao.crearProducto(Producto);
	}

	@RequestMapping("/actualizarProducto")
	public boolean actualizarProducto(long codigo_producto, String nombre_producto, long nitproveedor, double precio_compra, double ivacompra, double precio_venta) {
		
		
		ProductosVO Producto = new ProductosVO();
		
		
		Producto.setCodigo_producto(codigo_producto);
		Producto.setNombre_producto(nombre_producto);
		Producto.setNitproveedor(nitproveedor);
		Producto.setPrecio_compra(precio_compra);
		Producto.setIvacompra(ivacompra);
		Producto.setPrecio_venta(precio_venta);
		
		ProductosDAO dao = new ProductosDAO();
		
		return dao.actualizarproducto(Producto);	
	}
	
	@RequestMapping("/borrarProducto")
	public boolean borrarProducto(String codigo_producto) {
		ProductosDAO dao = new ProductosDAO();
		
		return dao.borrarproducto(Long.parseLong(codigo_producto));
	}
	
	@RequestMapping("/cargarArchivo")
	public String cargarArchivo(MultipartFile archivoCSV) {
		File archivoNew;
		String salida="";
		FileReader fuente = null;
		String linea="";
		ArrayList<ProductosVO> listado= new ArrayList<ProductosVO>();
		try {
			
			archivoNew = deMultiPartAFile(archivoCSV);
			fuente = new FileReader(((MultipartFile) archivoNew).getName());
			BufferedReader archivo = new  BufferedReader(fuente);
			do {
				linea = archivo.readLine();
				if (linea!=null) {
					String tmpLinea = linea.replace("\"","'");
					ArrayList<String> miLista = new ArrayList<String>(Arrays.asList(tmpLinea.split(",")));
					
					ProductosVO producto = new ProductosVO();
					
					producto.setCodigo_producto(Long.parseLong(miLista.get(0)));
					producto.setNombre_producto(miLista.get(1).replace("'",""));
					producto.setNitproveedor(Long.parseLong(miLista.get(2).replace("'","")));
					producto.setPrecio_compra(Long.parseLong(miLista.get(3).replace("'","")));
					producto.setIvacompra(Long.parseLong(miLista.get(4).replace("'","")));
					producto.setPrecio_venta(Long.parseLong(miLista.get(3).replace("'","")));
					listado.add(producto);
				}
			}while (linea!=null);
			archivo.close();
			fuente.close();
			
			boolean secreo= false;
			
			for(ProductosVO registro:listado) {
				ProductosDAO dao = new ProductosDAO();
				secreo = dao.crearProducto(registro);				
				
				salida = salida + "**"+secreo+"**" + registro.getCodigo_producto() + "---" +
								 registro.getNombre_producto() + " ---"+
								 registro.getNitproveedor() + "---" +
								 registro.getPrecio_compra() + "---"+
								 registro.getIvacompra() + "---"+
								 registro.getPrecio_venta() + "<br>";
				
			}
			salida = salida + " RTA: " + (secreo?"Ok":"No se pudo insertar el listadio");
		}catch(FileNotFoundException e) {
			System.out.println(e.getMessage());
		}catch(IOException e2) {
			System.out.println(e2.getMessage());
		}
		
		return salida;
	}
	
	private File deMultiPartAFile(MultipartFile archivo) {
		File convFile = new File(archivo.getOriginalFilename());
		FileOutputStream salida;
		try {
			salida = new FileOutputStream(convFile);
			salida.write(archivo.getBytes());
			salida.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return convFile;
	}
	
}



