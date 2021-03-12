import javax.swing.*;
import java.awt.*;
import java.util.Random;

/**
 * The type Ghost.
 */
public class Ghost extends Rectangle {
    /**
     * The Ghost.
     */
    Image GHOST;

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
    private int speed = 1;
    private int x,y;
    private Random random = new Random();
    /**
     * The Kierunek.
     */
    int kierunek = random.nextInt();


    /**
     * Instantiates a new Ghost.
     *
     * @param x the x
     * @param y the y
     */
    public Ghost(int x, int y){
        // System.out.println(x/22 + " " + y/22);
        setBounds(x,y,22, 22);
        this.loadImages();
        this.x = x;
        this.y = y;


    }
    private void loadImages(){
        this.GHOST = (new ImageIcon("policja2.gif").getImage());
    }

    /**
     * Tick.
     */
    public void tick(){
        // System.out.println(random.nextInt()%4);
        if(kierunek%4==0 && canMove(x+speed, y)!=0 )   x+=speed;
        else if(kierunek%4==1  && canMove(x-speed, y)!=0)   x-=speed;
        else if(kierunek%4==2  && canMove(x, y+speed)!=0)   y+=speed;
        else if(kierunek%4==3  && canMove(x, y-speed)!=0)   y-=speed;
        else{
            kierunek = random.nextInt();
        }

    }

    private int canMove (int nextX, int nextY) {
        Level level = Game.level;
        return Game.level.checkBlok2(nextX, nextY);
    }

    /**
     * Render.
     *
     * @param g the g
     */
    public void render(Graphics g){
        this.tick();
        g.drawImage(GHOST, x, y, null);
    }

    public int getx() {
        return x;
    }

    public int gety() {
        return y;
    }

    }
