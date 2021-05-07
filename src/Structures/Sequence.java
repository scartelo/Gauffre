package Structures;

public interface Sequence<Toto> {
	void insereTete(Toto element);
	void insereQueue(Toto element);
	Toto extraitTete();
	boolean estVide();
	Iterateur<Toto> iterateur();
}
