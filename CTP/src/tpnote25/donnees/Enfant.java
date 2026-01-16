package tpnote25.donnees;

public class Enfant extends Client{

	public Enfant(double argent, int patience, double peutPorter) throws IllegalArgumentException {
		super(argent, patience, peutPorter);
		try {
			if(peutPorter<5000 || argent>15 || patience>100) {
				throw new IllegalArgumentException();
			}
		}
		catch(IllegalArgumentException e) {
			if(peutPorter<5000) {
				System.out.println("IllegalArgumentException : peutPorter must be greater than (or equal to) 5000.");
			}
			if(argent>15) {
				System.out.println("IllegalArgumentException : argent must be inferior than (or equal to) 15.");
			}
			if(patience>100) {
				System.out.println("IllegalArgumentException : patience must be inferior than (or equal to) 100.");
			}
		}
	}
	
}
