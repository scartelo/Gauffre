package Structures;
import Modele.Arbitre.Jeu;
import Modele.Arbitre.PlateauDeJeu;

import java.io.*;
import java.util.*;
public class Saves {
    int nb_saves;
    int l;
    int c;
    String[] l_saves;
    Historique historique;
    String path;

    public Saves(){
        final String dir = System.getProperty("user.dir");
        String home = System.getProperty("user.dir");

        path=home + File.separator +"res" +File.separator+"Saves";
        File directory=new File(path);
        nb_saves=directory.list().length;
        l_saves=directory.list();
        l=0;
        c=0;
        historique= new Historique(l,c);

    }
    public void set_save(PlateauDeJeu p){
        historique=p.get_hist();
        l=p.lignes();
        c=p.colonnes();
    }

    public void load_save(Jeu jeu){
        PlateauDeJeu p= new PlateauDeJeu();
        jeu.set_plateau(p);
        for(int ite=0;ite<historique.taille;ite++){
            int i=historique.get_i(ite);
            int j=historique.get_j(ite);
            jeu.joue(i,j);
        }
        p.set_hist(historique);
    }
    public void read_save(int n_save) {
        String save_path = path + File.separator + "save_" + n_save + ".txt";
        File save=new File(save_path);
        try (Scanner myReader = new Scanner(save)) {
            l= myReader.nextInt();
            c=myReader.nextInt();
            Historique H=new Historique(l,c);
            int taille=myReader.nextInt();
            while (myReader.hasNextInt()) {
                int i=myReader.nextInt();
                int j=myReader.nextInt();
                H.H_ajouter(i,j);
                }
            H.taille=taille;
            historique=H;
        } catch(FileNotFoundException e){
            e.printStackTrace();
        }
    }


    public void write_save() {
        int new_save = nb_saves + 1;
        String save_path = path + File.separator + "save_" + new_save + ".txt";
        File f = new File(save_path);
        try {
            f.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            int taille_H=historique.getTaille();
            int taille_max_H=historique.getTailleMax();
            FileWriter myWriter = new FileWriter(save_path);
            myWriter.write(l+"\n");
            myWriter.write(c+"\n");
            myWriter.write(taille_H+"\n");
            for(int ite=0;ite<taille_max_H;ite++){
                int i=historique.get_i(ite);
                int j=historique.get_j(ite);
                myWriter.write(i+" "+j+"\n");
            }
            myWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public int get_nbsaves(){
        return nb_saves;
    }
    public String[] get_saves(){
        return l_saves;
    }
    public List<Integer> get_n_saves(){
        List<Integer> l=new ArrayList<>();
        for(int i=0;i<l_saves.length;i++){
            String[] splitted_file = l_saves[i].split("_");
            String file=splitted_file[1];
            splitted_file=file.split("\\.");
            file=splitted_file[0];
            int r=Integer.parseInt(file);
            l.add(r);
        }
        return l;
    }
    public void afficher_save(){
        System.out.println("Voici la sauvegarde:");
        System.out.println(l);
        System.out.println(c);
        System.out.println("Voici l'historique:");
        historique.afficher();
    }
}
