package p30.sub;

import org.springframework.stereotype.Component;

@Component("TheView")
public class View1 implements View {
	
	public void show(){
		
		System.out.println("Hello Hello");
		
	}

}
