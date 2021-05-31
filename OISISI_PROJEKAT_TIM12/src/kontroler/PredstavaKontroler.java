package kontroler;

import aplikacija.Singleton;
import komponente.ProzorPredstave;
import komponente.Ruter;
import model.Predstava;

import java.util.Map;
import java.util.Set;

public class PredstavaKontroler {

    private Map<Long, Predstava> predstave;

    public PredstavaKontroler() {
        predstave = Singleton.getInstance().getPredstave();
    }

    public void kreirajPredstavu(Predstava predstava) {
        Set<Long> kljucevi = this.predstave.keySet();
        Long sifra = new Long(kljucevi.size()+1);
        predstava.setSifra(sifra);
        this.predstave.put(sifra, predstava);
        Ruter ruter = Singleton.getInstance().getRuter();
        ruter.promeniProzor(ProzorPredstave.class);
        System.out.println("\nKreirana predstava: " + predstava);
    }
}
