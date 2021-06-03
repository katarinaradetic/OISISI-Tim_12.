package akcije;

import aplikacija.Singleton;
import komponente.ProzorPredstave;
import komponente.Ruter;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class PrikaziSvePredstaveAkcija extends AbstractAction {

    @Override
    public void actionPerformed(ActionEvent e) {
        Ruter ruter = Singleton.getInstance().getRuter();
        ruter.osveziProzor(ProzorPredstave.class);
        ruter.promeniProzor(ProzorPredstave.class);
    }
}
