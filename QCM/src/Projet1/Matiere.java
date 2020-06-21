package Projet1;

import java.io.Serializable;

public class Matiere implements Serializable {
	String nom_matiere="";
	Professeur prof;
	String filiere="";
	
	public Matiere() {
		super();
		this.nom_matiere = "";
		this.prof = new Professeur();
		this.filiere = "";
	}
	
	public Matiere(String nom_matiere, Professeur prof, String filiere) {
		super();
		this.nom_matiere = nom_matiere;
		this.prof = prof;
		this.filiere = filiere;
	}

	@Override
	public String toString() {
		return "Matiere [nom_matiere=" + nom_matiere + ", prof=" + prof + ", filiere=" + filiere + "]";
	}
	
	
}
