import java.io.*;
import java.net.*;
import java.util.Scanner;
/**
 * Client Class
 *
 * This is a class that communicates with the server to display options that a user can do
 *
 * @author Andrew Samayoa, Lab L33
 * @version Nov 23, 2025
 */
public class Client implements ClientInterface {
    public static void main(String[] args) {
        try (Scanner scan = new Scanner(System.in);
             Socket socket = new Socket("localhost", 6767);
             BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
             PrintWriter writer = new PrintWriter(socket.getOutputStream(), true);) {
            String welcome = reader.readLine();
            String options = reader.readLine();
            System.out.println(welcome);
            System.out.println(options);
            String response = scan.nextLine();
            boolean accountLoop = true;  //loops commands prompt
            boolean isErrorLine = true;  //only prints error line in first section if invalid command is entered
            sendServer(writer, response);
            String fromServer = reader.readLine();
            while (accountLoop) {
                while (fromServer.contains("ERROR")) {
                    if (isErrorLine) {
                        System.out.println(fromServer.replaceAll("\\|", ", "));
                    }
                    isErrorLine = true;
                    System.out.println(options);
                    response = scan.nextLine();
                    sendServer(writer, response);
                    fromServer = reader.readLine();
                }
                if (fromServer.contains("Please enter your card number:")) {
                    System.out.println(fromServer);
                    String cardNumber = scan.nextLine();
                    writer.println(cardNumber);
                    fromServer = reader.readLine();
                }
                if (fromServer.contains("SUCCESS")) {
                    System.out.println(fromServer.replaceAll("\\|", ", "));
                    isErrorLine = false;
                    fromServer = "ERROR";
                } else if (fromServer.contains("MOVIES")) {
                    String[] lines = fromServer.split("\\|");
                    for (String line : lines) {
                        System.out.println(line);
                    }
                    isErrorLine = false;
                    fromServer = "ERROR";
                } else if (fromServer.contains("BOOKINGS")) {
                    String[] lines = fromServer.split("split");
                    for (String line : lines) {
                        System.out.println(line);
                    }
                    isErrorLine = false;
                    fromServer = "ERROR";
                } else if (response.equalsIgnoreCase("EXIT")) {
                    System.out.println(fromServer);
                    accountLoop = false;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void sendServer(PrintWriter writer, String text) throws IOException {
        writer.write(text);
        writer.println();
    }
}
