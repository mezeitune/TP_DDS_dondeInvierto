package Server;

import java.util.LinkedList;
import java.util.List;

import mocks.EmpresasMock;
import mocks.IndicadoresMock;
import model.Empresa;
import model.Indicador;
import model.Usuario;
import repositorios.EmpresasRepository;
import repositorios.UsuariosRepository;

public class Loader {

	private static EmpresasRepository repositorio_empresas=new EmpresasRepository();
	private static UsuariosRepository repositorio_usuarios=new UsuariosRepository();
	
	public static void main(String[] args) {
		new Loader().init();
	}
	
	public void init(){
		
		List<Empresa> empresas = new EmpresasMock().getEmpresasMockeadas();
		List<Indicador> indicadores = new IndicadoresMock().getIndicadoresMockeados();
		
		Usuario root = new Usuario("root","root");
		
		root.setIndicadores(indicadores);
		
		List<Usuario> usuarios = new LinkedList<Usuario>();
		
		usuarios.add(root);
		
		repositorio_empresas.begin();
		empresas.forEach(empresa -> repositorio_empresas.agregar(empresa));
		usuarios.forEach(usuario -> repositorio_usuarios.agregar(usuario));
		repositorio_empresas.commit();
	}
	
	
}
