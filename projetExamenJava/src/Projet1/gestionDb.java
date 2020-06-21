package Projet1; 
import java.awt.CardLayout;
import java.awt.Toolkit;
import java.io.ByteArrayInputStream;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException; 
import java.sql.Statement;
import java.sql.Time;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import com.mysql.jdbc.Blob; 
public class gestionDb { 
	public Connection conx=null;
	public Statement stat=null;
	public ResultSet rs=null;
	Blob imgBlob=null;
	public gestionDb() {
		
		
	}
	public Connection getConx(){ 
		try {  Class.forName("com.mysql.jdbc.Driver");} 
		catch( ClassNotFoundException e1) {JOptionPane.showMessageDialog(null,"Driver connexion error: "+e1.getMessage());System.exit(0);}  
		try {  conx=DriverManager.getConnection("jdbc:mysql://localhost:3307/project1","root","");   //mariaDb port=3307 pour moi
		} catch (SQLException e1) {
			JOptionPane.showMessageDialog(null,"Connection error \ngestionDb says: the server can't establish connection with db, please check conx port or the db if exists, error:\n\t\t"+e1.getMessage());
			System.exit(0);}  
		return conx; 
	}

	public Question[] getQuestions(Connection conx,int nbreQuestion,String exm) {
		String query="select * from question where titreExamen='"+exm+"'"; 
	    Question[] question = new Question[nbreQuestion]; 
		try {
			 stat=conx.createStatement(); 
			 rs=stat.executeQuery(query);  
			 
			int i=0;
			if( ! rs.next()){ JOptionPane.showMessageDialog(null, "cet examen n'est pas encore préparé!"); rs.previous();}
			
			while( rs.next() && i<nbreQuestion) { 
			 question[i]=new Question();
			 question[i].numero=rs.getInt(1); 
			 question[i].ennonce=rs.getString(2);
			 question[i].choix[0]=rs.getString(3);
			 question[i].choix[1]=rs.getString(4);
			 question[i].choix[2]=rs.getString(5);
			 question[i].choix[3]=rs.getString(6);
			 question[i].solBinaire=rs.getString(7).toCharArray(); 
			 imgBlob=(Blob) rs.getBlob(8);
			 question[i].imgBin= imgBlob.getBytes(1, (int) imgBlob.length()); 
			 question[i].titreExamen=rs.getString(9);
			 i++;
			}
		} catch (SQLException e) { JOptionPane.showMessageDialog(null,"can't upload questions from db, error: "+e.getMessage());System.exit(0);}
		  
		return question;
	}
	
	public boolean isInFiliere(String id, String type) { //verifier si un etudiant/prof donné existe
		conx=this.getConx();
		boolean flag=false; 
		String query;
		
		if( type.equals("etudiant") ) 
			query="select * from etudiant where CNE='"+id.toLowerCase()+"'"; 
		else						  
			query="select * from professeur where idProf='"+id.toLowerCase()+"'";
		
		try {
			 stat=conx.createStatement(); 
			 rs=stat.executeQuery(query);   
			 flag=rs.next(); 
		} catch (SQLException e) { JOptionPane.showMessageDialog(null,"gestionDb says: error in query, error: "+e.getMessage());System.exit(0);}
		  
		 return flag;
	}
	
