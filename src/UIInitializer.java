import javax.swing.JButton;
@SuppressWarnings("unused")
public class UIInitializer {
    private JButton startButton;
    private JButton resetButton;

    public UIInitializer(Logica logica) {
        initializeStartButton(logica);
        initializeResetButton(logica);
    }

    private void initializeStartButton(Logica logica) {
        startButton = new JButton("Iniciar Juego");
        startButton.setBounds(Config.WIDTH / 2 - 60, Config.HEIGHT / 2 + 40, 120, 30);
        startButton.setFocusable(false);
        startButton.addActionListener(e -> logica.iniciarJuego());
        logica.add(startButton);
    }

    
    private void initializeResetButton(Logica logica) {
        resetButton = new JButton("¿Reiniciar?");
        resetButton.setBounds(Config.WIDTH / 2 - 60, Config.HEIGHT / 2 + 40, 120, 30);
        resetButton.setFocusable(false);
        resetButton.setVisible(false);
        resetButton.addActionListener(e -> logica.reset());
        logica.add(resetButton);
    }

    public JButton getStartButton() {
        return startButton;
    }

    public JButton getResetButton() {
        return resetButton;
    }
}