import java.io.*;
import java.net.*;

class ClientHandler implements Runnable {
    private Socket clientSocket;

    public ClientHandler(Socket socket) {
        this.clientSocket = socket;
    }

    public void run() {
        try {
            BufferedReader inFromClient = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            DataOutputStream outToClient = new DataOutputStream(clientSocket.getOutputStream());

            String clientSentence;
            String response;

            while ((clientSentence = inFromClient.readLine()) != null) {
                // Process the client's message, which is a math operation in your case
                response = processClientMessage(clientSentence);

                // Send the response back to the client
                outToClient.writeBytes(response + "\n");
            }

        } catch (IOException e) {
            System.out.println("Error handling client #" + this.clientSocket.getRemoteSocketAddress() + ": " + e.getMessage());
        } finally {
            try {
                if (clientSocket != null) {
                    clientSocket.close();
                }
            } catch (IOException e) {
                System.out.println("Error closing connection: " + e.getMessage());
            }
        }
    }

    private String processClientMessage(String clientSentence) {
        // Implement your logic to process the client's message here
        // For example, parsing the math operation and returning the result
        String response;
        // ... (process the request)
        response = clientSentence.toUpperCase(); // Placeholder for actual math operation processing
        return response;
    }
}
