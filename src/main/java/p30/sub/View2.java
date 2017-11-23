package p30.sub;

import javax.inject.Qualifier;

import org.springframework.stereotype.Component;

@Component("AView")
public class View2 implements View {
	

public void show(){
		
		System.out.println("Hi Hi");
		
	}

}
