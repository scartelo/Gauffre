package Vue;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class EcouteurDeSouris extends MouseAdapter {
    CollecteurEvenements controle;
    JeuGraphique jeuGraphique;

    EcouteurDeSouris( JeuGraphique jg, CollecteurEvenements c){
        controle = c;
        jeuGraphique = jg;
    }

    @Override
    public void mousePressed(MouseEvent e){
        int c = e.getX() / jeuGraphique.largeurCase();
        int l = e.getY() / jeuGraphique.hauteurCase();
        controle.clicSouris(l,c);
    }

}
