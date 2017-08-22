package repositorios;

import javax.persistence.EntityManager;


public class DBRelacionalRepository<E> {
	private EntityManager entityManager;

    final Class<E> typeParameterClass;

  
	public DBRelacionalRepository(Class<E> typeParameterClass,EntityManager em){
		this.entityManager=em;
		this.typeParameterClass = typeParameterClass;
	}
	
	public <E> void agregar(E vehiculo){//Poer
		entityManager.persist(vehiculo);
	
	}
	
	public <E> E findeById(long id){
		return (E) entityManager.find(typeParameterClass.getClass(),new Long(id));//el repo o el que implemente la interfaz de ORM deberia ser el encargado de hacer esto
		
	}
	
	public void all(){
		entityManager .createQuery("from Vehiculo where patente=123").getResultList();//adaptar
	}


}

