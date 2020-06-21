package Projet1;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.ActionEvent;
import javax.swing.border.EtchedBorder;

public class createExm extends JFrame {

	private JPanel contentPane;
	private JTextField nbQst_input;
	private JTextField titre_input;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					createExm frame = new createExm();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
 
	public createExm() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 758, 414);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel container = new JPanel();
		container.setBackground(new Color(224, 255, 255));
		contentPane.add(container, BorderLayout.CENTER);
		container.setLayout(null);
		
		JPanel form_pane = new JPanel();
		form_pane.setBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(175, 238, 238), new Color(32, 178, 170)));
		form_pane.setBackground(new Color(173, 216, 230));
		form_pane.setBounds(160, 11, 450, 343);
		container.add(form_pane);
		form_pane.setLayout(null);
		
		JLabel label = new JLabel("Cr\u00E9er un nouveau examen");
		label.setForeground(new Color(47, 79, 79));
		label.setFont(new Font("Arial", Font.BOLD, 16));
		label.setBounds(120, 41, 209, 24);
		form_pane.add(label);
		
		JLabel lbltitre = new JLabel("Titre d'examen :");
		lbltitre.setFont(new Font("Arial", Font.BOLD, 12));
		lbltitre.setBounds(63, 100, 107, 24);
		form_pane.add(lbltitre);
		
		JLabel lblnbqst = new JLabel("Nombre de questions :");
		lblnbqst.setFont(new Font("Arial", Font.BOLD, 12));
		lblnbqst.setBounds(63, 146, 128, 24);
		form_pane.add(lblnbqst);
		
		JLabel lblmatiere = new JLabel("Matiere :");
		lblmatiere.setFont(new Font("Arial", Font.BOLD, 12));
		lblmatiere.setBounds(63, 194, 107, 24);
		form_pane.add(lblmatiere);
		
		nbQst_input = new JTextField();
		nbQst_input.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) { 
				super.keyPressed(e); //accept only numbers
				if(e.getKeyChar()>='0' && e.getKeyChar()<='9' || e.getKeyCode()==8 || e.getKeyCode()==KeyEvent.VK_SHIFT || e.getKeyCode()==KeyEvent.VK_CAPS_LOCK ) {
					nbQst_input.setEditable(true); 
				}else { 
					nbQst_input.setEditable(false); 
					JOptionPane.showMessageDialog(null, "seuls les nombre sont acceptés!!");
				}
			}
		});
		nbQst_input.setColumns(10);
		nbQst_input.setBounds(234, 146, 175, 24);
		form_pane.add(nbQst_input);
		
		titre_input = new JTextField();
		titre_input.setColumns(10);
		titre_input.setBounds(234, 100, 175, 24);
		form_pane.add(titre_input);
		
		JComboBox combo_matiere = new JComboBox();
		combo_matiere.setBackground(new Color(144, 238, 144));
		combo_matiere.setBounds(234, 196, 107, 23);
		form_pane.add(combo_matiere);
		
		JButton creerBtn = new JButton("Cr\u00E9er examen");
		creerBtn.setBackground(new Color(60, 179, 113));
		creerBtn.setBounds(129, 263, 200, 34);
		form_pane.add(creerBtn);
	}
}
