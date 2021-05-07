import Controleur.ControleurMediateur;
import Modele.Arbitre.Jeu;
import Modele.Arbitre.PlateauDeJeu;
import Vue.InterfaceGraphique;

public class Gauffre {
    public static void main(String[] args) {
        Jeu jeu = new Jeu( new PlateauDeJeu());
        ControleurMediateur controle = new ControleurMediateur(jeu);
        InterfaceGraphique ig = new InterfaceGraphique(jeu, controle);
        ig.demarrer(jeu, controle);

    }
}
