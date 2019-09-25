package java8.learning;

public class AppleFancyPrinter implements ApplePrettyPrinter {

	public AppleFancyPrinter() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public String accept(Apple a) {
		// TODO Auto-generated method stub
		String characteristic = a.getWeight() >= 150 ? "heavy" :
			"light";
			return "A " + characteristic +
			" " + a.getColor() +" apple";
	}

}
