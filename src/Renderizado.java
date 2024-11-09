import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Renderizado extends JPanel {
    private ArrayList<Integer> puntajes;
    private Serpiente serpiente;
    private int puntaje;
    private boolean conExcepcion;
    private String habilidadActual;

    public Renderizado(ArrayList<Integer> puntajes, Serpiente serpiente, int puntaje, boolean conExcepcion, String habilidadActual) {
        this.puntajes = puntajes;
        this.serpiente = serpiente;
        this.puntaje = puntaje;
        this.conExcepcion = conExcepcion;
        this.habilidadActual = habilidadActual;
    }

    public void drawPantallaInicio(Graphics g) {
        g.setColor(Color.BLACK);
        g.setFont(new Font("Arial", Font.BOLD, 20));
        g.drawString("Puntajes Más Altos:", Config.WIDTH / 2 - 80, 60);

        g.setFont(new Font("Arial", Font.PLAIN, 18));
        if (puntajes.isEmpty()) {
            g.drawString("No hay puntajes registrados.", Config.WIDTH / 2 - 100, 100);
        } else {
            for (int i = 0; i < Math.min(5, puntajes.size()); i++) {
                g.drawString((i + 1) + ". " + puntajes.get(i), Config.WIDTH / 2 - 40, 100 + (i * 30));
            }
        }
    }

    public void drawPuntaje(Graphics g) {
        g.setColor(Color.BLACK);
        g.drawString("Puntaje: " + puntaje, 10, 20);
    }

    public void drawGameOver(Graphics g) {
        g.setColor(Color.RED);
        if (conExcepcion) {
            serpiente.setColor(Color.WHITE);
            serpiente.render(g);
            g.setColor(Color.RED);
            g.drawString("¡Ups...! ¡La serpiente se quedo sin partes!", Config.WIDTH / 2 - 130, Config.HEIGHT / 2 - 40);
        }
        g.drawString("Game Over!", Config.WIDTH / 2 - 50, Config.HEIGHT / 2);
        g.drawString("Puntaje Final: " + puntaje, Config.WIDTH / 2 - 60, Config.HEIGHT / 2 + 20);
    }

    public void drawHabilidad(Graphics g) {
        g.setColor(Color.BLUE);
        g.drawString("Habilidad Activada: " + habilidadActual, 10, 40);
    }
}