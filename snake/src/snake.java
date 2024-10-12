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

    AtomicBoolean kill = new AtomicBoolean(false);
    AtomicInteger dir;


    //Thread uiUpdate = new Thread(new uiUpdate(Score, len));



    public snake() {
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);
        setResizable(false);

        for (MouseListener mouseListener : table1.getMouseListeners()) {
            table1.removeMouseListener(mouseListener);
        }


        buttonOK.addActionListener(_ -> {

            if (!kill.get()) {
                kill.set(true);
                dir = new AtomicInteger(0);
                dir.set(2);
                Thread posUpdate = new Thread(new posUpdate(table1, dir, Score, kill));
                Thread getKeys = new Thread(new getKeys(dir, contentPane));


                getKeys.start();
                posUpdate.start();

            }



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

