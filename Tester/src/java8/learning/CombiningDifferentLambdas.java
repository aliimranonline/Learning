package java8.learning;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.Comparator;
public class CombiningDifferentLambdas {

	public CombiningDifferentLambdas() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		List<Apple> apples=new ArrayList<>();
		apples.add(new Apple(150,"RED", "PAK"));
		apples.add(new Apple(110,"GREEN","USA"));
		apples.add(new Apple(160,"YELLOW","PAK"));
		
		//Multiple Comparators lambda Chained example
		
		apples.sort(java.util.Comparator.comparing(Apple::getWeight)
				.reversed()
				.thenComparing(Apple::getCountry));
		System.out.println("Check Apples Sorted by Country:"+apples.size());
		
		//Multiple Predicates Lambda Chained Example
		Predicate<Apple> redApple= (Apple a) -> a.getColor().equalsIgnoreCase("RED");
		Predicate<Apple> notRedApple = redApple.negate();
		System.out.println("Is This Apple RED ? :"+notRedApple.test(apples.get(1)));
		
		
		//Multiple Functional Lambda Chained Example 1: In mathematics you’d write g(f(x)) or (g o f)(x).
		
		Function<Integer, Integer> f = x -> x + 1;
		Function<Integer, Integer> g = x -> x * 2;
		Function<Integer, Integer> h = f.andThen(g);
		int result = h.apply(1);
		System.out.println("Result :"+result);
		
		//Multiple Functional Lambda Chained Example 2 With Compose Method: In mathematics you’d write f(g(x)) or (f o g)(x).
		
		Function<Integer, Integer> i = f.compose(g);
		int resultOfCompose = i.apply(1);
		System.out.println("Result Of Compose :"+resultOfCompose);
		
		
		

	}

}
