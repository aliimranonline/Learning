package java8.learning.streams;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class StreamsFindingAndMatchingTest {

	public StreamsFindingAndMatchingTest() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		List<Dish> menu = Arrays.asList(
				new Dish("beef", false, 700, Dish.Type.MEAT),
				new Dish("chicken", false, 400, Dish.Type.MEAT),
				new Dish("french fries", true, 530, Dish.Type.OTHER),
				new Dish("rice", true, 350, Dish.Type.OTHER),
				new Dish("season fruit", true, 120, Dish.Type.OTHER),
				new Dish("pizza", true, 550, Dish.Type.OTHER),
				new Dish("prawns", false, 300, Dish.Type.FISH),
				new Dish("salmon", false, 450, Dish.Type.FISH) );
		
		//Example:anyMatch
		if(menu.stream().anyMatch(Dish::isVegetarian)) {
			System.out.println("The menu is (somewhat) vegetarian friendly!!");
		}
		//End Example:anyMatch
		
		//Example:allMatch
		boolean isHealthy = menu.stream()
							.allMatch(dish -> dish.getCalories() < 1000);
		//End Example:allMatch
		
		//Example:noneMatch
		boolean isSuperHealthy = menu.stream()
								 .noneMatch(d -> d.getCalories() >= 1000);
		//End Example:noneMatch
		
		//Example:findAny
		Optional<Dish> dish =
				menu.stream()
				.filter(Dish::isVegetarian)
				.findAny();
		//End Example:findAny
		
		//Example:findFirst
		List<Integer> someNumbers = Arrays.asList(1, 2, 3, 4, 5);
		Optional<Integer> firstSquareDivisibleByThree =
		someNumbers.stream()
		.map(n -> n * n)
		.filter(n -> n % 3 == 0)
		.findFirst(); // 9
		//End Example:findFirst
	}

}
