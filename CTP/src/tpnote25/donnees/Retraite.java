package tpnote25.donnees;

public class Retraite extends Client{

	public Retraite(double argent, int patience, double peutPorter) throws IllegalArgumentException {
		super(argent, patience, peutPorter);
		try {
			if(peutPorter<10000 || argent>200) {
				throw new IllegalArgumentException();
			}
		}
		catch(IllegalArgumentException e) {
			if(peutPorter>10000) {
				System.out.println("IllegalArgumentException : peutPorter must be less than (or equal to) 10000.");
			}
			if(argent>200) {
				System.out.println("IllegalArgumentException : argent must be inferior than (or equal to) 200.");
			}
		}
	}
	
}
