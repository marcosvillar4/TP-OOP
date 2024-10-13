package classes;

import javax.swing.*;
import java.util.LinkedList;
import java.util.Random;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

public class posUpdate implements Runnable {

    JTable table;
    LinkedList<bodyPart> partList = new LinkedList<>();

    AtomicInteger dir; // Direccion de la serpiente, 0 = S, 1 = N, 2 = E, 3 = W
    JLabel label;
    AtomicBoolean kill;

    bodyPart fruta = new bodyPart(16,16);

    Random rand = new Random();

    public posUpdate(JTable table, AtomicInteger dir, JLabel DEBUGLABEL, AtomicBoolean kill) {
        this.table = table;
        this.dir = dir;
        this.kill = kill;
        label = DEBUGLABEL;
        //DEBUGsnakeLen(3);


        partList.add(new bodyPart(16,6));
        partList.add(new bodyPart(16,5));
        partList.add(new bodyPart(16,4));
    }

    @Override
    public void run() {
        while (true){

            clearTable();
            fwd();
            renderBody();


            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }


            table.update(table.getGraphics());

            if (partList.getFirst().x == 0 || partList.getFirst().x == 32){
                kill.set(false);
                Thread.currentThread().interrupt();
                return;
            }
            if (partList.getFirst().y == 0 || partList.getFirst().y == 32){
                kill.set(false);
                Thread.currentThread().interrupt();
                return;
            }
            for (int i = 1; i < partList.size(); i++) {
                if (partList.getFirst().x == partList.get(i).x && partList.getFirst().y == partList.get(i).y){
                    kill.set(false);
                    Thread.currentThread().interrupt();

                    label.update(label.getGraphics());
                    return;
                }
            }

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
            if (part == partList.getFirst()){
                table.setValueAt("█", part.x, part.y);
            } else {
                table.setValueAt("█", part.x, part.y);
            }

        }


        table.setValueAt("🍗", fruta.x, fruta.y);
    }

    void fwd(){
        if (dir.get() == 0){

            bodyPart addPart = partList.removeLast();
            bodyPart temp = new bodyPart(partList.getFirst().x, partList.getFirst().y);
            temp.increaseX();
            partList.addFirst(temp);

            if (partList.getFirst().x == fruta.x && partList.getFirst().y == fruta.y){
                partList.add(addPart);
                label.setText(String.valueOf(partList.size()));
                fruitGen();
            }


        } else if (dir.get() == 1){

            bodyPart addPart = partList.removeLast();
            bodyPart temp = new bodyPart(partList.getFirst().x, partList.getFirst().y);
            temp.decreaseX();
            partList.addFirst(temp);

            if (partList.getFirst().x == fruta.x && partList.getFirst().y == fruta.y){
                partList.add(addPart);
                label.setText(String.valueOf(partList.size()));
                fruitGen();
            }


        } else if (dir.get() == 2){

            bodyPart addPart = partList.removeLast();
            bodyPart temp = new bodyPart(partList.getFirst().x, partList.getFirst().y);
            temp.increaseY();
            partList.addFirst(temp);

            if (partList.getFirst().x == fruta.x && partList.getFirst().y == fruta.y){
                partList.add(addPart);
                label.setText(String.valueOf(partList.size()));
                fruitGen();
            }

        } else if (dir.get() == 3){

            bodyPart addPart = partList.removeLast();
            bodyPart temp = new bodyPart(partList.getFirst().x, partList.getFirst().y);
            temp.decreaseY();
            partList.addFirst(temp);

            if (partList.getFirst().x == fruta.x && partList.getFirst().y == fruta.y){
                partList.add(addPart);
                label.setText(String.valueOf(partList.size()));
                fruitGen();
            }



        }

        // DEBUG FUNCS

        else if (dir.get() == 5) {

            partList.add(partList.getLast());
            dir.set(1);




        }




    }

    void DEBUGsnakeLen(int len){
        for (int i = 0; i < len; i++) {
            partList.add(new bodyPart(31 - i,16));
            label.setText(String.valueOf(partList.size()));
        }
    }

    void fruitGen(){
        boolean check = false;
        int x = rand.nextInt(0,32);
        int y = rand.nextInt(0,32);

        for (bodyPart bodyPart : partList) {
            if (x == bodyPart.x){
                check = true;
            }
            if (y == bodyPart.y){
                check = true;
            }
        }

        if (check){
            fruitGen();
        } else {
            fruta = new bodyPart(x,y);
        }

    }

}
