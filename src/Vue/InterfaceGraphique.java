package Vue;

import Modele.Arbitre.Jeu;
import Patterns.Observateur;

import javax.swing.*;
import java.awt.*;

public class InterfaceGraphique implements Runnable, InterfaceUtilisateur, Observateur {
    Jeu jeu;
    JeuGraphique jeuGraphique;
    CollecteurEvenements controle;
    private boolean maximized;
    private JFrame frame;

    public InterfaceGraphique(Jeu j, CollecteurEvenements c){
        controle = c;
        jeu = j;
        jeu.ajouteObservateur(this);
        jeuGraphique = new JeuGraphique(controle, jeu);
    }
    public static void demarrer(Jeu jeu, CollecteurEvenements controle){
        SwingUtilities.invokeLater(new InterfaceGraphique(jeu, controle));
    }

    @Override
    public void run() {
        frame = new JFrame("Gauffre empoisonnée");

        jeuGraphique.addMouseListener(new EcouteurDeSouris(jeuGraphique, controle));

        frame.add(jeuGraphique);

        controle.fixerIU(this);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 300);
        frame.setVisible(true);
    }

    @Override
    public void basculePleinEcran() {
        GraphicsEnvironment env = GraphicsEnvironment.getLocalGraphicsEnvironment();
        GraphicsDevice device = env.getDefaultScreenDevice();
        if (maximized) {
            device.setFullScreenWindow(null);
            maximized = false;
        } else {
            device.setFullScreenWindow(frame);
            maximized = true;
        }
    }

    @Override
    public void metAJour() {
        jeuGraphique.repaint();
    }
}
