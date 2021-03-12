
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.*;
import java.awt.*;
import java.awt.Graphics2D;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;
import java.awt.image.BufferStrategy;
import java.io.IOException;


/**
 * The type Game.
 */
public class Game extends Canvas implements Runnable, KeyListener{

    /**
     * The Score list. klasa posiada arraylist z klasą Score, w której
     * sa przechowywany wyniki
     */
  ///  ScoreList scoreList;


    private Graphics2D g;
    private Image pacman;
    private Image imageGhost;
    private Dimension d;

    private OknoGry oknoGry;

    private boolean isRunning = false;
    private static final int WIDTH = 440 , HEIGHT = 440;
    private Thread thread;
    int zmianaPunktow;
    BufferStrategy bs;
    /**
     * The constant player.
     */
    public static Player player;
    /**
     * The constant poziom.
     */
    public static Level level;
    private static int ghostNumber = 4;
    /**
     * The constant ghost.
     */
    public static Ghost ghost[] = new Ghost[ghostNumber];
    private Rectangle rec[] = new Rectangle[ghostNumber];

    private static OknoZapisowWynikow oknoZapisowWynikow;
    boolean killSingal = false;
    /**
     * Instantiates a new Game.
     *
     * @throws IOException the io exception
     */
    boolean isSpace = false;

    Client client;
    int wynik = 0;

    boolean isOnline = false;
    PacmanAudio pacmanAudio;

