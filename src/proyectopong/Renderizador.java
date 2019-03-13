package proyectopong;

import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JPanel;

/**
 *
 * @author Alba
 */
public class Renderizador extends JPanel {

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Pong.pong.renderizar((Graphics2D) g);
    }
}
