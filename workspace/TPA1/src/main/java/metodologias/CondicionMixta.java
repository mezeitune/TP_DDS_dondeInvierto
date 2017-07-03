package metodologias;

import java.util.Calendar;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

import Comparadores.Comparador;
import parserFormulaInidicador.ParserFormulaToIndicador;
import usuario.Empresa;
import usuario.Indicador;

public class CondicionMixta implements EstadoCondicion{

	
	private static CondicionMixta instance ;
	public Comparador comparadorTaxativo;
	public Comparador comparadorCompetitivo;
	public int valorRequerido;
	
	public CondicionComparativa comparativa;
	public CondicionTaxativa taxativa;


	public CondicionMixta(Comparador comparadorTaxativa,Comparador comparadorCompetitivo,int valorRequerido,int pesoComparativa){
		comparativa = new CondicionComparativa(comparadorCompetitivo,pesoComparativa);
		taxativa = new CondicionTaxativa(comparadorTaxativa,valorRequerido);
	}
	
	public CondicionMixta(){
		
	}
	
	
	public static CondicionMixta getInstance( ) {
        if(instance == null){
            instance = new CondicionMixta();
        }
        return instance;

	}	
	
	
	@Override
	public List<Empresa> evaluar(List<Empresa> empresas,String periodo,Condicion condicion){
		List<Empresa> empresasComparables = new LinkedList<>();
		empresasComparables = taxativa.evaluar(empresas, periodo, condicion);
		return comparativa.evaluar(empresasComparables, periodo, condicion);
	}
			
	
		

	
	/*
	@Override
	public int evaluar(Empresa empresa1, Empresa empresa2, Condicion condicion) {
		ParametroOperacion parametroOperacionTaxativas=condicion.getParametroOperacionTaxativa();
		ParametroOperacion parametroOperacionComparativas=condicion.getParametroOperacionComparativa();
		int year = Calendar.getInstance().get(Calendar.YEAR);
		
		int evaluacionTaxativa=CondicionTaxativa.getInstance().evaluar(empresa1, condicion);
		int evaluacionComparativa=CondicionComparativa.getInstance().evaluar(empresa1, empresa2, condicion);
		
		if(evaluacionComparativa+evaluacionTaxativa==2)//se cumplieron las dos condiciones
		{
			return 1;
		}else{
			return 0;
		}
		
		
		
	}
	*/
}

