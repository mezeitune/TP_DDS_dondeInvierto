package indicadoresPredefinidos;

import java.util.Calendar;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.Entity;
import javax.persistence.Transient;

import model.Empresa;
import model.Indicador;
import parserIndicadores.Constante;
import repositorios.RepositorioUsuarios;
@Entity
public class Antiguedad extends Indicador {
	
	
	private static Antiguedad instance ;
	@Transient
	public Empresa empresa;
	@Transient
	int anioActual; /*TODO: Hardcodeado para testear*/

	public void setAnioActual(int anoActual) {
		this.anioActual = anoActual;
	}

	public Antiguedad(){
		this.nombre = "Antiguedad";
		this.formula = "Edad de la Empresa";
		this.usuario = RepositorioUsuarios.getGen();
	}
	
	public void setEmpresa(Empresa empresa){
		this.empresa = empresa;
	}
	
	public static Antiguedad getInstance( ){
        if(instance == null){
            instance = new Antiguedad();
        }
        return instance;
	}	
	
	public int getEdadEmpresa(Empresa empresa){ //Devuelve el ano mas viejos
		
		Calendar cal = Calendar.getInstance(); 
		int anio = cal.get(Calendar.YEAR);
		
		this.setAnioActual(anio);
		
		List<Integer> periodos = empresa.getCuentas().stream().map(c1 ->Integer.parseInt(c1.getPeriodo())).collect(Collectors.toList());
		
		List<Integer> periodosOrdenados =  periodos.stream().sorted( (p1,p2) -> p1 < p2 ? -1:1 ).collect(Collectors.toList());
		
		return this.anioActual - periodosOrdenados.get(0);
	}
	
	@Override
	public void construirOperadorRaiz(Empresa empresa,String periodo) {
		this.raiz = new Constante(this.getEdadEmpresa(empresa));
	}

	
}
