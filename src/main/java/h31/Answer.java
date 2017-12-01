package h31;

import java.io.Serializable;
import java.time.LocalDate;

public class Answer implements Serializable {
	
	String message;
	LocalDate date;
	String server;
	String body;
	
	public String getMessage() {
		return message;
	}
	public LocalDate getDate() {
		return date;
	}
	public String getServer() {
		return server;
	}
	public String getBody() {
		return body;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public void setDate(LocalDate date) {
		this.date = date;
	}
	public void setServer(String server) {
		this.server = server;
	}
	public void setBody(String body) {
		this.body = body;
	}

}
