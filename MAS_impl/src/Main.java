import util.Init;
import frontend.LoginWindow;
import util.Ext;

import javax.swing.*;
import java.io.File;

public class Main {
    public static void main(String[] args) {
        File plik = new File("ExtSave.pls");

        if (plik.exists()) {
            Ext.load();
            System.out.println("Rozpoczynamy");
        } else {
            System.out.println("Tworzymy");
            Init.init();
        }

        SwingUtilities.invokeLater(LoginWindow::new);
    }
}
