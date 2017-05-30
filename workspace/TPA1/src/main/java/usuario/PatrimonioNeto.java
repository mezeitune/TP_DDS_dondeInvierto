package usuario;

import org.uqbar.commons.utils.Observable;

//indicador predefinido que extiende de indicador para que todos tengan el metodo calcular en su dominio
@Observable
public class PatrimonioNeto extends Indicador{

	
	private static PatrimonioNeto singleton = new PatrimonioNeto("PN", "1+2" );


	public static PatrimonioNeto getInstance( ) {
	    return singleton;
	}
	
	public PatrimonioNeto(String nombre,String formula) {
		super(nombre,formula);
	}




	

	
	
}
