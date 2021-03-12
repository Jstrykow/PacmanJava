import javax.swing.*;
import java.awt.*;

/**
 * The type Okno gry.
 */
public class OknoGry extends JFrame {
    /**
     * Instantiates a new Okno gry.
     */
    OknoGry(){
        this.setTitle("PACANMAN - wcinij SPACE aby wlaczyc/zatrzymac gre");

        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); //po wcisnieciu x zamyka okno, mozna to prawic bo zamyka oba
        this.setLocationRelativeTo((Component) null);//srodek
    }

    /**
     * Add game.
     *
     * @param game the game
     */
    public void addGame(Game game){
        this.add(game);
        this.pack();
        this.setLocationRelativeTo(null);
    }

    /**
     * Close okno gry.
     */
    void closeOknoGry(){
        this.setVisible(false);
        this.dispose();
    }

}
