import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

/**
 * The type Okno menu.
 */
public class OknoMenu extends JFrame {
    /**
     * The Panel obraz.
     */
    JPanel panelObraz = new JPanel();
    /**
     * The Panel przyciski.
     */
    JPanel panelPrzyciski = new JPanel();
    /**
     * The Start button.
     */
    JButton startButton = new JButton();
    /**
     * The Results button.
     */
    JButton newGameButton = new JButton();
    /**
     * The Results button.
     */
    JButton resultsButton = new JButton();
    /**
     * The Level 1. prośba do serwera o mapę o nazwie level1_s
     */
    JLabel label;
    /**
     * The Server connection.
     */
    JButton serverConnection = new JButton();

    /**
     * Instantiates a new Okno menu.
     *
     * @param panelObraz2     the panel obraz 2
     * @param panelPrzyciski2 the panel przyciski 2
     * @param startButton2    the start button 2
     * @param resultsButton2  the results button 2
     */
    OknoMenu(  JPanel panelObraz2,
               JPanel panelPrzyciski2,
               JButton startButton2,
               JButton resultsButton2 ) {
        this.panelObraz = panelObraz2 ;
        this.panelPrzyciski =panelPrzyciski2;
        this.startButton = startButton2;
        this.resultsButton = resultsButton2;
        //Tworzenie głowenego okna
        this.setTitle("Pacman - Menu");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //po wcisnieciu x zamyka okno
        this.setResizable(true); // pozwala na zmiane rozmiaru okna
        this.setSize(750, 700);


        ImageIcon image = new ImageIcon("eiti.jpg"); //obraz tla

        Border border = BorderFactory.createLineBorder(Color.black, 3);

        label = new JLabel(); //tworzy label
        label.setText("Siedz na wykladach, aby umiec napisac Pacmana!"); // wypisuje napis zachecajacy do gry
        label.setIcon(image);
        label.setVerticalTextPosition(JLabel.CENTER);
        label.setHorizontalTextPosition(JLabel.CENTER); // tekst w poziomie
        label.setForeground(Color.BLUE); //zmienia kolor tekstu
        label.setFont(new Font("MV Boli", Font.PLAIN, 20)); //zmienia font naszego tekstu
        label.setIconTextGap(-10); // ustawia lokalizacje wzgledem srodka obrazaka
        label.setBackground(Color.white); // zmiania kolor tla na bialy
        label.setOpaque(true); // tam gdzie jest wyswietlany
        label.setBorder(border);
        label.setVerticalAlignment(JLabel.CENTER); // ustawia pozycje wertykalna icony i tekstu
        label.setHorizontalAlignment(JLabel.CENTER); //ustawia pozycje horyzontalna icony i tekstu
        panelObraz.setBounds(0, 0, 500, 500);
        panelObraz.add(label);


        //ikona usmiechnietego pacmana
        ImageIcon imageHappyPacman = new ImageIcon("pacman.png");
        this.setIconImage(imageHappyPacman.getImage());

        //guzik rozpoczynajacy pacmana
        /**
         * Guzik rozpoczynajacy pacmana
         */

        startButton.setBounds(0, 0, 200, 50);
        startButton.setText("Start Pacman");
        startButton.setFocusable(false);
        startButton.setIcon(imageHappyPacman);
        startButton.setHorizontalTextPosition(JButton.CENTER);
        startButton.setVerticalTextPosition(JButton.BOTTOM);
        startButton.setVerticalAlignment(JButton.CENTER);
        startButton.setHorizontalAlignment(JButton.CENTER);
        panelPrzyciski.setBounds(0, 580, 750, 100);
        panelPrzyciski.add(startButton);
        this.add(panelPrzyciski);

        /**
         * Guzik pokazujący najlepsze wyniki
         */
        ImageIcon trophyIcon = new ImageIcon("trophy.png");
        resultsButton.setBounds(0, 0, 200, 50);
        resultsButton.setText("Hall of Fame");
        resultsButton.setFocusable(false);
        resultsButton.setIcon(trophyIcon);
        resultsButton.setHorizontalTextPosition(JButton.CENTER);
        resultsButton.setVerticalTextPosition(JButton.BOTTOM);
        resultsButton.setVerticalAlignment(JButton.CENTER);
        resultsButton.setHorizontalAlignment(JButton.CENTER);
        panelPrzyciski.add(resultsButton);
        this.add(panelPrzyciski);

        this.add(panelObraz);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }

}
