package co.edu.DTO.ciclo3backend;


public class ProductosVO {
	
	private long codigo_producto;
	private String nombre_producto;
	private long nitproveedor;
	private double precio_compra;
	private double ivacompra;
	private double precio_venta;
	
	
	public long getCodigo_producto() {
		return codigo_producto;
	}
	public void setCodigo_producto(long codigo_producto) {
		this.codigo_producto = codigo_producto;
	}
	public String getNombre_producto() {
		return nombre_producto;
	}
	public void setNombre_producto(String nombre_producto) {
		this.nombre_producto = nombre_producto;
	}
	public long getNitproveedor() {
		return nitproveedor;
	}
	public void setNitproveedor(long nitproveedor) {
		this.nitproveedor = nitproveedor;
	}
	public double getPrecio_compra() {
		return precio_compra;
	}
	public void setPrecio_compra(double precio_compra) {
		this.precio_compra = precio_compra;
	}
	public double getIvacompra() {
		return ivacompra;
	}
	public void setIvacompra(double ivacompra) {
		this.ivacompra = ivacompra;
	}
	public double getPrecio_venta() {
		return precio_venta;
	}
	public void setPrecio_venta(double precio_venta) {
		this.precio_venta = precio_venta;
	}
	
}
	
	
	
	