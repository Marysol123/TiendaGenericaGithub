package co.edu.DTO.ciclo3backend;

public class UsuarioVO {
	private long cedula_usuario;
	private String email_usuario;
	private String nombre_usuario;
	private String password_usuario;
	private String usuario;

	public long getCedula_usuario() {
		return cedula_usuario;
	}
	public void setCedula_usuario(long cedula_usuario) {
		this.cedula_usuario = cedula_usuario;
	}

	public String getEmail_usuario() {
		return email_usuario;
	}
	public void setEmail_usuario(String email_usuario) {
		this.email_usuario = email_usuario;
	}
	public String getNombre_usuario() {
		return nombre_usuario;
	}
	public void setNombre_usuario(String nombre_usuario) {
		this.nombre_usuario = nombre_usuario;
	}
	public String getpassword() {
		return password_usuario;
	}
	public void setpassword(String password_usuario) {
		this.password_usuario = password_usuario;
	}
	public String getusuario() {
		return usuario;
	}
	public void setusuario(String usuario) {
		this.usuario = usuario;
	}
	
}
