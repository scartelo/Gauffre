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




    void restart(){
        System.out.println("restart");
    }

    void refaire(){
        System.out.println("refaire");
    }
    void annule(){
        System.out.println("annule");
    }


    @Override
    public boolean commande(String c){
        switch(c){
            case "restart":
                restart();
                break;
            case "annule":
                annule();
                break;
            case "refaire":
                refaire();
                break;
            default:
                return false;
        }
        return true;
    }

}
