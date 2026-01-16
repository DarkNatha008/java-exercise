package tpnote25.donnees;

import tpnote25.logique.Comptable;

public abstract class Boisson extends Produit implements Comptable {

	public Boisson(int volume, double poids) {
		super(volume, poids);
	}

}
