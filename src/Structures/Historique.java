package Structures;

public class Historique {
    int[][] historique;
    int taille;

    public Historique(int l, int c){
        taille=0;
        historique=new int[l*c][2];
    }

    public void H_ajouter(int i, int j){
        historique[taille][0]=i;
        historique[taille][1]=j;
        taille=taille+1;
    }
    public void H_supprimer() {
        if (taille > 0) {
            taille = taille - 1;
        }
    }
    public void afficher(){
        for(int i=0;i<taille;i++){
            for(int j=0;j<2;j++){
                System.out.println(historique[i][j]);
            }
        }
    }
}
