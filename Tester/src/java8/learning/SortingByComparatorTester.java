package java8.learning;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class SortingByComparatorTester {

	public SortingByComparatorTester() {
		// TODO Auto-generated constructor stub
	}
	public static void main(String[] args) {
		List<Apple> apples=new ArrayList<>();
		apples.add(new Apple(150,"RED"));
		apples.add(new Apple(110,"GREEN"));
		apples.add(new Apple(160,"YELLOW"));
		//Sorting directly on Java 8 List using anonymous inner class
		apples.sort(new Comparator<Apple>() {
				public int compare(Apple a1, Apple a2) {
					return a1.getWeight().compareTo(a2.getWeight());
				}
		});
		System.out.print(apples.size());
		
		//Sorting using LAMBDA instead of anonymous inner class
		
		apples.sort((Apple a1, Apple a2) -> a1.getWeight().compareTo(a2.getWeight()));
		
		//OR Using Comparator's static method comparing Which also returns a Comparator based on Key and Value
		apples.sort(java.util.Comparator.comparing(Apple::getWeight));
		
		System.out.print("Lambda Comparator Sorted Apples"+apples.size());
	}

}
