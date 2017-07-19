package metodologias.Predefinidas;

import java.util.LinkedList;
import java.util.List;

import org.uqbar.arena.widgets.Link;
import org.uqbar.commons.utils.Observable;

import Comparadores.Comparador;
import Comparadores.ComparadorMayor;
import Comparadores.ComparadorMenor;
import Condiciones.Comparativa;
import Condiciones.Condicion;
import Condiciones.TipoCondicion;
import usuario.Indicador;
import usuario.Metodologia;
import usuario.PatrimonioNeto;
@Observable
public class WarrenBuffet extends Metodologia{

	
	private static WarrenBuffet instance ;
	private static List<Condicion> condiciones;

	public static WarrenBuffet getInstance( ) {
        if(instance == null){
            instance = new WarrenBuffet();
        }
        return instance;

	}	
	
	public WarrenBuffet() {
		super();
		this.setCondiciones(inicializarCondiciones());
		this.setNombre("Buffet");
	}
	
	
	public List<Condicion> inicializarCondiciones(){
		
		List<Condicion> condicionesPredefinidas = new LinkedList<>();
		
		int pesoRoe = 20;
		Indicador roe = new Indicador("ROE","Ingreso Neto-Dividendos/Capital Total");
		TipoCondicion comparativa = new Comparativa(new ComparadorMayor());
		Condicion maximizarROE = new Condicion(comparativa,roe,pesoRoe);
		
		condicionesPredefinidas.add(maximizarROE);		
		
		int pesoNivelDeuda=10;
		Indicador nivelDeuda = new Indicador ("Nivel de deuda","Activo/Pasivo");//TODO:Busar como se calcula
		comparativa.setComparador(new ComparadorMenor());
		Condicion minimizarDeuda = new Condicion(comparativa,nivelDeuda,pesoNivelDeuda);
		
		condicionesPredefinidas.add(minimizarDeuda);

		return condicionesPredefinidas;
	}
	
}


