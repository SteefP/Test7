package h31;

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

	public class ConnectJob implements Runnable {

		TextArea ta;
		Socket socket;

		public ConnectJob(TextArea ta, Socket socket) {
			this.ta = ta;
			this.socket = socket;
		}

		@Override
		public void run() {
			InetAddress inetAddress = socket.getInetAddress();
			ta.appendText("clients ip adress = "+inetAddress.getHostAddress());

			

			try {
				while(true){
			
					String request;

					// Compute area
					double area;
				// Create data input and output streams
				DataInputStream inputFromClient = new DataInputStream(socket.getInputStream());
				DataOutputStream outputToClient = new DataOutputStream(socket.getOutputStream());
				request = (inputFromClient).readLine();
				
				if(request.contains("GET")&&(request.contains("HTTP")){
					
				}
				area = radius * radius * Math.PI;
				// Send area back to the client
				outputToClient.writeDouble(area);
				Platform.runLater(() -> {
					ta.appendText("Radius received from client: " 
							+ radius + '\n');
					ta.appendText("Area is: " + area + '\n'); 
				});
				}
			} catch (Exception e) {
				// TODO: handle exception
			}
			
		}
	}

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

				while (true) {
					Socket socket = serverSocket.accept();

					Thread thread = new Thread(new ConnectJob(ta, socket));
					
					thread.start();

				}
			}
			catch(IOException ex) {
				ex.printStackTrace();
			}
		}).start();
	}

	/**
	 * The main method is only needed for the IDE with limited
	 * JavaFX support. Not needed for running from the command line.
	 */
	public static void main(String[] args) {
		launch(args);
	}
}


