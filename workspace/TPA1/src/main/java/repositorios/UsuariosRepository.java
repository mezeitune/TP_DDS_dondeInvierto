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

public class UsuariosRepository extends DBRelacionalRepository<Usuarios> {
	public UsuariosRepository(EntityManager em) {
		super(em);
		// TODO Auto-generated constructor stub
	}


	
	public static List<Usuarios> getUsuarios(){
		List<Usuarios> usuarios = new LinkedList<Usuarios> ();
		UsuariosRepository.cargarUsuarios(usuarios);
		return usuarios;
	}
	
	
	
	public List<String> getNombreUsuarios(){
		return this.getUsuarios().stream().map(usuario -> usuario.getUsername())
													 .collect(Collectors.toList());
	}

	public static List<Usuarios> getUsuariosDefinidosPorElUsuario() {
		Query queryUsuarios = entityManager.createQuery("from Usuarios"); 
		return queryUsuarios.getResultList(); 
	}
	
	

	public static List<Indicador> getIndicadoresPorUsuario(String user) {
		Query queryUsuarios = entityManager.createQuery("from Indicador where usuario_id=:us");
		queryUsuarios.setParameter("us", obtUsuario(user).getId());
		return queryUsuarios.getResultList(); 
	}
	
	
	
	public static void cargarUsuarios(List<Usuarios> usuarios) {
		UsuariosRepository.getUsuariosDefinidosPorElUsuario().stream().forEach(UsuarioDefinidoPorUsuario -> usuarios.add(UsuarioDefinidoPorUsuario));
	}
	
	public static Usuarios obtUsuario(String usuario){
		List<Usuarios> usersFiltrados=UsuariosRepository.getUsuarios().stream().filter(us -> us.getUsername().equals(usuario))
				 .collect(Collectors.toList());
		return usersFiltrados.get(0);
	}


	public Boolean usuarioExistente(String usuario){
		return this.getNombreUsuarios().contains(usuario);
	}
	
	public Boolean logeoCorrecto(String usuario,String password){
		if(this.usuarioExistente(usuario) && this.obtUsuario(usuario).getPassword().equals(password)){
			return true;
		}
		
		return false;
	}


}


