package repositorios;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;

import com.google.gson.Gson;

import metodologiasPredefinidas.WarrenBuffet;
import parserArchivos.ParserJsonAObjetosJava;
import parserArchivos.ParserJsonString;
import usuario.Metodologia;
import utilities.JPAUtility;

public class MetodologiasRepository {
	private static ParserJsonAObjetosJava parserMetodologias= new ParserJsonAObjetosJava("metodologias.json");
	
	static JPAUtility jpa=JPAUtility.getInstance();
	static EntityManager entityManager = jpa.getEntityManager();
	@SuppressWarnings("rawtypes")
	static
	DBRelacionalRepository repo=new DBRelacionalRepository<>(entityManager);
	
	public static List<Metodologia> getMetodologias(){
		List<Metodologia> metodologias = new LinkedList<Metodologia> ();
		MetodologiasRepository.cargarMetodologias(metodologias);
		return metodologias;
	}
	
	public static List<Metodologia> getMetodologiasDefinidasPorElUsuario(){
		return parserMetodologias.getMetodologiasDelArchivo();
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

	
	public static void addMetodologia(Metodologia nuevaMetodologia) {
		entityManager.getTransaction().begin();
		repo.agregar(nuevaMetodologia);
		entityManager.getTransaction().commit();
		//String jsonElement = new Gson().toJson(nuevaMetodologia); 
		//ParserJsonString.anidadoDeJsonAUnJsonArrayEnUnArchivo("metodologias",jsonElement );
	}

	public static boolean esMetodologiaRepetida(String nombreMetodologia) {
		return MetodologiasRepository.getMetodologias().stream().map(metodologia -> metodologia.getNombre()).collect(Collectors.toList()).contains(nombreMetodologia);
	}
}



