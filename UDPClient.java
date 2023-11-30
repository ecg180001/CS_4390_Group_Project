import java.io.*; 
import java.net.*; 
  
class UDPClient { 
    public static void main(String args[]) throws Exception { 
        BufferedReader inFromUser = new BufferedReader(new InputStreamReader(System.in)); 
        DatagramSocket clientSocket = new DatagramSocket(); 
        InetAddress IPAddress = InetAddress.getByName("localhost"); // Use localhost for testing
  
        while(true) { 
            System.out.print("Enter math expression or 'exit' to quit: ");
            String sentence = inFromUser.readLine(); 

            // Check if the user wants to exit
            if ("exit".equalsIgnoreCase(sentence)) {
                System.out.println("Client exiting...");
                break;
            }

            byte[] sendData = sentence.getBytes();         
            DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, 9876); 
            clientSocket.send(sendPacket); 
      
            byte[] receiveData = new byte[1024]; 
            DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length); 
            clientSocket.receive(receivePacket); 
      
            String modifiedSentence = new String(receivePacket.getData(), 0, receivePacket.getLength()); 
            System.out.println("FROM SERVER: " + modifiedSentence); 
        } 

        clientSocket.close(); 
    } 
}
