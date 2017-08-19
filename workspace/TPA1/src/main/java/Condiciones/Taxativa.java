package Condiciones;

import java.util.List;
import java.util.stream.Collectors;

import org.uqbar.commons.utils.Observable;

import Comparadores.Comparador;
import parserIndicadores.ParserFormulaToIndicador;
import usuario.Empresa;
import usuario.Indicador;

@Observable
public class Taxativa extends TipoCondicion {
	
	private static Taxativa instance ;
	public Comparador comparador;
	public int valorRequerido;
	
	public Taxativa(Comparador comparador,int valorRequerido){
		this.comparador=comparador;
		this.valorRequerido = valorRequerido;
	}

	public Taxativa(){
		this.nombre = "Taxativa";
	}

	public static Taxativa getInstance( ) {
        if(instance == null){
            instance = new Taxativa();
        }
        return instance;
	}	
	
	@Override
	public void setComparador(Comparador comparador){
		this.comparador = comparador;
	}
	
	@Override
		public List<Empresa> evaluar(List<Empresa> empresas,List<String> periodos,Condicion condicion){
			return empresas.stream().filter(empresa -> this.verificarCriterio(empresa,periodos,condicion.getIndicador())).collect(Collectors.toList());
		}
				
		boolean verificarCriterio(Empresa empresa,List<String> periodos,Indicador indicador){
			return periodos.stream().allMatch(periodo -> this.verificarCriterioEnPeriodo(empresa,periodo,indicador));
		}
	
		boolean verificarCriterioEnPeriodo(Empresa empresa,String periodo,Indicador indicador){ //Muy parecido a compararEnPeriodo
			ParserFormulaToIndicador.setEmpresa(empresa);
			ParserFormulaToIndicador.setPeriodo(periodo);
			return comparador.comparar(valorRequerido, indicador.calcular());
		}


	

}
