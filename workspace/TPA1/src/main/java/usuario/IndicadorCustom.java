package usuario;

import org.omg.CORBA.UserException;
import org.uqbar.commons.utils.Observable;
@Observable
public class IndicadorCustom extends Indicador  {

	
	
	public IndicadorCustom(String nombre,String formula) {
		super(nombre,formula);
	}
	
}
