package Modele.Arbitre;
import Structures.*;
import Patterns.Observable;

public class PlateauDeJeu{
    int [][] grille;
    int lignes, colonnes;
    Historique historique;

    public PlateauDeJeu(int l, int c){
        lignes = l;
        colonnes = c;
        grille = new int[lignes][colonnes];
        historique = new Historique(lignes,colonnes);
        dessineGrille();
    }

    void dessineGrille(){
        for (int i=0; i<lignes; i++){
            for (int j=0; j<colonnes; j++){
                if (i == 0 && j==0)
                    grille[i][j] = -1;
                else
                    grille[i][j] = 1;
            }
        }
    }

    public int contenu(int l, int c){
        return grille[l][c];
    }

    public int lignes(){
        return lignes;
    }

    public int colonnes(){
        return colonnes;
    }
    public boolean estAccessible(int l, int c){
        return (l>=0 && l<lignes && c>=0 && c<colonnes);
    }

    public boolean estJouable(int l, int c){
        return estAccessible(l,c) && contenu(l,c) != 0;
    }


    public void afficher(){
        //String s = "";

        for (int i=0; i<lignes(); i++){
            //s += "|";
            for (int j=0; j<colonnes(); j++){
                /*if (grille[i][j] == -1)
                    s += " @";
                else if (grille[i][j] == 0)
                    s += " ";
                else
                    s += "x";*/
                System.out.print(grille[i][j] + " ");
            }
            //s += " |";
            System.out.println();
        }
    }

    public boolean aPoison(int i, int j) {
        return grille[i][j] == -1;
    }

    public boolean aGaufre(int i, int j) {
        return grille[i][j] == 1;
    }

    public void croquer(int i, int j){
        grille[i][j] = 0;
    }
}
