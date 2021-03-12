package com.server2;

import java.io.*;
import java.util.ArrayList;

/**
 * The type File manager.
 */
public class FileManager {
    /**
     * The Path.
     */
    String path;

    /**
     * The Lista level.
     */
    ArrayList<File> listaLevel = new ArrayList<>();
    private int levelCount = 0;
    /**
     * The Result file.
     */
    File resultFile;

    /**
     * Instantiates a new File manager.
     *
     * @throws IOException the io exception
     */
    FileManager() throws IOException {
        listOfLevel();
    }


    private void listOfLevel() throws IOException {
       File fileName = new File(".");

        File[] matchingFiles = fileName.listFiles(new FilenameFilter() {
            public boolean accept(File dir, String name) {
                return name.startsWith("level") && name.endsWith("txt");
            }
        });

        for(File fileTemp : matchingFiles){
            listaLevel.add(fileTemp);
            levelCount++;
        }
    }

    /**
     * Next level string.
     *
     * @return the string
     * @throws IOException the io exception
     */
    public String nextLevel() throws IOException {
        //tutaj jest czary mary
        if(levelCount-- >0){
         return readLevel(listaLevel.get(levelCount));
        }
        System.out.println("[SEREVER] KONIEC MAP");
        return "koniec";
    }


    /**
     * Read level string.
     *
     * @param file the file
     * @return the string
     * @throws IOException the io exception
     */
    public String readLevel(File file) throws IOException {
        StringBuilder resultStringBuilder = new StringBuilder();
        try (BufferedReader br
             = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null)
            {
            resultStringBuilder.append(line);
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
       return resultStringBuilder.toString();
    }


    /**
     * Overide score list.
     *
     * @param str the str
     */
    public void overideScoreList(String str) {
        System.out.println("wyniki otrzymane od klienta: " + str);

        String[] temp = str.split(";");
        int size = 0;
        String[] wynik;
        ArrayList<Score> scores = new ArrayList<>();

        for (String st : temp) {
            String[] temp2 = st.split(" ");
            scores.add(new Score(temp2[0], Integer.parseInt(temp2[1])));
            size++;
        }
        ScoreList scoreList = new ScoreList(scores);
        System.out.println(scoreList.printScoreList());
        scoreList.sort();
        System.out.println(scoreList.printScoreList());

        resultFile = new File("highScoreServer.txt");
        FileWriter myWriter = null;
        try {
            myWriter = new FileWriter(resultFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            myWriter.write(scoreList.printScoreList());
            myWriter.close();
            System.out.println("[SERVER] Successfully overided score list.");
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    /**
     * Overide score list.
     *
     * @throws IOException the io exception
     */
    public void overideScoreList() throws IOException {
            String str  ="";
            str = readLevel(new File("highScoreServer.txt"));
            System.out.println("wyniki z serwera " + str);

            String[] temp = str.split(";");
            int size = 0;
            String[] wynik;
            ArrayList<Score> scores = new ArrayList<>();

            for(String st: temp){
                String[] temp2 = st.split(" ");
                scores.add(new Score(temp2[0], Integer.parseInt(temp2[1])));
                size++;
            }
            ScoreList scoreList = new ScoreList(scores);
            System.out.println(scoreList.printScoreList());
            scoreList.sort();
            System.out.println(scoreList.printScoreList());

            resultFile = new File("highScoreServer.txt");
            FileWriter myWriter = null;
            try {
                myWriter = new FileWriter(resultFile);
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                myWriter.write(scoreList.printScoreList());
                myWriter.close();
                System.out.println("[SERVER] Successfully overided score list.");
            } catch (IOException e) {
                System.out.println("An error occurred.");
                e.printStackTrace();
            }
    }
}
