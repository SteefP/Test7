package io.javabrains;
import java.util.function.Predicate;


public class TypeInferenceExample {
	
	
	static String[] Animals = {"Kangaroo", "Kanarie", "Kip", "Shark", "Kat", "Whale"};

	public static void main(String[] args) {  // eignelijk ben je gegeven input en outp ut parameters, nu vrij om de bewerkingsbody te manipuleren ipv de input te veranderen voor dezelfde bewerking
		printLambda(s -> s.length());
		
		StringLengthLambda myLambda = s -> s.length();
		System.out.println(myLambda.getLength("Hallo hallo"));
		
		printIf(Animals, s -> s.charAt(2) == 'n');
		
		String Animal = "Bear";
		Animal.charAt(0);
		
		

	}
	
	public static void printLambda(StringLengthLambda l) {
		System.out.println(l.getLength("Hello Lambda here i am"));
	}

	
	
	static public void printIf(String[] s, Predicate<String> ch){
		for(String string : s){
			if(ch.test(string)) System.out.println(string);
		}
	}
	
	interface StringLengthLambda {
		int getLength(String s);
	}
}
