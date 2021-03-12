import java.io.IOException;

/**
 * The type Main.
 */
public class Main {



    public Main() throws IOException {
    }

    /**
     * The entry point of application.
     *
     * @throws IOException the io exception when frame cannot be build
     */
    public static void main(String[] args) {

        /*try{
           Client client = new Client();
           Game game = new Game(client);
           Score score = new Score();
           score.updateScoreFromServer(client.sendRequestWyniki());
           new Menu(game, client);
       } catch (IOException e){
           System.out.println(e);
           try {
               Game game = new Game();
               new Menu(game);
           }catch (IOException e2){
               System.out.println("error");
           }
       }*/
        new Menu();

    }
}

