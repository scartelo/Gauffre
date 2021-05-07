package Modele.Joueur;

import Modele.Arbitre.Jeu;
import Modele.Arbitre.PlateauDeJeu;

import java.awt.*;
import java.util.Random;

public class IAAleatoire {
    Random r ;
    Jeu jeu;

    public IAAleatoire(Jeu j){
        r = new Random();
        jeu = j;
    }

    public Coup joue() {
        Coup coup = null;
        int l = r.nextInt(jeu.plateau().lignes());
        int c = r.nextInt(jeu.plateau().colonnes());
        while (!jeu.plateau().estJouable(l,c)){
            l = r.nextInt(jeu.plateau().lignes());
            c = r.nextInt(jeu.plateau().colonnes());
        }
        coup = new Coup(l,c);
        return coup;
    }
}
