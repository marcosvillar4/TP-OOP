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

    bodyPart fruta;

    Random rand = new Random();

    static int gridSize;

    public posUpdate(JTable table, AtomicInteger dir, JLabel DEBUGLABEL, AtomicBoolean kill, int GridSize) {
        this.table = table;
        this.dir = dir;
        this.kill = kill;
        label = DEBUGLABEL;
        //DEBUGsnakeLen(3);
        gridSize = GridSize;

        partList.add(new bodyPart(gridSize/2,4));
        partList.add(new bodyPart(gridSize/2,3));
        partList.add(new bodyPart(gridSize/2,2));
        fruta = new bodyPart((gridSize/2),(gridSize/2));
    }

    @Override
    public void run() {
        while (true){




            clearTable();
            fwd();
            renderBody();


            try {
                Thread.sleep(100);  // Se utiliza para definir intervalo entre cuadro "MSPF"
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }


            table.update(table.getGraphics());


            if (wallCol()) return;

            if (selfCol()) return;

        }
    }

    private boolean selfCol() {
        for (int i = 1; i < partList.size(); i++) {
            if (partList.getFirst().x == partList.get(i).x && partList.getFirst().y == partList.get(i).y){
                kill.set(false);
                Thread.currentThread().interrupt();

                label.update(label.getGraphics());
                return true;
            }
        }
        return false;
    }

    private boolean wallCol() {
        if (partList.getFirst().x == 0 || partList.getFirst().x == gridSize){
            kill.set(false);
            Thread.currentThread().interrupt();
            return true;
        }
        if (partList.getFirst().y == 0 || partList.getFirst().y == gridSize){
            kill.set(false);
            Thread.currentThread().interrupt();
            return true;
        }
        return false;
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

            colFruta(addPart);


        } else if (dir.get() == 1){

            bodyPart addPart = partList.removeLast();
            bodyPart temp = new bodyPart(partList.getFirst().x, partList.getFirst().y);
            temp.decreaseX();
            partList.addFirst(temp);

            colFruta(addPart);


        } else if (dir.get() == 2){

            bodyPart addPart = partList.removeLast();
            bodyPart temp = new bodyPart(partList.getFirst().x, partList.getFirst().y);
            temp.increaseY();
            partList.addFirst(temp);

            colFruta(addPart);

        } else if (dir.get() == 3){

            bodyPart addPart = partList.removeLast();
            bodyPart temp = new bodyPart(partList.getFirst().x, partList.getFirst().y);
            temp.decreaseY();
            partList.addFirst(temp);

            colFruta(addPart);

        }





        // DEBUG FUNCS

        /*else if (dir.get() == 5) {

            partList.add(partList.getLast());
            dir.set(1);

        }*/

    }

    private void colFruta(bodyPart addPart) {
        if (partList.getFirst().x == fruta.x && partList.getFirst().y == fruta.y){
            partList.add(addPart);
            label.setText(String.valueOf(partList.size()));
            fruitGen();
        }
    }



    void fruitGen(){
        boolean check = false;
        int x = rand.nextInt(0,gridSize);
        int y = rand.nextInt(0,gridSize);

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