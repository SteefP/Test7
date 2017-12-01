package h31;

import java.io.*;
import java.net.*;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class Client extends Application {
	

	@Override // Override the start method in the Application class
	public void start(Stage primaryStage) {
		// Panel p to hold the label and text field
		BorderPane paneForTextField = new BorderPane();
		paneForTextField.setPadding(new Insets(5, 5, 5, 5)); 
		paneForTextField.setStyle("-fx-border-color: green");
		paneForTextField.setLeft(new Label("Enter a request: "));

		TextField tf = new TextField();
		tf.setAlignment(Pos.BOTTOM_RIGHT);
		paneForTextField.setCenter(tf);

		BorderPane mainPane = new BorderPane();
		// Text area to display contents
		TextArea ta = new TextArea();
		mainPane.setCenter(new ScrollPane(ta));
		mainPane.setTop(paneForTextField);

		// Create a scene and place it in the stage
		Scene scene = new Scene(mainPane, 450, 200);
		primaryStage.setTitle("Client"); // Set the stage title
		primaryStage.setScene(scene); // Place the scene in the stage
		primaryStage.show(); // Display the stage

	
		tf.setOnAction(e -> {
			try {
				// IO streams
				
				
				// Create a socket to connect to the server
				Socket socket = new Socket("localhost", 8000);
				
				ObjectOutputStream toServer = new ObjectOutputStream(socket.getOutputStream());
				ObjectInputStream fromServer = new ObjectInputStream(socket.getInputStream());
				
				System.out.println("Trying to connect to server");
				// Socket socket = new Socket("130.254.204.36", 8000);
				// Socket socket = new Socket("drake.Armstrong.edu", 8000);

				// Create an input stream to receive data from the server
		

				// Create an output stream to send data to the server
				
				
				// Get info of text field and make request
				String requestString = tf.getText().trim();
				String[] requestArray =  requestString.split("/");
				String requestType = requestArray[0].trim();
				String resource= requestArray[1].replaceAll(" HTTP", "");
				String protocolVersie = requestArray[2];
				String host;
				System.out.println("Req type: "+requestType);
				Request request = new Request(requestType, resource,protocolVersie );

				// use object writer
				toServer.writeObject(request);
				toServer.flush();
		
				// Get answer from the server
				Answer answer = null;
				try {
		
					answer = (Answer) fromServer.readObject();
				} catch (ClassNotFoundException e1) {
					e1.printStackTrace();
				}
				// Display request and answer
				ta.appendText("Request send: " + request.requestType +" /"+request.resource+ " HTTP/"+request.protocolVersie+"\n");
				ta.appendText("Answer received " + answer.message + '\n');
				ta.appendText("Date " + answer.date.toString() + '\n');
				ta.appendText("Server" + answer.server + '\n');
				ta.appendText("Body" + answer.body + '\n');
			}
			catch (IOException ex) {
				ex.printStackTrace();
			}
		});


	}

	/**
	 * The main method is only needed for the IDE with limited
	 * JavaFX support. Not needed for running from the command line.
	 */
	public static void main(String[] args) {
		launch(args);
	}
}