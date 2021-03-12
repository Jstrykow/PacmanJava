
import javax.swing.*;
import java.awt.*;


/**
 * The type Punkt.
 */
public class Punkt extends Rectangle {
    /**
     * The Image 2.
     */
    Image image2;

    /**
     * Instantiates a new Punkt.
     *
     * @param x the x
     * @param y the y
     */
    public Punkt(int x, int y) {
        setBounds(x,y, 22,22);
        image2 = new ImageIcon("candy.png").getImage();
    }

    /**
     * Render.
     *
     * @param g the g
     */
    public void render(Graphics g) {
        g.drawImage(image2, x,y, null);

    }
}
