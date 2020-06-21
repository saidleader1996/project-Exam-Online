package Projet1;

import java.io.Serializable;

public class Etudiant implements Serializable {
	 private static final long serialVersionUID = 1L;
	String cne="";
	String nomComplet="";
	String filiere="";
	
	public Etudiant() {
		super();
		this.cne = "";
		this.nomComplet = "";
		this.filiere = "";
	}
	
	public Etudiant(String cne, String nomComplet, String filiere) {
		super();
		this.cne = cne;
		this.nomComplet = nomComplet;
		this.filiere = filiere;
	}
	@Override
	public String toString() {
		return "Etudiant [cne=" + cne + ", nomComplet=" + nomComplet + ", filiere=" + filiere + "]";
	}
	
	
}
