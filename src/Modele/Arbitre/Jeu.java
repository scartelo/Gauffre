package Modele.Arbitre;
import Structures.*;
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

    public void jouez(int l, int c){
        for (int i=l; i<plateau.lignes() ; i++) {
            for (int j = c; j < plateau.colonnes(); j++) {
                if (plateau.estJouable(i, j)) {
                    plateau.croquer(i, j);
                }
            }
        }
    }
    public void joue(int l, int c){
        if (plateau.estJouable(l, c)) {
            jouez(l, c);
            tour = (tour + 1) % 2;
            plateau.historique.H_ajouter(l, c);
            miseAJour();
        }
    }
    public void set_plateau(PlateauDeJeu p){
        plateau=p;
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
    public void restart() {
        plateau = new PlateauDeJeu();
        tour=0;
        miseAJour();
    }

    public void coup_refaire(){
        int taille=plateau.historique.getTaille();
        int taille_max=plateau.historique.getTailleMax();
        if(taille<taille_max) {
            int i = plateau.historique.get_i(taille);
            int j = plateau.historique.get_j(taille);
            joue(i, j);
        }
    }
    public void coup_precedent(){
        int taille=plateau.historique.getTaille();
        if(taille>0) {
            taille=taille-1;
            Historique H = plateau.historique;
            int tour_tmp = tour;
            restart();
            tour = tour_tmp;
            plateau.historique = H;
            for (int ite = 0; ite < taille; ite++) {
                int i = plateau.historique.get_i(ite);
                int j = plateau.historique.get_j(ite);
                jouez(i,j);
            }
            tour = (tour + 1) % 2;
            miseAJour();
            plateau.historique.dec_taille();
        }
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
