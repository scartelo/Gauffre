package Modele.Arbitre;
import Structures.*;
import Patterns.Observable;

public class PlateauDeJeu{
    int [][] grille;
    int lignes, colonnes;
    Historique historique;

    public PlateauDeJeu(){
        lignes = 6;
        colonnes = 8;
        grille = new int[lignes][colonnes];
        historique = new Historique(lignes,colonnes);
        dessineGrille();
    }

    void dessineGrille(){
        for (int i=0; i<lignes; i++){
            for (int j=0; j<colonnes; j++){
                //redimensionne(i,j);
                if (i == 0 && j==0)
                    grille[i][j] = -1;
                else
                    grille[i][j] = 1;
            }
        }
    }
    public void set_hist(Historique H ){
        historique=H;
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

    public Historique get_hist(){
        return historique;
    }

    int ajuste(int c, int i) {
        while (c <= i) {
            c *= 2;
        }
        return c;
    }

    void redimensionne(int l, int c) {
        int oldL = lignes;
        int oldC = colonnes;
        if ((oldL <= l) || (oldC <= c)) {
            int newL = ajuste(oldL, l);
            int newC = ajuste(oldC, c);
            int [][] newTab = new int[newL][newC];
            for (int i=0; i<oldL; i++)
                for (int j=0; j<oldC; j++) {
                    newTab[i][j] = grille[i][j];
                    lignes = i;
                    colonnes = j;
                }
            grille = newTab;


        }
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
