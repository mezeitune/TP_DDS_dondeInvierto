package ui.windows;

import java.awt.Color;
import java.io.IOException;

import org.uqbar.arena.bindings.PropertyAdapter;
import org.uqbar.arena.layout.ColumnLayout;
import org.uqbar.arena.widgets.Button;
import org.uqbar.arena.widgets.Label;
import org.uqbar.arena.widgets.Panel;
import org.uqbar.arena.widgets.Selector;
import org.uqbar.arena.widgets.TextBox;
import org.uqbar.arena.widgets.tables.Column;
import org.uqbar.arena.widgets.tables.Table;
import org.uqbar.arena.windows.Dialog;
import org.uqbar.arena.windows.WindowOwner;

import Condiciones.Condicion;
import parserFormulaInidicador.ParserFormulaToIndicador;
import ui.vm.CargarIndicadoresViewModel;
import ui.vm.CargarMetodologiaViewModel;
import usuario.Empresa;
import usuario.Indicador;
import usuario.Metodologia;

@SuppressWarnings("serial")
public class CargarMetodologiaWindow extends Dialog <CargarMetodologiaViewModel>{

	public CargarMetodologiaWindow(WindowOwner owner) {
		super(owner, new CargarMetodologiaViewModel());
	}
	
	@Override
	protected void createFormPanel(Panel mainPanel) {
		
		this.setTitle("Carga de metodologias");
		
		Panel form = new Panel(mainPanel);
		form.setLayout(new ColumnLayout(2));
		
		new Label(form).setText("Escriba el nombre de la metodologia").setBackground(Color.orange);
		
		new TextBox(form).setWidth(300).bindValueToProperty("nombreMetodologia");
		
		new Label(form).setText("Elija la condicion a aplicar de la metodologia").setBackground(Color.orange);
		
		Selector<Condicion> selectorCondicion = new Selector<Condicion>(mainPanel).allowNull(true);
		selectorCondicion.setWidth(100);
		selectorCondicion.bindItemsToProperty("condiciones").setAdapter(new PropertyAdapter(Metodologia.class, "nombre"));
		selectorCondicion.bindValueToProperty("condicion");
		
		new Label(mainPanel).setText("Metodologias Disponibles").setBackground(Color.ORANGE);
		
		Table<Metodologia> tableMetodologias = new Table<Metodologia>(mainPanel, Metodologia.class);
		
		tableMetodologias.setNumberVisibleRows(6).setWidth(200);
		
		tableMetodologias.bindItemsToProperty("metodologias"); 
		
		new Column<Metodologia>(tableMetodologias).setTitle("Nombre").bindContentsToProperty("nombre");
	}
	
	
	protected void addActions(Panel actionsPanel){
		
		new Button(actionsPanel).setCaption("Crear Condicion ")
		.onClick(() -> {
				try {
					CargarCondicionWindow();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}												
		}).setWidth(200);
		
		
		new Button(actionsPanel).setCaption("Crear Metodologia")
								.onClick(() -> {
										try {
											CargarMetodologiaViewModel.generarMetodologia();
											PreguntaNuevaMetodologia();
										} catch (IOException e) {
											// TODO Auto-generated catch block
											e.printStackTrace();
										}												
								}).setWidth(200);
	
		new Button(actionsPanel).setCaption("Cancelar")
									.onClick(() -> {
														this.getDelegate().close();
												
									}).setWidth(200);
	}
	
	
	public void MenuWindow() throws IOException {
		Dialog<?> dialog = new MenuWindow(this);
		dialog.open();
		dialog.onAccept(() -> {});
	}
	public void PreguntaNuevaMetodologia() throws IOException {
		Dialog<?> dialog = new PreguntaNuevaMetodologiaWindow(this);
		dialog.open();
		dialog.onAccept(() -> {});
		
	}
	public void MetodologiaRepetidaWindow() throws IOException {
		Dialog<?> dialog = new IndicadorRepetidoWindow(this);
		dialog.open();
		dialog.onAccept(() -> {});
		
	}
	public void CargarCondicionWindow() throws IOException {
		Dialog<?> dialog = new CargarCriterioWindow(this);
		dialog.open();
		dialog.onAccept(() -> {});
		
	}
	public void MetodologiaVaciaWindow() throws IOException {
		Dialog<?> dialog = new IndicadorVacioWindow(this);
		dialog.open();
		dialog.onAccept(() -> {});
		
	}
	
}

