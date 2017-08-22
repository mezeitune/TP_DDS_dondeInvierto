package repositorios;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;


public class DBRelacionalRepository<E> {//Usamos Generics para cualquier tabla
	private EntityManager entityManager;

    final Class<E> typeParameterClass;

  
	public DBRelacionalRepository(Class<E> typeParameterClass,EntityManager em){
		this.entityManager=em;
		this.typeParameterClass = typeParameterClass;
	}
	
	public <E> void agregar(E elemento){
		entityManager.persist(elemento);
	
	}
	
	public <E> E findeById(long id){
		return (E) entityManager.find(typeParameterClass,new Long(id));//el repo o el que implemente la interfaz de ORM deberia ser el encargado de hacer esto
		
	}
	
	public List<E> filtrarPorOtroCampo(String tabla,String campoFiltro, String value){  //falta ver bien como van a ser los criterios de filtro 
		Query query = entityManager.createQuery("from "+tabla);
		//query.setParameter("code", criterioFiltro);
		List<E> listado = query.getResultList();
		return listado;
	}


}

