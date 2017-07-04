package metodologias;

import org.uqbar.commons.utils.Observable;

import usuario.Empresa;
@Observable
public class EmpresaRank {

	private Empresa empresa;
	private int rank;
	
	public EmpresaRank(Empresa empresa , int rank){
		this.empresa=empresa;
		this.rank=rank;
	}

	public Empresa getEmpresa() {
		return empresa;
	}

	

	public int getRank() {
		return rank;
	}

	
	
	
}
