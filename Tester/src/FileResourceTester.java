import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.ToIntBiFunction;

import java8.learning.Apple;

public class FileResourceTester {

	  private ExecutorService executor = Executors.newFixedThreadPool(3);
	public FileResourceTester() {
		// TODO Auto-generated constructor stub
	}

	public String processFile() throws IOException {
		try (BufferedReader br =
		new BufferedReader(new FileReader("E:\\MyWorkSpace\\Tester\\src\\data.txt"))) {
			return br.readLine();
		}
	}
	//Passed A Functional Interface (BufferedReaderProcessor) to a method
	public String processFile(BufferedReaderProcessor p) throws IOException {
		try (BufferedReader br =
				new BufferedReader(new FileReader("E:\\MyWorkSpace\\Tester\\src\\data.txt"))) {
				return p.process(br);
		}
	}
	
	//Custom ForEach with Consumer Interface
	public <T> void forEach(List<T> list, Consumer<T> c) {
		for(T t: list) {
			c.accept(t);
		}
	}
	
	//Custom Map with Function Functional Interface
	
	public <T, R> List<R> map(List<T> list, Function<T, R> f) {
		List<R> result = new ArrayList<>();
		for(T t: list) {
			result.add(f.apply(t));
		}
		return result;
	}
	
	public void localVariableMultithreading() {
		boolean run = true;
		executor.execute(() -> {
		while (run) {}});
		
		// commented because it doesn't compile, it's just an example of non-final local variables in lambdas
        // run = false;
	}
	public static void main(String[] args) throws IOException {
		
		FileResourceTester tester=new FileResourceTester();
		System.out.println(tester.processFile());
		//Passed Lambda as Functional Interface Implementation
		String twoLines =tester.processFile((BufferedReader br) -> br.readLine() + br.readLine());
		System.out.println(twoLines);
		
		//Same method with different lambda expression
		String oneLine =tester.processFile((BufferedReader br) -> br.readLine());
		System.out.println(oneLine);
		
		//Custom For Each Loop Consumer Example
		
		tester.forEach(
				Arrays.asList(1,2,3,4,5),
				(Integer i) -> System.out.println(i)
				);
		//OR you can also declare a variable of type FunctionalInterface Consumer; just a sample to see how lambda can be assigned to It's FunctionalInterface Variable
		Consumer<Integer> consumerLambda=(Integer i) -> System.out.println(i);
		tester.forEach(
				Arrays.asList(1,2,3,4,5),
				consumerLambda
				);
		
		//Custom Map with Lambda implementation of Function Functional Interface
		//Accepts Type String and Returns Type Integer i.e Function<T, R> f
		List<Integer> l = tester.map(
				Arrays.asList("lambdas", "in", "action"),
				(String s) -> s.length()
				);
		tester.forEach(
				l,
				(Integer i) -> System.out.println(i)
				);
		
		//Same Lambda With different Functional Interfaces
		
				Comparator<Apple> c1 =(Apple a1, Apple a2) -> a1.getWeight().compareTo(a2.getWeight());
				ToIntBiFunction<Apple, Apple> c2 =	(Apple a1, Apple a2) -> a1.getWeight().compareTo(a2.getWeight());
				BiFunction<Apple, Apple, Integer> c3 =(Apple a1, Apple a2) -> a1.getWeight().compareTo(a2.getWeight());
	}

}
