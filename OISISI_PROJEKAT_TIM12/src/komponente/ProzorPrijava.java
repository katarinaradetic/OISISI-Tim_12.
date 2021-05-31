package komponente;

import aplikacija.Singleton;
import kontroler.KorisnikKontroler;
import model.Korisnik;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ProzorPrijava extends JPanel {

    private JLabel lblKorisnickoIme;
    private JTextField tfKorisnickoIme;
    private JLabel lblLozinka;
    private JPasswordField tfLozinka;
    private JButton btnPrijava;
    private JLabel lblRegistracija;
    private JButton btnRegistracija;

    public ProzorPrijava() {

        lblKorisnickoIme = new JLabel("Korisničko ime: ");
        tfKorisnickoIme = new JTextField(30);

        lblLozinka = new JLabel("Lozinka");
        tfLozinka = new JPasswordField(30);
        tfLozinka.setEchoChar('*');

        btnPrijava = new JButton("Prijavi se");
        lblRegistracija = new JLabel("Ukoliko još uvek nemate nalog");
        btnRegistracija = new JButton("Registrujte se");

        btnPrijava.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Korisnik korisnik = validacija();
                if (korisnik != null) {
                    KorisnikKontroler korisnikKontroler = Singleton.getInstance().getKorisnikKontroler();
                    if (korisnikKontroler.korisnikPostoji(korisnik)) {
                        korisnikKontroler.ulogujKorisnika(korisnik);
                        ocistiPolja();
                    } else {
                        JOptionPane.showMessageDialog(null,
                                "Korisnik sa unetim korisničkim imenom ne postoji!",
                                Singleton.APP_NAME, JOptionPane.WARNING_MESSAGE);
                    }
                }
            }
        });

        btnRegistracija.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Ruter ruter = Singleton.getInstance().getRuter();
                ruter.promeniProzor(ProzorRegistracija.class);
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
        add(btnPrijava, new GridBagConstraints(2, 2, 1, 1, 0, 0,
                GridBagConstraints.EAST, GridBagConstraints.NONE,
                new Insets(20, 5, 5,5 ), 0, 0));
        add(lblRegistracija, new GridBagConstraints(2, 3, 1, 1, 0, 0,
                GridBagConstraints.CENTER, GridBagConstraints.NONE,
                new Insets(50, 5, 5,5 ), 0, 0));
        add(btnRegistracija, new GridBagConstraints(2, 4, 1, 1, 0, 0,
                GridBagConstraints.CENTER, GridBagConstraints.NONE,
                new Insets(5, 5, 5,5 ), 0, 0));
    }

    private Korisnik validacija() {
        String korisnickoIme = this.tfKorisnickoIme.getText();
        String lozinka = new String(tfLozinka.getPassword());

        if (korisnickoIme.trim().equals("")) {
            JOptionPane.showMessageDialog(null, "Korisničko ime je obavezno!",
                    Singleton.APP_NAME, JOptionPane.WARNING_MESSAGE);
            return null;
        } else if (lozinka.trim().equals("")) {
            JOptionPane.showMessageDialog(null, "Lozinka je obavezna!",
                    Singleton.APP_NAME, JOptionPane.WARNING_MESSAGE);
            return null;
        }

        return new Korisnik(korisnickoIme, lozinka);
    }

    private void ocistiPolja() {
        this.tfKorisnickoIme.setText("");
        this.tfLozinka.setText("");
    }
}
