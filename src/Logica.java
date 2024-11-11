import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Random;

public class Logica extends JPanel implements ActionListener {
    private Serpiente serpiente;
    private Comida comida;
    private int puntaje;
    private boolean gameOver;
    private boolean conExcepcion;
    private final Timer timer;
    private String habilidadActual;
    private boolean pantallaInicio;
    private ArrayList<Integer> puntajes;
    private final FileManager fileManager;
    private final InicializadorUI inicializadorUI;
    private ArrayList<Color> colores = new ArrayList<>();

    public Logica(FileManager fileManager) {
        this.fileManager = fileManager;
        setPreferredSize(new Dimension(Config.WIDTH, Config.HEIGHT));
        setBackground(Color.LIGHT_GRAY);
        setFocusable(true);

        puntajes = cargarPuntajes();

        inicializadorUI = new InicializadorUI(this);
        inicializarPantallaInicio();

        colores = new ArrayList<>(Arrays.asList(Color.RED, Color.BLUE, Color.YELLOW, Color.GREEN, Color.MAGENTA));

        timer = new Timer(Config.SPEED, this);
        timer.start();

        addKeyListener(new GameKeyListener(this));
    }

    private ArrayList<Integer> cargarPuntajes() {
        ArrayList<String> lines = fileManager.readFile();
        ArrayList<Integer> puntajes = new ArrayList<>();

        for (String line : lines) {
            try {
                puntajes.add(Integer.parseInt(line));
            } catch (NumberFormatException ignored) {

            }
        }

        puntajes.sort(Comparator.reverseOrder());
        return puntajes;
    }

    private void inicializarPantallaInicio() {
        pantallaInicio = true;
        setLayout(null);
        inicializadorUI.getStartButton().setVisible(true);
    }

    protected void iniciarJuego() {
        pantallaInicio = false;
        inicializadorUI.getStartButton().setVisible(false);
        inicializarJuego();
    }

    private void inicializarJuego() {
        serpiente = new Serpiente(new Point(Config.WIDTH / 2, Config.HEIGHT / 2), Color.BLACK);
        comida = new Comida(Color.RED, Config.IMAGE_PATHS, Config.WIDTH, Config.HEIGHT, serpiente.getCuerpo());

        puntaje = 0;
        gameOver = false;
        conExcepcion = false;
        habilidadActual = "Ninguna";

        inicializadorUI.getStartButton().setVisible(false);
    }

    @Override
    public void actionPerformed(ActionEvent e) throws NoMasPartesException {
        if (!pantallaInicio) {
            try {
                if (!gameOver) {
                    serpiente.mover();
                    if (serpiente.comioManzana(comida.getPosicion())){
                        Random random = new Random();
                        int habilidad = random.nextInt(5); 


                        puntaje = serpiente.elegirHabilidad(habilidad, timer, puntaje);

                        switch (habilidad) {
                            case 0 -> habilidadActual = "Aumentar Velocidad";
                            case 1 -> habilidadActual = "Agregar Dos Partes";
                            case 2 -> habilidadActual = "Decrecer";
                            case 3 -> habilidadActual = "Invertir Dirección";
                            case 4 -> habilidadActual = "???";
                        }
                        serpiente.setColor(colores.get(random.nextInt(colores.size())));
                        comida.spawn();
                        puntaje++;
                    }

                    if(serpiente.estaMuerta(Config.WIDTH, Config.HEIGHT, null)){
                        gameOver = true;
                        endGame();
                    }
                }
            } catch (NoMasPartesException ex) {
                gameOver = true;
                conExcepcion = true;
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            } finally {
                repaint();
            }
        }
    }

    protected void reset() {
        inicializadorUI.getResetButton().setVisible(false);
        inicializarJuego();
        repaint();
    }

@Override
protected void paintComponent(Graphics g) {
    super.paintComponent(g);

    Renderizado renderizado = new Renderizado(puntajes, serpiente, puntaje, conExcepcion, habilidadActual);

    if (pantallaInicio) {
        renderizado.drawPantallaInicio(g);
    } else {
        serpiente.render(g);
        comida.render(g);
        renderizado.drawPuntaje(g);
        renderizado.drawHabilidad(g);
        if (gameOver) {
            renderizado.drawGameOver(g);
        }
    }
}

    private void endGame() {
        inicializadorUI.getResetButton().setVisible(true);
        fileManager.writeFile(String.valueOf(puntaje));
    }

    public boolean isPantallaInicio() {
        return pantallaInicio;
    }

    public Serpiente getSerpiente() {
        return serpiente;
    }
}
