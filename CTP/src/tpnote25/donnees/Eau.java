package tpnote25.donnees;

public class Eau extends Boisson {
	private static double prixParBouteille = 1.5;
	private final boolean isPlate;
	
	public Eau(int volume, double poids) {
		super(volume, poids);
		this.isPlate = false;
	}
	
	public Eau(int volume, double poids, boolean isPlate) {
		super(volume, poids);
		this.isPlate = isPlate;
	}
	
	public void setPrixParBouteille(double prixParBouteille) {
		Eau.prixParBouteille=prixParBouteille;
	}
	public double getPrixParBouteille() {
		return Eau.prixParBouteille;
	}

	public boolean isPlate() {
		return isPlate;
	}
	
	public Produit clone() {
		Boisson clone = new Eau(this.volume,this.poids,this.isPlate);
		return clone;
	}

	@Override
	public double prixParUnite() {
		return prixParBouteille;
	}

	
}
