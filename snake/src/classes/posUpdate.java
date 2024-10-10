package classes;

import javax.swing.*;
import java.util.LinkedList;
import java.util.concurrent.atomic.AtomicInteger;

public class posUpdate implements Runnable {

    JTable table;
    LinkedList<bodyPart> partList = new LinkedList<>();

    AtomicInteger dir; // Direccion de la serpiente, 0 = S, 1 = N, 2 = E, 3 = W

    public posUpdate(JTable table, AtomicInteger dir) {
        this.table = table;
        this.dir = dir;

        DEBUGsnakeLen(20);
    }

    @Override
    public void run() {
        while (true){

            clearTable();
            fwd();
            renderBody();


            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }


            table.update(table.getGraphics());

        }
    }

    void clearTable(){
        for (int i = 0; i < table.getColumnCount(); i++) {
            for (int j = 0; j < table.getRowCount(); j++) {
                table.setValueAt("", i,j);
            }
        }
    }

    void renderBody(){
        for (bodyPart part : partList) {
            table.setValueAt("█", part.x, part.y);
        }
    }

    void fwd(){
        if (dir.get() == 0){

            partList.removeLast();
            bodyPart temp = new bodyPart(partList.getFirst().x, partList.getFirst().y);
            temp.increaseX();
            partList.addFirst(temp);

        } else if (dir.get() == 1){

            partList.removeLast();
            bodyPart temp = new bodyPart(partList.getFirst().x, partList.getFirst().y);
            temp.decreaseX();
            partList.addFirst(temp);

        } else if (dir.get() == 2){

            partList.removeLast();
            bodyPart temp = new bodyPart(partList.getFirst().x, partList.getFirst().y);
            temp.increaseY();
            partList.addFirst(temp);

        } else if (dir.get() == 3){

            partList.removeLast();
            bodyPart temp = new bodyPart(partList.getFirst().x, partList.getFirst().y);
            temp.decreaseY();
            partList.addFirst(temp);

        }
    }

    void DEBUGsnakeLen(int len){
        for (int i = 0; i < len; i++) {
            partList.add(new bodyPart(32 - i,16));
        }
    }
}
