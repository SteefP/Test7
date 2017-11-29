package chatserver;

import java.io.*;
import java.net.*;


public class DailyAdviceServer2 {


	String[] adviceList2 = {"Take smaller bites", "Go for the tight jeans. No they NOT make you look fat.", "One word: inappropriate", "Just for today, be honest. Tell your boss what you *really* think", "You might want to rethink that haircut"};

	public void go() {

		try {
			ServerSocket serverSock = new ServerSocket(4242);

			while(true){

				Socket sock = serverSock.accept();

				PrintWriter writer = new PrintWriter(sock.getOutputStream());   // dit is dus degene die praat aan de server kant de vraag is  waar moet naartoe schrijven? en deze wrodt beantwoord binnen de haakjes, net als de reader vraagt, waar moet ik dan lezen?
				String advice = getAdvice();
				writer.println(advice);                // dus niet naar de console, maar naar als printer naar de outputstream naar de client
				writer.close();
				System.out.println(advice);
			}


		} catch(IOException ex){
			ex.printStackTrace();
		}

	}

	public String getAdvice(){
		int rand = (int)(Math.random()* 5);
		return adviceList2[rand];
	}


	public static void main(String[] args){
		DailyAdviceServer2 server = new DailyAdviceServer2();
		server.go();

	}
}