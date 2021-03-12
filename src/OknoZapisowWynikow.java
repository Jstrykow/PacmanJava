import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.Graphics2D;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;
import java.awt.image.BufferStrategy;
import java.io.IOException;

public class OknoZapisowWynikow extends JFrame {
    int wynik =0;
    private ScoreList scoreList;
    private Client client;
    JFrame frame2;
    JLabel label2;
    JPanel panel = new JPanel();

    OknoZapisowWynikow(int wynik2, Client client) {
        this.client = client;
        this.wynik = wynik2;
        this.frame2 = new JFrame();
        this.panel = new JPanel();
        this.label2 = new JLabel();
    }
    void initOknoZapisowWynikow(){
        label2.setText("Koniec Gry - Twój wynik: " + wynik + " })" ); // wypisuje napis zachecajacy do gry
        label2.setForeground(Color.BLACK); //zmienia kolor tekstu
        label2.setFont(new Font("MV Boli", Font.PLAIN, 25)); //zmienia font naszego tekstu
        label2.setBackground(Color.yellow); // zmiania kolor tla na bialy
        label2.setOpaque(true); // tam gdzie jest wyswietlany
        label2.setVerticalAlignment(JLabel.CENTER); // ustawia pozycje wertykalna icony i tekstu
        label2.setHorizontalAlignment(JLabel.CENTER); //ustawia pozycje horyzontalna icony i tekstu
        label2.setAlignmentX(Component.CENTER_ALIGNMENT);

        frame2.setTitle("Koniec");
        frame2.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame2. setLocationRelativeTo(null);
        frame2.setResizable(true);
        /**
         * projekt wyskakujacego okna z miejscem na wpisanie imienia
         */
        JLabel usernameLabelInPUt = new JLabel("Dodaj Twoje imie do Hall of Fame");
        usernameLabelInPUt.setForeground(Color.BLACK); //zmienia kolor tekstu
        usernameLabelInPUt.setFont(new Font("MV Boli", Font.PLAIN, 25)); //zmienia font naszego tekstu
        usernameLabelInPUt.setBackground(Color.yellow); // zmiania kolor tla na bialy
        usernameLabelInPUt.setOpaque(true); // tam gdzie jest wyswietlany
        usernameLabelInPUt.setVerticalAlignment(JLabel.CENTER); // ustawia pozycje wertykalna icony i tekstu
        usernameLabelInPUt.setHorizontalAlignment(JLabel.CENTER);

        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        usernameLabelInPUt.setAlignmentX(Component.CENTER_ALIGNMENT);

        JButton setNameButton = new JButton("Dodaj imie");
        setNameButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        /**
         * Pobieramy od usera imie i dodajemy wraz z wynikiem do ScoreList
         */
        setNameButton.addActionListener((
                ActionEvent e) -> {
            String userInPut = JOptionPane.showInputDialog(setNameButton, "Twoje imie imie", "Ustaw imie", JOptionPane.OK_OPTION);
            if (userInPut != null) {
                //usernameLabelInPUt.setText(String.valueOf(userInPut));
                String imie = "default";
                imie = String.valueOf(userInPut);
                Score score = new Score();
                try {
                    score.addScore(imie, wynik);
                    if(client != null){
                    try {
                        client.sendResults(score.readScore());
                        System.out.println("wyslano wyniki:" + score.readScore());
                    } catch (IOException e2) {
                        System.out.println(e2);
                    }
                }} catch (IOException ioException) {
                    ioException.printStackTrace();
                }
                frame2.dispose();
            }
        });

        panel.add(Box.createRigidArea(new Dimension(5,10)));
        panel.add(label2);
        panel.add(usernameLabelInPUt);
        panel.add(Box.createRigidArea(new Dimension(5,10)));
        panel.add(setNameButton);
        frame2.add(panel);
        frame2.pack();
        frame2.setVisible(true);
    }

    public void OknoZapisowWynikow(){
        frame2.dispose();
    }
}
