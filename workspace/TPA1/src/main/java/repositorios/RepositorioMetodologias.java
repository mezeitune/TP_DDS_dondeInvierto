package repositorios;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.Query;


import metodologiasPredefinidas.WarrenBuffet;
import model.Metodologia;

public class RepositorioMetodologias extends RepositorioDBRelational<Metodologia> {

	private static RepositorioCondiciones repositorio_condiciones = new RepositorioCondiciones();
	
	public  List<Metodologia> getMetodologias(){
		List<Metodologia> metodologias = new LinkedList<Metodologia> ();
		this.cargarMetodologias(metodologias);
		return metodologias;
	}
	
	@SuppressWarnings("unchecked")
	public List<Metodologia> getMetodologiasDefinidasPorElUsuario(){
		Query queryIndicadores = entityManager().createQuery("from Metodologia");
		return queryIndicadores.getResultList(); 
	}
	
	
	public  void cargarMetodologias(List<Metodologia> metodologias) {
		this.getMetodologiasDefinidasPorElUsuario().stream().forEach(metodologiaDefinidaPorUsuario -> metodologias.add(metodologiaDefinidaPorUsuario));
		this.getMetodologiasPredefinidas().stream().forEach(metodologiaPredefinida -> metodologias.add(metodologiaPredefinida));
	}

	public  List<Metodologia> getMetodologiasPredefinidas(){
		List<Metodologia> metodologiasPredefinidas = new LinkedList<Metodologia>();
		metodologiasPredefinidas.add(WarrenBuffet.getInstance());
		return metodologiasPredefinidas;
	}

	public Metodologia getMetodologia(String nombreMet){
		return this.getMetodologias().stream().filter(unInd -> unInd.getNombre().equals(nombreMet)).collect(Collectors.toList()).get(0);
	}
	


	public boolean esMetodologiaRepetida(String nombreMetodologia) {
		return this.getMetodologias().stream().map(metodologia -> metodologia.getNombre()).collect(Collectors.toList()).contains(nombreMetodologia);
	}

	public void agregar(Metodologia metodologia)
	{
		metodologia.getCondiciones().stream().forEach(condicion -> repositorio_condiciones.agregar(condicion));
		entityManager().persist(metodologia);
	}
	


}


