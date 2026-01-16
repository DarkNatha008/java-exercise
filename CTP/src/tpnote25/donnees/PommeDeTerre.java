package tpnote25.donnees;


public class PommeDeTerre extends FruitOuLegume {
	private static double prixParKG = 2.99;
	
	public PommeDeTerre(int volume, double poids) {
		super(volume, poids);
	}
	
	public void setPrixParKG(double prixParKG) {
		PommeDeTerre.prixParKG=prixParKG;
	}
	public double getPrixParKG() {
		return PommeDeTerre.prixParKG;
	}

	
	public Produit clone() {
		FruitOuLegume clone = new PommeDeTerre(this.volume,this.poids);
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


	
}
