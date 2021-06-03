package kontroler;

import aplikacija.Singleton;
import komponente.ProzorPredstavaDetaljanPrikaz;
import komponente.ProzorPredstave;
import komponente.Ruter;
import model.Predstava;
import model.Pretraga;

import java.util.*;
import java.util.stream.Collectors;

public class PredstavaKontroler {

    private Map<Long, Predstava> predstave;

    public PredstavaKontroler() {
        predstave = Singleton.getInstance().getPredstave();
    }

    public void kreirajPredstavu(Predstava predstava) {
        Set<Long> kljucevi = this.predstave.keySet();
        Long sifra = (long)(kljucevi.size()+1);
        predstava.setSifra(sifra);
        this.predstave.put(sifra, predstava);
        Ruter ruter = Singleton.getInstance().getRuter();
        ruter.osveziProzor(ProzorPredstave.class);
        ruter.promeniProzor(ProzorPredstave.class);
        System.out.println("\nKreirana predstava: " + predstava);
    }

    public void prikaziDetaljno(Predstava predstava) {
        Singleton.getInstance().setDetaljnoPrikazanaPredstava(predstava);
        Ruter ruter = Singleton.getInstance().getRuter();
        ruter.osveziProzor(ProzorPredstavaDetaljanPrikaz.class);
        ruter.promeniProzor(ProzorPredstavaDetaljanPrikaz.class);
    }



    public List<Predstava> pretraziPredstave(Pretraga pretraga) {
        Set<Predstava> rezultat = new HashSet<>();
        Set<Predstava> setNaziv = new HashSet<>();
        Set<Predstava> setCena = new HashSet<>();
        Set<Predstava> setDatum = new HashSet<>();
        boolean filterNaziv = false;
        boolean filterCena = false;
        boolean filterDatum = false;

        if (!pretraga.getNaziv().equals("")) {
            filterNaziv = true;
            this.predstave.values().forEach(predstava -> {
                if (predstava.getNaziv().toLowerCase().contains(pretraga.getNaziv().toLowerCase()))
                    setNaziv.add(predstava);
            });
        }
        if (pretraga.getGornjaCena() > pretraga.getDonjaCena()) {
            filterCena = true;
            this.predstave.values().forEach(predstava -> {
                if (predstava.getCena() < pretraga.getGornjaCena() && predstava.getCena() > pretraga.getDonjaCena())
                    setCena.add(predstava);
            });
        }
        if (pretraga.getKrajnjiDatum().after(pretraga.getPocetniDatum())) {
            filterDatum = true;
            this.predstave.values().forEach(predstava -> {
                if (predstava.getDatumVreme().after(pretraga.getPocetniDatum()) &&
                        pretraga.getKrajnjiDatum().after(predstava.getDatumVreme()))
                    setDatum.add(predstava);
            });
        }

        rezultat.addAll(setNaziv);
        rezultat.addAll(setCena);
        rezultat.addAll(setDatum);
        if (filterNaziv)
            rezultat.retainAll(setNaziv);
        if (filterCena)
            rezultat.retainAll(setCena);
        if (filterDatum)
            rezultat.retainAll(setDatum);

        return rezultat.stream().collect(Collectors.toList());
    }
}
