package utils;

import java.io.*;

public class ReadWriteFile {

    public ReadWriteFile() {

    }

    public void writeFile(String pathName, Object object) {
        try {
            FileOutputStream f = new FileOutputStream(new File(pathName));
            ObjectOutputStream o = new ObjectOutputStream(f);

            o.writeObject(object);

            o.close();
            f.close();
        } catch (FileNotFoundException fnfe) {
            fnfe.printStackTrace();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

    public Object readFile(String pathName) {
        Object object = null;
        try {
            FileInputStream f = new FileInputStream(new File(pathName));
            ObjectInputStream o = new ObjectInputStream(f);

            object = o.readObject();

            o.close();
            f.close();
        } catch (FileNotFoundException fnfe) {
            fnfe.printStackTrace();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        } catch (ClassNotFoundException cnfe) {
            cnfe.printStackTrace();
        }

        return object;
    }
}
