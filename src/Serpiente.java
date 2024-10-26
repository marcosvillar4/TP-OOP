import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Random;


public class Serpiente extends ObjetoJuego implements Habilidades {
    private final ArrayList<Point> cuerpo;
    private Direccion direccion;

    public Serpiente(Point startPos, Color color) {
        super(startPos, color);
        this.cuerpo = new ArrayList<>();
        this.cuerpo.add(startPos);
        this.cuerpo.add(new Point(startPos.x - Juego.BLOCK_SIZE, startPos.y));
        this.cuerpo.add(new Point(startPos.x - 2 * Juego.BLOCK_SIZE, startPos.y));
        this.direccion = Direccion.DERECHA;
    }

    public void mover() {
        Point cabeza = cuerpo.getFirst();
        Point newCabeza = new Point(cabeza);

        switch (this.direccion) {
            case DERECHA -> newCabeza.x += Juego.BLOCK_SIZE;
            case IZQUIERDA -> newCabeza.x -= Juego.BLOCK_SIZE;
            case ARRIBA -> newCabeza.y -= Juego.BLOCK_SIZE;
            case ABAJO -> newCabeza.y += Juego.BLOCK_SIZE;
        }

        cuerpo.addFirst(newCabeza);
        cuerpo.removeLast();
    }

    public void crecer() {
        Point cola = cuerpo.getLast();
        cuerpo.add(new Point(cola));
    }

    public boolean checkColision(Point comida){
        return cuerpo.getFirst().equals(comida);
    }

    public boolean estaMuerta(int ancho, int altura, Point punto) {
        Point cabeza = cuerpo.getFirst();

        if (punto == null){
            punto = cabeza;
        }

        if (punto.x < 0 || punto.x >= ancho || punto.y < 0 || punto.y >= altura) {
            return true;
        }

        for (int i = 1; i < cuerpo.size(); i++) {
            if (punto.equals(cuerpo.get(i))) {
                return true;
            }
        }

        return false;
    }

    public int elegirHabilidad(int n, Timer timer, int puntaje){
        switch (n){
            case 0:
                aumentarVelocidad(timer);
                break;
            case 1:
                puntaje = agregarDosPartes(puntaje);
                break;
            case 2:
                decrecer();
                break;
            case 3:
                invertirDireccion();
                break;
            default:
                crecer();
                break;
        }
        return puntaje;
    }

    public void aumentarVelocidad(Timer timer){
        int velocidadOriginal = timer.getDelay();

        timer.setDelay(40);

        Timer resetTimer = new Timer(3000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                timer.setDelay(velocidadOriginal);
                ((Timer) e.getSource()).stop();
            }
        });

        resetTimer.setRepeats(false);
        resetTimer.start();
    }

    public int agregarDosPartes(int puntaje){
        crecer();
        crecer();
        return puntaje + 1;
    }

    public void decrecer(){
        cuerpo.removeLast(); // HACER EXCEPCION
    }

    // public void funnyCommand(){

    // }

    public void invertirDireccion(){
        Point cabeza = cuerpo.getLast();
        Point newCabeza = new Point(cabeza);
        this.direccion= Direccion.IZQUIERDA;
    }

    @Override
    public void render(Graphics g) {
        for (Point p : cuerpo) {
            g.setColor(color);
            g.fillRect(p.x, p.y, Juego.BLOCK_SIZE, Juego.BLOCK_SIZE);
            g.setColor(Color.BLACK);
            g.fillRect(p.x + 4, p.y + 4, Juego.BLOCK_SIZE - 8, Juego.BLOCK_SIZE - 8);
        }
    }


    public void setDireccion(Direccion direccion) {
        this.direccion = direccion;
    }

    public ArrayList<Point> getCuerpo() {
        return cuerpo;
    }

    public Direccion getDireccion() {
        return direccion;
    }
}