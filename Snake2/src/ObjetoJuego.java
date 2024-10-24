import java.awt.*;

public abstract class ObjetoJuego {
    protected Point posicion;
    protected Color color;

    public ObjetoJuego(Point posicion, Color color) {
        this.posicion = posicion;
        this.color = color;
    }

    public Point getPosition() {
        return posicion;
    }

    public abstract void render(Graphics g);
}