import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

public class Comida extends ObjetoJuego {

    // private final String emoji = "\uD83C\uDF57";
    private Image imagen;
    private final int ancho;
    private final int altura;
    private final ArrayList<Point> cuerpo;

    public Comida(Color color, String imagenPath, int ancho, int altura, ArrayList<Point> cuerpo) {
        super(null, color);
        loadImage(imagenPath);
        this.ancho = ancho;
        this.altura = altura;
        this.cuerpo = cuerpo;
        spawn();
    }

    private void loadImage(String imagenPath){
        ImageIcon icon = new ImageIcon(imagenPath);
        imagen = icon.getImage();
    }

    public void spawn() {
        boolean enSerpiente;
        do{
            enSerpiente = false;
            Random rand = new Random();
            int x = rand.nextInt(ancho / Juego.BLOCK_SIZE)*Juego.BLOCK_SIZE;
            int y = rand.nextInt(altura/ Juego.BLOCK_SIZE)*Juego.BLOCK_SIZE;
            for (Point parte : cuerpo) {
                if(new Point(x, y).equals(parte)){
                    enSerpiente = true;
                    break;
                }
            }
            this.posicion = new Point(x, y);
        }while(enSerpiente);
    }

    @Override
    public void render(Graphics g) {
        g.drawImage(imagen, posicion.x, posicion.y, Juego.BLOCK_SIZE, Juego.BLOCK_SIZE, null);
        // g.setFont(new Font("SansSerif", Font.PLAIN, Juego.BLOCK_SIZE));
        // g.drawString(emoji, posicion.x, posicion.y + Juego.BLOCK_SIZE);
        // g.setColor(color);
        // g.fillRect(posicion.x, posicion.y, SnakeGame.BLOCK_SIZE, SnakeGame.BLOCK_SIZE);
    }
}
