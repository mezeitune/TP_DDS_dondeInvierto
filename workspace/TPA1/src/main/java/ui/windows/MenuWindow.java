package ui.windows;

import java.awt.Color;
import java.io.IOException;

import org.uqbar.arena.layout.ColumnLayout;
import org.uqbar.arena.widgets.Button;
import org.uqbar.arena.widgets.Label;
import org.uqbar.arena.widgets.Panel;
import org.uqbar.arena.windows.Dialog;
import org.uqbar.arena.windows.WindowOwner;

import ui.vm.MenuViewModel;


@SuppressWarnings("serial")
public class MenuWindow extends Dialog<MenuViewModel> {

	public MenuWindow(WindowOwner parent) {
		super(parent, new MenuViewModel());
	}

	@Override
	protected void createFormPanel(Panel mainPanel) {
		
		Panel form = new Panel(mainPanel);
		form.setLayout(new ColumnLayout(2));
		
		
		new Label(mainPanel).setText("MENÚ PRINCIPAL").setBackground(Color.ORANGE).setHeight(40);
	
		
	}
	protected void addActions(Panel actionsPanel){
		
		new Button(actionsPanel).setCaption("Cargar Cuentas")
								.onClick(() -> {
												try{
													this.getDelegate().close();
													CargaArchivoEmpresaWindow();
												}catch (IOException e) {
													e.printStackTrace();
												}
								});
	
		new Button(actionsPanel).setCaption("Cargar Indicadores")
									.onClick(() -> {
													try{
														this.getDelegate().close();
														CargarIndicadoresWindow();
													}catch (IOException e) {
														e.printStackTrace();
													}
									});
	}
	
	
	public void CargaArchivoEmpresaWindow() throws IOException {
		Dialog<?> dialog = new CargaArchivoEmpresaWindow(this);
		dialog.open();
		dialog.onAccept(() -> {});
	}
	public void CargarIndicadoresWindow() throws IOException {
		Dialog<?> dialog = new CargarIndicadoresWindow(this);
		dialog.open();
		dialog.onAccept(() -> {});
	}
	

}
