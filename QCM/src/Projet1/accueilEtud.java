package Projet1;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.ImageIcon;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JComboBox;
import javax.swing.JButton;

public class accueilEtud extends JFrame {

	public JPanel contentPane;
	static interfaceClient userGUI;
 
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					accueilEtud frame = new accueilEtud(userGUI);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
 
	public accueilEtud(interfaceClient userGui) {
		userGUI=userGui;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 807, 425);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new FlowLayout());
		
		
		
		JPanel main = new JPanel();
		main.setPreferredSize(new Dimension( 807, 425));
		main.setBackground(new Color(224, 255, 255));
		contentPane.add(main, BorderLayout.CENTER);
		main.setLayout(null);
		
		JPanel panelChoiExm = new JPanel();
		panelChoiExm.setBackground(new Color(102, 205, 170));
		panelChoiExm.setBounds(204, 80, 372, 226);
		main.add(panelChoiExm);
		panelChoiExm.setLayout(null);
		
		JLabel lblClose = new JLabel("test");
		/*lblClose.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				panelStartExm.setVisible(false);
				panel_start.setVisible(true);
				panel_notes.setVisible(true);
				userGUI.setCursor(Cursor.DEFAULT_CURSOR);
						 
			}
			@Override
			public void mouseEntered(MouseEvent e) { 
				userGUI.setCursor(Cursor.HAND_CURSOR);
			}
			@Override
			public void mouseExited(MouseEvent e) { 
				userGUI.setCursor(Cursor.DEFAULT_CURSOR);
			} 
		});*/
		lblClose.setIcon(new ImageIcon("ressources\\cancel.png"));
		lblClose.setBounds(348, 1, 25, 26);
		panelChoiExm.add(lblClose);
		
		JLabel lblNewLabel_3 = new JLabel("examen:");
		lblNewLabel_3.setFont(new Font("Calibri", Font.PLAIN, 16));
		lblNewLabel_3.setBounds(71, 61, 98, 49);
		panelChoiExm.add(lblNewLabel_3);
		 
		
		/*String[] choix=new String[userGUI.exams.size()];
		for (int i = 0; i < userGUI.exams.size(); i++) {
			choix[i]=userGUI.exams.get(i).matiere; 
		}*/
		String[] choix= {"JAVA","PHP"};
		JComboBox comboBox = new JComboBox(choix);
		comboBox.setBackground(new Color(127, 255, 212));
		comboBox.setBounds(203, 72, 85, 26);
		panelChoiExm.add(comboBox);
		
		JButton btnNewButton = new JButton("Commencer");
		btnNewButton.setFont(new Font("Calibri", Font.BOLD, 16));
		btnNewButton.setForeground(new Color(255, 255, 255));
		btnNewButton.setBackground(new Color(60, 179, 113));
		btnNewButton.setBounds(115, 156, 142, 35);
		panelChoiExm.add(btnNewButton);
		
		JLabel lblNewLabel = new JLabel("WELCOME");
		lblNewLabel.setFont(new Font("Calibri", Font.BOLD, 18));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(130, 11, 521, 58);
		main.add(lblNewLabel);
		
		JPanel panel_start = new JPanel();
		panel_start.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {
				 
				panel_start.setBackground(new Color(102, 205, 170));  
				accueilEtud.this.setCursor(Cursor.DEFAULT_CURSOR);
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				panel_start.setBackground(new Color(32,178,170));
				accueilEtud.this.setCursor(Cursor.HAND_CURSOR);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				panel_start.setBackground(new Color( 102, 205, 170)); 
				accueilEtud.this.setCursor(Cursor.DEFAULT_CURSOR);
			}
		});
		panel_start.setBackground(new Color(102, 205, 170));
		panel_start.setBounds(232, 86, 316, 58);
		main.add(panel_start);
		panel_start.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel(" ");
		lblNewLabel_1.setIcon(new ImageIcon("ressources\\test (1).png"));
		lblNewLabel_1.setBounds(10, 11, 39, 36);
		panel_start.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Faire un examen");
		lblNewLabel_2.setBounds(57, 11, 175, 36);
		panel_start.add(lblNewLabel_2);
		
		JPanel panel_notes = new JPanel(); panel_notes.setVisible(false);panel_start.setVisible(false);
		panel_notes.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				 
				panel_notes.setBackground(new Color(102, 205, 170));  
				accueilEtud.this.setCursor(Cursor.DEFAULT_CURSOR);
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				panel_notes.setBackground(new Color(32,178,170));
				accueilEtud.this.setCursor(Cursor.HAND_CURSOR);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				panel_notes.setBackground(new Color( 102, 205, 170)); 
				accueilEtud.this.setCursor(Cursor.DEFAULT_CURSOR);
			}
		});
		panel_notes.setBackground(new Color(102, 205, 170));
		panel_notes.setBounds(232, 164, 316, 58);
		main.add(panel_notes);
		panel_notes.setLayout(null);
		
		JLabel label = new JLabel(" ");
		label.setIcon(new ImageIcon("ressources\\exam (1).png"));
		label.setBounds(10, 8, 39, 42);
		panel_notes.add(label);
		
		JLabel lblHistoriqueDesExamens = new JLabel("Notes des examens complet\u00E9s");
		lblHistoriqueDesExamens.setBounds(57, 11, 213, 36);
		panel_notes.add(lblHistoriqueDesExamens);
	}
}
