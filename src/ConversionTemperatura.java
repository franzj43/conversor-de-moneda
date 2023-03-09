import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Label;

import java.text.DecimalFormat;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.List;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.VerifyListener;
import org.eclipse.swt.events.VerifyEvent;

public class ConversionTemperatura {

	protected Shell shell;
	private Text text;
	private Text text_1;
	private static final DecimalFormat DECIMAL_FORMAT = new DecimalFormat("#.##");
	
	public double aCelsius(double tempI, String unidad) {
		
		switch(unidad) {
		
		case "°C":
			return tempI * 1;
		case "°F":
			return (tempI-32)* 5/9;
		case "K":
			return tempI - 273.15;
		case "R":
			return (tempI - 491.67) * 5/9;
		default:
			return tempI * 1;
		
		}
		
	}
	
	public double aUnidad (double valorC, String unidad) {
		
		switch(unidad) {
		
		case "°C":
			return valorC * 1;
		case "°F":
			return (valorC * 9/5) + 32;
		case "K":
			return valorC + 273.15;
		case "R":
			return valorC * 9/5 + 491.67;
		default:
			return valorC * 1;
		
		}
		
	}
	
	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			ConversionTemperatura window = new ConversionTemperatura();
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
		shell.open();
		shell.layout();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}

	/**
	 * Create contents of the window.
	 */
	protected void createContents() {
		shell = new Shell();
		shell.setSize(350, 369);
		shell.setText("SWT Application");
		
		Label lblTemperatura = new Label(shell, SWT.NONE);
		lblTemperatura.setBounds(21, 19, 110, 15);
		lblTemperatura.setText("Temperatura 1");
		
		Label lblTemperatura_1 = new Label(shell, SWT.NONE);
		lblTemperatura_1.setBounds(201, 21, 109, 15);
		lblTemperatura_1.setText("Temperatura 2");
		
		List list = new List(shell, SWT.BORDER);
		list.setItems(new String[] {"°C", "°F", "K", "R"});
		list.setBounds(20, 40, 110, 110);
		
		List list_1 = new List(shell, SWT.BORDER);
		list_1.setItems(new String[] {"°C", "°F", "K", "R"});
		list_1.setBounds(201, 40, 110, 110);
		
		Label lblc = new Label(shell, SWT.NONE);
		lblc.setBounds(113, 195, 18, 15);
		lblc.setText("°C");
		
		Label lblf = new Label(shell, SWT.NONE);
		lblf.setBounds(292, 195, 18, 15);
		lblf.setText("°F");
		
		text = new Text(shell, SWT.BORDER);
		text.setEnabled(false);
		text.setBounds(21, 192, 86, 21);
		
		text_1 = new Text(shell, SWT.BORDER);
		text_1.setEnabled(false);
		text_1.setBounds(201, 192, 86, 21);
		
		Label lblXxGradosEquivalen = new Label(shell, SWT.NONE);
		lblXxGradosEquivalen.setAlignment(SWT.CENTER);
		lblXxGradosEquivalen.setBounds(21, 245, 290, 15);
		lblXxGradosEquivalen.setVisible(false);
		lblXxGradosEquivalen.setText("");
		
		Button btnAtrs = new Button(shell, SWT.NONE);
		btnAtrs.setBounds(44, 281, 75, 25);
		btnAtrs.setText("Atrás");
		
		Button btnCerrar = new Button(shell, SWT.NONE);
		btnCerrar.setBounds(225, 281, 75, 25);
		btnCerrar.setText("Cerrar");
		
		text.addModifyListener(new ModifyListener() {
			public void modifyText(ModifyEvent arg0) {
				
				if (text.getText().isEmpty() == false) {
					
					
					int indiceSeleccionado1 = list.getSelectionIndex();
					String unidad1 = list.getItem(indiceSeleccionado1);
					int indiceSeleccionado2 = list_1.getSelectionIndex();
					String unidad2 = list_1.getItem(indiceSeleccionado2);
					
					double valorOriginal = Double.parseDouble(text.getText());
					double valorEnCelsius = aCelsius(valorOriginal, unidad1);
					double valorFinal = aUnidad(valorEnCelsius, unidad2);
					
					text_1.setText(DECIMAL_FORMAT.format(valorFinal));
					
					lblXxGradosEquivalen.setText(DECIMAL_FORMAT.format(valorOriginal) + unidad1 + " equivale a " + DECIMAL_FORMAT.format(valorFinal) + unidad2);
					
				} else {
					
					text_1.setText("");
					
				}
				
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
				lblc.setText(list.getItem(indiceSeleccionado));
				
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
				lblf.setText(list_1.getItem(indiceSeleccionado));
				
			}
		});
		
		btnCerrar.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				
				shell.dispose();
				
			}
		});
		

		btnAtrs.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				
				shell.dispose();
				PantallaPrincipal pantallaPrincipal = new PantallaPrincipal();
				pantallaPrincipal.open();
				
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

	}

}
