package usuario;

import java.util.Calendar;
import java.util.List;
import java.util.stream.Collectors;

import parserFormulaInidicador.ParserFormulaToIndicador;

public class Antiguedad extends Indicador {
	
	public Empresa empresa;
	int anioActual; /*TODO: Hardcodeado para testear*/

	public void setAnioActual(int anoActual) {
		this.anioActual = anoActual;
	}

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
		
		Calendar cal = Calendar.getInstance(); 
		int anio = cal.get(Calendar.YEAR);
		
		this.setAnioActual(anio);
		
		
		this.empresa = ParserFormulaToIndicador.getEmpresa();
		List<Integer> periodos = empresa.getCuentas().stream().map(c1 ->Integer.parseInt(c1.getPeriodo())).collect(Collectors.toList());
		
		List<Integer> periodosOrdenados =  periodos.stream().sorted( (p1,p2) -> p1 < p2 ? -1:1 ).collect(Collectors.toList());
		
		return this.anioActual - periodosOrdenados.get(0);
	}

	
}
