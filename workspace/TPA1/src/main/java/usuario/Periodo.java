package usuario;

import java.util.LinkedList;
import java.util.List;

public class Periodo {

	private int valor;
	private List<Cuenta> cuentas = new LinkedList<Cuenta>();
	
	public Periodo(int valor){
		this.setValor(valor);
	}

	public int getValor() {
		return valor;
	}

	public void setValor(int valor) {
		this.valor = valor;
	}

	public List<Cuenta> getCuentas() {
		return cuentas;
	}

	public void setCuentas(List<Cuenta> cuentas) {
		this.cuentas = cuentas;
	}
	
	
	
	
}
