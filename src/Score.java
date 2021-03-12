import java.io.*;

/**
 * The type Score.
 */
public class Score{

    private String name;
    private int result;
    private File file;

    /**
     * Instantiates a new Score.
     *
     * @param n the n
     */
    public Score(String n) {
        name = n;
    }

    /**
     * Instantiates a new Score.
     */
    public Score() {
    file = new File("highScore.txt");
    //System.out.println("otwaro plik z wynikami");
    }


    /**
     * Read score string.
     *
     * @return the string
     */
    public String readScore()  {
        StringBuilder resultStringBuilder = new StringBuilder();
        try (BufferedReader br
                     = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                resultStringBuilder.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return resultStringBuilder.toString();
    }

    /**
     * Add score.
     *
     * @param name2   the name 2
     * @param result2 the result 2
     * @throws IOException the io exception
     */
    public void addScore(String name2, int result2) throws IOException {
         FileWriter fileWriter = new FileWriter(file.getName(), true);
         fileWriter.write(name2+" "+ result2 + ";");
         fileWriter.close();
         System.out.println("score added to file" + name2 +" " + result2);
    }

    /**
     * Update score from server.
     *
     * @param str the str
     * @throws IOException the io exception
     */
    public void updateScoreFromServer(String str) throws IOException {
        FileWriter fileWriter = new FileWriter(file.getName(), false);
        fileWriter.write(str);
        fileWriter.close();
        System.out.println("updateScoreFromServer from Server " + str);
        readScore();
    }


    /**
     * Gets name.
     *
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * Print score string.
     *
     * @return the string
     */
    public String printScore() {
        return name;
    }
}
