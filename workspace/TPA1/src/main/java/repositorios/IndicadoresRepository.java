package repositorios;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import com.google.gson.Gson;

import indicadoresPredefinidos.Antiguedad;
import indicadoresPredefinidos.PatrimonioNeto;
import parserArchivos.ParserJsonAObjetosJava;
import parserArchivos.ParserJsonString;
import usuario.Indicador;
import utilities.JPAUtility;

public class IndicadoresRepository {
	
	private static ParserJsonAObjetosJava parser = new ParserJsonAObjetosJava("indicadores.json");

	static JPAUtility jpa=JPAUtility.getInstance();
	static EntityManager entityManager = jpa.getEntityManager();
	@SuppressWarnings("rawtypes")
	static
	DBRelacionalRepository repo=new DBRelacionalRepository<>(entityManager);
	
	public static List<Indicador> getIndicadores(){
		List<Indicador> indicadores = new LinkedList<Indicador>();
		IndicadoresRepository.cargarIndicadores(indicadores);
		return indicadores;
	}
	
	public static List<String> getNombreIndicadores(){
		return IndicadoresRepository.getIndicadores().stream().map(indicador -> indicador.getNombre())
													 .collect(Collectors.toList());
	}
	
	public static List<Indicador> getIndicadoresDefinidosPorElUsuario() {
		Query queryIndicadores = entityManager.createQuery("from Indicador"); 
		return queryIndicadores.getResultList(); 
	}
	
	public static List<Indicador>  getIndicadoresPredefinidos() {
		List<Indicador> indicadoresPredefinidos = new LinkedList<Indicador>();
		indicadoresPredefinidos.add(PatrimonioNeto.getInstance());
		indicadoresPredefinidos.add(Antiguedad.getInstance());
		return indicadoresPredefinidos;
	}
	
	public static void cargarIndicadores(List<Indicador> indicadores) {
		IndicadoresRepository.getIndicadoresPredefinidos().stream().forEach(indicadorPredefinido -> indicadores.add(indicadorPredefinido));
		IndicadoresRepository.getIndicadoresDefinidosPorElUsuario().stream().forEach(indicadorDefinidoPorUsuario -> indicadores.add(indicadorDefinidoPorUsuario));
	}
	public static void addIndicador(Indicador nuevoIndicador) {
		entityManager.getTransaction().begin();
		repo.agregar(nuevoIndicador);
		entityManager.getTransaction().commit();

		//String jsonElement = new Gson().toJson(nuevoIndicador); 
		//ParserJsonString.anidadoDeJsonAUnJsonArrayEnUnArchivo("indicadores",jsonElement );
	}
	public static void deleteIndicador(Indicador indicadorAEliminar){
		
		entityManager.getTransaction().begin();
		repo.eliminar(indicadorAEliminar);
		entityManager.getTransaction().commit();
	}
	
}
