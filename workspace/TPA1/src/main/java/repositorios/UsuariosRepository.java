package repositorios;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.Query;

import model.Indicador;
import model.Usuario;

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
		Query queryUsuarios = entityManager().createQuery("from Usuario"); 
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


