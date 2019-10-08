package java8.learning.streams;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class StreamsReduceTest {

	public StreamsReduceTest() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {
		// Reduction stream operations allow us to produce one single result from a sequence of elements, 
		// by applying repeatedly a combining operation to the elements in the sequence.
		
		List<Integer> numbers=new ArrayList<Integer>();
		numbers.add(1);
		numbers.add(2);
		numbers.add(3);
		numbers.add(4);
		numbers.add(5);
		
		int sum = numbers.stream().reduce(0, (a, b) -> a + b);
		System.out.println("Sum: "+sum);
		
		
		int neg=numbers.stream().reduce(0, (a, b) -> a - b);
		System.out.println("Neg: "+neg);
		//See Initial value passed as 1 :)
		int mul=numbers.stream().reduce(1, (a, b) -> a * b);
		System.out.println("Mul: "+mul);
		
		List<Dish> menu = Arrays.asList(
				new Dish("beef", false, 700, Dish.Type.MEAT),
				new Dish("chicken", false, 400, Dish.Type.MEAT),
				new Dish("french fries", true, 530, Dish.Type.OTHER),
				new Dish("rice", true, 350, Dish.Type.OTHER),
				new Dish("season fruit", true, 120, Dish.Type.OTHER),
				new Dish("pizza", true, 550, Dish.Type.OTHER),
				new Dish("prawns", false, 300, Dish.Type.FISH),
				new Dish("salmon", false, 450, Dish.Type.FISH) );
		//Q:How would you count the number of dishes in a stream using the map	and	reduce methods?
		//See Method Reference Used instead of Lambda in reduce()
		Optional<Integer>count=menu.stream().map(i -> 1).reduce(Integer::sum);
		System.out.println("Total Dishes: "+count.get());
	}

}
