package Server;

import javax.persistence.EntityManager;

import repositorios.DBRelacionalRepository;
import spark.Request;
import spark.Response;

public class EntityManagerFilter {
	DBRelacionalRepository<EntityManager> repositorio_global = new DBRelacionalRepository<>();

	public void restart(Request req, Response res) {
		repositorio_global.entityManager().clear();
	}

	
}
