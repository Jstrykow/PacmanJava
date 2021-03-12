
import com.sun.source.tree.WhileLoopTree;

import javax.naming.ldap.SortKey;
import javax.xml.stream.util.StreamReaderDelegate;
import java.io.*;
import java.net.Socket;

/**
 * The type Client.
 */
public class Client {
    private static final String SERVER_IP = "127.0.0.1";
    private static final int SERVER_PORT = 4999 ;
    /**
     * The S.
     */
    Socket socket = new Socket();

    BufferedReader inPut;
    PrintWriter out;
    BufferedReader keyboard;

    Client() throws IOException {
        socket = new Socket(SERVER_IP, SERVER_PORT);
        inPut = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        out = new PrintWriter(socket.getOutputStream(),true);
        keyboard = new BufferedReader(new InputStreamReader(System.in));

    }


    public String sendRequestLevel() throws IOException {
        String command = "mapa";
        out.println(command);
        String serverResponse = inPut.readLine();
     //   System.out.println(serverResponse);
        return serverResponse;
    }

    public String sendRequestWyniki() throws IOException {
        String command = "wyniki";
        out.println(command);
        String serverResponse = inPut.readLine();
      //  System.out.println(serverResponse);
        return serverResponse;
    }

    public void sendResults(String str) throws IOException {
        String command = "zapis";
        out.println(command);
        out.println(str);
        System.out.println("wysłano wyniki na serwer");
    }


    /**
     * Receive mess string.
     *
     * @return the string
     * @throws IOException the io exception
     */

    public String receiveMess() throws IOException {
        StringBuilder resultStringBuilder = new StringBuilder();
        InputStreamReader in = new InputStreamReader(socket.getInputStream());
        BufferedReader bf = new BufferedReader(in);
        String str = bf.readLine();
        return str;
    }


}
