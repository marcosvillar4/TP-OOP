import classes.getKeys;
import classes.posUpdate;

import javax.swing.*;
import java.awt.event.*;

public class snake extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    protected JTable table1;

    Integer dir = 2;

    public snake() throws InterruptedException {
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);
        /*


        // call onCancel() when cross is clicked
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                onCancel();
            }
        });

        // call onCancel() on ESCAPE
        contentPane.registerKeyboardAction(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.out.println("AAAAAAA");
            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);

        */

        buttonOK.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {


                Thread posUpdate = new Thread(new posUpdate(table1, dir));
                Thread getKeys = new Thread(new getKeys(dir));

                posUpdate.start();
                getKeys.start();

            }
        });




    }

    private void onOK() throws InterruptedException {

    }

    private void onCancel() {
        // add your code here if necessary
        dispose();
    }

    public static void main(String[] args) throws InterruptedException {
        snake dialog = new snake();
        dialog.pack();
        dialog.setVisible(true);
        System.exit(0);}


    private void createUIComponents() {
        table1 = new JTable(32,32);
    }
}

