package proyectopong;

import piezas.Raqueta;
import piezas.Bola;
import java.util.ArrayList;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.FileWriter;
import java.util.Random;
import javax.swing.JOptionPane;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JFrame;
import javax.swing.Timer;
import utilidades.PedirDatos;

/**
 *
 */
public class Pong implements ActionListener, KeyListener {

    String[][] datos = new String[1][1];
    ArrayList<String> datosL = new ArrayList<String>();
    public static Pong pong;

    public int ancho = 800, altura = 600;

    public String datosFinal;

    public Renderizador renderizador;

    public Raqueta jugador1;

    public Raqueta jugador2;

    private String nombre;

    private Boolean dos = false;

    private String nombre2;

    public Bola numToques;

    public Bola bola;

    public boolean bot = false, seleccionarDificultad;

    public boolean fin = false;

    public boolean w, s, arriba, abajo;
//////////////
    public int estadoJuego = 0, maxPuntuacion = 2, jugGanador; //0 = Menu, 1 = En pausa, 2 = Jugando, 3 = Fin
////////////////
    public int dificultadBot, movimientosBot, reposoBot = 0;

    public Random random;

    public JFrame jframe;

    public void nombres() {
        nombre = PedirDatos.lerString("introducing your champion");
        JOptionPane.showMessageDialog(jframe, nombre);

        nombre2 = JOptionPane.showInputDialog("Jugador 2:", "IA");
        JOptionPane.showMessageDialog(jframe, nombre2);
    }

    public Pong() {
        nombres();
        Timer timer = new Timer(20, this);
        random = new Random();

        jframe = new JFrame("Pong");

        renderizador = new Renderizador();

        jframe.setSize(ancho + 15, altura + 35);
        jframe.setVisible(true);
        jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jframe.add(renderizador);
        jframe.addKeyListener(this);

        timer.start();

    }

    public void iniciar() {
        estadoJuego = 2;
        jugador1 = new Raqueta(this, 1);
        jugador2 = new Raqueta(this, 2);
        bola = new Bola(this);
        numToques = new Bola(this);
    }

    public void actualizar() throws IOException {
        if (jugador1.puntuacion >= maxPuntuacion) {
            jugGanador = 1;
            estadoJuego = 3;
            int msj = JOptionPane.showConfirmDialog(jframe, "el ganador es: " + nombre + " ¿quieres guardar nombre del ganador con la puntuacion de " + maxPuntuacion + "?");
            if (msj == JOptionPane.YES_OPTION) {
                datosL.add(nombre);
                datosL.add(Integer.toString(maxPuntuacion));

                for (String datosL1 : datosL) {
                    FileWriter archivo = null;
                    PrintWriter pw = null;
                    archivo = new FileWriter("archivo.txt", true);

                    try {
                        pw = new PrintWriter(archivo, true);
                        if (dificultadBot == 2) {
                            pw.println("DIFÍCIL");
                        } else if (dificultadBot == 1) {
                            pw.println("Normal");
                        } else {
                            pw.println("fácil");

                        }
                        pw.print("Nombre> |");

                        for (String doc : datosL) {
                            pw.print(doc + "|");
                        }
                        pw.println(" <Puntuación");
                        pw.close();

                    } catch (Exception e) {
                        e.printStackTrace();
                    } finally {
                        try {
                            if (null != archivo);
                        } catch (Exception e2) {
                            e2.printStackTrace();
                        }
                    }

                    break;
                }

            }
            if (msj == JOptionPane.CANCEL_OPTION) {
                JOptionPane.showConfirmDialog(jframe, "No se guarda");
            }
            if (msj == JOptionPane.CLOSED_OPTION) {
                System.exit(msj);
            }

        }
        if (jugador2.puntuacion >= maxPuntuacion) {
            estadoJuego = 3;
            jugGanador = 2;
            int msj = JOptionPane.showConfirmDialog(jframe, "el ganador es: " + nombre2 + " ¿quieres guardar nombre del ganador con la puntuacion de " + maxPuntuacion + "?");
            if (msj == JOptionPane.YES_OPTION) {
                datosL.add(nombre2);
                datosL.add(Integer.toString(maxPuntuacion));

                for (String datosL1 : datosL) {
                    FileWriter archivo = null;
                    PrintWriter pw = null;
                    archivo = new FileWriter("archivo.txt", true);

                    try {
                        pw = new PrintWriter(archivo);
                        if (dificultadBot == 2) {
                            pw.println("DIFÍCIL");
                        } else if (dificultadBot == 1) {
                            pw.println("Normal");
                        } else {
                            pw.println("fácil");

                        }
                        pw.print("Nombre> |");

                        for (String doc : datosL) {
                            pw.print(doc + "|");
                        }

                        pw.println(" <Puntuación");
                        pw.close();
                    } catch (Exception e) {
                        e.printStackTrace();
                    } finally {
                        try {
                            if (null != archivo);
                        } catch (Exception e2) {
                            e2.printStackTrace();
                        }
                    }

                    break;
                }
            }
            if (msj == JOptionPane.CANCEL_OPTION) {
                JOptionPane.showMessageDialog(jframe, "No se guarda");
            }
            if (msj == JOptionPane.CLOSED_OPTION) {
                System.exit(msj);
            }
        }

        if (w) {
            jugador1.mover(true);
        }
        if (s) {
            jugador1.mover(false);
        }

        if (!bot) {
            if (arriba) {
                jugador2.mover(true);
            }
            if (abajo) {
                jugador2.mover(false);
            }
        } else {
            if (reposoBot > 0) {
                reposoBot--;

                if (reposoBot == 0) {
                    movimientosBot = 0;
                }
            }

            if (movimientosBot < 10) {
                if (jugador2.y + jugador2.altura / 2 < bola.y) {
                    jugador2.mover(false);
                    movimientosBot++;
                }

                if (jugador2.y + jugador2.altura / 2 > bola.y) {
                    jugador2.mover(true);
                    movimientosBot++;
                }

                if (dificultadBot == 0) {
                    reposoBot = 20;
                }
                if (dificultadBot == 1) {
                    reposoBot = 15;
                }
                if (dificultadBot == 2) {
                    reposoBot = 10;
                }
            }
        }

        bola.actualizar(jugador1, jugador2);
    }

