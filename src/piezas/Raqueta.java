package piezas;

import java.awt.Color;
import java.awt.Graphics;
import proyectopong.Pong;

/**
 *
 */
public class Raqueta {

    public int numRaqueta;
 
    public int x, y, ancho = 20, altura = 180;

    public int puntuacion;

    public Raqueta(Pong pong, int numRaqueta) {
        this.numRaqueta = numRaqueta;

        if (numRaqueta == 1) {
            this.x = 0;
        }

        if (numRaqueta == 2) {
            this.x = pong.ancho - ancho;
        }

        this.y = pong.altura / 2 - this.altura / 2;
    }

    public void renderizar(Graphics g) {
        g.setColor(Color.WHITE);        
        g.fillRect(x, y, ancho, altura);
    }

    public void mover(boolean up) {
        int speed = 15;

        if (up) {
            if (y - speed > 0) { //limite de las palas hacia arriba
                y -= speed;
            } else {
                y = 0;
            }
        } else {        
            if (y + altura + speed < Pong.pong.altura) {//limite de las palas hacia abajo
                y += speed;
            } else {
                y = Pong.pong.altura - altura;
            }
        }
    }
}
