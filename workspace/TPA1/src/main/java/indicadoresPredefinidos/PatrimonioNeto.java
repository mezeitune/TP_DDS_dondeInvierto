package indicadoresPredefinidos;

import org.uqbar.commons.utils.Observable;

import usuario.Indicador;

//indicador predefinido que extiende de indicador para que todos tengan el metodo calcular en su dominio
@Observable

public class PatrimonioNeto extends Indicador{
	
	private static PatrimonioNeto instance ;

	public static PatrimonioNeto getInstance( ){
        if(instance == null){
            instance = new PatrimonioNeto("PN", "1+2" );
        }
        return instance;

	}	
	
	public PatrimonioNeto(String nombre,String formula){
		super(nombre,formula);
	}
	@Override
	public int calcular(){
		return 3;
	}



	

	
	
}
