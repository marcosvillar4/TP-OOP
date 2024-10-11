package classes;

import javax.swing.*;

import java.util.concurrent.atomic.AtomicInteger;

public class uiUpdate implements Runnable{

    JLabel scoreLabel;
    AtomicInteger len;

    public uiUpdate(JLabel scoreLabel, AtomicInteger len) {
        this.scoreLabel = scoreLabel;
        this.len = len;
    }

    @Override
    public void run() {
        while (true){
            if (!String.valueOf(len.get()).equals(scoreLabel.getText())){
                scoreLabel.setText(String.valueOf(len.get()));
                scoreLabel.update(scoreLabel.getGraphics());
            }
        }
    }
}
