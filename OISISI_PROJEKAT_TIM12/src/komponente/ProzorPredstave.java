package komponente;

import aplikacija.Singleton;
import kontroler.PredstavaKontroler;
import model.Predstava;
import model.TipKorisnika;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ProzorPredstavaDetaljanPrikaz extends JPanel {

    private JLabel lblNaziv;
    private JTextField tfNaziv;
    private JLabel lblOpis;
    private JTextArea taOpis;
    private JLabel lblDatumVreme;
    private JTextField tfDatumVreme;
    private JLabel lblCena;
    private JTextField tfCena;
    private JButton btnIzmeni;

    public ProzorPredstavaDetaljanPrikaz() {
        lblNaziv = new JLabel("Naziv: ");
        tfNaziv = new JTextField(30);
        tfNaziv.setEditable(false);

        lblOpis = new JLabel("Opis: ");
        taOpis = new JTextArea(5, 30);
        taOpis.setEditable(false);

        lblDatumVreme = new JLabel("Datum i vreme: ");
        tfDatumVreme = new JTextField(30);
        tfDatumVreme.setEditable(false);

        lblCena = new JLabel("Cena: ");
        tfCena = new JTextField(30);
        tfCena.setEditable(false);

        setLayout(new GridBagLayout());
        add(lblNaziv, new GridBagConstraints(0, 0, 1, 1, 0, 0,
                GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(50, 5, 5,5 ),
                0, 0));
        add(tfNaziv, new GridBagConstraints(1, 0, 2, 1, 0, 0,
                GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL,
                new Insets(50, 5, 5,5 ), 0, 0));
        add(lblOpis, new GridBagConstraints(0, 1, 1, 1, 0, 0,
                GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(5, 5, 5,5 ),
                0, 0));
        add(taOpis, new GridBagConstraints(1, 1, 2, 1, 0, 0,
                GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL,
                new Insets(5, 5, 5,5 ), 0, 0));
        add(lblDatumVreme, new GridBagConstraints(0, 2, 1, 1, 0, 0,
                GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(5, 5, 5,5 ),
                0, 0));
        add(tfDatumVreme, new GridBagConstraints(1, 2, 2, 1, 0, 0,
                GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL,
                new Insets(5, 5, 5,5 ), 0, 0));
        add(lblCena, new GridBagConstraints(0, 3, 1, 1, 0, 0,
                GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(5, 5, 5,5 ),
                0, 0));
        add(tfCena, new GridBagConstraints(1, 3, 2, 1, 0, 0,
                GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL,
                new Insets(5, 5, 5,5 ), 0, 0));

    }

    public void popuniPolja(Predstava predstava) {
        tfNaziv.setText(predstava.getNaziv());
        taOpis.setText(predstava.getOpis());
        tfDatumVreme.setText(predstava.getDatumVreme().toString());
        tfCena.setText(predstava.getCena().toString());

        if (Singleton.getInstance().getUlogovanKorisnik().getTipKorisnika().equals(TipKorisnika.KORISNIK))
            btnIzmeni.setVisible(false);
        else if (Singleton.getInstance().getUlogovanKorisnik().getTipKorisnika().equals(TipKorisnika.ADMINISTRATOR))
            btnIzmeni.setVisible(true);
    }
}
