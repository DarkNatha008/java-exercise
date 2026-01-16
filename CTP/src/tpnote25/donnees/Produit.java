package tpnote25.donnees;

public abstract class Produit implements Cloneable{
	protected int volume;
	protected double poids;
	public int getVolume() {
		return volume;
	}
	public void setVolume(int volume) {
		this.volume = volume;
	}
	public double getPoids() {
		return poids;
	}
	public void setPoids(double poids) {
		this.poids = poids;
	}
	public Produit(int volume, double poids) {
		this.volume=volume;
		this.poids=poids;
	}
	public abstract Produit clone();
}
