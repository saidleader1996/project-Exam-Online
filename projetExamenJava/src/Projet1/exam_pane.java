package Projet1;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension; 
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox; 
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;

public class exam_pane extends JPanel  { 
	JPanel contentPanel, center;
	CardLayout card;
	JButton valider;
	JTextArea txtarea; 
	JScrollPane imgScroll;
	JLabel img, lblScore , lblQuestion;
	JCheckBox box1,box2,box3,box4; 
	public String fullName,exm;
	public boolean isLoged=false;
	public int nbQst;
	char[] choix= {'0','0','0','0'};  
	Question qst;
	String score; 
	ObjectInputStream obj_In;
	interfaceClient userGUI=null;
	boolean running=false;
	public static void main(String[] args) {
		
	}
	public exam_pane(interfaceClient userGui) {  
		userGUI=userGui; 
		drawGUI();     
	} 
	public void newthread() {//deroulement d'examen
		Thread examthread=new Thread() {
			 public void run() {  
					start_qcm();  
				 }; 
			};
			examthread.start();	
	}
	public void start_qcm() { 
			int counter=1; 
			nbQst=userGUI.exams.get(userGUI.exmChoisie).nbQuestion;
			while(running ) { 
				try { 
						 
						String servermsg=userGUI.msg_In.readUTF(); 
						if( servermsg.equals("next_question") ) { 
							  txtarea.setText("");
							  box1.setText("");box2.setText("");box3.setText("");box4.setText("");
							  box1.setSelected(false);box2.setSelected(false);box3.setSelected(false);box4.setSelected(false); 
							 
							 
							   try {
								ObjectInputStream obj_In=new ObjectInputStream(userGUI.msg_In);
								qst=(Question) obj_In.readObject();//read question object 
								
							   }catch (ClassNotFoundException e) { System.out.println("qstObject hasn't been receved, error:\n"+e.getMessage());}
							   txtarea.insert(qst.ennonce,0); 
							   box1.setText(qst.choix[0]);
							   box2.setText(qst.choix[1]);
							   box3.setText(qst.choix[2]);
							   box4.setText(qst.choix[3]); 
							   if( qst.imgBin.length==1) { //if there is no image hide img panel
								   imgScroll.setVisible(false); 
							   }
							   else {
								   img.setIcon(new ImageIcon(qst.imgBin) );  
								   imgScroll.setVisible(true);
							   }
						} else if ( servermsg.equals("score") ) {
							score=userGUI.msg_In.readUTF();
							lblScore.setText("Score: "+score);
							counter++;
							if(counter<=nbQst)
								lblQuestion.setText("Question: "+String.valueOf(counter)+"/"+nbQst);  
						 } else if( servermsg.equals("test finished") ) {
							JOptionPane.showMessageDialog(null, "Congratulation! the test is finished\n             Your Score:"+score+"/"+nbQst);
						 
							try { //lire la nv table des notes 
								ObjectInputStream obj_In=new ObjectInputStream(userGUI.msg_In);
								userGUI.notes= (ArrayList<Note>) obj_In.readObject(); 
							} catch (ClassNotFoundException e) { System.out.println("exam_pane: cant read \'Notes\'");}
							userGUI.accueilEtudiant.model.getDataVector().removeAllElements(); //actualiser la table des notes
							for (int i = 0; i < userGUI.notes.size(); i++) {
								userGUI.accueilEtudiant.model.addRow(new Object[]{i+1,userGUI.notes.get(i).titreExamen,userGUI.notes.get(i).note,userGUI.notes.get(i).dateExm});
							}
							//System.exit(1); 
							//getting ready for the next exam
							counter=0; score="0";running=false;
							lblQuestion.setText("Question: 1/"+nbQst);  
							lblScore.setText("Score: "+score); 
							userGUI.remove(userGUI.exm_pane);
							userGUI.add(userGUI.accueilEtudiant, BorderLayout.CENTER); //go back home 
							userGUI.accueilEtudiant.panel_start.setVisible(true);
							userGUI.accueilEtudiant.panel_notes.setVisible(true); 
							userGUI.accueilEtudiant.pane_deconx.setVisible(true);
							userGUI.accueilEtudiant.panelStartExm.setVisible(false);
							userGUI.revalidate();
							userGUI.repaint();    
						  } 
					} catch (IOException e1) {System.out.println("connection error :client can't connect to the server, error:"+e1.getMessage()); running=false;}  
			 }	  	
	 } 	 
public void drawGUI() {
				this.setBorder(new EmptyBorder(5, 5, 5, 5));
				this.setLayout(new BorderLayout(0, 0));  
				//this.setBounds(0, 0, 850, 500);
				JPanel north = new JPanel();
				north.setBackground(new Color(102, 204, 204));
				north.setPreferredSize(new Dimension(800,40));
				this.add(north, BorderLayout.NORTH);
				north.setLayout(new GridLayout(1, 2, 0, 0));

				lblQuestion = new JLabel("Question: 1"); 
				north.add(lblQuestion);
				
				lblScore = new JLabel("Score: 0");
				north.add(lblScore); 
				
				center = new JPanel();
				center.setLayout(null);
				center.setBackground(new Color(176, 224, 230)); 
				
				txtarea = new JTextArea();
			//	txtarea.setColumns(3);
				txtarea.setEditable(false);
				txtarea.setLineWrap(true);
				txtarea.setText("Loading...");
				txtarea.setWrapStyleWord(true);
				txtarea.setBackground(new Color(135, 206, 235));
				txtarea.setForeground(Color.black);
			    //txtarea.setBounds(10, 11, 455, 80);
			    txtarea.setLocation(10, 11);
				JScrollPane scrolarea=new JScrollPane(txtarea);
				scrolarea.setBounds(10, 11, 455, 80); 
			    center.add(scrolarea); 
				
				box1 = new JCheckBox("Loading..."); 
				box1.setBackground(new Color(135, 206, 250));
				box1.setForeground(Color.black);
				box1.setBounds(20, 100, 433, 45);
				box1.addActionListener(new ActionListener() { 
					@Override
					public void actionPerformed(ActionEvent e) { 
						if(box1.isSelected()) choix[0]='1';
						if(!box1.isSelected()) choix[0]='0';
					//	System.out.println("choix1:"+choix[0]);
					}
				});
				center.add(box1);
				
				box2 = new JCheckBox("Loading...");
				box2.setBackground(new Color(135, 206, 250));
				box2.setForeground(Color.black);
				box2.setBounds(20, 155, 433, 45);
				box2.addActionListener(new ActionListener() { 
					@Override
					public void actionPerformed(ActionEvent e) { 
						if(box2.isSelected()) choix[1]='1';
						if(!box2.isSelected()) choix[1]='0';
						//System.out.println("choix2:"+choix[1]);
					}
				});
				center.add(box2);

				box3 = new JCheckBox("Loading...");
				box3.setBackground(new Color(135, 206, 250));
				box3.setForeground(Color.black);
				box3.setBounds(20, 210, 433, 45);
				box3.addActionListener(new ActionListener() { 
					@Override
					public void actionPerformed(ActionEvent e) { 
						if(box3.isSelected()) choix[2]='1';
						if(!box3.isSelected()) choix[2]='0';
						//System.out.println("choix3:"+choix[2]);
					}
				});
				center.add(box3);  
				box4 = new JCheckBox("Loading...");
				box4.setBackground(new Color(135, 206, 250));
				box4.setForeground(Color.black);
				box4.setBounds(20, 265, 433, 45); 
				box4.addActionListener(new ActionListener() { 
					@Override
					public void actionPerformed(ActionEvent e) { 
						if(box4.isSelected()) choix[3]='1';
						if(!box4.isSelected()) choix[3]='0'; 
					}
				}); 
				center.add(box4); 
				  
				img=new JLabel(); 
				img.setBounds(480, 10, 300, 300); 
				
				imgScroll=new JScrollPane(img);
				imgScroll.setBounds(470, 10, 350, 300 );
				imgScroll.setBackground( new Color(176, 224, 230)); 
				imgScroll.getViewport().setBackground(Color.white);
				center.add(imgScroll); 
				
				this.add(center,"Center");
				
				JPanel south = new JPanel();
				this.add(south, BorderLayout.SOUTH);
				
				valider = new JButton("Valider");
				valider.setForeground(new Color(240, 255, 240));
				valider.setFont(new Font("Calibri", Font.BOLD, 16));
				valider.setBackground(new Color(0, 128, 128));
				valider.setBounds(152, 6, 173, 37);
				south.add(valider);
				valider.addActionListener(new ActionListener() { 
					@Override
					public void actionPerformed(ActionEvent e) {  
						 try {
							 userGUI.msg_out=new DataOutputStream(userGUI.client.getOutputStream());
							 userGUI.msg_out.writeUTF("validation"); 
							 userGUI.msg_out.writeUTF(String.copyValueOf(choix)); 
							 userGUI.msg_out.flush();
							
						 }catch (IOException e1) { System.out.println("validation error: "+e1.getMessage());} 
						choix[0]='0';choix[1]='0';choix[2]='0';choix[3]='0'; 
					 }
				});	 
		 
				//________________________hover Effect
				 class MyMouseAdapter extends MouseAdapter{
					 JCheckBox checkBox;
					 public MyMouseAdapter(JCheckBox box) {
						this.checkBox=box;
					 }
					 @Override
					 public void mouseEntered(MouseEvent e) { 
						super.mouseEntered(e);
						checkBox.setBackground(new Color(47, 79, 79));
						checkBox.setForeground(Color.WHITE);
					}
					 @Override
					public void mouseExited(MouseEvent e) { 
						super.mouseExited(e);
						checkBox.setBackground(new Color(135, 206, 250));
						checkBox.setForeground(Color.BLACK);
					}
				 }
				 box1.addMouseListener(new MyMouseAdapter(box1)); 
				 box2.addMouseListener(new MyMouseAdapter(box2));
				 box3.addMouseListener(new MyMouseAdapter(box3));
				 box4.addMouseListener(new MyMouseAdapter(box4));

	}
   
}
