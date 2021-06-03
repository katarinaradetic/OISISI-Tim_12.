package aplikacija;

import kontroler.KorisnikKontroler;
import kontroler.PredstavaKontroler;
import model.Korisnik;
import komponente.Ruter;
import model.Predstava;
import model.TipKorisnika;
import utils.ReadWriteFile;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class Theatr {

    public static void main(String[] args) {

//        Korisnik korisnik1 = new Korisnik("teodora", "1234", "Teodora", "Jovanović");
//        Korisnik korisnik2 = new Korisnik("mogli", "1234", "Katarina", "Radetić");
//        Korisnik korisnik3 = new Korisnik("mend", "1234", "Miloš", "Nestorović");
//        korisnik2.setTipKorisnika(TipKorisnika.ADMINISTRATOR);
//
//        Map<String, Korisnik> korisnici = new HashMap<>();
//        korisnici.put(korisnik1.getKorisnickoIme(), korisnik1);
//        korisnici.put(korisnik2.getKorisnickoIme(), korisnik2);
//        korisnici.put(korisnik3.getKorisnickoIme(), korisnik3);
//
//        Predstava predstava1 = new Predstava("Mali princ", "Neki opis", new Date(), new Float(322));
//        predstava1.setSifra(new Long(0));
//        Predstava predstava2 = new Predstava("Pokondirana tikva", "Neki opis", new Date(1622908800000l),
//                new Float(420));
//        predstava2.setSifra(new Long(1));
//
//        Map<Long, Predstava> predstave = new HashMap<>();
//        predstave.put(predstava1.getSifra(), predstava1);
//        predstave.put(predstava2.getSifra(), predstava2);

        ReadWriteFile rw = new ReadWriteFile();
        Map<String, Korisnik> korisnici = (Map<String, Korisnik>) rw.readFile("src/podaci/korisnici.txt");
        Map<Long, Predstava> predstave = (Map<Long, Predstava>) rw.readFile("src/podaci/predstave.txt");

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
