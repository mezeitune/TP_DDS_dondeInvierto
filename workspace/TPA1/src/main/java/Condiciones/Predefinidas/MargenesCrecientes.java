package Condiciones.Predefinidas;


import Comparadores.ComparadorMayor;
import Condiciones.Comparativa;
import Condiciones.Condicion;
import Condiciones.TipoCondicion;
import usuario.Indicador;

public class MargenesCrecientes extends Condicion {

	static int pesoMargenesCrecientes = 20;
	
	private static MargenesCrecientes instance ;
	public MargenesCrecientes(String nombre,TipoCondicion tipo, Indicador indicador, int peso) {
		super(nombre,tipo, indicador, peso);
	}
	public static MargenesCrecientes getInstance() {
		
	        if(instance == null){
	            instance = new MargenesCrecientes("Margenes Crecientes"
	            									,new Comparativa(new ComparadorMayor())
	            									,new Indicador ("Margen","Activo/Capital Total")
	            									,pesoMargenesCrecientes);
	        }
	        return instance;
	
	}

}
