package kontroler;

import aplikacija.Singleton;
import komponente.ProzorPredstave;
import komponente.ProzorPrijava;
import komponente.Ruter;
import model.Korisnik;

import java.util.Map;

public class KorisnikKontroler {

    private Map<String, Korisnik> korisnici;

    public KorisnikKontroler(){
        korisnici = Singleton.getInstance().getKorisnici();
    }

    public Boolean korisnikPostoji(Korisnik korisnik) {
        Korisnik k = this.korisnici.get(korisnik.getKorisnickoIme());
        if (k == null) {
            return Boolean.FALSE;
        }

        return Boolean.TRUE;
    }

    public void ulogujKorisnika(Korisnik korisnik) {
        Korisnik k = this.korisnici.get(korisnik.getKorisnickoIme());
        if (k == null) {
            System.out.println("\nKorisnik ne postoji!");
        }
        if (k.getLozinka().equals(korisnik.getLozinka())) {
            Singleton.getInstance().setUlogovanKorisnik(k);
            Ruter ruter = Singleton.getInstance().getRuter();
            ruter.getMeni().setVisible(true);
            ruter.getMeni().podesiVidljivost(k.getTipKorisnika());
            ruter.promeniProzor(ProzorPredstave.class);
            System.out.println("\nUlogovan korisnik: " + k);
        }
    }

    public void registrujKorisnika(Korisnik korisnik) {
        this.korisnici.put(korisnik.getKorisnickoIme(), korisnik);
        Ruter ruter = Singleton.getInstance().getRuter();
        ruter.promeniProzor(ProzorPrijava.class);
        System.out.println("\nRegistrovan korisnik: " + korisnik);
    }
}
