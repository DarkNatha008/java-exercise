package tpnote25.donnees;

import tpnote25.organisation.Contenant;

public class Client {
	private double argent;
	private Contenant contenant;
	private final int patience;
	private final double peutPorter;
	

	public double getArgent() {
		return argent;
	}

	public void setArgent(double argent) {
		this.argent = argent;
	}

	public Contenant getContenant() {
		return contenant;
	}
	
	public void setContenant(Contenant contenant) {
		this.contenant = contenant;
	}

	public int getPatience() {
		return patience;
	}
	
	public double getPeutPorter() {
		return peutPorter;
	}
	
	public Client(double argent, int patience, double peutPorter) {
		this.argent=argent;
		this.patience=patience;
		this.peutPorter=peutPorter;
	}
	

	
	
}
