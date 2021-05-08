package Vue;

import Controleur.ControleurMediateur;
import Modele.Arbitre.Jeu;
import Patterns.Observateur;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class InterfaceGraphique implements Runnable, InterfaceUtilisateur, Observateur {
    Jeu jeu;
    String input;
    int tour;
    JLabel label_joueur1,label_joueur2;
    JButton annuler, refaire,restart,sauvegarde,loadbut;
    JeuGraphique jeuGraphique;
    JTextField jt;
    CollecteurEvenements controle;
    private boolean maximized;
    private JFrame frame;

    public InterfaceGraphique(Jeu j, CollecteurEvenements c){
        label_joueur1=createLabel("X  Joueur 1");
        label_joueur2=createLabel("Joueur 2");

        //Boite dialogue load
        jt=new JTextField("");
        jt.setSize(new Dimension(20,3));
        jt.setMinimumSize(new Dimension(20,3));
        jt.setMaximumSize(new Dimension(20,3));

        controle = c;
        jeu = j;
        jeu.ajouteObservateur(this);
        jeuGraphique = new JeuGraphique(controle, jeu);
    }
    public static void demarrer(Jeu jeu, CollecteurEvenements controle){
        SwingUtilities.invokeLater(new InterfaceGraphique(jeu, controle));
    }
    private JLabel createLabel(String s) {
        JLabel lab = new JLabel(s);
        lab.setAlignmentX(Component.CENTER_ALIGNMENT);
        return lab;
    }
    private JButton createButton(String s, String c) {
        JButton but = new JButton(s);
        but.addActionListener(new AdaptateurCommande(controle, c));
        but.setAlignmentX(Component.CENTER_ALIGNMENT);
        but.setFocusable(false);
        return but;
    }
    @Override
    public void run() {
        frame = new JFrame("Gauffre empoisonnée");

        jeuGraphique.addMouseListener(new EcouteurDeSouris(jeuGraphique, controle));
        Box principal=Box.createHorizontalBox();

        Box boxJeu= Box.createVerticalBox();
        boxJeu.add(jeuGraphique);
        boxJeu.setAlignmentY(Component.CENTER_ALIGNMENT);
        boxJeu.setPreferredSize(new Dimension(500,400));
        principal.add(boxJeu);
        frame.add(principal);


        Box barreLaterale= Box.createVerticalBox();

        barreLaterale.add(createLabel("Gaufre"));
        barreLaterale.add(Box.createGlue());

        barreLaterale.add(label_joueur1);
        barreLaterale.add(label_joueur2);
        barreLaterale.add(Box.createVerticalGlue());
        // Annuler / Refaire
        Box annulRef = Box.createHorizontalBox();
        annuler = createButton("<", "annule");

        refaire = createButton(">", "refaire");

        annulRef.add(annuler);
        annulRef.add(refaire);
        barreLaterale.add(annulRef);

        barreLaterale.add(Box.createGlue());

        sauvegarde = createButton("Save","save");
        barreLaterale.add(sauvegarde);
        barreLaterale.add(Box.createGlue());




        jt.setPreferredSize(new Dimension(40,30));
        jt.setMinimumSize(new Dimension(40,30));
        jt.setMaximumSize(new Dimension(40,30));


        jt.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                String input = jt.getText();
                controle.commandeInput("load",input);
            }
        });


        barreLaterale.add(jt);

        barreLaterale.add(Box.createGlue());

        barreLaterale.add(Box.createGlue());
        restart = createButton("Recommencer", "restart");

        barreLaterale.add(restart);

        barreLaterale.setAlignmentX(Component.CENTER_ALIGNMENT);

        principal.add(barreLaterale);




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
        tour=jeu.tour();
        if(tour==0){
            label_joueur2.setText("Joueur 2 ");
            label_joueur1.setText("X  Joueur 1 ");
        }
        else{
            label_joueur1.setText("Joueur 1 ");
            label_joueur2.setText("X  Joueur 2");
        }
        jeuGraphique.repaint();
    }
}
