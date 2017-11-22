package indicadoresPredefinidos;
import javax.persistence.Entity;

import model.Indicador;
import repositorios.RepositorioUsuarios;

@Entity
public class PatrimonioNeto extends Indicador{
	
	private static PatrimonioNeto instance ;

	public static PatrimonioNeto getInstance( ){
        if(instance == null){
            instance = new PatrimonioNeto();
        }
        return instance;

	}	
	
	public PatrimonioNeto(){
		this.setNombre("Patrimonio Neto");
		this.setFormula("1+2");
		this.usuario = RepositorioUsuarios.getGen();
	}
	
	public PatrimonioNeto(String nombre,String formula){
		super(nombre,formula);
	}
	@Override
	public int calcular(){
		return 3;
	}



	

	
	
}
