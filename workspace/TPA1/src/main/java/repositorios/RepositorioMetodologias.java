package repositorios;

import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.Query;


import model.Metodologia;

public class RepositorioMetodologias extends RepositorioDBRelational<Metodologia> {

	@SuppressWarnings("unchecked")
	public  List<Metodologia> getMetodologias(){
		Query queryIndicadores = entityManager().createQuery("from Metodologia");
		return queryIndicadores.getResultList();
	}
	
	@SuppressWarnings("unchecked")
	public List<Metodologia> getMetodologiasDefinidasPorElUsuario(){
		Query queryIndicadores = entityManager().createQuery("from Metodologia");
		return queryIndicadores.getResultList(); 
	}


	public Metodologia getMetodologia(String nombreMet){
		return this.getMetodologias().stream().filter(unInd -> unInd.getNombre().equals(nombreMet)).collect(Collectors.toList()).get(0);
	}
	


	public boolean esMetodologiaRepetida(String nombreMetodologia) {
		return this.getMetodologias().stream().map(metodologia -> metodologia.getNombre()).collect(Collectors.toList()).contains(nombreMetodologia);
	}

	public void agregar(Metodologia metodologia)
	{
		entityManager().persist(metodologia);
	}
	


}


