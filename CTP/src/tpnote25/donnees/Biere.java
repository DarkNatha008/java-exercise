package tpnote25.donnees;

public class Biere extends Boisson {
	private static double prixParBouteille = 3.3;
	private final double tauxDAlcool;
	
	public Biere(int volume, double poids) {
		super(volume, poids);
		this.tauxDAlcool = 5.0;
	}
	
	public Biere(int volume, double poids, double tauxDAlcool) {
		super(volume, poids);
		this.tauxDAlcool = tauxDAlcool;
	}
	
	public void setPrixParBouteille(double prixParBouteille) {
		Biere.prixParBouteille=prixParBouteille;
	}
	public double getPrixParBouteille() {
		return Biere.prixParBouteille;
	}

	public double getTauxDAlcool() {
		return tauxDAlcool;
	}
	
	public Produit clone() {
		Boisson clone = new Biere(this.volume,this.poids,this.tauxDAlcool);
		return clone;
	}

	@Override
	public double prixParUnite() {
		return prixParBouteille;
	}

	
}
