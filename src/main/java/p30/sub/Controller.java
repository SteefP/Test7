package p30.sub;



import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.Component;


@Component
public class Controller {
	
	
	@Autowired @Qualifier("AView")
 	View view;
	
	
	
	
	public Controller(View view) {
		this.view = view;
	}




	public void showView(){
		
		view.show();
		
	}

}
