package Projet1;

import java.io.Serializable;

public class Note implements Serializable {
	private static final long serialVersionUID = 1L;
	int n=0;
	String nomComplet="";
	String titreExamen="";
	int note=0;
	String dateExm="";
	public Note() {
		super();
		this.n = 0;
		this.nomComplet = "";
		this.titreExamen = "";
		this.note = 0;
		this.dateExm = "";
	}
	public Note(int n, String nomComplet, String titreExamen, int note, String dateExm) {
		super();
		this.n = n;
		this.nomComplet = nomComplet;
		this.titreExamen = titreExamen;
		this.note = note;
		this.dateExm = dateExm;
	}
	@Override
	public String toString() {
		return "Note [n=" + n + ", nomComplet=" + nomComplet + ", titreExamen=" + titreExamen + ", note=" + note
				+ ", dateExm=" + dateExm + "]";
	}
	public int getN() {
		return n;
	}
	public void setN(int n) {
		this.n = n;
	}
	public String getNomComplet() {
		return nomComplet;
	}
	public void setNomComplet(String nomComplet) {
		this.nomComplet = nomComplet;
	}
	public String getTitreExamen() {
		return titreExamen;
	}
	public void setTitreExamen(String titreExamen) {
		this.titreExamen = titreExamen;
	}
	public int getNote() {
		return note;
	}
	public void setNote(int note) {
		this.note = note;
	}
	public String getDateExm() {
		return dateExm;
	}
	public void setDateExm(String dateExm) {
		this.dateExm = dateExm;
	}
	
	
	
}
