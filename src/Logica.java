import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.util.ArrayList;
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
    private JButton startButton;
    private JButton resetButton;
    private boolean pantallaInicio;
    private ArrayList<Integer> puntajes;

    /*
    * BUGS A CORREGIR
    * 1. Cuando la serpiente tiene solo 1 parte (la cabeza), cualquier habilidad la hace perder
    */

    public Logica() {
        setPreferredSize(new Dimension(Juego.WIDTH, Juego.HEIGHT));
        setBackground(Color.LIGHT_GRAY);
        setFocusable(true);

        puntajes = cargarPuntajes();

        inicializarPantallaInicio();

        timer = new Timer(Juego.SPEED, this);
        timer.start();

        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (!pantallaInicio) {
                    switch (e.getKeyCode()) {
                        case KeyEvent.VK_LEFT -> {
                            if(serpiente.getDireccion() != Direccion.DERECHA){
                                serpiente.setDireccion(Direccion.IZQUIERDA);
                            }
                        }
                        case KeyEvent.VK_RIGHT -> {
                            if(serpiente.getDireccion() != Direccion.IZQUIERDA){
                                serpiente.setDireccion(Direccion.DERECHA);
                            }
                        }
                        case KeyEvent.VK_UP -> {
                            if(serpiente.getDireccion() != Direccion.ABAJO){
                                serpiente.setDireccion(Direccion.ARRIBA);
                            }
                        }
                        case KeyEvent.VK_DOWN -> {
                            if(serpiente.getDireccion() != Direccion.ARRIBA) {
                                serpiente.setDireccion(Direccion.ABAJO);
                            }
                        }
                        case KeyEvent.VK_R -> reset();
                    }
                }
            }
        });
    }

    private ArrayList<Integer> cargarPuntajes() {
        ArrayList<String> lines = Juego.fileManager.readFile("puntajes.txt");
        ArrayList<Integer> puntajes = new ArrayList<>();

        for (String line : lines) {
            try {
                puntajes.add(Integer.parseInt(line));
            } catch (NumberFormatException ignored) {
                System.out.println();
            }
        }

        puntajes.sort(Comparator.reverseOrder());
        return puntajes;
    }

    private void inicializarPantallaInicio() {
        pantallaInicio = true;
        setLayout(null);

        startButton = new JButton("Iniciar Juego");
        startButton.setBounds(Juego.WIDTH / 2 - 60, Juego.HEIGHT / 2 + 40, 120, 30);
        startButton.setFocusable(false);
        startButton.addActionListener(e -> iniciarJuego());
        add(startButton);
    }

    private void iniciarJuego() {
        pantallaInicio = false;
        startButton.setVisible(false);
        inicializarJuego();
    }

    private void inicializarJuego() {
        serpiente = new Serpiente(new Point(Juego.WIDTH / 2, Juego.HEIGHT / 2), Color.BLACK);
        comida = new Comida(Color.RED, "src/cara1.png", Juego.WIDTH, Juego.HEIGHT, serpiente.getCuerpo());

        puntaje = 0;
        gameOver = false;
        conExcepcion = false;
        habilidadActual = "Ninguna";

        resetButton = new JButton("¿Reiniciar?");
        resetButton.setBounds(Juego.WIDTH / 2 - 60, Juego.HEIGHT / 2 + 40, 120, 30);
        resetButton.setFocusable(false);
        resetButton.setVisible(false);
        resetButton.addActionListener(e -> reset());

        setLayout(null);
        add(resetButton);
    }

    @Override
    public void actionPerformed(ActionEvent e) throws NoMasPartesException {
        if (!pantallaInicio) {
            try {
                if (!gameOver) {
                    serpiente.mover();
                    if (serpiente.comioManzana(comida.getPosicion())){
                        Random random = new Random();
                        int habilidad = random.nextInt(5); // 2;


                        puntaje = serpiente.elegirHabilidad(habilidad, timer, puntaje);

                        switch (habilidad) {
                            case 0 -> habilidadActual = "Aumentar Velocidad";
                            case 1 -> habilidadActual = "Agregar Dos Partes";
                            case 2 -> habilidadActual = "Decrecer";
                            case 3 -> habilidadActual = "Invertir Dirección";
                            case 4 -> habilidadActual = "???";
                        }

                        comida.spawn();
                        puntaje++;
                    }

                    if(serpiente.estaMuerta(Juego.WIDTH, Juego.HEIGHT, null)){
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

    private void reset() {
        resetButton.setVisible(false);
        inicializarJuego();
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        if (pantallaInicio) {
            Renderizado.drawPantallaInicio(g, puntajes);
        } else {
            serpiente.render(g);
            comida.render(g);
            Renderizado.drawPuntaje(g, puntaje);
            Renderizado.drawHabilidad(g, habilidadActual);
            if (gameOver) {
                Renderizado.drawGameOver(g, serpiente, puntaje, conExcepcion);
            }
        }
    }

    private void endGame() {
        resetButton.setVisible(true);
        Juego.fileManager.writeFile(Juego.fd, String.valueOf(puntaje));
    }
}
