package Projet1; 
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

public class AccueilEtudiant extends JPanel {
	JPanel panel_start,panel_notes, pane_deconx,panelStartExm;
	JPanel tabpane, histoPane;
	JTable table;
	DefaultTableModel model;
	interfaceClient userGUI=null;
	public AccueilEtudiant( interfaceClient usergui) { 
		userGUI=usergui;
		this.setBounds(100, 100, 807, 425); 
		this.setBorder(new EmptyBorder(5, 5, 5, 5)); 
		this.setLayout(new FlowLayout());
		this.setBackground(new Color(224, 255, 255));
		
		JPanel main = new JPanel();
		main.setPreferredSize(new Dimension( 807, 425));
		main.setBackground(new Color(224, 255, 255));
		this.add(main, BorderLayout.CENTER);
		main.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("WELCOME "+userGUI.etudiant.nomComplet.toUpperCase());
		lblNewLabel.setFont(new Font("Calibri", Font.BOLD, 18));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(130, 11, 521, 58);
		main.add(lblNewLabel);
	//______________________________choix 1: commencer un examen________________________________________________	
		panel_start = new JPanel();
		panel_start.addMouseListener(new MouseAdapter() { 
			@Override
			public void mouseClicked(MouseEvent e) { 
				panel_start.setBackground(new Color(102, 205, 170));  
				userGUI.setCursor(Cursor.DEFAULT_CURSOR);
				
				panel_start.setVisible(false);
				panel_notes.setVisible(false);
				pane_deconx.setVisible(false);
				panelStartExm.setVisible(true);
			} 
			@Override
			public void mouseEntered(MouseEvent e) {
				panel_start.setBackground(new Color(32,178,170));
				userGUI.setCursor(Cursor.HAND_CURSOR);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				panel_start.setBackground(new Color( 102, 205, 170)); 
				userGUI.setCursor(Cursor.DEFAULT_CURSOR);
			}
		});
		panel_start.setBackground(new Color(102, 205, 170));
		panel_start.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, new Color(0, 139, 139)));
		panel_start.setBounds(232, 86, 316, 58);
		main.add(panel_start);
		panel_start.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel(" ");
		lblNewLabel_1.setIcon(new ImageIcon("ressources\\test (1).png"));
		lblNewLabel_1.setBounds(10, 11, 39, 36);
		panel_start.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Commencer un examen");
		lblNewLabel_2.setBounds(57, 11, 175, 36);
		panel_start.add(lblNewLabel_2);
	//______________________________choix 2:NOtes________________________________________________
		panel_notes = new JPanel();
		panel_notes.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) { 
				userGUI.remove(userGUI.accueilEtudiant);
				userGUI.add(histoPane, BorderLayout.CENTER);
				userGUI.revalidate();
				userGUI.repaint();
				panel_notes.setBackground(new Color(102, 205, 170));  
				userGUI.setCursor(Cursor.DEFAULT_CURSOR);
				  
			} 
			@Override
			public void mouseEntered(MouseEvent e) {
				panel_notes.setBackground(new Color(32,178,170));
				userGUI.setCursor(Cursor.HAND_CURSOR);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				panel_notes.setBackground(new Color( 102, 205, 170)); 
				userGUI.setCursor(Cursor.DEFAULT_CURSOR);
			}
		});
		panel_notes.setBackground(new Color(102, 205, 170)); 
		panel_notes.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, new Color(0, 139, 139)));
		panel_notes.setBounds(232, 164, 316, 58);
		main.add(panel_notes);
		panel_notes.setLayout(null);
		
		JLabel label = new JLabel(" ");
		label.setIcon(new ImageIcon("ressources\\exam (1).png"));
		label.setBounds(10, 8, 39, 42);
		panel_notes.add(label);
		
		JLabel lblHistoriqueDesExamens = new JLabel("Notes des Examens");
		lblHistoriqueDesExamens.setBounds(57, 11, 213, 36);
		panel_notes.add(lblHistoriqueDesExamens); 
  //_________________________________choix3 deconnexion_________________________________	
		pane_deconx = new JPanel();
		pane_deconx.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) { 
				userGUI.remove(userGUI.accueilEtudiant); 
				userGUI.add(userGUI.main, BorderLayout.CENTER); 
				userGUI.revalidate();
				userGUI.repaint();
				try {
					userGUI.loged=false;
					userGUI.client.close();
				} catch (IOException e1) { System.out.println("client has not disconnected");}
				pane_deconx.setBackground(new Color(102, 205, 170));  
				userGUI.setCursor(Cursor.DEFAULT_CURSOR);
			
			} 
			@Override
			public void mouseEntered(MouseEvent e) {
				pane_deconx.setBackground(new Color(32,178,170));
				userGUI.setCursor(Cursor.HAND_CURSOR);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				pane_deconx.setBackground(new Color( 102, 205, 170)); 
				userGUI.setCursor(Cursor.DEFAULT_CURSOR);
			}
		});
		pane_deconx.setBackground(new Color(102, 205, 170)); 
		pane_deconx.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, new Color(0, 139, 139)));
		pane_deconx.setBounds(232, 240, 316, 58);
		main.add(pane_deconx);
		pane_deconx.setLayout(null);
		
		JLabel deconxlbl = new JLabel(" ");
		deconxlbl.setIcon(new ImageIcon("ressources\\exit.png"));
		deconxlbl.setBounds(10, 8, 39, 42);
		pane_deconx.add(deconxlbl);
		
		JLabel lbldeconx = new JLabel("Déconnexion");
		lbldeconx.setBounds(57, 11, 213, 36);
		pane_deconx.add(lbldeconx); 
		
	//______________________________choisir Examen________________________________________________	
		panelStartExm = new JPanel();
		panelStartExm.setBackground(new Color(102, 205, 170));

		panelStartExm.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, new Color(0, 139, 139)));
		panelStartExm.setBounds(204, 80, 372, 226);
		panelStartExm.setVisible(false);
		main.add(panelStartExm);
		panelStartExm.setLayout(null);
		
		JLabel lblClose = new JLabel("");
		lblClose.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				panelStartExm.setVisible(false);
				panel_start.setVisible(true);
				panel_notes.setVisible(true);
				pane_deconx.setVisible(true);
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
		});
		lblClose.setIcon(new ImageIcon("ressources\\\\ferme.png")); 
		lblClose.setBounds(345, 3, 25, 26);
		panelStartExm.add(lblClose);
		
		JLabel lblNewLabel_3 = new JLabel("Examen:");
		lblNewLabel_3.setFont(new Font("Calibri", Font.PLAIN, 16));
		lblNewLabel_3.setBounds(71, 61, 98, 49);
		panelStartExm.add(lblNewLabel_3);
		  
		String[] choix=new String[userGUI.exams.size()];
		
		for (int i = 0; i < userGUI.exams.size(); i++) {
			 choix[i]=userGUI.exams.get(i).titre;   
		 } 
		//String[] choix= {"JAVA","PHP"};
		JComboBox comboBox = new JComboBox(choix);
		comboBox.setBackground(new Color(127, 255, 212));
		comboBox.setBounds(203, 72, 85, 26);
		panelStartExm.add(comboBox);
		
		JButton commencer = new JButton("Commencer");
		
		commencer.setFont(new Font("Calibri", Font.BOLD, 16));
		commencer.addActionListener(new ActionListener() { 
			@Override
			public void actionPerformed(ActionEvent e) {
				
				userGUI.exmChoisie= comboBox.getSelectedIndex();
				try { 
					userGUI.msg_out.writeUTF("examen"); 
					userGUI.msg_out.writeInt(userGUI.exmChoisie);
				} catch (IOException e1) { JOptionPane.showMessageDialog(null, "accueilEtudiant says: cant send the chosen exm to the server");}
				userGUI.remove(AccueilEtudiant.this); 
				userGUI.add(userGUI.exm_pane, BorderLayout.CENTER); 
				userGUI.exm_pane.running=true;
				userGUI.exm_pane.newthread();
				/* userGUI.exm_pane.running=true; userGUI.exm_pane.threadCtrl=true;
				 if(!userGUI.exm_pane.examthread.isAlive())//pour le 2eme appel
				 	userGUI.exm_pane.examthread.start(); 
				*/
 
				userGUI.revalidate();
				userGUI.repaint(); 
			}
		});
		commencer.setForeground(new Color(255, 255, 255));
		commencer.setBackground(new Color(60, 179, 113));
		commencer.setBounds(115, 156, 142, 35);
		panelStartExm.add(commencer);
		
	//______________________________Historique des examens________________________________________________
		histoPane = new JPanel();
		histoPane.setBackground(new Color(224, 255, 255));
		histoPane.setBorder(new EmptyBorder(5, 5, 5, 5)); 
		histoPane.setLayout(new FlowLayout());
		
		JPanel tabpane = new JPanel();
		tabpane.setBackground(new Color(224, 255, 255));
		tabpane.setPreferredSize(new Dimension(600,390));
		histoPane.add(tabpane, BorderLayout.CENTER);
		tabpane.setLayout(null);
		
	//	String[] th= {"nom","exam","note","date"};
	//	Object[][] data= {{"said","java","16","2020-09-22"} };
		table = new JTable( );
		table.setFont(new Font("Calibri", Font.PLAIN, 14)); 
		table.setModel(new DefaultTableModel(
			new Object[][] { },
			new String[] {
				"n", "Examen", "Note", "Date"
			}
		));
		model=(DefaultTableModel) table.getModel();
		for (int i = 0; i < userGUI.notes.size(); i++) {
			model.addRow(new Object[]{i+1,userGUI.notes.get(i).titreExamen,userGUI.notes.get(i).note,userGUI.notes.get(i).dateExm});
		}
		//model.addColumn("col1");
		
		table.setGridColor(new Color(0, 191, 255));
		table.getTableHeader().setBackground(new Color(32, 178, 170));
		table.getTableHeader().setFont(new Font("Calibri", Font.BOLD, 14));
		table.getTableHeader().setForeground(Color.black);
		table.setBounds(202, 133, 359, 320);
		JScrollPane scrollTab=new JScrollPane(table);
		scrollTab.getViewport().setBackground(new Color(224, 255, 255));
		scrollTab.setBounds(120, 57, 359, 320);
		tabpane.add(scrollTab);
		
		JLabel lblhead = new JLabel("Les notes des examens");
		lblhead.setHorizontalAlignment(SwingConstants.CENTER);
		lblhead.setForeground(new Color(0, 128, 128));
		lblhead.setFont(new Font("Calibri", Font.BOLD, 18));
		lblhead.setBounds(149, 11, 302, 35);
		tabpane.add(lblhead);
		
		JLabel lblback = new JLabel("");
		lblback.setIcon(new ImageIcon("ressources\\back (4).png"));
		lblback.setBounds(0, 0, 25, 25);
		lblback.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				userGUI.remove(histoPane);
				userGUI.add(userGUI.accueilEtudiant, BorderLayout.CENTER);
				userGUI.revalidate();
				userGUI.repaint();
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
		});
		tabpane.add(lblback); 
	}

}
