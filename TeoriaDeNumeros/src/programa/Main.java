package programa;

import java.awt.Button;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.TextArea;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class Main extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private int num1, num2, num3, num4, num5, resi, result;
	private JTextField jText1;
	private JTextField jText2;
	private JTextField jText3;
	private JTextField jText4;
	private JTextField jText5;
	ArrayList<String> residuos = new ArrayList<String>();
	ArrayList<Integer> listaA = new ArrayList<Integer>();
	ArrayList<Integer> listaB = new ArrayList<Integer>();
	ArrayList<Integer> listaC = new ArrayList<Integer>();
	ArrayList<Integer> todosNum = new ArrayList<Integer>();
	TextArea textArea;
	private JLabel jlabel;
	private String procedimiento = "Las divisiones sucesivas son: \n";
	private JLabel jLabel3;
	private JLabel jLabel4;
	String auxS = "";
	String convi="";

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Main frame = new Main();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public Main() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 600, 500);
		contentPane = new JPanel();
		contentPane.setToolTipText("");
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		jText1 = new JTextField();
		jText1.setText("0");
		jText1.addKeyListener(new KeyAdapter() {
			// Acción que permite solo el ingreso de caracteres numéricos
			public void keyTyped(KeyEvent e) {
				char caracter = e.getKeyChar();
				// Se permite el ingreso unicamente de números
				if ((caracter < '0' || caracter > '9') && (caracter != ',')) {
					e.consume();
				}
			}
		});
		jText1.setBounds(24, 92, 420, 20);
		contentPane.add(jText1);

		

		jLabel3 = new JLabel("MCD ES:");
		jLabel3.setFont(new Font("Tahoma", Font.PLAIN, 16));
		jLabel3.setBounds(208, 137, 94, 20);

		jLabel4 = new JLabel("");
		jLabel4.setFont(new Font("Tahoma", Font.PLAIN, 16));
		jLabel4.setBounds(284, 137, 94, 20);

		JButton jBoton = new JButton("Calcular");
		jBoton.setBackground(new Color(124, 252, 0));
		jBoton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				todosNum.clear();
				residuos.clear();
				listaA.clear();
				listaB.clear();
				listaC.clear();
				auxS = "";
				resi=0;
				result=0;
				String aux="";
				for(int i =0; i<jText1.getText().length();i++)
				{
					if(jText1.getText().charAt(i)!=',')
					{
						aux=aux+jText1.getText().charAt(i);
					}else
					{
						todosNum.add(Integer.parseInt(aux));
						aux="";						
					}
				}
				todosNum.add(Integer.parseInt(aux));
				if(todosNum.size()==2)
				{
					divisor(todosNum.get(0),todosNum.get(1));
				}else {
					mayores(todosNum);
				}		
				
				residuosM();
				iteraciones();
				textArea.setText(procedimiento);
				contentPane.add(jLabel3);
				jLabel4.setText(Integer.toString(result));
				contentPane.add(jLabel4);
				procedimiento = procedimiento
						+ "\n___________________________________________\nLas divisiones sucesivas son: \n";
				repaint();
			}
		});
		jBoton.setBounds(24, 123, 89, 23);
		contentPane.add(jBoton);

		jlabel = new JLabel("M.C.D ");
		jlabel.setFont(new Font("Tahoma", Font.BOLD, 36));
		jlabel.setBounds(24, 11, 122, 43);
		contentPane.add(jlabel);

		JLabel jLabel2 = new JLabel("Mediante el algoritmo de euclides");
		jLabel2.setFont(new Font("Tahoma", Font.PLAIN, 16));
		jLabel2.setBounds(156, 23, 288, 34);
		contentPane.add(jLabel2);

		JPanel panel = new JPanel();
		panel.setBounds(24, 191, 538, 247);
		contentPane.add(panel);
		panel.setLayout(null);

		textArea = new TextArea();
		textArea.setBounds(10, 10, 518, 227);
		textArea.setEditable(false);
		panel.add(textArea);

		textArea.setText("");

		Button button2 = new Button("Limpiar");
		button2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				textArea.setText("");
				procedimiento = "Las divisiones sucesivas son: \n";
				contentPane.remove(jLabel3);
				contentPane.remove(jLabel4);
				repaint();
			}
		});
		button2.setBounds(492, 163, 70, 22);
		contentPane.add(button2);
		
		JLabel lblNewLabel = new JLabel("Ingrese los n\u00FAmeros deseados separados por una coma");
		lblNewLabel.setBounds(24, 65, 338, 14);
		contentPane.add(lblNewLabel);

		Image icon = new ImageIcon(("logo.png")).getImage();
		setIconImage(icon);

		this.setLocationRelativeTo(null);
	}

	public int divisor(int A, int B) {

		if (A == 0)
			return B;

		if (B == 0)
			return A;

		resi = (A % B);

		procedimiento = procedimiento + A + " = " + B + "*" + (A / B) + " + " + resi + "\n";
		if (resi != 0) {
			residuos.add(resi + " = " + A + " - " + (A / B) + "*" + B + "\n");
			listaA.add(A);
			listaB.add(-1 * (A / B));
			listaC.add(B);
		}

		if (resi == 0) {
			result = B;
			procedimiento = procedimiento + "\n";
		}

		return divisor(B, resi);

	}

	public void residuosM() {
		if (residuos.size()!=0)
		{
		procedimiento = procedimiento + "\nResiduos desde la primera hasta la ultima ecuación:\n";
		}
		for (int i = residuos.size() - 1; i >= 0; i--) {
			
			procedimiento = procedimiento + residuos.get(i);
		}

	}

	public void iteraciones() {
		
		
		if (residuos.size()==0)
		{
			procedimiento = procedimiento + "No hay iteraciones";
		}
		else
		{
			procedimiento = procedimiento + "\nIteraciones al sustituir los residuos:\n";
			procedimiento = procedimiento + residuos.get( residuos.size()-1);
			int a, b, c, auxA = listaA.get(listaA.size() - 1), auxB = listaB.get(listaB.size() - 1), aux = 1;
			String signo = "";
			for (int i = residuos.size() - 2; i >= 0; i--) {
				a = listaA.get(i);
				b = listaB.get(i);
				c = listaC.get(i);
				procedimiento = procedimiento + result + " = " + aux + "*" + auxA + " + (" + auxB + ")(" + a + b + "*" + c
						+ ")" + auxS + "\n";
				signo = " - ";
				if (auxA == c) {
					int ay1 = auxB * b;
					int ay2 = aux + ay1;
					procedimiento = procedimiento + result + " = " + auxB + "*" + a + " + (" + ay2 + ")*" + c + auxS + "\n";
					if(i==0)
					{
						procedimiento= procedimiento + "\nLa conbinación resultante es:\n";
						procedimiento= procedimiento + result + " = " + auxB + "*" + a + " + (" + ay2 + ")*" + c + auxS + "\n";
					}
					aux = auxB;
					auxA = a;
					auxB = ay2;
					
				} else {
					int ay1 = auxB * b;
					auxS = auxS + "+ (" + aux + ")*" + auxA;
					procedimiento = procedimiento + result + " = " + auxB + "*" + a + " + (" + ay1 + ")*" + c + auxS + "\n";
					if(i==0)
					{
						procedimiento= procedimiento + "La conbinación resultante es:\n";
						procedimiento= procedimiento + result + " = " + auxB + "*" + a + " + (" + ay1 + ")*" + c + auxS + "\n";
					}
					aux = auxB;
					auxA = a;
					auxB = ay1;
					
				}

			}
		}
		

	}

	public void mayores(ArrayList<Integer> numeros) {
	
		
		int aux=numeros.get(1);
		for(int i=0; i<numeros.size();i++)
		{
			if(i==1)
			{
				i=2;
			}
			aux=divisor(numeros.get(i), aux);
			
		}
	}
}
