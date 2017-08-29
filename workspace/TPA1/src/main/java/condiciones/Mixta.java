package condiciones;

import java.util.LinkedList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

import org.uqbar.commons.utils.Observable;
import usuario.Empresa;

@Observable
@Entity
public class Mixta extends TipoCondicion{

	private static Mixta instance ;
	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
	
	@JoinColumn(name = "tipoCondicionComposite_id",nullable = false)//el nullable es para poder eliminar deshabilitando las FK
	private List<TipoCondicion> tiposCondiciones = new LinkedList<TipoCondicion> ();

	public Mixta(List<TipoCondicion> tiposCondiciones){
			this.tiposCondiciones = tiposCondiciones;
	}
	
	public Mixta(){
		this.nombre = "Mixta";
	}
	
	
	public static Mixta getInstance( ) {
        if(instance == null){
            instance = new Mixta();
        }
        return instance;

	}	
	
	@Override
	public List<Empresa> evaluar(List<Empresa> empresas,List<String> periodos,Condicion condicion){
		
		int i=0;

		List<Empresa> empresasAEvaluar = new LinkedList<Empresa> (empresas);
		List<Empresa> empresasEvaluadas = new LinkedList<Empresa>();
		
		for(i=0;i<tiposCondiciones.size();i++){ /*Implementar orden superior ---> reduce o algo parecido a fold de funcional*/
			
			empresasEvaluadas = tiposCondiciones.get(i).evaluar(empresasAEvaluar, periodos, condicion);
			empresasAEvaluar = empresasEvaluadas;
		}
		return empresasEvaluadas;
	}

}

