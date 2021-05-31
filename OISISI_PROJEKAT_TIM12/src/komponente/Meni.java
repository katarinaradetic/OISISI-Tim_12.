package komponente;

import akcije.ExitAkcija;
import akcije.KreirajPredstavuAkcija;
import model.TipKorisnika;

import javax.swing.*;

public class Meni extends JMenuBar {

    private JMenu meni;
    private JMenuItem miExit;
    private JMenu predstaveMeni;
    private JMenuItem miKreirajPredstavu;

    public Meni() {
        meni = new JMenu("Meni");
        miExit = new JMenuItem(new ExitAkcija());
        miExit.setText("Izađi");
        meni.add(miExit);

        predstaveMeni = new JMenu("Predstave");
        miKreirajPredstavu = new JMenuItem(new KreirajPredstavuAkcija());
        miKreirajPredstavu.setText("Kreiraj predstavu");
        predstaveMeni.add(miKreirajPredstavu);

        add(meni);
        add(predstaveMeni);
    }

    public void podesiVidljivost(TipKorisnika tipKorisnika) {
        switch (tipKorisnika) {
            case KORISNIK:
                this.miExit.setVisible(true);
                this.miKreirajPredstavu.setVisible(false);
                break;
            case ADMINISTRATOR:
                this.miExit.setVisible(true);
                this.miKreirajPredstavu.setVisible(true);
                break;
        }

    }
}
