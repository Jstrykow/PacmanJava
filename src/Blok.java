import java.awt.*;


/**
 * The type Blok pojedyncza kratka
 */
public class Blok extends Rectangle {
    /**
     * Instantiates a new Blok.
     *
     * @param x the x
     * @param y the y
     */
    public Blok(int x, int y) {
        setBounds(x,y, 22,22);
    }

    /**
     * Render.
     *
     * @param g the g
     */
    public void render(Graphics g) {
        g.setColor(Color.BLACK);
        g.fillRect(x,y,width, height);
    }
}
