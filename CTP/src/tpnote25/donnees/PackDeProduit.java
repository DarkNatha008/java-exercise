package tpnote25.donnees;

import java.util.ArrayList;

public class PackDeProduit extends Produit{
	private int nombreDUnite;
	private ArrayList<Produit> listProduit = new ArrayList<Produit>();
	
	public PackDeProduit(int volume, double poids, int nombreDUnite, Produit produit) {
		super(volume, poids);
	}

	@Override
	public Produit clone() {
		
		return null;
	}

	public ArrayList<Produit> getListProduit() {
		return listProduit;
	}

	public void setListProduit(ArrayList<Produit> listProduit) {
		this.listProduit = listProduit;
	}

	public int getNombreDUnite() {
		return nombreDUnite;
	}

	public void setNombreDUnite(int nombreDUnite) {
		this.nombreDUnite = nombreDUnite;
	}
	
	

}
