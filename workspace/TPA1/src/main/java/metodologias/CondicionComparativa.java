package metodologias;

import usuario.Empresa;

public class CondicionComparativa implements EstadoCondicion{

	
	private static CondicionComparativa instance ;




	public static CondicionComparativa getInstance( ) {
        if(instance == null){
            instance = new CondicionComparativa();
        }
        return instance;

	}	
	
	@Override
	public int evaluar(Empresa empresa1, Empresa empresa2, Condicion condicion) {
		ParametroOperacion parametroOperacion=condicion.getParametroOperacionTaxativa();
		
		return 0;
		
	}

}
