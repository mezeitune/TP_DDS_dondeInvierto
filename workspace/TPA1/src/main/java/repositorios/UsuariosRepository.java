package repositorios;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import com.google.gson.Gson;

import metodologiasPredefinidas.WarrenBuffet;
import model.Indicador;
import model.Metodologia;
import model.Usuario;
import parserArchivos.ParserJsonAObjetosJava;
import parserArchivos.ParserJsonString;
import utilities.JPAUtility;

public class UsuariosRepository extends DBRelacionalRepository<Usuario> {
	
	public List<Usuario> getUsuarios(){
		List<Usuario> usuarios = new LinkedList<Usuario> ();
		this.cargarUsuarios(usuarios);
		return usuarios;
	}
	
	
	
	public List<String> getNombreUsuarios(){
		return this.getUsuarios().stream().map(usuario -> usuario.getUsername())
													 .collect(Collectors.toList());
	}

	public List<Usuario> getUsuariosDefinidosPorElUsuario() {
		Query queryUsuarios = entityManager().createQuery("from Usuarios"); 
		return queryUsuarios.getResultList(); 
	}
	
	

	public List<Indicador> getIndicadoresPorUsuario(String username) {
		Query queryUsuarios = entityManager().createQuery("from Indicador where usuario=:us");
		queryUsuarios.setParameter("us", username);
		return queryUsuarios.getResultList(); 
	}
	
	
	
	public void cargarUsuarios(List<Usuario> usuarios) {
		this.getUsuariosDefinidosPorElUsuario().stream().forEach(UsuarioDefinidoPorUsuario -> usuarios.add(UsuarioDefinidoPorUsuario));
	}
	
	public Usuario obtenerUsuario(String usuario){
		return this.getUsuarios().stream().filter(us -> us.getUsername().equals(usuario))
				 .collect(Collectors.toList()).get(0);
	}


	public Boolean usuarioExistente(String usuario){
		return this.getNombreUsuarios().contains(usuario);
	}
	
	public Boolean logeoCorrecto(String usuario,String password){
		if(this.usuarioExistente(usuario) && this.obtenerUsuario(usuario).getPassword().equals(password)){
			return true;
		}
		
		return false;
	}


}


