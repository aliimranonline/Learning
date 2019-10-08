package java8.learning.streams;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import java8.learning.streams.Dish.Type;

import static java.util.stream.Collectors.toList;


public class StreamsFilteringTest {

	public StreamsFilteringTest() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		//Java 7 Sample For Low calories dish filtering
		List<Dish> menu = Arrays.asList(
				new Dish("beef", false, 700, Dish.Type.MEAT),
				new Dish("chicken", false, 400, Dish.Type.MEAT),
				new Dish("french fries", true, 530, Dish.Type.OTHER),
				new Dish("rice", true, 350, Dish.Type.OTHER),
				new Dish("season fruit", true, 120, Dish.Type.OTHER),
				new Dish("pizza", true, 550, Dish.Type.OTHER),
				new Dish("prawns", false, 300, Dish.Type.FISH),
				new Dish("salmon", false, 450, Dish.Type.FISH) );
		
		List<Dish> lowCaloricDishes = new ArrayList<>();
		for(Dish dish: menu) {
		if(dish.getCalories() < 400) {
		lowCaloricDishes.add(dish);
		}
		}
		Collections.sort(lowCaloricDishes, new Comparator<Dish>() {
		public int compare(Dish dish1, Dish dish2) {
		return Integer.compare(dish1.getCalories(), dish2.getCalories());
		}
		});
		List<String> lowCaloricDishesName = new ArrayList<>();
		for(Dish dish: lowCaloricDishes) {
		lowCaloricDishesName.add(dish.getName());
		}
		//End Java 7 Sample For Low calories dish filtering
		/*	
			To summarize, working with streams in general involves three items:
			.A data source (such as a collection) to perform a query on
			.A chain of intermediate operations that form a stream pipeline
			.A terminal operation that executes the stream pipeline and produces a result
			
		*/
		
		//Now Java 8 Filtering using streams for low calories; Also note how a multi line lambda is writtten
		List<String> listLowCaloricDishesName =
				menu.stream()
				.filter(d -> { System.out.println("Filtering: "+d.getName());
								return d.getCalories() < 400;
							}
				)
				.sorted(java.util.Comparator.comparing(Dish::getCalories))
				.map(Dish::getName)
				.collect(java.util.stream.Collectors.toList());
		System.out.println("List Size:"+listLowCaloricDishesName.size());
		//End Java 8 Filtering using streams for low calories
		
		//Java 8 Get 3 High Calories Dishes
		List<Dish> threeHighCaloricDishNames =
		menu.stream()
		.filter(dish -> dish.getCalories() > 300)
		.map(d -> new Dish(d.getName(), d.isVegetarian(), d.getCalories(), d.getType()))
		.limit(3)
		.collect(toList());
		System.out.println("Three High Calories Dishes:"+threeHighCaloricDishNames.size());
		//End Java 8 Get 3 High Calories Dishes
		
		//See How Streams are Performing Intermediate and Terminal Operations
		
		List<String> names =
				menu.stream()
				.filter(dish -> {
				System.out.println("filtering:" + dish.getName());
				return dish.getCalories() > 300;
				})
				.map(dish -> {
				System.out.println("mapping:" + dish.getName());
				return dish.getName();
				})
				.limit(3)
				.collect(toList());
				System.out.println(names);
		
		//End See How Streams are Performing Intermediate and Terminal Operations
				
		//Filtering with a Predicate		
				List<Dish> vegetarianMenu = menu.stream()
						.filter(Dish::isVegetarian)
						.collect(toList());
		//End Filtering with a Predicate
				
		//Filtering unique even elements
				List<Integer> numbers = Arrays.asList(1, 2, 1, 3, 3, 2, 4);
				numbers.stream()
				.filter(i -> i % 2 == 0)
				.distinct()
				.forEach(System.out::println);
		//End Filtering unique even elements		
	}

}
