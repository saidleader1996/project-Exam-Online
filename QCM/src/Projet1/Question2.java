package Projet1;

import java.awt.Image;
import java.io.InputStream;
import java.io.Serializable;
import java.util.Arrays;

import javax.swing.ImageIcon;

public class Question2 implements Serializable {
	public int numero;
	public String  ennonce = " "; 
	public String[] choix ={"","","",""};
	public char[] solBinaire= {'0','0','0','0'}; //1001 : les vrais choix sont choix[0] et choix[3] 
	ImageIcon img;
	String titreExamen="";
	public Question2() {
		super();  
		this.numero=0;
		this.ennonce="vide"; 
	}

	public Question2(int numero, String ennonce, String[] choix, char[] solBinaire) {
		super();
		this.numero = numero;
		this.ennonce = ennonce;
		this.choix = choix;
		this.solBinaire = solBinaire;
	}

	public int getNumero() {
		return numero;
	}

	public void setNumero(int numero) {
		this.numero = numero;
	}

	public String getEnnonce() {
		return ennonce;
	}

	public void setEnnonce(String ennonce) {
		this.ennonce = ennonce;
	}

	public String[] getChoix() {
		return choix;
	}

	public void setChoix(String[] choix) {
		this.choix = choix;
	}

	public char[] getSolBinaire() {
		return solBinaire;
	}

	public void setSolBinaire(char[] solBinaire) {
		this.solBinaire = solBinaire;
	}

	@Override
	public String toString() {
		return "Question [numero=" + numero + ", ennonce=" + ennonce + ", choix=" + Arrays.toString(choix)
				+ ", solBinaire=" + Arrays.toString(solBinaire) + ", imgBin=" 
				+ ", titreExamen=" + titreExamen + "]";
	} 
		 
}
