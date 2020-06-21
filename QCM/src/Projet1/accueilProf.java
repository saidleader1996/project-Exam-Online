package Projet1; 
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.border.BevelBorder;

public class accueilProf extends JPanel { 
	JPanel main,bresult, tabpane,pane_deconx,newExm_pane;//, histoPane;
	JTable table;
	DefaultTableModel model;
	String exmChoisi;
	createExm_Pane nvExmPane=null; 
	ArrayList<Matiere> matieres=new ArrayList<Matiere>();
	interfaceClient userGUI=null; 
	public accueilProf(interfaceClient usergui) { 	
			userGUI=usergui;
			this.setBounds(100, 100, 807, 425); 
			this.setBorder(new EmptyBorder(5, 5, 5, 5)); 
			this.setLayout(new FlowLayout());
			this.setBackground(new Color(224, 255, 255));
			
			main = new JPanel();
			main.setPreferredSize(new Dimension( 807, 425));
			main.setBackground(new Color(224, 255, 255));
			this.add(main, BorderLayout.CENTER);
			main.setLayout(null);
			
			JLabel lblNewLabel = new JLabel("WELCOME "+userGUI.prof.nomComplet.toUpperCase());
			lblNewLabel.setFont(new Font("Calibri", Font.BOLD, 18));
			lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
			lblNewLabel.setBounds(130, 11, 521, 58);
			main.add(lblNewLabel);
		//______________________________________________________________________________	
			bresult = new JPanel();
			bresult.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, new Color(0, 139, 139)));
			bresult.addMouseListener(new MouseAdapter() { 
				@Override
				public void mouseClicked(MouseEvent e) { 
					bresult.setBackground(new Color(102, 205, 170));  
					userGUI.setCursor(Cursor.DEFAULT_CURSOR);
					main.setVisible(false);
					tabpane.setVisible(true);
				} 
				@Override
				public void mouseEntered(MouseEvent e) {
					bresult.setBackground(new Color(32,178,170));
					userGUI.setCursor(Cursor.HAND_CURSOR);
				}
				@Override
				public void mouseExited(MouseEvent e) {
					bresult.setBackground(new Color( 102, 205, 170)); 
					userGUI.setCursor(Cursor.DEFAULT_CURSOR);
				}
			});
			bresult.setBackground(new Color(102, 205, 170));
			bresult.setBounds(232, 86, 316, 58);
			main.add(bresult);
			bresult.setLayout(null);
			
			JLabel iconlbl = new JLabel(" ");
			iconlbl.setIcon(new ImageIcon("ressources\\list (1).png"));
			iconlbl.setBounds(10, 11, 39, 36);
			bresult.add(iconlbl);
			
			JLabel lblresultat = new JLabel("Notes des étudiants");
			lblresultat.setBounds(57, 11, 175, 36);
			bresult.add(lblresultat); 
		//_________________________decconnexion pane_______________________________________
			pane_deconx = new JPanel();
			pane_deconx.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, new Color(0, 139, 139)));
			pane_deconx.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) { 
					userGUI.remove(userGUI.accueilprof); 
					userGUI.getContentPane().add(userGUI.main, BorderLayout.CENTER);
					userGUI.main.repaint();
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
	 //_________________________________choix3 creer nv examen_________________________________	
			newExm_pane= new JPanel();
			newExm_pane.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					
					try {	//get 
						userGUI.msg_out.writeUTF("matieres");
						userGUI.msg_out.flush(); 
						matieres=(ArrayList<Matiere>) userGUI.obj_In.readObject();
						//System.out.println("recu "+matieres.toString());
					} catch (ClassNotFoundException e1) { System.out.println("accueilProf: cant get matiere"+e1.getMessage());}
					catch (IOException e1) { System.out.println("accueilProf: cant get matiere"+e1.getMessage());}
					
					
					nvExmPane=new createExm_Pane(userGUI); 
					userGUI.remove(userGUI.accueilprof );
					userGUI.accueilprof.nvExmPane.setVisible(true);
					userGUI.add(nvExmPane,"Center"); 
					userGUI.revalidate();
					userGUI.repaint();  
					pane_deconx.setBackground(new Color(102, 205, 170));  
					userGUI.setCursor(Cursor.DEFAULT_CURSOR);
				
				} 
				@Override
				public void mouseEntered(MouseEvent e) {
					newExm_pane.setBackground(new Color(32,178,170));
					userGUI.setCursor(Cursor.HAND_CURSOR);
				}
				@Override
				public void mouseExited(MouseEvent e) {
					newExm_pane.setBackground(new Color( 102, 205, 170)); 
					userGUI.setCursor(Cursor.DEFAULT_CURSOR);
				}
			});
			newExm_pane.setBackground(new Color(102, 205, 170)); 
			newExm_pane.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, new Color(0, 139, 139)));
			newExm_pane.setBounds(232, 164, 316, 58);
			main.add(newExm_pane);
			newExm_pane.setLayout(null);
			
			JLabel lblicon = new JLabel(" ");
			lblicon.setIcon(new ImageIcon("ressources\\add (1).png"));
			lblicon.setBounds(10, 8, 39, 42);
			newExm_pane.add(lblicon);
			
			JLabel lblcreer = new JLabel("Créer un examen");
			lblcreer.setBounds(57, 11, 213, 36);
			newExm_pane.add(lblcreer); 
			 
	//________________________________PANE DES RESULTATS_________________________________________________________	 
			/*histoPane = new JPanel();
			histoPane.setBackground(new Color(224, 255, 255));
			histoPane.setBorder(new EmptyBorder(5, 5, 5, 5)); 
			histoPane.setLayout(new FlowLayout());*/
			
			tabpane = new JPanel();
			tabpane.setBackground(new Color(224, 255, 255));
			tabpane.setPreferredSize(new Dimension(600,340));
			this.add(tabpane, BorderLayout.CENTER);
			tabpane.setLayout(null);
			tabpane.setVisible(false);
			
			//String[] th= {"nom","exam","note","date"};
			//Object[][] data= {{"said","java","16","2020-09-22"} };
			table = new JTable( );
			table.setFont(new Font("Calibri", Font.PLAIN, 14));
			table.setModel(new DefaultTableModel(
				new Object[][] { },
				new String[] {
					"n","Etudiant", "Examen", "Note", "Date"
				}
			));
			/*model=(DefaultTableModel) table.getModel();
			for (int i = 0; i < userGUI.notes.size(); i++) {
				model.addRow(new Object[]{i+1,userGUI.notes.get(i).titreExamen,userGUI.notes.get(i).note,userGUI.notes.get(i).dateExm});
			}*/
			//model.addColumn("col1");
			
			table.setGridColor(new Color(0, 191, 255));
			table.getTableHeader().setBackground(new Color(32, 178, 170));
			table.getTableHeader().setFont(new Font("Calibri", Font.BOLD, 14));
			table.getTableHeader().setForeground(Color.black);  
			table.getTableHeader().getColumnModel().getColumn(1).setMinWidth(110);
			table.getTableHeader().getColumnModel().getColumn(0).setMaxWidth(35);
			table.getTableHeader().getColumnModel().getColumn(3).setMaxWidth(45);
			table.setBounds(202, 133, 420, 280);
			JScrollPane scrollTab=new JScrollPane(table);
			scrollTab.getViewport().setBackground(new Color(224, 255, 255));
			scrollTab.setBounds(120, 57, 420, 280);
			tabpane.add(scrollTab);
			
			
			JLabel lblhead = new JLabel("Examen:"); 
			lblhead.setForeground(new Color(0, 0, 0));
			lblhead.setFont(new Font("Calibri", Font.BOLD, 15));
			lblhead.setBounds(120, 8, 100, 35);
			tabpane.add(lblhead);
			
			JComboBox comboExams=new JComboBox(); 
			comboExams.setBackground(new Color(127, 255, 212));
			comboExams.setBounds(190, 11, 85, 26); 
		//	comboExams.addItem("exam1");
			for (int i = 0; i < userGUI.prof.listExam.size(); i++) {
				comboExams.addItem(userGUI.prof.listExam.get(i).titre);
			}
			tabpane.add(comboExams);
			comboExams.setSelectedItem(null);
			
			comboExams.addItemListener(new ItemListener() { 
				@Override
				public void itemStateChanged(ItemEvent e) {
					exmChoisi=comboExams.getSelectedItem().toString();
					model=(DefaultTableModel) table.getModel();
					model.getDataVector().removeAllElements(); 
					try { //pour remplir formulaire (choix de matiere)
						userGUI.msg_out.writeUTF("notes");  
						System.out.println("demand envoyé");
						userGUI.msg_out.writeUTF(exmChoisi);
						userGUI.msg_out.flush();
						userGUI.notes= (ArrayList<Note>) userGUI.obj_In.readObject(); 
						for (int i = 0; i < userGUI.notes.size(); i++) {
							model.addRow(new Object[]{i+1,userGUI.notes.get(i).nomComplet,userGUI.notes.get(i).titreExamen,userGUI.notes.get(i).note,userGUI.notes.get(i).dateExm});
						}
						userGUI.revalidate();
						userGUI.repaint();
					}  catch (ClassNotFoundException e1) { System.out.println("prof couldnt get list of \'Notes\', error:"+e1.getMessage());}
					 catch (IOException e1) {System.out.println("prof couldnt get list of \'Notes\', error:"+e1.getMessage()); }
					
				}
			});
			
			/*JLabel lblhead = new JLabel("Les notes des examens");
			lblhead.setHorizontalAlignment(SwingConstants.CENTER);
			lblhead.setForeground(new Color(0, 128, 128));
			lblhead.setFont(new Font("Calibri", Font.BOLD, 18));
			lblhead.setBounds(149, 11, 302, 35);
			tabpane.add(lblhead);*/
			
			JLabel lblback = new JLabel("");
			lblback.setIcon(new ImageIcon("ressources\\back (4).png"));
			lblback.setBounds(0, 0, 25, 25);
			lblback.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					main.setVisible(true);
					tabpane.setVisible(false);
					/*userGUI.add(userGUI.accueilEtudiant, BorderLayout.CENTER);
					userGUI.revalidate();
					userGUI.repaint();*/
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