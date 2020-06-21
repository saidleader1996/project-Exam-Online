package Projet1;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.io.ObjectOutputStream;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox; 
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;

public class createExm_Pane extends JPanel { 
	JPanel container,form_pane;
	private JTextField nbQst_input;
	private JTextField titre_input; 
	qstPane pane_qst=null;  int nbQst; Examen nvexm;
	interfaceClient userGUI=null;
	public createExm_Pane(interfaceClient usergui) { 
		userGUI=usergui;
		this.setBounds(100, 100, 807, 425); 
		this.setBorder(new EmptyBorder(5, 5, 5, 5)); 
		this.setLayout(new FlowLayout());
		this.setBackground(new Color(224, 255, 255));
		
		container = new JPanel();
		container.setBackground(new Color(224, 255, 255));
		container.setPreferredSize(new Dimension( 807, 425));
		this.add(container, BorderLayout.CENTER);
		container.setLayout(null);
		
		form_pane = new JPanel();
		form_pane.setBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(175, 238, 238), new Color(32, 178, 170)));
		form_pane.setBackground(new Color(173, 216, 230));
		form_pane.setBounds(160, 11, 450, 343); 
		container.add(form_pane);
		form_pane.setVisible(true);
		form_pane.setLayout(null);
		
		JLabel lblClose = new JLabel("");
		lblClose.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				 
				userGUI.remove(createExm_Pane.this ); 
				userGUI.add(userGUI.accueilprof,"Center"); 
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
		lblClose.setIcon(new ImageIcon("ressources\\\\ferme.png")); 
		lblClose.setBounds(422, 2, 25, 26);
		form_pane.add(lblClose);
		
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
		
		for (int i = 0; i < userGUI.accueilprof.matieres.size(); i++) {
			combo_matiere.addItem(userGUI.accueilprof.matieres.get(i).nom_matiere);
		} 
		JButton creerBtn = new JButton("Cr\u00E9er examen");
		creerBtn.setBackground(new Color(60, 179, 113));
		creerBtn.setBounds(129, 263, 200, 34);
		form_pane.add(creerBtn);
		creerBtn.addActionListener(new ActionListener() { 
			@Override
			public void actionPerformed(ActionEvent e) {  
				if(  nbQst_input.getText().trim().length()>0 && titre_input.getText().trim().length()>0 ) {
					 String titre=titre_input.getText(); 
					 nbQst=Integer.valueOf( nbQst_input.getText() ) ;
					 String filiere= userGUI.accueilprof.matieres.get(combo_matiere.getSelectedIndex()).filiere ;
					 String matiere=combo_matiere.getSelectedItem().toString();
				     nvexm=new Examen(titre, userGUI.prof, nbQst, filiere, matiere);
					 
					/* try {
						userGUI.msg_out.writeUTF("examen");
						userGUI.obj_Out=new ObjectOutputStream(userGUI.msg_out);
						userGUI.obj_Out.writeObject(nvexm);
					} catch (IOException e1) { e1.printStackTrace(); }
					 */
					pane_qst=new qstPane(userGUI);
					form_pane.setVisible(false);
					userGUI.accueilprof.nvExmPane.container.remove(container); 
					userGUI.accueilprof.nvExmPane.container.add(pane_qst);
					userGUI.revalidate();
					userGUI.repaint(); 
				}else JOptionPane.showMessageDialog(null, "veuilez saisir les champs demandés!");
			}
		} );
		
		
	//_____________________________ajouter questions_______________________________________________
		
		
		
		
	}

}
