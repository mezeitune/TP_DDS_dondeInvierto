package metodologias;

import java.util.Calendar;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

import org.uqbar.commons.utils.Observable;

import Comparadores.Comparador;
import parserFormulaInidicador.ParserFormulaToIndicador;
import usuario.Empresa;
import usuario.Indicador;
import usuario.PatrimonioNeto;
@Observable
public class CondicionTaxativa  implements EstadoCondicion {
	
	
	private static CondicionTaxativa instance ;
	public Comparador comparador;
	public int valorRequerido;
	
	public CondicionTaxativa(Comparador comparador,int valorRequerido){
		this.comparador=comparador;
		this.valorRequerido = valorRequerido;
	}

	public CondicionTaxativa(){
		
	}

	public static CondicionTaxativa getInstance( ) {
        if(instance == null){
            instance = new CondicionTaxativa();
        }
        return instance;
	}	
	
	
	public void setComparador(Comparador comparador){
		this.comparador = comparador;
	}
	
	@Override
		public List<Empresa> evaluar(List<Empresa> empresas,List<String> periodos,Condicion condicion){
			Indicador indicador = condicion.getIndicador();
			return empresas.stream().filter(empresa1 -> this.verificarCriterio(empresa1,periodos,indicador)).collect(Collectors.toList());
		}
				
		boolean verificarCriterio(Empresa empresa,List<String> periodos,Indicador indicador){
			
			return periodos.stream().allMatch(periodo -> this.verificarCriterioEnPeriodo(empresa,periodo,indicador));
			
		}
		
	
		boolean verificarCriterioEnPeriodo(Empresa empresa,String periodo,Indicador indicador){ //Muy parecido a compararEnPeriodo
			
			ParserFormulaToIndicador.setEmpresa(empresa);
			ParserFormulaToIndicador.setPeriodo(periodo);
			return comparador.comparar(valorRequerido, indicador.calcular());
		}

		@Override
		public void setComparador(Comparador comparadorTaxativo, Comparador comparadorCompetitivo) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void setPeso(int pesoRoe) {
			// TODO Auto-generated method stub
			
		}
	
	/*
	public int evaluar(Empresa empresa, Condicion condicion){
		ParametroOperacion parametroOperacion=condicion.getParametroOperacionTaxativa();
		int year = Calendar.getInstance().get(Calendar.YEAR);
		
		ParserFormulaToIndicador.setEmpresa(empresa);
		Indicador indicadorAEvaluar=parametroOperacion.getParametro();
		
		if(indicadorAEvaluar.getNombre().equals("indicadorNulo")){//Si instancio el indicador Nulo significa que no quiere evaluar un indicador  , sino una cierta longevidad
			
			
			//comprobar si tiene cierta cantidad de cuentas en la longevidad que le pase a parametroOperacion
			
			return 0;
			
		}else{
			ParserFormulaToIndicador.setPeriodo(String.valueOf(year));
			int aux=indicadorAEvaluar.calcular();
			int ver=1;
			for(int i=0;i<parametroOperacion.getLonngevidadAEvaluar();i++){
				ParserFormulaToIndicador.setPeriodo(String.valueOf(year-i));
				if(indicadorAEvaluar.calcular()==aux){//En vez del == habria que ver la forma der poner el parametroOperacion.getOperacion()
					
				}else{
					ver=0;
				}
				
			}
			
			//falta hacer una comprobacion para que si no tiene cierta cantidad de cuentas corte el flujo con una excepcion
			
			return ver;//Devuelve si cumplio o no con la condicion
		}


		
		
		
	}
*/
	

}
