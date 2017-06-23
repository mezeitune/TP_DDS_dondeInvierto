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
	public void evaluar(Empresa empresa1, Empresa empresa2) {
		// TODO Auto-generated method stub
		
	}

}
