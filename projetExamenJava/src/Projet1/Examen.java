package Projet1;

import java.io.Serializable;

public class Examen implements Serializable {
	public String titre="";
	Professeur prof;
	int nbQuestion=20;
	String filiere="";
	String matiere="";
	
	public Examen() {
		super();
		this.titre = "";
		prof=new Professeur();
		this.nbQuestion = 0;
		this.filiere = "";
		this.matiere = "";
	} 
	
	public Examen(String titre, Professeur prof, int nbQuestion, String filiere, String matiere) {
		super();
		this.titre = titre;
		this.prof = prof;
		this.nbQuestion = nbQuestion;
		this.filiere = filiere;
		this.matiere = matiere;
	}


	public String getTitre() {
		return titre;
	}


	public void setTitre(String titre) {
		this.titre = titre;
	}


	public Professeur getProf() {
		return prof;
	}


	public void setProf(Professeur prof) {
		this.prof = prof;
	}


	public int getNbQuestion() {
		return nbQuestion;
	}


	public void setNbQuestion(int nbQuestion) {
		this.nbQuestion = nbQuestion;
	}


	public String getFiliere() {
		return filiere;
	}


	public void setFiliere(String filiere) {
		this.filiere = filiere;
	}


	public String getMatiere() {
		return matiere;
	}


	public void setMatiere(String matiere) {
		this.matiere = matiere;
	}


	@Override
	public String toString() {
		return "Examen [titre=" + titre + ", prof=" +prof.nomComplet+ ", nbQuestion=" + nbQuestion + ", filiere=" + filiere
				+ ", matiere=" + matiere + "]";
	}

 
	
	
	

}