    public void renderizar(Graphics2D g) {
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, ancho, altura);
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        if (estadoJuego == 0) {
            g.setColor(Color.WHITE);
            g.setFont(new Font("Arial", 1, 50));

            g.drawString("PONG", ancho / 2 - 75, 50);

            if (!seleccionarDificultad) {
                g.setFont(new Font("Arial", 1, 30));

                g.drawString("Pulsa 'Espacio' para jugar", ancho / 2 - 200, altura / 2 - 25);
                g.drawString("Pulsa 'Shift' para jugar con la IA", ancho / 2 - 200, altura / 2 + 25);
                g.setFont(new Font("Arial", 3, 15));
                g.drawString("<< Puntuacion maxima: " + maxPuntuacion + " >>", ancho / 2 - 120, altura / 2 + 185);

            }
        }

        if (seleccionarDificultad) {
            String string = dificultadBot == 0 ? "Facil" : (dificultadBot == 1 ? "Medio" : "Dificil");

            g.setFont(new Font("Arial", 1, 30));

            g.drawString("<< Dificultad IA: " + string + " >>", ancho / 2 - 180, altura / 2 - 25);
            g.drawString("Pulsa 'espacio' para jugar", ancho / 2 - 150, altura / 2 + 25);

        }

        if (estadoJuego == 1) {
            g.setColor(Color.WHITE);
            g.setFont(new Font("Arial", 1, 50));
            g.drawString("EN PAUSA", ancho / 2 - 103, altura / 2 - 25);
        }

        if (estadoJuego == 1 || estadoJuego == 2) {
            g.setColor(Color.WHITE);

            g.setStroke(new BasicStroke(5f));

            g.drawLine(ancho / 2, 0, ancho / 2, altura);//

            g.setStroke(new BasicStroke(2f));

            g.drawOval(ancho / 2 - 150, altura / 2 - 150, 300, 300);//

            g.setFont(new Font("Arial", 1, 50));

            g.drawString(String.valueOf(jugador1.puntuacion), ancho / 2 - 90, 50);
            g.drawString(String.valueOf(jugador2.puntuacion), ancho / 2 + 65, 50);

            jugador1.renderizar(g);
            jugador2.renderizar(g);
            bola.renderizar(g);
        }

        if (estadoJuego == 3) {
            g.setColor(Color.WHITE);
            g.setFont(new Font("Arial", 1, 50));

            g.drawString("PONG", ancho / 2 - 75, 50);

            g.setFont(new Font("Arial", 1, 30));

            g.drawString("Pulsa 'Espacio' para volver a jugar", ancho / 2 - 220, altura / 2 - 15);
            g.drawString("Pulsa 'ESC' para volver al menu", ancho / 2 - 220, altura / 2 + 70);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (estadoJuego == 2) {
            try {
                actualizar();
            } catch (IOException ex) {
                Logger.getLogger(Pong.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        renderizador.repaint();
    }

    public static void main(String[] args) {
        pong = new Pong();
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int id = e.getKeyCode();

        if (id == KeyEvent.VK_W) {
            w = true;
        } else if (id == KeyEvent.VK_S) {
            s = true;
        } else if (id == KeyEvent.VK_UP) {
            arriba = true;
        } else if (id == KeyEvent.VK_DOWN) {
            abajo = true;
        } else if (id == KeyEvent.VK_RIGHT) {
            if (seleccionarDificultad) {
                if (dificultadBot < 2) {
                    dificultadBot++;
                } else {
                    dificultadBot = 0;
                }
            } else if (estadoJuego == 0) {
                maxPuntuacion++;
            }
        } else if (id == KeyEvent.VK_LEFT) {
            if (seleccionarDificultad) {
                if (dificultadBot > 0) {
                    dificultadBot--;
                } else {
                    dificultadBot = 2;
                }
            } else if (estadoJuego == 0 && maxPuntuacion > 1) {
                maxPuntuacion--;
            }
        } else if (id == KeyEvent.VK_ESCAPE && (estadoJuego == 2 || estadoJuego == 3)) {
            estadoJuego = 0;
        } else if (id == KeyEvent.VK_SHIFT && estadoJuego == 0) {
            bot = true;
            seleccionarDificultad = true;
        } else if (id == KeyEvent.VK_SPACE) {
            if (estadoJuego == 0 || estadoJuego == 3) {
                if (!seleccionarDificultad) {
                    bot = false;
                } else {
                    seleccionarDificultad = false;
                }

                iniciar();
            } else if (estadoJuego == 1) {
                estadoJuego = 2;
            } else if (estadoJuego == 2) {
                estadoJuego = 1;
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int id = e.getKeyCode();

        if (id == KeyEvent.VK_W) {
            w = false;
        } else if (id == KeyEvent.VK_S) {
            s = false;
        } else if (id == KeyEvent.VK_UP) {
            arriba = false;
        } else if (id == KeyEvent.VK_DOWN) {
            abajo = false;
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }
}
