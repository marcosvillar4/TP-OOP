import javax.swing.*;
import java.awt.event.*;
import java.util.LinkedList;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class snake extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JTable table1;

    class coord{
        int x;
        int y;

    }


    int row = 0;
    int column = 0;




    public snake() throws InterruptedException {
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);

        buttonOK.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {

                    while (true){
                        onOK();
                        TimeUnit.MILLISECONDS.sleep(50);
                        //update(getGraphics());
                        update(getGraphics());

                    }
                } catch (InterruptedException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });

        buttonCancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        });

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
                onCancel();
            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);




    }

    private void onOK() throws InterruptedException {
        //table1.setValueAt(1, row, column);
        // add your code here
        Random rand = new Random();

        table1.setValueAt("□", row, column);

        if (rand.nextBoolean()) {
            row++;
        } else {
            column++;
        }
        if (row > 31){
            row = 0;
            //column++;
        }
        if (column > 31){
           column = 0;
        }

        table1.setValueAt("■", row, column);





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