	public Etudiant getEtudiant(String cne) { 
		String query="select * from etudiant where CNE='"+cne+"'";
		Etudiant etudiant=new Etudiant();
		try {
			 stat=conx.createStatement(); 
			 rs=stat.executeQuery(query); 
			 if(rs.next()) { 
				 etudiant.cne=rs.getString("CNE");
				 etudiant.nomComplet=rs.getString("nomComplet");
				 etudiant.filiere=rs.getString("codeFil");
				 return etudiant;
			 }
		} catch (SQLException e) { JOptionPane.showMessageDialog(null,"error in query cant get etudiant, error: "+e.getMessage());System.exit(0);}
	 
		return null;
	}
	public Professeur getProf(String cin) { 
		String query="select * from professeur where idProf='"+cin+"'";
		Professeur prof=new Professeur();
		try {
			 stat=conx.createStatement(); 
			 rs=stat.executeQuery(query); 
			 if(rs.next()) { 
				 prof.idProf=rs.getString("idProf");
				 prof.nomComplet=rs.getString("NomComplet");
				 prof.specialite=rs.getString("specialite");
				
			 }
			 query="select * from examen where idProf='"+cin+"'";
			 rs=stat.executeQuery(query); 
			 int i=0;
			 while(rs.next()) { 
				 Examen exm=new Examen();  
				 exm.titre=rs.getString("titreExamen");
				 exm.nbQuestion=rs.getInt("nbQuestion");
				 exm.matiere=rs.getString("matiere");
				 exm.prof.idProf=rs.getString("idProf");
				// exm.prof.nomComplet=rs.getString("nomComplet");
				 exm.filiere=rs.getString("codeFil");
				// exm.prof.listExam.add( exm );   
				 prof.listExam.add(exm);
				 i++; 
			 }
			// System.out.println(prof.toString());
			
		} catch (SQLException e) { JOptionPane.showMessageDialog(null,"error in query cant get prof, error: "+e.getMessage());System.exit(0);}
	 
		 return prof;
	}
	public  ArrayList<Examen> getExamens() { //all available exams for etudiant
		String query="SELECT * FROM examen  INNER JOIN filiere on examen.codeFil=filiere.codeFil INNER JOIN professeur ON examen.idProf=professeur.idProf ;" ;
		ArrayList<Examen> exams = new ArrayList<Examen>(); 
		
		int i=0;
		
		try { 
			 stat=conx.createStatement(); 
			 rs=stat.executeQuery(query); 
			 while(rs.next()) { 
				 Examen exm=new Examen(); 
				 exm.titre=rs.getString("titreExamen");
				 exm.nbQuestion=rs.getInt("nbQuestion");
				 exm.matiere=rs.getString("matiere");
				 exm.prof.idProf=rs.getString("idProf");
				 exm.prof.nomComplet=rs.getString("nomComplet");
				 exm.prof.specialite=rs.getString("specialite");
				 exm.filiere=rs.getString("intitulé");  
				 exm.prof.listExam.add( exm );   
				 exams.add(exm);
				 i++; 
			 }
			
			 return exams;
		} catch (SQLException e) { JOptionPane.showMessageDialog(null,"gestionDb says:cant get exms error in query, error: "+e.getMessage());System.exit(0);}
	 
			 System.out.println("error");
		return null;
		
	}
	
	public void saveNote(String nom,String titreExm,int note) {
		LocalDate date=LocalDate.now();
		String query="INSERT INTO notes (nomComplet, titreExamen, note, date) VALUES ('"+nom+"','"+titreExm+"','"+note+"','"+date.toString()+"')";
		try {
			PreparedStatement pre=conx.prepareStatement(query); 
			pre.executeUpdate();
		} catch (SQLException e) { System.out.println("gestionDb : cant insert exam note : "+e.getMessage());}
	}

	public ArrayList<Note> getNotes(String nomEtud){
		String query="SELECT * FROM notes where nomComplet='"+nomEtud+"' order by date DESC ";
		ArrayList<Note> notes = new ArrayList<Note>(); 
		 
		try { 
			 stat=conx.createStatement(); 
			 rs=stat.executeQuery(query); 
			 while(rs.next()) { 
				 Note note=new Note();
				 note.n=rs.getInt("numero");
				 note.nomComplet=rs.getString("nomComplet");
				 note.titreExamen=rs.getString("titreExamen");
				 note.note=rs.getInt("note");
				 note.dateExm=rs.getString("date"); 

				 notes.add(note); 
			 }  
			 return notes;
		} catch (SQLException e) { JOptionPane.showMessageDialog(null,"gestionDb says:cant get \'Notes\' , error in query, error: "+e.getMessage());System.exit(0);}
	 
			 System.out.println("error");
		return null;
		
	}
 	
