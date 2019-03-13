package piezas;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;
import proyectopong.Pong;

/**
 *
 * @author Alba
 */
public class Bola {

    public int x, y, ancho = 25, altura = 25;

    public int mocionX, mocionY;

    public Random random;

    private Pong pong;

    public int numToques;

    public Bola(Pong pong) {
        this.pong = pong;

        this.random = new Random();

        generar();
    }

    public void actualizar(Raqueta raqueta1, Raqueta raqueta2) {
        int velocidad = 5;

        this.x += mocionX * velocidad;
        this.y += mocionY * velocidad;

        if (this.y + altura - mocionY > pong.altura || this.y + mocionY < 0) {
            if (this.mocionY < 0) {
                this.y = 0;
                this.mocionY = random.nextInt(4);

                if (mocionY == 0) {
                    mocionY = 1;
                }
            } else {
                this.mocionY = -random.nextInt(4);
                this.y = pong.altura - altura;

                if (mocionY == 0) {
                    mocionY = -1;
                }
            }
        }

        if (verColision(raqueta1) == 1) {
            this.mocionX = 1 + (numToques / 5);
            this.mocionY = -2 + random.nextInt(4);

            if (mocionY == 0) {
                mocionY = 1;
            }

            numToques++;

        } else if (verColision(raqueta2) == 1) {
            this.mocionX = -1 - (numToques / 5);
            this.mocionY = -2 + random.nextInt(4);

            if (mocionY == 0) {
                mocionY = 1;
            }

            numToques++;
        }

        if (verColision(raqueta1) == 2) {
            raqueta2.puntuacion++;
            generar();
        } else if (verColision(raqueta2) == 2) {
            raqueta1.puntuacion++;
            generar();
        }
    }

    public void generar() {
        this.numToques = 0;
        this.x = pong.ancho / 2 - this.ancho / 2;
        this.y = pong.altura / 2 - this.altura / 2;

        this.mocionY = -2 + random.nextInt(4);

        if (mocionY == 0) {
            mocionY = 1;
        }

        if (random.nextBoolean()) {
            mocionX = 1;
        } else {
            mocionX = -1;
        }
    }

    public int verColision(Raqueta raqueta) {
        if (this.x < raqueta.x + raqueta.ancho && this.x + ancho > raqueta.x && this.y < raqueta.y + raqueta.altura && this.y + altura > raqueta.y) {
            return 1; //rebota
        } else if ((raqueta.x > x && raqueta.numRaqueta == 1) || (raqueta.x < x - ancho && raqueta.numRaqueta == 2)) {
            return 2; //puntua
        }

        return 0; //nada
    }

    public void renderizar(Graphics g) {
        g.setColor(Color.WHITE);
        g.fillOval(x, y, ancho, altura);
    }
}
