package repositorios;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import condiciones.Condicion;
import parserArchivos.ParserCsv;
import parserArchivos.ParserJsonAObjetosJava;
import usuario.Empresa;
import usuario.Indicador;
import usuario.Metodologia;


public class DBRelacionalRepository<Entity> {//Usamos Generics para cualquier tabla
	private EntityManager entityManager;


  
	public DBRelacionalRepository(EntityManager em){
		this.entityManager=em;
	}
	
	@SuppressWarnings("hiding")
	public <Entity> void agregar(Entity elemento){
		
		entityManager.persist(elemento);
	
	}
	
	public <Entity> void eliminar (Entity elemento){
		entityManager.remove(elemento);
	}
	@SuppressWarnings("hiding")
	
	public <Entity> Entity findById(Class<Entity> typeParameterClass, long id){
		return entityManager.find(typeParameterClass,new Long(id));//el repo o el que implemente la interfaz de ORM deberia ser el encargado de hacer esto
		
	}
	
	public List <Entity> traerTodosLosCamposDeUnaTabla (Class<Entity> typeParameterClass ){
	
		Query query = entityManager.createQuery("from "+ typeParameterClass); 
		List <Entity> datosEnLaBD = query.getResultList(); 
		return datosEnLaBD;
	}
	
	public List<Entity> filtrarPorCampoEspecifico(Class<Entity> typeParameterClass,String tabla,String campoFiltro, String value){  //falta ver bien como van a ser los criterios de filtro 
		Query query = entityManager.createQuery("from "+tabla+" where "+campoFiltro+" = :value");
		query.setParameter("value", value);
		@SuppressWarnings("unchecked")
		List<Entity> listado = query.getResultList();
		return listado;
	}

	public void agregarDatosALaBDDDeLosArchivos(){
		this.agregarEmpresas();
		
		//this.agregarMetodologias();
		
		//this.agregarIndicadores();
	}
	
	public void agregarMetodologias(){
		ParserJsonAObjetosJava parserMetodologias = new ParserJsonAObjetosJava("metodologias.json");
		List <Metodologia> metodologiasAAgregarEnLaBD= parserMetodologias.getMetodologiasDelArchivo();
		Query queryMetodologias = entityManager.createQuery("from Metodologia"); 
		List <Condicion> metodologiasEnLaBD = queryMetodologias.getResultList(); 
		if(metodologiasEnLaBD.isEmpty()){
			entityManager.getTransaction().begin();
			
			metodologiasAAgregarEnLaBD.forEach(unaMet -> this.agregar(unaMet));
			
			entityManager.getTransaction().commit();
		}
	}
	
	
	
	
	public void agregarEmpresas(){
		ParserCsv parserCsv = new ParserCsv("empresas.csv");
		Query queryEmpresas = entityManager.createQuery("from Empresa"); 
		List <Empresa> empresasEnLaBD = queryEmpresas.getResultList(); 
		List <Empresa> empresasAAgregarEnLaBD= parserCsv.csvFileToEmpresas();
				
		if(empresasAAgregarEnLaBD.containsAll(empresasEnLaBD)){
			entityManager.getTransaction().begin();			

			empresasAAgregarEnLaBD.forEach(unaEmp -> this.agregar(unaEmp));
			
			entityManager.getTransaction().commit();
		
		}
	}
	
	public void agregarIndicadores(){
		ParserJsonAObjetosJava parserIndicadores = new ParserJsonAObjetosJava("indicadores.json");
		List<Indicador> indicadoresAAgregarEnLaBD= parserIndicadores.getIndicadoresDelArchivo();
		Query queryIndicadores = entityManager.createQuery("from Indicador"); 
		List <Indicador> indicadoresEnLaBD = queryIndicadores.getResultList(); 
		if(indicadoresEnLaBD.isEmpty()){
			entityManager.getTransaction().begin();
			
			indicadoresAAgregarEnLaBD.forEach(unInd->this.agregar(unInd));
			
			entityManager.getTransaction().commit();
		}
	}
	
}

