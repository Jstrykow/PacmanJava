package com.server2;

import java.io.*;
import java.net.Socket;
import java.security.PublicKey;

/**
 * The type Client handler.
 */
public class ClientHandler implements Runnable{
    private Socket client;
    private BufferedReader in;
    private PrintWriter out;
    private FileManager fileManager = new FileManager();

    /**
     * Instantiates a new Client handler.
     *
     * @param clientSocket the client socket
     * @throws IOException the io exception
     */
    public ClientHandler(Socket clientSocket) throws IOException {
        this.client = clientSocket;
        in = new BufferedReader(new InputStreamReader(client.getInputStream()));
        out = new PrintWriter(client.getOutputStream(),true);
    }


    @Override
    public void run() {
        try{
            while(true) {
                String request = in.readLine();
                if(request.contains("mapa")){
                    out.println(fileManager.nextLevel());
                    System.out.println("wysłano mapę");
                } else if (request.contains("wyniki")){
                    fileManager.overideScoreList();
                    String str = fileManager.readLevel(new File("highScoreServer.txt"));
                    out.println(str);
                    System.out.println("wysłano wyniki: " + str);
                } else if (request.contains("zapis")){
                    String str = in.readLine();
                    fileManager.overideScoreList(str);
                }
            }
        } catch(IOException e) {
            System.out.println(e);
        }
            finally {
            out.close();
            try {
                in.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }


}
