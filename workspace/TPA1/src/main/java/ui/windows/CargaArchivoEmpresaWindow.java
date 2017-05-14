package ui.windows;

import java.io.IOException;

import org.uqbar.arena.layout.ColumnLayout;
import org.uqbar.arena.widgets.Button;
import org.uqbar.arena.widgets.FileSelector;
import org.uqbar.arena.widgets.Label;
import org.uqbar.arena.widgets.Panel;
import org.uqbar.arena.widgets.TextBox;
import org.uqbar.arena.windows.Dialog;
import org.uqbar.arena.windows.WindowOwner;

import ui.vm.*;



@SuppressWarnings("serial")
public class CargaArchivoEmpresaWindow extends Dialog<CargaArchivoEmpresaViewModel>{

	public CargaArchivoEmpresaWindow(WindowOwner parent){
		super(parent, new CargaArchivoEmpresaViewModel());
	}
	@Override
	protected void createFormPanel(Panel mainPanel) {
		
		this.setTitle("Consultor de cuentas");
		Panel form = new Panel(mainPanel);
		
		form.setLayout(new ColumnLayout(1));
		
		new Label(form).setWidth(500);
		
		new Label(form).setText("Ruta seleccionada ");
		new TextBox(form).setWidth(500).bindValueToProperty("archivo");
		
	}
		
	protected void addActions(Panel actionsPanel) {
		
		new FileSelector(actionsPanel).setCaption("Seleccionar Archivo a Cargar")
	    							  .bindValueToProperty("archivo");
		
		
		
		new Button(actionsPanel).setCaption("Ver Datos")
								.onClick(() -> {
												try {
													this.getDelegate().close();
													DatosEmpresasWindow();
												} catch (IOException e) {
													e.printStackTrace();
												}
								});
		new Button(actionsPanel).setCaption("Cancelar")
								.onClick(() -> {
												try {
													this.getDelegate().close();
													MenuWindow();
												} catch (IOException e) {
													e.printStackTrace();
												}
								});
		
	}
	
	
	
	public void DatosEmpresasWindow() throws IOException {
		Dialog<?> dialog = new DatosEmpresasWindow(this);
		dialog.open();
		dialog.onAccept(() -> {});
	}
	public void MenuWindow() throws IOException {
		Dialog<?> dialog = new MenuWindow(this);
		dialog.open();
		dialog.onAccept(() -> {});
	}
	
	
	
}
