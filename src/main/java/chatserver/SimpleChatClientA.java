package chatserver;
import javax.swing.*;
import java.io.*;
import java.awt.*;
import java.net.*;
import java.awt.event.*;   // action listeners


public class SimpleChatClientA {

	JTextField outgoing;
	PrintWriter writer;
	Socket sock;



	public void go(){

		outgoing = new JTextField(20);
		JFrame frame = new JFrame("Ludicrously Simple Chat Client");
		JPanel panel = new JPanel();
		JButton button = new JButton("Send");
		button.addActionListener(new SendButtonListener());

		frame.add(panel);
		panel.add(outgoing);
		panel.add(button);

		frame.setVisible(true);
		frame.setSize(400, 100);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		setUpNetworking();
	}


	public void setUpNetworking(){

		try{
			sock = new Socket("127.0.0.1", 6000);
			writer = new PrintWriter(sock.getOutputStream());
			System.out.println("Networking established");

		} catch (Exception ex) {ex.printStackTrace();}
	}


	public static void main(String[] args) {

		SimpleChatClientA chat = new SimpleChatClientA();
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

}