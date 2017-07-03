package metodologias.Predefinidas;

import java.util.LinkedList;
import java.util.List;

import org.uqbar.arena.widgets.Link;

import Comparadores.Comparador;
import Comparadores.ComparadorMenor;
import metodologias.Condicion;
import metodologias.CondicionComparativa;
import metodologias.EstadoCondicion;
import usuario.Indicador;
import usuario.Metodologia;

public class WarrenBuffet extends Metodologia{

	this.condiciones = this.inicializarCondiciones();
	
	
	public List<Condicion> inicializarCondiciones(){
		
		List<Condicion> condicionesPredefinidas = new LinkedList<>();
		
		Indicador roe = new Indicador("ROE","1+5");//Buscar como se calcula el ROE
		EstadoCondicion comparativa = new CondicionComparativa();
		Condicion maximizarROE = new Condicion(comparativa);
		
		
		Indicador nivelDeuda = new Indicador ("Nivel de deuda","1+5");//Busar como se calcula
		Comparador menor = new ComparadorMenor();
		comparativa.setComparador(menor);
		Condicion minimizarDeuda = new Condicion(comparativa);

		return condicionesPredefinidas;
	}
	
}


