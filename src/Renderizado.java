import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Renderizado extends JPanel {
    public static void drawPantallaInicio(Graphics g, ArrayList<Integer> puntajes) {
        g.setColor(Color.BLACK);
        g.setFont(new Font("Arial", Font.BOLD, 20));
        g.drawString("Puntajes Más Altos:", Juego.WIDTH / 2 - 80, 60);

        g.setFont(new Font("Arial", Font.PLAIN, 18));
        if (puntajes.isEmpty()) {
            g.drawString("No hay puntajes registrados.", Juego.WIDTH / 2 - 100, 100);
        } else {
            for (int i = 0; i < Math.min(5, puntajes.size()); i++) {
                g.drawString((i + 1) + ". " + puntajes.get(i), Juego.WIDTH / 2 - 40, 100 + (i * 30));
            }
        }
    }

    public static void drawPuntaje(Graphics g, int puntaje) {
        g.setColor(Color.BLACK);
        g.drawString("Puntaje: " + puntaje, 10, 20);
    }

    public static void drawGameOver(Graphics g, Serpiente serpiente, int puntaje, boolean conExcepcion) {
        g.setColor(Color.RED);
        if (conExcepcion){
            serpiente.setColor(Color.WHITE);
            serpiente.render(g);
            g.setColor(Color.RED);
            g.drawString("¡Ups...! ¡La serpiente se quedo sin partes!", Juego.WIDTH / 2 - 130, Juego.HEIGHT / 2 - 40);
        }
        g.drawString("Game Over!", Juego.WIDTH / 2 - 50, Juego.HEIGHT / 2);
        g.drawString("Puntaje Final: " + puntaje, Juego.WIDTH / 2 - 60, Juego.HEIGHT / 2 + 20);
    }

    public static void drawHabilidad(Graphics g, String habilidadActual){
        g.setColor(Color.BLUE);
        g.drawString("Habilidad Activada: " + habilidadActual, 10, 40);
    }
}
