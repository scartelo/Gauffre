package Modele.Arbitre;

public class Jeu {
    int [][] grille;
    int tour;
    int lignes, colonnes;


    Jeu(int l, int c){
        lignes = l;
        colonnes = c;
        tour = 0;
        grille = new int[lignes][colonnes];
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
    public int tour(){
        return tour;
    }
    public boolean estAccessible(int l, int c){
        return (l>=0 && l<lignes && c>=0 && c<colonnes);
    }

    public boolean estJouable(int l, int c){
        return estAccessible(l,c) && contenu(l,c) != 0;
    }
    public void joue(int l, int c){
            for (int i=l; i<lignes ; i++){
                for (int j=c; j<colonnes ; j++){
                    if (estJouable(i,j)) {
                        grille[i][j] = 0;
                        tour = (tour + 1) % 2;
                    }
                }
            }
    }
    public boolean estTermine(){
        for (int i=0; i<lignes; i++){
            for (int j=0; j<colonnes; j++){
                if ( grille[0][0] == -1 && grille[i][j] == 1){
                        return false;
                }
            }
        }
        return true;
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
}
