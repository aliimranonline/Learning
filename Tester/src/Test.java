import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@FunctionalInterface
interface MyFunctionalInterface {

	//A method with no parameter
    public String sayHello();
}
public class Test {

	MyFunctionalInterface msg = () -> {
		return "Hello";
	};
   public Test() {
		super();
		// TODO Auto-generated constructor stub
	}

	public static void main(String args[]) {
	        // lambda expression
	/*	Test t=new Test();
	        System.out.println(t.msg.sayHello());*/
		Optional<Integer> possible = Optional.ofNullable(5);
		possible.ifPresent(System.out::println);
	    
		List<Integer> integers = Arrays.asList(1,12,433,5);
        
		Optional<Integer> max = integers.stream().reduce( Math::max );
		 
		max.ifPresent(value -> System.out.println(value));
		
	}

}