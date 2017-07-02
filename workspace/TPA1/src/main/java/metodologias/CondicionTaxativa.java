package metodologias;

import java.util.Calendar;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

import Comparadores.Comparador;
import parserFormulaInidicador.ParserFormulaToIndicador;
import usuario.Empresa;
import usuario.Indicador;
import usuario.PatrimonioNeto;

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
	
	@Override
		public List<Empresa> evaluar(List<Empresa> empresas,String periodo,Condicion condicion){
			ParserFormulaToIndicador.setPeriodo(periodo);
			Indicador indicador = condicion.getIndicador();
			return empresas.stream().filter(empresa1 -> this.verificarCriterio(empresa1,indicador)).collect(Collectors.toList());
		}
				
		boolean verificarCriterio(Empresa empresa,Indicador indicador){
			ParserFormulaToIndicador.setEmpresa(empresa);
			return comparador.comparar(valorRequerido, indicador.calcular());
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
