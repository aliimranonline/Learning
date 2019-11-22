package java8.learning.streams;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;



public class StreamsCollector {

	public static void main(String[] args) {
		Trader raoul = new Trader("Raoul", "Cambridge");
		Trader mario = new Trader("Mario","Milan");
		Trader alan = new Trader("Alan","Cambridge");
		Trader brian = new Trader("Brian","Cambridge");
		List<Transaction> transactions = Arrays.asList(
		new Transaction(brian, 2011, 300,"Dollar"),
		new Transaction(raoul, 2012, 1000,"Dollar"),
		new Transaction(raoul, 2011, 400,"Euro"),
		new Transaction(mario, 2012, 710,"Dollar"),
		new Transaction(mario, 2012, 700,"Euro"),
		new Transaction(alan, 2012, 950,"Euro")
		);
		
		// Imperative Programming Style :Java 7 Sample
				Map<String, List<Transaction>> transactionsByCurrenciesOld = new HashMap();
						for (Transaction transaction : transactions) {
						String currency = transaction.getCurrency();
						List<Transaction> transactionsForCurrency =
								transactionsByCurrenciesOld.get(currency);
						if (transactionsForCurrency == null) {
						transactionsForCurrency = new ArrayList<Transaction>();
						transactionsByCurrenciesOld
						.put(currency, transactionsForCurrency);
						}
						transactionsForCurrency.add(transaction);
						}
				//End Java 7 Sample
				Map<String,List<Transaction>> currencyTransactions=transactions.stream().collect(Collectors.groupingBy(Transaction::getCurrency));
				currencyTransactions.values().toArray();
				
		
		List<Dish> menu = Arrays.asList(
				new Dish("beef", false, 700, Dish.Type.MEAT),
				new Dish("chicken", false, 400, Dish.Type.MEAT),
				new Dish("french fries", true, 530, Dish.Type.OTHER),
				new Dish("rice", true, 350, Dish.Type.OTHER),
				new Dish("season fruit", true, 120, Dish.Type.OTHER),
				new Dish("pizza", true, 550, Dish.Type.OTHER),
				new Dish("prawns", false, 300, Dish.Type.FISH),
				new Dish("salmon", false, 450, Dish.Type.FISH) );
		
		//Using Collector's Factory Method counting
		long howManyDishes = menu.stream().collect(Collectors.counting());
		//Max and Min in a Stream
		Comparator<Dish> dishCaloriesComparator =
				Comparator.comparingInt(Dish::getCalories);
				Optional<Dish> mostCalorieDish =
				menu.stream()
				.collect(Collectors.maxBy(dishCaloriesComparator));
		
		//Using Collectors we can Sum Int Values
		int totalCalories = menu.stream().collect(Collectors.summingInt(Dish::getCalories));
		//Using Summary Statistics to get Summary
		//you can count the elements in the menu and obtain the sum, average,maximum, and minimum of the calories contained in each dish with a single
		//operation:summarizing
		IntSummaryStatistics menuStatistics =
				menu.stream().collect(Collectors.summarizingInt(Dish::getCalories));
		System.out.println("Summary Statistics: "+menuStatistics);
		
		//Joining Strings Using Collectors
		
		String shortMenu = menu.stream().map(Dish::getName).collect(Collectors.joining());
		System.out.println("Join Dish Names: "+shortMenu);
		
		String shortMenuDelimiter = menu.stream().map(Dish::getName).collect(Collectors.joining(" ,"));
		System.out.println("Join Dish Names With Delimiter: "+shortMenuDelimiter);
		
		Stream<Integer> stream = Arrays.asList(1, 2, 3, 4, 5, 6).stream();
		List<Integer> numbers = stream.reduce(new ArrayList<Integer>(), (List<Integer> l, Integer e) -> {
			l.add(e);
			return l;
		}, (List<Integer> l1, List<Integer> l2) -> {
			l1.addAll(l2);
			return l1;
		});
		//You can also use reducing on from collectors in combination of transform function (getCalories) and aggregate function(sum)
		totalCalories = menu.stream().collect(Collectors.reducing(0,
				Dish::getCalories,
				Integer::sum));
		
		//Grouping: Filtering dishes having calories > 500
		
		Map<Dish.Type, List<Dish>> caloricDishesByType =
				menu.stream()
				.collect(Collectors.groupingBy(Dish::getType,
						Collectors.filtering(dish -> dish.getCalories() > 500, Collectors.toList())));
		
		System.out.println("High Caloric Dishes: "+caloricDishesByType);
		
		//Grouping: convert	each Dish in the groups into their respective names
		Map<Dish.Type, List<String>> dishNamesByType =
				menu.stream()
				.collect(Collectors.groupingBy(Dish::getType,
						Collectors.mapping(Dish::getName, Collectors.toList())));
		
		System.out.println("Group Dish Names By Type: "+dishNamesByType);
		
		//FlatMap with Grouping: Extracting Tags using FlatMap
		
		Map<String, List<String>> dishTags = new HashMap<>();
		dishTags.put("beef", Arrays.asList("salty", "roasted"));
		dishTags.put("chicken", Arrays.asList("fried", "crisp"));
		dishTags.put("french fries", Arrays.asList("greasy", "fried"));
		dishTags.put("rice", Arrays.asList("light", "natural"));
		dishTags.put("season fruit", Arrays.asList("fresh", "natural"));
		dishTags.put("pizza", Arrays.asList("tasty", "salty"));
		dishTags.put("prawns", Arrays.asList("tasty", "roasted"));
		dishTags.put("salmon", Arrays.asList("delicious", "fresh"));
		
		Map<Dish.Type, Set<String>> flatMapdishNamesByType =
				menu.stream()
				.collect(Collectors.groupingBy(Dish::getType,
						Collectors.flatMapping(dish -> dishTags.get( dish.getName() ).stream(),
						Collectors.toSet())));
		System.out.println("FlashMap Dish Names By Type: "+flatMapdishNamesByType);
		
		//MultiLevel Grouping:
		
		Map<Dish.Type, Map<CaloricLevel, List<Dish>>> dishesByTypeCaloricLevel = menu.stream()
				.collect(Collectors.groupingBy(Dish::getType, Collectors.groupingBy(dish -> {
					if (dish.getCalories() <= 400)
						return CaloricLevel.DIET;
					else if (dish.getCalories() <= 700)
						return CaloricLevel.NORMAL;
					else
						return CaloricLevel.FAT;
				})));
		
		System.out.println("MultiLevel Grouping Dishes: "+dishesByTypeCaloricLevel);
		
		//Example of SubGroups:Collectors.Count the number of Dishes in the menu for each type
		
		Map<Dish.Type, Long> typesCount = menu.stream().collect(
				Collectors.groupingBy(Dish::getType, Collectors.counting()));
		
		System.out.println("Sub Group Dishes Count: "+typesCount);
		
		//ADAPTING THE COLLECTOR RESULT TO A DIFFERENT TYPE
		Map<Dish.Type, Dish> mostCaloricByType =
				menu.stream()
				.collect(Collectors.groupingBy(Dish::getType,
						Collectors.collectingAndThen(
						Collectors.maxBy(Comparator.comparingInt(Dish::getCalories)),
				Optional::get)));
		
		System.out.println("Most Caloric By Type: "+mostCaloricByType);

	}

}
