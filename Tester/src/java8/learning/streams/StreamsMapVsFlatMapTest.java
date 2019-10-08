package java8.learning.streams;

import java.util.Arrays;
import java.util.List;
import static java.util.stream.Collectors.toList;

public class StreamsMapVsFlatMapTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		/*
		 Streams support the method, which takes a function as argument. The function is map applied to each element, mapping it into a new element (the word mapping is used
		  because it has a meaning similar to transforming but with the nuance of “creating a new version of” rather than “modifying”).
		*/
		List<Dish> menu = Arrays.asList(
				new Dish("beef", false, 700, Dish.Type.MEAT),
				new Dish("chicken", false, 400, Dish.Type.MEAT),
				new Dish("french fries", true, 530, Dish.Type.OTHER),
				new Dish("rice", true, 350, Dish.Type.OTHER),
				new Dish("season fruit", true, 120, Dish.Type.OTHER),
				new Dish("pizza", true, 550, Dish.Type.OTHER),
				new Dish("prawns", false, 300, Dish.Type.FISH),
				new Dish("salmon", false, 450, Dish.Type.FISH) );
		
		List<String> dishNames = menu.stream()
				.map(Dish::getName)
				.collect(toList());

		//Example 2
		
		List<String> words = Arrays.asList("Modern", "Java", "In", "Action");
		List<Integer> wordLengths = words.stream()
		.map(String::length)
		.collect(toList());
		
		//Example 3: Just an example of calling multiple intermediate stream operations
		
		List<Integer> dishNameLengths = menu.stream()
				.map(Dish::getName)
				.map(String::length)
				.collect(toList());
		/*
		 * Stream flatMap(Function mapper) returns a stream consisting of the results of replacing each element of this stream with the contents of a mapped stream produced by applying the provided mapping function to each element. 
		 * Stream flatMap(Function mapper) is an intermediate operation. These operations are always lazy. Intermediate operations are invoked on a Stream instance and after they finish their processing, they give a Stream instance as output.
		   Note : Each mapped stream is closed after its contents have been placed into this stream. If a mapped stream is null, an empty stream is used, instead.

		  flatMap() V/s map() :
		  1) map() takes a Stream and transform it to another Stream. It applies a function on each element of Stream and store return value into new Stream. 
		   It does not flatten the stream. But flatMap() is the combination of a map and a flat operation i.e, it applies a function to elements as well as flatten them.
		  2) map() is used for transformation only, but flatMap() is used for both transformation and flattening.
		 */
		
		 // Creating a list of Prime Numbers 
        List<Integer> PrimeNumbers = Arrays.asList(5, 7, 11,13); 
          
        // Creating a list of Odd Numbers 
        List<Integer> OddNumbers = Arrays.asList(1, 3, 5); 
          
        // Creating a list of Even Numbers 
        List<Integer> EvenNumbers = Arrays.asList(2, 4, 6, 8); 
  
        List<List<Integer>> listOfListofInts = 
                Arrays.asList(PrimeNumbers, OddNumbers, EvenNumbers); 
  
        System.out.println("The Structure before flattening is : " + 
                                                  listOfListofInts); 
          
        // Using flatMap for transforming and flattening. 
        List<Integer> listofInts  = listOfListofInts.stream() 
                                    .flatMap(List::stream) 
                                    .collect(toList()); 
  
        System.out.println("The Structure after flattening is : " + 
                                                         listofInts);
        
		/*Q: Given two lists of numbers, how would you return all pairs of numbers? For example,
		given a list [1, 2, 3] and a list [3, 4] you should return [(1, 3), (1, 4), (2, 3), (2, 4),
		(3, 3), (3, 4)]. For simplicity, you can represent a pair as an array with two elements.*/
        
        List<Integer> first=Arrays.asList(1, 2, 3);
        List<Integer> second=Arrays.asList(3,4);
        
        List<int[]> pairs =
        		first.stream()
        		.flatMap(i -> second.stream()
        		.map(j -> new int[]{i, j})
        		)
        		.collect(toList());
        System.out.println("The Structure after flattening is : " + 
        		pairs);
        
	}

}
