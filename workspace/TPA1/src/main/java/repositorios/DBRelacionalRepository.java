package repositorios;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;


public class DBRelacionalRepository<E> {//Usamos Generics para cualquier tabla
	private EntityManager entityManager;


  
	public DBRelacionalRepository(EntityManager em){
		this.entityManager=em;
	}
	
	public <E> void agregar(E elemento){
		
		entityManager.persist(elemento);
	
	}
	
	public <E> E findById(Class<E> typeParameterClass, long id){
		return (E) entityManager.find(typeParameterClass,new Long(id));//el repo o el que implemente la interfaz de ORM deberia ser el encargado de hacer esto
		
	}
	
	public List<E> filtrarPorCampoEspecifico(Class<E> typeParameterClass,String tabla,String campoFiltro, String value){  //falta ver bien como van a ser los criterios de filtro 
		Query query = entityManager.createQuery("from "+tabla+" where "+campoFiltro+" = :value");
		query.setParameter("value", value);
		List<E> listado = query.getResultList();
		return listado;
	}


}

