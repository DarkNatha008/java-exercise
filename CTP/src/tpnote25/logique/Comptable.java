package tpnote25.logique;

public interface Comptable {
	default int nombreDUnite() {
	return 1;
}
double prixParUnite();
}