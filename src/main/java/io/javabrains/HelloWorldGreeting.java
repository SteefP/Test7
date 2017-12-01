package io.javabrains;

public class HelloWorldGreeting implements Greeting {

	@Override
	public void perform() {
		System.out.println("Hello World G");
	}
	
	
	// het is eigenlijk het veel koter schrijven van een functctie in loss code
	// dit kan korter geschreven worden, public is niet nodig voor losse code, naam is ook overboidg, want je wijst het al toe , return hoeft eigenoijk ook niet, de compiler kan dat ook gewon zien aan de code dan blijgft over
	//() -> {
	//	System.out.println("Hello World");
	// allen een -> moet je toeveogen
	
	//  a Block of code = () -> System.out.println("Hello World");

	/*
	greetingFunction = () -> System.out.println("Hello World");
	
	
	greet(() -> System.out.println("Hello World"))
	greet(greetingFunction);
	
	
	doubleNumberFunction = (int a) ->  a * 2;
	
	addFunction = (int a, int b) -> a + b;
	
	saveDivideFunction = (int a, int b) -> {
		if(b==0) return 0;
		return a/b};
	}

	stringLengthCountFunction = (Sting s) -> s.length();
*/
}
