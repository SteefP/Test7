package p30;

import java.util.ArrayList;
import java.util.Properties;
import java.io.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.*;

import p30.sub.*;
import p30.sub.Controller;


@Configuration
@ComponentScan
public class Application {

	@Autowired Controller controller;

	public static void main(String[] args) {

		ApplicationContext context = new AnnotationConfigApplicationContext(Application.class);

		Application app = context.getBean(Application.class);
		
		app.start();



	}

	public void start(){

		controller.showView();

	}

	
	public View getView(){
		View theView = null;
		Properties properties = new Properties();
		try(FileReader reader =  new FileReader("src/main/resources/META-INF/config")) {

			properties.load(reader);

		}catch (Exception e) {;
		e.printStackTrace();
		}
		String ViewType = properties.getProperty("ViewType");
		if(ViewType.equals("View1")){
			theView = new View1();
		} else {
			theView = new View2();
		}


			return theView; 
	}
}