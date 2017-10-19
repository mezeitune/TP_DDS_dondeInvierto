package condiciones;

import org.uqbar.commons.utils.Observable;

import model.Indicador;
@Observable
public class ParametroOperacion {

	private Indicador parametro;
	private String operacion;
	private int longevidadAEvaluar;
	
	
	public ParametroOperacion(Indicador parametro,String operacion, int longevidadAEvaluar){
		this.parametro=parametro;
		this.operacion=operacion;
		this.longevidadAEvaluar=longevidadAEvaluar;
	}
	
	public Indicador getParametro() {
		return parametro;
	}

	
	public String getOperacion() {
		return operacion;
	}
	
	public int getLonngevidadAEvaluar() {
		return longevidadAEvaluar;
	}

	
	
	
	
}
