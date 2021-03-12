import java.io.*;
import java.util.ArrayList;

/**
 * The type Score list.
 */
public class ScoreList {
    /**
     * The File.
     */
    File file;
    /**
     * The Score list array.
     */
    public ArrayList<Score>  scoreListArray = new ArrayList<Score>();

    /**
     * Instantiates a new Score list.
     *
     * @param path the path
     */
    public ScoreList(String path) {
        file = new File(path);
      updateScoreList();
    }
  //  public ScoreList(String str, int i){
   //     updateScoreListServer(str);
   // }

    /**
     * Update score list.
     */
    public void updateScoreList() {
        StringBuilder resultStringBuilder = new StringBuilder();

        try (BufferedReader br
                     = new BufferedReader(new FileReader(file))) {
            String line;
            String name = null;
            while ((line = br.readLine()) != null) {
                resultStringBuilder.append(line);
                        scoreListArray.add(new Score(line));
                name = null;
            }
        }catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Update score list server.
     *
     * @param str the str
     */
    public void updateScoreListServer(String str){
        String[] wyniki = str.split(";");
        for(String st: wyniki )
        scoreListArray.add(new Score(st+";"));
        this.printScoreList();

    };

    /**
     * Overide score list.
     *
     * @param str the str
     */
    public void overideScoreList(String str) {
        try {
            FileWriter myWriter = new FileWriter(file.getName());
            int k =0;
            for(Score temp: scoreListArray) {
                myWriter.write(temp.printScore() + "\n");
            }
            for(int x = scoreListArray.size() - 1; x > 0; x--)
            {
                scoreListArray.remove(x);
            }
           // scoreListArray.remove(0);
            myWriter.write(str + "\n");
            myWriter.close();
            System.out.println("Successfully wrote to the file.");
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    /**
     * Print score list string.
     *
     * @return the string
     */
    public String printScoreList(){
        StringBuilder resultStringBuilder = new StringBuilder();
        for(Score temp: scoreListArray){
            resultStringBuilder.append(temp.printScore() + ";");
        }
        return resultStringBuilder.toString();
    }
}


