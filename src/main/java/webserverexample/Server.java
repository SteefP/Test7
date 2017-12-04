package webserverexample;



import java.io.*;
import java.net.*;
import java.util.Date;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

import java.io.*;
import java.net.*;
import java.util.Date;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

public class Server extends Application {
	@Override // Override the start method in the Application class
	public void start(Stage primaryStage) {
		// Text area for displaying contents
		TextArea ta = new TextArea();

		// Create a scene and place it in the stage
		Scene scene = new Scene(new ScrollPane(ta), 450, 200);
		primaryStage.setTitle("Server"); // Set the stage title
		primaryStage.setScene(scene); // Place the scene in the stage
		primaryStage.show(); // Display the stage

		new Thread( () -> {
			try {
				// Create a server socket
				ServerSocket serverSocket = new ServerSocket(8000);
				Platform.runLater(() ->
				ta.appendText("Server started at " + new Date() + '\n'));

				// Listen for a connection request
				Socket socket = serverSocket.accept();

			

				InputStreamReader isReader = new InputStreamReader(socket.getInputStream());

				String HEADER_CONTENT_TYPE_TEXT_HTML = "Content-Type: text/html";
				while (true) {
					// Receive radius from the client
					BufferedReader bReader = new BufferedReader(isReader);
					String input = bReader.readLine();
					System.out.println(input);

					

					// Compute area


					// Send area back to the client
					String out= null;
					try {
						out = readFile();
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} // bijv index.html of een andere indien daarom gebraagd wordt
					String outString = "HTTP/1.1 200 OK\r\n" + HEADER_CONTENT_TYPE_TEXT_HTML + "\r\n\r\n" + out;
					System.out.println(outString);
					socket.getOutputStream().write(outString.getBytes("UTF-8"));

					Platform.runLater(() -> {

					
					});
				}
			}
			catch(IOException ex) {
				ex.printStackTrace();
			}
		}).start();
	}

	private String readFile() throws Exception {
		File myFile = new File ("index.txt");  // lees dit als verwijzing naar een locatie denk ik
		FileReader fileReader = new FileReader(myFile);
		BufferedReader reader = new BufferedReader(fileReader); // gaat aleemaal in elkaar/ verwijst trapsgewijs


		String line = reader.readLine();

		
		return line;
	}

	/**
	 * The main method is only needed for the IDE with limited
	 * JavaFX support. Not needed for running from the command line.
	 */
	public static void main(String[] args) {
		launch(args);
	}
}
