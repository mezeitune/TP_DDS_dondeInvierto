package repositorios;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import com.google.gson.Gson;

import metodologiasPredefinidas.WarrenBuffet;
import parserArchivos.ParserJsonAObjetosJava;
import parserArchivos.ParserJsonString;
import usuario.Metodologia;
import utilities.JPAUtility;

public class MetodologiasRepository extends DBRelacionalRepository<Metodologia> {
	public MetodologiasRepository(EntityManager em) {
		super(em);
		// TODO Auto-generated constructor stub
	}

	private ParserJsonAObjetosJava parserMetodologias= new ParserJsonAObjetosJava("metodologias.json");
	
	
	public static List<Metodologia> getMetodologias(){
		List<Metodologia> metodologias = new LinkedList<Metodologia> ();
		MetodologiasRepository.cargarMetodologias(metodologias);
		return metodologias;
	}
	
	public static List<Metodologia> getMetodologiasDefinidasPorElUsuario(){
		Query queryIndicadores = entityManager.createQuery("from Metodologia");
		return queryIndicadores.getResultList(); 
	}
	
	
	public static void cargarMetodologias(List<Metodologia> metodologias) {
		MetodologiasRepository.getMetodologiasDefinidasPorElUsuario().stream().forEach(metodologiaDefinidaPorUsuario -> metodologias.add(metodologiaDefinidaPorUsuario));
		MetodologiasRepository.getMetodologiasPredefinidas().stream().forEach(metodologiaPredefinida -> metodologias.add(metodologiaPredefinida));
	}

	public static List<Metodologia> getMetodologiasPredefinidas(){
		List<Metodologia> metodologiasPredefinidas = new LinkedList<Metodologia>();
		metodologiasPredefinidas.add(WarrenBuffet.getInstance());
		return metodologiasPredefinidas;
	}

	


	public boolean esMetodologiaRepetida(String nombreMetodologia) {
		return this.getMetodologias().stream().map(metodologia -> metodologia.getNombre()).collect(Collectors.toList()).contains(nombreMetodologia);
	}


}


