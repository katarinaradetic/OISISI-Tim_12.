package aplikacija;

import kontroler.KorisnikKontroler;
import kontroler.PredstavaKontroler;
import model.Karta;
import model.Korisnik;
import model.Predstava;
import komponente.Ruter;

import java.util.Map;

public class Singleton {

    public static final String APP_NAME = "Theatr";
    private static Singleton instance = new Singleton();

    private Ruter ruter;
    private KorisnikKontroler korisnikKontroler;
    private PredstavaKontroler predstavaKontroler;

    private Map<String, Korisnik> korisnici;
    private Map<Long, Predstava> predstave;
    private Map<Long, Karta> karte;

    private Korisnik ulogovanKorisnik;


    private Singleton() {

    }

    public static Singleton getInstance() {
        return instance;
    }

    public Ruter getRuter() {
        return ruter;
    }

    public void setRuter(Ruter ruter) {
        this.ruter = ruter;
    }

    public Korisnik getUlogovanKorisnik() {
        return ulogovanKorisnik;
    }

    public void setUlogovanKorisnik(Korisnik ulogovanKorisnik) {
        this.ulogovanKorisnik = ulogovanKorisnik;
    }

    public Map<String, Korisnik> getKorisnici() {
        return korisnici;
    }

    public void setKorisnici(Map<String, Korisnik> korisnici) {
        this.korisnici = korisnici;
    }

    public KorisnikKontroler getKorisnikKontroler() {
        return korisnikKontroler;
    }

    public void setKorisnikKontroler(KorisnikKontroler korisnikKontroler) {
        this.korisnikKontroler = korisnikKontroler;
    }

    public Map<Long, Predstava> getPredstave() {
        return predstave;
    }

    public void setPredstave(Map<Long, Predstava> predstave) {
        this.predstave = predstave;
    }

    public PredstavaKontroler getPredstavaKontroler() {
        return predstavaKontroler;
    }

    public void setPredstavaKontroler(PredstavaKontroler predstavaKontroler) {
        this.predstavaKontroler = predstavaKontroler;
    }
}
