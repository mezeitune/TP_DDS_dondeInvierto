package ui.windows;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.apache.commons.io.FilenameUtils;
import org.uqbar.arena.layout.ColumnLayout;
import org.uqbar.arena.widgets.Button;
import org.uqbar.arena.widgets.FileSelector;
import org.uqbar.arena.widgets.Label;
import org.uqbar.arena.widgets.Panel;
import org.uqbar.arena.widgets.TextBox;
import org.uqbar.arena.windows.Dialog;
import org.uqbar.arena.windows.WindowOwner;

import excepciones.ArchivoInexistenteException;
import excepciones.PathIncorrectoException;
import excepciones.TipoDeArchivoIncorrectoException;
import repository.ArchivoEIndicadoresUsuarioRepository;
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
							VerificarArchivo(ArchivoEIndicadoresUsuarioRepository.getArchivo());
							this.showInfo("El archivo se cargo exitosamente");
							this.getDelegate().close();
							MenuWindow();
		});
		new Button(actionsPanel).setCaption("Cancelar")
		.onClick(() -> {
							this.getDelegate().close();
							ArchivoEIndicadoresUsuarioRepository.setArchivo(null);
							MenuWindow();
		});
		
		
	}
	
	public void MenuWindow() {
		Dialog<?> dialog = new MenuWindow(this);
		dialog.open();
		dialog.onAccept(() -> {});
	}

	public void VerificarArchivo(String archivo){
		if(archivo==null) throw new ArchivoInexistenteException();
		Path path = Paths.get(archivo);
		if (Files.notExists(path))
			throw new PathIncorrectoException();
		if(!((FilenameUtils.getExtension(archivo)).equals("csv")))
			throw new TipoDeArchivoIncorrectoException();
	}

}
