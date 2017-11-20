package excepciones;

import model.Empresa;

public class AccountNotFoundException extends RuntimeException {
	private Empresa empresa;
	public Empresa getEmpresa() {
		return empresa;
	}

	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}

	public String getPeriodo() {
		return periodo;
	}

	public void setPeriodo(String periodo) {
		this.periodo = periodo;
	}

	public String getNombreCuenta() {
		return nombreCuenta;
	}

	public void setNombreCuenta(String nombreCuenta) {
		this.nombreCuenta = nombreCuenta;
	}

	private String periodo;
	private String nombreCuenta;
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public AccountNotFoundException(Empresa empresa,String periodo,String nombreCuenta) {
		this.empresa=empresa;
		this.periodo=periodo;
		this.nombreCuenta=nombreCuenta;
	}

	public AccountNotFoundException() {
		// TODO Auto-generated constructor stub
	}
	
}
