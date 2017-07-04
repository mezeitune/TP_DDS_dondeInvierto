package metodologias;

import org.uqbar.commons.utils.Observable;

import usuario.Empresa;
@Observable
public class EmpresaCumplimiento {

	private Empresa empresa;
	private int cumplio;//1 o 0
	private int pesoCondicion;
	public EmpresaCumplimiento(Empresa empresa , int cumplio , int pesoCondicion){
		this.empresa=empresa;
		this.cumplio=cumplio;
		this.pesoCondicion=pesoCondicion;
	}
	
	
	
	public Empresa getEmpresa() {
		return empresa;
	}

	
	public int getCumplio() {
		return cumplio;
	}

	
	public int getPesoCondicion() {
		return pesoCondicion;
	}

	
	
	
	
}
