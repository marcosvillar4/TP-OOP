import java.awt.*;

public abstract class ObjetoJuego {
    protected Point posicion;
    protected Color color;

    public ObjetoJuego(Point posicion, Color color) {
        this.posicion = posicion;
        this.color = color;
    }

    public Point getPosicion() {
        return posicion;
    }

    public void setPosicion(Point posicion) {
        this.posicion = posicion;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public abstract void render(Graphics g);
}