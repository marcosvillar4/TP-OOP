import javax.swing.*;

public class Juego {
    public static final int BLOCK_SIZE = 20;
    public static final int WIDTH = 640;
    public static final int HEIGHT = 480;
    public static final int SPEED = 100;
    public static final String fd = "puntajes.txt";
    public static FileManager fileManager = new FileManager(fd);

    public static void main(String[] args) {
        JFrame frame = new JFrame("Snake Game");
        Logica tabla = new Logica();
        frame.add(tabla);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
