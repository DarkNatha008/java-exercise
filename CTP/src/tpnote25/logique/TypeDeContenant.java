package tpnote25.logique;

public enum TypeDeContenant {
	panier(10000,false),
	panier_roulant(10000,true),
	caddie(0,true);
	private final double volumeMax;
	private final boolean peutRouler;
	TypeDeContenant(double volumeMax, boolean peutRouler){
		this.peutRouler=peutRouler;
		this.volumeMax=volumeMax;
	}
	public double getVolumeMax() {
		return volumeMax;
	}
	public boolean isPeutRouler() {
		return peutRouler;
	}
		
	
	
	
	
	
}
