package repositorios;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import com.google.gson.Gson;

import metodologiasPredefinidas.WarrenBuffet;
import parserArchivos.ParserJsonAObjetosJava;
import parserArchivos.ParserJsonString;
import usuario.Indicador;
import usuario.Metodologia;
import usuario.Usuarios;
import utilities.JPAUtility;

public class UsuariosRepository extends DBRelacionalRepository<Metodologia> {
	public UsuariosRepository(EntityManager em) {
		super(em);
		// TODO Auto-generated constructor stub
	}


	
	public List<Usuarios> getUsuarios(){
		List<Usuarios> usuarios = new LinkedList<Usuarios> ();
		this.cargarUsuarios(usuarios);
		return usuarios;
	}
	
	
	
	public List<String> getNombreUsuarios(){
		return this.getUsuarios().stream().map(usuario -> usuario.getUsername())
													 .collect(Collectors.toList());
	}

	public List<Usuarios> getUsuariosDefinidosPorElUsuario() {
		Query queryUsuarios = entityManager.createQuery("from Usuarios"); 
		return queryUsuarios.getResultList(); 
	}
	
	
	public void cargarUsuarios(List<Usuarios> usuarios) {
		this.getUsuariosDefinidosPorElUsuario().stream().forEach(UsuarioDefinidoPorUsuario -> usuarios.add(UsuarioDefinidoPorUsuario));
	}
	


	public boolean esMetodologiaRepetida(String nombreUsuario) {
		return this.getUsuarios().stream().map(usuario -> usuario.getUsername()).collect(Collectors.toList()).contains(nombreUsuario);
	}


}


