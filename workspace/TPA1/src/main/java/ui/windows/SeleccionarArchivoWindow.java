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

import parser.ParserFormulaToIndicador;
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
						try{
							this.getDelegate().close();
							CargaExitosaWindow();
						}catch (IOException e) {
							e.printStackTrace();
						}
		});
		new Button(actionsPanel).setCaption("Cancelar")
		.onClick(() -> {
						try{
							this.getDelegate().close();
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
	
	
	
}
