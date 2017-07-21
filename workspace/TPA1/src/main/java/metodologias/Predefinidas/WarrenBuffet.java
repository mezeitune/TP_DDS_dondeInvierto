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
import Condiciones.Mixta;
import Condiciones.Taxativa;
import Condiciones.TipoCondicion;
import usuario.Antiguedad;
import usuario.Empresa;
import usuario.Indicador;
import usuario.Metodologia;
import usuario.PatrimonioNeto;
@Observable
public class WarrenBuffet extends Metodologia{

	
	private static WarrenBuffet instance ;

	public static WarrenBuffet getInstance( ) {
        if(instance == null){
            instance = new WarrenBuffet();
        }
        return instance;

	}	
	
	public WarrenBuffet() {
		super();
		this.setCondiciones(this.inicializarCondiciones());
		this.setNombre("Buffet");
	}
	
	
	public List<Condicion> inicializarCondiciones(){
		List<Condicion> condicionesPredefinidas = new LinkedList<>();
		
		int pesoRoe = 20;
		Indicador roe = new Indicador("ROE","Ingreso Neto-Dividendos/Capital Total");
		Condicion maximizarROE = new Condicion("maximizarRoe",new Comparativa(new ComparadorMayor()),roe,pesoRoe);
		
		condicionesPredefinidas.add(maximizarROE);		
		
		int pesoNivelDeuda=10;
		Indicador nivelDeuda = new Indicador ("Nivel de deuda","Activo/Pasivo");
		Condicion minimizarDeuda = new Condicion("minimizarDeuda",new Comparativa(new ComparadorMenor()),nivelDeuda,pesoNivelDeuda);
		
		condicionesPredefinidas.add(minimizarDeuda);
		
		int pesoMargenesCrecientes=10;
		Indicador margen = new Indicador ("Margen","Activo/Capital Total");
		Condicion margenesCrecientes = new Condicion("margenesCrecientes",new Comparativa(new ComparadorMenor()),margen,pesoMargenesCrecientes);

		condicionesPredefinidas.add(margenesCrecientes);
		
		int anosRequeridos = 3;
		TipoCondicion comparativa = new Comparativa(new ComparadorMayor());
		TipoCondicion taxativa = new Taxativa(new ComparadorMenor(),anosRequeridos);
		List<TipoCondicion> tiposCondiciones = new LinkedList<TipoCondicion>();
		tiposCondiciones.add(comparativa);
		tiposCondiciones.add(taxativa);
		TipoCondicion mixta = new Mixta(tiposCondiciones);
		
		Condicion longevidad = new Condicion("longevidad",mixta,new Antiguedad(),0);
		
		condicionesPredefinidas.add(longevidad);
		
		return condicionesPredefinidas;
	}
	
}


