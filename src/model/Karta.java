package model;

import java.io.Serializable;

public class Karta implements Serializable {

    private static final long serialVersionUID = -555590659442080922L;

    private Long sifra;
    private Predstava predstava;
    private Korisnik korisnik;
    private Integer sediste;
    private Float cena;
}
