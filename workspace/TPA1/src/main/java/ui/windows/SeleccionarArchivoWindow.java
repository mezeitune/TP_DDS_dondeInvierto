package ui.windows;


import org.uqbar.arena.layout.ColumnLayout;
import org.uqbar.arena.widgets.Button;
import org.uqbar.arena.widgets.FileSelector;
import org.uqbar.arena.widgets.Label;
import org.uqbar.arena.widgets.Panel;
import org.uqbar.arena.widgets.TextBox;
import org.uqbar.arena.windows.Dialog;
import org.uqbar.arena.windows.WindowOwner;

import excepciones.ArchivoNotFoundException;
import excepciones.PathNotExistsException;
import excepciones.TipoDeArchivoIncorrectoException;
import repositorios.EmpresasRepository;
import ui.vm.*;



@SuppressWarnings("serial")
public class SeleccionarArchivoWindow extends Dialog<SeleccionarArchivoViewModel>{

	public SeleccionarArchivoWindow(WindowOwner parent){
		super(parent, new SeleccionarArchivoViewModel());
	}
	@Override
	protected void createFormPanel(Panel mainPanel) {
		
		this.setTitle("Seleccionar Archivo");
		Panel form = new Panel(mainPanel);
		
		form.setLayout(new ColumnLayout(1));
		
		new Label(form).setWidth(500);
		
		new Label(form).setText("Ruta seleccionada ");
		new TextBox(form).setWidth(500).bindValueToProperty("archivo");
		
	}
		
	protected void addActions(Panel actionsPanel) {
		
		new FileSelector(actionsPanel).setCaption("Seleccione el Archivo a Cargar")
	    							  .bindValueToProperty("archivo");
		
		
		new Button(actionsPanel).setCaption("Aceptar")
		.onClick(() -> {
						try {
							this.getModelObject().cargarArchivo();
							this.showInfo("El archivo se cargo exitosamente");
							this.getDelegate().close();
							MenuWindow();
						} catch (ArchivoNotFoundException e) {
							this.showError("Debe ingresar un archivo.csv");
						} catch (PathNotExistsException e) {
							this.showError("El archivo no existe, vuelva a intentarlo");
						} catch (TipoDeArchivoIncorrectoException e) {
							this.showError("La extension del archivo debe ser .csv");
						}
						
		});
		new Button(actionsPanel).setCaption("Cancelar")
		.onClick(() -> {
							this.getDelegate().close();
							EmpresasRepository.setArchivo(null);
							MenuWindow();
		});
		
		
	}
	
	public void MenuWindow() {
		Dialog<?> dialog = new MenuWindow(this);
		dialog.open();
		dialog.onAccept(() -> {});
	}


}
