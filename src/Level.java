import java.awt.*;
import java.io.*;

/**
 * The type Poziom.
 */
public class Level {

    private final int BLOCK_SIZE = 22; //rozmiar pojedynczej kratki
    private final int N_BLOCKS = 20; //ilosc kratek 15 x 15

    /**
     * The Width.
     */
    public int width = BLOCK_SIZE * N_BLOCKS ;
    /**
     * The Height.
     */
    public int height = BLOCK_SIZE * N_BLOCKS;

    /**
     * The Max punktow.
     */
    int maxPunktow = 0;

    private short[] screenData;
    /**
     * The Level data.
     */
    public short levelData[] = new short[N_BLOCKS * N_BLOCKS ];
    /**
     * The Blok.
     */
    public Blok blok;

    /**
     * Instantiates a new Poziom.
     *
     * @throws IOException the io exception
     */
    public Level() throws IOException {
        screenData = new short[N_BLOCKS * N_BLOCKS];
        maxPunktow = ileZostaloPunktow();

    }

    /**
     * Ile zostalo punktow int.
     *
     * @return the int
     */
    public int ileZostaloPunktow() {
        int ileZostaloPunktow = 0;
        for (int i = 0; i < N_BLOCKS; i++) {
            for (int j = 0; j < N_BLOCKS; j++) {
                this.screenData[i * N_BLOCKS + j] = this.levelData[i * N_BLOCKS + j];
                if (this.screenData[i * N_BLOCKS + j] == 2) {
                    ileZostaloPunktow++;
                }
            }
        }
        return ileZostaloPunktow;
    }

    /**
     * Gets max punktow.
     *
     * @return the max punktow
     */
    public int getMaxPunktow() {
        return maxPunktow;
    }


    /**
     * Read level.
     *
     * @param path the path do awaryjnej mapy
     * @throws IOException the io exception
     */
    public void readLevel(String path) throws IOException {
        File file = new File(path);

        BufferedReader br = new BufferedReader(new FileReader(file));
        int k = 0;
        String st;
        while ((st = br.readLine()) != null) {
            k = initLevel(k, st);
        }
    }

    private int initLevel(int k, String str) {
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) == '1' || str.charAt(i) == '2' || str.charAt(i) == '0') {
                levelData[k] = (short) Character.getNumericValue(str.charAt(i));
              //  System.out.print(levelData[k] + " ");
                k++;
            }
        }
        maxPunktow = this.ileZostaloPunktow();
        return k;
    }
    public void initLevel2(String str) {
        int temp = 0;
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) == '1' || str.charAt(i) == '2' || str.charAt(i) == '0') {
                levelData[temp++] = (short) Character.getNumericValue(str.charAt(i));
            }
        }
        maxPunktow = this.ileZostaloPunktow();
    }

    /**
     * Check blok int. dla pacmana
     *
     * @param nextX the next x
     * @param nextY the next y
     * @return the int
     */
    public int checkBlok(int nextX, int nextY) {

        Rectangle bound = new Rectangle(nextX, nextY, 22, 22);

        for (int i = 0; i < N_BLOCKS; i++) {
            for (int j = 0; j < N_BLOCKS; j++) {
                if (this.levelData[i * N_BLOCKS + j] == 2) {
                    blok = new Blok(i * 22, j * 22);
                    if (bound.intersects(blok)) {
                        levelData[i * N_BLOCKS + j] = 0;
                        return 2;
                      }
                    }else if (this.levelData[i * N_BLOCKS + j] == 1) {
                        blok = new Blok(i * 22, j * 22);
                        if (bound.intersects(blok)) {
                            return 0;
                        }
                    }
            }
        }
        return 1;
    }

    /**
     * Check blok 2 int. dla duchow
     *
     * @param nextX the next x
     * @param nextY the next y
     * @return the int
     */
    public int checkBlok2(int nextX, int nextY) {
        Rectangle bound = new Rectangle(nextX, nextY, 22, 22);
        for (int i = 0; i < N_BLOCKS; i++) {
            for (int j = 0; j < N_BLOCKS; j++) {
                if (this.levelData[i * N_BLOCKS + j] == 1) {
                    blok = new Blok(i * 22, j * 22);
                    //    System.out.print (i * 22+ ", " + j * 22 + " ");
                    if (bound.intersects(blok)) {
                        return 0;
                    }
                }
            }
        }
        return 1;
    }

    /**
     * Draw Level.
     *
     * @param g the g
     */
//render dla poziomu
    public void drawLevel(Graphics g) {

        for(int i = 0; i < N_BLOCKS; i++) {
            for(int j = 0; j<N_BLOCKS; j++){
                if( this.levelData[i*N_BLOCKS + j] == 1) {
                    blok = new Blok(i * 22, j * 22);
                    blok.render(g);
                }
                else if( this.levelData[i*N_BLOCKS + j] == 2) {
                    new Punkt(i * 22, j * 22).render(g);
                }
            }
        }
    }
}
