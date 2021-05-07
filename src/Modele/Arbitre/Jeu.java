package Modele.Arbitre;

import Modele.Joueur.IAAleatoire;
import Patterns.Observable;

import java.awt.*;

public class Jeu extends Observable {
    private boolean joueur1, joueur2, iaActive;
    PlateauDeJeu plateau;
    int tour;
    IAAleatoire ia;

    public Jeu(PlateauDeJeu p){
        joueur1 = false;
        joueur2 = false;
        iaActive = true;
        tour = 0;
        plateau = p;
        ia = new IAAleatoire(this);
    }

    public boolean estJoueurIA(boolean joueur){
        return joueur == true;
    }

    public void joue(int l, int c){
        for (int i=l; i<plateau.lignes() ; i++){
            for (int j=c; j<plateau.colonnes() ; j++){
                if (plateau.estJouable(i,j)) {
                    plateau.croquer(i,j);

                }
            }
        }
        tour = (tour + 1) % 2;
        miseAJour();
    }

    public boolean estTermine(){
        for (int i=0; i<plateau.lignes(); i++){
            for (int j=0; j<plateau.colonnes(); j++){
                if ( plateau.contenu(0,0) == -1 && plateau.contenu(i,j) == 1){
                    return false;
                }
            }
        }
        return true;
    }

    public int tour(){
        return tour;
    }

    public PlateauDeJeu plateau(){
        return plateau;
    }

    public int contenu(int i, int j){
        return plateau.contenu(i,j);
    }

    public boolean estTourIA(){
        return tour == 1;
    }

    public boolean IAActive(){
        return iaActive;
    }

    public IAAleatoire ia(){
        return ia;
    }

}
