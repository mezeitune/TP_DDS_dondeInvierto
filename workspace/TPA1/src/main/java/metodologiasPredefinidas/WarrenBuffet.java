package metodologiasPredefinidas;

import java.util.LinkedList;
import java.util.List;

import javax.persistence.Entity;

import org.uqbar.commons.utils.Observable;

import comparadores.ComparadorMayor;
import comparadores.ComparadorMenor;
import condiciones.Comparativa;
import condiciones.Condicion;
import condiciones.Mixta;
import condiciones.Taxativa;
import condiciones.TipoCondicion;
import indicadoresPredefinidos.Antiguedad;
import model.Indicador;
import model.Metodologia;

@Observable
@Entity
public class WarrenBuffet extends Metodologia{

	
	private static WarrenBuffet instance ;

	public static WarrenBuffet getInstance( ){
        if(instance == null){
            instance = new WarrenBuffet();
        }
        return instance;

	}	
	
	public WarrenBuffet() {
		super();
		this.setCondiciones(this.inicializarCondiciones());
		this.setNombre("Warren Buffet");
	}
	
	
	public List<Condicion> inicializarCondiciones(){
		List<Condicion> condicionesPredefinidas = new LinkedList<Condicion>();
		
		int pesoRoe = 10;
		Indicador roe = new Indicador("ROE","Ingreso Neto-Dividendos/Capital Total");
		Condicion maximizarROE = new Condicion("maximizarRoe",new Comparativa(new ComparadorMayor()),roe,pesoRoe);
		condicionesPredefinidas.add(maximizarROE);		
		
		int pesoNivelDeuda=5;
		Indicador nivelDeuda = new Indicador ("Nivel de deuda","Activo/Pasivo");
		Condicion minimizarDeuda = new Condicion("minimizarDeuda",new Comparativa(new ComparadorMenor()),nivelDeuda,pesoNivelDeuda);
		condicionesPredefinidas.add(minimizarDeuda);
		
		int pesoMargenesCrecientes=20;
		Indicador margen = new Indicador ("Margen","Activo/Capital Total");
		Condicion margenesCrecientes = new Condicion("margenesCrecientes",new Comparativa(new ComparadorMayor()),margen,pesoMargenesCrecientes);
		condicionesPredefinidas.add(margenesCrecientes);
		
		int pesoLongevidad=10;
		int anosRequeridos = 3;
		List<TipoCondicion> tiposCondiciones = new LinkedList<TipoCondicion>();
		tiposCondiciones.add(new Comparativa(new ComparadorMayor()));
		tiposCondiciones.add(new Taxativa(new ComparadorMenor(),anosRequeridos));
		Condicion longevidad = new Condicion("longevidad",new Mixta(tiposCondiciones),new Antiguedad(),pesoLongevidad);
		condicionesPredefinidas.add(longevidad);
		
		return condicionesPredefinidas;
	}
	
}


