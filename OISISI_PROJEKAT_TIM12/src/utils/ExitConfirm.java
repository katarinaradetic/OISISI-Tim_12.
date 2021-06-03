package utils;

import aplikacija.Singleton;

import javax.swing.*;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

public class ExitConfirm implements WindowListener {
    @Override
    public void windowOpened(WindowEvent e) {

    }

    @Override
    public void windowClosing(WindowEvent e) {
        JFrame frame = (JFrame) e.getComponent();
        int code = JOptionPane.showConfirmDialog(frame, "Da li ste sigurni da želite da zatvorite aplikaciju?",
                "Zatvaranje Aplikacije", JOptionPane.YES_NO_OPTION);

        if (code != JOptionPane.YES_OPTION) {
            frame.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        } else {
            ReadWriteFile rw = new ReadWriteFile();
            rw.writeFile("src/podaci/korisnici.txt", Singleton.getInstance().getKorisnici());
            rw.writeFile("src/podaci/predstave.txt", Singleton.getInstance().getPredstave());

            frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        }
    }

    @Override
    public void windowClosed(WindowEvent e) {

    }

    @Override
    public void windowIconified(WindowEvent e) {

    }

    @Override
    public void windowDeiconified(WindowEvent e) {

    }

    @Override
    public void windowActivated(WindowEvent e) {

    }

    @Override
    public void windowDeactivated(WindowEvent e) {

    }
}
