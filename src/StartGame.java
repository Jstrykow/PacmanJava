import java.io.IOException;


/**
 * The type Start game.
 */
public class StartGame {
    /**
     * The Game.
     */
    Game game;
    /**
     * The Client.
     */
    Client client;
    /**
     * The Okno gry.
     */
    OknoGry oknoGry;

    /**
     * Is started boolean.
     *
     * @return the boolean
     */
    public boolean isStarted() {
        return isStarted;
    }

    /**
     * The Is started.
     */
    boolean isStarted = false;

    /**
     * Instantiates a new Start game.
     */
    StartGame() {
        game = null;
        client = null;
        oknoGry = null;
    }

    /**
     * Start game.
     */
    public void startGame() {
        oknoGry  = new OknoGry();
       try {
            client = new Client();
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }

        if (client != null) {
            try {
                game = new Game(client, oknoGry);
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        } else {
            game = new Game(oknoGry);
        }
        oknoGry.addGame(game);
        game.start();
        oknoGry.setVisible(true);
        isStarted = true;
       }

    /**
     * Stop game.
     */
    public void stopGame(){
            isStarted = false;
           System.out.println("xc");
           oknoGry.closeOknoGry();
           game.koniecGry2();
       }
}

