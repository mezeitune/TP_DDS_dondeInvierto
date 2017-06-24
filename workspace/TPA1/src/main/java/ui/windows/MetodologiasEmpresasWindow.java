package ui.windows;


import java.awt.Color;
import java.io.IOException;

import org.uqbar.arena.bindings.PropertyAdapter;
import org.uqbar.arena.layout.HorizontalLayout;
import org.uqbar.arena.layout.VerticalLayout;
import org.uqbar.arena.widgets.Button;
import org.uqbar.arena.widgets.CheckBox;
import org.uqbar.arena.widgets.Label;
import org.uqbar.arena.widgets.Panel;
import org.uqbar.arena.widgets.Selector;
import org.uqbar.arena.widgets.TextBox;
import org.uqbar.arena.widgets.tables.Column;
import org.uqbar.arena.widgets.tables.Table;
import org.uqbar.arena.windows.Dialog;
import org.uqbar.arena.windows.WindowOwner;
import org.uqbar.commons.model.ObservableUtils;

import repository.EmpresasAEvaluarRepository;
import ui.vm.DatosViewModel;
import ui.vm.MetodologiasEmpresasViewModel;
import usuario.Cuenta;
import usuario.Empresa;
import usuario.Indicador;

public class MetodologiasEmpresasWindow extends Dialog<MetodologiasEmpresasViewModel>{
	public MetodologiasEmpresasWindow(WindowOwner parent) throws IOException {
		super(parent, new MetodologiasEmpresasViewModel());
	}
	
	protected void createFormPanel(Panel mainPanel) {
		this.setTitle("Consulta de Metodologias aplicadas a empresas");
		
		
		mainPanel.setLayout(new HorizontalLayout());
		Panel Panel = new Panel(mainPanel);
		
		
		new Label(Panel).setText("Lista de empresas a evaluar").setBackground(Color.ORANGE);
		new Label(Panel).setText("Cuentas").setBackground(Color.ORANGE);
		
		Table<Cuenta> tableCuentas = new Table<Cuenta>(Panel, Cuenta.class);
		
		tableCuentas.bindItemsToProperty("empresasAEvaluar");
		new Column<Cuenta>(tableCuentas).setTitle("Nombre").bindContentsToProperty("nombre");
		
		new Button(Panel).setCaption("Agregar una Empresa")
		.onClick(() -> {
			try{
				this.getDelegate().close();
				AgregarEmpresaWindow();
			}catch (IOException e) {
				e.printStackTrace();
			}
		});
		
		new Button(Panel).setCaption("Eliminar una Empresa")
		.onClick(() -> {
			try{
				this.getDelegate().close();
				EliminarEmpresaWindow();
			}catch (IOException e) {
				e.printStackTrace();
			}
		});
		
		new Button(Panel).setCaption("Vaciar la Lista")
		.onClick(() -> {
			EmpresasAEvaluarRepository.vaciarListaDeEmpresasAEvaluar();
			
		});
		new Button(Panel).setCaption("Completar la Lista con todas las empresas del archivo")
		.onClick(() -> {
		
			EmpresasAEvaluarRepository.llenarListaDeEmpresasAEvaluar();
			
		});
		new Label(Panel).setText("");
		new Label(Panel).setText("");
		new Label(Panel).setText("");
		
		new Button(Panel).setCaption("Volver al Menu Principal")
		.onClick(() -> {
			try{
				this.getDelegate().close();
				MenuWindow();
			}catch (IOException e) {
				e.printStackTrace();
			}
		});
}

	protected void addActions(Panel actionsPanel){
		
		Panel Panel2 = new Panel(actionsPanel);
		Panel2.setLayout(new VerticalLayout());
		
		new Label(Panel2).setText("Seleccione la metodologia").setBackground(Color.ORANGE);
		Selector<Empresa> selectorMetodologia = new Selector<Empresa>(Panel2).allowNull(true);
		selectorMetodologia.setWidth(100);
		selectorMetodologia.bindItemsToProperty("metodologias").setAdapter(new PropertyAdapter(Empresa.class, "nombre"));
		selectorMetodologia.bindValueToProperty("metodologia");
		
		new Label(Panel2).setText("RANKING EMPRESAS").setBackground(Color.GREEN);
		
		Table<Empresa> tableEmpresasRankeadas = new Table<Empresa>(Panel2, Empresa.class);
		tableEmpresasRankeadas.setNumberVisibleRows(6).setWidth(200);
		tableEmpresasRankeadas.bindItemsToProperty("empresasRankeadas"); 
		new Column<Empresa>(tableEmpresasRankeadas).setTitle("Nombre").bindContentsToProperty("nombre");
		
		new Label(Panel2).setText("NO CONVIENE INVERTIR EN").setBackground(Color.RED);
		
		Table<Indicador> tableEmpresasQueNoConviene = new Table<Indicador>(Panel2, Indicador.class);
		tableEmpresasQueNoConviene.setNumberVisibleRows(6).setWidth(200);
		tableEmpresasQueNoConviene.bindItemsToProperty("empresasQueNoConvieneInvertir"); 
		new Column<Indicador>(tableEmpresasQueNoConviene).setTitle("Nombre").bindContentsToProperty("nombre");
		
}

	
	public void MenuWindow() throws IOException {
		Dialog<?> dialog = new MenuWindow(this);
		dialog.open();
		dialog.onAccept(() -> {});
	}
	public void CargaIndicadoresWindow() throws IOException {
		Dialog<?> dialog = new CargarIndicadoresWindow(this);
		dialog.open();
		dialog.onAccept(() -> {});
	}
	public void AgregarEmpresaWindow() throws IOException {
		Dialog<?> dialog = new AgregarEmpresaWindow(this);
		dialog.open();
		dialog.onAccept(() -> {});
	}
	public void EliminarEmpresaWindow() throws IOException {
		Dialog<?> dialog = new EliminarEmpresaWindow(this);
		dialog.open();
		dialog.onAccept(() -> {});
	}
}
