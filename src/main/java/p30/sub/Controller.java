package p30.sub;

import javax.inject.Qualifier;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class Controller {
	
	
	@Autowired @Qualifier("View2")
 	View view2;
	
	
	
	
	public Controller(View view) {
		this.view = view;
	}




	public void showView(){
		
		view.show();
		
	}

}
