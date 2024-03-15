// A Java program for a Client
package djava;
import java.io.*;
import java.net.*;
import java.util.Scanner;

public class Client {
	// initialize socket and input output streams
	private Socket socket = null;
	private DataInputStream input	 = null;
	private PrintWriter out = null;
	private Scanner response = null;

	// constructor to put ip address and port
	public Client(String address, int port)
	{
		// establish a connection
		try {
			socket = new Socket(address, port);
			System.out.println("Connected");

			// takes input from terminal
			input = new DataInputStream(System.in);

			// sends output to the socket
			out = new PrintWriter(socket.getOutputStream());

         // Receives input from the socket
         response = new Scanner(socket.getInputStream());
		}
		catch (UnknownHostException u) {
			System.out.println(u);
			return;
		}
		catch (IOException i) {
			System.out.println(i);
			return;
		}

      out.println("echoMode");

		// string to read message from input
		String line = "";

		// keep reading until "Over" is input
		while (!line.equals("Over")) {
			try {
				line = input.readLine();
            out.println(line);
            out.flush();
				System.out.println(response.nextLine());
			}
			catch (IOException i) {
				System.out.println(i);
			}
		}

		// close the connection
		try {
			input.close();
			out.close();
         response.close();
			socket.close();
		}
		catch (IOException i) {
			System.out.println(i);
		}
	}

	public static void main(String args[])
	{
		Client client = new Client("127.0.0.1", 5000);
	}
}
