package ui.vm;


import java.io.IOException;
import java.util.List;

import org.uqbar.commons.utils.Observable;
import com.sun.jersey.api.client.Client;

import repository.ArchivoRepository;
import com.google.gson.Gson;
import usuario.*;


@SuppressWarnings("unused")
@Observable
public class DatosEmpresasViewModel{
	
	private Empresa empresa;
	private String nombre;
	private List<Cuenta> cuentas;
	private List<Empresa> empresas;
	private Cuenta cuenta;


	public DatosEmpresasViewModel() throws IOException {
		this.setEmpresas();
	}
	

	public void setEmpresas() throws IOException {
		ParserJsonAEmpresaAdapter parser = new ParserJsonAEmpresaAdapter();
		parser.definirObjetosDelArchivo(ArchivoRepository.getArchivo());
		this.empresas=parser.getEmpresasDelArchivo();
	}
	
	public String getNombre(){
		return empresa.getNombre();
	}
	
	public void setCuenta(Cuenta cuenta){
		this.cuenta=cuenta;
	}
	public Cuenta getCuenta(){
		return this.cuenta;
	}
	

	public void setEmpresa(Empresa empresaSeleccionada){
		this.empresa = empresaSeleccionada;
	}
	
	public Empresa getEmpresa(){
		return this.empresa;
	}
	
	public List<Empresa> getEmpresas(){
		return this.empresas;
	}
	
	public void setCuentas(){
		this.cuentas=this.empresa.getCuentas();
	}
	
	public List<Cuenta> getCuentas(){
		return cuentas;
	}
	
	
}