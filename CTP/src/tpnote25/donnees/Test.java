package tpnote25.donnees;

public class Test {

	public static void main(String[] args) {
		Adulte adulte = null;
		try {
			adulte = new Adulte(0, 0, 0);
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		}
		System.out.println(adulte.getPeutPorter());
	}

}
