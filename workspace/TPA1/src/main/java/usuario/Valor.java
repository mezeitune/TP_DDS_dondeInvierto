package usuario;

import org.uqbar.commons.utils.Observable;
@Observable
public class Valor {
	String periodo;
	String valorEnDolares;
	
	public String getPeriodo() {
		return periodo;
	}
	public String getValorEnDolares() {
		return valorEnDolares;
	}
	
	public void setPeriodo(String periodo) {
		this.periodo= periodo;
	}
	public void setValorEnDolares(String valorEnDolares) {
		this.valorEnDolares = valorEnDolares;
	}
	
	
}
