package io.javabrains;

public class Greeter {

	public void greet(Greeting greeting){
		greeting.perform();
	}

	public static void main(String[] args) {


		Greeting helloWorldGreeting = new HelloWorldGreeting(); // instance of class implementing a greeting interface
		Greeting lambdaGreeting = () -> System.out.println("Hello World L!");  // maar lijtk het wel of er ook een soort object word gemaakt, maar er is echt een verschil, maar eignelijk is het wel hetzelfde als hieronder het maken van een anonymous inner class, mmar voor nu doen e het velfde ik kan de methode aanroepen, en m in een nethode stoppen
		


		Greeting innerClassGreeting = new Greeting(){    // annonymus inner class  nieuwe isntance met 
			public void perform(){
				System.out.println("Hello World A!");
			}
		};


		innerClassGreeting.perform();
		lambdaGreeting.perform();
		helloWorldGreeting.perform();
		Greeter greeter = new Greeter();
		greeter.greet(lambdaGreeting);
		MyAdd addFunction = (int a, int b) -> a + b;



	}

}



interface MyAdd{

	int add(int a, int b);

}
