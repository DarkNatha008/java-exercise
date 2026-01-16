package tpnote25.donnees;

import tpnote25.logique.Pesable;

public abstract class FruitOuLegume extends Produit implements Pesable {

	public FruitOuLegume(int volume, double poids) {
		super(volume, poids);
	}

}
