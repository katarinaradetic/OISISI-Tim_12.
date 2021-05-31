package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Korisnik implements Serializable {

    private static final long serialVersionUID = -3017504378025844674L;

    private String korisnickoIme;
    private String lozinka;
    private String ime;
    private String prezime;
    private TipKorisnika tipKorisnika;
    private List<Karta> kupljeneKarte;

    public Korisnik() {}

    public Korisnik(String korisnickoIme, String lozinka, String ime, String prezime) {
        this.korisnickoIme = korisnickoIme;
        this.lozinka = lozinka;
        this.ime = ime;
        this.prezime = prezime;
        this.tipKorisnika = TipKorisnika.KORISNIK;
        this.kupljeneKarte = new ArrayList<>();
    }

    public Korisnik(String korisnickoIme, String lozinka) {
        this.korisnickoIme = korisnickoIme;
        this.lozinka = lozinka;
    }

    public String getKorisnickoIme() {
        return korisnickoIme;
    }

    public void setKorisnickoIme(String korisnickoIme) {
        this.korisnickoIme = korisnickoIme;
    }

    public String getLozinka() {
        return lozinka;
    }

    public void setLozinka(String lozinka) {
        this.lozinka = lozinka;
    }

    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public String getPrezime() {
        return prezime;
    }

    public void setPrezime(String prezime) {
        this.prezime = prezime;
    }

    public List<Karta> getKupljeneKarte() {
        return kupljeneKarte;
    }

    public void setKupljeneKarte(List<Karta> kupljeneKarte) {
        this.kupljeneKarte = kupljeneKarte;
    }

    public TipKorisnika getTipKorisnika() {
        return tipKorisnika;
    }

    public void setTipKorisnika(TipKorisnika tipKorisnika) {
        this.tipKorisnika = tipKorisnika;
    }

    @Override
    public String toString() {
        return "\nKorisnicko ime: "+this.korisnickoIme+"\nLozinka: "+this.lozinka+"\nIme: "+this.ime+"\nPrezime: "+
                this.prezime+"\nTip korisnika: "+this.tipKorisnika;
    }
}
