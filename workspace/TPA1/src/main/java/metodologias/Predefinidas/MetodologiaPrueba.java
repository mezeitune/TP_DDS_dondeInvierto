package metodologias.Predefinidas;

import java.util.List;

import Comparadores.ComparadorMayor;
import Comparadores.ComparadorMayorIgual;
import Comparadores.ComparadorMenorIgual;
import Condiciones.Comparativa;
import Condiciones.Condicion;
import Condiciones.Taxativa;
import Condiciones.TipoCondicion;
import usuario.Antiguedad;
import usuario.Indicador;
import usuario.Metodologia;

public class MetodologiaPrueba extends Metodologia {
	
	
	private static MetodologiaPrueba instance ;
	private static List<Condicion> condiciones;/* = this.inicializarCondiciones();*/



	public static MetodologiaPrueba getInstance( ) {
        if(instance == null){
            instance = new MetodologiaPrueba();
        }
        return instance;

	}	
	
	public MetodologiaPrueba() {
		super();
		//this.inicializarCondiciones();
	}
	

	@Override
	public String getNombre(){
		return "Prueba";
	}
	
	
/*public void inicializarCondiciones(){
		
		int peso = 5;
		
		Indicador indicador1 = new Indicador("Indicadoraaaa", "12+5");
		TipoCondicion comparativa = new Comparativa(new ComparadorMayorIgual());
		Condicion mayorFreeCashFlow = new Condicion(comparativa, indicador1,peso);
		
		
		Indicador indicador2 = new Antiguedad("Edad", "10");
		TipoCondicion taxativa = new Taxativa(new ComparadorMenorIgual(), 10);
		Condicion masViejoQue2018 = new Condicion(taxativa,indicador2,peso);
		
		
		condiciones.add(masViejoQue2018);
		condiciones.add(mayorFreeCashFlow);
		
		
	}*/
	
	
	
	
	
	
	
	
	
	
	

}
