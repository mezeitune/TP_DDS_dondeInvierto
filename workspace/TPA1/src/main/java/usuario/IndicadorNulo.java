package usuario;

import org.uqbar.commons.utils.Observable;

//indicador predefinido que extiende de indicador para que todos tengan el metodo calcular en su dominio
@Observable

public class IndicadorNulo extends Indicador{

	
	private static IndicadorNulo instance ;




	public static IndicadorNulo getInstance( ) {
        if(instance == null){
            instance = new IndicadorNulo("indicadorNulo", "0" );
        }
        return instance;

	}	
	
	public IndicadorNulo(String nombre,String formula) {
		super(nombre,formula);
	}
	@Override
	public int calcular(){
		return 0;
	}



	

	
	
}
