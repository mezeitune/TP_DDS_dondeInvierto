package ui.windows;

import java.awt.Color;
import java.io.IOException;

import org.uqbar.arena.bindings.PropertyAdapter;
import org.uqbar.arena.layout.VerticalLayout;
import org.uqbar.arena.widgets.Button;
import org.uqbar.arena.widgets.Label;
import org.uqbar.arena.widgets.Panel;
import org.uqbar.arena.widgets.Selector;
import org.uqbar.arena.windows.Dialog;
import org.uqbar.arena.windows.WindowOwner;
import ui.vm.AgregarEmpresaViewModel;
import usuario.Empresa;

@SuppressWarnings("serial")
public class AgregarEmpresaWindow extends Dialog<AgregarEmpresaViewModel>{
	public AgregarEmpresaWindow(WindowOwner parent) {
		super(parent, new AgregarEmpresaViewModel());
	}
	protected void createFormPanel(Panel mainPanel) {
		mainPanel.setLayout(new VerticalLayout());
		Panel Panel = new Panel(mainPanel);
		
		new Label(Panel).setText("Seleccione una empresa ").setBackground(Color.ORANGE);
		Selector<Empresa> selectorEmpresa = new Selector<Empresa>(Panel).allowNull(true);
		selectorEmpresa.setWidth(100);
		selectorEmpresa.bindItemsToProperty("empresas").setAdapter(new PropertyAdapter(Empresa.class, "nombre"));
		selectorEmpresa.bindValueToProperty("empresa");

		/*new Label(Panel).setText("Seleccione un Periodo ").setBackground(Color.ORANGE);
		Selector<Empresa> selectorPeriodo = new Selector<Empresa>(Panel).allowNull(true);
		selectorPeriodo.setWidth(100);
		selectorPeriodo.bindItemsToProperty("periodos");
		selectorPeriodo.bindValueToProperty("periodo");*/

		
}

	protected void addActions(Panel actionsPanel){
		
		new Button(actionsPanel).setCaption("Agregar")
		.onClick(() -> {
						if(AgregarEmpresaViewModel.getCodigoError()==1){
							
								System.out.println("Empresa con periodo ya agregada a la lista, seleccione otra");
						
							}else{
								try{
									this.getDelegate().close();
							
									MetodologiasEmpresasWindow();
							
								}catch (IOException e) {
									e.printStackTrace();
								}
						}
			});
		new Button(actionsPanel).setCaption("Volver")
		.onClick(() -> {
								try{
									this.getDelegate().close();
							
									MetodologiasEmpresasWindow();
							
								}catch (IOException e) {
									e.printStackTrace();
								}
					
				});
}
	

	
	public void MetodologiasEmpresasWindow() throws IOException {
		Dialog<?> dialog = new MetodologiasEmpresasWindow(this);
		dialog.open();
		dialog.onAccept(() -> {});
	}
	

}	
	

