package komponente;

import aplikacija.Singleton;
import kontroler.KorisnikKontroler;
import model.Korisnik;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ProzorRegistracija extends JPanel {

    private JLabel lblKorisnickoIme;
    private JTextField tfKorisnickoIme;
    private JLabel lblLozinka;
    private JPasswordField tfLozinka;
    private JLabel lblIme;
    private JTextField tfIme;
    private JLabel lblPrezime;
    private JTextField tfPrezime;
    private JLabel lblPrijava;
    private JButton btnPrijava;
    private JButton btnRegistracija;

    public ProzorRegistracija() {

        lblKorisnickoIme = new JLabel("Korisničko ime: ");
        tfKorisnickoIme = new JTextField(30);

        lblLozinka = new JLabel("Lozinka");
        tfLozinka = new JPasswordField(30);
        tfLozinka.setEchoChar('*');

        lblIme = new JLabel("Ime");
        tfIme = new JTextField(30);

        lblPrezime = new JLabel("Prezime");
        tfPrezime = new JTextField(30);

        lblPrijava = new JLabel("Ukoliko već imate nalog");
        btnPrijava = new JButton("Prijavite se");
        btnRegistracija = new JButton("Registruj se");

        btnPrijava.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Ruter ruter = Singleton.getInstance().getRuter();
                ruter.promeniProzor(ProzorPrijava.class);
            }
        });

        btnRegistracija.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Korisnik korisnik = validacija();

                if (korisnik != null) {
                    KorisnikKontroler korisnikKontroler = Singleton.getInstance().getKorisnikKontroler();
                    if (korisnikKontroler.korisnikPostoji(korisnik)) {
                        JOptionPane.showMessageDialog(null,
                                "Korisnik sa korisničkim imenom već postoji!",
                                Singleton.APP_NAME, JOptionPane.WARNING_MESSAGE);
                    } else {
                        korisnikKontroler.registrujKorisnika(korisnik);
                        ocistiPolja();
                    }
                }
            }
        });

        setLayout(new GridBagLayout());
        add(lblKorisnickoIme, new GridBagConstraints(0, 0, 1, 1, 0, 0,
                GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(50, 5, 5,5 ),
                0, 0));
        add(tfKorisnickoIme, new GridBagConstraints(1, 0, 2, 1, 0, 0,
                GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL,
                new Insets(50, 5, 5,5 ), 0, 0));
        add(lblLozinka, new GridBagConstraints(0, 1, 1, 1, 0, 0,
                GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(5, 5, 5,5 ),
                0, 0));
        add(tfLozinka, new GridBagConstraints(1, 1, 2, 1, 0, 0,
                GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL,
                new Insets(5, 5, 5,5 ), 0, 0));
        add(lblIme, new GridBagConstraints(0, 2, 1, 1, 0, 0,
                GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(5, 5, 5,5 ),
                0, 0));
        add(tfIme, new GridBagConstraints(1, 2, 2, 1, 0, 0,
                GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL,
                new Insets(5, 5, 5,5 ), 0, 0));
        add(lblPrezime, new GridBagConstraints(0, 3, 1, 1, 0, 0,
                GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(5, 5, 5,5 ),
                0, 0));
        add(tfPrezime, new GridBagConstraints(1, 3, 2, 1, 0, 0,
                GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL,
                new Insets(5, 5, 5,5 ), 0, 0));
        add(btnRegistracija, new GridBagConstraints(2, 4, 1, 1, 0, 0,
                GridBagConstraints.EAST, GridBagConstraints.NONE,
                new Insets(20, 5, 5,5 ), 0, 0));
        add(lblPrijava, new GridBagConstraints(2, 5, 1, 1, 0, 0,
                GridBagConstraints.CENTER, GridBagConstraints.NONE,
                new Insets(50, 5, 5,5 ), 0, 0));
        add(btnPrijava, new GridBagConstraints(2, 6, 1, 1, 0, 0,
                GridBagConstraints.CENTER, GridBagConstraints.NONE,
                new Insets(5, 5, 5,5 ), 0, 0));
    }

   private Korisnik validacija() {
        String korisnickiIme = tfKorisnickoIme.getText();
        String lozinka = new String(tfLozinka.getPassword());
        String ime =  tfIme.getText().trim();
        String prezime = tfPrezime.getText().trim();
        if(korisnickiIme.trim().equals("")) {
            JOptionPane.showMessageDialog(null, "Korisničko ime je obavezno!",
                    Singleton.APP_NAME, JOptionPane.WARNING_MESSAGE);
            return null;
        } else if (lozinka.trim().equals("")) {
            JOptionPane.showMessageDialog(null, "Lozinka je obavezna!",
                    Singleton.APP_NAME, JOptionPane.WARNING_MESSAGE);
            return null;
        } else if (ime.equals("")) {
            JOptionPane.showMessageDialog(null, "Ime je obavezno!",
                    Singleton.APP_NAME, JOptionPane.WARNING_MESSAGE);
            return null;
        } else if (!ime.matches("[a-zA-ZžŽćĆčČđĐšŠ]")) {
            JOptionPane.showMessageDialog(null, "Ime mora da bude sačinjeno samo od slova!",
                    Singleton.APP_NAME, JOptionPane.WARNING_MESSAGE);
            return null;
        } else if(prezime.equals("")) {
            JOptionPane.showMessageDialog(null, "Prezime je obavezno!",
                    Singleton.APP_NAME, JOptionPane.WARNING_MESSAGE);
            return null;
        } else if (!prezime.matches("[a-zA-ZžŽćĆčČđĐšŠ]")) {
            JOptionPane.showMessageDialog(null,
                    "Prezime mora da bude sačinjeno samo od slova!",
                    Singleton.APP_NAME, JOptionPane.WARNING_MESSAGE);
            return null;
        }
        return new Korisnik(korisnickiIme, lozinka, ime, prezime);
    }

    private void ocistiPolja() {
        this.tfKorisnickoIme.setText("");
        this.tfIme.setText("");
        this.tfPrezime.setText("");
        this.tfLozinka.setText("");
    }

}
