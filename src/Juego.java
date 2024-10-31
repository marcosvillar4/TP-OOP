import javax.swing.*;

public class Juego {
    public static final int BLOCK_SIZE = 20;
    public static final int WIDTH = 640;
    public static final int HEIGHT = 480;
    public static final int SPEED = 100;
    public static FileManager fileManager = new FileManager("puntajes.txt");

    public static void main(String[] args) {
        JFrame frame = new JFrame("Snake Game");
        ZonaDeJuego tabla = new ZonaDeJuego();
        frame.add(tabla);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
