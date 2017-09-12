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
import usuario.Metodologia;
import utilities.JPAUtility;

public class IndicadoresRepository extends DBRelacionalRepository<Indicador> {
	
	public IndicadoresRepository(EntityManager em) {
		super(em);
		// TODO Auto-generated constructor stub
	}
	private ParserJsonAObjetosJava parser = new ParserJsonAObjetosJava("indicadores.json");
	
	public List<Indicador> getIndicadores(){
		List<Indicador> indicadores = new LinkedList<Indicador>();
		this.cargarIndicadores(indicadores);
		return indicadores;
	}
	
	public List<String> getNombreIndicadores(){
		return this.getIndicadores().stream().map(indicador -> indicador.getNombre())
													 .collect(Collectors.toList());
	}
	
	public List<Indicador> getIndicadoresDefinidosPorElUsuario() {
		Query queryIndicadores = entityManager.createQuery("from Indicador"); 
		return queryIndicadores.getResultList(); 
	}
	
	public static List<Indicador>  getIndicadoresPredefinidos() {
		List<Indicador> indicadoresPredefinidos = new LinkedList<Indicador>();
		indicadoresPredefinidos.add(PatrimonioNeto.getInstance());
		indicadoresPredefinidos.add(Antiguedad.getInstance());
		return indicadoresPredefinidos;
	}
	
	public void cargarIndicadores(List<Indicador> indicadores) {
		this.getIndicadoresPredefinidos().stream().forEach(indicadorPredefinido -> indicadores.add(indicadorPredefinido));
		this.getIndicadoresDefinidosPorElUsuario().stream().forEach(indicadorDefinidoPorUsuario -> indicadores.add(indicadorDefinidoPorUsuario));
	}


	
}
