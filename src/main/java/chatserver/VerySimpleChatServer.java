package chatserver;
import java.io.*;
import java.net.*;
import java.util.*;


public class VerySimpleChatServer {

	ArrayList clientOutputStreams;               /// om ze in te bewaren??

	public class ClientHandler implements Runnable {    // inner class wanneer en hoe dit zo mag/moet weet ik niet

		BufferedReader reader;
		Socket sock;

		public ClientHandler(Socket clientSocket) {
			try{
				sock = clientSocket;
				InputStreamReader isReader = new InputStreamReader(sock.getInputStream());
				reader = new BufferedReader(isReader);

			} catch (Exception ex){ex.printStackTrace();}
		}

		public void run(){

			String message;
			try {
				while((message = reader.readLine()) !=null){
					System.out.println( "read " +message);
					tellEveryone(message);
				}
			} catch (Exception ex){ex.printStackTrace();}
		}
	}

	public static void main (String[] Args) {
		new VerySimpleChatServer().go();
	}

	public void go(){
		clientOutputStreams = new ArrayList();
		try {
			ServerSocket serverSock = new ServerSocket(4000);

			while(true) {
				Socket clientSocket = serverSock.accept();
				System.out.println("The port for thie message is: "+clientSocket.getPort());  // dit kan altijd ook hé niet met een contructor maar direct inhoud geven (kan mss maar soms) wel apart zo ineens met accept
				PrintWriter writer = new PrintWriter(clientSocket.getOutputStream());
				clientOutputStreams.add(writer);

				Thread t = new Thread(new ClientHandler(clientSocket)); // hier wordt denk ik het poort nummer van de verb doorgegeven voor de client, dit is dan inclusief de hele socket niet allen nr?
				t.start();
				System.out.println("Got a connection");

			}


		} catch (Exception ex) {ex.printStackTrace();}
	}

	public void tellEveryone(String message){
		
		Iterator it = clientOutputStreams.iterator();
		while(it.hasNext()) {
			try {
				PrintWriter writer = (PrintWriter) it.next();    // nu worder de writers er weer uitgehaald en gecast als writer dus iedere keer een nieuwe writer met een nieuwe socket om te printen?? ik denk vandaar de trouble
				writer.println(message);
				
				writer.flush();
			
			} catch(Exception ex){ex.printStackTrace();}
		}
		
	}

}




