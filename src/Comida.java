import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.ImageIcon;

public class Comida extends ObjetoJuego {
    private Image imagen;
    private final int ancho;
    private final int altura;
    private final ArrayList<Point> cuerpo;
    private final ArrayList<String> imagenes;

    public Comida(Color color, ArrayList<String> imagenes, int ancho, int altura, ArrayList<Point> cuerpo) {
        super(null, color);
        this.imagenes = imagenes;
        this.ancho = ancho;
        this.altura = altura;
        this.cuerpo = cuerpo;
        spawn();
    }

    private void loadImage() {
        Random rand = new Random();
        String imagePath = imagenes.get(rand.nextInt(imagenes.size()));
        ImageIcon icon = new ImageIcon(imagePath);
        imagen = icon.getImage();
    }

    public void spawn() {
        boolean enSerpiente;
        do {
            enSerpiente = false;
            Random rand = new Random();
            int x = rand.nextInt(ancho / Config.BLOCK_SIZE) * Config.BLOCK_SIZE;
            int y = rand.nextInt(altura / Config.BLOCK_SIZE) * Config.BLOCK_SIZE;

            for (Point parte : cuerpo) {
                if (new Point(x, y).equals(parte)) {
                    enSerpiente = true;
                    break;
                }
            }

            if (!enSerpiente) {
                this.posicion = new Point(x, y);
                loadImage();
            }

        } while (enSerpiente);
    }

    @Override
    public void render(Graphics g) {
        g.drawImage(imagen, posicion.x, posicion.y, Config.BLOCK_SIZE, Config.BLOCK_SIZE, null);
    }
}
