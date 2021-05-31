package komponente;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class Ruter extends JFrame {

    private Meni meni;

    private JPanel displayPanel;

    private Map<Class<?>, Object> prozori;

    public Ruter() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        inicijalizujProzore();
        inicijalizujGUI();
    }

    public void inicijalizujGUI() {
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Dimension screensize = toolkit.getScreenSize();
        int screenWidth = screensize.width;
        int screenHeight = screensize.height;
        setSize(screenWidth/2, screenHeight/2);
        setTitle("Theatr");

        setLocationRelativeTo(null);

        displayPanel = new JPanel();
        add(displayPanel);
        promeniProzor(ProzorPrijava.class);

        meni = new Meni();
        this.setJMenuBar(meni);
        this.meni.setVisible(false);
    }

    public void inicijalizujProzore() {
        ProzorPrijava prozorPrijava = new ProzorPrijava();
        ProzorRegistracija prozorRegistracija = new ProzorRegistracija();
        ProzorPredstave prozorPredstave = new ProzorPredstave();
        ProzorKreirajPredstavu prozorKreirajPredstavu = new ProzorKreirajPredstavu();

        this.prozori = new HashMap<>();
        this.prozori.put(ProzorPrijava.class, prozorPrijava);
        this.prozori.put(ProzorRegistracija.class, prozorRegistracija);
        this.prozori.put(ProzorPredstave.class, prozorPredstave);
        this.prozori.put(ProzorKreirajPredstavu.class, prozorKreirajPredstavu);
    }

    public void promeniProzor(Class<?> prozor) {
        this.displayPanel.removeAll();
        this.displayPanel.add((JPanel) this.prozori.get(prozor));
        this.displayPanel.revalidate();
        this.displayPanel.repaint();
    }

    public Meni getMeni() {
        return meni;
    }
}
