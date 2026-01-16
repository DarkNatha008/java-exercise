package tpnote25.donnees;

public class Adulte extends Client{

	public Adulte(double argent, int patience, double peutPorter) throws IllegalArgumentException {
		super(argent, patience, peutPorter);
		try {
			if(peutPorter<5000) {
				throw new IllegalArgumentException();
			}
		}
		catch(IllegalArgumentException e) {
			System.out.println("IllegalArgumentException : peutPorter must be greater than (or equal to) 5000.");
		}
	}
	
}
