import java.io.*; 
import java.net.*; 

class MathClient { 
  // ... existing code ...

  public static void main(String argv[]) throws Exception { 
    // ... existing code ...

    // Send name to server after establishing connection
    sendName(outToServer);

    // Wait for acknowledgment

    // Send math calculation requests
    sendMathRequest(outToServer, "ADD 10 20");

    // ... Additional code for handling responses and closing connection ...
  }

  private static void sendName(DataOutputStream out) throws IOException {
    // Send client's name to server
  }

  private static void sendMathRequest(DataOutputStream out, String calculation) throws IOException {
    // Send math request to server
  }
}