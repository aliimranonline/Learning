package java8.learning.streams;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

public class StreamsSlicingTest {

	public StreamsSlicingTest() {
		// TODO Auto-generated constructor stub
	}

	//Note: This code would run on Java 9 onwards
	public static void main(String[] args) {
		//Streams Slicing with takeWhile Method
		//takeWhile: takeWhile is similar to filter in the sense that it expects a predicate and returns a new stream consisting only of the elements that match the given predicate. 
		//But there’s a catch. In an ordered stream, takeWhile takes elements from the initial stream while the predicate holds true. 
		//Meaning that when an element is encountered that does not match the predicate, the rest of the stream is discarded. 
		//Let’s have a look at the following example.
		
		Stream.of(2, 4, 6, 8, 9, 10, 12)
	    .takeWhile(n -> n % 2 == 0)
	    .forEach(System.out::println);
		// prints out:
		// 2
		// 4
		// 6
		// 8
		//In this example, we’re taking even numbers from the initial stream until the first odd number is encountered. 
		//The stream returned by takeWhile contains 2, 4, 6, 8. It does not contain 9 since it did not match the predicate. 
		//Although 10 and 12 are even, they’re not included in the returned stream as well because the stream operation was cut off when 9 was encountered.
		
		
		//Streams Slicing with dropWhile Method
		//dropWhile:dropWhile is essentially the opposite of takeWhile. Instead of taking elements from the stream until the first element which does not match the predicate, 
		//dropWhile drops these elements and includes the remaining elements in the returned stream.The following is the same example we used previously with one noteworthy difference. 
		//takeWhile has been replaced with dropWhile.
		
		Stream.of(2, 4, 6, 8, 9, 10, 12)
	    .dropWhile(n -> n % 2 == 0)
	    .forEach(System.out::println);
		// prints out:
		// 9
		// 10
		// 12
		
		//In an ordered stream, dropWhile removes the longest contiguous sequence of elements that match the given predicate. 
		//In this example we’re dropping even numbers. 2, 4, 6 and 8 are removed because applying the predicate on them returns true. 
		//9 isn’t an even number and is therefore included in the result. Even though 10 and 12 are even numbers, 
		//they’re included in the result because they came after the first element which failed the predicate.
		
		
		//Stream Truncate Example Using Limit
		List<Dish> specialMenu = Arrays.asList(
				new Dish("beef", false, 700, Dish.Type.MEAT),
				new Dish("chicken", false, 400, Dish.Type.MEAT),
				new Dish("french fries", true, 530, Dish.Type.OTHER),
				new Dish("rice", true, 350, Dish.Type.OTHER),
				new Dish("season fruit", true, 120, Dish.Type.OTHER),
				new Dish("pizza", true, 550, Dish.Type.OTHER),
				new Dish("prawns", false, 300, Dish.Type.FISH),
				new Dish("salmon", false, 450, Dish.Type.FISH) );
		List<Dish> dishes = specialMenu
				.stream()
				.filter(dish -> dish.getCalories() > 300)
				.limit(3)
				.collect(toList());
		//End Stream Truncate Example Using Limit
		
		//Stream Skipping Elements Example Using skip::::Streams support the method to return a stream that discards the first n elements.
		//If the stream has fewer than n elements, an empty stream is returned.
		List<Dish> skippedDishes = specialMenu.stream()
				.filter(d -> d.getCalories() > 300)
				.skip(2)
				.collect(toList());
		
		//End Stream Skipping Elements Example Using skip
	}

}
