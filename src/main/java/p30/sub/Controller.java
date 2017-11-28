package p30.sub;



import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.Component;


@Component
public class Controller {
	
	
	@Autowired @Qualifier("TheView")
 	View view;
	
	
	
	
	public Controller() {
	
	}




	public void showView(){
		
		view.show();
		
	}

}
