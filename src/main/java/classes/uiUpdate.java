package classes;

import javax.swing.*;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;

public class uiUpdate implements Runnable{

    JLabel scoreLabel;
    AtomicInteger len;


    int len2 = 0;

    public uiUpdate(JLabel scoreLabel, AtomicInteger len) {
        this.scoreLabel = scoreLabel;
        this.len = len;
    }

    @Override
    public void run() {
        while (true) {
            if (!Objects.equals(len.toString(), scoreLabel.getText())) {
                scoreLabel.setText(len.toString());
                scoreLabel.update(scoreLabel.getGraphics());
            }
        }
    }
}
