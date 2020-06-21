package Projet1; 
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.io.File;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser; 
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.UIManager;  
import javax.swing.border.EtchedBorder;
import javax.swing.filechooser.FileNameExtensionFilter; 
import javax.swing.JTextArea;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionEvent;

public class qstPane extends JPanel {  
	private JTextField inputChoix1;
	private JTextField inputChoix2;
	private JTextField inputChoix3;
	private JTextField inputChoix4;
	JFileChooser file; int rs;
	int counter=1;
	ArrayList<Question> questions=new ArrayList<Question>();
	interfaceClient userGUI;
	public qstPane(interfaceClient usergui) { 
		userGUI=usergui;
		this.setBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(175, 238, 238), new Color(32, 178, 170)));
		this.setBackground(new Color(173, 216, 230));
		this.setBounds(160, 11, 450, 343); ;
		this.setLayout(null);
		
		JLabel lblClose = new JLabel("");
		lblClose.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				 
				userGUI.remove( userGUI.accueilprof.nvExmPane ); 
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
		this.add(lblClose);
		
		
		JLabel lblAjouterQuestions = new JLabel("ajouter Questions de l'examen");
		lblAjouterQuestions.setForeground(new Color(47, 79, 79));
		lblAjouterQuestions.setFont(new Font("Arial", Font.BOLD, 16));
		lblAjouterQuestions.setBounds(97, 11, 256, 24);
		this.add(lblAjouterQuestions);
		
		JLabel lblnbQst = new JLabel("Question : 1/"+userGUI.accueilprof.nvExmPane.nvexm.nbQuestion);
		lblnbQst.setFont(new Font("Arial", Font.BOLD, 12));
		lblnbQst.setHorizontalAlignment(SwingConstants.CENTER);
		lblnbQst.setBounds(172, 35, 107, 24);
		this.add(lblnbQst); 
		
		JLabel lbltitre = new JLabel("Enonc\u00E9");
		lbltitre.setFont(new Font("Arial", Font.BOLD, 12));
		lbltitre.setBounds(63, 66, 107, 24);
		this.add(lbltitre); 
		
		JTextArea textArea = new JTextArea(3,5); 
		textArea.setBounds(129, 67, 263, 34);
		this.add(textArea);
		textArea.setRows(3);
		textArea.setWrapStyleWord(true);
		textArea.setLineWrap(true); 
		JScrollPane scroll=new JScrollPane(textArea,JScrollPane.VERTICAL_SCROLLBAR_NEVER,JScrollPane.HORIZONTAL_SCROLLBAR_NEVER); 
		scroll.setBounds(129, 66, 265, 48);
		this.add(scroll);
		
		JLabel lblchoix1 = new JLabel("choix1");
		lblchoix1.setFont(new Font("Arial", Font.BOLD, 12));
		lblchoix1.setBounds(63, 125, 64, 24);
		this.add(lblchoix1);
		
		inputChoix1 = new JTextField();
		inputChoix1.setColumns(10);
		inputChoix1.setBounds(129, 126, 265, 24);
		this.add(inputChoix1);
		
		inputChoix2 = new JTextField();
		inputChoix2.setColumns(10);
		inputChoix2.setBounds(129, 156, 265, 24);
		this.add(inputChoix2);
		
		JLabel lblChoix2 = new JLabel("choix2");
		lblChoix2.setFont(new Font("Arial", Font.BOLD, 12));
		lblChoix2.setBounds(63, 159, 64, 24);
		this.add(lblChoix2);
		
		JLabel lblChoix3 = new JLabel("choix3");
		lblChoix3.setFont(new Font("Arial", Font.BOLD, 12));
		lblChoix3.setBounds(63, 189, 64, 24);
		this.add(lblChoix3);
		
		inputChoix3 = new JTextField();
		inputChoix3.setColumns(10);
		inputChoix3.setBounds(129, 188, 265, 24);
		this.add(inputChoix3);
		
		JLabel lblChoix4 = new JLabel("choix4");
		lblChoix4.setFont(new Font("Arial", Font.BOLD, 12));
		lblChoix4.setBounds(63, 220, 64, 24);
		this.add(lblChoix4);
		
		inputChoix4 = new JTextField();
		inputChoix4.setColumns(10);
		inputChoix4.setBounds(129, 221, 265, 24);
		this.add(inputChoix4);
		
		String[] choix= {"choix1","choix2","choix3","choix4"};
		JComboBox combo_sol = new JComboBox(choix);
		combo_sol.setBackground(new Color(0, 255, 127));
		combo_sol.setBounds(129, 252, 175, 24);
		this.add(combo_sol);
		
		JLabel lblsol = new JLabel("Solution");
		lblsol.setFont(new Font("Arial", Font.BOLD, 12));
		lblsol.setBounds(63, 252, 64, 24);
		this.add(lblsol);
		
		JLabel lblimage = new JLabel("image");
		lblimage.setFont(new Font("Arial", Font.BOLD, 12));
		lblimage.setBounds(63, 282, 64, 24);
		this.add(lblimage);
		
		JLabel lblimgName = new JLabel("");
		lblimgName.setBounds(237, 285, 132, 14);
		this.add(lblimgName);
		
		JButton bupload = new JButton("choisir...");
		bupload.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) { 
				 try { //jfileChooser with windows explorer luncher
						UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
					} catch (Exception e1) { e1.printStackTrace(); 	} 
				 
				 file=new JFileChooser("F:\\user\\Documents"); 
				 file.setBackground(Color.cyan);
				 FileNameExtensionFilter imgfilter=new FileNameExtensionFilter("Images Files",ImageIO.getReaderFileSuffixes());
				 file.setFileFilter(imgfilter);
				 rs=file.showOpenDialog(null); 
				// System.out.println(file.getSelectedFile().toPath()    +"   "+file.getSelectedFile().getName().endsWith(".png"));
				 if( file.getSelectedFile().getName().endsWith(".png") || file.getSelectedFile().getName().endsWith(".jpg")  ) {
					 System.out.println("accepté"); 
					 lblimgName.setText( file.getSelectedFile().getName() );
					 rs=1;
				 }else
					 JOptionPane.showMessageDialog(null, "seuls les images sont acceptées"); 
			}
		});
		bupload.setBackground(new Color(192, 192, 192));
		bupload.setBounds(139, 282, 89, 23);
		this.add(bupload); 
		
		JButton creerBtn = new JButton("ajouter question"); 
		
		creerBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			  if(textArea.getText().trim().length()>0 && inputChoix1.getText().trim().length()>0 && inputChoix2.getText().trim().length()>0 && inputChoix3.getText().trim().length()>0 &&inputChoix4.getText().trim().length()>0 ){
				 Question qst=new Question();	
				try {  
					     qst.ennonce=textArea.getText();
						 qst.choix[0]=inputChoix1.getText();
						 qst.choix[1]=inputChoix2.getText();
						 qst.choix[2]=inputChoix3.getText();
						 qst.choix[3]=inputChoix4.getText();
						 
						 if(combo_sol.getSelectedIndex()==0) qst.solBinaire= ("1000").toCharArray();
						 if(combo_sol.getSelectedIndex()==1) qst.solBinaire= ("0100").toCharArray();
						 if(combo_sol.getSelectedIndex()==2) qst.solBinaire= ("0010").toCharArray();
						 if(combo_sol.getSelectedIndex()==3) qst.solBinaire= ("0001").toCharArray();   
						 if(rs==1)  qst.imgBin= Files.readAllBytes(file.getSelectedFile().toPath() );   
						 else qst.imgBin= Files.readAllBytes( new File("ressources\\vide.png").toPath() ); 
						 questions.add(qst);
						 if( counter==userGUI.accueilprof.nvExmPane.nbQst) {//send data to server to insert it in db
							 try {
									userGUI.msg_out.writeUTF("examen");
									userGUI.obj_Out=new ObjectOutputStream(userGUI.msg_out);
									userGUI.obj_Out.writeObject(userGUI.accueilprof.nvExmPane.nvexm); 
									userGUI.obj_Out.writeObject(questions); 
									userGUI.msg_out.flush();
								} catch (IOException e1) { e1.printStackTrace(); }
							 
							 JOptionPane.showMessageDialog(null, "examen inseré avec succes");
							 userGUI.accueilprof.nvExmPane.setVisible(false);
							 userGUI.accueilprof.remove(userGUI.accueilprof.nvExmPane);
							 userGUI.add(userGUI.accueilprof );
							 userGUI.accueilprof.revalidate();
							 userGUI.accueilprof.repaint(); 
						 }
						 counter++;
						 lblnbQst.setText("Question : "+counter+"/"+userGUI.accueilprof.nvExmPane.nvexm.nbQuestion);
						 combo_sol.setSelectedIndex(0);
						 inputChoix1.setText(""); inputChoix2.setText(""); inputChoix3.setText(""); inputChoix4.setText("");
						 textArea.setText(""); lblimgName.setText("");
				}catch (IOException e1) { e1.printStackTrace(); }
			  }else JOptionPane.showMessageDialog(null, "veuillez remplir tous les champs");
				
			}
		});
		creerBtn.setBackground(new Color(60, 179, 113));
		creerBtn.setBounds(125, 310, 200, 24);
		this.add(creerBtn); 
		
	}
}
