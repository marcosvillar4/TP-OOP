import classes.getKeys;
import classes.posUpdate;
import classes.uiUpdate;

import javax.swing.*;
import java.awt.event.MouseListener;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

public class snake extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    protected JTable table1;
    private JLabel Score;

    AtomicInteger dir;
    AtomicInteger len;
    AtomicBoolean colision;

    public snake() {
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);
        setResizable(false);

        for (MouseListener mouseListener : table1.getMouseListeners()) {
            table1.removeMouseListener(mouseListener);
        }


        buttonOK.addActionListener(_ -> {

            dir = new AtomicInteger();
            len = new AtomicInteger();
            colision = new AtomicBoolean(false);
            dir.set(2);
            len.set(0);

            Thread posUpdate = new Thread(new posUpdate(table1, dir, len, Score));
            Thread getKeys = new Thread(new getKeys(dir, contentPane));
            //Thread uiUpdate = new Thread(new uiUpdate(Score, len));

            //uiUpdate.start();
            posUpdate.start();
            getKeys.start();


            getKeys.interrupt();
            //uiUpdate.interrupt();

        });




    }



    public static void main(String[] args) {
        snake dialog = new snake();
        dialog.pack();
        dialog.setVisible(true);
        System.exit(0);

    }



    private void createUIComponents() {
        table1 = new JTable(32,32);
    }
}

