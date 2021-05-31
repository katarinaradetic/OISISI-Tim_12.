package aplikacija;

import kontroler.KorisnikKontroler;
import kontroler.PredstavaKontroler;
import model.Korisnik;
import komponente.Ruter;
import model.Predstava;
import model.TipKorisnika;

import java.util.HashMap;
import java.util.Map;

public class Theatr {

    public static void main(String[] args) {
        //TODO: ucitavanje entiteta iz fajlova

        Korisnik korisnik1 = new Korisnik("mend", "1234", "Milos", "Nestorovic");
        korisnik1.setTipKorisnika(TipKorisnika.ADMINISTRATOR);
        Korisnik korisnik2 = new Korisnik("mogli", "1234", "Katarina", "Radetic");

        Map<String, Korisnik> korisnici = new HashMap<>();
        korisnici.put(korisnik1.getKorisnickoIme(), korisnik1);
        korisnici.put(korisnik2.getKorisnickoIme(), korisnik2);

        Map<Long, Predstava> predstave = new HashMap<>();

        Singleton.getInstance().setKorisnici(korisnici);
        Singleton.getInstance().setPredstave(predstave);

        Ruter ruter = new Ruter();
        Singleton.getInstance().setRuter(ruter);

        KorisnikKontroler korisnikKontroler = new KorisnikKontroler();
        Singleton.getInstance().setKorisnikKontroler(korisnikKontroler);
        PredstavaKontroler predstavaKontroler = new PredstavaKontroler();
        Singleton.getInstance().setPredstavaKontroler(predstavaKontroler);

        ruter.setVisible(true);

    }
}
