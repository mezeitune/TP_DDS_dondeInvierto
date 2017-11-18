package indicadoresPredefinidos;
import model.Indicador;


public class PatrimonioNeto extends Indicador{
	
	private static PatrimonioNeto instance ;

	public static PatrimonioNeto getInstance( ){
        if(instance == null){
            instance = new PatrimonioNeto("Patrimonio Neto", "1+2" );
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
