package repositorios;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import parserArchivos.ParserCsv;
import usuario.Empresa;

public class DBRelacionalRepository<Entity> {//Usamos Generics para cualquier tabla
	protected static EntityManager entityManager;


  
	public DBRelacionalRepository(EntityManager em){
		this.entityManager=em;
	}
	
	@SuppressWarnings("hiding")
	public static <Entity> void agregar(Entity elemento){
		
		entityManager.persist(elemento);
	
	}
	
	public static <Entity> void eliminar (Entity elemento){
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
		ParserCsv parserCsv = new ParserCsv();
		Query queryEmpresas = entityManager.createQuery("from Empresa"); 
		List <Empresa> empresasEnLaBD = queryEmpresas.getResultList(); 
		List <Empresa> empresasAAgregarEnLaBD= parserCsv.csvFileToEmpresas("empresas.csv");
				
		if(empresasAAgregarEnLaBD.containsAll(empresasEnLaBD)){
			entityManager.getTransaction().begin();			

			empresasAAgregarEnLaBD.forEach(unaEmp -> this.agregar(unaEmp));
			
			entityManager.getTransaction().commit();
		
		}
	}

	

	
}
