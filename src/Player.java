import javax.swing.*;
import java.awt.*;

/**
 * The type Player.
 */
public class Player extends Rectangle {


    /**
     * The Pacman.
     */
    Image pacman;

    /**
     * The Right.
     */
    public boolean right, /**
     * The Left.
     */
    left, /**
     * The Up.
     */
    up, /**
     * The Down.
     */
    down;
    /**
     * @param speed predkosc pacmana
     */
    private int speed = 1;
    /**
     * @param x współrzędna horyzontalna
     * @param y współrzędna wertykalna
     */
    private int x;



    private int y;

    private int wynik = 0;


    /**
     * Instantiates a new Player.
     *
     * @param x the x współrzędna horyzontalna
     * @param y the y współrzędna wertykalna
     */
    public Player(int x, int y){
        setBounds(x,y,22, 22);
        this.loadImages();
        this.x = x;
        this.y = y;

    }

    /**
     * @return pacman Image
     */
    private void loadImages(){
        this.pacman = (new ImageIcon("wixaman.png").getImage());
    }

    /**
     * Tick. tik zegra
     */
    public void tick(){
        if(right && canMove(x+speed, y)!=0)   x+=speed;
        if(left && canMove(x-speed, y)!=0)   x-=speed;
        if(down && canMove(x, y+speed)!=0)   y+=speed;
        if(up && canMove(x, y-speed)!=0)   y-=speed;

    }
    /**
     * canMove
     * sprawdzamy przyszły krok pacmana
     * @return boolen czy kolejny krok nas nie blokuje
     */
    private int canMove (int nextX, int nextY) {
        Level level = Game.level;
    return Game.level.checkBlok(nextX, nextY);
    }

    /**
     * Render.
     * rysujemy pacmana
     * @param g the g
     */
    public void render(Graphics g){
        this.tick();
        g.drawImage(pacman, x, y, null);
    }

    public int getx() {
        return x;
    }

    public int gety() {
        return y;
    }

}
