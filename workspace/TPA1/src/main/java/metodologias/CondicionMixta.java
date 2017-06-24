package metodologias;

import java.util.Calendar;

import usuario.Empresa;

public class CondicionMixta implements EstadoCondicion{

	
	private static CondicionMixta instance ;




	public static CondicionMixta getInstance( ) {
        if(instance == null){
            instance = new CondicionMixta();
        }
        return instance;

	}	
	
	@Override
	public int evaluar(Empresa empresa1, Empresa empresa2, Condicion condicion) {
		ParametroOperacion parametroOperacionTaxativas=condicion.getParametroOperacionTaxativa();
		ParametroOperacion parametroOperacionComparativas=condicion.getParametroOperacionComparativa();
		int year = Calendar.getInstance().get(Calendar.YEAR);
		
		
		return 0;
		
	}
}
