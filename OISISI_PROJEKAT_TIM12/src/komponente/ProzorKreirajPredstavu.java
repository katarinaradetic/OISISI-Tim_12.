package komponente;

import aplikacija.Singleton;
import kontroler.PredstavaKontroler;
import model.Predstava;
import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;
import utils.DateLabelFormatter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;
import java.util.Date;
import java.util.Properties;

public class ProzorKreirajPredstavu extends JPanel {

    private JLabel lblNaziv;
    private JTextField tfNaziv;
    private JLabel lblOpis;
    private JTextArea taOpis;
    private JLabel lblDatum;
    private JDatePickerImpl dpDatum;
    private JLabel lblVreme;
    private JSpinner jsSati;
    private JSpinner jsMinuti;
    private JLabel lblCenaKarte;
    private JSpinner jsCenaKarte;
    private JButton btnKreirajPredstavu;

    public ProzorKreirajPredstavu() {
        lblNaziv = new JLabel("Naziv: ");
        tfNaziv = new JTextField(30);

        lblOpis = new JLabel("Opis: ");
        taOpis = new JTextArea(5, 30);

        lblDatum = new JLabel("Datum: ");
        UtilDateModel model = new UtilDateModel();
        Date d = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(d);
        model.setDate(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH));
        model.setSelected(true);
        Properties p = new Properties();
        p.put("text.today", "Danas");
        p.put("text.month", "Mesec");
        p.put("text.year", "Godina");
        JDatePanelImpl datePanel = new JDatePanelImpl(model, p);
        dpDatum = new JDatePickerImpl(datePanel, new DateLabelFormatter());

        lblVreme = new JLabel("Vreme: ");
        SpinnerModel sati = new SpinnerNumberModel(0, 0, 23, 1);
        jsSati = new JSpinner(sati);
        SpinnerModel minuti = new SpinnerNumberModel(0, 0, 59, 1);
        jsMinuti = new JSpinner(minuti);

        lblCenaKarte = new JLabel("Cena karte: ");
        SpinnerModel cena = new SpinnerNumberModel(0.00f, 0.00f, 1000000000f, 0.01f);
        jsCenaKarte = new JSpinner(cena);

        btnKreirajPredstavu = new JButton("Kreiraj");

        btnKreirajPredstavu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Predstava predstava = validacija();
                if (predstava != null) {
                    PredstavaKontroler predstavaKontroler = Singleton.getInstance().getPredstavaKontroler();
                    predstavaKontroler.kreirajPredstavu(predstava);
                    ocistiPolja();
                }
            }
        });

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
        add(lblDatum, new GridBagConstraints(0, 2, 1, 1, 0, 0,
                GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(5, 5, 5,5 ),
                0, 0));
        add(dpDatum, new GridBagConstraints(1, 2, 2, 1, 0, 0,
                GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL,
                new Insets(5, 5, 5,5 ), 0, 0));
        add(lblVreme, new GridBagConstraints(0, 3, 1, 1, 0, 0,
                GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(5, 5, 5,5 ),
                0, 0));
        add(jsSati, new GridBagConstraints(1, 3, 1, 1, 0, 0,
                GridBagConstraints.EAST, GridBagConstraints.NONE,
                new Insets(5, 5, 5,5 ), 0, 0));
        add(jsMinuti, new GridBagConstraints(2, 3, 1, 1, 0, 0,
                GridBagConstraints.WEST, GridBagConstraints.NONE,
                new Insets(5, 5, 5,5 ), 0, 0));
        add(lblCenaKarte, new GridBagConstraints(0, 4, 1, 1, 0, 0,
                GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(5, 5, 5,5 ),
                0, 0));
        add(jsCenaKarte, new GridBagConstraints(1, 4, 2, 1, 0, 0,
                GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL,
                new Insets(5, 5, 5,5 ), 0, 0));
        add(btnKreirajPredstavu, new GridBagConstraints(2, 5, 1, 1, 0,
                0, GridBagConstraints.EAST, GridBagConstraints.NONE,
                new Insets(20, 5, 5,5 ), 0, 0));
    }

    private Predstava validacija() {
        String naziv = this.tfNaziv.getText();
        String opis = this.taOpis.getText();
        Float cenaKarte = (float)((double) this.jsCenaKarte.getValue());

        Integer sati = (Integer) this.jsSati.getValue();
        Integer minuti = (Integer) this.jsMinuti.getValue();
        Calendar datum = Calendar.getInstance();
        Date d = (Date) this.dpDatum.getModel().getValue();
        datum.setTime(d);
        Calendar datumVreme = Calendar.getInstance();
        datumVreme.set(datum.get(Calendar.YEAR), datum.get(Calendar.MONTH), datum.get(Calendar.DAY_OF_MONTH), sati, minuti);
        Date danasnjiDatumVreme = new Date();
        Date dat = datumVreme.getTime();

        if (naziv.equals("")) {
            JOptionPane.showMessageDialog(null, "Naziv predstave je obavezan!",
                    Singleton.APP_NAME, JOptionPane.WARNING_MESSAGE);
            return null;
        } else if (opis.equals("")) {
            JOptionPane.showMessageDialog(null, "Opis predstave je obavezan!",
                    Singleton.APP_NAME, JOptionPane.WARNING_MESSAGE);
            return null;
        } else if (!dat.after(danasnjiDatumVreme)) {
            JOptionPane.showMessageDialog(null,
                    "Datum prikazivanja predstave mora biti najkasnije tekući datum!",
                    Singleton.APP_NAME, JOptionPane.WARNING_MESSAGE);
            return null;
        }

        return new Predstava(naziv, opis, dat, cenaKarte);
    }

    private void ocistiPolja() {
        this.tfNaziv.setText("");
        this.taOpis.setText("");
        Date d = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(d);
        this.dpDatum.getModel().setDate(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH));
        this.dpDatum.getModel().setSelected(true);
        this.jsSati.setValue(0);
        this.jsMinuti.setValue(0);
        this.jsCenaKarte.setValue(0.00f);
    }
}
