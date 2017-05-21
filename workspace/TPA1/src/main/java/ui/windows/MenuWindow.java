package ui.windows;

import java.awt.Color;
import java.io.IOException;

import org.uqbar.arena.layout.ColumnLayout;
import org.uqbar.arena.widgets.Button;
import org.uqbar.arena.widgets.Label;
import org.uqbar.arena.widgets.Panel;
import org.uqbar.arena.windows.Dialog;
import org.uqbar.arena.windows.WindowOwner;

import parser.ParserFormulaToIndicador;
import ui.vm.CargarIndicadoresViewModel;
import ui.vm.MenuViewModel;


@SuppressWarnings("serial")
public class MenuWindow extends Dialog<MenuViewModel> {

	public MenuWindow(WindowOwner cargaExitosaWindow) {
		super(cargaExitosaWindow, new MenuViewModel());
	}

	@Override
	protected void createFormPanel(Panel mainPanel) {
		this.setTitle("Sistema de carga y consulta");
		Panel form = new Panel(mainPanel);
		form.setLayout(new ColumnLayout(2));
		
		
		new Label(mainPanel).setText("MENÚ PRINCIPAL").setBackground(Color.ORANGE).setHeight(40);
		new Label(mainPanel).setText("Seleccione el archivo .CSV para consultar indicadores y cuentas").setBackground(Color.GREEN);
		
	}
	protected void addActions(Panel actionsPanel){
		
		new Button(actionsPanel).setCaption("Consultar Cuentas")
								.onClick(() -> {
												try{
													this.getDelegate().close();
													DatosEmpresasWindow();
												}catch (IOException e) {
													e.printStackTrace();
												}
								});
	
		new Button(actionsPanel).setCaption("Cargar Indicadores")
									.onClick(() -> {
													try{
														this.getDelegate().close();
														new ParserFormulaToIndicador();
														CargarIndicadoresWindow();
													}catch (IOException e) {
														e.printStackTrace();
													}
									});
		new Button(actionsPanel).setCaption("Consultar Indicadores")
		.onClick(() -> {
						try{
							this.getDelegate().close();
							new ParserFormulaToIndicador();
							DatosIndicadoresWindow();
						}catch (IOException e) {
							e.printStackTrace();
						}
		});
		new Button(actionsPanel).setCaption("Seleccionar Archivo")
		.onClick(() -> {
						try{
							this.getDelegate().close();
							SeleccionarArchivoWindow();
						}catch (IOException e) {
							e.printStackTrace();
						}
		});

	}
	
	
	public void DatosEmpresasWindow() throws IOException {
		Dialog<?> dialog = new DatosEmpresasWindow(this);
		dialog.open();
		dialog.onAccept(() -> {});
	}
	public void CargarIndicadoresWindow() throws IOException {
		Dialog<?> dialog = new CargarIndicadoresWindow(this);
		dialog.open();
		dialog.onAccept(() -> {});
	}
	public void DatosIndicadoresWindow() throws IOException {
		Dialog<?> dialog = new DatosIndicadoresWindow(this);
		dialog.open();
		dialog.onAccept(() -> {});
	}
	public void SeleccionarArchivoWindow() throws IOException {
		Dialog<?> dialog = new SeleccionarArchivoWindow(this);
		dialog.open();
		dialog.onAccept(() -> {});
	}

}
