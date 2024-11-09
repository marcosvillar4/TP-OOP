import javax.swing.*;

public class Juego {
    public static FileManager fileManager = new FileManager(Config.PUNTAJE_FILE);

    public static void main(String[] args) {
        JFrame frame = new JFrame("Snake Game");
        Logica tabla = new Logica(fileManager);
        frame.add(tabla);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
