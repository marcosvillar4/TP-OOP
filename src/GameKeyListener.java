import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class GameKeyListener extends KeyAdapter {
    private final Logica logica;

    public GameKeyListener(Logica logica) {
        this.logica = logica;
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (!logica.isPantallaInicio()) {
            switch (e.getKeyCode()) {
                case KeyEvent.VK_LEFT -> {
                    if (logica.getSerpiente().getDireccion() != Direccion.DERECHA) {
                        logica.getSerpiente().setDireccion(Direccion.IZQUIERDA);
                    }
                }
                case KeyEvent.VK_RIGHT -> {
                    if (logica.getSerpiente().getDireccion() != Direccion.IZQUIERDA) {
                        logica.getSerpiente().setDireccion(Direccion.DERECHA);
                    }
                }
                case KeyEvent.VK_UP -> {
                    if (logica.getSerpiente().getDireccion() != Direccion.ABAJO) {
                        logica.getSerpiente().setDireccion(Direccion.ARRIBA);
                    }
                }
                case KeyEvent.VK_DOWN -> {
                    if (logica.getSerpiente().getDireccion() != Direccion.ARRIBA) {
                        logica.getSerpiente().setDireccion(Direccion.ABAJO);
                    }
                }
                case KeyEvent.VK_R -> logica.reset();
            }
        }
    }
}
