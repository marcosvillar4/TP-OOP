import javax.swing.*;
public interface Habilidades {
    int elegirHabilidad(int n, Timer timer, int puntaje);
    void aumentarVelocidad(Timer timer);
    int agregarDosPartes(int puntaje);
    void decrecer();
    // void funnyCommand();
    void invertirDireccion();
}
