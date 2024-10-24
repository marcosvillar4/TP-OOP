import java.awt.*;
import java.util.Random;

public class Comida extends ObjetoJuego {

    private final String emoji = "\uD83C\uDF57";
    private final int ancho;
    private final int cabeza;

    public Comida(Color color, int ancho, int altura) {
        super(null, color);
        this.ancho = ancho;
        this.cabeza = altura;
        spawn();
    }

    public void spawn() {
        Random rand = new Random();
        int x = rand.nextInt(ancho / Juego.BLOCK_SIZE) * Juego.BLOCK_SIZE;
        int y = rand.nextInt(cabeza / Juego.BLOCK_SIZE) * Juego.BLOCK_SIZE;
        this.posicion = new Point(x, y);
    }

    @Override
    public void render(Graphics g) {
        g.setFont(new Font("SansSerif", Font.PLAIN, Juego.BLOCK_SIZE));
        g.drawString(emoji, posicion.x, posicion.y + Juego.BLOCK_SIZE);
        // g.setColor(color);
        // g.fillRect(posicion.x, posicion.y, SnakeGame.BLOCK_SIZE, SnakeGame.BLOCK_SIZE);
    }
}