    Game(Client client, OknoGry oknoGry2) throws IOException {
        this.oknoGry = oknoGry2;
        killSingal = false;
        this.client = client;
        Dimension dimension = new Dimension(Game.WIDTH, Game.HEIGHT);
        setPreferredSize(dimension);
        setMaximumSize(dimension);
        setMinimumSize(dimension);
        addKeyListener(this);
        level = new Level();
        Score score = new Score();
        score.updateScoreFromServer(client.sendRequestWyniki());
        this.start();
        isOnline = true;
        pacmanAudio = new PacmanAudio();
        bs = null;

    }
    Game(OknoGry oknoGry2)  {
        this.oknoGry = oknoGry2;
        killSingal = false;
        this.client = null;
        Dimension dimension = new Dimension(Game.WIDTH, Game.HEIGHT);
        setPreferredSize(dimension);
        setMaximumSize(dimension);
        setMinimumSize(dimension);
        addKeyListener(this);
        try {
            level = new Level();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            initGameOffLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.start();
        isOnline = false;
        pacmanAudio = new PacmanAudio();
        bs = null;

    }

    private void initGameOffLine() throws IOException {
        player =  new Player(22, 22);
        for(int i = 0; i<ghostNumber; i++) {
            ghost[i] = new Ghost(220, 220);
        }
            this.level.readLevel("level1.txt");
    }


    private void continueLevel() throws IOException, AWTException {
        String str = "koniec";
        str = client.sendRequestLevel();
        System.out.println(str);
        if(!str.equals("koniec"))
        {
            level.initLevel2(str);
            level.ileZostaloPunktow();
            player = new Player(22, 22);
            for (int i = 0; i < ghostNumber; i++) {
                ghost[i] = new Ghost(220, 220);
            }
            zmianaPunktow = level.ileZostaloPunktow();
            if(bs != null) {
                render();
            }
            Robot r = new Robot();
            r.keyPress(KeyEvent.VK_SPACE);
            r.keyRelease(KeyEvent.VK_SPACE);
            JFrame frame = new JFrame();
            JOptionPane.showMessageDialog(frame,
                    "Przygotuj sie na rozgrywke }) - spacja rusz gre");

        } else {
           koniecGry();
        }
    }

    public void run(){
        int fps = 0; //counter fps
        double timer = System.currentTimeMillis();
        long lastTime = System.nanoTime();
        double targetTicks = 40;//oczekiweana liczba fps
        double delta = 0;
        double ns = 1000_000_000/targetTicks; //nano sekunda
        zmianaPunktow = level.ileZostaloPunktow();



        while(isRunning){


            if(zmianaPunktow != level.ileZostaloPunktow()){
                zmianaPunktow = level.ileZostaloPunktow();
                System.out.println(++wynik);
                try {
                    pacmanAudio.playDeathSound("pointSound.wav");
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                } catch (UnsupportedAudioFileException unsupportedAudioFileException) {
                    unsupportedAudioFileException.printStackTrace();
                } catch (LineUnavailableException lineUnavailableException) {
                    lineUnavailableException.printStackTrace();
                }
            }
                if(level.ileZostaloPunktow() > 0 ) {
                    long now = System.nanoTime();
                    delta += (now - lastTime) / ns;
                    lastTime = now;
                    while (delta >= 1) {
                        tick();
                        render();
                        fps++;
                        delta--;
                    }
                    if (System.currentTimeMillis() - timer >= 1000) {
                        fps = 0;
                        timer += 1000;
                    }
                } else if(isOnline){
                    try {
                        continueLevel();}
                    catch (AWTException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
        }
        if(!isSpace) {
            koniecGry();
        }
        stop();
    }

    /**
     * Render.
     */
    public void render() {
        if (isRunning = true) {
            bs = getBufferStrategy();
            if (bs == null) {
                createBufferStrategy(3);
                return;
            }
            g = (Graphics2D) bs.getDrawGraphics();
            g.setColor(new Color(253, 209, 197));
            g.fillRect(0, 0, Game.WIDTH, Game.HEIGHT);
            if (player != null)
                player.render(g);
            if (level != null)
                level.drawLevel(g);
            for (int i = 0; i < ghostNumber; i++) {
                if (ghost[i] != null)
                    ghost[i].render(g);
            }
            g.dispose();
            bs.show();
        }
    }



    private void tick(){
        player.tick();
        for(int i = 0; i<ghostNumber; i++) {
            ghost[i].tick();
        }
        collision();
    }

    private void collision(){
        Rectangle recPacman = new Rectangle();
        recPacman.setBounds(player.getx(), player.gety(),22,22);
        for(int i = 0; i<ghostNumber; i++) {
            rec[i] = new Rectangle();
            rec[i].setBounds(ghost[i].getx(), ghost[i].gety(),22,22);
            //rec[i].setBounds(ghost[i].getx(), ghost[i].gety(),22,22); //ghost[i] = new Ghost(220, 220);
        }
        //sprawdzamy czy pacman nie pokrywa sie z duchem
        for(int i = 0; i<ghostNumber; i++) {
            if (recPacman.intersects(rec[i])) {
                try {
                    pacmanAudio.playDeathSound("pacman_death.wav");
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                } catch (UnsupportedAudioFileException unsupportedAudioFileException) {
                    unsupportedAudioFileException.printStackTrace();
                } catch (LineUnavailableException lineUnavailableException) {
                    lineUnavailableException.printStackTrace();
                }
                koniecGry();
            }
        }
    }

    /**
     * Start.
     */
    public synchronized void start(){
        if(isRunning) return;
        isRunning = true;
        thread = new Thread(this);
        thread.start();
    }


    public void koniecGry(){
        this.oknoZapisowWynikow =  new OknoZapisowWynikow(wynik, client);
        oknoZapisowWynikow.initOknoZapisowWynikow();
        killSingal = true;
        oknoGry.closeOknoGry();
        oknoGry = null;
        this.stop();
    }
    public void koniecGry2(){
        this.oknoZapisowWynikow= null;
        this.stop();
        killSingal = true;
        this.pacman = null;
        player = null;
        level =null;
        for(int i = 0; i<ghostNumber; i++) {
            ghost[i]= null;
        }
        g = null;
    }

    public synchronized void stop(){
        if(!isRunning) return;
        isRunning = false;
        try{
            thread.join();
        } catch (InterruptedException e){
            e.printStackTrace();
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

        if(isRunning==true) {
            //  System.out.println(e.getKeyCode());;
            if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
                player.right = true;
            } else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
                player.left = true;
            } else if (e.getKeyCode() == KeyEvent.VK_UP) {
                player.up = true;
            } else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
                player.down = true;
            } else if (e.getKeyCode() == KeyEvent.VK_SPACE) {
                /**
                 * pauzowanie g
                 */
                isRunning = false;
                isSpace = true;
               // System.out.println(isSpace);
            }
        } else {
            if (e.getKeyCode() == KeyEvent.VK_SPACE) {
                this.start();//dziala
                isSpace = false;
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_RIGHT) player.right = false;
        if(e.getKeyCode() == KeyEvent.VK_LEFT) player.left = false;
        if(e.getKeyCode() == KeyEvent.VK_UP) player.up = false;
        if(e.getKeyCode() == KeyEvent.VK_DOWN) player.down = false;
    }
}



