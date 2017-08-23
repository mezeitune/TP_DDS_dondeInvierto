package repositorios;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;


public class DBRelacionalRepository<Entity> {//Usamos Generics para cualquier tabla
	private EntityManager entityManager;


  
	public DBRelacionalRepository(EntityManager em){
		this.entityManager=em;
	}
	
	@SuppressWarnings("hiding")
	public <Entity> void agregar(Entity elemento){
		
		entityManager.persist(elemento);
	
	}
	
	@SuppressWarnings("hiding")
	public <Entity> Entity findById(Class<Entity> typeParameterClass, long id){
		return entityManager.find(typeParameterClass,new Long(id));//el repo o el que implemente la interfaz de ORM deberia ser el encargado de hacer esto
		
	}
	
	public List<Entity> filtrarPorCampoEspecifico(Class<Entity> typeParameterClass,String tabla,String campoFiltro, String value){  //falta ver bien como van a ser los criterios de filtro 
		Query query = entityManager.createQuery("from "+tabla+" where "+campoFiltro+" = :value");
		query.setParameter("value", value);
		@SuppressWarnings("unchecked")
		List<Entity> listado = query.getResultList();
		return listado;
	}


}

