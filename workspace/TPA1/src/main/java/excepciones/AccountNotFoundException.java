package excepciones;

import model.Empresa;

public class AccountNotFoundException extends RuntimeException {
	private Empresa empresa;
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
	
}
