package Modele.Joueur;

public class Coup {
    int l, c;

    public Coup(int l, int c){
        this.l = l;
        this.c = c;
    }

    public int ligne(){
        return l;
    }

    public int colonne(){
        return c;
    }
}
