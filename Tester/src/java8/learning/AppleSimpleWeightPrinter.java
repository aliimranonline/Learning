package java8.learning;

public class AppleSimpleWeightPrinter implements ApplePrettyPrinter {

	public AppleSimpleWeightPrinter() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public String accept(Apple a) {
		// TODO Auto-generated method stub
		return "Apple Weight is:"+a.getWeight();
	}

}
