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
        System.out.println("joueIA");
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
        jeu.restart();
    }

    void refaire(){
        System.out.println("refaire");
        jeu.coup_refaire();
    }
    void annule(){
        System.out.println("annule");
        jeu.coup_precedent();
    }

    void save(){
        System.out.println("save");
    }

    void load(String c){
        System.out.println("load  " + c);
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
            case "save":
                save();
                break;
            case "5":
                load(c);
                break;
            default:
                return false;
        }
        return true;
    }

    @Override
    public void commandeInput(String commande,String input) {
        switch(commande){
            case "load":
                load(input);
                break;
            default:

        }


    }

}
