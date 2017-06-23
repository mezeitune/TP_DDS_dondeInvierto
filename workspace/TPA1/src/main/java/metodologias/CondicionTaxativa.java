package metodologias;

import usuario.Empresa;
import usuario.PatrimonioNeto;

public class CondicionTaxativa  {
	
	
	private static CondicionTaxativa instance ;




	public static CondicionTaxativa getInstance( ) {
        if(instance == null){
            instance = new CondicionTaxativa();
        }
        return instance;

	}	
	
	public int evaluar(Empresa empresa, ParametroOperacion parametroOperacion){
		
		
		return 0;
		
	}

}
