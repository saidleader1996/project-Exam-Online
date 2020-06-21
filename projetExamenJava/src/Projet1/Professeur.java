package Projet1;

import java.io.Serializable;
import java.util.ArrayList;

public class Professeur implements Serializable {
	String idProf="";
	String nomComplet="";
	String specialite="";
	ArrayList<Examen> listExam=new ArrayList<Examen>();
	
	public Professeur() { 
		super();
		this.idProf = "";
		this.nomComplet = "";
		this.specialite = "";
	}
	
	public Professeur(String idProf, String nomComplet, String specialite) {
		super();
		this.idProf = idProf;
		this.nomComplet = nomComplet;
		this.specialite = specialite;
	}

 

	@Override
	public String toString() {
		return "Professeur [idProf=" + idProf + ", nomComplet=" + nomComplet + ", specialite=" + specialite
				+ ", listExam=" + listExam + "]";
	}

	public String getIdProf() {
		return idProf;
	}

	public void setIdProf(String idProf) {
		this.idProf = idProf;
	}

	public String getNomComplet() {
		return nomComplet;
	}

	public void setNomComplet(String nomComplet) {
		this.nomComplet = nomComplet;
	}

	public String getSpecialite() {
		return specialite;
	}

	public void setSpecialite(String specialite) {
		this.specialite = specialite;
	}

	public ArrayList<Examen> getListExam() {
		return listExam;
	}

	public void setListExam(ArrayList<Examen> listExam) {
		this.listExam = listExam;
	}
	 
	
	
	

}
