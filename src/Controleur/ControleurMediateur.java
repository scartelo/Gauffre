package Controleur;

import Modele.Arbitre.Jeu;
import Modele.Joueur.Coup;
import Vue.CollecteurEvenements;
import Vue.InterfaceUtilisateur;

public class ControleurMediateur implements CollecteurEvenements {
    Jeu jeu;
    InterfaceUtilisateur iu;
    boolean iaActive;


    public ControleurMediateur(Jeu j){
        jeu = j;
        iaActive = true;
    }

    @Override
    public void joue(int l, int c){
        Coup coup = null;
        if (jeu.IAActive() && jeu.estTourIA()) {
            coup = jeu.ia().joue();
            jouerCoup(coup);
        }
        else if (!jeu.estTourIA()){
            clicSouris(l,c);
        }

    }
    @Override
    public void clicSouris(int l, int c) {
        jouerCoup(new Coup(l,c));
    }

    @Override
    public void jouerCoup(Coup coup){
        jeu.joue(coup.ligne(), coup.colonne());
    }
    @Override
    public void fixerIU(InterfaceUtilisateur iu) {
        this.iu = iu;
    }
}
