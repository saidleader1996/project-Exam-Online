package Projet1;   
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList; 
import javax.swing.JOptionPane;  

public class MyServer {   
	ServerSocket server = null; 
	Socket client = null;
	ArrayList<ClientSpace> clients; 
	__demarrer_serveur serverGUI;
	ClientSpace c;
	 public MyServer(__demarrer_serveur serverGui)  { 
		 this.serverGUI=serverGui;
		 clients=new ArrayList<ClientSpace>();
		try {//------create a server socket with port=6666 
			server = new ServerSocket(6667);
			System.out.println("server is listening...");
		} catch (IOException e) {JOptionPane.showMessageDialog(null,"server has not been created error: "+e.getMessage());System.exit(0);	} 

	    while(true) {
			try { 
				client=server.accept();//bloquante
				c=new ClientSpace(client,this); //ecouter chaque nv client
				clients.add(c);   
				c.start();
				
			} catch (IOException e) { 
				JOptionPane.showMessageDialog(null, "client has not been accepted error: "+e.getMessage());  } 
	   }
	} 
}