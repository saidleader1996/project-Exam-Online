package Projet1; 
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.chrono.JapaneseDate;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel; 
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class __demarrer_serveur extends Thread{
	JFrame frame=new JFrame("Serveur d'examens");
	JPanel border,south, north; 
	JLabel header;
	JTextArea txtarea;
	JTextField input;
	JButton Clear;
	MyServer monserveur;
	gestionDb db=new gestionDb();
 public __demarrer_serveur() {
	 	db.getConx();//conx to db must established FIRST
	 	draw();
	    monserveur=new MyServer(this); 
 }
	public static void main(String[] args) { 
			new __demarrer_serveur(); 
	}  
	public void draw() {  
			frame.setBounds(930,100,450,600);
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
			//frame.setLocationRelativeTo(null);
			frame.setLayout(new BorderLayout()); 
			
			south=new JPanel( ); 
			south.setBackground(new Color(0, 128, 128));
			south.setBounds(150, 100, 300, 200);
			
			north=new JPanel(new FlowLayout());
			
			JLabel logo=new JLabel();
			logo.setIcon(new ImageIcon("ressources\\school (2).png"));
			north.add(logo);
			
			header=new JLabel("Serveur d'examens");  
			header.setFont(new Font("Calibri", Font.ITALIC, 20));
			
			north.add(header);
			frame.add(north,"North"); 
			txtarea=new JTextArea(); 
			txtarea.setLineWrap(true);
			txtarea.setWrapStyleWord(true);  
		 	txtarea.setEditable(false);   
			JScrollPane scrollPane = new JScrollPane(txtarea/* ,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS */);     
			frame.add(scrollPane,"Center");  
			   
		    Clear=new JButton("Clear logs");   
		    Clear.addActionListener(new ActionListener() { 
				@Override
				public void actionPerformed(ActionEvent e) {
					txtarea.setText(""); 
				}
			});
			south.add(Clear);  	  
		    frame.add(south ,"South");
			frame.setVisible(true); 
		 } 
} 