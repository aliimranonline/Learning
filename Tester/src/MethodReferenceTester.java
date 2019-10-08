import java.util.Comparator;
import java.util.List;
import java.util.function.BiPredicate;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.function.ToIntFunction;

import java8.learning.Apple;

public class MethodReferenceTester {

	public MethodReferenceTester() {
		// TODO Auto-generated constructor stub
	}

	public boolean startsWithNumber(String str){
		return true;
	}
	public void SampleReferences(){

		//This class shows lambda short hand as method references
		//Sample Lambda
		ToIntFunction<String> stringToInt =	(String s) -> Integer.parseInt(s);
		//Equivalent Method Reference
		stringToInt=Integer::parseInt;
		
		//Sample Lambda
		BiPredicate<List<String>, String> contains =(list, element) -> list.contains(element);
		//Equivalent Method Reference
		contains=List::contains;
		
		//Sample Lambda
		Predicate<String> startsWithNumber =(String string) -> this.startsWithNumber(string);
		//Tricky One: If a method reference points to this then use this:methodName
		startsWithNumber=this::startsWithNumber;
		
		//You can also use  same techniques as above 3 for Constructor References
		//Sample Lambda
		Supplier<Apple> c1 = () -> new Apple();
		//Equivalent Constructor Reference
		c1=Apple::new;
		
		Comparator<Apple> test=null;
		
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
