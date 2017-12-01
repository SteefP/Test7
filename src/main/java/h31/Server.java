package h31;

import java.io.*;
import java.net.*;
import java.time.LocalDate;
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
			ta.appendText("clients ip adress = "+inetAddress.getHostAddress()+ '\n');



			try {
				while(true){
				
					// Create data input and output streams
					ObjectInputStream inputFromClient = new ObjectInputStream(socket.getInputStream());
					ObjectOutputStream outputToClient = new ObjectOutputStream(socket.getOutputStream());
					Request request = (Request) inputFromClient.readObject();

					
					String message=null;
					LocalDate date= LocalDate.now();
					String server="SteefsServer";
					String body=null;
					
					ta.appendText(request.requestType + '\n');
					ta.appendText("GET" + '\n');
					if(request.requestType.equals("GET")){
					
						message = "HTTP/"+request.protocolVersie+ " 200 OK";
					}
			
					
					if(request.resource.equals("index.html")){
						body = "<html><head>Steef P</head><body>En toen ging steef...</body></html>";
					}
					
					Answer answer = new Answer();
					answer.setMessage(message);
					answer.setDate(date);
					answer.setServer(server);
					answer.setBody(body);
					
					outputToClient.writeObject(answer);
					Platform.runLater(() -> {
						// tbd
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


