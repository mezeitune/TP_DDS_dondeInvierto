package ui.windows;

import java.io.IOException;

import org.uqbar.arena.layout.ColumnLayout;
import org.uqbar.arena.widgets.Button;
import org.uqbar.arena.widgets.Label;
import org.uqbar.arena.widgets.Panel;
import org.uqbar.arena.widgets.TextBox;
import org.uqbar.arena.windows.Dialog;
import org.uqbar.arena.windows.SimpleWindow;
import org.uqbar.arena.windows.WindowOwner;

import ui.vm.*;



public class CargaArchivoEmpresaWindow extends SimpleWindow<CargaArchivoEmpresaViewModel>{

	public CargaArchivoEmpresaWindow(WindowOwner parent){
		super(parent, new CargaArchivoEmpresaViewModel());
	}
	@Override
	protected void createFormPanel(Panel mainPanel) {
		this.setTitle("Consultor de cuentas");
		Panel form = new Panel(mainPanel);
		form.setLayout(new ColumnLayout(2));
		
		new Label(form).setText("Ingrese el nombre del archivo (nombreArchivo.json)");
		
		new TextBox(form).setWidth(100).bindValueToProperty("archivo");
		
		
	}
	
	protected void addActions(Panel actionsPanel) {
		new Button(actionsPanel)
		.setCaption("Ver Datos")
		.onClick(() -> {
			try {
				DatosEmpresasWindow();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		});
		
	}
	
	public void DatosEmpresasWindow() throws IOException {
		Dialog<?> dialog = new DatosEmpresasWindow(this);
		dialog.open();
		dialog.onAccept(() -> {});
}
	
	
	
}
