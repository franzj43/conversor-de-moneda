import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.List;

import java.io.IOException;
import java.text.DecimalFormat;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Label;
import org.eclipse.wb.swt.SWTResourceManager;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.events.ShellAdapter;
import org.eclipse.swt.events.ShellEvent;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.VerifyListener;
import org.eclipse.swt.events.VerifyEvent;

public class ConversionMoneda {

	protected Shell shlConversorDeMoneda;
	private Text text;
	private Text text_1;
	private static final DecimalFormat DECIMAL_FORMAT = new DecimalFormat("#.##");

	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			ConversionMoneda window = new ConversionMoneda();
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
		try {
			createContents();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		shlConversorDeMoneda.open();
		shlConversorDeMoneda.layout();
		while (!shlConversorDeMoneda.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}

	/**
	 * Create contents of the window.
	 * @throws IOException 
	 */
	protected void createContents() throws IOException {
		shlConversorDeMoneda = new Shell();
		shlConversorDeMoneda.setSize(350, 369);
		shlConversorDeMoneda.setText("Conversor de Moneda");
		
		CambioMoneda cambio = new CambioMoneda();
		
		Label lblNewLabel = new Label(shlConversorDeMoneda, SWT.NONE);
		lblNewLabel.setBounds(21, 19, 109, 15);
		lblNewLabel.setText("Moneda 1");
		
		Label lblMonedaFinal = new Label(shlConversorDeMoneda, SWT.NONE);
		lblMonedaFinal.setBounds(201, 19, 100, 15);
		lblMonedaFinal.setText("Moneda 2");
		
		Label lblNewLabel_1 = new Label(shlConversorDeMoneda, SWT.NONE);
		lblNewLabel_1.setFont(SWTResourceManager.getFont("Segoe UI", 24, SWT.NORMAL));
		lblNewLabel_1.setText("→");
		lblNewLabel_1.setBounds(152, 66, 28, 52);
		
		Label lblQ = new Label(shlConversorDeMoneda, SWT.NONE);
		lblQ.setBounds(20, 195, 18, 15);
		lblQ.setText("Q.");
		
		Label lblQ_1 = new Label(shlConversorDeMoneda, SWT.NONE);
		lblQ_1.setBounds(201, 195, 18, 15);
		lblQ_1.setText("Q.");
		
		Label lblXxMonedaEquivale = new Label(shlConversorDeMoneda, SWT.NONE);
		lblXxMonedaEquivale.setAlignment(SWT.CENTER);
		lblXxMonedaEquivale.setBounds(21, 245, 290, 15);
		lblXxMonedaEquivale.setText("");
		lblXxMonedaEquivale.setVisible(false);
		
		List list = new List(shlConversorDeMoneda, SWT.BORDER);
		list.setItems(new String[] {"GTQ", "USD", "EUR", "GBP", "JPY", "KRW"});
		list.setBounds(20, 40, 110, 110);
		
		List list_1 = new List(shlConversorDeMoneda, SWT.BORDER);
		list_1.setItems(new String[] {"GTQ", "USD", "EUR", "GBP", "JPY", "KRW"});
		list_1.setBounds(201, 40, 110, 110);
		
		text = new Text(shlConversorDeMoneda, SWT.BORDER);
		text.setEnabled(false);
		text.setBounds(44, 192, 86, 21);
		
		text_1 = new Text(shlConversorDeMoneda, SWT.BORDER);
		text_1.setEnabled(false);
		text_1.setBounds(225, 192, 86, 21);
		
		Button btnAtrs = new Button(shlConversorDeMoneda, SWT.NONE);
		btnAtrs.setBounds(44, 281, 75, 25);
		btnAtrs.setText("Atrás");
		
		Button btnCerrar = new Button(shlConversorDeMoneda, SWT.NONE);
		btnCerrar.setBounds(225, 281, 75, 25);
		btnCerrar.setText("Cerrar");
		
		shlConversorDeMoneda.addShellListener(new ShellAdapter() {
			@Override
			public void shellClosed(ShellEvent e) {
				
				shlConversorDeMoneda.dispose();
				
			}
		});
		
		list.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				
				int[] lista1 = list.getSelectionIndices();
				int[] lista2 = list_1.getSelectionIndices();
				
				if(lista1.length > 0 && lista2.length > 0) {
					
					text.setEnabled(true);
					
				}
				
				int indiceSeleccionado = list.getSelectionIndex();
				
				switch(indiceSeleccionado) {
				
				case 0:
					
					lblQ.setText("Q");
					break;
				
				case 1:
					lblQ.setText("$");
					break;
				
				case 2:
					lblQ.setText("€");
					break;
					
				case 3:
					lblQ.setText("£");
					break;
					
				case 4:
					lblQ.setText("¥");
					break;
					
				case 5:
					lblQ.setText("₩");
					break;
				
				}
				
			}
		});
		
		list_1.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				
				int[] lista1 = list.getSelectionIndices();
				int[] lista2 = list_1.getSelectionIndices();
				
				if(lista1.length > 0 && lista2.length > 0) {
					
					text.setEnabled(true);
					
				}
				
				int indiceSeleccionado = list_1.getSelectionIndex();
				
				switch(indiceSeleccionado) {
				
				case 0:
					
					lblQ_1.setText("Q");
					break;
				
				case 1:
					lblQ_1.setText("$");
					break;
				
				case 2:
					lblQ_1.setText("€");
					break;
					
				case 3:
					lblQ_1.setText("£");
					break;
					
				case 4:
					lblQ_1.setText("¥");
					break;
					
				case 5:
					lblQ_1.setText("₩");
					break;
				
				}
				
			}
		});
		
		text.addVerifyListener(new VerifyListener() {
			public void verifyText(VerifyEvent arg0) {
				
				String currentText = ((Text) arg0.widget).getText();
				String newText = currentText.substring(0, arg0.start) + arg0.text + currentText.substring(arg0.end);
				
				if(!newText.matches("^\\d*\\.?\\d*$")) {
					
					arg0.doit = false;
					
				}
				
			}
		});
		
		text.addModifyListener(new ModifyListener() {
			public void modifyText(ModifyEvent arg0) {
				
				if (text.getText().isEmpty() == false) {
					
					int indiceSeleccionado1 = list.getSelectionIndex();
					String moneda1 = list.getItem(indiceSeleccionado1);
					int indiceSeleccionado2 = list_1.getSelectionIndex();
					String moneda2 = list_1.getItem(indiceSeleccionado2);
					
					//Se obtiene valor del campo Text
					double valorOriginal = Double.parseDouble(text.getText());
					
					//Se obtiene valor del tipo de cambio de las monedas a dólares
					double cambioMoneda1 = cambio.getCambioMoneda(moneda1);
					double cambioMoneda2 = cambio.getCambioMoneda(moneda2);
					
					double valorFinal = valorOriginal/cambioMoneda1 * cambioMoneda2;
					
					text_1.setText(DECIMAL_FORMAT.format(valorFinal));
					
					lblXxMonedaEquivale.setVisible(true);
					lblXxMonedaEquivale.setText(DECIMAL_FORMAT.format(valorOriginal) + " " + moneda1 + " equivale a " + DECIMAL_FORMAT.format(valorFinal) + " " + moneda2);
					
					
				} else {
					
					text_1.setText("");
					
				}
				
				
			}
				
		});
		

		btnAtrs.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				
				shlConversorDeMoneda.dispose();
				PantallaPrincipal pantallaPrincipal = new PantallaPrincipal();
				pantallaPrincipal.open();
				
			}
		});
		

		btnCerrar.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				
				shlConversorDeMoneda.dispose();
				
			}
		});
		
		
	}
}
