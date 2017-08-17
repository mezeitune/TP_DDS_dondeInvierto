package Condiciones;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import org.uqbar.commons.utils.Observable;

import Comparadores.Comparador;
import parserIndicadores.ParserFormulaToIndicador;
import usuario.Empresa;
import usuario.Indicador;
@Observable
public class Comparativa extends TipoCondicion{
	
	
	private static Comparativa instance ;
	public Comparador comparador;


	public Comparativa(Comparador comparador){
		this.comparador = comparador;
	}
	
	public Comparativa(){
		
	}
	
	
	public static Comparativa getInstance( ) {
        if(instance == null){
            instance = new Comparativa();
        }
        return instance;

	}	
	
	public void setComparador(Comparador unComparador){
		this.comparador = unComparador;
	}
	public Comparador getComparador(){
		return this.comparador;
	}
	
	
	@Override
	public List<Empresa> evaluar(List<Empresa> empresas,List <String> periodos,Condicion condicion){
		List<Empresa> empresasComparadas = new LinkedList<Empresa> (empresas);
		Collections.sort(empresasComparadas,(empresa1,empresa2)->this.comparar(empresa1, empresa2, periodos,condicion.getIndicador())? -1 : 1);
		return empresasComparadas;
	}
	
	boolean comparar(Empresa empresa1,Empresa empresa2,List<String> periodos,Indicador indicador){
		return periodos.stream().allMatch(periodo -> this.compararEnPeriodo(empresa1,empresa2,periodo,indicador));
	}
	
	boolean compararEnPeriodo(Empresa empresa1,Empresa empresa2,String periodo,Indicador indicador){
		//System.out.println(periodo);
		ParserFormulaToIndicador.setEmpresa(empresa1);
		ParserFormulaToIndicador.setPeriodo(periodo);
		
		int valor1 = indicador.calcular();
		
		//System.out.println(empresa1.getNombre());
		//System.out.println(valor1);
		
		ParserFormulaToIndicador.setEmpresa(empresa2);
		ParserFormulaToIndicador.setPeriodo(periodo);
		int valor2 = indicador.calcular();
		
		//System.out.println(empresa2.getNombre());
		//System.out.println(valor2);
		
		return this.comparador.comparar(valor1, valor2);
	}
	
	

}
