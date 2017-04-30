package ui.vm;


import java.io.IOException;
import java.util.List;

import org.uqbar.commons.utils.Observable;
import com.sun.jersey.api.client.Client;

import parser.ParserJsonAEmpresaAdapter;
import repository.ArchivoRepository;
import com.google.gson.Gson;
import usuario.*;


@SuppressWarnings("unused")
@Observable
public class DatosEmpresasViewModel{
	
	private Empresa empresa;
	private String nombre;
	private static List<Cuenta> cuentas;
	private List<Empresa> empresas;
	private Cuenta cuenta;
	private String periodo;
	private String periodoo;
	
	
	public DatosEmpresasViewModel() {
		this.setEmpresas();
	}
	

	public void setEmpresas() {
		ParserJsonAEmpresaAdapter parser = new ParserJsonAEmpresaAdapter(ArchivoRepository.getArchivo());
		this.empresas=parser.getEmpresasDelArchivo();
	}
	
	public String getNombre(){
		return empresa.getNombre();
	}
	
	public String getPeriodo(){
		return cuenta.getPeriodo();
	}
	
	public void setPeriodo(String periodo){
		this.periodo= this.cuenta.getPeriodo();
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
		this.cuentas=this.empresa.getCuentasPorPeriodo(this.getPeriodoo());
	}
	
	public String getPeriodoo() {
		return periodoo;
	}


	public void setPeriodoo(String periodooo) {
		this.periodoo = periodooo;
	}


	public static List<Cuenta> getCuentas(){
		return cuentas;
	}
	
	
}