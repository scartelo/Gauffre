package Structures;

public class Historique {
    int[][] historique;
    int taille;
    int taille_max;

    public Historique(int l, int c){
        taille=0;
        taille_max=0;
        historique=new int[l*c][2];
    }

    public void H_ajouter(int i, int j){
        historique[taille][0]=i;
        historique[taille][1]=j;
        taille=taille+1;
        if(taille>taille_max){
            taille_max=taille;
        }
    }

    public void H_supprimer() {
        if (taille > 0) {
            taille = taille - 1;
        }
    }
    public void afficher(){
        for(int i=0;i<taille;i++){
            System.out.print(historique[i][0] + " ");
            System.out.println(historique[i][1]);
        }
    }

    public int get_i(int i){
        return historique[i][0];
    }
    public int get_j(int i){
        return historique[i][1];
    }
    public void inc_taille(){
        taille++;
    }
    public void dec_taille(){
        if(taille>0) {
            taille=taille-1;
        }
    }

    public int getTaille(){
        return taille;
    }
    public int getTailleMax(){
        return taille_max;
    }
}
