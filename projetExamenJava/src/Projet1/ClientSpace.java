package Projet1; 
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JOptionPane; 
public class ClientSpace extends Thread {  
	String clientMsg,reponse;
	Socket soclient;
	MyServer myserver;
	DataOutputStream msg_Out;
	DataInputStream msg_In; 
	ObjectOutputStream obj_out;
	ObjectInputStream obj_In; 
	gestionDb db=new gestionDb();
	Connection conx=db.getConx();  
	boolean loged=false;
	String  exm="";
	int nbQst;
	Question[] questions;
	Etudiant etudiant;
	Professeur prof;
	ArrayList<Examen> exams=db.getExamens();//tous les exms dispo
	ArrayList<Note> notes=new ArrayList<Note>();
		public ClientSpace(Socket client,MyServer server) { 
			soclient=client;
			myserver=server;
		  }  
 
		@Override
		public void run() { //communication client-serveur
			super.run(); 
			String id=loging();//bloquante ,authentification...  
			//myserver.serverGUI.txtarea.append("\t   "+etudiant.nomComplet+" has started the Test\n"); 
			if(id.equals("etudiant")) {
				 int i = 0,score = 0;  
				 ArrayList<Integer> rand=new ArrayList<Integer>();//contient des indices aleatoire de 0 à 30
				 while(soclient.isConnected()) {  //deroulement d'examen
					  try {  
						clientMsg=msg_In.readUTF(); 
						if(clientMsg.equals("examen") ) {//Load the first question 
							 getSelectedExm();i=0; score=0; 
							 rand=getUniqueRandNbs(nbQst,30);   
							 msg_Out.writeUTF("next_question");
							 obj_out=new ObjectOutputStream(msg_Out);
							 obj_out.writeObject(questions[rand.get(i)]);
						 }
						    //	myserver.serverGUI.txtarea.append("\t\t\t"+etudiant.nomComplet+": "+clientMsg+"\n");  
						
						if(clientMsg.equals("validation") && i<=nbQst) {  //client demande la validation de reponse
					  		
							String reponse=msg_In.readUTF(); //read client response to the question
							//myserver.serverGUI.txtarea.append(etudiant.nomComplet+"'s answer: "+reponse+" sol: "+String.valueOf(questions[rand[i]].solBinaire)+"\n");
							if(reponse.equals( String.valueOf(questions[rand.get(i)].solBinaire)) ) score++;  //verify answer
							msg_Out.writeUTF("score");
							msg_Out.writeUTF(String.valueOf(score)); 
						 
							if( i== nbQst-1 ) { //when the test is done ,notify:
								myserver.serverGUI.txtarea.append("--"+etudiant.nomComplet+" a terminé l'examen "+exm+" , score: "+score+"/"+nbQst+"\n");
								msg_Out.writeUTF("test finished"); i=0;
								db.saveNote(etudiant.nomComplet, exm, score);  //save score in db
								obj_out=new ObjectOutputStream(msg_Out); 
								notes=db.getNotes(etudiant.nomComplet) ; 		//send the latest update of 'Notes'
								obj_out.writeObject(notes);  
							  } 
							i++;
							if(i<nbQst) {//send next question to the client
								msg_Out=new DataOutputStream(soclient.getOutputStream());
								msg_Out.writeUTF("next_question");  //send next question to client
								obj_out=new ObjectOutputStream(msg_Out); 
								obj_out.writeObject(questions[rand.get(i)]);  
							}  
						}  
						
					  }catch (IOException e1) {  System.out.println(etudiant.nomComplet+" left cuz: "+e1.getMessage()); myserver.clients.remove(this); return;}  
				  } 
			}else if( id.equals("prof") ) {
				while(soclient.isConnected()) {
					 try { 
							String msg=msg_In.readUTF();
							if(msg.equals("notes")) { 
									String exmChoisie=msg_In.readUTF();
									notes=db.getExamNotes(prof.idProf, exmChoisie);
									obj_out.writeObject(notes);  
									msg_Out.flush();
							} 
							if(msg.equals("matieres")) { 
								System.out.println("server : demand de matieres recu");
								ArrayList<Matiere> matieres=db.getMatieres(prof);
								obj_out.writeObject(matieres );  
								msg_Out.flush();
							} 
							if(msg.equals("examen")) { 
								obj_In=new ObjectInputStream(msg_In);
								try {
									Examen exm= (Examen) obj_In.readObject(); 
									db.insertExm(exm);
									ArrayList<Question> questions=(ArrayList<Question>) obj_In.readObject();  
									db.insertExmQuestions( exm, questions);
								} catch (ClassNotFoundException e) { e.printStackTrace(); }
							} 
					} catch (IOException e) { System.out.println(prof.nomComplet+" left cuz: "+e.getMessage()); myserver.clients.remove(this); return; } 
				 } 
			}
			 myserver.clients.remove(this); return;
		}
		
public String loging() {
			String who = "";
			try {//inscription... 
				msg_In=new DataInputStream(soclient.getInputStream()); 
				msg_Out=new DataOutputStream(soclient.getOutputStream());
				who=msg_In.readUTF();
				while(loged==false) {//  block server till login is done
							if(who.equals("etudiant")) { 
										String get=msg_In.readUTF();
										if(get.equals("CNE")) { //lire le nom de candidat
												String cne=msg_In.readUTF();	  
												if( db.isInFiliere(cne ,"etudiant") ) { //verifi si l'etudiant existe dans la filiere(db)
														msg_Out.writeUTF("found"); who="etudiant";
														obj_out=new ObjectOutputStream(msg_Out);//send Etudiant info to client
														etudiant=db.getEtudiant(cne) ; 
														obj_out.writeObject(etudiant ); 
														loged=true;  
														obj_out.writeObject(exams );
														notes=db.getNotes(etudiant.nomComplet); 
														obj_out.writeObject(notes);  
												} else msg_Out.writeUTF("not found");
										}  	
							}else if(who.equals("prof")) { 
										String get=msg_In.readUTF();
										if(get.equals("CIN")) { //lire le nom de prof
												String cin=msg_In.readUTF();	  
												if( db.isInFiliere(cin,"prof") ) { //verifi si prof existe dans la filiere(db)
														msg_Out.writeUTF("found");  who="prof";
														obj_out=new ObjectOutputStream(msg_Out);//send prof info to client
														prof=db.getProf(cin);
														obj_out.writeObject(prof ); 
														msg_Out.flush();
														loged=true;     
												} else msg_Out.writeUTF("not found");
										}   
							} 
				}
			}catch (IOException e) { System.out.println("server says: error in login, error:  "+e.getMessage());} 
			return who;
		}
public void getSelectedExm() { 
			try {  //get selected exm from client
					int exmChoisi=msg_In.readInt(); 
					exm=exams.get(exmChoisi).titre;
					myserver.serverGUI.txtarea.append("--"+etudiant.nomComplet+" a commencé l'examen de:  "+exm+"\n"); 
					nbQst=exams.get(exmChoisi).nbQuestion;  
					questions=db.getQuestions(conx, 30,exm);  
					if(questions.length<nbQst) nbQst=questions.length; //le cas ou les qsts dans db sans insuffisant 
			} catch (IOException e) { }
			
		}
public ArrayList<Integer> getUniqueRandNbs(int length,int tous) { // genere nbres aleatoire non repetés
			int len=length,k=0;  
			ArrayList<Integer> randInt=new ArrayList<Integer>();
			boolean deja;
			for (int i = 0; i < len; i++) { 
					 deja=false;
					 Random r=new Random();
					 int n=r.nextInt(tous-1);//it must be 30 but i change it just for test cuz some exms dont have enough qsts
					 for (int j = 0; j < k; j++) {
						 if( n==randInt.get(j) ) {
							 deja=true;
							 len++; 
						 } 
					 }
					 if(!deja) {
						 randInt.add(n);
						 k++;
					 } 
					 if(k==length) break; 
			 }
			 return randInt; 	
		}
}
