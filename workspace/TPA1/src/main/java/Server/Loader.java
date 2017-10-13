package Server;

import java.util.LinkedList;
import java.util.List;

import mocks.EmpresasMock;
import mocks.IndicadoresMock;
import repositorios.EmpresasRepository;
import repositorios.IndicadoresRepository;
import repositorios.UsuariosRepository;
import usuario.Empresa;
import usuario.Indicador;
import usuario.Usuarios;

public class Loader {

	private static EmpresasRepository repositorio_empresas=new EmpresasRepository();
	private static UsuariosRepository repositorio_usuarios=new UsuariosRepository();
	
	public static void main(String[] args) {
		new Loader().init();
	}
	
	public void init(){
		
		List<Empresa> empresas = new EmpresasMock().getEmpresasMockeadas();
		List<Indicador> indicadores = new IndicadoresMock().getIndicadoresMockeados();
		
		Usuarios root = new Usuarios("root","root");
		Usuarios admin = new Usuarios("admin","admin");
		
		root.setIndicadores(indicadores);
		admin.setIndicadores(indicadores);
		
		List<Usuarios> usuarios = new LinkedList<Usuarios>();
		
		usuarios.add(root);
		usuarios.add(admin);
		
		repositorio_empresas.begin();
		empresas.forEach(empresa -> repositorio_empresas.agregar(empresa));
		usuarios.forEach(usuario -> repositorio_usuarios.agregar(usuario));
		repositorio_empresas.commit();
	}
	
	
}
