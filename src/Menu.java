import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

/**
 * The type Menu.
 */
public class Menu extends JFrame implements ActionListener {

    JFrame hallFrame = new JFrame();

    JPanel panelObraz = new JPanel();

    JPanel panelPrzyciski = new JPanel();

    JButton startButton = new JButton();

    JButton resultsButton = new JButton();

    JButton serverConnection = new JButton();


    private OknoMenu  oknoMenu;
    private StartGame startGame;

       public Menu() {
        this.oknoMenu = new OknoMenu(panelObraz,panelPrzyciski,startButton, resultsButton );
        startGame = new StartGame();
        startButton.addActionListener(this);
        resultsButton.addActionListener(this);
        serverConnection.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if(e.getSource() == startButton ) {
            startGame.startGame();
        }
        if(e.getSource() == resultsButton){
            this.hallFrame = new OknoHallOfFame();
           hallFrame.setVisible(true);
       // startGame.stopGame();
        }

    }
}


