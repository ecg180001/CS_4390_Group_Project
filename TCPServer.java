import java.net.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.io.*;

public class TCPServer {
    private ServerSocket server = null;

    // A simple method to evaluate basic math expressions
    private static double evaluateMath(String expression) throws Exception {
        Pattern pattern = Pattern.compile("([0-9]+\\.?[0-9]*)\\s*([+\\-*/])\\s*([0-9]+\\.?[0-9]*)");
        Matcher matcher = pattern.matcher(expression);
        if (matcher.find()) {
            double operand1 = Double.parseDouble(matcher.group(1));
            String operator = matcher.group(2);
            double operand2 = Double.parseDouble(matcher.group(3));

            switch (operator) {
                case "+": return operand1 + operand2;
                case "-": return operand1 - operand2;
                case "*": return operand1 * operand2;
                case "/": return operand1 / operand2;
                default: throw new Exception("Invalid operation");
            }
        } else {
            throw new Exception("Invalid expression");
        }
    }

    public TCPServer(int port) throws Exception {
        try {
            server = new ServerSocket(port);
            System.out.println("Server started");

            while (true) {
                System.out.println("Waiting for a client ...");
                Socket socket = server.accept();
                System.out.println("Client accepted");

                // takes input from the client socket
                DataInputStream in = new DataInputStream(new BufferedInputStream(socket.getInputStream()));
                DataOutputStream out = new DataOutputStream(socket.getOutputStream());

                String line = "";
                while (!line.equals("exit")) {
                    try {
                        line = in.readUTF();
                        System.out.println("Client sent: " + line);
                        if (!line.equals("exit")) {
                            try {
                                // Here you can call a method to process the arithmetic operation
                                String result = "Result: " + evaluateMath(line); // Replace with actual processing
                                out.writeUTF(result);
                            } catch (Exception e) {
                                String result = "Error: " + e.getMessage();
                                out.writeUTF(result);
                            }
                        }
                    } catch (IOException i) {
                        System.out.println(i);
                        break;
                    }
                }
                System.out.println("Closing connection with the current client");

                // close connection
                socket.close();
                in.close();
                out.close();
            }
        } catch (IOException i) {
            System.out.println(i);
        }
    }

    public static void main(String args[]) {
        try {
            TCPServer server = new TCPServer(6789);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

}
