package co.edu.DTO.ciclo3backend;

public class VentasVO {
	private long cedula_cliente;
	private String nombre_cliente;
	private long ValorTotalVentas;
	
	
	public long getValorTotalVentas() {
		return ValorTotalVentas;
	}
	public void setValorTotalVentas(long valorTotalVentas) {
		ValorTotalVentas = valorTotalVentas;
	}
	public long getCedula_cliente() {
		return cedula_cliente;
	}
	public void setCedula_cliente(long cedula_cliente) {
		this.cedula_cliente = cedula_cliente;
	}
	public String getNombre_cliente() {
		return nombre_cliente;
	}
	public void setNombre_cliente(String nombre_cliente) {
		this.nombre_cliente = nombre_cliente;
	}
	
	
	
	


}
