package p30.sub;



import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.Component;


@Component
public class Controller {
	
	
	@Autowired @Qualifier(value = "TheView")
 	View view1;
	
	
	
	
	public Controller() {
	
	}




	public void showView(){
		
		view1.show();
		
	}

}
