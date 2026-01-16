package tpnote25.donnees;

import tpnote25.logique.Comptable;

public class Banane extends FruitOuLegume implements Comptable {
	private static double prixParKG = 3.99;
	
	public Banane(int volume, double poids) {
		super(volume, poids);
	}
	
	public void setPrixParKG(double prixParKG) {
		Banane.prixParKG=prixParKG;
	}
	public double getPrixParKG() {
		return Banane.prixParKG;
	}

	
	public Produit clone() {
		FruitOuLegume clone = new Banane(this.volume,this.poids);
		return clone;
	}

	@Override
	public double poids() {
		return poids;
	}

	@Override
	public double prixParKG() {
		return prixParKG;
	}

	@Override
	public double prixParUnite() {
		return prixParKG/this.poids;
	}

	
}
