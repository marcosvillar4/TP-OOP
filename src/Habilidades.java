import javax.swing.*;
import java.io.IOException;

public interface Habilidades {
    int elegirHabilidad(int n, Timer timer, int puntaje) throws IOException;
    void aumentarVelocidad(Timer timer);
    int agregarDosPartes(int puntaje);
    void decrecer();
    void funnyCommand() throws IOException;
    void invertirDireccion();
}
