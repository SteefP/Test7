package h31;

import java.io.Serializable;

public class Request implements Serializable {

	String requestType;
	String resource;
	String protocolVersie;

	public Request(String requestType, String resource, String protocolVersie) {
		this.requestType = requestType;
		this.resource = resource;
		this.protocolVersie = protocolVersie;

	}
	
}
