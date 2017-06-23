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
	
	public void evaluar(Empresa empresa){
		
	}

}
