import java.awt.*;

public class Renderizado {
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
