package Server;

import javax.persistence.EntityManager;
import repositorios.RepositorioDBRelational;

public class Cleanear {

	private static RepositorioDBRelational<EntityManager> repositorio_global = new RepositorioDBRelational<>();
	
	public static void main(String[] args) {
		init();
	}
	
	public static void init(){
	
		repositorio_global.removeAll();
		
		System.out.println("Schema droped");
		
	}
}
