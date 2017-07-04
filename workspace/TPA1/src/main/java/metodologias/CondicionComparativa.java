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
@Observable
public class CondicionComparativa implements EstadoCondicion{
	
	
	private static CondicionComparativa instance ;
	public Comparador comparador;
	public int peso;


	public CondicionComparativa(Comparador comparador,int peso){
		this.comparador = comparador;
		this.peso=peso;
	}
	
	public CondicionComparativa(){
		
	}
	
	
	public static CondicionComparativa getInstance( ) {
        if(instance == null){
            instance = new CondicionComparativa();
        }
        return instance;

	}	
	
	public void setComparador(Comparador unComparador){
		this.comparador = unComparador;
	}
	public Comparador getComparador(){
		return this.comparador;
	}
	
	public void setPeso(int peso){
		this.peso = peso;
	}
	public int getPeso(){
		return this.peso;
	}
	
	@Override
	public List<Empresa> evaluar(List<Empresa> empresas,List <String> periodos,Condicion condicion){
		int i;
		
		List<Empresa> empresasComparadas = new LinkedList<>();
		List<Empresa> empresasPerdedoras = new LinkedList<>();
		
		for(i=0;i<empresas.size();i++){

			Empresa empresaAComparar = empresas.get(i);
			empresasPerdedoras = empresas.stream().filter(empresa2->this.comparar(empresaAComparar,empresa2,periodos,condicion.getIndicador())).collect(Collectors.toList());

			empresaAComparar.actualizarPeso(empresasPerdedoras.size()*this.peso); // Cada victoria de la empresa, se le suma el peso de la condicion
			empresasComparadas.add(empresaAComparar);
		}
		return empresasComparadas;
	}
	
	boolean comparar(Empresa empresa1,Empresa empresa2,List<String> periodos,Indicador indicador){
		return periodos.stream().allMatch(periodo -> this.compararEnPeriodo(empresa1,empresa2,periodo,indicador));
	}
	
	boolean compararEnPeriodo(Empresa empresa1,Empresa empresa2,String periodo,Indicador indicador){
		ParserFormulaToIndicador.setEmpresa(empresa1);
		ParserFormulaToIndicador.setPeriodo(periodo);
		int valor1=indicador.calcular();
		
		ParserFormulaToIndicador.setEmpresa(empresa2);
		ParserFormulaToIndicador.setPeriodo(periodo);
		int valor2=indicador.calcular();
		
		return this.comparador.comparar(valor1, valor2);
	}
	
	

	@Override
	public void setComparador(Comparador comparadorTaxativo, Comparador comparadorCompetitivo) {
		// TODO Auto-generated method stub
		
	}
	
	/*
	@Override
	public int evaluar(Empresa empresa1, Empresa empresa2, Condicion condicion) {
		ParametroOperacion parametroOperacion=condicion.getParametroOperacionComparativa();
		int year = Calendar.getInstance().get(Calendar.YEAR);
		
		Indicador indicadorAEvaluar=parametroOperacion.getParametro();
		ParserFormulaToIndicador.setEmpresa(empresa2);
		ParserFormulaToIndicador.setPeriodo(String.valueOf(year));
		int aux=indicadorAEvaluar.calcular();
		int ver=1;
		ParserFormulaToIndicador.setEmpresa(empresa1);
		for(int i=0;i<parametroOperacion.getLonngevidadAEvaluar();i++){
			ParserFormulaToIndicador.setPeriodo(String.valueOf(year-i));
			ParserFormulaToIndicador.setEmpresa(empresa1);
			if(indicadorAEvaluar.calcular()>aux){	//En vez del > habria que ver la forma der poner el parametroOperacion.getOperacion()
				ParserFormulaToIndicador.setEmpresa(empresa2);
				aux=indicadorAEvaluar.calcular();
				
			}else{
				ver=0;
			}
			
		}
		
		
		
		return ver;
		
	}
	*/

}
