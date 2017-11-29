package chatservermetantwoord;
import javax.swing.*;
import java.io.*;
import java.awt.*;
import java.net.*;
import java.awt.event.*;   // action listeners

public class SimpleChatClient {

	JTextArea incoming;
	JTextField outgoing;
	PrintWriter writer;
	BufferedReader reader;
	Socket sock;
	
	JFrame frame;
	JPanel panel;
	
	boolean incomingCreated = false;

	public void go(){

		outgoing = new JTextField(20);
		


	


		frame = new JFrame("Ludicrously Simple Chat Client2");
		panel = new JPanel();
		JButton button = new JButton("Send");
		button.addActionListener(new SendButtonListener());
		
		frame.add(panel);
	
		panel.add(outgoing);
		panel.add(button);

		frame.setVisible(true);
		frame.setSize(800, 500);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		initIncoming();
		setUpNetworking();
		Thread readerThread = new Thread(new readJob());
		readerThread.start();
	}
	
	public void initIncoming(){
		System.out.println("Incoming shouldbe created");
		incoming = new JTextArea(15,50);
		incoming.setLineWrap(true);
		incoming.setWrapStyleWord(true);
		incoming.setEditable(false);
		JScrollPane qScroller = new JScrollPane(incoming);
		qScroller.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		qScroller.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		panel.add(qScroller);
		frame.revalidate();
		
	}


	public void setUpNetworking(){
		try{
			sock = new Socket("127.0.0.1", 4000);
			reader = new BufferedReader(new InputStreamReader(sock.getInputStream()));
			writer = new PrintWriter(sock.getOutputStream());
			System.out.println("Networking established");
		} catch (Exception ex) {ex.printStackTrace();}
	}


	public static void main(String[] args) {
		SimpleChatClient chat = new SimpleChatClient();
		chat.go();
	}

	public class SendButtonListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			try {
				writer.println(outgoing.getText());
				writer.flush();  // flush is used to write any buffered output to the straeam (lijkt me dus neit persé nodig)
			} catch (Exception ex){ex.printStackTrace();}
			outgoing.setText("");
			outgoing.requestFocus();
		}
	}

	public class readJob implements Runnable {
		public void run(){
			String message;
			try {
				while((message = reader.readLine()) !=null){       // dit vind ik wel een vreemde expressie want het gaat hier om een pointer die niet null mag zijn
					System.out.println("read "+ message);
					
					incoming.append(message+ "\n");
					
				}
			} catch (Exception ex){ex.printStackTrace();}
		}
	}
}