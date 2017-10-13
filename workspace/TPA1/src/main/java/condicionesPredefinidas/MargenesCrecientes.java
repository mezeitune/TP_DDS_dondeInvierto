package condicionesPredefinidas;


import comparadores.ComparadorMayor;
import condiciones.Comparativa;
import condiciones.Condicion;
import condiciones.TipoCondicion;
import model.Indicador;

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
