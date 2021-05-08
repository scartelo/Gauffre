package Controleur;

import Modele.Arbitre.Jeu;
import Modele.Joueur.Coup;
import Structures.Saves;
import Vue.CollecteurEvenements;
import Vue.InterfaceUtilisateur;
import java.util.*;

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

    void save() {
        System.out.println("saving");
        Saves save = new Saves();
        save.set_save(jeu.plateau());
        save.write_save();
    }
    void load(String c){
        int s = Integer.parseInt(c);
        Saves save = new Saves();
        List<Integer> l = save.get_n_saves();
        if (l.contains(s)) {
            save.read_save(s);
            save.load_save(jeu);
        }
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
