package akcije;

import aplikacija.Singleton;
import komponente.ProzorKreirajPredstavu;
import komponente.Ruter;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class KreirajPredstavuAkcija extends AbstractAction {
    @Override
    public void actionPerformed(ActionEvent e) {
        Ruter ruter = Singleton.getInstance().getRuter();
        ruter.promeniProzor(ProzorKreirajPredstavu.class);
    }
}
