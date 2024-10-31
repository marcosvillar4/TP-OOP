import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

public class ZonaDeJuego extends JPanel implements ActionListener {
    private Serpiente serpiente;
    private Comida comida;
    private int puntaje;
    private boolean gameOver;
    private boolean conExcepcion;
    private final Timer timer;
    private String habilidadActual;
    private JButton resetButton;
    private ArrayList<String> lines;

    public ZonaDeJuego() {
        setPreferredSize(new Dimension(Juego.WIDTH, Juego.HEIGHT));
        setBackground(Color.LIGHT_GRAY);
        setFocusable(true);

        lines = Juego.fileManager.readFile("puntajes.txt");

        inicializarJuego();

        timer = new Timer(Juego.SPEED, this);
        timer.start();
        // timer.setDelay();

        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
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
        });
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
    public void actionPerformed(ActionEvent e) throws NoMasPartesException{ // por cada frame
        try{
            if (!gameOver) {
                serpiente.mover();
                if (serpiente.checkColision(comida.getPosicion())){
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

                    comida.spawn();
                    puntaje++;
                }

                if(serpiente.estaMuerta(Juego.WIDTH, Juego.HEIGHT, null)){
                    gameOver = true;
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

    private void reset(){
        resetButton.setVisible(false);
        inicializarJuego();
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        serpiente.render(g);
        comida.render(g);
        Renderizado.drawPuntaje(g, puntaje);
        Renderizado.drawHabilidad(g, habilidadActual);

        if (gameOver) {
            resetButton.setVisible(true);
            Renderizado.drawGameOver(g, serpiente, puntaje, conExcepcion);
        }
    }


}
