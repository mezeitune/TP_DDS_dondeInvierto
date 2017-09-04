package repositorios;

import java.util.LinkedList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import com.google.gson.Gson;

import comparadores.Comparador;
import comparadores.ComparadorMayor;
import comparadores.ComparadorMayorIgual;
import comparadores.ComparadorMenor;
import comparadores.ComparadorMenorIgual;
import condiciones.Comparativa;
import condiciones.Condicion;
import condiciones.Mixta;
import condiciones.Taxativa;
import condiciones.TipoCondicion;
import condicionesPredefinidas.MargenesCrecientes;
import parserArchivos.ParserJsonAObjetosJava;
import parserArchivos.ParserJsonString;
import usuario.Indicador;
import utilities.JPAUtility;

public class CondicionesRepository {

	private static ParserJsonAObjetosJava parserCondiciones = new ParserJsonAObjetosJava("condiciones.json");
	
	static JPAUtility jpa=JPAUtility.getInstance();
	static EntityManager entityManager = jpa.getEntityManager();
	@SuppressWarnings("rawtypes")
	static
	DBRelacionalRepository repo=new DBRelacionalRepository<>(entityManager);
	
	public static List<Condicion> getCondiciones() {
		List<Condicion> condiciones = new LinkedList<Condicion>();
		CondicionesRepository.cargarCondiciones(condiciones);
		return condiciones;
	}
	
	private static List<Condicion> getCondicionesPredefinidas() {
		List<Condicion> condicionesPredefinidas = new LinkedList<Condicion>();
		condicionesPredefinidas.add(MargenesCrecientes.getInstance());
		return condicionesPredefinidas;
	}

	private static List<Condicion> getCondicionesDefinidasPorElUsuario() {
		Query queryIndicadores = entityManager.createQuery("from Condicion"); 
		return queryIndicadores.getResultList(); 
		
	}

	public static void addCondicion(Condicion condicion) {
		
		repo.agregar(condicion);
		
		//String jsonElement = new Gson().toJson(condicion); 
		//ParserJsonString.anidadoDeJsonAUnJsonArrayEnUnArchivo("condiciones",jsonElement );
	}
	
	public static void cargarCondiciones(List<Condicion> condiciones){
		CondicionesRepository.getCondicionesDefinidasPorElUsuario().stream().forEach(condicionDefinidaPorUsuario -> condiciones.add(condicionDefinidaPorUsuario));
		CondicionesRepository.getCondicionesPredefinidas().stream().forEach(condicionPredefinida -> condiciones.add(condicionPredefinida));
	}
	
	public static List<TipoCondicion> getTipoCondiciones() {
		List<TipoCondicion> tipoCondiciones = new LinkedList<TipoCondicion>();
		tipoCondiciones.add(new Comparativa());
		tipoCondiciones.add(new Taxativa());
		tipoCondiciones.add(new Mixta());
		return tipoCondiciones;
	}

	public static List<Comparador> getComparadores() {
		List<Comparador> comparadores = new LinkedList<Comparador>();
		comparadores.add(new ComparadorMayor());
		comparadores.add(new ComparadorMayorIgual());
		comparadores.add(new ComparadorMenor());
		comparadores.add(new ComparadorMenorIgual());
		return comparadores;
	}
}
