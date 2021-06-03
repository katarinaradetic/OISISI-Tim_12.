package model;

import java.util.Date;

public class Pretraga {

    private String naziv;
    private Date pocetniDatum;
    private Date krajnjiDatum;
    private Float donjaCena;
    private Float gornjaCena;

    public Pretraga() {

    }

    public Pretraga(String naziv, Date pocetniDatum, Date krajnjiDatum, Float donjaCena, Float gornjaCena) {
        this.naziv = naziv;
        this.pocetniDatum = pocetniDatum;
        this.krajnjiDatum = krajnjiDatum;
        this.donjaCena = donjaCena;
        this.gornjaCena = gornjaCena;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public Date getPocetniDatum() {
        return pocetniDatum;
    }

    public void setPocetniDatum(Date pocetniDatum) {
        this.pocetniDatum = pocetniDatum;
    }

    public Date getKrajnjiDatum() {
        return krajnjiDatum;
    }

    public void setKrajnjiDatum(Date krajnjiDatum) {
        this.krajnjiDatum = krajnjiDatum;
    }

    public Float getDonjaCena() {
        return donjaCena;
    }

    public void setDonjaCena(Float donjaCena) {
        this.donjaCena = donjaCena;
    }

    public Float getGornjaCena() {
        return gornjaCena;
    }

    public void setGornjaCena(Float gornjaCena) {
        this.gornjaCena = gornjaCena;
    }
}
