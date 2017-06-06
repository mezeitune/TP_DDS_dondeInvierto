package ui.windows;

import java.io.IOException;
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

import exceptions.ArchivoInexistenteException;
import exceptions.PathIncorrectoException;
import exceptions.TipoDeArchivoIncorrectoException;
import parser.parserArchivos.CSVToEmpresas;
import parserFormulaInidicador.ParserFormulaToIndicador;
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
		
		/*new Button(actionsPanel).setCaption("Volver")
		.onClick(() -> {
						try{
							this.getDelegate().close();
							VerificarArchivo(ArchivoEIndicadoresUsuarioRepository.getArchivo());
							CargaExitosaWindow();
						}catch (ArchivoInexistenteException e) {
							try {
								MenuWindow();
							} catch (IOException e1) {
								e1.printStackTrace();
							}
						}catch (IOException e) {
							e.printStackTrace();
						}
						
		});*/

		
		new Button(actionsPanel).setCaption("Aceptar")
		.onClick(() -> {
						try{
							this.getDelegate().close();
							VerificarArchivo(ArchivoEIndicadoresUsuarioRepository.getArchivo());
							CargaExitosaWindow();
						}catch (IOException e) {
							e.printStackTrace();
						}
		});
		new Button(actionsPanel).setCaption("Cancelar")
		.onClick(() -> {
						try{
							this.getDelegate().close();
							ArchivoEIndicadoresUsuarioRepository.setArchivo(null);
							MenuWindow();
						}catch (IOException e) {
							e.printStackTrace();
						}
		});
		
		
	}
	
	public void MenuWindow() throws IOException {
		Dialog<?> dialog = new MenuWindow(this);
		dialog.open();
		dialog.onAccept(() -> {});
	}
	public void CargaExitosaWindow() throws IOException {
		Dialog<?> dialog = new CargaExitosaWindow(this);
		dialog.open();
		dialog.onAccept(() -> {});
	}
	
	public void VerificarArchivo(String archivo){
		String extension;
		if(archivo==null)
			throw new ArchivoInexistenteException();
		Path path = Paths.get(archivo);
		if (Files.notExists(path))
			throw new PathIncorrectoException();
		if(!((extension=FilenameUtils.getExtension(archivo)).equals("csv")))
			throw new TipoDeArchivoIncorrectoException();
	}

	
	
	
}
