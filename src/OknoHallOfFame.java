import javax.swing.*;
import java.awt.*;

/**
 * The type Okno hall of fame.
 */
public class OknoHallOfFame extends JFrame {
    private JList list;

    /**
     * Instantiates a new Okno hall of fame.
     */
    OknoHallOfFame() {
        this.setTitle("Hall of Fame");
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); //po wcisnieciu x zamyka okno
        this.setResizable(true); // pozwala na zmiane rozmiaru okna
        this.setLocationRelativeTo(null);
        ImageIcon image3 = new ImageIcon("wixaman.png");
        this.setIconImage(image3.getImage());
        JPanel panelWynikow = new JPanel();
        //this.setSize(300, 300);

        Score score = new Score();
        String wyniki = score.readScore();
        list = new JList(wyniki.split(";"));
        list.setForeground(Color.BLACK); //zmienia kolor tekstu
        list.setFont(new Font("MV Boli", Font.PLAIN, 25)); //zmienia font naszego tekstu
        list.setBackground(Color.yellow); // zmiania kolor tla na bialy
        list.setOpaque(true); // tam gdzie jest wyswietlany
        panelWynikow.add(list);

        this.add(panelWynikow);
        this.pack();
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setVisible(true);
    }

}
