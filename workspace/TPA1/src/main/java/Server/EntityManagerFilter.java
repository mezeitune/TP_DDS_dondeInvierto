package Server;

import javax.persistence.EntityManager;

import repositorios.RepositorioDBRelational;
import spark.Request;
import spark.Response;

public class EntityManagerFilter {
	RepositorioDBRelational<EntityManager> repositorio_global = new RepositorioDBRelational<>();

	public void restart(Request req, Response res) {
		repositorio_global.entityManager().clear();
	}

	
}
