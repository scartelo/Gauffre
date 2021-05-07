package Vue;

import Modele.Arbitre.Jeu;
import Patterns.Observateur;

import javax.swing.*;
import java.awt.*;

public class JeuGraphique extends JComponent implements Observateur {
    int largeur, hauteur, largeurCase, hauteurCase;
    Graphics2D drawable;
    Jeu jeu;
    CollecteurEvenements controle;
    ImageGauffre poison, gauffre, vide;


    public JeuGraphique(CollecteurEvenements c, Jeu j){
        controle = c;
        jeu = j;
        poison = new ImageGauffre(ImageGauffre.charge("Images/poison.png"));
        gauffre = new ImageGauffre(ImageGauffre.charge("Images/gauffre.png"));
        vide = new ImageGauffre(ImageGauffre.charge("Images/vide.png"));

    }

    @Override
    public void paintComponent(Graphics graphics){
        drawable = (Graphics2D) graphics;
        largeur = getSize().width;
        hauteur = getSize().height;
        largeurCase = largeur / jeu.plateau().colonnes();
        hauteurCase = hauteur / jeu.plateau().lignes();

        largeurCase = hauteurCase = Math.min(largeurCase, hauteurCase);

        drawable.clearRect(0,0, largeur, hauteur);

        tracerGrille();
    }

    void tracerGrille(){
        for (int i = 0; i< jeu.plateau().lignes(); i++){
            for (int j = 0; j < jeu.plateau().colonnes(); j++){
                int x = j * largeurCase;
                int y = i * hauteurCase;
                if (jeu.plateau().aPoison(i,j)){
                    tracerImage(poison, x, y, largeurCase, hauteurCase);
                }else if (jeu.plateau().aGaufre(i,j)){
                    tracerImage(gauffre, x, y, largeurCase, hauteurCase);
                }else{
                    tracerImage(vide,x, y, largeurCase, hauteurCase);
                }
            }
        }
    }

    void tracerImage(ImageGauffre img, int x, int y, int lC, int hC){
        drawable.drawImage(img.image(), x, y, lC, hC, null);
    }
    public int largeurCase(){
        return largeurCase;
    }

    public int hauteurCase(){
        return hauteurCase;
    }

    @Override
    public void metAJour() {
        repaint();
    }
}
