package ui.windows;

import java.awt.Color;
import java.io.IOException;

import org.omg.CORBA.UserException;
import org.uqbar.arena.layout.ColumnLayout;
import org.uqbar.arena.widgets.Button;
import org.uqbar.arena.widgets.Label;
import org.uqbar.arena.widgets.Panel;
import org.uqbar.arena.windows.Dialog;
import org.uqbar.arena.windows.WindowOwner;

import exceptions.ArchivoInexistenteException;
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
		
		
		new Label(mainPanel).setText("MENU PRINCIPAL").setBackground(Color.ORANGE).setHeight(40);
		new Label(mainPanel).setText("Debe cargar un archivo .CSV previamente para poder consultar Empresas").setBackground(Color.GREEN);
		
	}
	protected void addActions(Panel actionsPanel){

		new Button(actionsPanel).setCaption("Consultar Empresas")
								.onClick(() -> {
												try{
													this.getDelegate().close();
													new ParserFormulaToIndicador();
													try{
													DatosIndicadoresWindow();
													} catch(ArchivoInexistenteException e){
														SeleccionarArchivoWindow();
													}
												}catch (IOException e) {
													e.printStackTrace();
												}
									});
		new Button(actionsPanel).setCaption("Seleccionar archivo de cuentas")
								.onClick(() -> {
												try{
													this.getDelegate().close();
													SeleccionarArchivoWindow();
												}catch (IOException e) {
													e.printStackTrace();
												}
								});
		new Button(actionsPanel).setCaption("Cargar y consultar indicadores")
								.onClick(() -> {
												new ParserFormulaToIndicador();
												try{
													this.getDelegate().close();
													CargarIndicadoresWindow();
												}catch (IOException e) {
													e.printStackTrace();
												}
								}).setWidth(200);
		
	}

	public void CargarIndicadoresWindow() throws IOException {
		Dialog<?> dialog = new CargarIndicadoresWindow(this);
		dialog.open();
		dialog.onAccept(() -> {});
	}
	public void DatosIndicadoresWindow() throws IOException {
		Dialog<?> dialog = new DatosWindow(this);
		dialog.open();
		dialog.onAccept(() -> {});
	}
	public void SeleccionarArchivoWindow() throws IOException {
		Dialog<?> dialog = new SeleccionarArchivoWindow(this);
		dialog.open();
		dialog.onAccept(() -> {});
	}

}
