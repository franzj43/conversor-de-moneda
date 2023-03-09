import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.custom.CCombo;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

public class PantallaPrincipal {

	protected Shell shlPantallaPrincipal;

	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			PantallaPrincipal window = new PantallaPrincipal();
			window.open();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Open the window.
	 */
	public void open() {
		Display display = Display.getDefault();
		createContents();
		shlPantallaPrincipal.open();
		shlPantallaPrincipal.layout();
		while (!shlPantallaPrincipal.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}

	/**
	 * Create contents of the window.
	 */
	protected void createContents() {
		shlPantallaPrincipal = new Shell();
		shlPantallaPrincipal.setSize(450, 109);
		shlPantallaPrincipal.setText("Inicio");
		
		Label lblTipoDeConversin = new Label(shlPantallaPrincipal, SWT.NONE);
		lblTipoDeConversin.setBounds(10, 10, 107, 15);
		lblTipoDeConversin.setText("Tipo de conversión");
		
		CCombo combo = new CCombo(shlPantallaPrincipal, SWT.BORDER);
		combo.setText("Seleccione el tipo de conversión");
		combo.setItems(new String[] {"Moneda", "Temperatura"});
		combo.setEditable(false);
		combo.setBounds(10, 35, 332, 21);
		
		Button btnAceptar = new Button(shlPantallaPrincipal, SWT.NONE);
		btnAceptar.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				
				int indiceSeleccionado = combo.getSelectionIndex();
				
				if (indiceSeleccionado == 0) {
					
					shlPantallaPrincipal.dispose();
					ConversionMoneda ventana = new ConversionMoneda();
					ventana.open();
					
					
				} else if (indiceSeleccionado == 1) {
					
					shlPantallaPrincipal.dispose();
					ConversionTemperatura ventana2 = new ConversionTemperatura();
					ventana2.open();
					
				};
				
			}
		});
		btnAceptar.setBounds(349, 31, 75, 25);
		btnAceptar.setText("Aceptar");

	}
}
