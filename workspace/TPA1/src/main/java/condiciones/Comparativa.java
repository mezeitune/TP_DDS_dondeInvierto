package condiciones;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import javax.persistence.Entity;

import org.uqbar.commons.utils.Observable;

import comparadores.Comparador;
import usuario.Empresa;
import usuario.Indicador;
@Observable
@Entity
public class Comparativa extends TipoCondicion{
	
	
	private static Comparativa instance ;



	public Comparativa(Comparador comparador){
		this.comparador = comparador;
	}
	
	public Comparativa(){
		this.nombre = "Comparativa";
	}
	
	
	public static Comparativa getInstance( ) {
        if(instance == null){
            instance = new Comparativa();
        }
        return instance;

	}	
	@Override
	public void setComparador(Comparador unComparador){
		this.comparador = unComparador;
	}
	public Comparador getComparador(){
		return this.comparador;
	}
	
	public String getNombre(){
		return this.nombre;
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
		
		//System.out.println(empresa1.getNombre());
		indicador.construirOperadorRaiz(empresa1, periodo);
		int valor1 = indicador.calcular();
		//System.out.println(valor1);
		
		indicador.construirOperadorRaiz(empresa2, periodo);
		int valor2 = indicador.calcular();
		
		//System.out.println(empresa2.getNombre());
		//System.out.println(valor2);
		
		return this.comparador.comparar(valor1, valor2);
	}
	
	

}
