package metodologias.Predefinidas;

import java.util.LinkedList;
import java.util.List;

import org.uqbar.arena.widgets.Link;
import org.uqbar.commons.utils.Observable;

import Comparadores.Comparador;
import Comparadores.ComparadorMayor;
import Comparadores.ComparadorMenor;
import metodologias.Condicion;
import metodologias.CondicionComparativa;
import metodologias.EstadoCondicion;
import usuario.Indicador;
import usuario.Metodologia;
@Observable
public class WarrenBuffet extends Metodologia{

	List<Condicion> condiciones = this.inicializarCondiciones();
	
	
	public List<Condicion> inicializarCondiciones(){
		
		List<Condicion> condicionesPredefinidas = new LinkedList<>();
		
		int pesoRoe = 20;
		Indicador roe = new Indicador("ROE","Ingreso Neto-Dividendos/Capital Total");
		EstadoCondicion comparativa = new CondicionComparativa(new ComparadorMayor(),pesoRoe);
		Condicion maximizarROE = new Condicion(comparativa,roe);
		
		condicionesPredefinidas.add(maximizarROE);		
		
		int pesoNivelDeuda=10;
		Indicador nivelDeuda = new Indicador ("Nivel de deuda","Activo/Pasivo");//TODO:Busar como se calcula
		comparativa.setComparador(new ComparadorMenor());
		comparativa.setPeso(pesoNivelDeuda);
		Condicion minimizarDeuda = new Condicion(comparativa,nivelDeuda);
		
		condicionesPredefinidas.add(minimizarDeuda);

		return condicionesPredefinidas;
	}
	
}


