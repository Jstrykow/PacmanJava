package com.server2;
import javax.swing.*;
import java.awt.event.KeyAdapter;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.security.PrivateKey;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


/**
 * The type Main.
 */
public class Main {
private static ArrayList<ClientHandler> clients = new ArrayList<>();
private static final int PORT = 4999;



    private static ExecutorService pool = Executors.newFixedThreadPool(4);

    /**
     * Instantiates a new Main.
     *
     * @throws IOException the io exception
     */
    public Main() throws IOException {
    }

    /**
     * The entry point of application.
     *
     * @param args the input arguments
     * @throws IOException the io exception
     */
    public static void main(String[] args) throws IOException {

        ServerSocket listener = new ServerSocket(PORT);
        JFrame frame = new JFrame();
        JOptionPane.showMessageDialog(frame,
                "Serwer działa - akceptuj, aby rozpoczac");
       // frame.setVisible(true);
        while (true){
            System.out.println("[SERVER] Czekam na klientow");
            Socket client = listener.accept();
            System.out.println("[SERVER] Połaczono z klientem");
            ClientHandler clientThread = new ClientHandler(client);
            clients.add(clientThread);
            pool.execute(clientThread);
        }
    }

  //  FileManager fileManager = new FileManager();
  //  fileManager.overideScoreList("test 9;test 8;test3 7;ala 6;kot 5;pies 4");
}

