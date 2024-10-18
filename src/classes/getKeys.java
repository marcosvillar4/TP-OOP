package classes;

import javax.swing.*;
import java.awt.event.KeyEvent;
import java.util.concurrent.atomic.AtomicInteger;

public class getKeys implements Runnable{

    AtomicInteger dir;
    JPanel frame;

    public getKeys(AtomicInteger dir, JPanel frame) {
        this.dir = dir;
        this.frame = frame;
    }



    @Override
    public void run() {
        frame.registerKeyboardAction(_ -> {
            if (dir.get() != 0) {
                dir.set(1);
                System.out.println(dir);
            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_UP, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);

        frame.registerKeyboardAction(_ -> {
            if (dir.get() != 1) {
                dir.set(0);
                System.out.println(dir);
            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_DOWN, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);

        frame.registerKeyboardAction(_ -> {
            if (dir.get() != 2) {
                dir.set(3);
                System.out.println(dir);
            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_LEFT, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);

        frame.registerKeyboardAction(_ -> {
            if (dir.get() != 3) {
                dir.set(2);
                System.out.println(dir);
            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_RIGHT, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);

        // DEBUG FUNCS

        frame.registerKeyboardAction(_ -> {

            dir.set(5);
            System.out.println(dir);

        }, KeyStroke.getKeyStroke(KeyEvent.VK_E, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);






    }
}