	public ArrayList<Note> getExamNotes(String idProf,String exm){
			String query="SELECT * FROM  notes \r\n" + 
					"				INNER JOIN examen on notes.titreExamen=examen.titreExamen\r\n" + 
					"                WHERE examen.idProf='"+idProf+"' AND examen.titreExamen='"+exm+"'\r\n" + 
					"                ORDER BY date DESC ";
			ArrayList<Note> notes = new ArrayList<Note>(); 
			 
			try { 
				 stat=conx.createStatement(); 
				 rs=stat.executeQuery(query); 
				 while(rs.next()) { 
					 Note note=new Note();
					 note.n=rs.getInt("numero");
					 note.nomComplet=rs.getString("nomComplet");
					 note.titreExamen=rs.getString("titreExamen");
					 note.note=rs.getInt("note");
					 note.dateExm=rs.getString("date"); 
	
					 notes.add(note); 
				 }   
			} catch (SQLException e) { JOptionPane.showMessageDialog(null,"gestionDb says:cant get \'Notes\' , error in query, error: "+e.getMessage());System.exit(0);}
			return notes; 
	}
	
	public void insertExm(Examen exm) { 
		String query="INSERT INTO `examen` (`titreExamen`, `nbQuestion`, `matiere`, `idProf`, `codeFil`) VALUES ('"+exm.titre+"','"+exm.nbQuestion+"','"+exm.matiere+"','"+exm.prof.idProf+"','"+exm.filiere+"')";
		try {
			PreparedStatement pre=conx.prepareStatement(query); 
			pre.executeUpdate();
		} catch (SQLException e) { System.out.println("gestionDb : cant insert exam : "+e.getMessage());}
	}
	public void insertExmQuestions(Examen exm,ArrayList<Question> questions) {  
		try { 
			for (int i = 0; i < questions.size(); i++) { 
				 String query="INSERT INTO question(description,choix1,choix2,choix3,choix4,solbinaire,img,titreExamen) VALUES (?,?,?,?,?,?,?,?)"; 
				 PreparedStatement pre=conx.prepareStatement(query);  
				 pre.setString(1,  questions.get(i).ennonce);
				 pre.setString(2,  questions.get(i).choix[0]);
				 pre.setString(3,  questions.get(i).choix[1]);
				 pre.setString(4,  questions.get(i).choix[2]);
				 pre.setString(5,  questions.get(i).choix[3]);
				 pre.setString(6,  String.copyValueOf(questions.get(i).solBinaire )	 );
				 ByteArrayInputStream imgbin=new ByteArrayInputStream(questions.get(i).imgBin);
				 pre.setBinaryStream(7,imgbin );
				 pre.setString(8, exm.titre  ); 
				 pre.execute();
			}
		} catch (SQLException e) { System.out.println("gestionDb : cant insert questions: "+e.getMessage());}
	}
	
	public ArrayList<Matiere> getMatieres(Professeur prof){
		String query="SELECT * FROM matiere where idProf='"+prof.idProf+"';";
		ArrayList<Matiere> matieres = new ArrayList<Matiere>(); 
		 
		try { 
			 stat=conx.createStatement(); 
			 rs=stat.executeQuery(query); 
			 while(rs.next()) { 
				 Matiere matiere=new Matiere();
				 
				 matiere.nom_matiere=rs.getString("nom_matiere");
				 matiere.prof=prof;
				 matiere.filiere=rs.getString("codeFil"); 
				 
				
				 matieres.add(matiere);
			 }   
		} catch (SQLException e) { JOptionPane.showMessageDialog(null,"gestionDb says:cant get \'Matieres\' , error in query, error: "+e.getMessage());System.exit(0);}
		return matieres; 
	}

	
	
	public static void main(String[] args) {
		gestionDb db=new gestionDb();
		Connection conx=db.getConx();
		Question[] qst=db.getQuestions(conx,5,"JAVA");
		
		
		Professeur prof=new Professeur("prof1", "khrissi", "info");
		
		char[] c= {'f','f','k'};
		//System.out.println(String.valueOf(c ));
		//db.getMatieres( prof);
		// System.out.println( db.getProf("prof1").toString() ); 
		 
		// db.getExamNotes("prof1","JAVA");
		 
		  //System.out.println(qst[4].toString());
		//System.out.println(db.isInFiliere("N139300001","etudiant")) ;  
		//System.out.println(db.isInFiliere("prof1","prof")) ;  
		
		//System.out.println(db.getEtudiant("N139300001").toString() );
		// System.out.println( db.getExamens().toString()); 
		/*Professeur p=new Professeur("prof1", "sai", "info");
		Examen e=new Examen("exm", p, 20, "info", "java");
		System.out.println(e.toString());*/ 
	}	 

}
