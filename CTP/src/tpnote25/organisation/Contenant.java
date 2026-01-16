package tpnote25.organisation;

import tpnote25.donnees.Produit;
import tpnote25.logique.TypeDeContenant;

public class Contenant {
	private final TypeDeContenant type;
	private int volumeActuel;
	private double poidsActuel;
	private final Produit[] contenu;
	public TypeDeContenant getType() {
		return type;
	}
	public int getVolumeActuel() {
		return volumeActuel;
	}
	public void setVolumeActuel(int volumeActuel) {
		this.volumeActuel = volumeActuel;
	}
	public double getPoidsActuel() {
		if(type.isPeutRouler()) {
			return (poidsActuel/2);
		}
		return poidsActuel;
	}
	public void setPoidsActuel(double poidsActuel) {
		this.poidsActuel = poidsActuel;
	}
	public Produit[] getProduit() {
		return contenu;
	}
	public Contenant(TypeDeContenant type) {
		this.type=type;
		this.contenu = null;
	}
	
}
