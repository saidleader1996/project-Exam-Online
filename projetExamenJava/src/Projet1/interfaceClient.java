package Projet1; 
import java.awt.BorderLayout;
import java.awt.EventQueue; 
import javax.swing.JFrame;
import javax.swing.JPanel; 
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.FlowLayout; 
import java.awt.Font;
import javax.swing.ImageIcon;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;

import javax.swing.SwingConstants;
import javax.swing.border.BevelBorder;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JPasswordField; 
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class interfaceClient extends JFrame {
	JPanel  main,panelProf,panelEtud,loginEtud,loginProf;
	private JTextField CNEinput;
	private JPasswordField passwordField; 
	boolean loged=false;
	Etudiant etudiant=new Etudiant(); 
	Professeur prof=new Professeur();
	ArrayList<Examen> exams= new ArrayList<Examen>();
	ArrayList<Note> notes=new ArrayList<Note>();
	int exmChoisie;
	Socket client=null; 
	DataOutputStream msg_out;
	DataInputStream msg_In;
	ObjectInputStream obj_In; 
	ObjectOutputStream obj_Out; 
	exam_pane exm_pane=new exam_pane(this);
	AccueilEtudiant accueilEtudiant=null;
	accueilProf accueilprof=null;
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					interfaceClient frame = new interfaceClient();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	} 
	   public interfaceClient() {
		   try {
				client=new Socket("127.0.0.1",6667);
				System.out.println("client connecté");
				msg_out=new DataOutputStream(client.getOutputStream()); 
				msg_In=new DataInputStream(client.getInputStream());  
			}catch (IOException e1) { JOptionPane.showMessageDialog(null,"Client couldn't connect to the server, error:\n"+e1.getMessage());System.exit(0);  }
			  
		   drawGUI();
		}
	   public void drawGUI() {

			getContentPane().setBackground(new Color(224, 255, 255));
			getContentPane().setLayout(new BorderLayout(0, 0));
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			setBounds(100, 100, 850, 505);
			
			main = new JPanel();
			FlowLayout flowLayout_1 = (FlowLayout) main.getLayout();
			flowLayout_1.setHgap(100);
			main.setBackground(new Color(224, 255, 255));   
			accueilProf accprof=new accueilProf(this);
			getContentPane().add(main, BorderLayout.CENTER);
			
		////_____________HEADER__________________________
			JPanel head = new JPanel();
			FlowLayout flowLayout = (FlowLayout) head.getLayout();
			flowLayout.setHgap(0);
			flowLayout.setVgap(0);
			head.setPreferredSize(new Dimension(400,60));
			head.setBackground(new Color(173, 216, 230));
			getContentPane().add(head, BorderLayout.NORTH);
			
			JLabel iconHead = new JLabel("");
			iconHead.setPreferredSize(new Dimension(70,60));
			iconHead.setIcon(new ImageIcon("ressources/school (4).png"));
			//iconHead.setPreferredSize(new Dimension(64,64));
			head.add(iconHead);
			
			JLabel lblHead = new JLabel("GESTION EXAMEN"); 
			lblHead.setVerticalAlignment(SwingConstants.BOTTOM);
			lblHead.setHorizontalAlignment(SwingConstants.CENTER);
			lblHead.setPreferredSize(new Dimension(200,45)); 
			lblHead.setFont(new Font("Calibri", Font.PLAIN, 27));
			head.add(lblHead);
		
			
		////_____________STUDENT ICON__________________________
			panelEtud = new JPanel();
			panelEtud.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					//JOptionPane.showMessageDialog(null, "etudiant");
					loginProf.setVisible(false);
					loginEtud.setVisible(true);
					main.remove(panelEtud); 
					main.remove(panelProf); 
					main.repaint();
					panelEtud.setBackground(new Color(224, 255, 255));
					interfaceClient.this.setCursor(Cursor.DEFAULT_CURSOR);
				}
				@Override
				public void mouseEntered(MouseEvent e) {
					panelEtud.setBackground(new Color(102, 205, 170)); 
					interfaceClient.this.setCursor(Cursor.HAND_CURSOR);
				}
				@Override
				public void mouseExited(MouseEvent e) {
					panelEtud.setBackground(new Color(224, 255, 255)); 
					interfaceClient.this.setCursor(Cursor.DEFAULT_CURSOR);
				}
			});
			
			JPanel pane_header = new JPanel();
			pane_header.setPreferredSize(new Dimension(950,60));
			pane_header.setBackground(new Color(224, 255, 255));
			main.add(pane_header);
			pane_header.setLayout(null);
			
			JLabel lblNewLabel_2 = new JLabel("Connectez-vous et gérez tes examens facilement");
			lblNewLabel_2.setForeground(new Color(51, 102, 0));
			lblNewLabel_2.setFont(new Font("Cambria", Font.BOLD | Font.ITALIC, 18));
			lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
			lblNewLabel_2.setBounds(266, 11, 417, 38);
			pane_header.add(lblNewLabel_2);
			
			panelEtud.setBackground(new Color(224, 255, 255));
			panelEtud.setPreferredSize(new Dimension(150,160));
			main.add(panelEtud);
			panelEtud.setLayout(null);
			
			JLabel lblNewLabel = new JLabel("icon etudiant");
			lblNewLabel.setIcon(new ImageIcon("ressources/student (1).png"));
			lblNewLabel.setBounds(10, 4, 130, 129);
			panelEtud.add(lblNewLabel);
			
			JLabel lblNewLabel_1 = new JLabel("Etudiant");
			lblNewLabel_1.setFont(new Font("Calibri", Font.BOLD, 18));
			lblNewLabel_1.setBounds(38, 134, 73, 25);
			panelEtud.add(lblNewLabel_1);
			
		////_____________TEACHER ICON__________________________
			
			panelProf = new JPanel();
			panelProf.setLayout(null);
			panelProf.setPreferredSize(new Dimension(150, 160));
			panelProf.setBackground(new Color(224, 255, 255)); 
		
			panelProf.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					//JOptionPane.showMessageDialog(null, "prof");
					loginEtud.setVisible(false);
					loginProf.setVisible(true); 
					main.remove(panelEtud); 
					main.remove(panelProf); 
					main.repaint();
					panelProf.setBackground(new Color(224, 255, 255));  
					interfaceClient.this.setCursor(Cursor.DEFAULT_CURSOR);
				}
				@Override
				public void mouseEntered(MouseEvent e) {
					panelProf.setBackground(new Color(102, 205, 170));
					interfaceClient.this.setCursor(Cursor.HAND_CURSOR);
				}
				@Override
				public void mouseExited(MouseEvent e) {
					panelProf.setBackground(new Color(224, 255, 255)); 
					interfaceClient.this.setCursor(Cursor.DEFAULT_CURSOR);
				}
			}); 
			main.add(panelProf);
			 
			JLabel lblProf = new JLabel("icon prof");
			lblProf.setIcon(new ImageIcon("ressources/teacher (1).png"));
			lblProf.setBounds(10, 4, 128, 128);
			panelProf.add(lblProf);
			
			JLabel lblProfesseur = new JLabel("Professeur");
			lblProfesseur.setFont(new Font("Calibri", Font.BOLD, 18));
			lblProfesseur.setBounds(38, 134, 88, 25);
			panelProf.add(lblProfesseur);
	
	
			JPanel south = new JPanel();
			south.setBackground(new Color(224, 255, 255));
			south.setPreferredSize(new Dimension(770,380));
			main.add(south);
			south.setLayout(null);
					
					
		////_____________LOGIN TEACHER__________________________
	
			loginProf = new JPanel();
			loginProf.setBackground(new Color(102, 205, 170));
			loginProf.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, new Color(0, 139, 139)));
			loginProf.setBounds(185,0, 400, 308);
			loginProf.setVisible(false);
			south.add(loginProf);
			loginProf.setLayout(null);   
					
			JLabel lblogin2 = new JLabel("LOGIN");
			lblogin2.setHorizontalAlignment(SwingConstants.CENTER);
			lblogin2.setFont(new Font("Calibri", Font.BOLD, 30));
			lblogin2.setBounds(89, 11, 222, 105);
			loginProf.add(lblogin2); 
					
			JLabel lblclose2 = new JLabel("");
			lblclose2.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					main.remove(south); 
					main.add(panelEtud);
					main.add(panelProf); 
					loginEtud.setVisible(false);
					loginProf.setVisible(false); 
					main.add(south); 
					main.repaint();
					interfaceClient.this.revalidate();
					interfaceClient.this.repaint();  
					interfaceClient.this.setCursor(Cursor.DEFAULT_CURSOR);
				}
				@Override
				public void mouseEntered(MouseEvent e) {
					interfaceClient.this.setCursor(Cursor.HAND_CURSOR);
				}
				@Override
				public void mouseExited(MouseEvent e) {
					interfaceClient.this.setCursor(Cursor.DEFAULT_CURSOR);
					}
			});
			lblclose2.setIcon(new ImageIcon("ressources\\ferme.png"));
			lblclose2.setBounds(375, 0, 24, 28); 
			loginProf.add(lblclose2);
			
			JLabel errorMsg2 = new JLabel("Se connecter \u00E0 votre compte");
			errorMsg2.setFont(new Font("Calibri", Font.PLAIN, 14));
			errorMsg2.setHorizontalAlignment(SwingConstants.CENTER);
			errorMsg2.setBounds(34, 111, 310, 30);
			loginProf.add(errorMsg2);
					
			JLabel lblId = new JLabel("CIN:");
			lblId.setBounds(89, 152, 69, 23);
			loginProf.add(lblId);
					
			JTextField profIdInput  = new JTextField("prof1");
			profIdInput.setColumns(10);
			profIdInput.setBounds(89, 173, 222, 25);
			loginProf.add(profIdInput);
			
			JPasswordField mdpProf = new JPasswordField("sss");
			mdpProf.setBounds(88, 231, 223, 25);
			loginProf.add(mdpProf );
			errorMsg2.setBounds(34, 111, 332, 30);
			loginProf.add(errorMsg2);
					
			JButton buttonProf = new JButton("Connexion");
			buttonProf.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if(!loged) { //le cas de deconnection 
						  try {
								client=new Socket("127.0.0.1",6667);
								System.out.println("client connecté");
								msg_out=new DataOutputStream(client.getOutputStream()); 
								msg_In=new DataInputStream(client.getInputStream());  
							}catch (IOException e1) { JOptionPane.showMessageDialog(null,"Client couldn't connect to the server, error:\n"+e1.getMessage());System.exit(0);  }
					 }
					
					if(profIdInput.getText().trim().length()>0 && mdpProf.getText().trim().length()>0 ) {
						String CIN=profIdInput.getText();  
						 try {  
							 msg_out.writeUTF("prof");
								 msg_out.writeUTF("CIN");
								 msg_out.writeUTF(CIN); 
									msg_out.flush();
								 String rep= msg_In.readUTF();
								if(rep.equals("found") ) { 
									loged=true;
									obj_In=new ObjectInputStream(msg_In); 
									prof=(Professeur) obj_In.readObject();
									//System.out.println(prof.toString());
									//exams= (ArrayList<Examen>) obj_In.readObject();
									//notes= (ArrayList<Note>) obj_In.readObject();    
									interfaceClient.this.remove(main);  
									accueilprof=new accueilProf(interfaceClient.this);
									getContentPane().add(accueilprof, BorderLayout.CENTER);
									interfaceClient.this.revalidate();
									interfaceClient.this.repaint();  
									errorMsg2.setText("Se connecter à votre compte");errorMsg2.setForeground(Color.black);mdpProf.setText("");
								}else if( rep.equals("not found") ) { 
									errorMsg2.setText("CIN ou mot de passe est incorrect");
									errorMsg2.setForeground(Color.red);
									//	JOptionPane.showMessageDialog(null, "votre nom n'existe pas dans la liste !");
								}	 
						 }catch(Exception e1) { System.out.println("error in login :"+e1.getMessage()); }
					}else {
						errorMsg2.setText("veuillez remplir les deuxs champs demandés");
						errorMsg2.setForeground(Color.red);
					}

				}
			});
			buttonProf.setForeground(new Color(255, 255, 255));
			buttonProf.setBackground(new Color(0, 128, 128));
			buttonProf.setFont(new Font("Calibri", Font.BOLD, 14));
			buttonProf.setBounds(89, 267, 222, 30);
			loginProf.add(buttonProf); 
					
			JLabel lblmdp = new JLabel("Mot de pass:");
			lblmdp.setBounds(89, 210, 79, 25);
			loginProf.add(lblmdp); 
							 
		////_____________________LOGIN STUDENT____________________________	
			loginEtud = new JPanel();
			loginEtud.setBackground(new Color(102, 205, 170));
			loginEtud.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, new Color(0, 139, 139)));
			loginEtud.setBounds(185,0, 400, 308);
			loginEtud.setVisible(false);
			south.add(loginEtud);
			loginEtud.setLayout(null);
							
			
			JLabel errorMsg1 = new JLabel("Se connecter \u00E0 votre compte");
			errorMsg1.setFont(new Font("Calibri", Font.PLAIN, 14));
			errorMsg1.setHorizontalAlignment(SwingConstants.CENTER);
			errorMsg1.setBounds(34, 111, 332, 30);
			loginEtud.add(errorMsg1);
			
			JLabel lblCne = new JLabel("CNE:");
			lblCne.setBounds(89, 152, 69, 23);
			loginEtud.add(lblCne);
			
			CNEinput = new JTextField("N139300001");
			CNEinput.setColumns(10);
			CNEinput.setBounds(89, 173, 222, 25);
			loginEtud.add(CNEinput); 
			
			JLabel lblCin = new JLabel("Mot de pass:");
			lblCin.setBounds(89, 210, 79, 25);
			loginEtud.add(lblCin);
			
			passwordField = new JPasswordField("123");
			passwordField.setBounds(88, 231, 223, 25);
			loginEtud.add(passwordField);
			
			JButton buttonEtud = new JButton("Connexion"); 
			buttonEtud.setForeground(new Color(255, 255, 255));
			buttonEtud.setBackground(new Color(0, 128, 128));
			buttonEtud.setFont(new Font("Calibri", Font.BOLD, 14));
			buttonEtud.setBounds(89, 267, 222, 30); 
			loginEtud.add(buttonEtud);
					
			JLabel lblclose = new JLabel("");
			lblclose.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					main.remove(south); 
					main.add(panelEtud);
					main.add(panelProf); 
						
					loginEtud.setVisible(false);
					loginProf.setVisible(false); 
					main.add(south);
					main.repaint();
					interfaceClient.this.revalidate();
					interfaceClient.this.repaint();  
					interfaceClient.this.setCursor(Cursor.DEFAULT_CURSOR);
							 
				}
				@Override
				public void mouseEntered(MouseEvent e) { 
					interfaceClient.this.setCursor(Cursor.HAND_CURSOR);
				}
				@Override
				public void mouseExited(MouseEvent e) { 
					interfaceClient.this.setCursor(Cursor.DEFAULT_CURSOR);
				} 
			});
			lblclose.setIcon(new ImageIcon("ressources\\ferme.png"));
			lblclose.setBounds(373, 3, 25, 26);
			loginEtud.add(lblclose);
				
			JLabel lblogin1 = new JLabel("LOGIN");
			lblogin1.setBackground(new Color(0, 128, 128));
			lblogin1.setHorizontalAlignment(SwingConstants.CENTER);
			lblogin1.setFont(new Font("Calibri", Font.BOLD, 30));
			lblogin1.setBounds(89, 11, 222, 105);
			loginEtud.add(lblogin1); 
			
			buttonEtud.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if(!loged) { //le cas de deconnection 
						  try {
								client=new Socket("127.0.0.1",6667);
								System.out.println("client connecté");
								msg_out=new DataOutputStream(client.getOutputStream()); 
								msg_In=new DataInputStream(client.getInputStream());  
							}catch (IOException e1) { JOptionPane.showMessageDialog(null,"Client couldn't connect to the server, error:\n"+e1.getMessage());System.exit(0);  }
					 }
					
					if(CNEinput.getText().trim().length()>0 && passwordField.getText().trim().length()>0 ) {
						String CNE=CNEinput.getText();   
						 try {  
							 msg_out.writeUTF("etudiant");
								 msg_out.writeUTF("CNE");
								 msg_out.writeUTF(CNE); 
									
								 String rep= msg_In.readUTF();
								if(rep.equals("found") ) {
									loged=true;
									obj_In=new ObjectInputStream(msg_In); 
									etudiant=(Etudiant) obj_In.readObject();
									exams= (ArrayList<Examen>) obj_In.readObject();
									notes= (ArrayList<Note>) obj_In.readObject();    
									interfaceClient.this.remove(main);  
								 	accueilEtudiant=new AccueilEtudiant(interfaceClient.this);
									getContentPane().add(accueilEtudiant, BorderLayout.CENTER);
									interfaceClient.this.revalidate();
									interfaceClient.this.repaint();  
									errorMsg1.setText("Se connecter à votre compte");passwordField.setForeground(Color.black);mdpProf.setText("");
								}else if( rep.equals("not found") ) { 
									errorMsg1.setText("CNE ou mot de passe est incorrect");
									errorMsg1.setForeground(Color.red);
									//	JOptionPane.showMessageDialog(null, "votre nom n'existe pas dans la liste !");
								}	 
						 }catch(Exception e1) { System.out.println("error in login :"+e1.getMessage()); }
					}else {
						errorMsg1.setText("veuillez remplir les deuxs champs demandés");
						errorMsg1.setForeground(Color.red);
					}
				}
			}); 
	   }
}



















































