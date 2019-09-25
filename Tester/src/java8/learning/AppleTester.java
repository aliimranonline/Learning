package java8.learning;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class AppleTester {

	public AppleTester() {
		// TODO Auto-generated constructor stub
	}
	
	public static <T> List<T> filter(List<T> list, Predicate<T> p) {
			List<T> result = new ArrayList<>();
			for(T e: list) {
				if(p.test(e)) {
				result.add(e);
				}
			}
			return result;
	}
	
	public static void prettyPrintApple(List<Apple> inventory, ApplePrettyPrinter printer) {
		for(Apple apple: inventory) {
			String output = printer.accept(apple);
			System.out.println(output);
		}
	}
	public static List<Apple> filterApples(List<Apple> apples,
		ApplePredicate p) {
		List<Apple> result = new ArrayList<>();
			for (Apple apple : apples) {
				if (p.test(apple)){
					result.add(apple);
				}
			}
		return result;
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		List<Apple> apples=new ArrayList<>();
		apples.add(new Apple(150,"RED"));
		apples.add(new Apple(110,"GREEN"));
		ApplePrettyPrinter printer=new AppleSimpleWeightPrinter();
		prettyPrintApple(apples, printer);
		
		printer=new AppleFancyPrinter();
		prettyPrintApple(apples, printer);
		//Filtering based on Anonymous Class Predicate implementation instead of concrete classes
		List<Apple> redApples=filterApples(apples, new ApplePredicate() {
			
			@Override
			public boolean test(Apple apple) {
				// TODO Auto-generated method stub
				return "RED".equalsIgnoreCase(apple.getColor());
			}
		}) ; 
		System.out.println("Only RED Apples: "+redApples.size());
		
		List<Apple> lembdaFilteredRedApples=filterApples(apples, (Apple apple) ->"RED".equalsIgnoreCase(apple.getColor()) ); 
		
		System.out.println("Filtered By Lambda Only RED Apples: "+lembdaFilteredRedApples.size());
		//Filter by Built In Predicate Class
		
		List<Apple> filterByPredicateRedApples =filter(apples, (Apple apple) -> "RED".equalsIgnoreCase(apple.getColor()));
		
		System.out.println("Filtered By Built In Predicate For RED Apples: "+filterByPredicateRedApples.size());
		
	}

}
