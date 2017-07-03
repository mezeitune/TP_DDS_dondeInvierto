package usuario;

import java.util.List;
import java.util.stream.Collectors;

import parserFormulaInidicador.ParserFormulaToIndicador;

public class Antiguedad extends Indicador {
	
	public Empresa empresa;
	int anoActual = 2017; /*TODO: Hardcodeado para testear*/

	public Antiguedad(String nombre, String formula) {
		super(nombre, formula);
		// TODO Auto-generated constructor stub
	}
	
	public Antiguedad(){
		
	}
	
	public void setEmpresa(Empresa empresa){
		this.empresa = empresa;
	}
	
	@Override
	public int calcular(){ //Devuelve el ano mas viejos
		this.empresa = ParserFormulaToIndicador.getEmpresa();
		List<Integer> periodos = empresa.getCuentas().stream().map(c1 ->Integer.parseInt(c1.getPeriodo())).collect(Collectors.toList());
		System.out.println("Periodos de la empreosa");
		System.out.println(this.empresa.getNombre());
		
		List<Integer> periodosOrdenados =  periodos.stream().sorted( (p1,p2) -> p1 < p2 ? -1:1 ).collect(Collectors.toList());
		periodosOrdenados.stream().forEach(p1 -> System.out.println(p1));
		return this.anoActual - periodosOrdenados.get(0);
	}

	
}
