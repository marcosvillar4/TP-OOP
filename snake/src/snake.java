import classes.getKeys;
import classes.posUpdate;

import javax.swing.*;
import java.util.concurrent.atomic.AtomicInteger;

public class snake extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    protected JTable table1;

    AtomicInteger dir;

    public snake() {
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);

        buttonOK.addActionListener(_ -> {

            dir = new AtomicInteger();
            dir.set(2);

            Thread posUpdate = new Thread(new posUpdate(table1, dir));
            Thread getKeys = new Thread(new getKeys(dir, contentPane));

            posUpdate.start();
            getKeys.start();

        });




    }



    public static void main(String[] args) {
        snake dialog = new snake();
        dialog.pack();
        dialog.setVisible(true);
        System.exit(0);}


    private void createUIComponents() {
        table1 = new JTable(32,32);
    }
}

