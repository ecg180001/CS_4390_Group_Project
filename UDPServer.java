import java.io.*; 
import java.net.*; 
import java.util.regex.Matcher;
import java.util.regex.Pattern;

class UDPServer { 

    public static void main(String args[]) throws Exception { 
        DatagramSocket serverSocket = new DatagramSocket(9876); 
        System.out.println("Server is running and ready to handle requests");

        while(true) { 
            byte[] receiveData = new byte[1024]; 
            DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length); 
            serverSocket.receive(receivePacket); 

            // Create a new thread for each request
            Thread thread = new Thread(new ClientHandler(serverSocket, receivePacket));
            thread.start();
        } 
    }
}

class ClientHandler implements Runnable {
    private final DatagramSocket serverSocket;
    private final DatagramPacket receivePacket;

    public ClientHandler(DatagramSocket serverSocket, DatagramPacket packet) {
        this.serverSocket = serverSocket;
        this.receivePacket = packet;
    }

    public void run() {
        try {
            String sentence = new String(receivePacket.getData(), 0, receivePacket.getLength()); 
            InetAddress IPAddress = receivePacket.getAddress(); 
            int port = receivePacket.getPort(); 

            // Log the request with the sender's IP and port
            System.out.println("REQUEST from " + IPAddress + ":" + port + " -> " + sentence);

            // Attempt to parse and calculate the math expression
            String response;
            try {
                response = String.valueOf(evaluateMath(sentence));
            } catch (Exception e) {
                response = "Error: " + e.getMessage();
            }

            byte[] sendData = response.getBytes();
            DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, port);
            serverSocket.send(sendPacket); 
        } catch (IOException e) {
            System.err.println("IOException: " + e.getMessage());
        }
    }

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
}
