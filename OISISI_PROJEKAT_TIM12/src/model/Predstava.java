package model;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class Predstava {

    private Long sifra;
    private String naziv;
    private String opis;
    private Date datumVreme;
    private Float cena;
    private Map<Integer, Karta> sedista;
    private Boolean karteRasprodate;

    public Predstava() {

    }

    public Predstava(String naziv, String opis, Date datumVreme, Float cena) {
        this.naziv = naziv;
        this.opis = opis;
        this.datumVreme = datumVreme;
        this.cena = cena;
        this.sedista = new HashMap<>();
        this.karteRasprodate = false;
    }

    public Long getSifra() {
        return sifra;
    }

    public void setSifra(Long sifra) {
        this.sifra = sifra;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public String getOpis() {
        return opis;
    }

    public void setOpis(String opis) {
        this.opis = opis;
    }

    public Date getDatumVreme() {
        return datumVreme;
    }

    public void setDatumVreme(Date datumVreme) {
        this.datumVreme = datumVreme;
    }

    public Float getCena() {
        return cena;
    }

    public void setCena(Float cena) {
        this.cena = cena;
    }

    public Map<Integer, Karta> getSedista() {
        return sedista;
    }

    public void setSedista(Map<Integer, Karta> sedista) {
        this.sedista = sedista;
    }

    public Boolean getKarteRasprodate() {
        return karteRasprodate;
    }

    public void setKarteRasprodate(Boolean karteRasprodate) {
        this.karteRasprodate = karteRasprodate;
    }

    @Override
    public String toString() {
        return "\nSifra: "+this.sifra+"\nNaziv: "+ this.naziv+"\nOpis: "+this.opis+"\nDatum i vreme: "+this.datumVreme+
                "\nCena: "+this.cena+"\nKarte rasprodate: "+this.karteRasprodate;
    }
}
