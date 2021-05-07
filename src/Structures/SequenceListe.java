package Structures;

class Maillon<Tutu> {
	Tutu element;
	Maillon<Tutu> suivant;
}

public class SequenceListe<Titi> implements Sequence<Titi> {
	Maillon<Titi> tete, queue;

	public void insereTete(Titi element) {
		Maillon<Titi> nouveau = new Maillon<>();
		nouveau.element = element;
		nouveau.suivant = tete;
		if (tete == null) {
			tete = nouveau;
			queue = nouveau;
		} else {
			tete = nouveau;
		}
	}

	public void insereQueue(Titi element) {
		Maillon<Titi> nouveau = new Maillon<>();
		nouveau.element = element;
		nouveau.suivant = null;
		if (tete == null) {
			tete = nouveau;
			queue = nouveau;
		} else {
			queue.suivant = nouveau;
			queue = nouveau;
		}
	}

	public Titi extraitTete() {
		if (tete == null)
			throw new RuntimeException("Sequence vide !");
		Titi resultat = tete.element;
		tete = tete.suivant;
		// Ici, oubli de la mise à jour de la queue probablement sans conséquences :
		// la queue n'est incohérente qu'en cas de liste vide, dans ce cas pas d'itération
		// possible sur ses éléments et tout sera remis en cohérence à la prochaine insertion
		return resultat;
	}

	public boolean estVide() {
		return tete == null;
	}

	@Override
	public Iterateur<Titi> iterateur() {
		return new IterateurListe<>(this);
	}

	public String toString() {
		String resultat = "Sequence liste : [ ";
		Maillon<Titi> courant = tete;
		while (courant != null) {
			resultat += courant.element + " ";
			courant = courant.suivant;
		}
		resultat += "]";
		return resultat;
	}
}
