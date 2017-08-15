package usuario;

import org.uqbar.commons.utils.Observable;

import excepciones.AccountNotFoundException;
@Observable
public class IndicadorCustom extends Indicador  {

	public IndicadorCustom(String nombre,String formula)  {
		super(nombre,formula);
	}
	
}
