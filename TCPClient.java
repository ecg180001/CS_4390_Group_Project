/* Developed by:
 * Emmanuel Giah (ECG180001)
 * Pouria Mohseninejad (PXM220043)
 * Emmanuel Asante (EFA190000)
 * Date: November 1, 2023
 * Class: CS 4390.003
 */
// import java.net.*;
// import java.io.*;

// public class TCPClient {
//     private Socket socket = null;
//     private DataOutputStream out = null;
//     private DataInputStream in = null;

//     public TCPClient(String address, int port) {
//         try {
//             socket = new Socket(address, port);
//             System.out.println("Connected");

//             // sends output to the socket
//             out = new DataOutputStream(socket.getOutputStream());

//             // receives input from the socket
//             in = new DataInputStream(new BufferedInputStream(socket.getInputStream()));
//         } catch (UnknownHostException u) {
//             System.out.println(u);
//         } catch (IOException i) {
//             System.out.println(i);
//         }

//         String line = "";
//         while (!line.equals("exit")) {
//             try {
//                 System.out.print("Enter math expression (or 'exit' to terminate): ");
//                 BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
//                 line = input.readLine();
//                 out.writeUTF(line);
//                 if (!line.equals("exit")) {
//                     String response = in.readUTF();
//                     System.out.println("Server response: " + response);
//                 }
//             } catch (IOException i) {
//                 System.out.println(i);
//             }
//         }

//         // close the connection
//         try {
//             out.close();
//             in.close();
//             socket.close();
//         } catch (IOException i) {
//             System.out.println(i);
//         }
//     }

//     public static void main(String args[]) {
//         TCPClient client = new TCPClient("127.0.0.1", 6789);
//     }
// }
import java.net.*;
import java.io.*;

public class TCPClient {
    private Socket socket = null;
    private DataOutputStream out = null;
    private DataInputStream in = null;

    public TCPClient(String address, int port) {
        try {
            socket = new Socket(address, port);
            System.out.println("Connected");

            // sends output to the socket
            out = new DataOutputStream(socket.getOutputStream());

            // receives input from the socket
            in = new DataInputStream(new BufferedInputStream(socket.getInputStream()));
        } catch (UnknownHostException u) {
            System.out.println(u);
        } catch (IOException i) {
            System.out.println(i);
        }

        String line = "";
        while (!line.equals("exit")) {
            try {
                System.out.print("Enter math expression (or 'exit' to terminate): ");
                BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
                line = input.readLine();
                out.writeUTF(line);
                if (!line.equals("exit")) {
                    String response = in.readUTF();
                    System.out.println(response);
                }
            } catch (IOException i) {
                System.out.println(i);
            }
        }

        // close the connection
        try {
            out.close();
            in.close();
            socket.close();
        } catch (IOException i) {
            System.out.println(i);
        }
    }

    public static void main(String args[]) {
        TCPClient client = new TCPClient("127.0.0.1", 6789);
    }
}
